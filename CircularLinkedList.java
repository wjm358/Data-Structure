package data_structure;

public class CircularLinkedList<T> {
	LinkedNode<T> head;
	int count = 0;

	public CircularLinkedList() {
		head = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void insertFirst(T data) {
		LinkedNode<T> newNode = new LinkedNode<T>(data);
		if (isEmpty()) {
			head = newNode;
			head.setNextNode(head);
		} else {
			newNode.setNextNode(head.getNextNode());
			head.setNextNode(newNode);
		}
	}

	public void insertLast(T data) {
		LinkedNode<T> newNode = new LinkedNode<T>(data);
		if (isEmpty()) {
			head = newNode;
			head.setNextNode(head);
		} else {
			newNode.setNextNode(head.getNextNode());
			head.setNextNode(newNode);
			head = newNode;
		}
	}

	public void printList() {
		LinkedNode<T> temp = head.getNextNode();
		do {
			System.out.printf("%d -> ", temp.getData());
			temp = temp.getNextNode();
		} while (temp != head.getNextNode());
		System.out.println();

		/*
		 * LinkedNode<T> temp = head.getNextNode(); int index = count; while
		 * (index > 0) { System.out.println(temp.getData()); temp =
		 * temp.getNextNode(); index--; }
		 */
	}
	
	/*
	 * ���� : ������ ��� ����, ù ��° ��� ����, �߰� ��� ���� , 
	 * ��� ���� ���� , ��� Ž��  
	 * �߰� ��� ���� 
	 */
}
