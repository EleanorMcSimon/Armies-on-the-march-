package specalcardeffect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

import cards.card;
import player.player;

class testcase {
	static player one = new player(1);
	static player two = new player(2);
	
	
@Test
	void baseCard()
	{
	for(int x =0; x< 3;x++)
	{
	try {
		Scanner sc = new Scanner(new File("C:\\Users\\ellies\\Documents\\card.csv"));
		sc.useDelimiter(",");
		int i = 0;
		sc.nextLine();
		while(sc.hasNextLine())
		{
		if(i%2==0)
		{
			String[] r = sc.nextLine().split(",");
			int id = Integer.parseInt(r[2]);
			int e = Integer.parseInt(r[3]);
			int d = Integer.parseInt(r[6]);
			
			card f = new card(r[0],r[1],r[4],id,e,d,0);
			f.makeeffic(one,two);
			this.one.getdrawdeck().add(0, f);
		}
		else
		{
			String[] r = sc.nextLine().split(",");
			int id = Integer.parseInt(r[2]);
			int e = Integer.parseInt(r[3]);
			int d = Integer.parseInt(r[6]);
			card m = new card(r[0],r[1],r[4],id,e,d,0);
			m.makeeffic(two,one);
			this.two.getdrawdeck().add(0, m);
		}
		i++;
		}
		this.two.draw(4);
		this.one.draw(4);
		this.two.printcardhand();
		this.one.printcardhand();
		//assertEquals("hand Size", 4, this.one.gethand().size());
	    //assertEquals("hand Size", 4,this.two.gethand().size());
	    play(two);
	    play(one);
	}
	 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	
	}
public static void play(player s)
{/**
*this plays a random card
**/
	if(!s.gethand().isEmpty())
{
	

	int hand = ThreadLocalRandom.current().nextInt(1, s.gethand().size());
	card[] h = new card[hand];
	for(int x =0; x < h.length; x++)
	{
		h[x] = s.gethand().get(randomhan(s));
	}
	s.removefor(h);
}
}
public static int randomhan(player s) {
	int randomNum = ThreadLocalRandom.current().nextInt(0, s.gethand().size());
	return randomNum;
	
}
@Test
void rehand() {
/**
 * add card back to hand that the player chose
 * to pass must add one to user's hand 
 * 
 */
	int w = one.gethand().size()+1;
	card f = new card("rebirth","black","none",4,0,0,0);
	
	f.makeeffic(one,two);
	one.getplayfield().getfield().add(f);
	one.printplayefield();
	
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(f)).usecard(0, 2);
	//System.out.println(this.one.gethand().size());
	assertEquals("card change", w, this.one.gethand().size());
}
@Test
void theif() {
	/** 
	 * check to steal a card from the oppeint hands
	 * adds to playfield after
	 */
	int w = one.getplayfield().getfield().size()+2;
	card f = new card("theif","black","none",0,0,0,0);
	two.gethand().add(f);
	f.makeeffic(one,two);
	one.getplayfield().getfield().add(f);
	one.printplayefield();
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(f)).usecard(0, 0);
	assertEquals("card change", w, this.one.getplayfield().getfield().size());
}

@Test 
void
discardhand()
{
	/** 
	 * removes half of user hand 
	 * in turn the oppent must remove there full hand
	 */
	System.out.println("");
	
	card w = new card("disguard","black","none",2,0,0,0);
	
	w.makeeffic(one,two);

	one.getplayfield().getfield().add(w);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(w)).usecard(0, 0);
    assertTrue(two.gethand().isEmpty());
	
	}

@Test
void cardswap()

{	
	/**
	 * swaps hands between player 
	 */
	two.draw(5);
	System.out.println("");
	//two.printcardhand();
	card f = new card("change","green","none",1,0,0,0);
	ArrayList<card>  e= one.gethand();
	f.makeeffic(one,two);

	one.getplayfield().getfield().add(f);
	one.getplayfield().getfield().get(one.getplayfield().getfield().size()-1).usecard(0, 0);
	//one.printcardhand();
}
@Test 
void colordraw()
{
	/** 
	 * tests the two cards that use color based drawing 
	 * this these cards are added to hand by color
	 * works for bolth cards
	 */
	int w = two.gethand().size()+5;
	System.out.println("");
	
	card f = new card("Draw","green","none",5,0,0,0);

	f.makeeffic(two,one);
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(0, 0);
	assertEquals("card change", w, this.two.gethand().size());
	int eo = one.gethand().size()+2;
	card n = new card("Draw","gold","none",6,0,0,0);
	n.makeeffic(one,two);
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	if(!one.getplayfield().getfield().isEmpty())
	{
		
		
	//int o = one.getplayfield().getfield().indexOf(n);
	
	assertEquals("card change", eo, this.one.gethand().size());
	}
}
@Test
void swapHands() {
	/**
	 * swap hands between the players
	 */
	ArrayList<card> car = two.gethand();
	card n = new card("Draw","gold","none",7,0,0,0);
	n.makeeffic(one,two);
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	assertEquals("card change", car, this.one.gethand());
}
@Test 
void overDrive()
{
	int d = one.gethand().size();
	card n = new card("Draw","gold","none",8,0,0,0);
	n.makeeffic(one,two);
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	assertNotEquals("overDrive",d , one.gethand().size());
	int w = two.gethand().size();
	card f = new card("Draw","green","none",8,0,0,0);
	f.makeeffic(two, one);
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
//	assertNotEquals("overDrive",w ,two.gethand().size());
}
@Test
void ChangingFate()
{
	
	int d = one.gethand().size();
	card n = new card("Draw","green","none",8,0,0,0);
	n.makeeffic(one,two);
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	one.draw(1);
}
@Test
void StopDrawing()
{
	int d = one.gethand().size();
	card n = new card("Draw","gold","none",0,0,1,0);
	n.makeeffic(one,two);
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	int s = two.gethand().size();
	two.getplayfield().getfield().add(n);
	two.getplayfield().removecard(two.getplayfield().getfield().get(0));

	assertEquals("equals", s , two.gethand().size());
	
}
@Test
void unionStrike() {
	int d = one.gethand().size();
	card n = new card("Draw","gold","none",1,0,1,0);
	n.makeeffic(two,one);
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
//one.printplayefield();
	
}
@Test
void skipturn() {
	card n = new card("Draw","gold","none",3,0,1,0);
	n.makeeffic(two,one);
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	   assertTrue(one.TurnSkipped());
	
}
@Test
void darkness() {
	card n = new card("Draw","black","none",4,0,1,0);
	n.makeeffic(two,one);
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	//two.printplayefield();
	
}
@Test 
void hypnotist() {
	card n = new card("Draw","black","none",5,0,1,0);
	n.makeeffic(two,one);
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(3, 0);
	two.printplayefield();
}
@Test
void saboteur() {
	card n = new card("Draw","gold","none",6,0,1,0);
	n.makeeffic(one,two);
	
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	two.printplayefield();
}
@Test
void brokenHands() {
	card n = new card("Draw","black","none",7,0,1,0);
	int[] dis = {0,1,2,3};
	n.makeeffic(two,one);
	n.setmanyuse(dis);
	
	two.getplayfield().getfield().add(n);
for(int x =0; x < 4; x++)
{
	one.gethand().add(n);
	two.gethand().add(n);
}

	int in = two.gethand().size()-(dis.length);
	int w = one.gethand().size() -(dis.length*2);
	
	
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	
	assertEquals("equals", in , two.gethand().size());
 // assertEquals("equals", w , one.gethand().size());

}
@Test
void Mischiefmaker() {
	int w = one.getscore();
	
	card n = new card("blood","black","none",0,0,2,0);
	n.setscore(1);
	n.makeeffic(one,two);
	int[] io = {1,2,3};
	n.setmanyuse(io);
	
	one.getplayfield().getfield().add(n);
	int wn =one.getplayfield().getfield().size()-io.length;
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	assertNotEquals("scoreing",w,one.getscore());
	assertEquals("Kills",wn,one.getplayfield().getfield().size());
}
@Test
void scoringbytype() {
	int w = two.getscore();
	card n = new card("Tree","green","none",1,0,2,0);
	n.setscore(1);
	n.makeeffic(two,one);
	int[] io = {1,2,3};
	n.setmanyuse(io);
	two.getplayfield().getfield().add(n);
	
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	assertNotEquals("scoreing",w,two.getscore());
}
@Test
void parsite() {
	int w = one.getscore();
	card n = new card("parsite","gold","none",2,0,2,0);
	n.setscore(1);
	n.makeeffic(one,two);
	one.getplayfield().getfield().add(n);

	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	assertNotEquals("scoreing",w,one.getscore());
}
@Test
void Exclusivescoring()
{
	int w = two.getscore();
	card n = new card("dimon","gold","none",3,0,2,0);
	n.setscore(1);
	n.makeeffic(two,one);

	two.getplayfield().getfield().add(n);
	
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	assertEquals("scoreing",w,two.getscore());	
}
@Test
void Socoring()
{
	int w = two.getscore();
	card n = new card("silve","gold","none",4,0,2,0);
	n.setscore(1);
	n.makeeffic(two,one);

	two.getplayfield().getfield().add(n);
	
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	assertNotEquals("scoreing",w,two.getscore());	
}
@Test 
void doubletime()
{
	card f = new card("change","green","none",1,0,0,0);
	f.makeeffic(two, one);
	two.getplayfield().getfield().add(f);
	int index = two.getplayfield().getfield().indexOf(f);
	card n = new card("twoUse","green","none",0,0,3,0);
	n.makeeffic(two, one);
	int[] e = {0,0,1,1};
	n.setmanyuse(e);
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(index, index);;

}
@Test 
void allforone()
{
	
	card f = new card("dimond","gold","none",3,0,2,0);
	f.makeeffic(two, one);
	two.getplayfield().getfield().add(f);
	card n = new card("all","gold","none",1,0,3,0);
	n.makeeffic(two, one);
	ArrayList<card> c = two.getplayfield().getfield();
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(0, 0);
	two.printplayefield();
	
}
@Test 
void forst()
{
	
	card f = new card("dimond","gold","none",3,0,2,0);
	f.makeeffic(one, two);
	one.getplayfield().getfield().add(f);
	card n = new card("all","green","none",2,0,3,0);
	n.makeeffic(one, two);
	ArrayList<card> c = one.getplayfield().getfield();
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard(0, 0);

	//one.printplayefield();
	
}
@Test 
void goldrush()
{
	card f = new card("dimond","gold","none",3,0,3,0);
	f.makeeffic(two, one);
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(0, 0);
	
}
@Test 
void nyft()
{
	card f = new card("tree","green","none",1,0,2,0);
	
	f.makeeffic(two, one);
	card n = new card("tree","green","none",4,2,3,0);
	n.makeeffic(two, one);
	two.getplayfield().getfield().add(f);
/// y=	two.getplayfield().getfield().indexOf(f);
two.getplayfield().getfield().add(n);
two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(two.getplayfield().getfield().indexOf(f), 0);
assertEquals("this",0,two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).getdecay());	
	
}
@Test 
void forstgrowth()
{
	card f = new card("tree","green","none",1,0,2,0);
	
	f.makeeffic(two, one);
	card n = new card("tree","green","none",5,0,3,0);
	n.makeeffic(two, one);
	two.getplayfield().getfield().add(f);
/// y=	two.getplayfield().getfield().indexOf(f);
two.getplayfield().getfield().add(n);
two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(n)).usecard(two.getplayfield().getfield().indexOf(f), 0);
assertEquals("this",2,two.getplayfield().getfield().get(0).getdecay());	
	
}

@Test 
void viveGrowth()
{

	card w = new card("mine","black","none",4,1,2,0);	
	w.makeeffic(one, two);
	
	card n = new card("vivGrwoth ","green","none",6,0,3,0);
	n.makeeffic(one, two);
	one.getplayfield().getfield().add(w);
	one.getplayfield().getfield().add(n);
	int y = one.getplayfield().getfield().indexOf(n);
	int h = one.getplayfield().getfield().indexOf(w);
	one.getplayfield().getfield().get(y).usecard(h, 0);
	// assertTrue(one.getplayfield().getfield().get( one.getplayfield().getfield().indexOf(w)).colormatch("green"));
}

@Test 
void Digdeeper()
{
	one.getplayfield().getfield().clear();
	card f = new card("mine","gold","none",4,2,2,0);	
	card w = new card("mine","gold","none",4,2,2,0);	
	w.makeeffic(one, two);
	f.makeeffic(one, two);

	card n = new card("digdeeper ","gold","none",7,0,3,0);
	n.makeeffic(one, two);
	one.getplayfield().getfield().add(w);

	one.getplayfield().getfield().add(f);
	one.getplayfield().getfield().add(n);
	
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(n)).usecard( one.getplayfield().getfield().indexOf(w), 0);
	assertEquals("this",2,one.getplayfield().getfield().get( one.getplayfield().getfield().indexOf(w)).getdecay());	
	
}
@Test
void lamb()
{
	card hn = new card("lamb","black","none",8,2,3,0);
	card noi = new card("bloodtower","black","none",0,2,4,0);
	noi.makeeffic(two,one);
	hn.makeeffic(two, one);
	two.getplayfield().getfield().clear();
	


	one.getplayfield().getfield().add(noi);

	two.getplayfield().getfield().add(noi);
	
	
	

	two.getplayfield().getfield().add(hn);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(hn)).usecard(0, 0);
	two.getplayfield().getfield().get(two.getplayfield().getfield().lastIndexOf(noi)).usecard(two.getplayfield().getfield().lastIndexOf(hn),0);
	two.getplayfield().defensitveActive();
	//assertTrue(two.getplayfield().getfield().get(two.getplayfield().getfield().lastIndexOf(hn)).dyingeffect(false));

	//assertTrue(two.getplayfield().getfield().get(o).dyingeffect(true));
	
	
	
	
}
@Test
void miner()
{
	card oo= new card("mine","gold","none",3,3,2,0);	
	card h = new card("miner","gold","none",10,2,3,0);
	h.makeeffic(two, one);
	oo.makeeffic(two, one);
	two.getplayfield().getfield().add(h);
	two.getplayfield().getfield().add(oo);
	two.getplayfield().getfield().get(two.getplayfield().getfield().lastIndexOf(h)).usecard(two.getplayfield().getfield().indexOf(oo),0);
}
@Test
void getallout()
{
	/**
	 * passing error needs to be fixed
	 */
	int how = two.getscore();
	card f = new card("solver","gold","none",4,2,2,0);
	card h = new card("getall","gold","none",11,0,3,0);
	h.makeeffic(two, one);
	f.makeeffic(two, one);
	f.setscore(2);

	two.getplayfield().getfield().add(h);
	two.getplayfield().getfield().add(f);
	assertTrue(two.getplayfield().getfield().get(two.getplayfield().getfield().lastIndexOf(f)).colormatch("gold"));
	assertEquals(two.getplayfield().getfield().get(two.getplayfield().getfield().lastIndexOf(f)).handeffect(),2 );
	int find =two.getplayfield().getfield().indexOf(f);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(h)).usecard(find,find);
	assertEquals(0,two.getplayfield().getfield().get(two.getplayfield().getfield().lastIndexOf(f)).getdecay());
	assertNotEquals("scoreing",how,two.getscore());	
}
@Test
void bloodTowwer()
{
	/**
	 *fixed
	 */
	two.getplayfield().getfield().clear(); 
	card f = new card("bloodtower","black","none",0,2,4,0);
	f.makeeffic(two, one);
	
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().add(f);
	one.getplayfield().getfield().add(f);
	int w = two.getplayfield().getfield().size()-1;
	int h = one.getplayfield().getfield().size()-1;
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(0, 0);
	assertEquals("scoreing",w,two.getplayfield().getfield().size());	
	assertEquals("scoreing",h,one.getplayfield().getfield().size());
}
@Test
void robotworkers()
{
	int sizestest = two.getplayfield().getfield().size();
	card h = new card("miner","gold","none",10,2,3,0);
	card f = new card("robot","gold","none",1,0,4,0);
	f.makeeffic(two,one);
	h.makeeffic(two, one);
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().add(h);
	two.getplayfield().getfield().add(h);
	two.getplayfield().getfield().add(h);
	
	int w = two.getscore();
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(0, 0);
	assertNotEquals("scoreing",w,two.getscore());	
	assertNotEquals("size",sizestest,two.getplayfield().getfield().size());	
}
@Test
void snowSweaped()
{
	card f = new card("robot","gold","none",2,0,4,0);
	f.makeeffic(one,two);
	one.getplayfield().getfield().add(f);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(f)).usecard(0, 0);
assertTrue(one.getplayfield().getfield().isEmpty());
}
@Test
void bloodblood()
{
	int size = one.getplayfield().getfield().size();
	int sizeu = two.getplayfield().getfield().size();
	card hn = new card("lamb","black","none",8,2,3,0);
	hn.makeeffic(two, one);
	two.getplayfield().getfield().add(hn);
	two.getplayfield().getfield().add(hn);
	two.getplayfield().getfield().add(hn);
	card f = new card("blood","black","none",3,0,4,0);
	f.makeeffic(two,one);
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(0, 0);
	assertNotEquals("scoreing",size,one.getplayfield().getfield().size());
	assertNotEquals("scoreing",sizeu,two.getplayfield().getfield().size());
}
@Test
void trader()
{one.getplayfield().getfield().clear();
two.getplayfield().getfield().clear();
	card n = new card("blood","black","none",3,0,4,0);
	card f = new card("trader","black","none",4,0,4,0);
	f.makeeffic(two,one);
	n.makeeffic(two, one);

	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().add(f);
	one.getplayfield().getfield().add(f);
	int w = one.getplayfield().getfield().size();
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(two.getplayfield().getfield().indexOf(n), one.getplayfield().getfield().indexOf(f));
	assertEquals("size",w-1,one.getplayfield().getfield().size());	
}
@Test
void faster()
{
	/** 
	 * works on it's own but not in full run
	 */
	two.getplayfield().getfield().clear();
	card wn = new card("blood","gold","none",4,0,2,0);
	card n = new card("blood","gold","none",4,0,2,0);
	card f = new card("faster","gold","none",5,0,4,0);
	f.makeeffic(two,one);
	n.makeeffic(two, one);
  wn.makeeffic(two, one);
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().add(wn);
	int w = two.getplayfield().getfield().size();
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(two.getplayfield().getfield().indexOf(n), two.getplayfield().getfield().indexOf(wn));
	assertNotEquals("scoreing",w,two.getplayfield().getfield().size());	
}
@Test
void lambborn()
{
	card wn = new card("born","black","none",0,0,5,0);
	wn.makeeffic(one, two);
	one.getplayfield().getfield().add(wn);
	one.gethand().add(wn);
	int get = one.gethand().size()-1;
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(wn)).usecard(one.gethand().indexOf(wn), 0);
	assertEquals("matches",get,one.gethand().size());	
}	
@Test
void rebirth()
{
	card wn = new card("born","green","none",1,0,5,0);
	wn.makeeffic(one, two);
	one.getplayfield().getfield().add(wn);

	//one.gethand().add(wn);
	int get = one.getplayfield().getfield().size();
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(wn)).usecard(one.gethand().indexOf(wn), 0);
	assertEquals("matches",get,one.getplayfield().getfield().size());	
}	
@Test
void turned()
{
	two.getplayfield().getfield().clear();
	card wn = new card("bornlamb","black","none",2,0,5,0);
	wn.makeeffic(one, two);
	int get = two.getplayfield().getfield().size();
	one.getplayfield().getfield().add(wn);
	two.getplayfield().getfield().add(wn);

	
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(wn)).usecard(two.getplayfield().getfield().indexOf(wn), 0);
	assertEquals("matches",get,two.getplayfield().getfield().size());	

}
@Test
void neworders()
{
	card wn = new card("neworders","gold","none",3,0,5,0);
	wn.makeeffic(one, two);
	one.getplayfield().getfield().add(wn);
	one.getplayfield().getfield().add(wn);
	//one.gethand().add(wn);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(wn)).usecard(one.gethand().indexOf(wn), 0);


}
@Test
void constuction()
{ two.getplayfield().getfield().clear();
	card wf = new card("constuction","gold","none",4,0,5,0);
	wf.makeeffic(two, one);
	card gold = new card("mine","gold","none",4,3,2,0);	
	two.getplayfield().getfield().add(wf);
	

	int w = two.getplayfield().getfield().size()+2;
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(wf)).usecard(0, 0);
	assertEquals( w, two.getplayfield().getfield().size());


}
@Test
void forman()
{ 
	card wf = new card("constuction","gold","none",5,0,5,0);
	wf.makeeffic(two, one);
	two.getplayfield().getfield().add(wf);
	two.getplayfield().getfield().add(wf);
	two.getplayfield().getfield().add(wf);
	int g  =two.getplayfield().getfield().indexOf(wf);
	
	two.getplayfield().getfield().get(g).usecard(two.getplayfield().getfield().indexOf(wf), two.getplayfield().getfield().lastIndexOf(wf));


}
@Test
void rit()
{ 	card h = new card("miner","gold","none",10,2,3,0);
	card f = new card("faster","gold","none",5,0,4,0);
	card wf = new card("rit","black","none",6,0,5,0);
	wf.makeeffic(two, one);
	f.makeeffic(two, one);
	h.makeeffic(two, one);
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().add(h);
	two.getplayfield().getfield().add(wf);
	int g  =two.getplayfield().getfield().indexOf(wf);
	
	two.getplayfield().getfield().get(g).usecard(two.getplayfield().getfield().indexOf(f), two.getplayfield().getfield().lastIndexOf(h));


}
@Test
void silver()
{ 	card h = new card("miner","gold","none",4,2,2,0);
	card f = new card("faster","gold","none",4,0,2,0);
	card wf = new card("rit","gold","none",7,0,5,0);
	wf.makeeffic(two, one);
	f.makeeffic(two, one);
	h.makeeffic(two, one);
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().add(h);
	two.getplayfield().getfield().add(wf);
	int g  =two.getplayfield().getfield().indexOf(wf);
	
	two.getplayfield().getfield().get(g).usecard(two.getplayfield().getfield().indexOf(f), two.getplayfield().getfield().lastIndexOf(h));


}
@Test
void forsight()
{
 	card h = new card("forsight","green","none",0,2,6,0);
	h.makeeffic(two, one);
	two.getplayfield().getfield().add(h);
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(h)).usecard(0, 0);
}
@Test
void phorsey()
{
 	card h = new card("forsight","green","none",1,2,6,0);
	h.makeeffic(one, two);
	one.getplayfield().getfield().add(h);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(h)).usecard(0, 0);
}
@Test
void unknownunboad()
{
 	card h = new card("forsight","green","none",2,2,6,0);
	h.makeeffic(one, two);
	one.getplayfield().getfield().add(h);
	//one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(h)).usecard(0, 0);
}
@Test 
void throns()

{
	card n = new card("forsight","green","none",2,2,6,0);
	card h = new card("throb","green","none",1,2,7,1);
	h.makeeffic(one,two);
	n.makeeffic(one, two);
	two.getplayfield().getfield().add(n);
	two.getplayfield().getfield().add(h);
	
	card f = new card("trader","black","none",4,1,4,1);
	f.makeeffic(two,one);
	two.getplayfield().getfield().add(f);
	two.getplayfield().getfield().add(f);
	one.getplayfield().getfield().add(n);
	one.getplayfield().getfield().add(h);
	one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(h)).usecard(one.getplayfield().getfield().indexOf(n), two.getplayfield().getfield().indexOf(f));
	
	two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(two.getplayfield().getfield().indexOf(h), one.getplayfield().getfield().indexOf(h));
	int y = two.getplayfield().getfield().size();


	
	one.getplayfield().defensitveActive();	
	assertEquals(y-1, two.getplayfield().getfield().size());
}
@Test
void scafnyth()
{	
	two.getplayfield().getfield().clear();
	one.getplayfield().getfield().clear();
		card n = new card("forsight","green","none",2,2,6,0);
		card h = new card("nyth","green","none",0,2,7,1);
		h.makeeffic(two,one);
		n.makeeffic(two, one);
		two.getplayfield().getfield().add(n);
		two.getplayfield().getfield().add(h);
		
		card f = new card("trader","black","none",4,0,4,0);
		f.makeeffic(two,one);
		two.getplayfield().getfield().add(f);
		h.makeeffic(one,two);
		n.makeeffic(one, two);
		one.getplayfield().getfield().add(n);
		one.getplayfield().getfield().add(h);
		one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(h)).usecard(one.getplayfield().getfield().indexOf(n), one.getplayfield().getfield().indexOf(n));
		two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(two.getplayfield().getfield().indexOf(h), one.getplayfield().getfield().indexOf(h));

		int y = one.getplayfield().getfield().size()+1;

		
		one.getplayfield().defensitveActive();	
		assertEquals(y, one.getplayfield().getfield().size());
}
@Test
void protect()
{	
	two.getplayfield().getfield().clear();
	one.getplayfield().getfield().clear();
		card n = new card("forsight","green","none",2,2,6,0);
		card h = new card("nyth","green","none",2,2,7,1);
		h.makeeffic(two,one);
		n.makeeffic(two, one);
		two.getplayfield().getfield().add(n);
		two.getplayfield().getfield().add(h);
		
		card f = new card("trader","black","none",4,0,4,0);
		f.makeeffic(two,one);
		two.getplayfield().getfield().add(f);
		h.makeeffic(one,two);
		n.makeeffic(one, two);
		one.getplayfield().getfield().add(n);
		one.getplayfield().getfield().add(h);
		int y = one.getplayfield().getfield().size() ;
		one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(h)).usecard(one.getplayfield().getfield().indexOf(n), one.getplayfield().getfield().indexOf(n));
		one.getplayfield().defensitveActive();	
	
		//two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(two.getplayfield().getfield().indexOf(h), one.getplayfield().getfield().indexOf(h));

		

		

		assertEquals(y, one.getplayfield().getfield().size());
		assertTrue(one.getplayfield().getfield().get(0).isdisable());
}
@Test
void forstunion()
{	

	two.getplayfield().getfield().clear();
	one.getplayfield().getfield().clear();
		card n = new card("forsight","green","none",2,7,6,0);
		card h = new card("nyth","green","none",3,1,7,1);
		card nw = new card("forsight","green","none",2,6,6,0);
		h.makeeffic(two,one);
		n.makeeffic(two, one);
		
		two.getplayfield().getfield().add(n);
		two.getplayfield().getfield().add(nw);
		two.getplayfield().getfield().add(h);
		
		card f = new card("trader","black","none",4,0,4,0);
		f.makeeffic(two,one);
		two.getplayfield().getfield().add(f);
		h.makeeffic(one,two);
		n.makeeffic(one, two);
		nw.makeeffic(one, two);
		
		one.getplayfield().getfield().add(n);
		one.getplayfield().getfield().add(h);
		one.getplayfield().getfield().add(nw);
		one.getplayfield().getfield().get(one.getplayfield().getfield().indexOf(h)).usecard(0,0);
		

			
		two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(two.getplayfield().getfield().indexOf(h), one.getplayfield().getfield().indexOf(n));
		//two.getplayfield().getfield().get(two.getplayfield().getfield().indexOf(f)).usecard(two.getplayfield().getfield().indexOf(h), one.getplayfield().getfield().indexOf(h));
	one.getplayfield().defensitveActive();
		

		

		//assertEquals(y, one.getplayfield().getfield().size());
		//assertTrue(one.getplayfield().getfield().get(0).isdisable());
}

}

