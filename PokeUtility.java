import java.io.*;
import javax.swing.*;
import java.net.*;
import java.util.*;

public class PokeUtility {
    public static void main(String args[]) {
        Search Search = new Search();
        
        System.out.println("Files found:");
        String[] files = Search.names();
        System.out.println(files.length);
        
        System.out.println("Results for '" + args[0] + "'...");
        String[] results = Search.searchByName(args[0]);
        Pokemon result;
        for (int i = 0; i < results.length; i++) {
            result = Search.loadData(results[i]);
            System.out.println((i + 1) + ". " + result.getDexNumber() + " " + result.getName());
            System.out.println();
            System.out.println("\tType:" + result.getType());
            System.out.println();
        }
    }
}

class Search {
    public String[] searchByName(String keyword) {
        keyword = keyword.toLowerCase();
        String[] names = this.names();
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < names.length; i++) {
            if (names[i].contains(keyword)) results.add(names[i]);
        }
        return results.toArray(new String[results.size()]);
    }
    public String[] names() {
        String[] namesOfPokemon = null;
        try {
            File dexFolder = new File("pokedex");
            namesOfPokemon = dexFolder.list();
        } catch(Exception e) {
            System.err.println(e);
        }
        for (int a = 0; a < namesOfPokemon.length; a++) namesOfPokemon[a] = namesOfPokemon[a].substring(0, namesOfPokemon[a].length() - 4).toLowerCase();
        return namesOfPokemon;
    }
    public Pokemon loadData(String name) {
        Pokemon data = null;
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File("pokedex/" + name.substring(0, 1).toUpperCase() + name.substring(1) + ".obj")));
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
    public String description;
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
    public String getDescription() {
        return this.description;
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
    
    public void set(String dexNumber, String name, String type, String description, String ability, String hiddenAbility, String evolution) {
        this.dexNumber = dexNumber;
        this.name = name;
        this.type = type;
        this.description = description;
        this.ability = ability;
        this.hiddenAbility = hiddenAbility;
        this.evolution = evolution;
    }
}