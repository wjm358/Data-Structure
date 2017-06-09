package DataStructure;

public class StackNode<T> {
	private T data;
	private StackNode<T> nextNode;

	public StackNode(T data) {
		this.data = data;
		nextNode = null;
	}

	public StackNode() {

	}

	public T getData() {
		return data;
	}

	public StackNode<T> getNextNode() {
		return nextNode;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setNextNode(StackNode<T> nextNode) {
		this.nextNode = nextNode;
	}
}
