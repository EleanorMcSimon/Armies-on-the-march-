package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import ai.Motocralo;
import cards.card;
import javafx.scene.image.Image;
import player.player;

public class gamemanger {
player one = new player(0);
player two = new player(1);
int turn = 1;
Stack<card> moveing = new Stack<card>();

public player two() {return two; }
public player one() {return one; }
public void aiturn()
{	
	/**
	 * player two ai turn
	 */
partone();
Motocralo n = new Motocralo(two,one);
if(!two.gethand().isEmpty())
{
n.whatcardstoplay();
}
if(!two.getplayfield().getfield().isEmpty())
{
n.minmax();
two.getplayfield().defensitveActive();
}
one.getplayfield().defensitveActive();
turn++;
}
public void testingai()
{	
partone();
Motocralo n = new Motocralo(one,two);
if(!one.gethand().isEmpty())
{
n.whatcardstoplay();
}
if(!one.getplayfield().getfield().isEmpty())
{
n.minmax();
two.getplayfield().defensitveActive();
}
turn++;
}
public void playeturn(player n)
{

	partone();
	if(!n.gethand().isEmpty())
	{
		textpartwo(n);	
	}
	if(!n.getplayfield().getfield().isEmpty())
	{
	this.ativewhat(n);
	}
	turn++;
}
public void startround()
{
	/**
	 * shuffles the decks and deals to the players
	 * 
	 */
Stack<card> deck = new Stack<card>();
Stack<card> t1a0 = new Stack<card>();	
Stack<card> t2a3 = new Stack<card>();	
Stack<card> t4a5 = new Stack<card>();	
	try {
	
		Scanner sc = new Scanner(new File("C:\\Users\\ellies\\Documents\\card.csv"));
		sc.useDelimiter(",");
		sc.nextLine();
		while(sc.hasNextLine())
		{String[] r = sc.nextLine().split(",");
		int wh = Integer.parseInt(r[7]);
		int par =Integer.parseInt(r[5]);
		int id = Integer.parseInt(r[2]);
		int e = Integer.parseInt(r[3]);
		int d = Integer.parseInt(r[6]);
	System.out.print(r[0]);
		card f = new card(r[0],r[1],r[4],id,e,d,wh);
		f.setratiy(par);
		if(f.getid() == 2)
		{
			System.out.print(f.getname());
			int en = Integer.parseInt(r[8]);
			f.setscore(en);
		}
			switch(par)
			{
			case 0: t1a0.push(f);   break;
			case 1: t1a0.push(f);  t1a0.push(f);  break;
			case 2:{
				for(int x =0; x < 2; x++)
				{
					t2a3.push(f);
				}
				break;
			}
			case 3:{
				for(int x =0; x < 3; x++)
				{
					t2a3.push(f);
				}
				break;
			}
			case 4:{
				for(int x =0; x < 3; x++)
				{
					t4a5.push(f);
				}
				break;
			}
			case 5:{
				for(int x =0; x < 4; x++)
				{
					
					t4a5.push(f);
				}
				break;
			
			}
			}
		}
		    Collections.shuffle(t4a5);
		    Collections.shuffle(t2a3);
		    Collections.shuffle(t1a0);
			for(int x= 0; x < 130; x++)
			{
				if(deck.size() == 120)
				{
					break;
				}
				 if(!t1a0.isEmpty())
				 {
					 int thread= ThreadLocalRandom.current().nextInt(t1a0.size());  
				 deck.push(t1a0.get(thread));
				 t1a0.remove(t1a0.get(thread));
				 }
				 for(int z = 0; z < 1; z++)
				 {
					 if(!t2a3.isEmpty())
					 {
						 int thread= ThreadLocalRandom.current().nextInt(t2a3.size());  
					 deck.push(t2a3.get(thread));
					 t2a3.remove(t2a3.get(thread));
					 }
				 }
				 
			 for(int y = 0; y < 2; y++)
			 {
				 if(!t4a5.isEmpty())
				 {
					int thread= ThreadLocalRandom.current().nextInt(t4a5.size());  
				 deck.push(t4a5.get(thread));
				 t4a5.remove(t4a5.get(thread));
				 }
			 }
		
			
			}
		 
			for(int z =0; z < 60; z++)
			{
			
					if(!deck.isEmpty())
					{
					deck.peek().makeeffic(one, two);
					this.one.getdrawdeck().push(deck.pop());
					}
			}
			for(int a =0; a < 60; a++)
			{
				if(!deck.isEmpty())
				{
				deck.peek().makeeffic(two, one);
				this.two.getdrawdeck().push(deck.pop());	
				}	
			}
				
				
			
		
		Collections.shuffle(this.one.getdrawdeck());
		Collections.shuffle(this.two.getdrawdeck());
		System.out.println(this.one.getdrawdeck().size());
		System.out.println(this.two.getdrawdeck().size());
		this.one.draw(5);
		this.two.draw(5);
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
public void partone()
{
	/**
	 * manges part of the game 
	 */
	if(this.turn %2 ==0)
	{
		int oldsize = this.two.getplayfield().getfield().size();
		
		
		two.getplayfield().decaycard();
		int w = this.two.getplayfield().getfield().size()- oldsize;
		if(w > 0)
		{
			two.draw(w);
		}
		else
		{
			two.draw(1);
		}
		System.out.println("op cards");
		one.printplayefield();
		System.out.println("");
	
	}
	else
	{
		int oldsize2 = this.one.getplayfield().getfield().size();

	

		one.getplayfield().decaycard();	
		int n = this.one.getplayfield().getfield().size() - oldsize2;
		if(n> 0)
		{
			one.draw(n);
		}
		else
		{
			one.draw(1);
		}
		System.out.println("op cards");
		two.printplayefield();
		System.out.println("");
	}
}
public void textpartwo(player n)
{
	n.printcardhand();
	
	  Scanner myObj = new Scanner(System.in);

		System.out.println("entre text:");
		String cast = myObj.nextLine();
		
		
		 
	
	 card[] inw = new card[cast.length()];
		for(int x=0; x < inw.length; x++)
		{
			inw[x] = n.gethand().get(Character.getNumericValue(cast.charAt(x)));
			
			
		 }
		this.moveing.clear();
	 cardplay(inw);
}

public void partTwoplay(int x)
{/** lamba called
*
*/
	if(this.turn %2 ==0)
	{
		moveing.push(two.gethand().get(x));
	}
	else
	{
		moveing.push(one.gethand().get(x));
	}
	
}
public void  cardplay(card[] o)
{
	if(this.turn %2 ==0)
	{
		two.removefor(o);
	}
	else
	{
		one.removefor(o);
	}
}
private void ativewhat(player n)
{
	/**
	 * prombt for the player to use cards
	 */
	n.printplayefield();
	int w =0;
	int clr =0;
	
	  Scanner myObj = new Scanner(System.in);
	  Stack<int[]>  c = new Stack<int[]>();
	  Stack<int[]> two = new Stack<int[]>();
	  Stack<Integer> where = new Stack<Integer>();
for(int x =0; x < n.getplayfield().getfield().size(); x++)
{

	 System.out.println("active card?"+ n.getplayfield().getfield().get(x).getname() );
	 boolean f= myObj.nextBoolean();
	 if(f == true)
	 {
		 where.push(x);
		 myObj.nextLine();
		 System.out.print("do what:");
		 String d = myObj.nextLine();
		 
		 int[] out = new int[d.length()];
		 for(int y =0; y < out.length; y++)
		 {
			 out[y] = Character.getNumericValue(d.charAt(y));
		 }
		 if(out.length > 2 )
		 {
			 if(n.getplayfield().specialcard(x))
			 {
				 int[] select = { out[0], out[1]};
				 int[] more = new int[out.length-3];
				 for(int wh = 2; wh < out.length; wh++)
				 {
					 more[wh] = out[wh];
				 }
				 c.push(select);
				 two.push(more);
				 
			 }
			 
		 }
		 else
		 {
			 c.push(out);
		 }
	 }
	
}
int[] hello = new int[where.size()];
for(int z =0; z < hello.length; z++)
{
	hello[z] = where.pop().intValue();
}
n.getplayfield().activecards(hello, c, two);
}
public void game(boolean ai, boolean ai2, int rounds)
{
/**
 * starts game
 */
	
for(int x =0;  x < rounds;x++)
{ this.startround();
	while(!this.one.getdrawdeck().isEmpty() && !this.two.getdrawdeck().isEmpty() )
	{
	
if(this.turn%2 == 0)
{
		if(ai)
		{
			this.aiturn();
			System.out.println(this.two.getdrawdeck().size());
			two.printcardhand();
			System.out.println(" score"+two.getscore());
		}
		else
		{
		
			this.playeturn(two);
			one.getplayfield().defensitveActive();
			
		}
}
else if(this.turn%2 != 0)
{
		if(ai2)
		{
			this.testingai();
			
			System.out.println(2);
			one.printcardhand();
			System.out.println(" score"+one.getscore());
		}

		else
		{
		this.playeturn(one);
		two.getplayfield().defensitveActive();
		}
	}
	}
}
Scanner myObj = new Scanner(System.in);
if(this.one.getscore() > this.two.getscore())
{
	System.out.println("player one won by"+this.one.getscore()+" to" +this.two.getscore());
	System.out.print("Would you like to play again yes/no?");
	String fo = myObj.next();
	if(fo.equals("yes"))
	{
	this.game(ai, ai2, rounds);
	}
}
else if(this.one.getscore() < this.two.getscore())
{
	System.out.println("player two won by"+this.two.getscore()+" to" +this.one.getscore());
	System.out.print("Would you like to play again yes/no?");
	String fo = myObj.next();
	if(fo.equals("yes"))
	{
	this.game(ai, ai2, rounds);
	}
}
else
{
	System.out.println("It was a draw"+this.two.getscore()+" to" +this.one.getscore());
	System.out.print("Would you like to play again yes/no?");
	String fo = myObj.next();
	if(fo.equals("yes"))
	{
	this.game(ai, ai2, rounds);
	}
}
	}


public Image[] giveview()
{
	   String card[];
	//Creating a File object for directory
    File directoryPath = new File("C:\\Users\\ellies\\landmarkproject\\Armise on march protype\\bin\\images\\");
    //List of all files and directories
    card = directoryPath.list();
    return null;
    
	
}
}
