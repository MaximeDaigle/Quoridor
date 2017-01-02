
package quoridor;

import java.awt.Color;

/* 
* Generates new quoridor moves.
*
* @author Joey Tuong
* @author Luke Pearson
* 
* modified by Maxime Daigle
*/

public abstract class Player {
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Player other = (Player) obj;
        if (id != other.id) return false;
        return true;
    }

    private Direction direction;
    protected String  name;
    private int       walls;
    private int       id;
    private boolean   initialised = false;
    Vue vue;
    Cellule[][] tabBoard;
    boolean ai = false;
    
    /**
     * Create a player object and set its name.
     * 
     * @param name Name of the player
     */
    public Player(String name) {
        this.name = name;
    }
    
    public Player() {
    }
    
    /**
     * Initialise the player with an ID, direction, and walls.
     * 
     * Initialise may only be called once, and will generally be called by the
     * board class when the game is initialised.
     * 
     * @param id Player id
     * @param d Target direction
     * @param walls Number of walls
     */
    public final void initialise(int id, Direction d, int walls) {
        if (!initialised) {
            this.id = id;
            this.direction = d;
            this.walls = walls;
            this.initialised = true;
            this.onCreation();
        }
    }
    
    /**
     * Get the direction of the target wall.
     * @return direction of target wall
     */
    public final Direction getEnd() {
        return this.direction;
    }
    
    /**
     * Get the player's ID.
     * 
     * @return player's id
     */
    public final Integer getID() {
        return id;
    }
    
    /**
     * Get the number of remaining walls.
     * 
     * @return number of remaining walls.
     */
    public final Integer getNumWalls() {
        return walls;
    }
    
    /**
     * Use a wall.
     */
    public final void useWall() {
        walls--;
    }
    
    /**
     * Template method to get the player's next move given a certain game state.
     * 
     * @param g Game object
     * @return player's move.
     */
    public final String getMove(Game g) {
        String move = this.nextMove(g.getBoard());
        while (!g.isValidMove(move) || (move.length() == 3 && getNumWalls() <= 0)) {
            onFailure(g.getBoard());
            move = this.nextMove(g.getBoard());
        }
        if (move.length() == 3) {
            useWall();
            if(this.ai){
            	int colonne = Cellule.translate2Number(move.charAt(0));
            	int row = Integer.parseInt(move.substring(1,2)) - 1;
            	if(move.charAt(2) == 'h'){
            		Cellule cellWall = tabBoard[row*2+1][colonne*2];
            		Cellule cellWallFin = tabBoard[row*2+1][colonne*2+2];
            		cellWall.placed();
            		cellWall.setBackground(Color.gray);
            		cellWall.repaint();
            		cellWall.cellTrou.placed();
            		cellWall.cellTrou.setBackground(Color.gray);
            		cellWall.cellTrou.repaint();
            		cellWallFin.placed();  
            		cellWallFin.setBackground(Color.gray);
            		cellWallFin.repaint();
            		vue.updateNbWall(g.getBoard().getCurrent());
            	}else if(move.charAt(2) == 'v'){
            		Cellule cellWall = tabBoard[row*2][colonne*2+1];
            		Cellule cellWallFin = tabBoard[row*2+2][colonne*2+1];
            		cellWall.placed();
            		cellWall.setBackground(Color.gray);
            		cellWall.repaint();
            		cellWall.cellTrou.placed();
            		cellWall.cellTrou.setBackground(Color.gray);
            		cellWall.cellTrou.repaint();
            		cellWallFin.placed();  
            		cellWallFin.setBackground(Color.gray);
            		cellWallFin.repaint();
            		vue.updateNbWall(g.getBoard().getCurrent());
            	}
            }
        }
        if (move == "undo" && g.getBoard().lastMove().length() == 3) {
            this.walls++;
        }
        if( this.ai && move.length() == 2){
        	//coord de la cellule ou le joueur est avant deplacement
        	Position coord = g.getBoard().positionOf(this);
        	int c = coord.getCol();
        	int r = coord.getRow();
        	//cell ou etait le joueur
        	tabBoard[r*2][c*2].player[g.getBoard().getCurrent()] = false;
        	tabBoard[r*2][c*2].repaint();

        	//cell ou le player va
        	int colonne = Cellule.translate2Number(move.charAt(0));
        	int row = Integer.parseInt(move.substring(1)) -1;
        	tabBoard[row*2][colonne*2].player[g.getBoard().getCurrent()] = true;
        	tabBoard[row*2][colonne*2].repaint();
        }

        
        return move;
    }
    
    // Overridable
    /**
     * Called on initialisation.
     */
    protected void onCreation() {
    }
    
    /**
     * Called when a move returned by nextMove is not valid.
     * @param b current board state
     */
    protected void onFailure(Board b) {
    }
    
    /**
     * Get the name of the player.
     * @return name of the player
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Set the player's name.
     * 
     * @param name name of the player
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAI(){
    	this.ai = true;
    }
    
    /**
     * Generate the player's next move.
     * @param b board state
     * @return next move
     */
    public abstract String nextMove(Board b);
    
    /**
     * Generate the player's next move.
     * @param m 
     * @return next move
     */
    public abstract void setMove(String m);
}
