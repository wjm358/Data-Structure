package data_structure;

import java.util.Scanner;

public class SequentialSearch {

	public static void main(String[] args) {
		int arr[] = new int[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		printArray(arr);

		System.out.println("5의 위치 : " + sequentialSearch(arr, 5));
	}

	// 기본 순차 탐색
	public static int sequentialSearch(int arr[], int key) {
		for (int index = 0; index < arr.length; index++) {
			if (arr[index] == key)
				return index;
		}
		return -1;
	}

	// 개선된 순차 탐색
	public static int sequentialSearch2(int arr[], int key) {
		int index;
		arr[arr.length] = key;
		for (index = 0; arr[index] != key; index++)
			;
		if (index == arr.length)
			return -1;
		else
			return index;
	}

	// 배열 출력
	public static void printArray(int arr[]) {
		for (int index = 0; index < arr.length; index++) {
			System.out.print(arr[index] + " ");
		}
		System.out.println();
	}
}
