javac PokeUtility.java
jar -cvfm PokeUtility.jar manifest Pokemon.class PokeUtility.class Search.class PokeUtilityGUI.class SearchInput.class resources/pokedex/* resources/PressStart2P.ttf
rm *.class
echo "Made excecutable: PokeUtility.jar"