package cards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;

import player.player;
import player.playfleld;
import specalcardeffect.draw;
import specalcardeffect.effect;

public class card implements Runnable  {
	/**
	 * controller class for the effects
	 * and the card itself
	 * @author Eleanor Simon
	 * @param color 
	 * @param effect
	 * @param name
	 * @param imagelinek
	 * @param id
	 * @param semphore
	 * @param y
	 * @param z
	 * @param arrayuse[]
	 */
	boolean colorchanged= false;
	boolean disabled = false;
	boolean inplay =false;
	boolean drawing = true;
	boolean transmustaion = false;
	boolean alotofinpute = false;
	boolean canno = false;
	int y;
	int z;
	int id;
	int decay;
	int eff;
	int score;
	int coloreffect;
	int imageint;
	int valueforai;
	int durationofdisabled =0;
	int value = 0;
	int ratity =0;
	String name;
	String tempcolor;
	String color;
	String color2;
	player n;
	effect hero;
	playfleld p;
	Semaphore sem;
	public void makeeffic(player n,player vic)
	{
		this.n = n;
		this.p = n.getplayfield();
		this.sem = n.getplayfield().getsem();
		hero = new effect(this, vic);
		
	}
	public int imagelink()
	{
		return this.imageint;
	}
	public int getvalue()
	{
		return this.valueforai;
	}
	public void setratiy(int x)
	{
		this.ratity = x;
	}
	public int raility()
	{
		return this.ratity;
	}
	public void setvalue(int x)
	{
		this.valueforai = x;
	}
	public void setscore(int d) {
		this.score = d;
	}
	public int getscore() {return this.score;}
	public void setmanyuse(int[] w) {
		
		this.hero.alotofcard(w);
	}
	public void cannotbetarget()
	{
		this.canno =false;
	}
	public boolean cantarget()
	{
		return canno;
	}
	public boolean effectmatch(card n)
	{
		if(n.handeffect() == this.handeffect() && n.getid() == this.getid())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setvaluebase(int x)
	{
		this.valueforai=10-x;
	}
	
	/*public void user(boolean delay) throws IOException
	{
		int z =0;
	int y=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.print("usecard");
		 String str = br.readLine();
		 int[] hero = new int[str.length()-2];
		 for(int x=0; x< str.length(); x++)
		 {
		if(x ==0)
		{
		y = Character.getNumericValue(str.charAt(x));	
		}
		else if(x == 1)
		{
			z = Character.getNumericValue(str.charAt(x));;
		}
		else
		{
			hero[x-2] = Character.getNumericValue(str.charAt(x));
		}
		 }
		if(delay)
		{
		this.setmanyuse(hero);
		this.usecard(y, z);
		}
		else
		{
			this.usecard(y, z);
		}
		
		 
	}*/
	public void restcolor()
	{
		if(coloreffect <= 0)
		{
		this.tempcolor = this.color;
		}
	}
	public void defence()
	{
		this.geteffet().specialeff();
	}
	public boolean dyingeffect(boolean u)
	{
		/*if(this.eff == 7 )
		{
			if(!u)
			{
				
				return true;
			}
			
		}*/
	 if(this.eff == 3 && this.id == 8 ||  this.eff ==3 &&this.id == 9)
		{
			if(u)
			{
				this.geteffet().specialeff();
			}
			else
				return true;
	
		}
		return false;
	}
	public void setastempcolor(String color,int d ) 
	{ 
		this.coloreffect = d;
		String h= this.color; 
		this.tempcolor = h;
		this.color = color;
		colorchanged = true;
		
	
	}
	public String getname() {return this.name;}
	public boolean isdisable() { return disabled;}
	public player owner(){return n;}
	public int handeffect()	{return eff;}
	public int getid() {return id;}
	public effect geteffet() {return hero;}
	public void disablecard(int s) { s =durationofdisabled;
	if(!this.canno)
	{
	disabled = true;
	}}
	public boolean colormatch(String colo)
	{
		if(this.color.equalsIgnoreCase(colo))
		{
			return true;
		}
		else if(this.color2.equalsIgnoreCase(colo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public card(String name,String c,String c2, int id, int decay, int eff,int imageint)
	{
		//creates card with all effects 
		this.name = name;
		this.color = c;
		this.color2 = c2;
		this.id = id;
		this.decay = decay;
		this.eff = eff;
		this.imageint =imageint;
	
		//
		
		}
	

public void usecard(int y,int z)
	{ 
		// actives the card
		if(!this.disabled)
		{
			this.y =y;
			this.z = z;
	
		
		this.run();
		}
		
	}
	
	/*public void setcardanduse(int y, int z)
	{
		hero.changeuse(y,z);
		hero.usecard();
	}*/
	// card is on the playfleid 
	public card played()
	{
		inplay = true;
		return this;
		
	}
	// double color's do not count
public String getcolor() {return color;}
	public void turnpast() {
		
	/** 
	 * called when turn pass and
	 * card decay 
	 * if 0 card is removed
	 */
		decay--;
		if(this.colorchanged)
		{
			this.coloreffect--;
			restcolor();
		}
		if(this.disabled)
		{
		this.durationofdisabled--;
		if(this.durationofdisabled < 1)
		{
			this.disabled = false;
		}
		}
	
	
	}
	public void setdecay(int s) { decay =s;}
	// used for certain effects
	public int getdecay() { return decay;}

	@Override
	public void run() {
		/**
		 * starts thread for effect and passes data
		 * uses a semaphore to prevent data erros 
		 * @param y
		 * @param z
		 * @param mannyuses()
		 */
		
	boolean tf	=this.sem.tryAcquire();
	if(tf)
	{
		
		this.hero.usecard(y,z);
		this.sem.release();
	}
	else
	{
	try {
		Thread.sleep(50);
		
		this.sem.release();
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	
		
	
		
	}


}

