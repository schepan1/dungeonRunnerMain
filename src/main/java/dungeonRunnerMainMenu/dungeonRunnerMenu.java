package dungeonRunnerMainMenu;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.util.dungeonRunnerStates;


public class dungeonRunnerMenu {
	
	
	private String[] menuPoints =  {"[START]","[LOAD]","[OPTIONS]","[EXIT]"};
	private int menuPointer = 0;
	
	private int menuPointerGraphic = 205;
	public Music menuMusic;
	private Sound upDown;
	private Sound confirm;
	
	public static boolean menuMusicBool;

	
	public dungeonRunnerMenu(){
		try {
			menuMusic = new Music("res/music/menuMusic/menuMusic.wav");
			upDown = new Sound("res/sounds/menu/menuSound+-.wav");
			confirm = new Sound("res/sounds/menu/menuConfirm.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Zeichnet das Graphic Men�
	 */
	
	public void initMenuGraphic(Graphics g){

		g.setBackground(org.newdawn.slick.Color.white);
		g.setColor(org.newdawn.slick.Color.black);
		

		g.drawString(menuPoints[0], 380, 200);
		g.drawString(menuPoints[1], 380, 220);
		g.drawString(menuPoints[2], 380, 240);
		g.drawString(menuPoints[3], 380, 260);
		
		
		/**
		 * Bei neuem Men�punkt -------HIER EINTRAGEN---------
		 */
		g.setLineWidth(2);
		if (menuPointer == 0) {
			g.drawOval(370, menuPointerGraphic, 5, 5);
		}

		if (menuPointer == 1) {
			g.drawOval(370, menuPointerGraphic, 5, 5);
		}

		if (menuPointer == 2) {
			g.drawOval(370, menuPointerGraphic, 5, 5);
		}

		if (menuPointer == 3) {
			g.drawOval(370, menuPointerGraphic, 5, 5);
		}
	}
	
	
	/**
	 * 
	 * Logik des Men�s
	 * 
	 * 
	 * 
	 * @param gc
	 * @param delta
	 */
	public void menuControl(GameContainer gc, int delta){
		
		if(menuMusicBool == true){
		playMenuMusic();
		}
		
		/**
		 * �nderung des Men�Punkts f�r Key_DOWN
		 */
		if (dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_S)) {
			if(menuPointer == menuPoints.length-1){
				menuPointer = 0; 					// �nderung des Men�punktes
				menuPointerGraphic = 205;				//�nderung des Pointers(Graphic)
				upDown.play(1,0.5f);					// Sound der Abgespielt wird.
				System.out.println("Current Menu Point " + menuPointer);
			}
			else{
			menuPointer += 1;		// �nderung des Men�punktes
			menuPointerGraphic += 20;		//�nderung des Pointers(Graphic)
			upDown.play(1,0.5f);			// Sound der Abgespielt wird.
			System.out.println("Current Menu Point " + menuPointer);
			}
		} 
		
		/**
		 * �nderung des Men�Punkts f�r Key_UP
		 */
		if (dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_W)) {
			if(menuPointer == 0){
				menuPointer = menuPoints.length-1;			// �nderung des Men�punktes
				menuPointerGraphic = 265;					//�nderung des Pointers(Graphic)
				upDown.play(1,0.5f);							// Sound der Abgespielt wird.
				System.out.println("Current Menu Point " + menuPointer);
			}
			else{
			menuPointer -= 1;					// �nderung des Men�punktes
			menuPointerGraphic -= 20;					//�nderung des Pointers(Graphic)
			upDown.play(1,0.5f);						// Sound der Abgespielt wird.
			System.out.println("Current Menu Point " + menuPointer);
			}
		}
		
		/**
		 * Men�punkteingabe Best�tigung mit Enter
		 */
			if(menuPoints[menuPointer].equals("[START]") &&  dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) || dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_SPACE)){
				confirm.play(1,0.7f);				// Sound der Abgespielt wird.
				dungeonRunnerEngine.currentState = dungeonRunnerStates.Chapterchoose;
			}
			
			if(menuPoints[menuPointer].equals("[LOAD]") &&  dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) || dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_SPACE)){
				confirm.play(1,0.7f);				// Sound der Abgespielt wird.
				System.out.println("Loading..");
			}
			
			if(menuPoints[menuPointer].equals("[OPTIONS]") &&  dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) || dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_SPACE) ){
				confirm.play(1,0.7f);				// Sound der Abgespielt wird.
				dungeonRunnerEngine.currentState = dungeonRunnerStates.Options;
			}
			
			if(menuPoints[menuPointer].equals("[EXIT]") &&  dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER)|| dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_SPACE) ){
				gc.exit();
			}
	}
	
	public void playMenuMusic(){
		menuMusic.loop(0.9f, 0.6f);
	}
	


}
