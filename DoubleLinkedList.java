package data_structure;

import java.util.NoSuchElementException;

/*
 * 원형 연결 리스트를 이용한 이중 연결 리스트
 */
public class DoubleLinkedList<T> {
	private DoubleNode headNode;
	private DoubleNode tailNode;
	private int size;

	// 초기화 생성자
	public DoubleLinkedList() {
		headNode = null;
		size = 0;
	}

	// 공백 상태 확인
	public boolean isEmpty() {
		return headNode == null;
	}

	// 크기
	public int getSize() {
		return size;
	}

	// 맨 앞 노드 삽입
	public void insertFirst(T data) {
		DoubleNode newNode = new DoubleNode(data);

		if (isEmpty()) {
			// 빈 리스트일 때
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			headNode = tailNode = newNode;
		} else {
			newNode.setPrev(tailNode);
			tailNode.setNext(newNode);
			newNode.setNext(headNode);
			headNode.setPrev(newNode);
			headNode = newNode;
		}
		size++;
	}

	// 맨 앞 노드 삭제
	public T deleteFirst() {
		if (isEmpty()) {
			// 빈 리스트일 때
			throw new NoSuchElementException("빈 리스트");
		} else if (size == 1) {
			// 리스트에 하나의 노드만 있을 때
			DoubleNode deleteNode = headNode;
			headNode = tailNode = null;
			size--;
			return deleteNode.getData();
		} else {
			// 리스트에 여러개의 노드가 있을 때
			DoubleNode deleteNode = headNode;
			DoubleNode nextNode = headNode.getNext();
			nextNode.setPrev(tailNode);
			tailNode.setNext(nextNode);
			headNode = nextNode;
			size--;
			return deleteNode.getData();
		}

	}

	// 맨 마지막 노드 삽입
	public void insertLast(T data) {
		DoubleNode newNode = new DoubleNode(data);

		if (isEmpty()) {
			// 빈 리스트일 때
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			headNode = tailNode = newNode;
		} else {
			newNode.setNext(headNode);
			headNode.setPrev(newNode);
			newNode.setPrev(tailNode);
			tailNode.setNext(newNode);
			tailNode = newNode;
		}
		size++;
	}

	// 맨 마지막 노드 삭제
	public T deleteLast() {
		if (isEmpty()) {
			// 빈 리스트일 때
			throw new NoSuchElementException("빈 리스트");
		} else if (size == 1) {
			// 리스트에 하나의 노드만 있을 때
			DoubleNode deleteNode = tailNode;
			headNode = tailNode = null;
			size--;
			return deleteNode.getData();
		} else {
			// 리스트에 여러개의 노드가 있을 때
			DoubleNode deleteNode = tailNode;
			DoubleNode prevNode = tailNode.getPrev();
			prevNode.setNext(headNode);
			headNode.setPrev(prevNode);
			tailNode = prevNode;
			size--;
			return deleteNode.getData();
		}
	}

	// position에 데이터 삽입
	public void insertPos(int position, T data) {
		DoubleNode prevNode;
		DoubleNode posNode;
		DoubleNode newNode;

		if (position > size) {
			throw new NullPointerException("노드 없음");
		} else if (position == 0) {
			// 맨 앞에 추가하는 것이므로 insertFrist 메소드 호출
			insertFirst(data);
		} else if (position == size) {
			// 맨 마지막에 추가하는 것이므로 insertLast 메소드 호출
			insertLast(data);
		} else {
			// position 위치에 새로운 노드를 추가하고, 원래 position 위치에 있던 노드는 뒤로 옮겨진다
			prevNode = searchNode(position - 1); // 인덱스 위치 전의 노드를 찾아옴
			posNode = prevNode.getNext(); // 인덱스 위치의 노드를 찾아옴
			newNode = new DoubleNode(data);
			prevNode.setNext(newNode);
			newNode.setNext(posNode);
			posNode.setPrev(newNode);
			newNode.setPrev(prevNode);
			size++;
		}

	}

	// position에 존재하는 노드 삭제
	public T deletePos(int position) {
		DoubleNode prevNode;
		DoubleNode posNode;

		if (position >= size) {
			// position이 size보다 같거나 크면 없는 노드를 삭제하는 것이므로
			throw new NullPointerException("노드 없음");
		} else if (position == 0)
			// 맨 앞에 노드를 삭제하므로 deleteFirst() 호출
			return deleteFirst();
		else if (position == size - 1) {
			// 맨 뒤 노드를 삭제하므로 deleteLast() 호출
			return deleteLast();
		} else {
			// position 위치에 있는 노드를 삭제하고 position 앞에 있는 노드와 뒤에있는 노드를 연결
			prevNode = searchNode(position - 1);
			posNode = prevNode.getNext();
			prevNode.setNext(posNode.getNext());
			posNode.getNext().setPrev(prevNode);
			size--;
			return posNode.getData();
		}

	}

	// 데이터와 같은값을 가진 노드의 인덱스 출력
	public int searchData(T data) {
		int index=0;
		if (size == 0)
			//빈 리스트
			return -1;
		else {
			DoubleNode temp = headNode;
			while(index <size){
				if(data.equals(temp.getData()))
					return index;
				temp = temp.getNext();
				index++;
			}
		}
		return -1;
	}

	// position위치의 노드에서 부터 tail 방향으로 넘어가면서 리스트 내부 노드 출력
	public void printList(int position) {

		DoubleNode temp = searchNode(position);
		int count = size;
		if (isEmpty())
			throw new NoSuchElementException("빈 리스트");
		else if (temp == null) {
			//해당 위치에 노드가 없으므로
			throw new NullPointerException();
		} else {
			while (count > 0) {
				System.out.print(temp.getData() + " --> ");
				temp = temp.getNext();
				count--;
			}
			System.out.println();
		}
	}

	// 인덱스 위치에 있는 노드 반환
	public DoubleNode searchNode(int index) {
		DoubleNode searchNode;

		if (index >= size) {
			// index가 size보다 같거나 크면 노드를 찾을 수 없으므로
			throw new NullPointerException();
		} else if (index < size / 2) {
			// index가 size/2 보다 작으면 headNode에 더 가까우므로
			searchNode = headNode;
			for (int i = 0; i < index; i++) {
				searchNode = searchNode.getNext();
			}
			return searchNode;
		} else {
			// index가 size/2 보다 크면 tailNode에 더 가까우므로
			searchNode = tailNode;
			for (int i = size - 1; i > index; i--) {
				searchNode = searchNode.getPrev();
			}
			return searchNode;
		}
	}

	// DoubleNode 구현
	private class DoubleNode {
		private T data;
		private DoubleNode prev;
		private DoubleNode next;

		public DoubleNode(T data) {
			this.data = data;
			prev = null;
			next = null;

		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public DoubleNode getPrev() {
			return prev;
		}

		public void setPrev(DoubleNode prev) {
			this.prev = prev;
		}

		public DoubleNode getNext() {
			return next;
		}

		public void setNext(DoubleNode next) {
			this.next = next;
		}

	}
}
