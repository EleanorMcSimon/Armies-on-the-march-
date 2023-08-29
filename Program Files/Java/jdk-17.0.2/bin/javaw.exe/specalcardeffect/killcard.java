package specalcardeffect;

import java.io.IOException;
import java.util.Stack;

import cards.card;
import player.player;

public class killcard extends effect{


	public killcard(card n, player op) {
		
		super(n, op);
		
	
		// TODO Auto-generated constructor stub
	}
	public void playcard()
	{
		switch(this.x)
		{
		case 0: bloodtower(); break;
		case 1: robotworkers(); break;
		case 2: snowsweaped(); break;
		case 3: bloodBloodBlood(); break;
		case 4 : trader(); break;
		case 5: faster(); break;
		}
	}
synchronized private void bloodtower()
{
	/**
	 * removes card from play extage 
	 * kill and oppient card
	 * 
	 */


	//this.owner.getplayfield().getfield().get(y).dyingeffect(true);
	if(!owner.getplayfield().getfield().isEmpty() && this.owner.getplayfield().getfield().size() > y)
	{
	
	if(this.y != -2)
	{
	card f = owner.getplayfield().getfield().get(y);
	this.owner.getplayfield().getfield().remove(f);
	}
	if(z < op.getplayfield().getfield().size())
	{
	
	card n = op.getplayfield().getfield().get(z);
op.getplayfield().getfield().remove(n);
	
	
}
	}
	

	}
	

private void robotworkers()
{
	/**
	 * kill all gold miner cards
	 */
	for(int x=0;  x <this.owner.getplayfield().getfield().size(); x++)
	{
		
		if(this.owner.getplayfield().getfield().get(x).handeffect() == 3)
		{
			if(this.owner.getplayfield().getfield().get(x).getid() == 10)
		
		{
			this.owner.addtoscore(2);
			System.out.print(owner.getscore());
			this.owner.getplayfield().getfield().remove(x);
			
		}
		}
	}
}
private void snowsweaped()
{
	/**
	 * kill a card and draw the about of decay of the card 
	 * 
	 */
	while(!this.owner.getplayfield().getfield().isEmpty())
	{
		this.owner.draw(this.owner.getplayfield().getfield().get(0).getdecay());
		this.owner.getplayfield().getfield().remove(0);
	}
}
private void bloodBloodBlood()
{
	/**
	 * kills all lambs to kill oppeint cards
	 * 
	 */
	Stack<card> kill = new Stack<card>();
	
	for(int x=0; x < this.owner.getplayfield().getfield().size(); x++)
	{
		if(this.owner.getplayfield().getfield().get(x).dyingeffect(false)&& this.owner.getplayfield().getfield().get(x).colormatch(this.color))
		{
			kill.push(this.owner.getplayfield().getfield().get(x));
		}
	}
	Stack<card> takedout = new Stack<card>();
	
	int oe = kill.size();
	
	for(int x=0;  x < kill.size();x++)
	{
		if(this.op.getplayfield().getfield().size() > x)
		{
			takedout.push(this.op.getplayfield().getfield().get(x));
		}
		else
		{
			this.op.getplayfield().getfield().clear();
			break;
		}
	}
	for(int y =0; y < oe; y++)
	{
			//n.usecard(this.arrayuse[y], this.arrayuse[y+1]);
		if(!this.op.getplayfield().getfield().isEmpty() && !takedout.isEmpty())
		{
			this.y = -2;
			this.z = this.op.getplayfield().getfield().indexOf(takedout.pop());
			bloodtower();
			if(!takedout.isEmpty() && !kill.isEmpty())
			{
				break;
			}
			this.y = this.owner.getplayfield().getfield().indexOf(kill.pop());
			this.z = this.op.getplayfield().getfield().indexOf(takedout.pop());
			bloodtower();
		}
		else
		{
			this.owner.getplayfield().getfield().remove(kill.pop());
		}
		
	}
	
}
private void trader()
{
	/**
	 * color based card killer
	 */
	if(!this.op.getplayfield().getfield().isEmpty())
	{
	if( this.op.getplayfield().getfield().get(z).colormatch(owner.getplayfield().getfield().get(y).getcolor()))
	{
	//	this.owner.getplayfield().getfield().get(y).dyingeffect(false);
		//this.op.getplayfield().getfield().get(z).disablecard(1);;
		if(!this.owner.getplayfield().getfield().isEmpty() && this.owner.getplayfield().getfield().size() > y) 
		{
			card f = owner.getplayfield().getfield().get(y);
			this.owner.getplayfield().getfield().remove(f);
		
	

		if(this.op.getplayfield().getfield().size() > z)
		{
			card n = op.getplayfield().getfield().get(z);
			this.op.getplayfield().getfield().remove(n);
		}
		}
	
	}
	}
}
private void faster()
{
	/** 
	 * kills mine to get more scoring
	 */
	if( this.owner.getplayfield().getfield().get(y).colormatch(color) && this.owner.getplayfield().getfield().get(y).handeffect() == 2 && this.owner.getplayfield().getfield().get(z).colormatch(color) && this.owner.getplayfield().getfield().get(z).handeffect() == 2 )
	{
		 this.owner.getplayfield().getfield().get(y).setscore( this.owner.getplayfield().getfield().get(y).getscore()+2);
		 this.owner.getplayfield().getfield().remove(z);
	}
}
}
