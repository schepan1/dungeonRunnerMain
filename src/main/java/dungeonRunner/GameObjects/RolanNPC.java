package dungeonRunner.GameObjects;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.Log;

import dungeonRunner.GameObjects.Player;
import dungeonRunner.core.Battle;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerPlayerLookState;
import dungeonRunner.util.dungeonRunnerTextField;

public class RolanNPC extends NPC {
	
	
	public SpriteSheet batSheetRight;
	public Animation batAnimationRight;
	
	public SpriteSheet batSheetLeft;
	public Animation batAnimationLeft;

	public Animation batAnimationDown;
	public SpriteSheet batSheetDown;
	
	public Animation batAnimationUp;
	public SpriteSheet batSheetUp;
	
	public SpriteSheet standSouth;
	public SpriteSheet standNorth;
	public SpriteSheet standWest;
	public SpriteSheet standEast;
	
	public dungeonRunnerTextField text;
	
	public boolean dropped;
	public float movementFreQ;
	
	public Random rnd;
	int rand = -1;
	
	boolean moveDown;
	boolean moveUp;
	boolean moveLeft;
	boolean moveRight;
	
	
	
	public RolanNPC(float x, float y, String pName, float moveSpeedX, float moveSpeedY, Map map) throws SlickException{
		super(x,y, pName, moveSpeedX, moveSpeedY, map);	
		
	
		health = 100;
		maxHealth = 100;
		quest = true;
		rnd = new Random();
		
		givesExperience = 5;
		enemyImage = new Image("res/sprites/enemies/bat/batImage.png");
		
		batSheetRight = new SpriteSheet("res/sprites/enemies/rolan/right.png", 32, 32);
		batAnimationRight = new Animation(batSheetRight, 300);

		batSheetLeft = new SpriteSheet("res/sprites/enemies/rolan/left.png", 32, 32);
		batAnimationLeft = new Animation(batSheetLeft, 300);
		
		batSheetDown = new SpriteSheet("res/sprites/enemies/rolan/down.png", 32, 32);
		batAnimationDown = new Animation(batSheetDown, 300);
		
		batSheetUp = new SpriteSheet("res/sprites/enemies/rolan/up.png", 32, 32);
		batAnimationUp = new Animation(batSheetUp, 300);
		
		standSouth = new SpriteSheet("res/sprites/enemies/rolan/standDown.png", 32, 32);
		standNorth = new SpriteSheet("res/sprites/enemies/rolan/standUp.png", 32, 32);
		standEast = new SpriteSheet("res/sprites/enemies/rolan/standRight.png", 32, 32);
		standWest = new SpriteSheet("res/sprites/enemies/rolan/standLeft.png", 32, 32);
		
		movementFreQ = 0; //ms
		

		
		currentMap = map;
	
		}
	
	public void update(){	
		

		
		if(interActionHitbox1.intersects(Player.hitbox) && dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) || interActionHitbox2.intersects(Player.hitbox) && dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) ){
			this.action();
			
			if(interActionHitbox1.intersects(Player.hitbox) && Player.x < x){
				Player.ausrichtung = dungeonRunnerPlayerLookState.East;
				}
			if(interActionHitbox1.intersects(Player.hitbox) && Player.x > x){
				Player.ausrichtung = dungeonRunnerPlayerLookState.West;
				}
			if(interActionHitbox2.intersects(Player.hitbox) && Player.y > y){
				Player.ausrichtung = dungeonRunnerPlayerLookState.North;
				}
			if(interActionHitbox2.intersects(Player.hitbox) && Player.y < y){
				Player.ausrichtung = dungeonRunnerPlayerLookState.South;
				}
		}
		
	
		
		movementFreQ += dungeonRunnerEngine.fdelta;
		
		
//		// Sorgt daf�r dass die Gegner nicht durch W�nde laufen k�nnen und dass sich die Gegner auf den Spieler zu bewegen.
//		if((Math.round(x * 100) /100.0f) <= (Math.round(Player.x * 100 ) / 100.0f) && !(currentMap.isBlocked(x + SIZE + dungeonRunnerEngine.fdelta, y) || currentMap.isBlocked(x + SIZE + dungeonRunnerEngine.fdelta, y + SIZE- 1))){
//			x+= moveSpeedX;
//		
//			ausrichtung = dungeonRunnerPlayerLookState.East;
//		}
//		if((Math.round(x * 100) /100.0f) >= (Math.round(Player.x * 100 ) / 100.0f) && !(currentMap.isBlocked(x - dungeonRunnerEngine.fdelta, y) || currentMap.isBlocked(x - dungeonRunnerEngine.fdelta, y + SIZE - 1))){
//			x-= moveSpeedX;
//			ausrichtung = dungeonRunnerPlayerLookState.West;
//		}
//		
//		if((Math.round(y * 100) /100.0f) <= (Math.round(Player.y * 100 ) / 100.0f) && !(currentMap.isBlocked(x, y + SIZE + dungeonRunnerEngine.fdelta) || currentMap.isBlocked(x + SIZE - 1, y + SIZE+ dungeonRunnerEngine.fdelta))){
//			y+= moveSpeedY;
//			ausrichtung = dungeonRunnerPlayerLookState.South;
//		}
//		if((Math.round(y * 100) /100.0f) >= (Math.round(Player.y * 100 ) / 100.0f) && !(currentMap.isBlocked(x, y - dungeonRunnerEngine.fdelta) || currentMap.isBlocked(x + SIZE - 1, y - dungeonRunnerEngine.fdelta)) && x != Player.x){
//			y-= moveSpeedY;
//			ausrichtung = dungeonRunnerPlayerLookState.North;
//		}
		
		
		if(movementFreQ >= 200){ //700
			rand = rnd.nextInt(4); 
			
		//	rand = 0;
						
			if(rand == 0){
				moveDown = true;
				y += 0.5*32;	
				interActionHitbox1.setLocation(x-32, y);
				interActionHitbox2.setLocation(x, y-32);
				super.hitbox.setLocation(x, y);
				this.ausrichtung = dungeonRunnerPlayerLookState.South;

			}
			else if(rand == 1){
				moveUp = true;
				y -= 0.5*32;
				interActionHitbox1.setLocation(x-32, y);
				interActionHitbox2.setLocation(x, y-32);
				super.hitbox.setLocation(x, y);
				this.ausrichtung = dungeonRunnerPlayerLookState.North;
			
			}
			else if(rand == 2){
				moveLeft = true;
				x -= 0.5*32;
				interActionHitbox1.setLocation(x-32, y);
				interActionHitbox2.setLocation(x, y-32);
				super.hitbox.setLocation(x, y);
				this.ausrichtung = dungeonRunnerPlayerLookState.West;
			}
			else if(rand == 3){
				moveRight = true;
				x += 0.5*31;
				interActionHitbox1.setLocation(x-32, y);
				interActionHitbox2.setLocation(x, y-32);
				super.hitbox.setLocation(x, y);
				this.ausrichtung = dungeonRunnerPlayerLookState.East;
			}
			
			movementFreQ = -1;
			rand = -1;
		}
		
		
		
	}
	
	
	@Override
	public void renderEnemy(Graphics g){
			
		
		drawNPCName(g, Color.green.brighter());
		
		if(this.ausrichtung == dungeonRunnerPlayerLookState.South){
			standSouth.draw(x,y);
		}
		if(this.ausrichtung == dungeonRunnerPlayerLookState.North){
			standNorth.draw(x,y);
		}
		if(this.ausrichtung == dungeonRunnerPlayerLookState.West){
			standWest.draw(x,y);
		}
		if(this.ausrichtung == dungeonRunnerPlayerLookState.East){
			standEast.draw(x,y);
		}
		

		if(moveDown == true ){
		if(batAnimationDown.isStopped()){
			batAnimationDown.restart();
		}
		batAnimationDown.draw(x, y);
		batAnimationDown.stopAt(3);
		if(batAnimationDown.isStopped())
			moveDown = false;
		}
	
		if(moveUp == true ){
		if(batAnimationUp.isStopped()){
			batAnimationUp.restart();
		}
		batAnimationUp.draw(x, y);
		batAnimationUp.stopAt(1);
		if(batAnimationUp.isStopped())
			moveUp = false;
		}
		
		if(moveLeft == true ){
		if(batAnimationLeft.isStopped()){
			batAnimationLeft.restart();
		}
		batAnimationLeft.draw(x, y);
		batAnimationLeft.stopAt(1);
		if(batAnimationLeft.isStopped())
			moveLeft = false;
		}
		
		if(moveRight == true ){
		if(batAnimationRight.isStopped()){
			batAnimationRight.restart();
		}
		batAnimationRight.draw(x, y);
		batAnimationRight.stopAt(1);
		if(batAnimationRight.isStopped())
			moveRight = false;
		}
	}
		
		
//		if(rand == 0){	
//			g.drawAnimation(batAnimationUp, x, y);
//		}
//		else if(rand == 1){
//			g.drawAnimation(batAnimationUp, x, y);
//		}
//		else if(rand == 2){
//			batAnimationLeft.draw(x, y);
//		}
//		else if(rand == 3){
//			batAnimationRight.draw(x, y);
//		}
		


	
	
	public void moveUpAnimation(){
		batAnimationUp.draw(x, y);
	}
	
	
	
	@Override
	public void action(){
		System.out.println("Rolan");
	}
		
	



	}
	
	



