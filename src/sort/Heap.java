package sort;

import java.util.ArrayList;

public class Heap {
	public static int heapSize;
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(0, 0);
		arr.add(4);
		arr.add(13);
		arr.add(6);
		arr.add(10);
		arr.add(5);
		arr.add(7);
		arr.add(8);
		arr.add(20);
		heapSize=arr.size()-1;
		heapSort(arr);

		for (int i : arr) {
			System.out.println(i);
		}
		
	
	}

	private static ArrayList<Integer> heapSort(ArrayList<Integer> arr) {
		buildMaxHeap(arr);
		for(int i = heapSize;i>=2;i--){
			Integer temp = arr.get(i);
			arr.set(i,arr.get(1));
			arr.set(1,temp);
			heapSize =heapSize -1;
			maxHeapify(arr,1);
		}
		return arr;

	}

	private static ArrayList<Integer> buildMaxHeap(ArrayList<Integer> arr) {
		for (int i = (heapSize / 2); i >= 1; i--) {
			maxHeapify(arr, i);
		}
		return arr;
	}

	private static void maxHeapify(ArrayList<Integer> arr, int i) {
		int largest = i;
		int leftIdx = i * 2;
		int rightIdx = (i * 2) + 1;
		
		if (leftIdx <= heapSize && arr.get(leftIdx) > arr.get(largest)) {
			largest = leftIdx;
		} else {
			largest = i;
		}

		if (rightIdx <= heapSize && arr.get(rightIdx) > arr.get(largest)) {
			largest = rightIdx;
		}
		if (largest != i) {
			Integer temp = arr.get(i);
			arr.set(i, arr.get(largest));
			arr.set(largest, temp);
			maxHeapify(arr, largest);
		}
	}

}
