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