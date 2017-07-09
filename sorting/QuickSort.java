package data_structure;

import java.util.Random;

//������ ����
public class QuickSort {
	
	//�迭�� ������ �ε��� ���� pivot���� ����
	public int partition(int arr[],int start,int end) {
		int pivot = arr[end];
		int pIndex = start;
		
		//�迭 ������ �Ǻ����� ������, �Ǻ����� ū���κ����� ������ �ݺ�
		for(int i=start; i<end;i++) {
			if(arr[i] <=pivot) {
				swap(arr, i,pIndex);
				pIndex++;
			}
		}
		swap(arr,pIndex,end);
		
		//�Ǻ� ��ġ ��ȯ
		return pIndex;
		
	}
	
	//��� �Լ��� ����Ͽ� �� ���� ����
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

	//�迭 �� ���
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
		System.out.println("---------------------------���� ��-------------------------");
		quick.printArray(arr);
		quick.quickSort(arr, 0, arr.length-1);
		System.out.println("---------------------------���� ��-------------------------");
		quick.printArray(arr);
	}

}
