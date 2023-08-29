package ai;

import static org.junit.Assert.*;

import org.junit.Test;

import game.gamemanger;
import player.player;

public class testcTest {
	gamemanger m = new gamemanger();


@Test
public void playgame()
{
	m.startround();
	
	m.game(true, true,2 );
	System.out.print(m.one().getscore());
	System.out.print(m.two().getscore());
}/*
	@Test
	public void test() {
		m.startround();
		m.partone();
		
		w.whatcardstoplay();
		m.one().printplayefield();
		n.whatcardstoplay();
		
		n.minmax();
	}
	
	@Test 
	public void fiveround()
	{
		
		//m.startround();
		for(int x=0; x < 10; x++)
		{
			m.partone();
			
			w.whatcardstoplay();
			w.minmax();
			n.whatcardstoplay();
			n.minmax();

			
		}
	}
*/
}
