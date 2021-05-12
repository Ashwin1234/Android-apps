package com.example.project4;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*Using AI min max strategy to get the next move*/

/**
 * This class is used to read in a state of a tic tac toe board. It creates a MinMax object and passes the state to it. What returns is a list 
 * of possible moves for the player X that have been given min/max values by the method findMoves. The moves that can result in a win or a 
 * tie for X are printed out with the method printBestMoves()
 * 
 * @author Mark Hallenbeck
 *
 * Copyright© 2014, Mark Hallenbeck, All Rights Reservered.
 *
 */
public class AI_MinMax {
	
	private String[] init_board;
	
	private HashMap<Integer,Integer> state_minmax;

	AI_MinMax()
	{
		/*init_board = board.split(" ");
		System.out.println(init_board.length);
		if(init_board.length != 9)
		{
			System.out.println("You have entered an invalid state for tic tac toe, exiting......");
			System.exit(-1);
		}
		
		MinMax sendIn_InitState = new MinMax(init_board);
		
		movesList = sendIn_InitState.findMoves();
		
		printBestMoves();*/
	}
	
	/**
	 * reads in a string from user and parses the individual letters into a string array
	 * @return String[]
	 */
	public int getBoard(String[] init_board)
	{

			/*String puzzle;
			String[] puzzleParsed;
			String delim = " ";

			//give input message
			System.out.println("Enter a string to represent the board state:");

			Scanner userInput = new Scanner(System.in);		//open scanner

			puzzle = userInput.nextLine();					//scan in string

			puzzleParsed = puzzle.split(delim);
			userInput.close();*/   	  						//close scanner


		Log.i("Ai_minmax","came her");

		MinMax sendIn_InitState = new MinMax(init_board);

		Log.i("Ai_minmax","came her");

		state_minmax = sendIn_InitState.findMoves();



		ArrayList<Integer> arr=new ArrayList<>();

		for(Map.Entry<Integer,Integer> set : state_minmax.entrySet()){
			Log.i("Algorithm values",set.getKey()+" ");
			arr.add(set.getValue());
		}
		Collections.sort(arr, (o1, o2) -> o1.compareTo(o2));



		for(Map.Entry<Integer,Integer> set : state_minmax.entrySet()){
			if(set.getValue() == arr.get(0)){
				Log.i("Algorithm",arr.get(0)+" ");
			return set.getKey();

			}
		}


		return 0;

	}
	
	/**
	 * goes through a node list and prints out the moves with the best result for player X
	 * checks the min/max function of each state and only recomends a path that leads to a win or tie
	 */
	/*private void printBestMoves()
	{
		System.out.print("\n\nThe moves list is: < ");
		
		for(int x = 0; x < movesList.size(); x++)
		{
			Node temp = movesList.get(x);
			
			if(temp.getMinMax() == 10 || temp.getMinMax() == 0)
			{
				System.out.print(temp.getMovedTo() + " ");
			}
		}
		
		System.out.print(">");
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AI_MinMax startThis = new AI_MinMax();
	}

}
