package item;

public class DestroyableItem {
	public int x, y;// ���ƻ���Ʒ������
	public int size = 8; // ��С
	public int type;// ����
	public int status = 1;// Ŀǰ״̬

	public DestroyableItem(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
