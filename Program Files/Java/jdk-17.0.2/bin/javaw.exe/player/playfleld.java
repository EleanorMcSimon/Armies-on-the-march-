package player;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import cards.card;

public class playfleld {
	boolean cannotDraw = false;
	int turn =0;
	  Semaphore sem = new Semaphore(1,true);
	player current;
	ArrayList<card> playfield  = new ArrayList<card>();
	
	public Semaphore getsem()
	{
		return sem;
	}
	
	public ArrayList<card> getfield(){return playfield;}
	public playfleld(player n)
	{
		/**
		 * sets playe
		 */
		this.current = n;
	}
	
	public void printcard()
	{
		/**
		 * prints cards
		 */
		for(int x = 0; x < playfield.size(); x++)
		{
			if(playfield.get(x).isdisable())
			{
				System.out.print("Is Disabled");
			}
			System.out.print(playfield.get(x).getcolor()+ "_");
			System.out.print(playfield.get(x).getname()+ " ");
		
		}
		System.out.println("");

	}
	public void decaycard()
	{
		/**
		 * decays cards in playfield
		 */
		if(!playfield.isEmpty())
		{
			for(int x =0; x < this.playfield.size(); x++)
			{
				playfield.get(x).turnpast();
				if(this.playfield.get(x).getdecay() ==0)
				{
					this.playfield.remove(x);
				}
			}
			
		}
	
		
		
	}
	public void cardplay(card[] f)
	{
		/** 
		 * plays cards
		 */
for(int x =0; x < f.length; x++)
{

	if(f[x] != null)
	{
	playfield.add(f[x]);
	}
}
	}
	public boolean specialcard(int x)
	{
		/**
		 * checks if card is use more than two times
		 */
	card array = new card("Draw","black","none",7,0,1,0);
	card n = new card("Tree","green","none",1,0,2,0);
	card oi = new card("blood","black","none",0,0,2,0);
	card f = new card("change","green","none",0,0,3,0);
	
	card w = new card("lamb","black","none",9,2,3,0);
	if(x < this.playfield.size())
{
		if(this.playfield.get(x).effectmatch(w)|| this.playfield.get(x).effectmatch(oi)||this.playfield.get(x).effectmatch(n)||this.playfield.get(x).effectmatch(array) ||this.playfield.get(x).effectmatch(f))
		{
			return true;
		}
}
		return false;
	}
	public void activecards(int[] d,Stack<int[]>  c, Stack<int[]> p )
	{/**
	uses card on the playfeild
	*/
		int[] y = new int[2];
	
		for(int x=0;x < d.length; x++)
		{ 
	
		
		
	if(specialcard(d[x])){
	if(!p.isEmpty())
	{
		this.playfield.get(d[x]).setmanyuse(p.pop());
	}
	}
			y=c.pop();
			if(d[x] < this.playfield.size())
			{
			this.playfield.get(d[x]).usecard(y[0], y[1]);
			}
		}
	}
	public void defensitveActive()
	{
		for(int x=0; x < this.playfield.size(); x++)
		{
			if(this.playfield.get(x).handeffect() == 7)
			{
				this.playfield.get(x).defence();
				
			}
		}
		
	}
	public void removcards( card[] d, boolean draw)
	{
		for(int x =0; x < d.length; x++)
		{
			removecard(d[x]);
		}
	}
	
	public void removecard(card d)
	{
		
		
	if(playfield.indexOf(d) != -1)
	{
			if(playfield.get(playfield.indexOf(d)) == d)
			{
			playfield.remove(d);
			}
			
	}

		
		
	}
	public int addvalue()
	{
		/**
		 * for ai
		 */
		int value =0;
		for(int x =0; x < this.getfield().size(); x++)
		{
			switch(this.getfield().get(x).handeffect())
			{
			case 0: this.getfield().get(x).setvalue(1+(this.getfield().get(x).getdecay())*this.getfield().get(x).raility()- this.getfield().get(x).getdecay()); break;
			case 1:	this.getfield().get(x).setvalue(3+(this.getfield().get(x).getdecay())* this.getfield().get(x).raility()-  this.getfield().get(x).getdecay()); break;
			case 2: this.getfield().get(x).setvalue(1+(this.getfield().get(x).getdecay())* this.getfield().get(x).raility()+ this.getfield().get(x).getscore()-  this.getfield().get(x).getdecay()); break;
			case 3: this.getfield().get(x).setvalue(4+( this.getfield().get(x).getdecay())* this.getfield().get(x).raility()-  this.getfield().get(x).getdecay()); break;
			case 4: this.getfield().get(x).setvalue(3+(this.getfield().get(x).getdecay())*this.getfield().get(x).raility()- this.getfield().get(x).getdecay()); break;
			case 5: this.getfield().get(x).setvalue(0+(this.getfield().get(x).getdecay())*this.getfield().get(x).raility()- this.getfield().get(x).getdecay()); break;
			case 6: this.getfield().get(x).setvalue(1+(this.getfield().get(x).getdecay())*this.getfield().get(x).raility()- this.getfield().get(x).getdecay()); break;
			case 7: this.getfield().get(x).setvalue(6+(this.getfield().get(x).getdecay())*this.getfield().get(x).raility()- this.getfield().get(x).getdecay()); break;
			}
			value += this.getfield().get(x).getvalue();
		}
		return value;
	}
	public void disable()
	{
		this.cannotDraw = true;
		this.cannotDraw = true;
		
	}
	
}
