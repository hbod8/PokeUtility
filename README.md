# PokeUtility
A pokedex for your desktop.

#### Help us by giving us your feedback [here](http://goo.gl/forms/IXylqIQJC4).

### Table of contents:

- Insructions for use.
- How to make a Pokemon.
- How to customize a Pokemon.
- Plans.
- Notes.

## Instructions for use: (Requires JDK)

### Simple:

1. Double-click either 'CreateExecutable.bat' (Windows) or 'CreateExecutable.sh' (Mac and Linux).
2. Run the new 'PokeUtility.jar' file from the directory to launch.

### Standard (or if simple failed):

#### Windows:

1. Open the start menu and type 'CMD' and press enter.
2. In the new window type `javac -version`.
3. If you get an error like: `javac is not recognized as an internal or external command`, your JDK needs to be configured.
4. Type `cd GIT_DIRECTORY` replacing `GIT_DIRECTORY` with the location of the the github files.
5. Type `CreateExecutable.bat`.
6. Run the PokeUtility by typing `java -jar PokeUtility.jar`.

#### Mac & Linux:

1. Open either Terminal (Mac), your defualt command line (Linux), or shell (Linux).
2. In the window type `javac -version`.
3. If you do not get any errors then you are clear to proceed.
4. Type `cd GIT_DIRECTORY` replacing `GIT_DIRECTORY` with the location of the the github files.
5. Type `./CreateExecutable.sh`.
6. Run the PokeUtility by typing `java -jar PokeUtility.jar`.

## How to make a Pokemon.
#### Only for people who know how to run and compile java programs.

1. In the resources folder you will find a file titled "pokedex.txt".  Open the file in a text editor and add the data as follows:

```
[THREE_DIGIT_POKEDEX_NUMBER/NAME_OF_POKEMON/TYPE/DESCRIPTION/ABILITY/HIDDEN_ABILITY/EVOLUTION]
```

2. Place an image of the pokemon in the icons folder titled with its 3 digit pokedex number.

3. Compile & Run PokedexConstructor.java

4. Re-run CreateExecutable.

5. Done! your modified pokeutility will be in the new `PokeUtility.jar` file.

## How to customize a Pokemon.
##### Only for people who know how to run and compile java programs.

1. In the resources folder you will find a file titled "pokedex.txt".  Open the file in a text editor and edit the data as follows:

```
[THREE_DIGIT_POKEDEX_NUMBER/NAME_OF_POKEMON/TYPE/DESCRIPTION/ABILITY/HIDDEN_ABILITY/EVOLUTION]
```

*Keep the `THREE_DIGIT_POKEDEX_NUMBER` the same or the icon will no longer work.

2. Re-run CreateExecutable.

3. Done! your new modified PokeUtility is in the `PokeUtility.jar` file.

## Plans.

Finish the pokedex.

## Notes.

### Ideas:

- Use the 2-3 to 23 to alphabetically list the images to complete the pokedex icons.