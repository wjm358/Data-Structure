package data_structure;

import java.util.Random;

//퀵정렬 구현
public class QuickSort {
	
	//배열의 마지막 인덱스 값을 pivot으로 설정
	public int partition(int arr[],int start,int end) {
		int pivot = arr[end];
		int pIndex = start;
		
		//배열 내에서 피봇보다 작은값, 피봇보다 큰값부분으로 나뉘게 반복
		for(int i=start; i<end;i++) {
			if(arr[i] <=pivot) {
				swap(arr, i,pIndex);
				pIndex++;
			}
		}
		swap(arr,pIndex,end);
		
		//피봇 위치 반환
		return pIndex;
		
	}
	
	//재귀 함수를 사용하여 퀵 정렬 구현
	public void quickSort(int arr[], int start, int end) {
		if (start < end) {
			int pivot = partition(arr, start, end);
			quickSort(arr, start, pivot - 1);
			quickSort(arr, pivot + 1, end);
		}
	}

	public void swap(int arr[],int s, int e) {
		int temp = arr[s];
		arr[s] = arr[e];
		arr[e] = temp;
	}

	//배열 값 출력
	public void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int arr[] = new int[50];
		Random random = new Random();
		QuickSort quick = new QuickSort();
		for (int i = 0; i < arr.length; i++) {
			arr[i]= (random.nextInt(100));
		}
		System.out.println("---------------------------정렬 전-------------------------");
		quick.printArray(arr);
		quick.quickSort(arr, 0, arr.length-1);
		System.out.println("---------------------------정렬 후-------------------------");
		quick.printArray(arr);
	}

}
