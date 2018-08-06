// Name: Ruohan Sun
// USC NetID: 6539994087
// CS 455 PA4
// Spring 2018

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;




/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary{
   
	//a table that map each word to it's all anagram list
	private Map<String, ArrayList<String>> agMap;
   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */

	public AnagramDictionary(String fileName) throws FileNotFoundException {
		
		agMap=new HashMap<String, ArrayList<String>>();
	   	File file=new File(fileName);
	   	Scanner scanner=new Scanner(file);
	   	
	   	//build the anggramMap in O(n) time
	   	while(scanner.hasNext()) {
	   		
	   		String str=scanner.next();
	   		String cleanStr=ScrabbleTools.cleanString(str);
	   		String sortedStr=ScrabbleTools.sortString(cleanStr);
	   		
	   		//use the sorted string as the key, and store all of it's anagrams in the list
	   		if(agMap.containsKey(sortedStr)) {
	   			agMap.get(sortedStr).add(str);
	   		}
	   		else {
	   			agMap.put(sortedStr, new ArrayList<String>());
	   			agMap.get(sortedStr).add(str);
	   		}
	   	}
	   	
   }
   
   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
	   
	   String sortedStr=ScrabbleTools.sortString(ScrabbleTools.cleanString(s));
	   
	   // if the given string has anagrams in the dictionary, return an arrayList containing all of them. Otherwise, return null
	   if(agMap.containsKey(sortedStr)) {
		   return new ArrayList<String>(agMap.get(sortedStr)); 
	   }
	   else {
		   return null;
	   }
   }
   

   
   
}