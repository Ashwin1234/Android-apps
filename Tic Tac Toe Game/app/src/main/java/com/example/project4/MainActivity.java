package com.example.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,start,reset;
    TextView result,moves;

    int m=3,n=3,move;
    Player1 p1;
    Player2 p2;
    AI_MinMax ai_minMax;
    private Handler mhandler;
    public Boolean minmaxlock;
    ArrayList<Button> buttons;
    TextView player1,player2;
    Bundle messageBundle;
    Message playerMessage;
    Boolean flag=false;
    Strategy2 startegy2;

    @Override
    @SuppressLint("HandlerLeak")
    protected void onCreate(Bundle savedInstanceState) {
        //declaring all the variables and UI elements
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1=new Player1("Player 1");
        p2=new Player2("player 2");
        //Initialising The UI handler
        mhandler=new Handler(Looper.myLooper());
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        b7=(Button)findViewById(R.id.button7);
        b8=(Button)findViewById(R.id.button8);
        b9=(Button)findViewById(R.id.button9);
        buttons=new ArrayList<>();
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
        buttons.add(b8);
        buttons.add(b9);
        start=(Button)findViewById(R.id.start);
        //reset=(Button)findViewById(R.id.reset);
        result=(TextView)findViewById(R.id.result);
        moves=(TextView)findViewById(R.id.move);
        b1.setText(" - ");
        b2.setText(" - ");
        b3.setText(" - ");
        b4.setText(" - ");
        b5.setText(" - ");
        b6.setText(" - ");
        b7.setText(" - ");
        b8.setText(" - ");
        b9.setText(" - ");
        player1=(TextView)findViewById(R.id.player1);
        player2=(TextView)findViewById(R.id.player2);
        m=3;
        n=3;
        ai_minMax=new AI_MinMax();
        minmaxlock=false;
        messageBundle=new Bundle();
        boolean flag=false;
        startegy2= new Strategy2();

    }

    public void startGame(View view) {
        //Start the game by checking whether the thread is alive or not. If it is interrupt and start the game, else start the game
        Log.i("flag value+",flag+" ");
        if(p1.isAlive()){
            Log.i("game running","came here");
            try {
                p1.interrupt();      /*Stop player1 and restart player1*/
                p1=new Player1("Player 1");
            }
            catch(Exception e){
                Log.i("threads interrupted", "came here");
            }
            try {
                p2.interrupt();      /* Stop player2 and restart player2*/
                p2=new Player2("Player 2");
            }
            catch(Exception e){
                Log.i("threads interrupted", Arrays.toString(e.getStackTrace()) +" ");
            }

            Log.i("status interrupted",p1.isAlive()+"");
            Log.i("status interrupted",p2.isAlive()+"");



        }
        //Declare an empty board
        String[] board=clear_board();
        p1.start();
        p2.start();



        try {
            Thread.sleep(1000);
        }
        catch(Exception e){

        }



        //board=initialize_array();
        messageBundle.putStringArray("board", board);
        playerMessage = Message.obtain();
        playerMessage.setData(messageBundle);

        p1.p1handler.sendMessage(playerMessage);

    }

    public String[] clear_board() {
        b1.setText(" - ");
        b2.setText(" - ");
        b3.setText(" - ");
        b4.setText(" - ");
        b5.setText(" - ");
        b6.setText(" - ");
        b7.setText(" - ");
        b8.setText(" - ");
        b9.setText(" - ");
        result.setText(R.string.result4);
        String[] board=new String[9];
        for(int i=0;i<9;i++){
            board[i]="b";
        }
        return board;
    }


    public class Player1 extends HandlerThread {

        String[] board;
        public Handler p1handler;
        public Player1(String name) {
            super(name);
        }

        @Override
        @SuppressLint("HandlerLeak")
        public void run() {

            Looper.prepare();

            p1handler=new Handler(){
                /* Handler for Player 1 thread*/
                public void handleMessage(Message msg){

                    board=msg.getData().getStringArray("board");
                    /*Update the UI regarding which game is playing using the UI thread*/
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            player2.setTextColor(Color.WHITE);
                            player1.setTextColor(Color.RED);
                        }
                    });




                    synchronized (minmaxlock) {

                        /*Use ai min max to get the next move*/

                        move = ai_minMax.getBoard(board);

                    }




                    mhandler.post(() -> buttons.get(move - 1).setText("X"));

                    board[move - 1] = "X";

                    mhandler.post(() -> moves.setText(getString(R.string.move1)+" "+move));



                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted!");
                    }

                    thread_switch(board,"Thread 1");
                }
            };
            Looper.loop();

        }

    }
    public class Player2 extends HandlerThread {

        String[] board;
        public Handler p2handler;
        public Player2(String name) {
            super(name);
        }

        @Override
        @SuppressLint("HandlerLeak")
        public void run() {

            Looper.prepare();
            p2handler = new Handler(){
                /* Handler for player 2 thread*/
                public void handleMessage(Message msg){

                    board=msg.getData().getStringArray("board");
                    /*Update the UI regarding which game is playing using the UI thread*/
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mhandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    player1.setTextColor(Color.WHITE);
                                    player2.setTextColor(Color.RED);
                                }
                            });

                        }
                    });




                    synchronized (minmaxlock) {

                        /* Get the next move randomly*/

                        move=startegy2.get_move(board);

                    }


                    Log.i("MainActivity", "Move" + move + " ");

                    mhandler.post(() -> buttons.get(move - 1).setText("O"));

                    board[move - 1] = "O";

                    mhandler.post(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            moves.setText(getString(R.string.move2)+" "+move);
                        }
                    });



                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted!");
                    }

                    thread_switch(board,"Thread 2");

                }
            };
            Looper.loop();


        }


    }

    public void thread_switch(String[] board, String msg){

        /* Use the UI thread to check the status of the board and update the UI if the game is over or notify other thread to start playing its move */

        String player=msg;
        if(check_status(board)==1)
        {


            if(player.equals("Thread 1")){
                mhandler.post(() -> {                        /*Use UI thread to update the result of player 1 winning*/
                    result.setText(R.string.result1);
                    result.setTextColor(Color.RED);
                });
            }
            else{
                mhandler.post(() -> {                      /*Use UI thread to update the result of player 2 winning*/
                    result.setText(R.string.result2);
                    result.setTextColor(Color.RED);
                });
            }
            try {
                p1.interrupt();
            }
            catch(Exception e){

            }
            try {
                p2.interrupt();
            }
            catch(Exception e){

            }



        }
        else if(check_status(board)==2){
            mhandler.post(() -> {                                               /*Use UI thread to update the result when the match is draw*/
                result.setText(R.string.result3);
                result.setTextColor(Color.RED);

            });
        }
        else{
            if(player.equals("Thread 1")){
                messageBundle.putStringArray("board", board);
                playerMessage = Message.obtain();
                playerMessage.setData(messageBundle);

                if(p2.p2handler!=null) {
                    p2.p2handler.sendMessage(playerMessage);
                }
            }
            else{
                messageBundle.putStringArray("board", board);
                playerMessage = Message.obtain();
                playerMessage.setData(messageBundle);

                if(p1.p1handler!=null) {
                    p1.p1handler.sendMessage(playerMessage);
                }
            }
        }

    }

    public int check_status(String[] board){

        /* This method is used to check the status of the board */

        String board_string=String.join("",board);

        ArrayList<String> board_list=string_list(board_string);

        for(int i=0;i<board_list.size();i++){

        }
        String[][] table=convert_string_board(board_list);
        int count_x,count_o;

        /*Check the row wise filling of X and Os*/

        for(int i=0;i<m;i++){
            count_x=0;
            count_o=0;
            for(int j=0;j<n;j++){
                if(table[i][j].equals("X")){
                    count_x++;
                }
                else if(table[i][j].equals("O")){
                    count_o++;
                }
            }
            if(check_condition(count_x,count_o)){
                return 1;
            }
        }

        count_x=0;
        count_o=0;

        /* Check the column wise filling of Xs and Os*/

        for(int i=0;i<m;i++){
            count_x=0;
            count_o=0;
            for(int j=0;j<n;j++){
                if(table[j][i].trim().equals("X")){
                    count_x++;
                }
                else if(table[j][i].trim().equals("O")){
                    count_o++;
                }
            }
            if(check_condition(count_x,count_o)){
                return 1;
            }
        }

        count_x=0;
        count_o=0;

        /*Check the diagonal wise filling of Xs and Os*/

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {

                if (i == j) {
                    Log.i("check condition","came here");
                    if (table[i][j].equals("X")) {

                        count_x=count_x+1;
                    } else if (table[i][j].equals("O")) {
                        count_o=count_o+1;
                    }
                }
            }

        }


        if(check_condition(count_x,count_o)){
            return 1;
        }
        count_x=0;
        count_o=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++) {

                if (i == 0 && j==2) {

                    if (table[i][j].equals("X")) {

                        count_x=count_x+1;
                    } else if (table[i][j].equals("O")) {
                        count_o=count_o+1;
                    }
                }
                else if(i==1 && j==1){

                    if (table[i][j].equals("X")) {

                        count_x=count_x+1;
                    } else if (table[i][j].equals("O")) {
                        count_o=count_o+1;
                    }
                }
                else if(i==2 && j==0){

                    if (table[i][j].equals("X")) {

                        count_x=count_x+1;
                    } else if (table[i][j].equals("O")) {
                        count_o=count_o+1;
                    }
                }
            }

        }

        if(check_condition(count_x,count_o)){
            return 1;
        }

        count_x=0;
        count_o=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(table[i][j].equals("X")){
                    count_x=count_x+1;
                }
                else if(table[i][j].equals("O")){
                    count_o=count_o+1;
                }
            }
        }
        if((count_x==5 && count_o==4) || (count_x==4 && count_o==5)){

            return 2;
        }
        return 0;

    }
    public String[][] convert_string_board(ArrayList<String> board){
        String[][] b=new String[m][n];
        int k=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                b[i][j]=board.get(k);

                k=k+1;
            }
        }
        return b;

    }
    public boolean check_condition(int count_x,int count_o){
        if(count_x==3 || count_o==3){

            return true;
        }
        return false;
    }
    public ArrayList<String> string_list(String board){
        ArrayList<String> board_list=new ArrayList<>(Arrays.asList(board.split("")));
        return board_list;
    }


}

