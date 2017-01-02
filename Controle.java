package quoridor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/* 
* @author Maxime Daigle
*/

public class Controle implements MouseListener, ActionListener {

	Vue vue;
	Modele modele;

	public Controle(Vue vue, Modele modele){
		this.vue = vue;
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent a) {
		Cellule clickedCell = (Cellule) a.getSource();
		int current = vue.game.board.getCurrent();
		int wallRestant = vue.getWall(current);

		//si clicker sur une cellule pour mettre un mur
		if((clickedCell.orientation == 'v' || clickedCell.orientation == 'h') && !clickedCell.placed && !clickedCell.cellTrou.placed && (wallRestant > 0)){
			modele.clickerPlacerMur(clickedCell, current, wallRestant);
		}



		//if cellule contenant pion du joueur en cours, colorer les cellules qui sont un mouvement possible
		if(clickedCell.player[current]){
			modele.colorerMove(current);
		}
		//si clicker un mouvement possible, fait le deplacement
		if(clickedCell.getBackground().equals(Color.CYAN)){
			modele.deplacement(clickedCell, current);
		}
		//enleve la couleur sur les cases ou les mouvements sont possibles
		if(!clickedCell.player[current]){
			modele.decolorerMove();
		}
	}

	public void mouseClicked(MouseEvent arg0) {	}

	//quand souris sur mur potentiel, colorer
	public void mouseEntered(MouseEvent a) {
		Cellule clickedCell = (Cellule) a.getSource();
		modele.colorerMur(clickedCell);
	}


	//if cellule pour mur exited, decolorer
	public void mouseExited(MouseEvent a) {
		Cellule clickedCell = (Cellule) a.getSource();
		modele.decolorerMur(clickedCell);
	}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

}
