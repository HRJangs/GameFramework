package game;


import javax.swing.JFrame;

public class GameWindow extends JFrame{
	
	GamePanel gamepanel;
	
	
	public GameWindow(){
		gamepanel = new GamePanel();
		add(gamepanel);
		
		//�гο� ���α׷��������� ��Ŀ�� �ø���
		gamepanel.setFocusable(true);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new GameWindow();
	}


}
