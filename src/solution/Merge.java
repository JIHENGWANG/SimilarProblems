package solution;

public class Merge {
	
	/*
	 * Assumption: 
	 * Input has at least one element or more.
	 * 
	 * steps: Fill the 2d array to find out the minimum cost. array[i][j] represent the 
	 * 		  minimum cost between ith to jth stone.
	 * 
	 * Induction Rules: fill out Cost[i][j] and subSum[i][j]
	 * 		  Base Case:
	 * 					i == j: cost[i][j] = 0 and subSum[i][j] = stones[i];
	 * 		  
	 * 		  subSum[j][i] += subSum[j][i - 1] + stones[i];
	 * 		  for(int k = i; k > j; k--){
	 * 				minCost[j][i] = Math.min(minCost[j][k], minCost[j][k] + minCost[k - 1][i]) + subSum[j][i];
	 * 		  }
	 * 		  
	 * Return array[0][stone.length - 1] which represents the min cost from first stone to final stone
	 * 
	 * Time Complexity: O(n^3) = space complexity o(n^2)
	 */
	
	public static int minCost(int[] stones) {
	    // Write your solution here.
		int[][] minCost = new int[stones.length][stones.length];
		int[][] subSum = new int[stones.length][stones.length];
		for(int i = 0; i < stones.length; i++){
			for(int j = i; j >= 0; j--){
				if(j == i){
					minCost[j][i] = 0;
					subSum[j][i] = stones[i];
				} else {
					minCost[j][i] = Integer.MAX_VALUE;
					subSum[j][i] = subSum[j][i - 1] + stones[i];
					for(int k = j; k < i; k++){
						minCost[j][i] = Math.min(minCost[j][i], minCost[j][k] + minCost[k + 1][i] + subSum[j][i]);
					}
				}
			}
		}
	    return minCost[0][stones.length - 1];
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {4,2,2,2,4};
		System.out.print(minCost(array));
		System.exit(0);
	}

}
