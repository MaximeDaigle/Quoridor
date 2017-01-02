package quoridor;

import java.awt.Color;
import java.util.List;

/* 
* @author Maxime Daigle
*/

public class Modele {

	Vue vue;
	
	public Modele(Vue vue){
		this.vue = vue;
	}
	
	public void clickerPlacerMur(Cellule clickedCell, int current, int wallRestant){
		int c = Cellule.translate2Number(clickedCell.colonne);
		int r = clickedCell.hauteur;
		
		String m = "" + clickedCell.colonne + clickedCell.hauteur + clickedCell.orientation; //tried move
		if(clickedCell.orientation == 'h'){
			Cellule cellWallFin = vue.uiBoard.tabBoard[r*2-1][c*2+2];
			if(!cellWallFin.placed && vue.game.board.isValidMove(m) ){
				clickedCell.placed();
				clickedCell.cellTrou.placed();
				cellWallFin.placed();
				vue.players[current].setMove(m);
				vue.updateNbWall(current);
			}
		}else if(clickedCell.orientation == 'v'){
			Cellule cellWallFin = vue.uiBoard.tabBoard[r*2][c*2+1];
			if(!cellWallFin.placed && vue.game.board.isValidMove(m)){
				clickedCell.placed();
				clickedCell.cellTrou.placed();
				cellWallFin.placed();
				vue.players[current].setMove(m);
				vue.updateNbWall(current);
			}
		}
		if(!vue.game.board.isValidMove(m) || wallRestant <= 0){
			Vue.invalidMoveMsg();
		}
	}
	
	public void colorerMove(int current){
		Human currentPlayer = (Human) vue.players[current];
		List<Position> moves = currentPlayer.generateMoves(vue.game.board);
		for(int i = 0; i < moves.size(); i++){
			int colonne = moves.get(i).getCol();
			int row = moves.get(i).getRow();
			vue.uiBoard.tabBoard[row*2][colonne*2].setBackground(Color.CYAN);
		}
	}
	
	public void deplacement(Cellule clickedCell, int current){
		String m = "" + clickedCell.colonne + clickedCell.hauteur;
		if(vue.game.board.isValidMove(m)){
			//alternative au double boucle for
			//cell ou etait le player[current] = false
			//Player currentPlayer = vue.players[current];
			//Position coordCell vue.game.board.positionOf(currentPlayer);
			//int i = coordCell.getRow() -1;
			//int j = coordCell.getCol();
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					vue.uiBoard.tabBoard[i*2][j*2].player[current] = false;
					vue.uiBoard.tabBoard[i*2][j*2].repaint();
				}
			}
			//nouvelle position du player
			clickedCell.player[current] = true;
			vue.players[current].setMove(m);
			decolorerMove();
		}else{
			Vue.invalidMoveMsg();
		}
	}
	
	public void decolorerMove(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(vue.uiBoard.tabBoard[i*2][j*2].getBackground().equals(Color.CYAN)){
					vue.uiBoard.tabBoard[i*2][j*2].setBackground(null);
				}
			}
		}
	}
	
	public void colorerMur(Cellule clickedCell){
		if((clickedCell.orientation == 'v' || clickedCell.orientation == 'h') && !clickedCell.placed && !clickedCell.cellTrou.placed){
			int c = Cellule.translate2Number(clickedCell.colonne);
			int r = clickedCell.hauteur;
			if(clickedCell.orientation == 'v'){
				Cellule cellWallFin = vue.uiBoard.tabBoard[r*2][c*2+1];
				if(!cellWallFin.placed){
					cellWallFin.setBackground(Color.gray);
					clickedCell.setBackground(Color.gray);
					clickedCell.cellTrou.setBackground(Color.gray);
				}
			}else if(clickedCell.orientation == 'h' ){
				Cellule cellWallFin = vue.uiBoard.tabBoard[r*2-1][c*2+2];
				if(!cellWallFin.placed){
					cellWallFin.setBackground(Color.gray);
					clickedCell.setBackground(Color.gray);
					clickedCell.cellTrou.setBackground(Color.gray);
				}
			}
		}
	}
	
	public void decolorerMur(Cellule clickedCell){

		if((clickedCell.orientation == 'v' || clickedCell.orientation == 'h') && !clickedCell.placed && !clickedCell.cellTrou.placed){
			int c = Cellule.translate2Number(clickedCell.colonne);
			int r = clickedCell.hauteur;
			if(clickedCell.orientation == 'v'){
				Cellule cellWallFin = vue.uiBoard.tabBoard[r*2][c*2+1];
				if(!cellWallFin.placed){
					cellWallFin.setBackground(null);
					clickedCell.setBackground(null);
					clickedCell.cellTrou.setBackground(null);
				}
			}else if(clickedCell.orientation == 'h' ){
				Cellule cellWallFin = vue.uiBoard.tabBoard[r*2-1][c*2+2];
				if(!cellWallFin.placed){
					cellWallFin.setBackground(null);
					clickedCell.setBackground(null);
					clickedCell.cellTrou.setBackground(null);
				}
			}
		}
	}
}
