## Why does this file exist?

In this file, I will post all changes and implementations I make and explain the idea and reasoning behind doing so in detail. This will help me keep track of my level of knowledge I display with each commit and also allows me to keep notes of ideas to implement in the future.
This will be different from the README.md doc in a way that shows what I have worked on in detail with each change/commit I have made.
This will be introduced for commits following the commit 0522e5c16d4f674c63d7ed985863582865304998 made on June 28 2026 8:01 PM GMT +3.


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

### Commit ___________ : Date Time
- Change 1: Reason behind change
- Change 2: Reason behind change
- ......

Overall change made and reason behind change

### Commit ____________ : Date Time