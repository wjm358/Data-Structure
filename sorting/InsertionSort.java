package data_structure;

public class InsertionSort {
	public <T extends Comparable<T>> void sort(T arr[]) {
		int i, j;
		int size = arr.length;
		for (i = 1; i < size; i++) {
			T key = arr[i];
			for (j = i - 1; j >= 0 && (arr[j].compareTo(key) > 0); j--)
				arr[j + 1] = arr[j];
			arr[j + 1] = key;
		}
	}

	public static void main(String[] args) {
		InsertionSort insertion = new InsertionSort();

		int size = 10;
		Integer[] arr = new Integer[size];
		for (int i = 0; i < size; i++) {
			arr[i] = size - i;
		}
		
		System.out.print("Á¤·² Àü : ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%d " ,arr[i]);
		}
		System.out.println();
		System.out.println();
		
		insertion.sort(arr);
		System.out.print("Á¤·² ÈÄ : ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%d " ,arr[i]);
		}
		System.out.println();
		

	}
}
