package UI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.GameEngine;
import bullet.Bullet;
import tank.Tank;

/**
 * ����Ϸ�Ľ���
 * 
 * @author john
 * 
 */
public class GameStartUI extends JFrame implements Runnable {
	public static boolean isUp = false;
	public static boolean isDown = false;
	public static boolean isLeft = false;
	public static boolean isRight = false;
	public static boolean isFire = false;
	public static int pressed = 2;// ������¼�ϴΰ��µķ����(Ĭ�Ϸ�������)
	public boolean isStop = false;
	public boolean isSuspend = false;
	public MyPanel myPanel;

	public void initUI() {
		// ���ô���
		this.setTitle("̹�˴�ս");
		this.setSize(246, 348);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setFocusable(true);// ����JFrame ������Ի�ý���:���̼���������Ҫ�������ý���֮���������
		// ����һ�������ػ��PANEL
		myPanel = new MyPanel();
		this.add(myPanel);
		GameEngine engine = new GameEngine();
		engine.gameStart();// ������Ϸ
		engine.start();// ������Ϸ�����̣߳����ֺ��ж���Ӯ
		this.setVisible(true);
		// �������̼�����
		ButtonListener butnLis = new ButtonListener();
		this.addKeyListener(butnLis);
		Thread t = new Thread(this);// ����ˢ���߳�
		t.start();
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
			ImageIcon pic = new ImageIcon("images/background.png");
			ImageIcon condition = new ImageIcon("images/infshow.png");
			// ����ͼ
			gr.drawImage(pic.getImage(), 0, 0, null);
			// gr.drawImage(condition.getImage(), 0, 234, null);

			// ��̹�˺��ӵ��Լ����ƻ���������ȥ
			Vector<Tank> ta = GameEngine.tankArray;
			Vector<Bullet> ba = GameEngine.bulletArray;
			for (int i = 0; i < ta.size(); i++) {
				// �ж�����
				if (ta.get(i).status == 0) {
					// ̹������
					ta.get(i).stopThread();// �����߳�
					ImageIcon movies = new ImageIcon(
							"images/explode_tankmovie_frame01.png");
					gr.drawImage(movies.getImage(), ta.get(i).x, ta.get(i).y,
							null);// ��̹�˱�ը��ͼƬ����ȥ
					ta.remove(i);// ���Ѿ���ը��̹�˴Ӷ������Ƴ�
				} else {
					// ѡ��ͼƬ
					ImageIcon tankIcon[] = new ImageIcon[4];// ̹��4�������ͼƬ
					if (ta.get(i).type > 0) {
						// ����0 ���������̹��
						for (int j = 0; j < tankIcon.length; j++) {
							tankIcon[j] = new ImageIcon("images/playertank"
									+ ta.get(i).type + (j + 1) + ".png");
						}// ����ϴΰ������ĸ�����������ݷ����������ӡ��ͼƬ
						gr.drawImage(tankIcon[pressed - 1].getImage(),
								ta.get(i).x, ta.get(i).y, null);
					} else if (ta.get(i).type < 0) {
						// С��0���ǵз�̹��
						for (int j = 0; j < tankIcon.length; j++) {
							tankIcon[j] = new ImageIcon("images/enemytank"
									+ (-(ta.get(i).type)) + (j + 1) + ".png");
						}// ����ϴΰ������ĸ�����������ݷ����������ӡ��ͼƬ
						gr.drawImage(
								tankIcon[ta.get(i).direction - 1].getImage(),
								ta.get(i).x, ta.get(i).y, null);
					} else {

						// ����0 ���ϳ�
						ImageIcon image = new ImageIcon(
								"images/headquarters00.png");
						gr.drawImage(image.getImage(), ta.get(i).x,
								ta.get(i).y, null);
					}
				}

			}
			// ���ӵ�ͼƬ
			for (int i = 0; i < ba.size(); i++) {
				// �ж��ӵ��Ƿ�����
				if (ba.get(i).status == 0) {
					// �����ӵ��߳�
					ba.get(i).stopThread();
					// ������ըͼƬ
					ImageIcon explode = new ImageIcon(
							"images/explode_bulletmovie_frame01.png");
					// ����ըͼƬ����������
					gr.drawImage(explode.getImage(), ba.get(i).x, ba.get(i).y,
							null);
					ba.remove(i);// �����ٵ��ӵ����ӵ��������Ƴ�
				} else {
					// �����ӵ�����ͼƬ����
					ImageIcon bimg[] = new ImageIcon[4];
					for (int j = 0; j < 4; j++) {
						bimg[j] = new ImageIcon("images/bullet3" + j + ".png");
					}
					gr.drawImage(bimg[ba.get(i).direction].getImage(),
							ba.get(i).x, ba.get(i).y, null);
				}

			}
			// ��Ϸ��ͣ��������ͼƬ
			if (GameEngine.status == -1) {
				// ��Ϸ����
				// ������Ϸ������ͼƬ
				ImageIcon failImg = new ImageIcon("images/tankgameover.png");
				gr.drawImage(failImg.getImage(), 0, 0, null);// ������Ļ��ȥ

			} else if (GameEngine.status == 1) {
				// ��Ϸʤ��
				ImageIcon winImg = new ImageIcon("images/");
				// gr.drawImage(winImg.getImage(), 0, 0, null);
			}
			// 3�����⻭��������Ļ��ȥ
			g.drawImage(img, 0, 0, null);
		}
	}

	public void stopThread() {
		isStop = true;
	}

	public void suspendThread() {
		isSuspend = true;
	}

	public void run() {
		while (!isStop) {
			if (!isSuspend) {
				myPanel.repaint();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * ���̼�����
	 * 
	 * @author john
	 * 
	 */
	class ButtonListener extends KeyAdapter {

		Tank mt = GameEngine.playerTank;// ��ҿ��Ƶ�̹��

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				// �����ƶ�
				pressed = 2;
				isUp = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				// �����ƶ�
				pressed = 4;
				isDown = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				// �����ƶ�
				pressed = 1;
				isLeft = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				// �����ƶ�
				pressed = 3;
				isRight = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				isFire = true;// ���ӵ�
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				// �˳���Ϸ

			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// ��ͣ��Ϸ
			}
			mt.direction = pressed;// ̹�˴�ʱ���ٶȷ���
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				// �����ƶ�
				isUp = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				// �����ƶ�
				isDown = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				// �����ƶ�
				isLeft = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				// �����ƶ�
				isRight = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				isFire = false;
			}
		}
	}
}
