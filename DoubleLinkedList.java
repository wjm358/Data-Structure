package data_structure;

import java.util.NoSuchElementException;

/*
 * ���� ���� ����Ʈ�� �̿��� ���� ���� ����Ʈ
 */
public class DoubleLinkedList<T> {
	private DoubleNode headNode;
	private DoubleNode tailNode;
	private int size;

	// �ʱ�ȭ ������
	public DoubleLinkedList() {
		headNode = null;
		size = 0;
	}

	// ���� ���� Ȯ��
	public boolean isEmpty() {
		return headNode == null;
	}

	// ũ��
	public int getSize() {
		return size;
	}

	// �� �� ��� ����
	public void insertFirst(T data) {
		DoubleNode newNode = new DoubleNode(data);

		if (isEmpty()) {
			// �� ����Ʈ�� ��
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

	// �� �� ��� ����
	public T deleteFirst() {
		if (isEmpty()) {
			// �� ����Ʈ�� ��
			throw new NoSuchElementException("�� ����Ʈ");
		} else if (size == 1) {
			// ����Ʈ�� �ϳ��� ��常 ���� ��
			DoubleNode deleteNode = headNode;
			headNode = tailNode = null;
			size--;
			return deleteNode.getData();
		} else {
			// ����Ʈ�� �������� ��尡 ���� ��
			DoubleNode deleteNode = headNode;
			DoubleNode nextNode = headNode.getNext();
			nextNode.setPrev(tailNode);
			tailNode.setNext(nextNode);
			headNode = nextNode;
			size--;
			return deleteNode.getData();
		}

	}

	// �� ������ ��� ����
	public void insertLast(T data) {
		DoubleNode newNode = new DoubleNode(data);

		if (isEmpty()) {
			// �� ����Ʈ�� ��
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

	// �� ������ ��� ����
	public T deleteLast() {
		if (isEmpty()) {
			// �� ����Ʈ�� ��
			throw new NoSuchElementException("�� ����Ʈ");
		} else if (size == 1) {
			// ����Ʈ�� �ϳ��� ��常 ���� ��
			DoubleNode deleteNode = tailNode;
			headNode = tailNode = null;
			size--;
			return deleteNode.getData();
		} else {
			// ����Ʈ�� �������� ��尡 ���� ��
			DoubleNode deleteNode = tailNode;
			DoubleNode prevNode = tailNode.getPrev();
			prevNode.setNext(headNode);
			headNode.setPrev(prevNode);
			tailNode = prevNode;
			size--;
			return deleteNode.getData();
		}
	}

	// position�� ������ ����
	public void insertPos(int position, T data) {
		DoubleNode prevNode;
		DoubleNode posNode;
		DoubleNode newNode;

		if (position > size) {
			throw new NullPointerException("��� ����");
		} else if (position == 0) {
			// �� �տ� �߰��ϴ� ���̹Ƿ� insertFrist �޼ҵ� ȣ��
			insertFirst(data);
		} else if (position == size) {
			// �� �������� �߰��ϴ� ���̹Ƿ� insertLast �޼ҵ� ȣ��
			insertLast(data);
		} else {
			// position ��ġ�� ���ο� ��带 �߰��ϰ�, ���� position ��ġ�� �ִ� ���� �ڷ� �Ű�����
			prevNode = searchNode(position - 1); // �ε��� ��ġ ���� ��带 ã�ƿ�
			posNode = prevNode.getNext(); // �ε��� ��ġ�� ��带 ã�ƿ�
			newNode = new DoubleNode(data);
			prevNode.setNext(newNode);
			newNode.setNext(posNode);
			posNode.setPrev(newNode);
			newNode.setPrev(prevNode);
			size++;
		}

	}

	// position�� �����ϴ� ��� ����
	public T deletePos(int position) {
		DoubleNode prevNode;
		DoubleNode posNode;

		if (position >= size) {
			// position�� size���� ���ų� ũ�� ���� ��带 �����ϴ� ���̹Ƿ�
			throw new NullPointerException("��� ����");
		} else if (position == 0)
			// �� �տ� ��带 �����ϹǷ� deleteFirst() ȣ��
			return deleteFirst();
		else if (position == size - 1) {
			// �� �� ��带 �����ϹǷ� deleteLast() ȣ��
			return deleteLast();
		} else {
			// position ��ġ�� �ִ� ��带 �����ϰ� position �տ� �ִ� ���� �ڿ��ִ� ��带 ����
			prevNode = searchNode(position - 1);
			posNode = prevNode.getNext();
			prevNode.setNext(posNode.getNext());
			posNode.getNext().setPrev(prevNode);
			size--;
			return posNode.getData();
		}

	}

	// �����Ϳ� �������� ���� ����� �ε��� ���
	public int searchData(T data) {
		int index=0;
		if (size == 0)
			//�� ����Ʈ
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

	// position��ġ�� ��忡�� ���� tail �������� �Ѿ�鼭 ����Ʈ ���� ��� ���
	public void printList(int position) {

		DoubleNode temp = searchNode(position);
		int count = size;
		if (isEmpty())
			throw new NoSuchElementException("�� ����Ʈ");
		else if (temp == null) {
			//�ش� ��ġ�� ��尡 �����Ƿ�
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

	// �ε��� ��ġ�� �ִ� ��� ��ȯ
	public DoubleNode searchNode(int index) {
		DoubleNode searchNode;

		if (index >= size) {
			// index�� size���� ���ų� ũ�� ��带 ã�� �� �����Ƿ�
			throw new NullPointerException();
		} else if (index < size / 2) {
			// index�� size/2 ���� ������ headNode�� �� �����Ƿ�
			searchNode = headNode;
			for (int i = 0; i < index; i++) {
				searchNode = searchNode.getNext();
			}
			return searchNode;
		} else {
			// index�� size/2 ���� ũ�� tailNode�� �� �����Ƿ�
			searchNode = tailNode;
			for (int i = size - 1; i > index; i--) {
				searchNode = searchNode.getPrev();
			}
			return searchNode;
		}
	}

	// DoubleNode ����
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
