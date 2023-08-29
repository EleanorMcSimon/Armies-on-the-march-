package specalcardeffect;

import java.util.ArrayList;
import java.util.Stack;

import cards.card;
import player.player;

public class defestive extends effect{
private int py;
private int pz;
private card def;
private ArrayList<card> play = new ArrayList<card>();
	public defestive(card n, player op) {
		super(n, op);
		// TODO Auto-generated constructor stub
	}
	public void playCard()
	{
		switch(this.x)
		{
		case 0:scafnyth(); break;
		case 1: thorns(); break;
		case 2: protect(); break;
		case 3: forestunion(); break;
		}
	}
	public void primecard()
	{
		 this.py = this.y;
		 this.pz = this.z;
		 this.def = this.owner.getplayfield().getfield().get(py);
		 if(this.x == 3)
		 {
			 playfieldset();
		 }
		
	}
	public void playfieldset()
	{
		for(int x =0; x < this.owner.getplayfield().getfield().size(); x++)
		{
			play.add(this.owner.getplayfield().getfield().get(x));
		}
				
				
	}
	private void protect()
	{
		/** 
		 * protects a card from opponent attacks
		 */
		if(this.owner.getplayfield().getfield().indexOf(def) != -1)
				{
		this.owner.getplayfield().getfield().get(this.owner.getplayfield().getfield().indexOf(def)).cannotbetarget();
		this.owner.getplayfield().getfield().get(this.owner.getplayfield().getfield().indexOf(def)).setdecay(this.owner.getplayfield().getfield().get(this.owner.getplayfield().getfield().indexOf(def)).getdecay()+this.n.getdecay());
		this.owner.getplayfield().getfield().get(this.owner.getplayfield().getfield().indexOf(def)).disablecard(this.n.getdecay());;
		this.owner.getplayfield().getfield().remove(this);
				}
	}
	private void forestunion()
	{
		/**
		 * removes bad cards a replace them with better ones
		 */
		Stack<card> removeme= new Stack<card>();
		Stack<card> c= new Stack<card>();
		int size =this.owner.getplayfield().getfield().size();
		for(int y=0; y <size; y++)
		{
		for(int x =0; x< this.play.size(); x++)
		{
			if( y >= this.owner.getplayfield().getfield().size())
			{
				break;
			}
		if(this.owner.getplayfield().getfield().get(y).effectmatch(this.play.get(x)))
		{
			
		
			if(this.play.get(x).getdecay() > this.owner.getplayfield().getfield().get(y).getdecay())
			{
			
			c.push(this.play.get(x));
			removeme.push(owner.getplayfield().getfield().get(y));
			break;
			
		
		}
		}
		}
		}
		while(!removeme.isEmpty())
		{
			this.owner.getplayfield().getfield().remove(removeme.pop());
		}
		while(!c.isEmpty())
		{
			this.owner.getplayfield().getfield().add(c.pop());
		}
		
	}

	private void scafnyth()
	{
		/** 
		 *kills card in excange for a other one to live
		 *
		 */
		if(this.owner.getplayfield().getfield().indexOf(def) == -1)
		{
			this.owner.getplayfield().getfield().add(def);
			this.owner.getplayfield().getfield().remove(this);	
		}
	}
	private void thorns()
	{
		if(this.owner.getplayfield().getfield().indexOf(def) == -1)
		{
		for(int x=0;x < this.op.getplayfield().getfield().size(); x++)
		{
			System.out.print(this.op.getplayfield().getfield().get(x).handeffect());
			if(this.op.getplayfield().getfield().get(x).handeffect() == 4 || this.op.getplayfield().getfield().get(x).getid() == 0)
			{
				card w = this.op.getplayfield().getfield().get(x);
				this.op.getplayfield().getfield().remove(w);
				this.owner.getplayfield().getfield().remove(this);
				break;
			}
		}
		
		}
		
	}

}
