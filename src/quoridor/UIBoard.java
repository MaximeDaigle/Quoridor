package quoridor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/* 
* @author Maxime Daigle
*/

public class UIBoard {
	
	Cellule[][] tabBoard = new Cellule[17][17];
	JPanel pane;
	Controle controle;
	
	public UIBoard(Controle controle){
		this.controle = controle;
		
		pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		Cellule cell;
		for(int j = 0; j < 9; j++){
			for(int i = 0; i < 9; i++){
				cell = new Cellule(i,j+1,controle);
				c.ipady = 50;
				c.ipadx = 30;
				c.gridx = i*2;
				c.gridy = j*2;
				tabBoard[j*2][i*2] = cell;
				pane.add(cell, c);

				if( j != 8 && i !=8){
					cell = new Cellule(i,j+1, 't', controle);
					Cellule cellTrou = cell;
					Dimension d1 = new Dimension(1,1);
					cell.setPreferredSize(d1);
					cell.setBorderPainted(false);
					c.ipady = 0;
					c.ipadx = 0;
					c.gridx = i*2+1;
					c.gridy = j*2+1;
					tabBoard[j*2+1][i*2+1] = cell;
					pane.add(cell, c);

					cell = new Cellule(i,j+1, 'h', cellTrou, controle);
					cell.setBorderPainted(false);
					c.gridx = i*2;
					c.gridy = j*2+1;
					tabBoard[j*2+1][i*2] = cell;
					pane.add(cell, c);

					cell = new Cellule(i,j+1, 'v', cellTrou, controle);
					Dimension d2 = new Dimension(9,10);
					cell.setPreferredSize(d2);
					cell.setBorderPainted(false);
					c.gridx = i*2+1;
					c.gridy = j*2;
					tabBoard[j*2][i*2+1] = cell;
					pane.add(cell, c);
				}

				if(j != 8 && i == 8){
					cell = new Cellule(i,j+1, 'n', controle);
					cell.setBorderPainted(false);
					c.ipady = 0;
					c.ipadx = 0;
					c.gridx = i*2;
					c.gridy = j*2+1;
					tabBoard[j*2+1][i*2] = cell;
					pane.add(cell, c);
				}

				if(i != 8 && j == 8){
					cell = new Cellule(i,j+1, 'n', controle);
					Dimension d2 = new Dimension(9,10);
					cell.setPreferredSize(d2);
					cell.setBorderPainted(false);
					c.ipady = 0;
					c.ipadx = 0;
					c.gridx = i*2+1;
					c.gridy = j*2;
					tabBoard[j*2][i*2+1] = cell;
					pane.add(cell, c);
				}

			}
		}
	}
	
	//TEST
	public static void main(String[] args){
		
	}
}

/*
	
	*/

