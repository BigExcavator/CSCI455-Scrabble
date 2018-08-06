// Name:Ruohan Sun
// USC NetID:6539994087
// CS 455 PA4
// Spring 2018

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

/**
 * This class contain the main method, which let the user input the dictionary and the words
 * Printout all the found words and the corresponding score in decreasing order
 * @author Ruohan Sun
 *
 */
public class WordFinder {
	
	//The constant is used to change the Upper char to it's corresponding lower char
	private static int UPPEER_TO_LOWER=32;
	/**
	 * the main function lets the user input the dictionary file on command-line
	 * if user doesn't provide dictionary file, the program will use the default sowpods.txt dictionary 
	 * @param args: the input from the user
	 */
  public static void main(String[] args) {
  	
  		try {
  			System.out.println("Type . to quit.");
  			
  			//create the AnagramDictionary from the given dictionary file 
  			AnagramDictionary agDict=getDictionary(args);
  			Scanner in=new Scanner(System.in);
  			
  			while(true) {
  				System.out.print("Rack? ");
  				String input=in.nextLine();
  				if(input.equals(".")) {
  					break;
  				}
  				//get all the subsets of the given input string
  				ArrayList<String> subSet=getSubset(input);
  				//get a map containing all legal words could be found in the dictionary, and the corresponding score
  				HashMap<String, Integer> wordToScore=wordToScore(subSet, agDict);
  				//print all the words and scores in decreasing order
  				printAll(wordToScore, input);
  			}
  		}
  		catch(FileNotFoundException exc){
  			System.out.print(exc.getMessage());
  		}
  }
  
  /**
   * return an AnagramDictionary according to the given dictionary file
   * @param args: the user's input, where we get the dictionary file from
   * @return an AnagramDictionary
   * @throws FileNotFoundException
   */
  private static AnagramDictionary getDictionary(String[] args) throws FileNotFoundException{
  	
  		AnagramDictionary agMap;
    if (args.length > 0) {
      agMap = new AnagramDictionary(args[0]);
    } else{
      //if the file given by user not found, turn to the default dictionary
      agMap = new AnagramDictionary("sowpods.txt");
    }
  		return agMap;
  }
  
  /**
   * get all the subset of the given string
   * @param input: the input string given by the user
   * @return an arraylist containing all the subset of the given string
   */
  private static ArrayList<String> getSubset(String input) {
  		//get the sorted version, unique version of the input, and the multi array.
  		String cleanStr=ScrabbleTools.cleanString(input);
  		String uniqueStr=ScrabbleTools.unique(cleanStr);
  		String sortedStr=ScrabbleTools.sortString(cleanStr);
  		int[] multi=ScrabbleTools.getMult(sortedStr);
  		
  		Rack rack = new Rack();
  		//using the unique version and the multi array to get all the subsets
  		ArrayList<String> res = rack.findAllSubsets(uniqueStr, multi, 0);
		return res;
	}
  
  /**
   * get a hashMap containing all legal words could be found in the dictionary, and the corresponding score
   * @param subSet: the list contain all the subsets of the given string
   * @param agDict: the AnagramDictionary we use
   * @return a hashMap containing all the legal words, and the corresponding score
   */
  private static HashMap<String, Integer> wordToScore(ArrayList<String> subSet, AnagramDictionary agDict){
  		ScoreTable scoreTable = new ScoreTable();
  		ArrayList<String> legalWords=new ArrayList<String>();
  		
  		//add all the legal words in the dictionary into the list
  		for(String str:subSet) {
  			if(agDict.getAnagramsOf(ScrabbleTools.sortString(str))!=null) {
  				legalWords.addAll(agDict.getAnagramsOf(ScrabbleTools.sortString(str)));
  			}
  		}
  		HashMap<String, Integer> wordToScore=new HashMap<String, Integer>();
  		
  		//map each legal words into it's score
  		for(String str:legalWords) {
  			int score=0;
  			for(int i=0; i<str.length(); i++) {
  				//if a char is a upper letter, search the score of it's corresponding lower letter in the score table
  				if(('A'<=str.charAt(i) && str.charAt(i)<='Z')) {
  					score+=scoreTable.getScore((char)(str.charAt(i)+UPPEER_TO_LOWER));
  				}
  				else {
  					score+=scoreTable.getScore(str.charAt(i));
  				}
  			}
  			wordToScore.put(str, score);
  		}
  		return wordToScore;
  }
  
  /**
   * printAll the words and score in decreasing order
   * @param wordToScore: the map that containing all the words and the scores
   * @param source: the input string given by the user
   */
  private static void printAll(HashMap<String, Integer> wordToScore, String source) {
  		ArrayList<Map.Entry<String, Integer>> entries=new ArrayList<>();
  		for (Map.Entry<String, Integer> entry : wordToScore.entrySet()) {
  			entries.add(entry);
  		}
  		//sort the entries List using our own comparator
  		Collections.sort(entries, new myComparator());
  		
  		System.out.println("We can make "+entries.size()+" words from "+"\""+ScrabbleTools.sortString(ScrabbleTools.cleanString(source))+"\"");
  		
  		if(entries.size()>0) {
    		System.out.println("All of the words with their scores (sorted by score):");  			
  		}
  		//print all the words and score in decreasing order
  		for(int i=0; i<entries.size(); i++) {
  			System.out.println(entries.get(i).getValue() + ": " + entries.get(i).getKey());
  		}
  }
  
}

/**
 * The class is used to sort entries based on it's value and key
 * @author Ruohan Sun
 *
 */
class myComparator implements Comparator<Map.Entry<String, Integer>>{

	/**
	 * Overwrite the compare method
	 * Given two entries and then decide which come first
	 */
	public int compare(Map.Entry<String, Integer> et1, Map.Entry<String, Integer> et2) {
			if(et1.getValue()>et2.getValue()) {
				return -1;
			}
			else if(et1.getValue()<et2.getValue()) {
				return 1;
			}
			else {
				return et1.getKey().compareTo(et2.getKey());
			}
	}
}
