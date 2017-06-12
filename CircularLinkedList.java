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
	 * 구현 : 마지막 노드 삭제, 첫 번째 노드 삭제, 중간 노드 삭제 , 
	 * 노드 순서 반전 , 노드 탐색  
	 * 중간 노드 삽입 
	 */
}
