package data_structure;



public class SelectionSort {

	public <T extends Comparable<T>> void sort(T arr[]) {
		int size = arr.length;

		int j;
		for (int i = 0; i < size - 1; i++) {
			int least = i;
			for (j = i + 1; j < size; j++) {
				if (arr[j].compareTo(arr[least]) < 0)
					least = j;
			}
			T temp = arr[least];
			arr[least] = arr[i];
			arr[i] = temp;
		}
	}

	public static void main(String[] args) {
		SelectionSort selection = new SelectionSort();

		int size = 10;
		Integer[] arr = new Integer[size];
		for (int i = 0; i < size; i++) {
			arr[i] = size - i;
		}

		System.out.print("Á¤·² Àü : ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
		System.out.println();

		selection.sort(arr);
		System.out.print("Á¤·² ÈÄ : ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
	}
}
