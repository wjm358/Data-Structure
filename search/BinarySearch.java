package data_structure;

import java.util.Arrays;
import java.util.Random;

//이진 탐색 구현
public class BinarySearch {

	public static void main(String[] args) {
		int arr[] = new int[50];
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(25);
		}

		printArray(arr);
		Arrays.sort(arr);
		printArray(arr);

		int key = random.nextInt(25);
		System.out.println(key + " 의 위치 : " + binarySearch(arr, key));
	}

	// 반복문을 이용한 이진 탐색
	public static int binarySearch(int arr[], int key) {
		int start = 0;
		int end = arr.length - 1;
		int middle;

		while (start <= end) {
			middle = (start + end) / 2;

			if (arr[middle] == key)
				return middle;

			if (key > arr[middle])
				start = middle + 1;
			else
				end = middle - 1;
		}

		return -1;
	}

	// 배열 출력
	public static void printArray(int arr[]) {
		for (int index = 0; index < arr.length; index++) {
			System.out.print(arr[index] + " ");
		}
		System.out.println();
	}
}
