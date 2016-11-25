import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Player1Wins extends BasicGameState{
	
	public String mouse = "";

    public Player1Wins(int state){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
    	Image bg = new Image("res/gameover1.png");
    	g.drawImage(bg, 0, 0);
    	
    	g.drawString(mouse, 50, 50);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
    	int xpos = Mouse.getX();
    	int ypos = Mouse.getY();
    	mouse = "x pos = "+xpos+"   y pos = "+ypos;
    	Input input = gc.getInput();
    	if((xpos>190 && xpos<279) && (ypos>264&&ypos<362) ){
    		if(input.isMouseButtonDown(0)){	//0: left-clicked
    			sbg.enterState(5);	//0: menu state
    		}
    	}
    	if((xpos>190 && xpos<290) && (ypos>129&&ypos<224) ){
    		if(input.isMouseButtonDown(0)){	//0: left-clicked
    			sbg.enterState(0);	//0: menu state
    		}
    	}
    }

    public int getID(){
        return 7;
    }

}
