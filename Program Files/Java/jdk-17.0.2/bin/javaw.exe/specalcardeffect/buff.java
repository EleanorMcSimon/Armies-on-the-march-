package specalcardeffect;

import cards.card;
import player.player;

public class buff extends effect{

	int lamby =0;
	int lambz =0;
	int[] grater;
	public buff(card n, player op) {
		super(n, op);
		// TODO Auto-generated constructor stub
	}
	public void playcard()
	{
		/**
		 * swtich of all buffs stored here
		 */
		switch(this.x)
		{
		case 0:  twoused(); break;
		case 1: setalltoOnecolor(true); break;
		case 2: setalltoOnecolor(false); break;
		case 3: goldrush(); break;
		case 4:{ decaymod (y,this.n.getdecay());
		this.n.setdecay(0); break;
			}
		case 5: {
			for(int x =0; x < this.owner.getplayfield().getfield().size(); x++)
			{
				if(this.owner.getplayfield().getfield().get(x).colormatch(color))
						{
					decaymod (z,(this.owner.getplayfield().getfield().get(x).getdecay()+2));
						}
				
			}
	break;
		}
		case 6: this.owner.getplayfield().getfield().get(y).setastempcolor(color, this.n.getdecay()); break;
		case 7:digdeeper(); break;
		case 8: this.primelamb(); break;
		case 9:this.primelamb(); break;
		case 10: toscoringcard(1); break;
		case 11: getalloutput(2); break;
		}
	}
	public void active()
	{
		switch(this.x)
		{
		case 8: lambdeath(false); break;
		case 9: lambdeath(true); break;
		}
	}
	private void getalloutput(int u)
	{
		/** get all scoring of a card in one turn
		 * @param u 
		 * divides the amout scored
		 */
		System.out.println("look");
		System.out.print(owner.getplayfield().getfield().get(y).handeffect());
		
		if(owner.getplayfield().getfield().get(y).colormatch(color)&& owner.getplayfield().getfield().get(y).handeffect() == 2)
	{
		int w =(owner.getplayfield().getfield().get(y).getdecay()/u);
		int out = w*owner.getplayfield().getfield().get(y).getscore();
		if(out ==0)
		{
			out++;
		}
		this.owner.addtoscore(out);
		owner.getplayfield().getfield().get(y).setdecay(0);
	}
	
	}
	private void primelamb()
	{
		this.lamby =y;
		this.lambz = z;
		this.grater = this.arrayuse;
	}
	private void lambdeath(boolean grater)
	{
		/** 
		 * manges death of lambs
		 * @param greater
		 */
		if(!grater)
		{
		
			this.owner.getplayfield().getfield().get(this.lamby).usecard(-1,this.lambz);
		}
		else {
			for(int x =0; x < this.grater.length; x++)
			{
				this.owner.getplayfield().getfield().get(this.lamby).usecard(-1, this.grater[x]);
			}
		}
		
	}
	public boolean isscoring(boolean color, card m)
	{
		/**
		 * method helper used to check color for effect
		 * @param color 
		 * @param card 
		 */
		if( color)
		{
			if(this.owner.getplayfield().getfield().get(y).geteffet().cardtype ==2 && this.owner.getplayfield().getfield().get(y).colormatch(this.color))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(this.owner.getplayfield().getfield().get(y).geteffet().cardtype ==2)
			{
				return true;
			}
			else {
				return false;
			}
			}
		
		}
	
	private void twoused()
	{
		/**
		 * use a card twice 
		 */
		if(this.arrayuse.length >= 4 && this.owner.getplayfield().getfield().size() > y)
		{
			this.owner.getplayfield().getfield().get(y).disablecard(1);
		this.owner.getplayfield().getfield().get(y).usecard(this.arrayuse[0], this.arrayuse[1]);
		this.owner.getplayfield().getfield().get(y).usecard(this.arrayuse[2], this.arrayuse[3]);
	
		}
		}
	private void setalltoOnecolor(boolean ui)
	{ /**
	*set all card or scoreing cards to one color
	*/
		
		for(int y =0; y < this.owner.getplayfield().getfield().size(); y++)
		{	
		if(ui)
		{
			System.out.println(this.owner.getplayfield().getfield().get(y).handeffect());
			if(this.owner.getplayfield().getfield().get(y).handeffect() == 2)
			{
				this.owner.getplayfield().getfield().get(y).setastempcolor(color, this.n.getdecay());
			}
		}
		else
		{
			this.owner.getplayfield().getfield().get(y).setastempcolor(color, this.n.getdecay());
		}
		}
	}
	private void decaymod (int x, int y)
{
		/**
		 * changes the decay of a card
		 */
	this.owner.getplayfield().getfield().get(x).setdecay(y);
}
	private void toscoringcard(int y)
{
	/**
	 * Adds score to a scoring card
	 */
	 int mem = this.owner.getplayfield().getfield().get(this.y).geteffet().x +y;
	 this.owner.getplayfield().getfield().get(this.y).geteffet().x = 5;
	 this.owner.getplayfield().getfield().get(this.y).usecard(0, 0);
	  this.owner.getplayfield().getfield().get(this.y).geteffet().x =mem ;
}
	private void digdeeper()

{
	/** 
	 * removes a card to add to an other
	 * 
	 */
	if(!this.owner.getplayfield().getfield().isEmpty() && this.owner.getplayfield().getfield().size() > y)
{
	this.y = 2*(this.owner.getplayfield().getfield().get(y).getscore());

toscoringcard(this.y);

for(int y =0; y < this.owner.getplayfield().getfield().size(); y++)
{
;
	if(this.owner.getplayfield().getfield().get(y).effectmatch(n)) 
	{
		if(this.owner.getplayfield().getfield().get(y).getdecay() <= 0)
		{
	int f=	this.owner.getplayfield().getfield().get(y).getdecay()-1;

	
		this.owner.getplayfield().getfield().get(y).setdecay(f);
		}
		this.owner.getplayfield().getfield().get(y).setdecay(0);
}
}
}
}
	private void goldrush()
{
	/**
	 * doubles score if all cards are gold
	 */
	
	for(int x =0; x < this.owner.getplayfield().getfield().size(); x++)
	{
		if(!this.owner.getplayfield().getfield().get(x).colormatch(color))
		{
		this.n.disablecard(1);
		}
	}
	if(!this.n.isdisable())
	{
		for(int y =0; y < this.owner.getplayfield().getfield().size(); y++)
		{
			if(this.owner.getplayfield().getfield().get(y).handeffect() == 2 && this.owner.getplayfield().getfield().get(x).colormatch(color))
			{
				toscoringcard(y);
				 
		}
		}
	}
}
}
