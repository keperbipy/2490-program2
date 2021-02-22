// Finite State Machine
// Counts the number of occurrences of 4 words: 
// pancake, peek, cake, keep 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Program2 {

	private static final int settleACCEPT = 6;  
	private static final int assetACCEPT = 19;  
	private static final int leastACCEPT = 11;  
	private static final int staleACCEPT = 23; 
	private static final int lateACCEPT = 14;    

	
	private static final int[][] STATE_TABLE = {
	 // S  E  T  L  A   ?
       {1, 0, 0, 7, 15, 0}, // state 0
	   {1, 2, 20, 7, 15, 0}, // state 1:  s
	   {1, 0, 3, 7, 15, 0}, // state 2:  se
	   {1, 0, 4, 7, 15, 0}, // state 3:  set
	   {1, 0, 0, 5, 15, 0}, // state 4:  sett
	   {1, 6, 0, 7, 12, 0}, // state 5:  settl
	   {1, 0, 0, 7, 9, 0}, // state 6:  settle
       {1, 8, 0, 7, 12, 0}, // state 7: l
       {1, 0, 0, 7, 9, 0},// state 8: le 
       {10, 0, 0, 7, 15, 0}, // state 9: lea
       {17, 2, 11, 7, 15, 0}, // state 10: leas
       {1, 0, 0, 7, 21, 0}, // state 11: least
       {16, 0, 13, 7, 15, 0}, // state 12: la
       {1, 14, 0, 7, 15, 0}, // state 13: lat 
       {1, 0, 0, 7, 15, 0}, // state 14: late 
       {16, 0, 0, 7, 15, 0},  // state 15:a 
       {17, 2, 20, 7, 15, 0}, // state 16: as 
       {1, 18, 20, 7, 15, 0}, // state 17: ass 
       {1, 0, 19, 7, 15, 0}, // state 18: asse
       {1, 0, 4, 7, 15, 0}, // state 19: asset 
       {1, 0, 0, 7, 21, 0}, // state 20: st 
	   {16, 0, 0, 22, 15, 0}, // state 21: sta 
	   {1, 23, 0, 7, 12, 0}, // state 22: stal 
	   {1, 0, 0, 7, 9, 0} // state 23: stale 
   };

    private BufferedReader in;
    private int settleCount; 
    private int assetCount;
    private int leastCount;
    private int staleCount;
	private int lateCount;


    public Program2(String filename) throws IOException {
        in = new BufferedReader(
                 new FileReader(filename));
        settleCount = 0; 
		assetCount = 0;
		leastCount = 0;
		staleCount = 0;
		lateCount = 0;
    }


    public void run() throws IOException {
        char ch;
        int unicode;
        int state = 0;

        unicode = in.read();
        while (unicode != -1) {
           ch    = (char) unicode;
           state = STATE_TABLE[state][charToColumn(ch)];
           if (state == settleACCEPT) {
              settleCount++;
           }
           if (state == assetACCEPT) { 
              assetCount++;
           }
           if (state == leastACCEPT)
              leastCount++;
           if (state == staleACCEPT) 
              staleCount++;
		   if (state == lateACCEPT) 
              lateCount++;
           unicode = in.read();
        }
        System.out.println( "Occurrence counts: ");
        System.out.println( "settle count is " + settleCount);
        System.out.println( "asset  count is " + assetCount);
        System.out.println( "least  count is " + leastCount);
        System.out.println( "stale  count is " + staleCount);
		System.out.println( "late   count is " + lateCount);
    }

    public int charToColumn(char ch) {
        if (ch == 'S' || ch == 's') 
           return 0;
        if (ch == 'E' || ch == 'e') 
           return 1;
        if (ch == 'T' || ch == 't') 
           return 2;
        if (ch == 'L' || ch == 'l') 
           return 3;
        if (ch == 'A' || ch == 'a') 
           return 4;
        return 5;
    }

    public static void main(String[] args) {
        if (args.length < 1)
           System.out.println 
                ("Run again, entering a filename at the commandline");
        else {
           try {
               Program2 fsm = new Program2(args[0]);
               fsm.run();
           } catch (IOException ex) {
               ex.printStackTrace();
               System.exit(1);
           }
       }
    }
}
