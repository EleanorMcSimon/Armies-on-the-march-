package specalcardeffect;

import java.util.Stack;



import cards.card;
import player.player;

public class scoring extends effect {

	
	int deflaut= 1;
	int on = op.getscore();
	int value =1;
	

	public scoring(card n, player op) {
		super(n, op);
		this.deflaut = n.getscore();
		this.value = n.getscore();
		// TODO Auto-generated constructor stub
	}
	public void active()
	{
		switch(this.x)
		{
		case 0:{
			scoreingbyType(false);
			addpoints();
			break;
		}
		case 1:{
			scoreingbyType(true);
			addpoints();
			break;
		}
		case 2:{
			 parsite();
			 addpoints();
			 break;
		}
		case 3:{
			exsivecard();
			addpoints();
			break;
		}
		case 4:{
			addpoints();
			break;
		}
		case 5: {
			changescoring(this.z);
			break;
		}
		
		}
		}
	
	private void exsivecard()
	{
		/** 
		 * for card that must 
		 */
		for(int x =0; x < this.owner.getplayfield().getfield().size(); x++)
		{
			if(this.owner.getplayfield().getfield().get(x).handeffect() == this.cardtype && this.color.equalsIgnoreCase(this.owner.getplayfield().getfield().get(x).getcolor() ))
			{
				this.n.disablecard(1);
				break;
			}
		}
	}
	public void changedeflaut(int f)
	{
		deflaut = deflaut+f;
	}
	public void roundreset() {value = deflaut;}
	private void parsite()
	{
	/** 
	 * take from the ops score
	 */
	 double f = (this.op.getscore() - on)/2;
	 value += f;
	}
	private void addpoints()
	{
		/** 
		 * add to score
		 */
	if(!this.n.isdisable())
	{
		if(value ==0 )
		{
			this.value = 1;
		}
		this.owner.addtoscore(value);
	}
	}
	private void changescoring(int f)
	{/**
	add number to the value
	*/
		value = value +f;
	}
	private void scoreingbyType(boolean ui)
	{
		/**
		 * scores by type contains the kill card
		 */
		Stack<Integer> cardChose = new Stack<Integer>();
	
		
		for(int y=0; y < this.arrayuse.length; y++)
		{
			//boolean valid = false;
			cardChose.push(this.arrayuse[y]);
			
		}
		if(ui)
		{
			this.changescoring((cardChose.size()/2));
		}
		else
		{
			
		card[] ed = new card[cardChose.size()];
		if(ed.length > 1 && !cardChose.isEmpty() &&  !this.owner.getplayfield().getfield().isEmpty())
		{
		for(int u=0; u < ed.length; u++)
		{if( this.owner.getplayfield().getfield().size() > cardChose.peek())
		{
			ed[u] = this.owner.getplayfield().getfield().get(cardChose.pop());
			
			this.owner.getplayfield().getfield().remove(ed[u]);
		}
		
		}
		this.changescoring(ed.length - cardChose.size());
		}
		}
		}
	
	

}
