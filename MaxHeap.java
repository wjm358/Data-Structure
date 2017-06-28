package data_structure;

import java.util.Random;

//�ִ� ���� ����
public class MaxHeap {
	private int heap[];
	private int maxSize;
	private int heapSize;

	// �ʱ�ȭ ������
	public MaxHeap(int size) {
		heap = new int[size + 1];
		maxSize = size;
		heapSize = 0;
	}

	// ������ ����
	public void insert(int data) {
		heap[++heapSize] = data;
		int pos = heapSize;

		while (pos != 1 && heap[pos] > heap[pos / 2]) {
			swap(pos / 2, pos);
			pos /= 2;
		}
	}

	// ������ ����
	public int delete() {
		if (heapSize == 0) //�����Ͱ� ������ -1 ����
			return -1;

		int data = heap[1];
		heap[1] = heap[heapSize--];

		int start = 1;
		int end = 2;
		while (end <= heapSize) {
			if (end < heapSize && (heap[end] < heap[end + 1]))
				end++;
			if (heap[end] <= heap[start])
				break;
			swap(start, end);
			start = end;
			end *= 2;
		}
		return data;
	}

	// �ڽĳ��� �θ��� ������ ����
	public void swap(int parent, int current) {
		int temp = heap[parent];
		heap[parent] = heap[current];
		heap[current] = temp;
	}

	// �� ����
	public void heapSort(int[] arr) {
		MaxHeap temp = new MaxHeap(arr.length);
		for (int i = 0; i < arr.length; i++) {
			temp.insert(arr[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = temp.delete(); // maxHeap�̹Ƿ� ������������ ���� ��
		}
	}

	// heap ������ ���
	public void printHeap() {
		if (heapSize == 0) // �����Ͱ� ���� ��
			return;
		else if (heapSize == 1) { 
			// �����Ͱ� �ϳ��� ���� ��
			System.out.println("parent : " + heap[1]);
		} else if (heapSize == 2) {
			// �����Ͱ� �ΰ� ���� ��
			System.out.println("parent : " + heap[1] + " left child : " + heap[2]);
		} else { 
			// ������
			for (int i = 1; i <= heapSize / 2; i++) {
				System.out.println(
						"parent : " + heap[i] + " left child : " + heap[2 * i] + " right child : " + heap[2 * i + 1]);
			}
		}
	}

	public static void main(String[] args) {
		MaxHeap heap = new MaxHeap(10);

		heap.insert(5);
		heap.printHeap();
		heap.insert(3);
		heap.printHeap();
		heap.insert(17);
		heap.insert(10);
		heap.insert(84);
		heap.insert(19);
		heap.insert(6);
		heap.insert(22);
		heap.insert(9);
		heap.delete();
		heap.printHeap();
		int[] arr = new int[10];
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(100); // 0-99 ���� ������
		}

		System.out.println("���� �� ");

		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%d : ", arr[i]);
		}
		System.out.println();

		heap.heapSort(arr);
		System.out.println("���� ��");
		;
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%d : ", arr[i]);
		}
	}
}
