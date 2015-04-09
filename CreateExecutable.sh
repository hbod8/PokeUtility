javac PokeUtility.java
jar -cvfm PokeUtility.jar manifest *.class
rm *.class
echo "Made excecutable: PokeUtility.jar"