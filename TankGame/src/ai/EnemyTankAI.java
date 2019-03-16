package ai;

import java.util.Random;

import tank.Tank;
import engine.GameEngine;

/**
 * ̹���˹�����
 * 
 * @author john
 * 
 */
public class EnemyTankAI extends Thread {
	public boolean isStop = false;
	public boolean isSuspend = false;
	public Tank t;

	public EnemyTankAI(Tank t) {
		this.t = t;
	}

	/**
	 * �������̹��
	 * 
	 * @param t
	 *            �ط�̹��
	 */
	public void atk() {

		if (GameEngine.playerTank.x >= t.x
				&& GameEngine.playerTank.x <= t.x + t.size) {
			if (t.y < GameEngine.playerTank.y) {
				t.direction = 4;
			} else {
				t.direction = 2;

			}
			t.enemyFire = true;
		} else if (GameEngine.playerTank.y >= t.y
				&& GameEngine.playerTank.y <= t.y + t.size) {
			if (t.x < GameEngine.playerTank.x) {
				t.direction = 3;

			} else {
				t.direction = 1;

			}
			t.enemyFire = true;
		}
		t.enemyFire = false;
	}

	/**
	 * �з�̹������
	 */
	public void randWay() {
		Random rand = new Random();
		t.direction = rand.nextInt(4) + 1;
	}

	public void randFire() {
		Random rand = new Random();
		int temp = rand.nextInt(2);
		if (temp == 0) {
			t.enemyFire = false;
		} else {
			t.enemyFire = true;
		}
	}

	public void run() {
		//atk();// �����̹��
		randWay();// �����������·
		//randFire();// �ҿ�ǹ
	}
}
