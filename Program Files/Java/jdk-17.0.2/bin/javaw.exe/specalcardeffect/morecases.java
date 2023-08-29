package specalcardeffect;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cards.card;
import player.player;

class morecases {

	static player one = new player(1);
	static player two = new player(2);
	@Test
	void lamb()
	{
		card hn = new card("lamb","black","none",8,2,3,0);
		card noi = new card("Draw","black","none",5,2,1,0);
		noi.makeeffic(two,one);
		hn.makeeffic(two, one);
		two.getplayfield().getfield().clear();
		


		one.getplayfield().getfield().add(noi);

		two.getplayfield().getfield().add(noi);
		two.getplayfield().getfield().add(noi);
		
		

		two.getplayfield().getfield().add(hn);
		two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(noi)).usecard(104, 10);
		assertTrue(two.getplayfield().getfield().get(two.getplayfield().getfield().lastIndexOf(hn)).dyingeffect(false));
	//assertTrue(two.getplayfield().getfield().get(o).dyingeffect(true));
		
		
		
		
	}

}
