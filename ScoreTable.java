// Name:Ruohan Sun
// USC NetID:6539994087
// CS 455 PA4
// Spring 2018

import java.util.HashMap;
import java.util.Map;


/**
 * Provide a table that map each letter to the corresponding score
 * Let user get the score of a given letter
 * @author Ruohan Sun
 */
public class ScoreTable {
	
	//These constants store all the possible scores
    private final int ONE_SCORE=1;
    private final int TWO_SCORE=2;
    private final int THREE_SCORE=3;
    private final int FOUR_SCORE=4;
    private final int FIVE_SCORE=5;
    private final int EIGHT_SCORE=8;
    private final int TEN_SCORE=10;
 
    // the table is used to map the letter to it's score
    private Map<Character, Integer> table;
    
    /**
     * Initialize the ScoreTable, map each letter to it's score
     */
    public ScoreTable(){
    	
        table= new HashMap<>();
        table.put('a', ONE_SCORE);
        table.put('b', THREE_SCORE);
        table.put('c', THREE_SCORE);
        table.put('d', TWO_SCORE);
        table.put('e', ONE_SCORE);
        table.put('f', FOUR_SCORE);
        table.put('g', TWO_SCORE);
        table.put('h', FOUR_SCORE);
        table.put('i', ONE_SCORE);
        table.put('j', EIGHT_SCORE);
        table.put('k', FIVE_SCORE);
        table.put('l', ONE_SCORE);
        table.put('m', THREE_SCORE);
        table.put('n', ONE_SCORE);
        table.put('o', ONE_SCORE);
        table.put('p', THREE_SCORE);
        table.put('q', TEN_SCORE);
        table.put('r', ONE_SCORE);
        table.put('s', ONE_SCORE);
        table.put('t', ONE_SCORE);
        table.put('u', ONE_SCORE);
        table.put('v', FOUR_SCORE);
        table.put('w', FOUR_SCORE);
        table.put('x', EIGHT_SCORE);
        table.put('y', FOUR_SCORE);
        table.put('z', TEN_SCORE);
    }
    
    /**
     * return the score of the given letter
     * @param letter
     * @return the score of the letter
     */
    public int getScore(char letter) {
    		return table.get(letter);
    }

}