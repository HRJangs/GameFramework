package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * 플레이어의 움직임을 제어하자
 * */
public class KeyBoard extends KeyAdapter{

	Player player;
	
	public KeyBoard(Player player) {
		this.player =player;
	}
	//키보드 누르면
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){ //소괄호안의 값에 대해 조건을 따져보겠다
			case KeyEvent.VK_LEFT:
				player.velX=-2;break;
			case KeyEvent.VK_RIGHT:
				player.velX=2;break;
			case KeyEvent.VK_UP:
				player.velY=-2;break;
			case KeyEvent.VK_DOWN:
				player.velY=2;break;
			case KeyEvent.VK_SPACE:
				player.fire();
				break;
		}
	}
	//키보드 떼면
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){ //소괄호안의 값에 대해 조건을 따져보겠다
			case KeyEvent.VK_LEFT:
				player.velX=0;break;
			case KeyEvent.VK_RIGHT:
				player.velX=0;break;
			case KeyEvent.VK_UP:
				player.velY=0;break;
			case KeyEvent.VK_DOWN:
				player.velY=0;break;			
		}
	}
}
