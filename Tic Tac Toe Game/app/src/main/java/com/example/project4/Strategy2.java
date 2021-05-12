package com.example.project4;

import java.util.ArrayList;
import java.util.Random;

/*Random strategy to get the next move*/

public class Strategy2 {
    int m=3,n=3;
    public int get_move(String[] board){
        ArrayList<Integer> moves=new ArrayList<>();

       for(int i=0;i<board.length;i++){
           if(!board[i].equals("X") && !board[i].equals("O")){
               moves.add(i+1);
           }
       }
       Random rand=new Random();
       return moves.get(rand.nextInt(moves.size()));
    }
}
