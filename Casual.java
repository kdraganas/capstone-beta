import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Casual extends BasicGameState{
	
	public String mouse = "";

    public Casual(int state){

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
    	Image bg = new Image("res/casual.png");
    	g.drawImage(bg, 0, 0);
    	g.drawString(mouse, 50, 50);
    	g.drawString("naa ka sa casual", 50, 100);


    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
    	int xpos = Mouse.getX();
    	int ypos = Mouse.getY();
    	mouse = "x pos = "+xpos+"   y pos = "+ypos;
    	Input input = gc.getInput();
    	if((xpos>379 && xpos<431) && (ypos>22&&ypos<83) ){
    		if(input.isMouseButtonDown(0)){	//0: left-clicked
    			sbg.enterState(1);
    		}
    	}

    	
    }
    
    public int getID(){
        return 10;
    }

}
