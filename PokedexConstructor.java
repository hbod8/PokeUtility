import java.io.*;

public class PokedexConstructor {
    public static void main(String args[]) throws Exception {
        Pokemon newPokemon = new Pokemon();
        newPokemon.set("001", "Bulbasuar", "GRASS", "Strength", "Photosynthesis", "Ivysuar");
        saveObject("Bulbasuar", newPokemon);
    }
    public static void saveObject(String name, Pokemon data) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pokedex/" + name + ".obj"));
        oos.writeObject(data);
    }
}