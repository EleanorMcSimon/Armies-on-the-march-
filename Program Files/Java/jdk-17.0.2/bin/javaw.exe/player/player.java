package player;

import java.util.ArrayList;
import java.util.Stack;

import cards.card;

public class player {
	boolean outofcards = false;
	int turnforplayer =0;
	int score =1;
	int drawfrom =0;
	boolean skipturn = false;
	playfleld play = new playfleld(this);
	ArrayList<card> hand = new ArrayList<card>();
	Stack<card> drawdeck = new Stack<card>();

	public playfleld getplayfield() {return play;}
	public int getscore() {return score;}
	public void score(int i) { score = i;}
	public ArrayList<card> gethand(){return hand;}
	public void skipturn()
	{ skipturn = true;
	}
	public boolean TurnSkipped()
	{
		return this.skipturn;
	}
	public player(int turn)
	{
		turnforplayer = turn;
	}
	public void addtoscore(int value)
	{
		/** 
		 * add to player score
		 */
		value = score+value;
		score = value;
	}
	public void setdrawfrom(int d)
	{
		/**
		 * set where the player draw from
		 */
		this.drawfrom = d;
	}
	public void showto(card[] f)
	{
		/**
		 * used in unknownable unbond
		 */
		System.out.print("seeing cards");
		for(int x=0; x < f.length; x++)
		{
			System.out.print(f[x].getcolor()+ "_");
			System.out.print(f[x].getid()+ "_");
			// add a way for ai to see
			
		}
		System.out.println("");
		
	}
	public void printcardhand()
	{
		System.out.print("hand ");
		for(int x = 0; x < this.hand.size(); x++)
		{
			System.out.print(hand.get(x).getcolor()+ "_");
			System.out.print(hand.get(x).getname()+ "	");
			
		}
		System.out.println("");
	}
	public void printplayefield()
	{
		System.out.print("playfield ");
		this.play.printcard();
	}
	public void draw(int times)
	{
		/**
		 * draw from draw deck
		 */
		System.out.print("drawing");
		if(drawdeck.size() == 1)
		{
			hand.add(drawdeck.pop());
			
		}
		else
		{
		for(int x =0; x < times; x++)
		{
			if(drawdeck.isEmpty())
			{
				this.outofcards = true;
				break;
			}
	

		
		if(this.hand.size() >= 8)
		{
			break;
		}
		else
		{
		hand.add(drawdeck.pop());
		}
		}
		}	
	
		
		
	}
	public int gethandvalue()
	{
		int value =0;
	
		for(int x=0; x < this.hand.size(); x++)
		{
		//this.hand.get(x).setvalue(this.hand.get(x).getvalue()+this.hand.get(x).raility());
		 value =+ this.hand.get(x).getvalue();
		}
		return value;
	}
	public Stack<card> getdrawdeck()
	{
		return drawdeck;
	}
	public void lookatdeck()
	{
		for(int x=0; x < this.drawdeck.size(); x++)
		{
			System.out.print(this.drawdeck.get(x).getname());
		}
	}
	public void spremovefor(card[] n)
	{
		/**
		 * used for ai
		 */
		for(int x =0; x < n.length; x++)
		{
			if(hand.indexOf(n[x]) != -1)
			{
			hand.remove(hand.indexOf(n[x]));
			}
		}
	}
	public void removefor(card[] n)
	{
		/**
		 * remove for play
		 */
		for(int x =0; x < n.length; x++)
		{
			if(hand.indexOf(n[x]) != -1)
			{
			hand.remove(hand.indexOf(n[x]));
			}
		}
		play.cardplay(n);
	}
}
