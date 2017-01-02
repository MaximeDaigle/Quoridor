
package quoridor;


import java.io.*;
import java.util.List;
import java.util.Random;

/* 
* @author Joey Tuong
* @author Luke Pearson
* 
* modified by Maxime Daigle
*/

/**
 * Provide a command line interface to a human player.
 * 
 * @author Joey Tuong
 * @author Luke Pearson
 *
 */
public class Human extends Player {
    
    boolean suppressPrompt;
    String nextMove = "";
    
    public Human() {
        super();
        suppressPrompt = false;
    }
    
    @Override
    public void onFailure(Board b) {
    	//Done afficher message d'erreur
        //System.out.println("Invalid move!");
    	Vue.invalidMoveMsg();
    }
    
    @Override
    public String nextMove(Board b) {
        String temp = "";
        /*
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        b.printBoard();
        System.out.print("Enter the move for player "
                + new Integer(this.getID()).toString() + ": ");
        try {
        	//DONE remplace pour que temp = string exemple "f9" envoyer par detection de la case f9 clicker
        	//ou si place mur, string exemple "f9h" ou "f9v" dependemment de l'orientation
            temp = in.readLine();
        } catch (IOException e) {
        }*/
        while(nextMove.equalsIgnoreCase("")){ 
        	 try {
        		 System.out.print(nextMove);
        	       Thread.sleep(1000);
        	    } catch(InterruptedException e) {
        	    }
        }
        temp = nextMove;
        nextMove = "";
        return temp;
    }
    
    /**
     * Get all possible valid moves for the current player at a given state.
     * 
     * @param state board state
     * @return list of valid moves
     */
    public List<Position> generateMoves(Board state) {
        int player = state.currentPlayer();
        List<Position> moves = state.validMoves(state.positionOf(player));
    /*    if (state.remainingWalls(player) > 0) {
            for (char i = '1'; i < '9'; i++) {
                for (char j = 'a'; j < 'i'; j++) {
                    Position horstate = new Position(String.valueOf(j) + String.valueOf(i) + "h");
                	Position verstate = new Position(String.valueOf(j) + String.valueOf(i) + "v");
                    if (state.isValidMove(horstate.toString())) {
                        moves.add(horstate);
                    }
                    if (state.isValidMove(verstate.toString())) {
                        moves.add(verstate);
                    }
                }
            }
        }
        if (moves.size() > 2) {
           moves.remove(rand.nextInt(moves.size()));
        }*/
        return moves;
    }
    
    public void setMove(String move){
    	nextMove = move;
    }
    
}
