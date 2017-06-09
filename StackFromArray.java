package DataStructure;

public class StackFromArray<T> {
	private int top;
	private Object stack[];
	private int size;

	// stack constructor
	public StackFromArray(int size) {
		top = -1;
		this.size = size;
		stack = new Object[size];
	}

	// print data
	public void printStack() {
		if (top == -1) {
			System.out.println("It's empty.");
			return;
		}
		for (int i = 0; i <= top; i++) {
			System.out.println(stack[i]);
		}
	}

	// insert data
	public void push(T data) {
		if (isFull()) {
			size *= 2;
			Object[] temp = stack.clone();
			stack = new Object[size];
			System.arraycopy(temp, 0, stack, 0, temp.length);
			stack[++top] = data;
		} else
			stack[++top] = data;
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public boolean isFull() {
		return (top == size - 1);
	}

	// search data
	public int search(T data) {
		int index = -1;
		for (int i = 0; i < stack.length; i++) {
			if (data.equals(stack[i])) {
				index = i + 1;
				break;
			}
		}
		return index;
	}

	// delete data
	public T pop() {
		if (isEmpty())
			return null;
		else
			return (T) stack[top--];
	}

	// bring in data of top
	public T peek() {
		if (isEmpty())
			return null;
		else
			return (T) stack[top];
	}

}
