// Name:Ruohan Sun
// USC NetID:6539994087
// CS 455 PA4
// Spring 2018

import java.util.*;


/**
 * This class is a tool box that providing some static methods to process string
 */

public class ScrabbleTools{
	
	/**
	 * sort the characters in the string by alphabetical order, and return the sorted string
	 * @param source:  the string need to be sorted
	 * @return  the sorted string
	 */
	public static String sortString(String source) {
		char[] res=source.toCharArray();
		Arrays.sort(res);
        String str = new String(res);
        return str;
	}
	
	/**
	 * remove all the non-alphabet characters in the string, and return the clean string
	 * @param source:  the string needed to be cleaned
	 * @return the cleanString
	 */
	public static String cleanString(String source) {

		String res="";
		for(int i=0; i<source.length();i++) {
			if(('a'<=source.charAt(i) && source.charAt(i)<='z') || ('A'<=source.charAt(i) && source.charAt(i)<='Z')) {
				res+=source.charAt(i);
			}
		}
		return res;
	}
	
	/**
	 * Get the multi array of the given clean and sorted string
	 * @param source: the given string that we get the multiplicity from
	 * @return the multi array
	 */
	public static int[] getMult(String source) {
		int length=1;
		
		//find how many unique letters in the given string
		for(int i=0; i<source.length()-1; i++) {
			if(source.charAt(i)!=source.charAt(i+1)) {
				length++;
			}
		}
		
		int[] res=new int[length];
		int num=1;
		int index=0;
		
		//store the multiplicity of each letter in an array
		for(int i=0; i<source.length()-1; i++) {
			if(source.charAt(i)==source.charAt(i+1)) {
				num++;
			}
			else {
				res[index++]=num;
				num=1;
			}
		}
		res[length-1]=num;
		
		return res;
	}
	
    /**
     * Get the string contain unique characters from the given string
     * @param string: the given string
     * @return the unique string
     */
    public static String unique(String string){

        String res = ScrabbleTools.cleanString(string);
        String result = ScrabbleTools.sortString(res);
        String unique = ""+result.charAt(0);

        //get the new unique string from the original string
        for(int i = 1; i < result.length(); i++){
            if(result.charAt(i) != result.charAt(i-1)){
                unique = unique + result.charAt(i);
            }
        }
        return unique;
    }
		
}