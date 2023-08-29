package specalcardeffect;





import java.io.IOException;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import cards.card;
import player.player;

public class neff extends effect {
	/*
	 * These cards are typically black cards  and focus on hindering your opponent, 
	 * With little or no benefit to yourself 
	 */
	
	public neff(card n, player op) {
		super(n, op);
	}
	public void playcard()
	{
		switch(this.x)
		{
		case 0: drawing(); break;
		case 1: unionStrike(); break;
		case 2: disablecard(); break;
		case 3: skipturn(); break;
		case 4: darkness(); break;
		case 5: disablecard(0); break;
		case 6: disablecard(1); break;
		case 7:this.brokenhands(); break;
		}
	}
	private void drawing()
	{
		/**stop the other player from drawing
		 * 
		 */
		op.getplayfield().disable();
	}
	private void disablecard()
	{
		/**
		 *  this card can not use abilities
		 *  kept around for testing  
		 */
		this.op.getplayfield().getfield().get(y).disablecard(this.n.getdecay());
	}
	private void unionStrike()
	{
		int player1 =0;
		
		Stack<Integer> toremove = new Stack<Integer>();
		for(int x =0; x < (this.owner.getplayfield().getfield().size()); x++)
		{
			if(this.owner.getplayfield().getfield().get(x).colormatch(color) && !this.owner.getplayfield().getfield().get(x).cantarget()) 
			{
				toremove.push(x);
				player1++;
				
			}
		}
		for(int q =0; q < (player1/2); q++)
		{toremove.pop();
			
		}
		while(!toremove.isEmpty())
		{
			this.owner.getplayfield().getfield().get(toremove.pop()).disablecard(this.n.getdecay());
		}
		for(int y =0; y < this.op.getplayfield().getfield().size(); y++)
		{
			if(this.op.getplayfield().getfield().get(y).colormatch(color) )
			{
				toremove.push(x);
				
				
			}
		}
		while(!toremove.isEmpty())
		{
			this.op.getplayfield().getfield().get(toremove.pop()).disablecard(this.n.getdecay());
		}
		
		
	}
	private void brokenhands()
	{/**
	* remove one card have your oppoeint discard two
	* 
	*
	*/
		
		
	if(this.arrayuse.length != (this.owner.gethand().size()))
	{
		card[] card = new card[this.arrayuse.length];
		card[] ult =  new card[(this.arrayuse.length*2)];
		//System.out.print(ult);
		for(int x =0; x < this.arrayuse.length; x++)
		{
			if(this.arrayuse[x] < this.owner.gethand().size())
			{
			card[x] = this.owner.gethand().get(this.arrayuse[x]);
			}
			else if(!this.owner.gethand().isEmpty())
			{
				int e = ThreadLocalRandom.current().nextInt(0, owner.gethand().size());
				card[x] = this.owner.gethand().get(e);	
			}
			else
			{
				break;
			}
	for(int y =0; y < ult.length; y++)
	{
		
	
			if(this.op.gethand().size() <= y && !this.op.gethand().isEmpty())
			{
				int randomNum = ThreadLocalRandom.current().nextInt(0, op.gethand().size());
				ult[y] = this.op.gethand().get(randomNum);
			}
			else if(op.gethand().isEmpty())
					{
				op.draw(2);
					}
			else
			{
				int randomNum = ThreadLocalRandom.current().nextInt(0, op.gethand().size());
				ult[y] = this.op.gethand().get(randomNum);
				int randomNum1 = ThreadLocalRandom.current().nextInt(0, op.gethand().size());
				ult[y] = this.op.gethand().get(randomNum1);
			}
		}
		}	
		this.owner.spremovefor(card);
		
		
		this.op.spremovefor(ult);
		
	}else
	{
		this.op.gethand().clear();
		this.owner.gethand().clear();
	}
	
		
	}
	public void skipturn()
	{this.op.skipturn();}
	public void darkness()
	{
		// disables all of a player black cards in turn disables all of the victume cards
		for(int x =0; x < this.owner.getplayfield().getfield().size(); x++)
		{
			if(this.owner.getplayfield().getfield().get(x).colormatch(color)&& !this.owner.getplayfield().getfield().get(x).colormatch(color))
			{
				this.owner.getplayfield().getfield().get(x).disablecard(1);
			}
		}
		for(int y =0; y < this.op.getplayfield().getfield().size(); y++)
		{
			
			this.op.getplayfield().getfield().get(y).disablecard(this.n.getdecay());
		}
	}
	
	private void disablecard(int s)
	{
		//uses array use
		if(s == 0)
		{
			
		
		
		
		if(this.y != -1)
		{
			this.owner.getplayfield().getfield().get(this.y).dyingeffect(true);
		
		//System.out.println(this.owner.getplayfield().getfield().get(this.y).getname());
			this.owner.printplayefield();
		
		this.owner.getplayfield().getfield().get(this.y).disablecard(1);
		}
		}
	
	

//this.vic.getplayfield().getfield().get(this.z).usecard(this.arrayuse[0], this.arrayuse[1]);
this.op.getplayfield().getfield().get(this.z).dyingeffect(true);
if(this.op.getplayfield().getfield().get(this.z).cantarget())
{
this.op.getplayfield().getfield().get(this.z).disablecard(1);	
}		
		if(s == 1)
		{ Stack<Integer> stack = new Stack<Integer>();
			for(int x =0; x < this.op.getplayfield().getfield().size(); x++)
			{
				if(this.op.getplayfield().getfield().get(x).handeffect() == 2 && !this.op.getplayfield().getfield().get(x).isdisable())
				{
					stack.push(x);
				}
				}
			if(this.y < stack.size() && this.z < stack.size())
			{
			if(!this.op.getplayfield().getfield().isEmpty() &&this.op.getplayfield().getfield().get(stack.get(this.y))!= null && this.op.getplayfield().getfield().get(stack.get(this.z))!= null )
			{
			this.op.getplayfield().getfield().get(stack.get(this.y)).disablecard(1);
			this.op.getplayfield().getfield().get(stack.get(this.z)).disablecard(1);
			}
			}
			
			}
	}

}
