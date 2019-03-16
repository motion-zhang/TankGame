package UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ̹�˵�������
 * 
 * @author john
 * 
 */
public class GameMenuUI extends JFrame implements Runnable {
	// ��Ӱ�ťͼƬ
	private ImageIcon Icons[] = new ImageIcon[5];
	public static int loc = 0;// ���ó�ʼ���Ϊ0
	private MyPanel myPanel;
	private boolean isstop = false;

	public static void main(String args[]) {
		GameMenuUI tui = new GameMenuUI();
		tui.initUI();
		// ����ˢ���߳�
		Thread thread = new Thread(tui);
		thread.start();
	}

	public void initUI() {
		// ���ô���
		this.setTitle("̹�˴�ս");
		this.setSize(246, 348);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setFocusable(true);// ����JFrame ������Ի�ý���:���̼���������Ҫ�������ý���֮���������

		// // ��Ӱ�ť����
		for (int i = 0; i < Icons.length; i++) {
			Icons[i] = new ImageIcon("images/tankfacemenu_start" + i + ".png");
		}

		// ���һ��JPanel(�����ػ�)
		myPanel = new MyPanel();
		this.add(myPanel);
		this.setVisible(true);
		// ��Ӽ��̼�����
		ButtonListener butnLis = new ButtonListener(this);
		this.addKeyListener(butnLis);
	}

	public void stopThread() {
		isstop = true;
	}

	public void run() {
		while (!isstop) {
			myPanel.repaint();
			try {
				Thread.sleep(100);
			} catch (Exception ef) {
				ef.printStackTrace();
			}
		}
	}

	class MyPanel extends JPanel {
		public MyPanel() {
			this.setPreferredSize(new Dimension(240, 320));
		}

		// override ��дJPanel��paint���������㻭��Ϸ����
		public void paint(Graphics g) {
			super.paint(g);
			// ������Ϸ����
			// ˫����
			// 1�������⻭��
			Image img = this.createImage(this.getPreferredSize().width,
					this.getPreferredSize().height);
			Graphics gr = img.getGraphics();
			// 2��Ҫ���Ƶ�ͼ����Ƶ����⻭����ȥ
			ImageIcon pic = new ImageIcon("images/tankfacing.png");
			// ��Ϸ��ʼ���洦��
			// 1���ý����ϵ�ͼƬ�ָ���ʼ״̬��û��һ����ť����������ʵ��һ��ͼƬ������ѡ�У�
			gr.drawImage(pic.getImage(), 0, 0, null);
			// 2�����½��������ϰ�ťͼƬ�������Ӿ�Ч������ÿ��ֻ��һ����ť��ѡ����
			gr.drawImage(Icons[loc].getImage(), 75, 150 + 25 * loc, null);
			// gr.drawImage(startIcon.getImage(), 75, 150, null);
			// gr.drawImage(settingIcon.getImage(), 75, 175, null);
			// gr.drawImage(achieveIcon.getImage(), 75, 200, null);
			// gr.drawImage(helpIcon.getImage(), 75, 225, null);
			// gr.drawImage(quitIcon.getImage(), 75, 250, null);

			// 3�����⻭��������Ļ��ȥ
			g.drawImage(img, 0, 0, null);
		}
	}

	public class ButtonListener extends KeyAdapter {
		private GameMenuUI gameMenu;

		public ButtonListener(GameMenuUI gameMenu) {
			this.gameMenu = gameMenu;
		}

		public void keyPressed(KeyEvent e) {
			// ���·����
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (GameMenuUI.loc == 0) {
					GameMenuUI.loc = 4;
				} else {
					GameMenuUI.loc--;
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				GameMenuUI.loc = (GameMenuUI.loc + 1) % 5;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (GameMenuUI.loc == 0) {
					GameMenuUI.loc = 4;
				} else {
					GameMenuUI.loc--;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				GameMenuUI.loc = (GameMenuUI.loc + 1) % 5;
			}
			// ���»س���
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				gameMenu.stopThread();// �����߳�
				switch (GameMenuUI.loc) {
				case 0:
					GameStartUI gameStart = new GameStartUI();
					gameStart.initUI();
					gameMenu.dispose();
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:

					gameMenu.dispose();// �߳�û���Ƿ���Ӱ��
				}
			}
			// ����ESC��
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				gameMenu.stopThread();// �����߳�
				gameMenu.dispose();
			}
		}
	}
}
