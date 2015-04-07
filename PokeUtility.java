import java.io.*;
import javax.swing.*;
import java.net.*;

public class PokeUtility {
    public static void main(String args[]) {
        Search Search = new Search();
    }
}

class Search {
    public void searchByName(String keyword) {
        
    }
    public String[] names() {
        String[] namesOfPokemon = null;
        try {
            File dexFolder = new File("pokedex");
            namesOfPokemon = dexFolder.list();
        } catch(Exception e) {
            System.err.println(e);
        }
        return namesOfPokemon;
    }
}

class Pokemon implements Serializable {
    public int dexNumber;
    public String name;
    public String type;
    public String ability;
    public String evolution;
    
    public int getDexNumber() {
        return this.dexNumber;
    }
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public String getAbility() {
        return this.ability;
    }
    public String getEvolution() {
        return this.evolution;
    }
    public String get(String attribute) {
        switch (attribute) {
            case "dexNumber": return String.valueOf(this.dexNumber);
            case "name": return this.name;
            case "type": return this.type;
            case "ability": return this.ability;
            case "evolution": return this.evolution;
            default: return "Error not an attribute.";
        }
    }
}