package bullet;

import java.util.Vector;

import engine.GameEngine;
import tank.Tank;

public class Bullet{
	public int v = 6;// �ٶ�
	public int direction;// ���� 1:�� 2���� 3���� 4����
	public int vx;// �ӵ���������ٶ�
	public int vy;// �ӵ���������ٶ�
	public int x, y;// ����
	public int type;// �ӵ�����
	public int power;// �ӵ�����
	public int status = 1;// �ӵ���ǰ��״̬ 0������ 1�����e
	public int size = 12;
	public boolean isStop = false;
	public boolean isSuspend = false;

	public void stopThread() {
		isStop = true;
	}

	public void suspendThread() {
		isSuspend = true;
	}

	public Bullet(int type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	/**
	 * �ӵ���̹��
	 */
	public void destoryTank() {
		Vector<Tank> ta = GameEngine.tankArray;
		for (int i = 0; i < ta.size(); i++) {
			if (ta.get(i).type != this.type) {// �Լ����ӵ����������Լ�
				if (this.x + this.size > ta.get(i).x
						&& this.x < ta.get(i).x + ta.get(i).size
						&& this.y + this.size > ta.get(i).y
						&& this.y < ta.get(i).y + ta.get(i).size) {
					this.status = 0;
					ta.get(i).status = 0;
				}

			}
		}
	}

	/**
	 * �ӵ����ӵ�
	 */
	public void destoryBullet() {
		Vector<Bullet> ba = GameEngine.bulletArray;
		for (int i = 0; i < ba.size(); i++) {
			if (ba.get(i) != this) {
				if (Math.max(ba.get(i).type, 0) != Math.max(this.type, 0)) {
					if (this.x + this.size > ba.get(i).x
							&& this.x < ba.get(i).x + ba.get(i).size
							&& this.y + this.size > ba.get(i).y
							&& this.y < ba.get(i).y + ba.get(i).size) {
						this.status = 0;
						ba.get(i).status = 0;
					}
				}
			}
		}
	}

	/**
	 * �ӵ�����
	 */
	public void bulletCollision() {
		if (this.x <= 0 || this.x + this.size >= 240 || this.y <= 0
				|| this.y + this.size >= 320) {
			this.status = 0;
		}
	}

	public void run() {
		while (!isStop) {
			if (!isSuspend) {
				destoryTank();
				destoryBullet();
				bulletCollision();
				this.x += this.vx;
				this.y += this.vy;
			}
			try {
				Thread.sleep(60);
			} catch (Exception ef) {
				ef.printStackTrace();
			}
		}
	}
}
