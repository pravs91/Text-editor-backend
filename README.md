# Text editor backend
Implemented classes to support auto-complete, spell check and spelling corrections for a text editor. Also developed a class to find the 
word-path between 2 words through one character modifications.

## Auto-complete
Suggests predictions for the word being typed. The prediction search was implemented using Tries.
Related classes:
* ```TrieNode``` - represents a node in the Trie data structure
* ```AutoComplete``` - interface that has the ```predictCompletions``` method
* ```AutoCompleteDictionaryTrie``` - implements the ```AutoComplete``` interface using Tries
* ```AutoCompleteDictionaryTrieTester``` - JUnit tests for the auto-complete feature

## Spelling suggestions
Does spell-check and suggests possible corrections for the misspelt word.
Related classes:
* ```SpellingSuggest``` - interface that has the ```suggestions``` method
* ```NearbyWords``` - implements the ```SpellingSuggest``` interface. Finds suggestions for the misspelt word by iteratively 
looking at words that are one character modification away (insertion, deletion or substitution)

## Word-Path
Return a path between 2 words, ```word1``` and ```word2```, through dictionary words with the restriction that each step in the path can 
involve only a single character mutation.
Related classes:
* ```WordPath``` - interface that has the ```findPath``` method
* ```WPTree``` - implements the ```WordPath``` interface. 
* ```WPTreeNode``` represents a node in the word path tree. Each node only
contains a word in the dictionary and the relationship between nodes is that a child is one character mutation (deletion, insertion, or
substitution) away from its parent




