import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.util.jar.*;

public class PokeUtility {
    public static void main(String args[]) {
        Search Search = new Search();
        
//        Search.names();
        
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
        java.util.List<String> namesOfPokemon = new ArrayList<String>();
        try {
            JarFile jf = new JarFile("PokeUtility.jar");
            Enumeration<JarEntry> je = jf.entries();
            JarEntry entry;
            String name;
            while (je.hasMoreElements()) {
                name = je.nextElement().getName();
                if (name.startsWith("resources/pokedex/")) namesOfPokemon.add(name.substring(18, name.length() - 4).toLowerCase());
            }
        } catch(Exception e) {
            System.err.println(e);
        }
        return toPrimitiveString(namesOfPokemon);
    }
    public Pokemon loadData(String name) {
        Pokemon data = null;
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(this.getClass().getResourceAsStream("resources/pokedex/" + name.substring(0, 1).toUpperCase() + name.substring(1) + ".obj"));
            data = (Pokemon) ois.readObject();
            ois.close();
        } catch(Exception e) {
            System.err.println(e);
        }
        return data;
    }
    public String[] toPrimitiveString(java.util.List<String> objString) {
        String[] string = new String[objString.size()];
        for (int i = 0; i < objString.size(); i++) {
            string[i] = objString.get(i);
        }
        return string;
    }
}

class PokeUtilityGUI implements Runnable {
    public void run() {
        
        Font customFont = null;
        try {customFont = Font.createFont(Font.PLAIN, this.getClass().getResourceAsStream("resources/PressStart2P.ttf")).deriveFont(Font.PLAIN, 14);}
        catch (Exception e) {System.err.println("Error loading font: " + e);}
        
        JFrame window = new JFrame("Pokemon Utility");
        window.setSize(700, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        
        JTextPane content = new JTextPane();
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
    public JTextPane output;
    public SearchInput(JTextField input, JTextPane output) {
        this.input = input;
        this.output = output;
    }
    public void actionPerformed(ActionEvent ae) {
        Search Search = new Search();
        output.setText("");
        String[] results = Search.searchByName(input.getText());
        Pokemon result;
        
        StyledDocument sd = output.getStyledDocument();
        Style icon = sd.addStyle("icon", null);
        
        for (int i = 0; i < results.length; i++) {
            result = Search.loadData(results[i]);
            StyleConstants.setIcon(icon, new ImageIcon(result.getIcon()));
            try {
                sd.insertString(sd.getLength(), (i + 1) + ". #" + result.getDexNumber() + " " + result.getName() + "\n", null);
                sd.insertString(sd.getLength(), "icon\n", icon);
                sd.insertString(sd.getLength(), "\tType:" + result.getType() + "\n\tDescription:" + result.getDescription() + "\n\n", null);
            } catch(Exception e) {
                System.err.println(e);
            }
        }
        output.setStyledDocument(sd);
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
