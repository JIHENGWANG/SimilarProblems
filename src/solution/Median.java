package solution;

public class Median {
	public static double median(int[] a, int[] b) {
		// not sorted arrays
		// need do quick select
		// combine two arrays first
		int[] array = new int[a.length + b.length];
		// laicode doesn't support the following arraycopy: Invalid dependency java/lang/System
		// work OK in eclipse
		//System.arraycopy(a, 0, array, 0, a.length);
		//System.arraycopy(b, 0, array, a.length, b.length);
		int c = 0;
		for (int num : a) {
			array[c++] = num;
		}
		for (int num : b) {
			array[c++] = num;
		}
		if (array.length % 2 != 0) {
			return helper(array, 0, array.length - 1, array.length / 2);
		} else {
			return (helper(array, 0, array.length - 1, array.length / 2 - 1) + helper(array, 0, array.length - 1, array.length / 2)) / 2;
		}
	}

	private static double helper(int[] array, int left, int right, int k) {
		int pivotIndex = findPivot(array, left, right);
		if (pivotIndex == k) {
			return (double) array[pivotIndex];
		} 
		if (pivotIndex < k) {
			return helper(array, pivotIndex + 1, right, k);
		} else {
			return helper(array, left, pivotIndex - 1, k);
		}
	}
	private static int findPivot(int[] array, int left, int right) {
		if (left >= right) {
			return left;
		}
		// pick the middle one as the pivot;
		int mid = left + (right - left) / 2;
		swap(array, mid, right);
		int i = left;
		int j = right - 1;
		while (i <= j) {
			if(array[i] <= array[right]) {
				i++;
			} else {
				swap(array, i, j);
				j--;
			}
		}
		swap(array, i, right);
		return i;
	}
		
	private static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	
	public static double medianSorted(int[] a, int[] b) {
	    // write your solution here
		int length = a.length + b.length;
	    if(length % 2 != 0){
	    	return findKth(a, b, 0, 0, (length + 1) / 2);
	    }
		return (findKth(a, b, 0, 0, (length) / 2) + findKth(a, b, 0, 0,((length) / 2) + 1)) / 2.0;
	}
	private static int findKth(int[] a, int[] b, int aLeft, int bLeft,int k){
		if(aLeft >= a.length){
			return b[bLeft + k - 1];
		} else if(bLeft >= b.length){
			return a[aLeft + k - 1];
		}
		if(k == 1){
			return Math.min(a[aLeft], b[bLeft]);
		}
		int kthOfA = aLeft + k / 2 - 1 < a.length ? a[aLeft + k / 2 - 1] : Integer.MAX_VALUE;
		int kthOfB = bLeft + k / 2 - 1 < b.length ? b[bLeft + k / 2 - 1] : Integer.MAX_VALUE;
		if(kthOfA < kthOfB || kthOfA == Integer.MAX_VALUE){
			return findKth(a, b, aLeft + k / 2, bLeft, k - k / 2);
		} else {
			return findKth(a, b, aLeft, bLeft + k / 2, k - k / 2);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] a = {2, 3, 4, 5, 6};
		int[] b = {1};
		System.out.println(medianSorted(a, b));

	}

}
