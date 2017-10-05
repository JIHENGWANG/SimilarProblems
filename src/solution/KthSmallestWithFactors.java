package solution;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KthSmallestWithFactors {
	
	/*
	 * Assumptions: K >= 1
	 * 
	 * Steps: Define the initial state using 3, 5, 7. and explore it neighbors.
	 * 	      3^i, 5^j, 7^k. Using and minHeap to save and pop k - 1 times. The peek
	 * 	      of the heap is the Kth smallest with 3, 5, 7 factors.
	 * 
	 * Return the top of heap, which is thekth smallest.
	 * 
	 *  Time Complexity: O(klogk), Space complexity: O(k)
	 */
	public static long kth(int k) {
		// Write your solution here.
		PriorityQueue<Long> minHeap = new PriorityQueue(k);
		Set<Long> set = new HashSet<> ();
		minHeap.offer(3 * 5 * 7L);
		set.add(minHeap.peek());
		for(int i = k; i > 1; i--){
			long curr = minHeap.poll();
			if(!set.contains(curr * 3)){
				set.add(curr * 3);
				minHeap.offer(curr * 3);
			}
			if(!set.contains(curr * 5)){
				set.add(curr * 5);
				minHeap.offer(curr * 5);
			}
			if(!set.contains(curr * 7)){
				set.add(curr * 7);
				minHeap.offer(curr * 7);
			}
		}
		return minHeap.peek();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print(kth(20));
		System.exit(0);
	}

}
