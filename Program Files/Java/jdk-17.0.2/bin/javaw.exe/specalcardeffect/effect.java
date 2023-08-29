package specalcardeffect;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import cards.card;
import player.player;

public class effect {
	
	int cardtype;
	int x = 0;
	int y;
	int z; 
	int score = 1;
	int[] arrayuse = new int[10];
	player owner;
	card n;
	player op;
	effect[] hidden = new effect[3];
	String color;
	buff buffing; 
	defestive de;
public void alotofcard(int[] w)
{ this.arrayuse =w;
}


synchronized public void changeuse(int y, int z)
	{
		this.y = y;
	
		this.z = z;
	}
synchronized public void usecard(int y,int z)
{
	switch(this.cardtype)
	{
	case 0:{
	draw d = new draw(n,this.op);
	d.y = y;
	d.z = z;
	d.arrayuse = this.arrayuse;
	d.called();

	break;
	}
	case 1: 
	{
	neff bad = new neff(n, this.op);
	bad.z = z;
	bad.y = y;
	bad.arrayuse = this.arrayuse;
	bad.playcard();
	
	break;
	}
	case 2:
	{scoring points = new scoring(n, this.op);	
	points.y = z;
	points.z = z;
	points.arrayuse = this.arrayuse;
	points.active();
	break;
	}
	case 3:
	{
	this.buffing = new buff(n, this.op);	
	buffing.y = z;
	buffing.z = z;
	buffing.arrayuse = this.arrayuse;
	buffing.playcard();
		break;
	}
	case 4:
	{
		killcard k = new killcard(n,op);
		k.playcard();
		
		break;
	}
	case 5:
	{
		transumation tran= new transumation(n,op);
		tran.playcard();
		break;
	}
	case 6:
	{
		seeClass see= new seeClass(n,op);
		see.playcard();
		break;
	}
	case 7:
	{
		this.de = new defestive(n,op);
		de.primecard();
		
		break;
	}
	}
}

public void specialeff() {
	switch(this.cardtype){
	case 3:
	{
	buffing.active();
	break;
	}
	case 7:
	{
		
		de.playCard();
		
		break;
	}
	}
}
public effect(card n, player op)
{
	
	 this.n = n;
	 this.x = n.getid();
	 this.owner  = n.owner();
	 this.cardtype = n.handeffect();
	 this.color = n.getcolor();
	 this.op = op;
	 this.score = n.getscore();
	 
}



}
