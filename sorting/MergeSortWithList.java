package data_structure;

import java.util.Random;


class Node {
	int value;
	Node next;

	public Node(int value) {
		this.value = value;
		next = null;
	}
}

//����Ʈ�� �պ����� ����, �迭�� ������ �պ����ĺ��� �޸� ����
public class MergeSortWithList {
	Node head;
	int count = 0;

	//����Ʈ�� �� �߰�
	public void add(int value) {
		Node newNode = new Node(value);
		if (head == null) {
			head = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		
	}

	//����Ʈ ���
	public void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.printf("%d ", temp.value);
			temp = temp.next;
		}
		System.out.println();
	}

	//���� ���� ������ ��带 ��ħ
	public Node sortMerge(Node start, Node end) {

		Node result = null;

		if (start == null)
			return end;
		if (end == null)
			return start;

		if (start.value <= end.value) {
			result = start;
			result.next = sortMerge(start.next, end);
		} else {
			result = end;
			result.next = sortMerge(start, end.next);
		}
		return result;
	}

	//�ش� ����� ���� ��ȯ
	public int getLength(Node head) {
		int count = 0;
		Node temp = head;
		while (temp != null) {
			count++;
			temp = temp.next;

		}
		return count;
	}

	public Node mergeSort(Node head) {
		int mid = getLength(head) / 2;

		//��尡 �ϳ��� ��������� 
		if (head.next == null)
			return head;

		Node startLeft = head;

		while (mid - 1 > 0) {
			startLeft = startLeft.next;
			mid--;
		}

		Node startRight = startLeft.next;
		startLeft.next = null;
		startLeft = head;

		Node left = mergeSort(startLeft);
		Node right = mergeSort(startRight);

		Node sortedList = sortMerge(left, right);
		return sortedList;

	}

	public static void main(String[] args) {
		MergeSortWithList list = new MergeSortWithList();

		Random random = new Random();

		for (int i = 0; i < 50; i++) {
			list.add(random.nextInt(100));
		}
		
		list.printList();
		list.head = list.mergeSort(list.head);
		list.printList();
	}

}
