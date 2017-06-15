package data_structure;

/*
 * 이중 연결 리스트를 이용한 덱 구현
 */
public class Deque<T> {
	DoubleNode front;
	DoubleNode rear;
	int size;

	// 초기화 생성자
	public Deque() {
		front = rear = null;
		size = 0;
	}

	// 공백 확인
	public boolean isEmpty() {
		return front == null;
	}

	// 앞에 노드 삽입
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

	// 뒤에 노드 삽입
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

	// 앞에 노드 삭제
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

	// 뒤에 노드 삭제
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

	//앞에 노드 반환
	public T peekFront() {
		if (front == null)
			return null;
		else
			return front.getData();
	}

	//뒤에 노드 반환
	public T peekRear() {
		if (rear == null)
			return null;
		else
			return rear.getData();
	}

	//리스트 출력
	public void printDeque() {
		DoubleNode temp = front;
		for (int i = 0; i < size; i++) {
			System.out.print(temp.getData() + " -> ");
			temp = temp.getNext();
		}
		System.out.println();
	}

	//데이터 유무 확인
	public boolean contains(T data) {
		DoubleNode temp = front;
		while (temp != null) {
			if (temp.getData().equals(data))
				return true;
			temp = temp.getNext();
		}
		return false;
	}

	//사이즈 출력
	public int getSize() {
		return size;
	}

	//이중 연결 리스트 구현
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
