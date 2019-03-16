package engine;

import java.awt.Point;
import java.util.Vector;

import tank.Tank;
import ai.EnemyTankAI;
import bullet.Bullet;

public class GameEngine extends Thread {
	public static Vector<Tank> tankArray = new Vector<Tank>();
	public static Vector<Bullet> bulletArray = new Vector<Bullet>();
	public static boolean isStop = false;
	public static boolean isSuspend = false;
	public static int status;// ��Ϸ��ǰ״̬ 0:���ڽ���1:Ӯ�� -1������
	public static Tank playerTank;// ��ҿ��Ƶ�̹��
	public static Tank hdQuarter;// �ϳ�
	public static Point etp[] = new Point[6];

	/**
	 * ��Ϸ��ʼ
	 */
	public void gameStart() {
		status = 0;
		// �������̹��
		playerTank = new Tank();
		playerTank.type = 1;
		playerTank.x = 123;
		playerTank.y = 160;
		playerTank.start();
		tankArray.add(playerTank);
		// ���ϳ�
		hdQuarter = new Tank();
		hdQuarter.type = 0;
		// �ϳ�����ʼ����
		hdQuarter.x = 240 / 2 - 16 / 2;
		hdQuarter.y = 320 - 16;
		hdQuarter.start();// �����ϳ��߳�
		tankArray.add(hdQuarter);
		// �����з�̹�˳�����
		for (int i = 0; i < etp.length; i++) {
			etp[i] = new Point(i * 44 + 3, 0);
		}
		// �����ط�̹��
		creatETank();

	}

	/**
	 * �ط�̹�˵Ķ���
	 */
	public void action() {
		// �з�̹�˵Ķ���
		for (int i = 0; i < tankArray.size(); i++) {
			if (tankArray.get(i).type < 0) {
				EnemyTankAI ai = new EnemyTankAI(tankArray.get(i));
				ai.start();
			}
		}

	}

	/**
	 * ���ݵз�̹�˳����㴴���з�̹��
	 */
	public void creatETank() {

		// ���ݳ����㴴���з�̹��
		for (int i = -1; i > -7; i--) {
			boolean find = false;// ��¼����Ϊi �ĵз�̹���Ƿ��ҵ�
			for (int j = 0; j < tankArray.size(); j++) {
				if (tankArray.get(j).type == i) {
					find = true;
					break;
				}
			}
			if (find == false) {
				Tank t = new Tank();
				t.direction = 3;
				t.type = i;
				t.x = etp[-(i + 1)].x;
				t.y = etp[-(i + 1)].y;
				// ���Ѿ�����AI��Ӧ���ڴ������̣߳���AIѡ�����̹�˵���Ϊ
				t.start();
				tankArray.add(t);
			}
		}
	}

	/**
	 * ��Ϸ����
	 */
	public void gameOver() {
		if (hdQuarter.status == 0) {
			// �ϳ�������
			status = -1;
		}
		int count = 0;
		for (int i = 0; i < tankArray.size(); i++) {
			if (tankArray.get(i).type > 0) {
				count++;
				continue;
			}
		}
		if (count == 0) {
			status = -1;
			stopThread();
			tankArray.clear();
		}
	}

	/**
	 * ����
	 */
	public void win() {
		int count = 0;// ��¼�з�̹�˵�����
		for (int i = 0; i < tankArray.size(); i++) {
			if (tankArray.get(i).type < 0) {
				count++;
				continue;
			}
		}
		if (count == 0) {
			// ��Ϸʤ��
			status = 1;
			// stopThread();
			// tankArray.clear();
		}
	}

	public void stopThread() {
		isStop = true;
	}

	public void suspendThread() {
		isSuspend = true;
	}

	public void resumeThread() {
		isSuspend = false;
	}

	public void run() {
		boolean respawn = true;// ��̹��
		long t = 0;// ��¼̹�˸���ʱ��ʱ��
		while (!isStop) {
			if (!isSuspend) {
				gameOver();
				win();
				action();
				if (respawn) {
					creatETank();// ���з�̹���б����еģ����ϴӸ�����̹�˵Ļ�������������һ����
					respawn = false;
					t = System.currentTimeMillis();
				} else if (System.currentTimeMillis() - t > 5000) {
					// �ط�̹�˵ĸ���ʱ��Ϊ5��
					respawn = true;
				}

			}
			try {
				Thread.sleep(500);

			} catch (Exception ef) {
				ef.printStackTrace();
			}
		}
	}
}
