package solution;

public class CuttingWood {

	/*
	 * Assumptions: The length of wood must be greater than 1.
	 * 
	 * Steps: Try all possible numbers between 0 to length, and find all possible cuts on each
	 * 		  length. Fill out M[i][j] to find the minimum cost on all possible cuts. 
	 * 		  M[i][j] represents the minimum cost between ith to jth cuts. we need to
	 * 		  find M[0][length - 1] which is min cost from 0 to length.
	 * 
	 * Induction rules:
	 * 		  Base Case: when i = j + 1, then minCost[j][i] = 0;
	 * 		  for(int k = j + 1; k >= i - 1; k--){
						minCost[j][i] = Math.min(minCost[j][i], minCost[j][k] + minCost[k][i]);
			  }
	 * 
	 * Return M[0][length - 1] which is which is min cost from 0 to length.
	 * 
	 * TIme Complexity:O(n^3) Space Complexity:O(n^2 + n)
	 */
	
	public static int minCost(int[] cuts, int length) {
		// Write your solution here.
		if(length < 1){
			return 0;
		}
		int[] allCuts = getCuts(cuts, length);
		int[][] minCost = new int[allCuts.length][allCuts.length];
		for(int i = 1; i < allCuts.length; i++){
			for(int j = i - 1; j >= 0; j--){
				if(j == i - 1){
					minCost[j][i] = allCuts[0];
				} else {
				  minCost[j][i] = Integer.MAX_VALUE;
					for(int k = j + 1; k <= i - 1; k++){
						minCost[j][i] = Math.min(minCost[j][i], minCost[j][k] + minCost[k][i]);
					}
					minCost[j][i] += allCuts[i] - allCuts[j];
				}
			}
		}
		return minCost[0][allCuts.length - 1];
	}

	private static int[] getCuts(int[] cuts, int length){
		int[] sol = new int [cuts.length + 2];
		sol[0] = 0;
		for(int i = 0; i < cuts.length; i++){
			sol[i + 1] = cuts[i];
		}
		sol[sol.length - 1] = length;
		return sol;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.exit(0);

	}

}
