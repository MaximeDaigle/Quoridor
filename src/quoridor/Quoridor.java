package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
* @author Joey Tuong
* @author Luke Pearson
* 
* modified by Maxime Daigle
*/
public class Quoridor {
	private static Game game;
	/**
	 * IO handling function
	 * gets players and starts the game
	 * @throws IOException 
	 */
	//n[0] est le nombre de joueur 2 ou 4
	public static void main (String[] n) throws IOException{ 
		Vue vue;
		String[] AInames = {"GLaQuOS", "Deep Cyan", "Quoribot", "Digital Turk"};
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String temp = "";
		Integer size =0;
		Player[] players;
		while(size != 2 && size != 4	){
			temp = "";
			if( (n.length != 1) || !(n[0].equalsIgnoreCase("2") || n[0].equalsIgnoreCase("4")) ){
				while(!tryParseInt(temp)){
					System.out.print("Number of players (2/4): ");
					temp = in.readLine();
				}
			}else{ temp = n[0];}
			size = Integer.parseInt(temp);
		}
		players = new Player[size];

		for (Integer i = 1; i<= size; i++){
			temp = "";
			while (!temp.toLowerCase().matches("(ai?|h(uman)?)")) {
				temp = Vue.aiOrHuman(i.toString());
			}
			if (temp.toLowerCase().matches("h(uman)?")){
				players[i - 1] = new Human();
				temp = Vue.nameOfPlayer(i.toString());
				players[i - 1].setName(temp);
				if(temp.equalsIgnoreCase("")){
					players[i - 1].setName(""+i);
				}
			} else {
				while (!temp.toLowerCase().matches("(e(asy)?|m(edium)?|h(ard)?)")){
					temp = Vue.difficultyAI();
				}
				Difficulty diff;
				if (temp.toLowerCase().matches("e(asy)?")){
					diff = Difficulty.Easy;
				} else if (temp.toLowerCase().matches("h(ard)?")){
					diff = Difficulty.Hard;
				} else {
					diff = Difficulty.Normal;
				}
				players[i - 1] = new AI(diff);
				players[i - 1].setName(AInames[i-1]);
				players[i - 1].setAI();

			}
		}
		game = new Game(players);
		vue = new Vue(size, game);
		game.play();
		game.getBoard().printBoard();
		
		Vue.victoryMsg(game);
	}
	/**
	 * @param string which is meant to be an int
	 * @return false or true if correct parse
	 */
	static boolean tryParseInt(String value)  
	{  
		try  
		{  
			Integer.parseInt(value);  
			return true;  
		} catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
	}
}
