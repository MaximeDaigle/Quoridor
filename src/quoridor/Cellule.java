
package quoridor;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/* 
* @author Maxime Daigle
*/
public class Cellule extends JButton{

	char colonne;
	int hauteur;
	char orientation = 'n'; //no orientation
	Cellule cellTrou;
	boolean placed = false;  //not placed yet
	boolean[] player = {false,false,false,false}; //if player[i] is in the cellule
	
	
	//colonne de 'a' a 'i' a partir de la gauche
	//hauteur de 1 a 9 a partir d'en haut
	public Cellule(int colonne, int hauteur, Controle controle) {
		this.colonne = translate2Board(colonne);
		this.hauteur = hauteur;
		addMouseListener(controle);
		addActionListener(controle);
	}

	//constructeur cellule wall 'h' pour horizontal et 'v' pour vertical
	public Cellule(int colonne, int hauteur, char orientation, Controle controle) {
		this.colonne = translate2Board(colonne);
		this.hauteur = hauteur;
		this.orientation = orientation;
		addMouseListener(controle);
		addActionListener(controle);
	}
	//constructeur si wall associé a une cellule trou
	public Cellule(int colonne, int hauteur, char orientation, Cellule cellTrou, Controle controle) {
		this.colonne = translate2Board(colonne);
		this.hauteur = hauteur;
		this.orientation = orientation;
		this.cellTrou = cellTrou;
		addMouseListener(controle);
		addActionListener(controle);
	}

	public void placed(){
		placed = true;
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D gg = (Graphics2D) g;

		// Makes drawing smooth
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] color = {Color.blue, Color.RED,Color.YELLOW,Color.green};
		for(int i = 0; i < 4; i ++){
			if(player[i]){
				// Drawing player
				gg.setColor(color[i]);
				gg.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
				gg.fillOval(10, 10, getWidth() - 20, getHeight() - 20);
			}
		}
	}

	public static char translate2Board(int colonne){
		if(colonne == 0){
			return 'a';
		}else if(colonne == 1){
			return 'b';
		}else if(colonne == 2){
			return 'c';
		}else if(colonne == 3){
			return 'd';
		}else if(colonne == 4){
			return 'e';
		}else if(colonne == 5){
			return 'f';
		}else if(colonne == 6){
			return 'g';
		}else if(colonne == 7){
			return 'h';
		}else if(colonne == 8){
			return 'i';
		}else return 'n'; //si n'est pas une colonne du board 
	}
	
	public static int translate2Number(char colonne){
		if(colonne == 'a'){
			return 0;
		}else if(colonne == 'b'){
			return 1;
		}else if(colonne == 'c'){
			return 2;
		}else if(colonne == 'd'){
			return 3;
		}else if(colonne == 'e'){
			return 4;
		}else if(colonne == 'f'){
			return 5;
		}else if(colonne == 'g'){
			return 6;
		}else if(colonne == 'h'){
			return 7;
		}else if(colonne == 'i'){
			return 8;
		}else return 'n'; //si n'est pas une colonne du board 
	}
}
