import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Modes extends BasicGameState{
	
	public String mouse = "";

    public Modes(int state){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
    	Image bg = new Image("res/modes.png");
    	g.drawImage(bg, 0, 0);
    	g.drawString(mouse, 50, 50);
    	g.drawString("naa ka sa modes", 50, 50);


    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
    	int xpos = Mouse.getX();
    	int ypos = Mouse.getY();
    	mouse = "x pos = "+xpos+"   y pos = "+ypos;
    	Input input = gc.getInput();
    	if((xpos>258 && xpos<434) && (ypos>333&&ypos<373) ){
    		if(input.isMouseButtonDown(0)){	//0: left-clicked
    			sbg.enterState(10);	
    		}
    	}
    	if((xpos>85 && xpos<438) && (ypos>206&&ypos<246) ){
    		if(input.isMouseButtonDown(0)){	//0: left-clicked
    			sbg.enterState(11);
    		}
    	}
    	
    }
    
    public int getID(){
        return 6;
    }

}
