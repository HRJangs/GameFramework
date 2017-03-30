package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

//모든 게임은 이 패널안에서 그려질 예정이다.
//아무리게임의 장면이 다양하더라도 
//패널은 오직 1개만 사용된다.
/*
 * 모든 오브젝트는 결국 이패널에 그려져야 하므로
 * 이 패널의 paint 메서드의 인수로 전달되는
 * Graphics객체를 게임에 등장할 모든 오브젝트가 전달받아야 한다..
 * */
public class GamePanel extends JPanel implements Runnable{

	public static final int WIDTH =400;
	public static final int HEIGHT =300;
	public static final int SCALE =2;
	Thread thread; //게임을 진행할 스레드를 선언(운영스레드)
	boolean flag=true;//게임가동여부를 결정하는 변수
	Player player;
	ObjectManager objectManager; //객체 명단 관리자
	
	public GamePanel() {
		thread = new Thread(this);
		
		thread.start();
		init();
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
	}
	//주인공 등장시키기
	public void init(){
		//명단 관리자 생성
		objectManager = new ObjectManager();
		//주인공 등장시키기
		player =  new Player(objectManager,ObjectId.Player,300, 200, 50, 50);
		objectManager.addObject(player);
		//패널과 키보드 리스너 연결
		this.addKeyListener(new KeyBoard(player));
		//적군 등장
		Random r= new Random();
		for(int i=0;i<10;i++){
			int y = r.nextInt(HEIGHT*SCALE-50-50+1)+50;
			int x = r.nextInt(WIDTH*SCALE+500-(WIDTH*SCALE+1))+50;
			Enemy enemy = new Enemy(objectManager,ObjectId.Enemy, x, y, 30, 30);
			objectManager.addObject(enemy);
		}
	}
	protected void paintComponent(Graphics g) {
		 g.setColor(Color.black);
		 g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		 for(int i =0; i< objectManager.list.size();i++){
				objectManager.list.get(i).render(g);
			}
	}	
	public void run() {
		while(flag){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//오브젝트 매니저에 등록된 모든 객체를 대상으로 tick()을 호출해보자
			for(int i =0; i< objectManager.list.size();i++){
				objectManager.list.get(i).tick();
			}
			repaint();  //paintComponent를 간접적으로 호출한다.
		}
	}
}
