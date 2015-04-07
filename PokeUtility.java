import java.io.*;
import javax.swing.*;
import java.net.*;

public class PokeUtility {
    public static void main(String args[]) {
        Search Search = new Search();
        Pokemon bulb = (Pokemon) Search.loadData("Bulbasuar.obj");
        System.out.println(bulb.getDexNumber());
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
    public Pokemon loadData(String filename) {
        Pokemon data = null;
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File("pokedex/" + filename)));
            data = (Pokemon) ois.readObject();
            ois.close();
        } catch(Exception e) {
            System.err.println(e);
        }
        return data;
    }
}

class Pokemon implements Serializable {
    public String dexNumber;
    public String name;
    public String type;
    public String ability;
    public String hiddenAbility;
    public String evolution;
    
    public String getDexNumber() {
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
    public String getHiddenAbility() {
        return this.hiddenAbility;
    }
    public String getEvolution() {
        return this.evolution;
    }
    public String get(String attribute) {
        switch (attribute) {
            case "dexNumber": return this.dexNumber;
            case "name": return this.name;
            case "type": return this.type;
            case "ability": return this.ability;
            case "hiddenAbility": return this.hiddenAbility;
            case "evolution": return this.evolution;
            default: return "Error not an attribute.";
        }
    }
    
    public void set(String dexNumber, String name, String type, String ability, String hiddenAbility, String evolution) {
        this.dexNumber = dexNumber;
        this.name = name;
        this.type = type;
        this.ability = ability;
        this.hiddenAbility = hiddenAbility;
        this.evolution = evolution;
    }
}