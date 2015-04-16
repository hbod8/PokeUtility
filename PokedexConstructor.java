import java.io.*;
import java.util.*;

public class PokedexConstructor {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/pokedex.txt"));
        String data;
        String[] attr = new String[10];
        Pokemon newPkmn = new Pokemon();
        FileInputStream fis;
        byte[] imageData;
        byte iByte;
        File file = null;
        while ((data = br.readLine()) != null) {
            attr = data.substring(1, data.length() - 1).split("/");
            
            try {
                file = new File("resources/icons/" + attr[0] + ".png");
            } catch (Exception e) {
                file = null;
            }
            
            fis = new FileInputStream(file);
            imageData = new byte[(int)file.length()];
            fis.read(imageData);
            fis.close();
            
            newPkmn.set(attr[0], attr[1], attr[2], "NA", "NA", "NA", "NA", imageData);
            saveObject(attr[1], newPkmn);
            System.out.println(attr[1]);
        }
    }
    public static void saveObject(String name, Pokemon data) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/pokedex/" + name + ".obj"));
        oos.writeObject(data);
    }
}