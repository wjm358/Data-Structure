package data_structure;


public class BubbleSort {
	public <T extends Comparable<T>> void sort(T arr[]) {
		int size = arr.length;

		for (int i = size - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					T temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {

		BubbleSort bubble = new BubbleSort();
		int size = 10;
		Integer arr[] = new Integer[10];
		for (int i = 0; i < size; i++) {
			arr[i] = size - i;
		}

		System.out.print("Á¤·² Àü : ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%d " ,arr[i]);
		}
		System.out.println();
		System.out.println();
		
		bubble.sort(arr);
		System.out.print("Á¤·² ÈÄ : ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%d " ,arr[i]);
		}
		System.out.println();
		
	}
}
