# simplesearch

This project is used to search words in a directory

# Steps
1. Checkout the files
2. Build a jar file(JDK 1.8 or later)
3. run from command prompt
  java -jar simplesearch-1.0.0.jar /directory
4. search> "text to search"
5. Result would be percentage of match found in each file

# Algorithms
1. HashMap used to store all words of a file with count
2. special characters replaced by blank
3. percentage calculation done based on number of count found in file including duplicate values.
