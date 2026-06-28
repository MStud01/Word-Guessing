## Why does this file exist?

In this file, I will post all changes and implementations I make and explain the idea and reasoning behind doing so in detail. This will help me keep track of my level of knowledge I display with each commit and also allows me to keep notes of ideas to implement in the future.
This will be different from the README.md doc in a way that shows what I have worked on in detail with each change/commit I have made.
This will be introduced for commits following the commit 36344df2e48cb18540b030b59d734e8ec4a188e5 made on June 28 2026 8:01 PM GMT +3.


## Project Design changes to make

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

## Changes made

### 28th June 2026 11:30 PM GMT +3
In this commit, I made the changes necessary in the GuessingGameMode class to make explicit the dependency relationship of the WordGameApp class on the concrete class extending the abstract GameMode class. This is one of a couple commits I will be making to resolve this issue.
- Changed the static-ity of fields rsg, dsl, generatedStrings, addedStrings and changedStrings to non-static in GuessingGameMode class
    These fields had no reason to be static.
- Changed the static-ity of all methods in GuessingGameMode class
    The methods should not have been static in the first place since I only needed to pass in the property of being able to call the bootGameMode() method of any class extending the GameMode class, which can be done by passing the constructor of the corresponding class instead. 
- Implemented a constructor for GuessingGameMode class
    Now, I can pass into the GameModes enum that the WordGameApp class can access to run the bootGameMode() method.
- Removed redundant code and unnecessary commented TODOs in GuessingGameMode class
    These lines and comments existed since I needed a fix the persistance of some of the fields that were static prior to this commit.
- Removed TODOs detailing the need for the above changes in the GuessingGameMode class
- Added TODOs for fixing the inherited methods in GuessingGameMode class
    The inherited methods in GuessingGameMode class were given different names and their following methods in the abstract superclass (GameMode class) were not made abstract.
- Changed the visibility of the bootGameMode() method in GuessingGameMode class to public
    This was done so the WordGameApp class could call the method.


### Next commit : Date Time
- Changed the passing of bootGameMode() to the passing of the corresponding constructor in GameModes enum
    This is to make explicit the dependency that the WordGameApp class has on classes that extend the GameMode class. In the previous implementation, this was obscured unintentionally when I realized I could just pass the bootGameMode() method. Additionally, I was only focused on utilizing a piece of knowledge that I was not very familiar with as I had not been able to utilize this knowledge elsewhere in the past year.
- Changed the static-ity of the bootGameMode() method in GameMode class
    So the compiler does not complain.
- Changed the visibility of the bootGameMode() method in GameMode class to public
    I made this change once I realized I would not be able to call the method in WordGameApp class.