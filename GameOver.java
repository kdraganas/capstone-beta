

/**
 * Created by Conni on 11/16/2016.
 */

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameOver extends BasicGameState{
	public String mouse = "";
	public Score sc;
	public String score = "0";
	public int s = 0;
	
	
	
	
    public GameOver(int state){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

    	Image gameover = new Image("res/gameover.png");
    	g.drawImage(gameover, 0, 0);
    	g.setColor(Color.darkGray);
    	g.drawString(score, 10, 70);
    	g.drawString(mouse, 50, 50);
    	
    	
    }
    
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
    	int xpos = Mouse.getX();
    	int ypos = Mouse.getY();
    	mouse = "x pos = "+xpos+"   y pos = "+ypos;
    //	s = sc.getScore();
    	//score = "" + s; 
    	Input input = gc.getInput();
    	
    	
    	if((xpos>181 && xpos<278) && (ypos>132 && ypos<223) ){
    		if(input.isMouseButtonDown(0)){	//0: left-clicked
    			sbg.enterState(0);	//1: play state
    		}
    	}
    	
    	if((xpos>193 && xpos<283) && (ypos>261 && ypos<348) ){
    		if(input.isMouseButtonDown(0)){	//0: left-clicked
    			sbg.enterState(1);	//1: play state
    		}
    	}

    	
    }

    public int getID(){
        return 4;
    }

}
