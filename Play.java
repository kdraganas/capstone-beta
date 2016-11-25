

/**
 * Created by Conni on 11/16/2016.
 */

import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;
import org.newdawn.slick.geom.ShapeRenderer;



public class Play extends BasicGameState{
	
	public String mouse = "";
	protected int score = 0;
	private float rect1X = 220;
	private float rect1Y = 0;
	private float rect2X = 220;
	private float rect2Y = 160;
	private float rect3X = 220;
	private float rect3Y = 320;
	private float rect4X = 220;
	private float rect4Y = 480;
	private double rectYAdd = 0.3;
	private final int sxpos = 195;
	private final int ypos = 550;
	private final int oxpos = 171;
	private final int imagex = 40;
	private final int imagey = 50;
	private final int obstaclex = 83;
	private final int obstacley = 118;
	private float obsy = 30;
	private int count = 0;
	private boolean[] boo = new boolean[6];
	protected boolean[] shapes = new boolean[5];
	private boolean[] state = new boolean[5];
	private Rectangle sp = new Rectangle(sxpos,ypos, obsy, obsy);
	private Rectangle ob = new Rectangle(oxpos,obsy, obsy, obsy);
	protected boolean diffShape = false;
	protected boolean theresAnObject = false;
	protected int value;
	private double add = 1.0;
	public Score s;
	
	//private Graphics g;


	public Play(int state){
    	
    }
	

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
    	for(int i=0;i<6;i++) {
			boo[i] = false;
		}
    	initilizer();

    }

    private void initilizer() {
		for(int i=0; i<5; i++){
			shapes[i] = false;
			state[i] = false;
		}
		shapes[0] = true;
		theresAnObject = false;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
    	Image background = new Image("res/pastelbg.png");

    	g.drawImage(background, 0, 0);
    	g.setColor(Color.white);
    	
    	g.setColor(Color.darkGray);
    	g.fillRect(170, 0, 110, 640);

		Random rand = new Random();
		
		if(!theresAnObject){
			obsy = 0;
			value = rand.nextInt(5);
			theresAnObject = true;
			initializer(state);
			ObstacleDraw(value,state,ob);
		}
		if(theresAnObject && obsy < 500){
			ObstacleDraw(value,state,ob);
		}
		if(obsy >= 500){
			obsy = 640;
			collided(shapes,state);
			theresAnObject = false;
			initializer(state);

		}
    	
    	for(int i=0; i<5; i++){
    		if(shapes[i]){
				initializer(shapes);
    			ShapeDraw(i,shapes,sp);
    			break;
    		}
    	}
    	
    	if(rect1Y > 640){
    		rect1Y = 0;
    	}
    	if(rect2Y > 640){
    		rect2Y = 0;
    	}
    	if(rect3Y > 640){
    		rect3Y = 0;
    	}
    	if(rect4Y > 640){
    		rect4Y = 0;
    	}
    	g.setColor(Color.darkGray);
    	g.drawString(mouse, 50, 50);
    	g.drawString("SCORE: "+score, 10, 70);
    	g.drawString("speed: "+add, 10, 100);
    }
	
	@Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Graphics g = null;
		int mxpos = Mouse.getX();
    	int mypos = Mouse.getY();
    	mouse = "x pos = "+mxpos+"   y pos = "+mypos;
		
    	obsy+=add;
    	Input input = gc.getInput();
    	
    	if (diffShape){
    		diffShape = false;
    		initializer(shapes);
    		shapes[0] = true;
    		add = 1.0;
    		//write file for score
    		score = 0;
    		sbg.enterState(4);	//4: gameover state
    		
    	}

    	if(input.isKeyDown(Input.KEY_W)){
    		initializer(shapes);
    		shapes[0] = true;
    	}
    	if(input.isKeyDown(Input.KEY_D)){
    		initializer(shapes);
    		shapes[1] = true;
    	}
    	if(input.isKeyDown(Input.KEY_S)){
    		initializer(shapes);
    		shapes[2] = true;
    	}
    	if(input.isKeyDown(Input.KEY_A)){
    		initializer(shapes);
    		shapes[3] = true;
    	}
    	if(input.isKeyDown(Input.KEY_SPACE)){
    		initializer(shapes);
    		shapes[4] = true;
    	}
    	if(input.isKeyDown(Input.KEY_UP)){
    		//boo = false;
    	}
    }

	public void ShapeDraw(int i, boolean[] br, Rectangle sp2) throws SlickException{
		Image[] im = {new Image("res/CircleChar.png"), new Image("res/SquareChar.png"), new Image("res/TriangleChar.png"), new Image ("res/RectangleChar.png"), new Image ("res/DiamondChar.png")} ;
		Graphics gr = new Graphics();	
		gr.drawImage(im[i], sxpos, ypos);
		br[i] = true;
	}
	public void ObstacleDraw(int i, boolean[] br, Rectangle rc) throws SlickException{
		Image[] obs = {new Image("res/Circle2.png"), new Image("res/Square2.png"), new Image("res/Triangle2.png"), new Image("res/Rectangle2.png"), new Image("res/Diamond2.png")};
		Graphics gd = new Graphics();
		gd.drawImage(obs[i], oxpos, obsy);
		state[i] = true;
	}
	public int getTrue(boolean[] br){
		for(int i=0; i<5; i++){
			if(br[i]){
				return i;
			}
		}
		return -2;
	}
	public void initializer(boolean[] br){
		for(int i=0; i<5; i++){
			br[i] = false;
		}
	}
	public void collided(boolean[] br, boolean[] bs){
		if(getTrue(br) == getTrue(bs)){
			score++;
			//s.setScore(score);
			add = add+0.1;
		}
		else{
			diffShape = true;
		}
	}

    public int getID(){
        return 1;
    }

}
