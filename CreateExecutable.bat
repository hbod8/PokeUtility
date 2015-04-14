javac PokeUtility.java
jar -cvfm PokeUtility.jar manifest *.class
del *.class
echo "Made excecutable: PokeUtility.jar"