package data_structure;

public class QueueFromList<T> {
	QueueNode<T> front, rear;

	public QueueFromList() {
		front = null;
		rear = null;
	}

	public boolean isEmpty() {
		return front == null;
	}

	public boolean isRearEmpty() {
		return rear == null;
	}

	public void enQueue(T data) {
		QueueNode<T> newNode = new QueueNode<T>(data);
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.setNextNode(newNode);
			rear = newNode;
		}
	}

	public T deQueue() {
		T data = front.getData();
		front = front.getNextNode();
		if (front == null)
			rear = null;
		return data;
	}

	public T peek() {
		if (isEmpty())
			return null;
		else
			return front.getData();
	}

	public int search(T data) {
		QueueNode<T> temp = front;
		int index = 1;
		while (temp != null) {
			if (data.equals(temp.getData()))
				return index;
			temp = temp.getNextNode();
		}
		return -1;
	}

	public void printQueue() {
		QueueNode<T> temp = front;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNextNode();
		}
	}
}
