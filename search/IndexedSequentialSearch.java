package data_structure;

import java.util.Arrays;
import java.util.Random;


//�ε��� ���̺� ����, key�� ���� ������������ ����
class IndexTable implements Comparable<IndexTable> {
	int key;
	int index;

	@Override
	public int compareTo(IndexTable o) {
		if (this.key < o.key)
			return -1;
		else if (this.key > o.key)
			return 1;
		return 0;
	}

}

public class IndexedSequentialSearch {

	public static void main(String[] args) {
		int dataTable[] = new int[50];
		Random random = new Random();

		// �ε��� ���̺��� ũ�Ⱑ �����ϸ� �ε��� ���̺��� Ž���ð��� ����
		IndexTable[] indexTable = new IndexTable[5];
		initIndexTable(indexTable);

		// ������ ���̺� �������� �� ����
		for (int i = 0; i < dataTable.length; i++) {
			dataTable[i] = random.nextInt(50);
		}
		// ������ ���̺� ����
		Arrays.sort(dataTable);

		addIndexTable(dataTable, indexTable);

		// ã������ �� �������� ����
		int key = random.nextInt(50);

		printArray(dataTable);
		printArray(indexTable);
		System.out.println("ã������ �� : " + key);

		int search = indexedSequentialSearch(dataTable, indexTable, key);
		if (search != -1)
			System.out.println(key + " �� " + (search + 1) + "��°�� ��ġ");
		else
			System.out.println("ã������ ���� ����");
	}

	// ���� ���� Ž��
	public static int indexedSequentialSearch(int dataTable[], IndexTable[] indexTable, int key) {
		int i, start, end;

		// Ű ���� ������ ���̺� �������� ������ -1 ��ȯ
		if (key < dataTable[0] || key > dataTable[dataTable.length - 1])
			return -1;

		// �ε��� ���̺��� �����Ͽ� �ش� Ű�� ������ ����
		for (i = 0; i < indexTable.length - 1; i++)
			if (indexTable[i].key <= key && indexTable[i + 1].key > key)
				break;

		if (i == indexTable.length - 1) { // �ε��� ���̺��� ���� ������
			start = indexTable[i - 1].index;
			end = dataTable.length - 1;
		} else {
			start = indexTable[i].index;
			end = indexTable[i + 1].index - 1;
		}
		
		//������ ������ ����Ž��
		return sequentialSearch(dataTable, key, start, end);
	}

	// ������ ���������� ����Ž�� �ϱ� ���� ����Ž�� ����
	public static int sequentialSearch(int dataTable[], int key, int start, int end) {
		int index;
		dataTable[end] = key;
		for (index = start; dataTable[index] != key && index <= end; index++)
			;
		if (index == end)
			return -1;
		else
			return index;
	}

	// �ε��� ���̺� �ʱ�ȭ
	public static void initIndexTable(IndexTable[] indexTable) {
		for (int i = 0; i < indexTable.length; i++) {
			indexTable[i] = new IndexTable();
		}
	}

	// ������ ���̺��� ���� n �̰� �ε��� ���̺��� ���� m �϶� �� n/m�� °�� �����͵��� indexTable�� ����
	public static void addIndexTable(int dataTable[], IndexTable[] indexTable) {
		int section = dataTable.length / indexTable.length;
		int tableIndex = 0;

		for (int i = 0; i < indexTable.length; i++) {
			indexTable[i].key = dataTable[tableIndex];
			indexTable[i].index = tableIndex;
			tableIndex += section;
		}
	}

	// ������ ���̺� ���
	public static void printArray(int dataTable[]) {
		System.out.print("������ ���̺� ��� :   ");
		for (int index = 0; index < dataTable.length; index++) {
			System.out.print(dataTable[index] + " ");
		}
		System.out.println();
	}

	// �ε��� ���̺� ���
	public static void printArray(IndexTable[] indexTable) {
		System.out.print("�ε��� ���̺� ��� :   ");
		for (int i = 0; i < indexTable.length; i++) {
			System.out.printf("%d ", indexTable[i].key);
		}
		System.out.println();
	}
}
