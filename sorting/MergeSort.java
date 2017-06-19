package data_structure;

public class MergeSort<T extends Comparable<T>> {
	private Object temp[];

	public MergeSort(T[] arr) {
		temp = new Object[arr.length];
		mergeSort(arr, 0, arr.length - 1);
	}

	public void mergeSort(T[] arr, int start, int end) {

		if (start < end) {
			int mid = (start + end) / 2;
			mergeSort(arr, start, mid);
			mergeSort(arr, mid + 1, end);
			merge(arr, start, mid, end);
		}
	}

	public void merge(T arr[], int start, int mid, int end) {

		int left = start;
		int right = mid + 1;
		int index = left;
		while (left <= mid && right <= end) {
			if (arr[left].compareTo(arr[right]) > 0) {
				temp[index] = arr[right];
				right++;

			} else {
				temp[index] = arr[left];
				left++;
			}
			index++;
		}

		if (left <= mid) {
			while (left <= mid) {
				temp[index++] = arr[left++];
			}
		} else {
			while (right <= end)
				temp[index++] = arr[right++];
		}

		for (int i = start; i <= end; i++)
			arr[i] = (T) temp[i];

	}

	public static void main(String[] args) {

		int size = 10;
		Integer arr[] = new Integer[size];
		for (int i = 0; i < size; i++) {
			arr[i] = size - i;
		}

		System.out.print("Á¤·² Àü : ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%d " ,arr[i]);
		}
		System.out.println();
		System.out.println();
		
		MergeSort<Integer> merge = new MergeSort<Integer>(arr);
		
		System.out.print("Á¤·² ÈÄ : ");
		for (int i = 0; i < size; i++) {
			System.out.printf("%d " ,arr[i]);
		}
		System.out.println();

	}
}
