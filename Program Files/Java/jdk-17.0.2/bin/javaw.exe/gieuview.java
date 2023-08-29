import java.awt.TextField;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Stack;
import java.util.concurrent.Semaphore;

import game.gamemanger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import player.player;

public class gieuview extends Application{

	
	   private ImageView[] maincard;
	   private ImageView[] fieldcards;
	   private ImageView[] opcardsfield;
	   String card[];
	   int activecard= 0;
	   int carduser =0;
	   int active =0;
	   boolean phase2= false;
	   Stack<Integer> getall = new Stack<Integer>();
	   Stack<Integer> remove = new Stack<Integer>();
	   Stack<int[]> usermove = new Stack<int[]>();
	   Stack<int[]> morestack = new Stack<int[]>();
	   gamemanger m = new gamemanger();
	   player c;
	   Hashtable<String, String> my_dict = new Hashtable<String, String>();
	   int userScroll =0;
	   FlowPane cards = new FlowPane();  
	   FlowPane play = new FlowPane();  
	   FlowPane op = new FlowPane();  
	   Stack<Integer> move = new Stack<Integer>();
	   Stack<Integer> cardschosen = new Stack<Integer>();
		int[] messer = new int[0];
	     private static final Semaphore uiSemaphore = new Semaphore(1);
public void setforvsai()
{
	m.startround();
	m.one().draw(5);
	m.two().draw(5);
	//m.one().lookatdeck();
	//m.game(true, false);
	c = m.one();
}
public static void main(String[] args) {
	// TODO Auto-generated method stub
	/**
	 * starts gui
	 */
	 launch(args);
}
EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e)
    {
    	cards.card[] messer = new cards.card[ getall.size()];
     for(int x =0; x < messer.length; x++)
     {
    
    	 messer[x] = c.gethand().get(getall.pop());
    	
     }
     c.removefor(messer);
     cards.getChildren().clear();
     playfield(c, play, true);
    }
};
EventHandler<ActionEvent> submet = new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e)
    {
     messer = new int[cardschosen.size() ];
    	Stack<int[]> out = new Stack<int[]>();
    	Stack<int[]> other = new Stack<int[]>();
    	if(!phase2)
        {
    		play.getChildren().clear();
    		 phase2 = true;
    		playfield(c,play, false);
    		
    		 scrollhand();
    		
        
     for(int x =0; x < messer.length; x++)
     {
    
    	 messer[x] = cardschosen.pop();
    	 
   
    	 
     }
    
        }
    	else if(phase2 && !move.isEmpty() && cardschosen.isEmpty())
     {
    		 int[] what = new int[2];;
    		 int[] why =  new int[0];
    	if(activecard < messer.length)
    	{
    		activecard++;
    	}
    	else
    	{
    		c.getplayfield().activecards(messer, usermove, morestack);
    		
    	}
    	
    	 if(move.size() > 2)
    	{
    		
    		why = new int[move.size()-2];
    		
    	}
    for(int y= 0; y<  what.length; y++)
    {
    	what[y] = move.pop();
    }
    if(!move.isEmpty())
    {
    	 for(int z= 0; z<  why.length; z++)
    	    {
    		 why[z] = move.pop();
    	    }	
    }
    usermove.push(what);
    	if(why.length !=0)
    	{
    		morestack.push(why);
    	}
    	
    }
    }
};

public void  playfield(player n,FlowPane in, boolean d)
{if( d || !this.cardschosen.isEmpty() )
{
	
	   Button b = new Button("use Cards");
	    b.setOnAction(submet);
	    in.getChildren().add(b);
}
 

	File directoryPath = new File("C:\\Users\\ellies\\Documents\\images\\");
    //List of all files and directories
    card = directoryPath.list();
   
    VBox first = new VBox();
    first.setOnMouseEntered(event -> {
    	try {
			this.uiSemaphore.acquire();
			System.out.println("in");
	    	
	    	if(userScroll-5 < 0)
	    	{
	    		
	    		userScroll =0;
	    	}
	    	else if(userScroll-5 > 0)
	    	{
	    		cards.getChildren().clear();
	    		userScroll--;
	    		scrollhand();
	    	}
	    
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    	
    		
    	
    	
    });
	 String cssLayout = "-fx-border-color: black;\n" +
             "-fx-border-insets: 5;\n" +
             "-fx-border-width: 3;\n" +
             "-fx-border-style: solid;\n"+
             "-fx-background-color: #FFFFFF;\n";
  	first.setStyle(cssLayout);
  	in.getChildren().add(first);
    int cardsm =0;
    ImageView[] view;
   if(d)
   {
	   view = this.fieldcards;
   }
   else
   {
	   view = this.opcardsfield;   
   }
   view = new ImageView[n.getplayfield().getfield().size()];
  
    for(int x =userScroll; x < n.getplayfield().getfield().size(); x++)

  	  
    {
    	
  
  	
  	  Image f = new Image("C:\\Users\\ellies\\Documents\\images\\"+ this.card[0]);
  	 
  	view[x] = new ImageView(f);
  	  
  	view[x].setPreserveRatio(true);
  	view[x].setFitHeight(65);
  	view[x].setFitWidth(80);
  	view[x].setTranslateY(-80);
  	view[x].setTranslateX(10);
  	  VBox holder = new VBox();
  	holder.setMinWidth(100);
  	holder.setMinHeight(185);
  	holder.setSpacing(5);
  	 String cssLayout2 = "-fx-border-color: black;\n" +
             "-fx-border-insets: 5;\n" +
             "-fx-border-width: 3;\n" +
             "-fx-border-style: solid;\n"+
             "-fx-background-color: #FFFFFF;\n";
  	holder.setStyle(cssLayout2);
  	
  	  Label name = new Label();
  	  name.setText(n.getplayfield().getfield().get(x).getname());
	    Label label = new Label();
	    label.setText(n.getplayfield().getfield().get(x).getcolor());
	    label.setWrapText(true);
	    label.setMinWidth(1);
	    label.setPrefWidth(1);;
	    label.setMaxWidth(1);
	    label.setAlignment(Pos.TOP_LEFT);
	 
	   
  	   
  	    name.setAlignment(Pos.TOP_RIGHT);
  	    holder.getChildren().add(name);
  	   holder.getChildren().add(label);
  	   
  	   holder.setId(""+(x+2));
   	  holder.getChildren().add( view[x]);
   	 
  	  holder.setOnMouseClicked(event ->{

  		int w = Character.getNumericValue(holder.getId().charAt(0));
  		 
  		 if (event.getButton() == MouseButton.PRIMARY &&!this.phase2 )
         {
  		 if(holder.getParent()== play  )
  		{
  			if(holder.getTranslateY() == -100)
  			{
  				
  				holder.setTranslateY(0);
  				Integer r= Integer.valueOf(w);
  				cardschosen.remove(r);
  				
  				
  				
  			}
  			else
  			{  
  				cardschosen.push(w);
  				holder.setTranslateY(-100);
  			
  			}
  		
  			
  		}
         }
  		else if (this.phase2 ) {
  		if( this.carduser == 7 || this.carduser== 3 || this.carduser == 4 ||this.carduser == 3 || this.carduser == 2)
  		{
  		
  			if(holder.getTranslateY() == -50)
			{
  				
				
			//holder.getChildren().remove(2+activecard);
  		
  				Integer r= Integer.valueOf(w);
				move.remove(r);
				holder.setTranslateY(0);
			}
			else
			{
			
			
				
				move.push(w);
				holder.setTranslateY(-50);
				move.pop();
				
			}
  		}
  		}
  		
  		    	  });
  	in.getChildren().add(holder);

    }
   
    String cssLayout3 = "-fx-border-color: black;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-border-width: 3;\n" +
            "-fx-border-style: solid;\n"+
            "-fx-background-color: #FFFFFF;\n";
    
  

  
    

}

public void  scrollhand()
{if(this.phase2 == false)
{
    Button b = new Button("Play cards");
    b.setOnAction(event);
    cards.getChildren().add(b);
}
    
	File directoryPath = new File("C:\\Users\\ellies\\Documents\\images\\");
    //List of all files and directories
    card = directoryPath.list();
   
    VBox first = new VBox();
    first.setOnMouseEntered(event -> {
    	
    	System.out.println("in");
    	
    	if(this.userScroll-5 < 0)
    	{
    		
    		userScroll =0;
    	}
    	else if(this.userScroll-5 > 0)
    	{
    		this.cards.getChildren().clear();
    		this.userScroll--;
    		this.scrollhand();
    	}
    
    		
    	
    	
    });
	 String cssLayout = "-fx-border-color: black;\n" +
             "-fx-border-insets: 5;\n" +
             "-fx-border-width: 3;\n" +
             "-fx-border-style: solid;\n"+
             "-fx-background-color: #FFFFFF;\n";
  	first.setStyle(cssLayout);
    cards.getChildren().add(first);
    int cardsm =0;
    this.maincard = new ImageView[this.c.gethand().size()];
  
    for(int x =userScroll; x < this.c.gethand().size(); x++)

  	  
    {
    	
  
  	
  	  Image f = new Image("C:\\Users\\ellies\\Documents\\images\\"+ this.card[this.c.gethand().get(x).imagelink()]);
  	 
  	  this.maincard[x] = new ImageView(f);
  	  
  	  this.maincard[x].setPreserveRatio(true);
  	  this.maincard[x].setFitHeight(65);
  	  this.maincard[x].setFitWidth(80);
  	this.maincard[x].setTranslateY(-80);
	this.maincard[x].setTranslateX(10);
  	  VBox holder = new VBox();
  	holder.setMinWidth(100);
  	holder.setMinHeight(185);
  	holder.setSpacing(5);
  	 String cssLayout2 = "-fx-border-color: black;\n" +
             "-fx-border-insets: 5;\n" +
             "-fx-border-width: 3;\n" +
             "-fx-border-style: solid;\n"+
             "-fx-background-color: #FFFFFF;\n";
  	holder.setStyle(cssLayout2);
  	
  	  Label name = new Label();
  	  name.setText(this.c.gethand().get(x).getname());
	    Label label = new Label();
	    label.setText(this.c.gethand().get(x).getcolor());
	    label.setWrapText(true);
	    label.setMinWidth(1);
	    label.setPrefWidth(1);;
	    label.setMaxWidth(1);
	    label.setAlignment(Pos.TOP_LEFT);
	 
	   
  	   
  	    name.setAlignment(Pos.TOP_RIGHT);
  	    holder.getChildren().add(name);
  	   holder.getChildren().add(label);
  	   
  	   holder.setId(""+(x));
   	  holder.getChildren().add( this.maincard[x]);
  	  holder.setOnMouseClicked(event ->{

int w = Character.getNumericValue(holder.getId().charAt(0));
	if(holder.getTranslateY() == -100)
	{
		
	
		//int get = getall.indexOf(w-1);
		
		Integer r= Integer.valueOf(w);
	
			getall.remove(r);		
			holder.setTranslateY(0);
		
		
	}else	
	{
		holder.setTranslateY(-100);
  	  
	 	 
	  		getall.push(w);
	}
		
  		try {
			this.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
  		    	  });
  	  cards.getChildren().add(holder);

    }
   
    String cssLayout3 = "-fx-border-color: black;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-border-width: 3;\n" +
            "-fx-border-style: solid;\n"+
            "-fx-background-color: #FFFFFF;\n";
    
    VBox end = new VBox();
    end.setStyle(cssLayout3);
    end.setOnMouseEntered(event -> {
    	
    	if(this.c.gethand().size() >= this.maincard.length)
    	{
    	System.out.println("in");
    		cards.getChildren().clear();
        	userScroll++;
    		if(userScroll+5 > c.gethand().size())
    	{
    		userScroll=0;
    	}
    	cards.getChildren().clear();
    	scrollhand();
    	}
    });

  
    
    cards.getChildren().add(end);
}
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.setTitle("armies on the march");
		 VBox content = new VBox();
		 gamemanger m = new gamemanger();
		
		
		 setforvsai();
		   content.setBackground(new Background(
				   new BackgroundImage(
				   new Image("C:\\Users\\ellies\\Documents\\images\\tableasset.png",400, 300, false, false),
				   BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				   new BackgroundPosition(Side.LEFT, 10, true, Side.TOP,10000, true),
                   new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
           )
				
				  
				   )
   );
		   //Creating a File object for directory
		      
		  
		      
		      cards.setAlignment(Pos.BOTTOM_CENTER);
		      cards.setTranslateY(500);
		      cards.setVgap(10);
		      play.setAlignment(Pos.CENTER);
		      play.setTranslateY(200);
		      play.setVgap(10);
		      scrollhand();
		      this.playfield(this.c, this.play, false);
		      content.getChildren().add(cards);
		      content.getChildren().add(this.play);
		   Scene scene = new Scene(content, 800, 600);
		   arg0.setScene(scene);
		arg0.show();
	}

}
