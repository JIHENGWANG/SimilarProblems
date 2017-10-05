package solution;
import java.util.*;

public class HistogramProblems {
	/*
	 * Assumptions:
	 * If array is null or empty, then the area is zero.
	 * 
	 * Steps:
	 * 		Explore each index, to find the left border and right border. if the
	 * 		height = Math.min(array[current index to left reamaining index]).
	 * 		Area = height * (right border - left border + 1) since we start from zero.
	 * 
	 * Return the area which is the largest area in histogram.
	 * 
	 * Time Complexity: o(n^2). Space: o(1)
	 */
	public static int largestArea(int[] array) {
	    // Write your solution here.
		if(array == null || array.length == 0){
			return 0;
		}
		int FinalArea = 0;
		for(int i = 0; i < array.length; i++){
			int currentArea = findArea(array, i);
			FinalArea =Math.max(FinalArea, currentArea);
		}
	    return FinalArea;
	}
	
	private static int findArea(int[] array, int start){
		int area = 0;
		int height = array[start];
		int left = start;
		while(left >= 0){
			height = Math.min(array[left], height);
			area = Math.max(area, height * (start - left + 1));
			left--;
			
		}
		return area;
	}
	
	/*
	 * Largest area in histogram using stack
	 * Steps:Save the indice of array into stack in acsending order, any time
	 * if the array[i] <= array[stack.peek], then keep calculate the left border
	 * and the height, and get the max area from the current index
	 * 
	 * Time o(n), Space o(n) - o(1)
	 */
	public static int largest(int[] array) {
	    // Write your solution here.
		if(array == null || array.length == 0){
			return 0;
		}
		int area = 0;
		Deque<Integer> stack = new LinkedList<> ();
		for(int i = 0; i <= array.length; i++){
			int curr = i == array.length ? 0 : array[i];
			while(!stack.isEmpty() && array[stack.peekFirst()] >= curr){
				int height = array[stack.pollFirst()];
				int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
				area = Math.max(area, height *(i - left));
			}
			stack.offerFirst(i);
		}
		
	    return area;
	  }
	
	/*
	 * Assumptions:
	 * If array == null or array is empty, then return 0;
	 * 
	 * Steps:
	 * create a leftmax to save the maximum column from 0 to current index.
	 * Create a rightmax to save the maximum column from array.length - 1 to current index
	 * if leftMax < rightMax, then calculate the diff of leftmax and arraya[ith], and 
	 * trapped water += diff, and then i++.
	 * Do the same way of rightmax < leftmax
	 * 
	 * return the max trapped water
	 * 
	 * Time: o(n), Space o(1)
	 * 
	 */
	
	public static int maxTrapped(int[] array) {
	    // Write your solution here.
		if(array == null || array.length == 0){
			return 0;
		}
		int leftMax = 0;
		int rightMax = 0;
		int trapped = 0;
		int start = 0, end = array.length - 1;
		while(start <= end){
			leftMax = Math.max(leftMax, array[start]);
			rightMax = Math.max(rightMax, array[end]);
			if(leftMax < rightMax){
				trapped += leftMax - array[start++];
			} else {
				trapped += rightMax - array[end--];
			}
		}
	    return trapped;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,3,2,4,1,3,2,4,3,2};
		System.out.print(maxTrapped(array));
		System.exit(0);
	}

}
