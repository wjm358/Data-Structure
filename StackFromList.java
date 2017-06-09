package DataStructure;

public class StackFromList<T> {
	StackNode<T> top;

	public StackFromList() {
		top = null;
	}

	public boolean isEmpty() {
		return (top == null);
	}

	public void push(T data) {
		StackNode<T> newNode = new StackNode<T>(data);
		newNode.setNextNode(top);
		top = newNode;
	}

	public T peek() {
		if (isEmpty())
			return null;
		else
			return top.getData();
	}

	public T pop() {
		if (isEmpty())
			return null;
		else {
			T data = peek();
			top = top.getNextNode();
			return data;
		}
	}

	public int search(T data) {
		StackNode<T> temp = top;
		int index = 1;

		while (temp != null) {
			index++;
			if (temp.getData().equals(data))
				break;
			else {
				temp = temp.getNextNode();
			}
		}
		return index;
	}

	public void printStack() {
		StackNode<T> temp = top;

		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNextNode();
		}
	}

}
