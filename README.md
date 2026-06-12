## How the program works

At bootup, I welcome and ask an input for a n-lettered word (and inquire if it needs to be capitalized at the starting point) which I generate randomly using Random module to get random chars (as chars have a hidden unicode number) to convert into a String.

I then display the String and inquire the user if it is a word, 
upon which I confirm its status among my map of strings to bools (where strings are the randomly generated string and bools are the status that confirm if it is a word or not) and if it is different, then I display the appropriate message confirming the correctness or incorrectness of the user's guess.

if it is incorrect, then I inquire if the status of the String (from word (previously stored in map) to not-word (newly guessed from user) or from not-word (previously stored in map) to word (newly guessed from user)) would be changed.
If the string could not be found at all in the map, then I would inquire if it would need to be added in as a (word / not-word) depending on what they had guessed before (they CANNOT change that status of the randomly generated string by changing their guess).

If the response is yes, then I commit the change to the map after which I commit it back to the json file storing the map.

This continues till either the user wants to generate a string of a different length or would like to quit the program.
Upon quitting the program, I could again inquire if the changes made should be saved (if I have not done so at the time of making changes to the randomly generated Strings).

In terminal version, I would just generate Strings from randomly generated chars in any order but in the GUI version, I would use outsourced images or self-created images of each alphabet and generate them randomly using a char field for each of these images.

An advanced challenge can be introduced where the user gives me a n-lettered word where I randomly sort ( / flip / rotate, only in the GUI version) the characters in the word to generate a String of the same length and inquire if it is a word and follow the same process as above. Also, the program stores the the status of each generated string and the initial word into the same map defined into the program.

Additional challenges can be made using the generation of chars, guessing of words and possibly using synonyms (and what not, if need be).

## Design principles used

- Singleton classes
- Test-driven development


---------X----------------------X-------------------X---------------

## Changes to make

Rework the word guessing game so it utilizes phonetics instead of chars and look into appropriate syllable compositions (like voiceless postalveolar fricative + mid front non-rounded vowel + voiceless alveolar stop, etc.)

There will exist 2 modes for this app:
First mode is where the player gets a set of randomly selected/generated words and the player is asked to identify for certain features of the words (like are they familiar with the word, do certain words match with a particular theme). This will be the general gameplay until the difficulty of the game can be adjusted (look into this further). 
Second mode is the build-up of the word library that the player can work with, where they are presented with a randomly generated word with the aforementioned appropriate syntax. 

I need to look into:
1) Proper syntax that makes the syllables of an English word
2) Adjusted difficulty for the first mode of the game
3) GUI elements that are available in Java (or C/C++)

The reason behind this change is that the initial idea seems stupid and uninteresting. It will be more appropriate to randomize IPA phonetics since they are easier to sound out.
Additionally, this could work well with another project that utilizes IPA phonetics for voice recognition.