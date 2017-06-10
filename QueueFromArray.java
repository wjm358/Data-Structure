package data_structure;

public class QueueFromArray<T> {
	private int rear, front;
	private int size;
	private int count;
	private Object[] queue;

	public QueueFromArray(int size) {
		this.size = size;
		count = 0;
		rear = 0;
		front = 0;
		queue = new Object[size];
	}

	public boolean isEmpty() {
		return front == rear;
	}

	public boolean isFull() {
		return (rear + 1) % size == front;
	}

	public void enQueue(T data) {
		if (isFull())
			System.out.println("Full Queue");
		else {
			rear = (rear + 1) % size;
			queue[rear] = data;
		}
	}

	public T deQueue() {
		if (isEmpty())
			return null;
		else {
			return (T) queue[++front];
		}
	}

	public T peek() {
		if (isEmpty())
			return null;
		else
			return (T) queue[front];
	}

	public int search(T data) {
		int count = 1;
		for (int i = front+1; i <= rear; i++) {
			if (queue[i].equals(data)) {
				return count;
			}
			count++;
		}
		return -1;
	}

	public void printQueue() {
		for(int i=front+1; i<=rear; i++) {
			System.out.println(queue[i]);
		}
	}
}
