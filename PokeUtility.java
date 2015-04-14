import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;

public class PokeUtility {
    public static void main(String args[]) {
        Search Search = new Search();
        
        System.out.println("Files found:");
        String[] files = Search.names();
        System.out.println(files.length);
        
        SwingUtilities.invokeLater(new PokeUtilityGUI());
    }
}

class Search {
    public String[] searchByName(String keyword) {
        keyword = keyword.toLowerCase();
        String[] names = this.names();
        java.util.List<String> results = new ArrayList<String>();
        for (int i = 0; i < names.length; i++) {
            if (names[i].contains(keyword)) results.add(names[i]);
        }
        return results.toArray(new String[results.size()]);
    }
    public String[] names() {
        String[] namesOfPokemon = null;
        try {
            File dexFolder = new File("resources/pokedex");
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
            ois = new ObjectInputStream(new FileInputStream(new File("resources/pokedex/" + name.substring(0, 1).toUpperCase() + name.substring(1) + ".obj")));
            data = (Pokemon) ois.readObject();
            ois.close();
        } catch(Exception e) {
            System.err.println(e);
        }
        return data;
    }
}

class PokeUtilityGUI implements Runnable {
    public void run() {
        
        Font customFont = null;
        try {customFont = Font.createFont(Font.PLAIN, new File("resources/PressStart2P.ttf")).deriveFont(Font.PLAIN, 14);}
        catch (Exception e) {System.err.println("Error loading font: " + e);}
        
        JFrame window = new JFrame("Pokemon Utility");
        window.setSize(700, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        
        JTextArea content = new JTextArea();
        content.setFont(customFont);
        content.setEditable(false);
        content.setMargin(new Insets(10, 10, 10, 10));
        
        DefaultCaret caret = (DefaultCaret)content.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        
        JScrollPane jsp = new JScrollPane(content);
        window.add(jsp, BorderLayout.CENTER);
        
        JTextField input = new JTextField();
        input.setFont(customFont);
        input.setMargin(new Insets(3, 3, 3, 3));
        input.addActionListener(new SearchInput(input, content));
        window.add(input, BorderLayout.PAGE_START);
        
    }
}

class SearchInput implements ActionListener {
    public JTextField input;
    public JTextArea output;
    public SearchInput(JTextField input, JTextArea output) {
        this.input = input;
        this.output = output;
    }
    public void actionPerformed(ActionEvent ae) {
        Search Search = new Search();
        output.setText("");
        String[] results = Search.searchByName(input.getText());
        Pokemon result;
        for (int i = 0; i < results.length; i++) {
            result = Search.loadData(results[i]);
            output.append((i + 1) + ". " + result.getDexNumber() + " " + result.getName() + "\n\n\tType:" + result.getType() + "\n\tDescription:" + result.getDescription() + "\n\n");
        }
        input.setText("");
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
    public byte[] icon;
    
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
    public byte[] getIcon() {
        return this.icon;
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
    
    public void set(String dexNumber, String name, String type, String description, String ability, String hiddenAbility, String evolution, byte[] icon) {
        this.dexNumber = dexNumber;
        this.name = name;
        this.type = type;
        this.description = description;
        this.ability = ability;
        this.hiddenAbility = hiddenAbility;
        this.evolution = evolution;
        this.icon = icon;
    }
}
