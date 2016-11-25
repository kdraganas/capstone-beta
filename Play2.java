
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;



public class Play2 extends BasicGameState{
	
	public String mouse = "";
	protected int score = 0;
	//shapes
	private final int p1xpos = 73;
	private final int p2xpos = 343;
	//obstacles
	private final int xpos1 = 50;
	private final int xpos2 = 320;
	private float obsy = 30;
	private final int oxpos = 171;
	protected boolean[] shapes = new boolean[5];
	protected boolean[] shapes2 = new boolean[5];
	private boolean[] state = new boolean[5];
	private boolean[] state2 = new boolean[5];
	private Rectangle ob = new Rectangle(oxpos,obsy, obsy, obsy);
	protected boolean diffShape = false;
	protected boolean diffShape2 = false;
	protected boolean theresAnObject = false;
	protected int value;
	protected int rndmObs;
	private double add = 1.0;
	
	public Play2(int state){
    	
    }
	
	public int getScore(){
    	return this.score;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	//	diffShape = false;
		//diffShape2 = false;
    	initilizer();
    }

    private void initilizer() {

		for(int i=0; i<5; i++){
			shapes[i] = false;
			state[i] = false;
			shapes2[i] = false;
			state2[i] = false;

		}
		shapes[0] = true;
		shapes2[0] = true;
		add = 1.0;
		
		theresAnObject = false;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
    	Image background = new Image("res/pastelbg.png");
    	g.drawImage(background, 0, 0);
    	
    	g.setColor(Color.darkGray);
    	
		Random rand = new Random();
		
		if(!theresAnObject){
			obsy = 0;
			value = rand.nextInt(5);
			rndmObs = rand.nextInt(5);
			theresAnObject = true;
			initializer(state,state2);
			ObstacleDraw(value,rndmObs,state,ob);
		}
		if(theresAnObject && obsy < 500){
			ObstacleDraw(value,rndmObs,state,ob);
		}
		if(obsy >= 450){
			obsy = 640;
			collided(shapes,state,shapes2,state2);
			theresAnObject = false;
			initializer(state,state2);
		}
    	
    	for(int i=0; i<5; i++){
    		if(shapes[i]){
    	    	initializerFor1(shapes);
    			ShapeDraw(i,shapes,1);
    			break;
    		}
    	}
    	for(int i=0; i<5; i++){
    		if(shapes2[i]){
    	    	initializerFor1(shapes2);
    			ShapeDraw(i,shapes2,2);
    			break;
    		}
    	}
    	g.setColor(Color.darkGray);
    	g.drawString(mouse, 50, 50);
    	g.drawString("SCORE: "+score, 10, 70);
    	g.drawString("speed: "+add, 10, 100);
    }
	
	@Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int mxpos = Mouse.getX();
    	int mypos = Mouse.getY();
    	mouse = "x pos = "+mxpos+"   y pos = "+mypos;
		
    	obsy+=add;
    	Input input = gc.getInput();
    	
    	if (diffShape || diffShape2){	//game over
    		initializer(shapes,shapes2);
    		shapes[0] = true;
    		shapes2[0] = true;
    		score = 0;
    		if(diffShape && diffShape2){
    			sbg.enterState(9);	//state sa image nga mulugwa kay player 2 wins	
    		}
    		else{
    			if(diffShape){
    				sbg.enterState(8);
    			}
    			else{
    				sbg.enterState(7);
    			}
    		}
    	}
    	
    	if(input.isKeyPressed(Input.KEY_W)){
    		initializerFor1(shapes);
    		shapes[0] = true;
    	}
    	if(input.isKeyPressed(Input.KEY_D)){
    		initializerFor1(shapes);
    		shapes[1] = true;
    	}
    	if(input.isKeyPressed(Input.KEY_S)){
    		initializerFor1(shapes);
    		shapes[2] = true;
    	}
    	if(input.isKeyPressed(Input.KEY_A)){
    		initializerFor1(shapes);
    		shapes[3] = true;
    	}
    	if(input.isKeyPressed(Input.KEY_SPACE)){
    		initializerFor1(shapes);
    		shapes[4] = true;
    	}
    	if(input.isKeyPressed(Input.KEY_UP)){
    		initializerFor1(shapes2);
    		shapes2[0] = true;
    	}
    	
    	if(input.isKeyPressed(Input.KEY_DOWN)){
    		initializerFor1(shapes2);
    		shapes2[2] = true;
    	}
    	
    	if(input.isKeyPressed(Input.KEY_LEFT)){
    		initializerFor1(shapes2);
    		shapes2[3] = true;
    	}
    	
    	if(input.isKeyPressed(Input.KEY_RIGHT)){
    		initializerFor1(shapes2);
    		shapes2[1] = true;
    	}
    	
    	if(input.isKeyPressed(Input.KEY_ENTER)){
    		initializerFor1(shapes2);
    		shapes2[4] = true;
    	}
    }

	public void ShapeDraw(int i, boolean[] br, int player) throws SlickException{
		Image[] im = {new Image("res/miniCircleChar.png"), new Image("res/miniSquareChar.png"), new Image("res/miniTriangleChar.png"), new Image ("res/miniRectangleChar.png"), new Image ("res/miniDiamondChar.png")} ;
		Graphics gr = new Graphics();	
		if(player==1){
			gr.drawImage(im[i], p1xpos, /*p1ypos*/ 500);
		}
		
		if(player==2){
			gr.drawImage(im[i], p2xpos, /*p2ypos*/500);
		}
		
		br[i] = true;
		
	}
	public void ObstacleDraw(int i, int rndmObs, boolean[] br, Rectangle rc) throws SlickException{
		Image[] obs = {new Image("res/miniCircle.png"), new Image("res/miniSquare.png"), new Image("res/miniTriangle.png"), new Image("res/miniRectangle.png"), new Image("res/miniDiamond.png")};
		Graphics gd = new Graphics();
		gd.drawImage(obs[i], xpos1, obsy);
		gd.drawImage(obs[rndmObs], xpos2, obsy);
		state[i] = true;
		state2[rndmObs] = true;
	}
	public int getTrue(boolean[] br){
		for(int i=0; i<5; i++){
			if(br[i]){
				return i;
			}
		}
		return -2;
	}
	public void initializer(boolean[] br, boolean[] br2){
		for(int i=0; i<5; i++){
			br[i] = false;
		}
		for(int i=0; i<5; i++){
			br2[i] = false;
		}
	}
	public void initializerFor1(boolean[] br){
		for(int i=0; i<5; i++){
			br[i] = false;
		}
	}
	public void collided(boolean[] sh, boolean[] st, boolean[] sh2, boolean[] st2){
		if(getTrue(sh) != getTrue(st)){
			diffShape = true;
		}
		if(getTrue(sh2) != getTrue(st2)){
			diffShape2 = true;
		}
		add = add+0.2;
	}

    public int getID(){
        return 5;
    }

}
