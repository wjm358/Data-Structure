package data_structure;

public class LinkedNode<T> {
	private T data;
	private LinkedNode<T> nextNode;
	
	public LinkedNode(T data) {
		this.data = data;
		nextNode = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LinkedNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(LinkedNode<T> nextNode) {
		this.nextNode = nextNode;
	}
	
	

}
