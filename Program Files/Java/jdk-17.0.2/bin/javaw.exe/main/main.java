package main;

import java.util.Scanner;

import game.gamemanger;

public class main {

	public static void main(String[] args) {
		gamemanger game = new gamemanger();
		  Scanner myObj = new Scanner(System.in);
		System.out.println("one player or two:");
		int cast = myObj.nextInt();
		if(cast == 1)
		{
			game.game(false, true,2);
		}
		else if(cast ==2)
		{
			game.game(false, false,2);
		}
		else
		{
			game.game(true, true,2);
		}
		
		// TODO Auto-generated method stub

	}

}
