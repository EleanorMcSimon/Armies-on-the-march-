package specalcardeffect;

import java.util.Stack;

import cards.card;
import player.player;

public class seeClass extends effect {

	public seeClass(card n, player op) {
		super(n, op);
		// TODO Auto-generated constructor stub
	}
	public void playcard()
	{
		switch(x)
		{
		case 0: this.owner.showto(drawdeckcard(this.owner)); break;
		case 1: this.owner.showto(drawdeckcard(this.op)); break;
		case 2: this.owner.showto(handbase()); break;
		case 3: this.owner.showto(unbondknowable()); break;
			
		}
	}
	
	private card[] unbondknowable()
	{	
		/**
		 * exchange cards to see cards 
		 */
		 int y =0;
		 card[] f = new card[this.arrayuse.length];
			for(int x =0; x < this.arrayuse.length; x++)
			{
				
			f[x] =	this.owner.gethand().get(arrayuse[x]);
			}
			this.op.showto(f);
			
			 Stack card = new Stack<card>();
			 for(int x =0; x < f.length*2; x++)
				{
				 if(this.owner.getplayfield().getfield().get(x).colormatch(color)&& y < this.op.gethand().size() )
					{
						card.push(this.op.gethand().get(y));
						y++;
					}
				}
				return (cards.card[]) card.toArray();
		}

	public card[] handbase()
	{	
		/** 
		 * show the op hand
		 */
		 int y =0;
		 Stack card = new Stack<card>();
			for(int x =0; x < this.owner.getplayfield().getfield().size(); x++)
			{
				if(this.owner.getplayfield().getfield().get(x).colormatch(color)&& y < this.op.gethand().size() )
				{
					card.push(this.op.gethand().get(y));
					y++;
				}
				
			}
			return (cards.card[]) card.toArray();
		}

		
	
	private card[] drawdeckcard(player n)
	{
		/** 
		 * looks at your own drawdeck or ops
		 */
	 int y =0;
	 Stack card = new Stack<card>();
	 for(int x =0; x < this.owner.getplayfield().getfield().size(); x++)
		{
		 if(this.owner.getplayfield().getfield().get(x).colormatch(color) &&  y <n.getdrawdeck().size() )
			{
			 y++;
			}
		}
		
			
			card[] car =new card[y];
			for(int z =0; z < car.length; z++)
			{
				car[z] = n.getdrawdeck().get(z);
			}
				
			
		
		
		return car;
	}

}
