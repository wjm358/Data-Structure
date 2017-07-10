package data_structure;

import java.util.Arrays;
import java.util.Random;


//인덱스 테이블 구현, key에 대해 오름차순으로 정렬
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

		// 인덱스 테이블의 크기가 증가하면 인덱스 테이블의 탐색시간이 증가
		IndexTable[] indexTable = new IndexTable[5];
		initIndexTable(indexTable);

		// 데이터 테이블에 랜덤으로 값 삽입
		for (int i = 0; i < dataTable.length; i++) {
			dataTable[i] = random.nextInt(50);
		}
		// 데이터 테이블 정렬
		Arrays.sort(dataTable);

		addIndexTable(dataTable, indexTable);

		// 찾으려는 값 랜덤으로 지정
		int key = random.nextInt(50);

		printArray(dataTable);
		printArray(indexTable);
		System.out.println("찾으려는 값 : " + key);

		int search = indexedSequentialSearch(dataTable, indexTable, key);
		if (search != -1)
			System.out.println(key + " 는 " + (search + 1) + "번째에 위치");
		else
			System.out.println("찾으려는 값이 없음");
	}

	// 색인 순차 탐색
	public static int indexedSequentialSearch(int dataTable[], IndexTable[] indexTable, int key) {
		int i, start, end;

		// 키 값이 데이터 테이블에 존재하지 않으면 -1 반환
		if (key < dataTable[0] || key > dataTable[dataTable.length - 1])
			return -1;

		// 인덱스 테이블을 조사하여 해당 키의 구간을 결정
		for (i = 0; i < indexTable.length - 1; i++)
			if (indexTable[i].key <= key && indexTable[i + 1].key > key)
				break;

		if (i == indexTable.length - 1) { // 인덱스 테이블의 끝에 있으면
			start = indexTable[i - 1].index;
			end = dataTable.length - 1;
		} else {
			start = indexTable[i].index;
			end = indexTable[i + 1].index - 1;
		}
		
		//정해진 구간의 순차탐색
		return sequentialSearch(dataTable, key, start, end);
	}

	// 정해진 범위내에서 순차탐색 하기 위해 순차탐색 구현
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

	// 인덱스 테이블 초기화
	public static void initIndexTable(IndexTable[] indexTable) {
		for (int i = 0; i < indexTable.length; i++) {
			indexTable[i] = new IndexTable();
		}
	}

	// 데이터 테이블의 수가 n 이고 인덱스 테이블의 수가 m 일때 각 n/m번 째의 데이터들을 indexTable에 삽입
	public static void addIndexTable(int dataTable[], IndexTable[] indexTable) {
		int section = dataTable.length / indexTable.length;
		int tableIndex = 0;

		for (int i = 0; i < indexTable.length; i++) {
			indexTable[i].key = dataTable[tableIndex];
			indexTable[i].index = tableIndex;
			tableIndex += section;
		}
	}

	// 데이터 테이블 출력
	public static void printArray(int dataTable[]) {
		System.out.print("데이터 테이블 출력 :   ");
		for (int index = 0; index < dataTable.length; index++) {
			System.out.print(dataTable[index] + " ");
		}
		System.out.println();
	}

	// 인덱스 테이블 출력
	public static void printArray(IndexTable[] indexTable) {
		System.out.print("인덱스 테이블 출력 :   ");
		for (int i = 0; i < indexTable.length; i++) {
			System.out.printf("%d ", indexTable[i].key);
		}
		System.out.println();
	}
}
