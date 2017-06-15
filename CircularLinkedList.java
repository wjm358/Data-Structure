package data_structure;

/*
 * 원형 변형 노드를 이용, 헤드는 리스트의 맨 끝 노드를 가르킴.
 * 삽입 속도 증가
 */
public class CircularLinkedList<T> {
	LinkedNode<T> head;
	int count = 0;

	// 초기화
	public CircularLinkedList() {
		head = null;
	}

	// head 공백 상태 확인
	public boolean isEmpty() {
		return head == null;
	}

	// 맨 앞에 노드 추가
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

	// 맨 뒤에 노드 추가
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

	// 원하는 위치에 데이터를 삽입, pos는 0이 아닌 1부터 시작
	public void insertPos(int pos, T data) {
		LinkedNode<T> newNode = new LinkedNode<T>(data);
		LinkedNode<T> preNode = nodeFromPos(pos - 1);

		if (isEmpty())
			System.out.println("빈 리스트 입니다");
		else if (preNode == null)
			System.out.println("이전 인덱스에 값이 넣어져 있지 않습니다.");
		else {
			newNode.setNextNode(preNode.getNextNode());
			preNode.setNextNode(newNode);
		}
	}

	// 맨 앞 노드 삭제
	public T deleteFirst() {
		if (isEmpty())
			return null;
		else {
			LinkedNode<T> temp = head.getNextNode();
			head.setNextNode(temp.getNextNode());
			return temp.getData();
		}
	}

	// 원하는 위치의 노드 삭제, pos는 0이 아닌 1부터 시작
	public T deletePos(int pos) {
		int position = 1;
		LinkedNode<T> temp = null;
		LinkedNode<T> tempHead = head.getNextNode();
		while (position < pos) {
			if (position == pos - 1) {
				temp = tempHead;
			}
			tempHead = tempHead.getNextNode();
			position++;
		}
		temp.setNextNode(tempHead.getNextNode());
		return tempHead.getData();
	}

	// 리스트를 거꾸로 변경
	public void reverse() {
		LinkedNode<T> p, q, r;
		if (head == null || head.getNextNode() == head)
			return;
		p = head.getNextNode();
		q = head;
		int count = 0;
		while (p != head) {
			r = q;
			q = p;
			p = p.getNextNode();
			q.setNextNode(r);
		}
		head = head.getNextNode();
		p.setNextNode(q);

	}

	// 원하는 위치의 데이터 반환. pos는 0이아닌 1부터 시작
	public T dataFromPos(int pos) {
		LinkedNode<T> temp = head.getNextNode();
		int position = 1;
		while (position < pos) {
			temp = temp.getNextNode();
			position++;
		}
		return temp.getData();
	}

	// 원하는 위치의 노드 반환
	public LinkedNode<T> nodeFromPos(int pos) {
		LinkedNode<T> temp = head.getNextNode();
		int position = 1;
		while (position < pos) {
			temp = temp.getNextNode();
			position++;
		}
		return temp;
	}

	// 해당하는 데이터를 가진 노드의 인덱스 반환
	public int indexFromData(T data) {
		int position = 1;
		LinkedNode<T> temp = head.getNextNode();
		do {
			if (data.equals(temp.getData()))
				return position;
			position++;
			temp = temp.getNextNode();
		} while (temp != head.getNextNode());
		return -1;
	}

	// 리스트 데이터 출력
	public void printList() {
		LinkedNode<T> temp = head.getNextNode();
		do {
			System.out.print(temp.getData() + " - > ");
			temp = temp.getNextNode();
		} while (temp != head.getNextNode());
		System.out.println();
	}
}