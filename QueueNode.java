package data_structure;

public class QueueNode<T> {
	private T data;
	private QueueNode<T> nextNode;

	QueueNode(T data) {
		this.data = data;
		this.nextNode = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public QueueNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(QueueNode<T> nextNode) {
		this.nextNode = nextNode;
	}
	
	
}
