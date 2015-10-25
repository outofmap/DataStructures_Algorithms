package Sort;

import java.util.Arrays;

public class insertionSort {
	
	public static int[] insertionSort(int[] arr) {
		int key, i, prekey;
		if (arr.length <= 0) {
			return null;
		}
		for (i = 1; i < arr.length; i++) {
			key = arr[i];
			prekey = i - 1;

			while ((prekey >= 0) && (arr[prekey] > key)) {
				arr[prekey + 1] = arr[prekey];
				prekey--;
			}
			arr[prekey + 1] = key;
		}
		return arr;
	}

	public static int isSorted(int[] arr) {
		int key, next;
		int i = 0;

		for (i = 0; i < arr.length - 1; i++) {
			key = arr[i];
			next = arr[i + 1];

			if (key > next) {
				return 0;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		int[] arrNull = {};
		int[] arr1 = { 2 };
		int[] arr2 = { 2, 3 };
		int[] arrReverse = { 10, 5 };
		int[] arrRandom = { 10, 2, 3, 7, 80, 9, 11, 20, 54, 46, 78, 85, 69, 543, 11, 77, 65, 32, 50, 13, 26 };
		
		insertionSort(arrNull);
		insertionSort(arr1);
		insertionSort(arr2);
		insertionSort(arrReverse);
		insertionSort(arrRandom);
		
		System.err.println("before Sorted:" + Arrays.toString(arrRandom));
		System.out.println("atfer Sorted:"+Arrays.toString((insertionSort(arrRandom))));
		int result = isSorted(insertionSort(arrRandom));
		System.out.println("Is Sorted:" + result);
		
		System.out.println("arrNull:"+isSorted(arrNull));
		System.out.println("arr1 :"+isSorted(arr1));
		System.out.println("arr2 :"+isSorted(arr2));
		System.out.println("arrReverse:"+isSorted(arrReverse));
		System.out.println("arrRandom:"+isSorted(arrRandom));
	}



}