package quoridor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/* 
* @author Maxime Daigle
*/

public class Vue {
	
	UIBoard uiBoard;
	JFrame frame;
	JPanel panel; 

	JLabel nbWall1;  //contient nombre de wall restant pour joueur 1
	int wall1;
	JLabel nbWall2;
	int wall2;
	JLabel nbWall3;
	int wall3;
	JLabel nbWall4;
	int wall4;
	
	Modele modele;
	Controle controle;
	Player[] players;
	Game game;

	public Vue(int nbPlayer, Game game) {

		this.game = game;
		this.players = game.players;
		modele = new Modele(this);
		controle = new Controle(this, modele);
		
		frame = new JFrame("Quoridor");

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		uiBoard = new UIBoard(controle);
		
		uiBoard.tabBoard[16][8].player[0] = true;
		uiBoard.tabBoard[0][8].player[1] = true;
		
		for(int i = 0; i < nbPlayer; i++){
			players[i].tabBoard = uiBoard.tabBoard;
			players[i].vue = this;
		}
		wall1 = players[0].getNumWalls();
		wall2 = players[1].getNumWalls();
		nbWall1 = new JLabel("Walls: " + wall1);
		nbWall1.setHorizontalAlignment(JLabel.CENTER);
		nbWall2 = new JLabel("Walls: " + wall2);
		nbWall2.setHorizontalAlignment(JLabel.CENTER);
		panel.add(nbWall1, BorderLayout.SOUTH);
		panel.add(nbWall2, BorderLayout.NORTH);
		
		if(nbPlayer == 4){
			uiBoard.tabBoard[8][16].player[2] = true;
			uiBoard.tabBoard[8][0].player[3] = true;
			wall3 = players[2].getNumWalls();
			wall4 = players[3].getNumWalls();
			nbWall3 = new JLabel("Walls: "+ wall3);
			nbWall4 = new JLabel("Walls: " + wall3);
			panel.add(nbWall3, BorderLayout.EAST);
			panel.add(nbWall4, BorderLayout.WEST);
		}
		
		panel.add(uiBoard.pane, BorderLayout.CENTER);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void updateNbWall(int i){
		if(i == 0){
			wall1--;
			nbWall1.setText("Walls: " + wall1 );
		}else if(i == 1){
			wall2--;
			nbWall2.setText("Walls: " + wall2 );
		}else if(i == 2){
			wall3--;
			nbWall3.setText("Walls: " + wall3);
		}else if(i == 3){
			wall4--;
			nbWall4.setText("Walls: " + wall4);
		}
	}
	
	//donne nombre wall restant au joueur courant
	public int getWall(int currentPlayer){
		if(currentPlayer == 0){
			return wall1;
		}else if(currentPlayer == 1){
			return wall2;
		}else if(currentPlayer == 2){
			return wall3;
		}else if(currentPlayer == 3){
			return wall4;
		}else return -1;
	}
	
	//prend le numero du joueur et demande si c'est un humain ou un ai
	public static String aiOrHuman(String nombre){
		JFrame frame = new JFrame("Quoridor");
		Object[] options = {"Human", "AI"};
		while(true){
			int answer = JOptionPane.showOptionDialog(frame,
					"Player "+ nombre +" is",
					"Type of player",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,        //do not use a custom Icon
					options,     //the titles of buttons
					options[0]); //default button title
			if(answer == 0){
				return "human";
			}else if (answer == 1){
				return "ai";
			}
		}
	}
	
	//prend le nombre du joueur et lui demande son nom
	public static String nameOfPlayer(String nombre){
		JFrame frame = new JFrame("Quoridor");
		while(true){
			String name = (String)JOptionPane.showInputDialog(
					frame,
					"Player " + nombre + " name: ",
					"Name",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					"");

			return name;
		}
	}

	public static String difficultyAI(){
		JFrame frame = new JFrame("Quoridor");
		Object[] options = {"Easy", "Medium","Hard"};
		while(true){
			int answer = JOptionPane.showOptionDialog(frame,
					"AI difficulty",
					"Difficulty",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,        //do not use a custom Icon
					options,     //the titles of buttons
					options[0]); //default button title
			if(answer == 0){
				return "easy";
			}else if (answer == 1){
				return "medium";
			}else if(answer == 2){
				return "hard";
			}
		}
	}
	
	public static void victoryMsg(Game game){
		JFrame frame = new JFrame("Quoridor");
		JOptionPane.showMessageDialog(frame, "Player "+ game.getWinner().getName() + " wins!");
	}
	
	public static void invalidMoveMsg(){
		JFrame frame = new JFrame("Quoridor");
		JOptionPane.showMessageDialog(frame, "Invalid move!");
	}
}
