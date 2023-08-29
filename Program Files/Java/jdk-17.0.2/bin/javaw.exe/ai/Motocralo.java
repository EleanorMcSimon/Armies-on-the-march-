package ai;

import java.util.ArrayList;
import java.util.Stack;

import cards.card;
import player.player;
import player.playfleld;

public class Motocralo {
player user;
player op;
int stace =0;
Stack<card> cards =  new Stack<card>();
Stack<card> targets =  new Stack<card>();

	public Motocralo(player n, player op)
	{
		user = n;
		this.op = op;
	}
	private void chosestace()
	{
		/**
		 * chose the way the ai wants to play the game
		 */
		int r=user.getscore()-this.op.getscore();
		if(r< -3)
		{
			this.stace=0;
		}
		else if(r >5 )
		{
			this.stace = 2;
		}
		else 
		{ 
			if( r > 10)
			{
				this.stace = 1;
			}else
			{
				this.stace = 0;	
			}
			
			}
		
		}
	
	private int getkillcards(player n)
	{
		/**
		 * gets cards to kill
		 */
		if(this.op.getplayfield().getfield().isEmpty())
		{
			return 0;
		}
		card array = new card("Draw","black","none",5,0,1,0);
		card other = new card("Draw","black","none",6,0,1,0);
		int kill =0;
		for(int x =0; x < n.getplayfield().getfield().size(); x++)
		{
			if(n.getplayfield().getfield().get(x).handeffect() == 4)
			{
				kill++;
			}
			else if(n.getplayfield().getfield().get(x).effectmatch(other) ||n.getplayfield().getfield().get(x).effectmatch(array) )
			{
				kill++;
			}
		}
		return kill;
	}
	public void valuechecker(player n, int ammount)
	{
		/**
		 * check the values of the hand
		 */
		Stack<Integer> input =  new Stack<Integer>();
	for(int x =0; x < n.gethand().size(); x++)
	{
		switch(stace)
		{
		case 0:
		{
			if(n.gethand().get(x).handeffect() == 2 || n.gethand().get(x).handeffect() == 6)
			{
				
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+3);
			}
			else if(n.gethand().get(x).handeffect() == 3)
			{
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+2);
			}
			else if(n.gethand().get(x).handeffect() == 0|| n.gethand().get(x).handeffect() == 5)
			{
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+1);
			}
			break;
		}
		case 1:
		{
			if(n.gethand().get(x).handeffect() == 0)
			{
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+3);
			}
			else if(n.gethand().get(x).handeffect() == 1 ||  n.gethand().get(x).handeffect() == 4)
			{
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+2);
			}
			else if(n.gethand().get(x).handeffect() == 2 )
			{
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+1);
			}
	break;		
		}
		case 2:
		{
			if(n.gethand().get(x).handeffect() == 5)
			{
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+3);
			}
			else if(n.gethand().get(x).handeffect() == 1 ||  n.gethand().get(x).handeffect() == 4)
			{
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+2);
			}
			else if(n.gethand().get(x).handeffect() == 0|| n.gethand().get(x).handeffect() == 5)
			{
				n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+1);
			}
			break;
		}
		
		
		}
		input.push(n.gethand().get(x).getscore());
		cards.push(n.gethand().get(x));
	}
	Stack<Integer> stacking = sortlw( input,0);
	stacking.clear();
}
	public void playcards(player n, int ammount)
	{	chosestace();
		int b =0;
		ammount= ammount*2;
		Stack<Integer> input =  new Stack<Integer>();
		for(int x =0; x < n.gethand().size(); x++)
		{
			switch(stace)
			{
			case 0:
			{
				if(n.gethand().get(x).handeffect() == 2 || n.gethand().get(x).handeffect() == 6)
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+3);
				}
				else if(n.gethand().get(x).handeffect() == 3)
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+2);
				}
				else if(n.gethand().get(x).handeffect() == 0|| n.gethand().get(x).handeffect() == 5)
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+1);
				}
				break;
			}
			case 1:
			{
				if(n.gethand().get(x).handeffect() == 0)
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+3);
				}
				else if(n.gethand().get(x).handeffect() == 1 ||  n.gethand().get(x).handeffect() == 4)
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+2);
				}
				else if(n.gethand().get(x).handeffect() == 2 )
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+1);
				}
		break;		
			}
			case 2:
			{
				if(n.gethand().get(x).handeffect() == 5)
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+3);
				}
				else if(n.gethand().get(x).handeffect() == 1 ||  n.gethand().get(x).handeffect() == 4)
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+2);
				}
				else if(n.gethand().get(x).handeffect() == 0|| n.gethand().get(x).handeffect() == 5)
				{
					n.gethand().get(x).setvalue(n.gethand().get(x).getscore()+1);
				}
				break;
			}
			
			
			}
			input.push(n.gethand().get(x).getscore());
			cards.push(n.gethand().get(x));
		}
		if(ammount> 0)
		{
		card[] out = new card[ammount];
			Stack<Integer> stacking = sort( input,0);
			while (stacking.size() > ammount)
			{
				stacking.pop();
				cards.pop();
		}
			int w=0;
			stacking.clear();
			while(!cards.isEmpty())
			{
				out[w] = cards.pop();
				w++;
			}
			cards.clear();
			
		this.user.removefor(out);
		}
	}
	
	public void alliedvalue(player n, int amount)
	{
		Stack<Integer> input =  new Stack<Integer>();
		
		
		for(int x =0; x < n.getplayfield().getfield().size(); x++)
		{
			switch(n.getplayfield().getfield().get(x).handeffect())
			{
			case 0: n.getplayfield().getfield().get(x).setvalue(1+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()- n.getplayfield().getfield().get(x).getdecay()); break;
			case 1: n.getplayfield().getfield().get(x).setvalue(3+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()- n.getplayfield().getfield().get(x).getdecay()); break;
			case 2: n.getplayfield().getfield().get(x).setvalue(1+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()+n.getplayfield().getfield().get(x).getscore()- n.getplayfield().getfield().get(x).getdecay()); break;
			case 3: n.getplayfield().getfield().get(x).setvalue(4+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()- n.getplayfield().getfield().get(x).getdecay()); break;
			case 4: n.getplayfield().getfield().get(x).setvalue(3+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()- n.getplayfield().getfield().get(x).getdecay()); break;
			case 5: n.getplayfield().getfield().get(x).setvalue(0+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()- n.getplayfield().getfield().get(x).getdecay()); break;
			case 6: n.getplayfield().getfield().get(x).setvalue(1+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()- n.getplayfield().getfield().get(x).getdecay()); break;
			case 7: n.getplayfield().getfield().get(x).setvalue(6+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()- n.getplayfield().getfield().get(x).getdecay()); break;
			}
			input.push(n.getplayfield().getfield().get(x).getscore());
			cards.push(n.getplayfield().getfield().get(x));
		}
		sort( input,0);	
	}
	public void checkopinetvalue(player n, int amount)
	{Stack<Integer> input =  new Stack<Integer>();
	
	if(!n.getplayfield().getfield().isEmpty()) {
		for(int x =0; x < n.getplayfield().getfield().size(); x++)
		{
			switch(n.getplayfield().getfield().get(x).handeffect())
			{
			case 0: n.getplayfield().getfield().get(x).setvalue(1+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()); break;
			case 1: n.getplayfield().getfield().get(x).setvalue(3+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()); break;
			case 2: n.getplayfield().getfield().get(x).setvalue(1+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()+n.getplayfield().getfield().get(x).getscore()); break;
			case 3: n.getplayfield().getfield().get(x).setvalue(4+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()); break;
			case 4: n.getplayfield().getfield().get(x).setvalue(3+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()); break;
			case 5: n.getplayfield().getfield().get(x).setvalue(0+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()); break;
			case 6: n.getplayfield().getfield().get(x).setvalue(1+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()); break;
			case 7: n.getplayfield().getfield().get(x).setvalue(6+(n.getplayfield().getfield().get(x).getdecay())*n.getplayfield().getfield().get(x).raility()); break;
			}
			input.push(n.getplayfield().getfield().get(x).getscore());
			targets.push(n.getplayfield().getfield().get(x));
		}
	if(!input.isEmpty() && !targets.isEmpty())
	{
		sort( input,1);
	}
		
		for(int y =0; y < amount; y++)
		{
			if(!targets.isEmpty())
			{
			targets.pop();
			}
		}
	}
	}
	private void targets(int y)
	{
		checkopinetvalue(op,y);
	}
	private boolean specialcard(int x, playfleld y)
	{
	card array = new card("Draw","black","none",7,0,1,0);
	card n = new card("Tree","green","none",1,0,2,0);
	card oi = new card("blood","black","none",0,0,2,0);
	card f = new card("change","green","none",0,0,3,0);
	
	card w = new card("lamb","black","none",9,2,3,0);
	
		if(y.getfield().get(x).effectmatch(w)|| y.getfield().get(x).effectmatch(oi)||y.getfield().get(x).effectmatch(n)||y.getfield().get(x).effectmatch(array) ||y.getfield().get(x).effectmatch(f))
		{
			return true;
		}
		return false;
	}
	public Stack<int[]> moveone(int i)
	{Stack<int[]> move = new Stack<int[]>();
		if(i==0)
		{
		Stack<card> f = new Stack<card>(); 
		
		int w = this.user.gethandvalue()/this.user.gethand().size();
		while(this.cards.peek().getvalue() <  w)
		{
			f.push(this.cards.pop());
		}
	int[] ew = new int[f.size()];
	for(int s= 0; s < ew.length;s++)
	{
		ew[s] = op.gethand().indexOf(f.pop());
	}
	move.push(ew);
		}
		else if(i==1)
		{
			Stack<Integer> f = new Stack<Integer>(); 
			for(int x=0; x < this.user.getplayfield().getfield().size(); x++)
			{
				if(this.user.getplayfield().getfield().get(x).colormatch("Green"))
				{
					f.push(x);
				}
			}
			int[] temp = new int[f.size()];
			for(int z=0; z < f.size(); z++)
			{
				temp[z] = f.pop();
			}
			move.push(temp);
		}
		else if(i == 2)
		{
			Stack<Integer> f = new Stack<Integer>(); 
	
			int w = this.user.getplayfield().addvalue()/this.user.getplayfield().getfield().size();
			
		for(int y = 0; y<this.user.getplayfield().getfield().size();y++)
		{
			if(this.user.getplayfield().getfield().get(y).colormatch("black") && this.user.getplayfield().getfield().get(y).getvalue() < w)
			{
			f.push(y);
			}
		}
		int[] temp = new int[f.size()];
		for(int z=0; z < f.size(); z++)
		{
			temp[z] = f.pop();
		}
		move.push(temp);
		}
	return move;
	}
	public Stack<int[]>  calucateaboutTouse(playfleld n,int x)
	{
		card array = new card("Draw","black","none",7,0,1,0);
		card w = new card("Tree","green","none",1,0,2,0);
		card f = new card("change","green","none",0,0,3,0);
		if(this.specialcard(x, n))
		{
			if(n.getfield().get(x).effectmatch(array))
			{
				this.valuechecker(this.user, this.user.gethand().size());
				return this.moveone(0);
			}
			else if(n.getfield().get(x).effectmatch(w))
			{
				this.alliedvalue(this.user,this.user.getplayfield().getfield().size());
				return this.moveone(1);
			}
			else if(n.getfield().get(x).effectmatch(f))
			{
				this.alliedvalue(this.user,this.user.getplayfield().getfield().size());
				return this.moveone(2);
			}
{
	
}

		}
		
		return null;
	}
	public int[] checkvalue(playfleld n,int x )
	{
		/**
		 * check value for minmax
		 */
		Stack<int[]> move = new Stack<int[]>();
		int[] eo = {0,0,0};
		move.push(eo);
		playfleld r = this.user.getplayfield();
	player p = this.user;
	r.disable();
	p.getplayfield().disable();
	if(n.getfield().size() < x || n.getfield().isEmpty())
		
		{
		return null;
		}
		
int value =	this.user.getscore(); 
if(n.getfield().get(x).handeffect() ==0)
	
{for(int y =0; y < n.getfield().size(); y++)
{
	for(int z= 0; z < cards.size(); z++)
	{
		if(x < r.getfield().size())
		{
		r.getfield().get(x).usecard(n.getfield().indexOf(n.getfield().get(y)), n.getfield().indexOf(cards.get(z)));
		if(user.getscore()>= value)
		{int[] check = move.peek();
			if(check[2] >= r.addvalue())
			{
				if(n.getfield().size() > y && n.getfield().size() > z)
				{
				int[] inside = {n.getfield().indexOf(n.getfield().get(y)), n.getfield().indexOf(cards.get(z)), this.user.getscore() };
				move.push(inside);
				}
			}
		
		}
		r.equals(this.user.getplayfield());
		r.disable();
}
}
}
}
else if(n.getfield().size() > x)
{
		if(n.getfield().get(x).handeffect()== 4)
		{
			
			
			if(!this.targets.isEmpty() && !this.cards.isEmpty() )
			{
			if(this.cards.peek().getvalue() <= this.targets.peek().getvalue() )
			{
				int[] e = {this.user.getplayfield().getfield().indexOf(cards.pop()), this.op.getplayfield().getfield().indexOf(targets.peek())};
			move.push(e);	
				
				
			}
			}
			
		}
		else {
			for(int y1 =0; y1 < n.getfield().size(); y1++)
			{
				for(int z= 0; z < cards.size(); z++)
				{
					r.equals(this.user.getplayfield());
					p.equals(user);
					if(x < p.getplayfield().getfield().size())
					{
					p.getplayfield().getfield().get(x).usecard(n.getfield().indexOf(n.getfield().get(y1)), n.getfield().indexOf(cards.get(z)));
					if(!r.getfield().isEmpty())
					{
					r.getfield().get(x).usecard(n.getfield().indexOf(n.getfield().get(y1)), n.getfield().indexOf(cards.get(z)));
					}
					int[] check = move.peek();
					
						if(check[2] >= r.addvalue() || p.getscore() > this.user.getscore())
						{
						if(!n.getfield().isEmpty())
						{
							int[] inside = {n.getfield().indexOf(r.getfield().get(y1)), n.getfield().indexOf(r.getfield().get(z)), n.addvalue()};
							move.push(inside);
						}
					
					}
					
					r.equals(this.user.getplayfield());
					p.equals(this.user);
					p.getplayfield().disable();
					r.disable();
					}
				}
	
	
			}
		}}
		


return move.peek();
		
		
	
	}
	public int get(int x, ArrayList<card> c)
	{
	int o= 0;
	for(int y=0; y< c.size(); y++)
	{
		if(c.get(y).handeffect() == x)
		{
		o++;	
		}
	}
		return o;
	}
private int[] cardsThatcanbeuse() 
{
	/**
	 * finds card to use
	 */
	Stack<Integer> f = new Stack<Integer>();
	for(int x = 0; x < this.user.getplayfield().getfield().size(); x++)
	{
		/*if(!this.user.getplayfield().getfield().get(x).isdisable() )
		{*/
			f.push(x);
		//}
	}
	int[] re = new int[f.size()];
	for(int y=0; y < re.length; y++)
	{
		re[y]= f.pop();
	}
	return re;
}
	public void minmax()
	{
		/** calucate moves
	*/
		int user = this.user.getscore();
		Stack<int[]> p = new Stack();
		//playfleld n =this.user.getplayfield();
		int b=getkillcards(this.user);
		Stack<int[]>  a = new Stack<int[]>();
		targets(get(b, this.user.getplayfield().getfield()));
		 int[] play =cardsThatcanbeuse();
		for(int x= 0; x < play.length; x++)
		{
			  
			if(play[x] > this.user.getplayfield().getfield().size())
			{
				break;
			}
			
			int[] e  = {play[x]};
			if(play[x] < this.user.getplayfield().getfield().size())
			{
				p	=calucateaboutTouse(this.user.getplayfield(), play[x]);
				if(p == null)
				{
					p = new Stack();
				}
				if(this.user.getplayfield().getfield().size() > play[x])
				{
		  a.push(checkvalue(this.user.getplayfield(), e[0]));
				
			if(!a.isEmpty() )
			{
				int[] get = a.peek();
				if(get.length >2)
				{
				System.out.print(get[0]+get[1]+get[2]);
				}
			}
				}
			}
			
			
			
		}

		this.user.getplayfield().activecards(play,a,p);

		
	}
	private Stack<Integer> sortlw(Stack<Integer> input, int w)
	{
		/**
		 * lowest to highest sort
		 */
		   Stack<Integer> tmpStack = new Stack<Integer>();
		   Stack<card> card = new Stack<card>();
	        while(!input.isEmpty())
	        {
	            // pop out the first element
	            int tmp = input.pop();
	         
	            
	            while(!tmpStack.isEmpty() && tmpStack.peek()
	                                                 > tmp)
	            {
	                // pop from temporary stack and
	                // push it to the input stack
	            input.push(tmpStack.pop());
	            if(w ==0)
	            {
	            cards.push(card.pop());
	            }else if(w==1)
	            {
	            	this.targets.push(cards.pop());
	            }
	            
	            }
	             
	            // push temp in temporary of stack
	            if(w==0)
	            {
	            card.push(cards.pop());
	            }
	            else if(w==1)
	            {
	            	card.push(this.targets.pop());
	            }
	            tmpStack.push(tmp);
	        }
	        if(w== 0)
	        {
	        	cards.clear();
		        cards.addAll(card);
	        }
	        else if(w ==1)
	        {
	        	targets.clear();
	        	targets.addAll(card);
	        }
	        
	        return tmpStack;
	    }
	private Stack<Integer> sort(Stack<Integer> input, int w)
	{
		/**
		 * highest to lowest sort
		 */
		   Stack<Integer> tmpStack = new Stack<Integer>();
		   Stack<card> card = new Stack<card>();
	        while(!input.isEmpty())
	        {
	            // pop out the first element
	            int tmp = input.pop();
	         
	            
	            while(!tmpStack.isEmpty() && tmpStack.peek()
	                                                 < tmp)
	            {
	                // pop from temporary stack and
	                // push it to the input stack
	            input.push(tmpStack.pop());
	            if(w ==0)
	            {
	            cards.push(card.pop());
	            }else if(w==1&& !this.cards.isEmpty())
	            {
	            	this.targets.push(cards.pop());
	            }
	            
	            }
	             
	            // push temp in temporary of stack
	            if(w==0)
	            {
	            card.push(cards.pop());
	            }
	            else if(w==1)
	            {
	            	if(!this.targets.isEmpty())
	            	{
	            	card.push(this.targets.pop());
	            	}
	            }
	            tmpStack.push(tmp);
	        }
	        if(w== 0)
	        {
	        	cards.clear();
		        cards.addAll(card);
	        }
	        else if(w ==1)
	        {
	        	targets.clear();
	        	targets.addAll(card);
	        }
	        
	        return tmpStack;
	    }
	
	/*public int chosestace(player n)
	{
		
	
	}*/
	public int cardeffect(player n)
	{
		int amount =0;
		for(int x =0; x < n.getplayfield().getfield().size(); x++)
		{
			if(n.getplayfield().getfield().get(x).handeffect() ==0)
			{
				amount++;
			}
			else if(n.getplayfield().getfield().get(x).handeffect() ==4 && n.getplayfield().getfield().get(x).getid() == 0 ||n.getplayfield().getfield().get(x).handeffect() ==4 && n.getplayfield().getfield().get(x).getid() == 3||n.getplayfield().getfield().get(x).handeffect() ==4 && n.getplayfield().getfield().get(x).getid() == 4) {
				amount++;
			}
			else if(n.getplayfield().getfield().get(x).handeffect() ==2 && n.getplayfield().getfield().get(x).getid() == 5 ||   n.getplayfield().getfield().get(x).handeffect() ==2 && n.getplayfield().getfield().get(x).getid() == 7)
			{
				amount++;	
			}
		}
		return amount;
		
	}
	public void whatcardstoplay()
	{
		int Egpendcards =0;
		int inhand = user.gethand().size()+this.op.getplayfield().getfield().size();
		double e = ((this.user.getscore())-this.op.getscore());
	
		if(e < 0)
		{
			
			e=(e*(1+Math.pow(e, Math.abs(inhand))));
			
		}
		boolean gapbetween = true;
		int opdrawcards = cardeffect(op);
		int useropcard = cardeffect(user);
		for(int x =0; x < user.getplayfield().getfield().size(); x++)
		{
			if(user.getplayfield().getfield().get(x).getdecay() == 1)
			{
				Egpendcards++;
			}
			else if(user.getplayfield().getfield().get(x).getdecay() == 2)
			{
				gapbetween = false;	
			}
		}int amount= 0;
		if(inhand != 0)
		{
			int cast = (int) e;
	 amount = (int) (((Egpendcards^3*inhand^4)-(opdrawcards^2-useropcard))*e)^2;
	
		}	
	if(amount <1)
	{
		amount=amount*-1;
	}
	if(amount > 0)
	{
	playcards(this.user, amount);	
	}	
	}
}
