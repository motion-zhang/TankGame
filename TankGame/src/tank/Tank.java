package tank;

import java.util.Vector;

import engine.GameEngine;
import bullet.Bullet;
import UI.GameStartUI;

public class Tank implements ITank {

	private boolean alive = false; // Check whether tank alive
	private Steering vel = new Steering(); // velocity according to steering behavior


	public int type;// ̹������ 0:�ϳ� ����0�����̹�� С��0���з�̹�ˣ����ԣ�
	public int x, y;// ̹�˵ĵ�ǰλ��
	public int size = 16;// ̹�˵Ĵ�С
	public int vup = 0;// ̹�˵�ǰ������ʻ�ٶ�
	public int vdown = 0;// ̹�˵�ǰ���µ��ٶ�
	public int vleft = 0;// ��ǰ ���ٶ�
	public int vright = 0;// ��ǰ ���ٶ�
	public int v = 3;// û���谭��ʱ���ٶ�
	public int status = 1;// ̹�˵�ǰ��״̬ 0������ 1:���
	public int direction;// ̹�˵�ǰǰ������
	public boolean enemyFire = false;// �з�̹���ܷ񿪻�
	public boolean isStop;
	public boolean isSuspend;

	/**
	 * ̹���ƶ��ķ���(�������̹��)
	 */
	public void playerMove() {
		if (GameStartUI.isUp) {
			y -= vup;
		} else if (GameStartUI.isDown) {
			y += vdown;
		} else if (GameStartUI.isLeft) {
			x -= vleft;
		} else if (GameStartUI.isRight) {
			x += vright;
		}
	}

	/**
	 * �з�̹���ƶ�
	 */
	public void enemyMove() {
		switch (direction) {
		case 1:
			y -= vup;
			break;
		case 2:
			x -= vleft;
			break;
		case 3:
			y += vdown;
			break;
		case 4:
			x += vright;
		}
	}

	// ̹�˿�ǹ
	public void fire() {
		// �����ӵ��߳�
		Bullet bullet = new Bullet(type, x + 1, y + 1);
		bullet.direction = direction - 1;// ����ӵ�����
		// �ӵ��ٶ�
		switch (direction) {
		case 1:
			bullet.vx = -bullet.v;
			break;
		case 2:
			bullet.vy = -bullet.v;
			break;
		case 3:
			bullet.vx = bullet.v;
			break;
		case 4:
			bullet.vy = bullet.v;
		}
		bullet.start();// �����ӵ��߳�
		GameEngine.bulletArray.add(bullet);// ����ӵ����ӵ�����
	}

	/**
	 * ̹�˴���
	 */
	public void tankCollision() {
		// ̹�˺�̹�˵Ĵ���
		Vector<Tank> Array = GameEngine.tankArray;
		for (int i = 0; i < Array.size(); i++) {
			if (this != Array.get(i)) {
				if (this.x + this.size > Array.get(i).x
						&& this.x < Array.get(i).x + Array.get(i).size
						&& this.y + this.size > Array.get(i).y
						&& this.y < Array.get(i).y + Array.get(i).size) {
					switch (this.direction) {
					case 1:// ��tank�������󣬼���tank������̹�˵��ұ�
						this.x = Array.get(i).x + Array.get(i).size;
						this.vleft = 0;
						break;
					case 2:// ��������
						this.y = Array.get(i).y + Array.get(i).size;
						this.vup = 0;
						break;
					case 3:// ��������
						this.x = Array.get(i).x - this.size;
						this.vright = 0;
						break;
					case 4:// ��������
						this.y = Array.get(i).y - this.size;
						this.vdown = 0;
					}
				} else {
					if (this.x + this.size == Array.get(i).x
							&& this.y + this.size > Array.get(i).y
							&& this.y < Array.get(i).y + Array.get(i).size) {

						// ��̹�����ҽ�����������̹��
						this.vleft = this.v;
						this.vup = this.v;
						this.vright = 0;
						this.vdown = this.v;
					} else if (this.x == Array.get(i).x + Array.get(i).size
							&& this.y + this.size > Array.get(i).y
							&& this.y < Array.get(i).y + Array.get(i).size) {

						// ��̹�����������������̹��
						this.vleft = 0;
						this.vup = this.vright = this.vdown = this.v;
					} else if (this.y + this.size == Array.get(i).y
							&& this.x + this.size > Array.get(i).x
							&& this.x < Array.get(i).x + Array.get(i).size) {

						// ��̹�����½�����������̹��
						this.vdown = 0;
						this.vleft = this.vup = this.vright = this.v;
					} else if (this.y == Array.get(i).y + Array.get(i).size
							&& this.x + this.size > Array.get(i).x
							&& this.x < Array.get(i).x + Array.get(i).size) {

						// ��̹�����Ͻ�����������̹��
						this.vup = 0;
						this.vleft = this.vright = this.vdown = this.v;
					} else {

						this.vleft = this.vup = this.vright = this.vdown = this.v;
					}
				}
			}
		}
		// ��Ե����
		if (this.x <= 0) {
			this.x = 0;
			this.vleft = 0;
		} else if (this.x + this.size >= 240) {
			this.x = 240 - this.size;
			this.vright = 0;
		} else if (this.x > 0 && this.x + size < 240) {
			if (this.vleft == 0) {
				this.vleft = this.v;
			} else if (this.vright == 0) {
				this.vright = this.v;
			}
		}

		if (this.y <= 0) {
			this.y = 0;
			this.vup = 0;
		} else if (this.y + size >= 320) {
			this.y = 320 - size;
			this.vdown = 0;
		} else if (this.y > 0 && this.y + size < 320) {
			if (this.vdown == 0) {
				this.vdown = this.v;
			} else if (this.vup == 0) {
				this.vup = this.v;
			}
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

	/**
	 * �߳����еķ���
	 */
	public void run() {
		boolean iscd = true;// ���ӵ��Ƿ���ȴ
		long fireTime = 0;// ���ӵ���һ�̵�ʱ��
		boolean cd = true;// �з�̹���ӵ���ȴʱ��
		long time = 0;// �з�̹�˷��ӵ�����һ��ʱ��
		while (!isStop) {
			if (!isSuspend) {
				tankCollision();
				if (type > 0) {
					playerMove();
				} else if (type < 0) {
					enemyMove();
					System.out.println("move");
				}
				if (GameStartUI.isFire && type > 0) {

					if (iscd) {// �����ȴʱ�䵽�˾Ϳ������·��ӵ���
						fire();
						iscd = false;
						fireTime = System.currentTimeMillis();// �õ���ǰʱ���
					} else if (System.currentTimeMillis() - fireTime > 500) {
						// ���ӵ�ʱ����Ϊһ��
						iscd = true;
					}
				} else if (type < 0 && enemyFire) {
					System.out.println("fire");
					if (cd) {
						fire();
						cd = false;
						time = System.currentTimeMillis();
					} else if (System.currentTimeMillis() - time > 200) {
						cd = true;
					}
				}
			}
			try {
				Thread.sleep(60);
			} catch (Exception ef) {
				ef.printStackTrace();
			}
		}
	}
}
