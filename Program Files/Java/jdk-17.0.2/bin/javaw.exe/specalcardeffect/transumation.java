package specalcardeffect;

import cards.card;
import player.player;

public class transumation extends effect {

	public void playcard()
	{
		
		switch(x)
		{
		case 0:
			{
				card lamb = new card("lamb","black","none",8,2,3,0);
				lamb.makeeffic(owner, op);
				colorbased(lamb, true);
				break;
			}
		case 1:
		{
			
			card Snyth = new card("nyfth","green","none",7,3,4,0);
			Snyth.makeeffic(owner, op);
			playfield(Snyth, false);
			break;
		}
		case 2:
		{
			card lamb = new card("lamb","black","none",8,2,3,0);

			lamb.makeeffic(owner, op);
			change(lamb);
			break;
			
		}
		case 3:
		{
			card par = new card("parsite","gold","none",2,4,2,0);
			par.makeeffic(owner, op);
			playfield(par,false);
			break;
		}
		case 4:
		{ 
			card gold = new card("mine","gold","none",4,3,2,0);	
			gold.makeeffic(owner, op);
			timedecay(gold);
			break;
			
		}
		case 5:
		{
			card lamb = new card("lamb","black","none",8,2,3,0);
			lamb.makeeffic(owner, op);
			rit(lamb, true);
			break;
		}
		case 6:
		{ 
			card gold = new card("mine","gold","none",4,3,2,0);	
			gold.makeeffic(owner, op);
			 rit(gold, false);
			break;
			
		}
		case 7:
		{
			card gold = new card("mine","gold","none",4,3,2,0);	
			gold.makeeffic(owner, op);
			playfield(gold, true);
			break;
		}
		
		}
		
	}
	public transumation(card n, player op) {
		super(n, op);
		// TODO Auto-generated constructor stub
	}
	private void playfield(card turn, boolean f)
	{
		/** 
		 * turns a card on a playe to an other card
		 */
		if(!this.owner.getplayfield().getfield().isEmpty())
		{
			
		
		if(this.owner.getplayfield().getfield().get(y).colormatch(color))
		{
			
			
			this.owner.getplayfield().getfield().remove(y);
		
			this.owner.getplayfield().getfield().add(turn);
			if(f)
			{
				this.owner.getplayfield().getfield().remove(z);
			}
		}
		}
	}
	private void colorbased(card turn, boolean f)
	{
		/**
		 * color based tranumation from the hand
		 * 
		 */
		if(this.owner.gethand().get(y).colormatch(color) || f)
		{
			
			
			this.owner.gethand().remove(y);
			this.owner.getplayfield().getfield().add(turn);
		}
	}

	private void change(card turn)
{
	/**
	 * color based tranumation from field
	 * 
	 */
	if(this.op.getplayfield().getfield().get(y).colormatch(color))
	{
		card f = this.op.getplayfield().getfield().get(y);
		this.op.getplayfield().getfield().remove(f);
		this.owner.getplayfield().getfield().remove(this);
		this.owner.getplayfield().getfield().add(turn);
	}
}
	private void rit(card turn, boolean d)
	{ 
		/** 
		 * takes two cards from the give the player a better card
		 * 
		 */
		if(this.n.getdecay() == 0)
		{
			if(d)
			{
				card n = this.owner.getplayfield().getfield().get(y);
				card e = this.owner.getplayfield().getfield().get(z);
				this.owner.getplayfield().getfield().add(turn);
				this.owner.getplayfield().getfield().add(turn);
				this.owner.getplayfield().getfield().remove(n);
				this.owner.getplayfield().getfield().remove(e);
				this.owner.getplayfield().getfield().remove(this);
			
			}
			else
			{
				card n = this.owner.getplayfield().getfield().get(y);
				card e = this.owner.getplayfield().getfield().get(z);
				if(n.colormatch(color)&& e.colormatch(color))
				{
					this.owner.getplayfield().getfield().add(turn);
					this.owner.getplayfield().getfield().remove(n);
					this.owner.getplayfield().getfield().remove(e);
					this.owner.getplayfield().getfield().remove(this);
				}
				
			}
		}
		this.owner.getplayfield().getfield().get(y).disablecard(1);
		this.owner.getplayfield().getfield().get(z).disablecard(1);
		
	}
	private void timedecay(card turn)
	{
		/**
		 * constuction based one
		 */
		if(this.n.getdecay() == 0)
		{
			
			this.owner.getplayfield().getfield().remove(this);
			this.owner.getplayfield().getfield().add(turn);
			this.owner.getplayfield().getfield().add(turn);
		}
		else
		{
		this.n.disablecard(1);
		}
	}

}
