import java.io.*;

public class PokedexConstructor {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("pokedex.txt"));
        String data;
        String[] attr = new String[10];
        Pokemon newPkmn = new Pokemon();
        while ((data = br.readLine()) != null) {
            attr = data.substring(1, data.length() - 1).split("/");
            //Add a byte[] for image data in Pokemon.class and a int getImageSize for the array also set the byte[] data
            newPkmn.set(attr[0], attr[1], attr[2], "NA", "NA", "NA", "NA");
            saveObject(attr[1], newPkmn);
        }
    }
    public static void saveObject(String name, Pokemon data) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pokedex/" + name + ".obj"));
        oos.writeObject(data);
    }
}