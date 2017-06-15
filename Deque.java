package data_structure;

/*
 * ���� ���� ����Ʈ�� �̿��� �� ����
 */
public class Deque<T> {
	DoubleNode front;
	DoubleNode rear;
	int size;

	// �ʱ�ȭ ������
	public Deque() {
		front = rear = null;
		size = 0;
	}

	// ���� Ȯ��
	public boolean isEmpty() {
		return front == null;
	}

	// �տ� ��� ����
	public void addFront(T data) {
		DoubleNode newNode = new DoubleNode(data);
		if (isEmpty()) {
			front = rear = newNode;
		} else {
			newNode.setNext(front);
			front.setPrev(newNode);
			front = newNode;
		}
		size++;
	}

	// �ڿ� ��� ����
	public void addRear(T data) {
		DoubleNode newNode = new DoubleNode(data);
		if (isEmpty()) {
			front = rear = newNode;
		} else {
			newNode.setPrev(rear);
			rear.setNext(newNode);
			rear = newNode;
		}
		size++;
	}

	// �տ� ��� ����
	public T deleteFront() {
		if (isEmpty())
			return null;
		else {
			T data = front.getData();
			front = front.getNext();
			if (front == null) {
				front = rear = null;
			} else {
				front.setPrev(null);
			}
			size--;
			return data;
		}
	}

	// �ڿ� ��� ����
	public T deleteRear() {
		if (isEmpty())
			return null;
		else {
			T data = rear.getData();
			rear = rear.getPrev();
			if (rear == null) {
				front = rear = null;
			} else {
				rear.setNext(null);
			}
			size--;
			return data;
		}
	}

	//�տ� ��� ��ȯ
	public T peekFront() {
		if (front == null)
			return null;
		else
			return front.getData();
	}

	//�ڿ� ��� ��ȯ
	public T peekRear() {
		if (rear == null)
			return null;
		else
			return rear.getData();
	}

	//����Ʈ ���
	public void printDeque() {
		DoubleNode temp = front;
		for (int i = 0; i < size; i++) {
			System.out.print(temp.getData() + " -> ");
			temp = temp.getNext();
		}
		System.out.println();
	}

	//������ ���� Ȯ��
	public boolean contains(T data) {
		DoubleNode temp = front;
		while (temp != null) {
			if (temp.getData().equals(data))
				return true;
			temp = temp.getNext();
		}
		return false;
	}

	//������ ���
	public int getSize() {
		return size;
	}

	//���� ���� ����Ʈ ����
	private class DoubleNode {
		T data;
		DoubleNode next;
		DoubleNode prev;

		public DoubleNode(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public DoubleNode getNext() {
			return next;
		}

		public void setNext(DoubleNode next) {
			this.next = next;
		}

		public DoubleNode getPrev() {
			return prev;
		}

		public void setPrev(DoubleNode prev) {
			this.prev = prev;
		}

	}
}
