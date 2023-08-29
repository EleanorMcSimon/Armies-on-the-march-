package specalcardeffect;

import java.util.ArrayList;

import cards.card;
import player.player;

public class draw extends  effect{

	/**
	 * @author Eleanor Simon
	 * @param n
	 * @param op
	 * @param effecthappens
	 */
	boolean effecthappens = false; // does it go off
	public draw(card n, player op) {
		super(n, op);
		
		
	}

	

	
	
	
	public void called()
	{
		/** case for card effects given a number 
		 * the card uses a different function
		 *@param y
		 *@param z
		 * */
		switch(this.x)
		{
		case 0: stealfromhand(this.op, this.owner, this.y); break;
		case 1: swapcard(this.op,this.owner, this.z, this.y); break;
		case 2:{
			System.out.println("here");
			disacardhand((this.owner.gethand().size()/2), this.owner );
			this.op.gethand().clear();
			break;
			}
		case 3: this.op.gethand().clear(); break;
		case 4: unplay(this.y); break;
		case 5: colordraw(4, this.color, this.owner, this.owner); break;
		case 6:  colordraw(1, this.color, this.owner, this.op); break;
		case 7: swaphands(this.owner, this.op); break;
		case 8: OverDrive(1); break;
		case 9: OverDrive(3); break; // replnishing area 
		case 10: changeinfate(); break;
		}
	}
	private void unplay(int y)
	{
		System.out.println("unplay");
		// returns card from the playfield to the hand
		
	if(!this.owner.getplayfield().getfield().isEmpty()&& this.owner.getplayfield().getfield().size()> y)
	{
		if(!this.owner.getdrawdeck().isEmpty())
		{
		this.owner.getdrawdeck().pop();
		}
		this.owner.gethand().add(this.owner.getplayfield().getfield().get(y));
		this.owner.getplayfield().getfield().remove(y);
	}
	else
	{
		//throw err make erro class
	}
	}
	
	private void swapcard(player vic,player placer, int w, int f)
	{
		if(!placer.gethand().isEmpty() && !vic.gethand().isEmpty())
		{
			vic.gethand().get(w).makeeffic(placer, vic);
			placer.gethand().get(f).makeeffic(vic, placer);
		placer.gethand().add(vic.gethand().get(w));
		vic.gethand().add(placer.gethand().get(f));
		vic.gethand().remove(w);
		placer.gethand().remove(f);
		effecthappens= true;
		}
		
	}
	private void changeinfate()
	{
		if(this.owner.getdrawdeck().size() > 1 && !this.owner.getdrawdeck().isEmpty() )
		{
		this.owner.setdrawfrom(this.owner.getdrawdeck().size()-1);
		}
	}
	private void stealfromhand(player vic,player placer,int f)
	{
		// takes a card from someone else hand and plays it
		if(vic.gethand().size() >f )
		{
		vic.gethand().get(f).makeeffic(placer, vic);
 placer.getplayfield().getfield().add(vic.gethand().get(f));
 vic.gethand().remove(f);
		}
	}
	private void colordraw(int amount, String color, player p, player t)
	{
		/**
		 * get color card from a the other player
		 *  or your own draw deck
		 */
		int instance =0;
		for(int x =0; x< p.getdrawdeck().size(); x++)
		{
			if(p.getdrawdeck().get(x).colormatch(color))
			{
				p.gethand().add(p.getdrawdeck().get(x));
				p.getdrawdeck().remove(x);
				instance++;	
			}
			if(instance > amount)
			{
				break;
			}
		}
	}
	private void swaphands(player one, player two)
	{
		//swaps players hands
		ArrayList<card>  A =	one.gethand();
		ArrayList<card>  B = two.gethand();
		for(int x =0; x < B.size(); x++)
		{
			B.get(x).makeeffic(one, two);
		}
		for(int y =0; y < A.size(); y++)
		{
			A.get(y).makeeffic(two, one);
		}
		one.gethand().clear();
		one.gethand().addAll(B); // add everthing from the other player hand
		two.gethand().clear();
		two.gethand().addAll(A);
	}
	private void disacardhand(int amount, player p)
	{
		/* disacards part of a player hand for benfits or
		 *  neff to other playe
		*/
		if(!p.gethand().isEmpty())
		{
		effecthappens= true;
		}
		for(int x= 0; x < amount; x++)
		{
			if(p.gethand().isEmpty())
			{
				break;
			}
			p.gethand().remove(0);
		}
	}
	
	private void OverDrive(int alt)
	{
		int goldcount =0;
		for(int x=0; x< this.owner.getplayfield().getfield().size(); x++)
		{
			if(this.owner.getplayfield().getfield().get(x).colormatch(color) )
{
				goldcount++;
}
		}
		this.owner.draw(goldcount/alt);
	}
	
	
}
