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

//리스트로 합병정렬 구현, 배열로 구현한 합병정렬보다 메모리 절약
public class MergeSortWithList {
	Node head;
	int count = 0;

	//리스트에 값 추가
	public void add(int value) {
		Node newNode = new Node(value);
		if (head == null) {
			head = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		
	}

	//리스트 출력
	public void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.printf("%d ", temp.value);
			temp = temp.next;
		}
		System.out.println();
	}

	//왼쪽 노드와 오른쪽 노드를 합침
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

	//해당 노드의 길이 반환
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

		//노드가 하나만 남았을경우 
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
