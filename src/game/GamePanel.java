package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

//��� ������ �� �гξȿ��� �׷��� �����̴�.
//�ƹ��������� ����� �پ��ϴ��� 
//�г��� ���� 1���� ���ȴ�.
/*
 * ��� ������Ʈ�� �ᱹ ���гο� �׷����� �ϹǷ�
 * �� �г��� paint �޼����� �μ��� ���޵Ǵ�
 * Graphics��ü�� ���ӿ� ������ ��� ������Ʈ�� ���޹޾ƾ� �Ѵ�..
 * */
public class GamePanel extends JPanel implements Runnable{

	public static final int WIDTH =400;
	public static final int HEIGHT =300;
	public static final int SCALE =2;
	Thread thread; //������ ������ �����带 ����(�������)
	boolean flag=true;//���Ӱ������θ� �����ϴ� ����
	Player player;
	ObjectManager objectManager; //��ü ��� ������
	
	public GamePanel() {
		thread = new Thread(this);
		
		thread.start();
		init();
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
	}
	//���ΰ� �����Ű��
	public void init(){
		//��� ������ ����
		objectManager = new ObjectManager();
		//���ΰ� �����Ű��
		player =  new Player(objectManager,ObjectId.Player,300, 200, 50, 50);
		objectManager.addObject(player);
		//�гΰ� Ű���� ������ ����
		this.addKeyListener(new KeyBoard(player));
		//���� ����
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
			//������Ʈ �Ŵ����� ��ϵ� ��� ��ü�� ������� tick()�� ȣ���غ���
			for(int i =0; i< objectManager.list.size();i++){
				objectManager.list.get(i).tick();
			}
			repaint();  //paintComponent�� ���������� ȣ���Ѵ�.
		}
	}
}
