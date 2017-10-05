package solution;

public class UniquePath {
	
	/*
	 * Assumptions:
	 * Case 1: either n or m is zero, then the unique path from <0,0> to <n,m> is zero.
	 * Case 2: either n or m is 1, then there is only one unique path from <0,0> to <n, m>.
	 * Case 3: n and n is greater than 1
	 * 
	 * Steps:
	 * 		step 1: we can use DFS to explore each node and its neighbors. and find the total number of unique path
	 * 		Note: mark the current node when this node is visited.
	 * 
	 * Return the total number of unique paths.
	 * 
	 * Time complexity O(2^(longest path)). space O(level of DFS).
	 */

	public static int uniquePaths(int m, int n) {
	    // write your solution here.
		if(n == 0 || m == 0){
			return 0;
		}
		if((n == 1 || m == 1)){
			return 1;
		}
		int[] totalPath = {0};
	    findPath(0, 0, n - 1, m - 1,totalPath);
	    return totalPath[0];
	  }
	
	private static void findPath(int right, int down, int row, int col, int[] path){
		if(right == col && down == row){
			path[0]++;
			return;
		}
		if(right < col){
			findPath(right + 1, down, row, col, path);
		}
		if(down < row){
			findPath(right, down + 1, row, col, path);
		}
	}
	
	
	/*
	 * Sulotion 2: find uniquePaths using dynamic progamming
	 * Assumptions: Same as solution 1.
	 * Base Case: 1. two boundaries count two path.
	 * 
	 * steps: fill out the 2d array to find the total number of unique paths.
	 * array[i][j] represents total number of path from array[0][0] to array[i][j];
	 * 
	 * Inductions Rules: array[i][j] = array[i - 1][j] + array[i][j - 1]
	 * 
	 * return array[row - 1][col - 1] which is the total path to the end
	 * 
	 * time complexity: O(n*n) Space complexity: o(n*n)
	 * 		
	 */
	
	public static int uniquePathDP(int m, int n){
		if(n == 0 || m == 0){
			return 0;
		}
		if((n == 1 || m == 1)){
			return 1;
		}
		int[][] path = new int [n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(i == 0 || j == 0){
					path[i][j] = 1;
				} else {
					path[i][j] = path[i - 1][j] + path[i][j - 1];
				}
			}
		}
		return path[n - 1][m - 1];
	}
	
	/*
	 * Assumptions:
	 * Case 1: if input is null for empty, then the min sum is zero.
	 * Case 2: there is only one row or one col in input, then the min sum is the total sum of this row or this column.
	 * 
	 * Steps: Fill out an 2d array and find out the min sum in input.
	 * 		  array[i][j] represent the current min sum from array[0][0] to array[i][j].
	 * 
	 * Induction rules:  1: if we only move down or right, 
	 * 						then array[i][j] = Min(array[i - 1][j], array[i][j - 1]) + input[i][j].
	 * 					 2: if we only down, right, and right down, 
	 * 						then array[i][j] = Min(array[i - 1][j], array[i][j - 1], array[i - 1][j - 1]) + input[i][j].
	 * 
	 * return array[row - 1][coloumn - 1] which is the min sum from top of input to bottom of input.
	 * 
	 * Time complexity: o(n^2). Space complexity: O(n^2)
	 */
	
	public static int miniSum(int[][] grid) {
		//Input your code here
		if(grid == null || grid.length == 0 ||  grid[0].length == 0 ){
			return 0;
		}
		int[][] min = new int [grid.length][grid[0].length];
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(i == 0 && j == 0){
					min[i][j] = grid[i][j];
				} else if(i == 0){
					min[i][j] = min[i][j - 1] + grid[i][j]; 
				} else if(j == 0) {
					min[i][j] = min[i - 1][j] + grid[i][j]; 
				} else {
					min[i][j] = Math.min(min[i -1][j], min[i][j - 1]) + grid[i][j];
				}
			}
		}
		return min[min.length - 1][min[0].length - 1];
	}
	
	/*
	 * Assumptions:
	 * Case 1: if the input matrix is null or empty, then there is no any path.
	 * Case 2: if there is only one row or one column in matrix, then we may find the only path if we don't hit 
	 * 		   any obstacle. 
	 * 
	 * Steps: Use Dynamic Programming to fill out a 2d array to find the possible paths. Array[i][j] represents the all possible 
	 * 		  Path from matrix[0][0] to matrix[i][j]
	 * 
	 * Induction rules: if(matrix[i][j] != 0) then array[i][j] = array[i - 1][j] + array[i][j - 1];
	 * 
	 * Return array[row - 1][col - 1] which represents the total possible path from top to bottom.
	 * Time Complexity: o(n^2) Space complexity: O(n^2)
	 */

	public static int possiblepath(int[][] matrix) {
		// Write your solution here.
		if(matrix == null || matrix.length == 0 ||  matrix[0].length == 0 ){
			return 0;
		}
		int [][] path = new int [matrix.length][matrix[0].length];
		for(int i = 0; i < path.length; i++){
			for(int j = 0; j < path[0].length; j++){
				if(matrix[i][j] != 1){
					if(i == 0 && j == 0){
						path[i][j] = 1;
					} else if(i == 0){
						path[i][j] = path[i][j - 1]; 
					} else if(j == 0) {
						path[i][j] = path[i - 1][j]; 
					} else {
						path[i][j] = path[i -1][j] + path[i][j - 1];
					}
				}
			}
		}
		return path[path.length - 1][path[0].length - 1];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] input = {

				 {0,0,0},

	                {0,1,0},

	                {0,0,0}



							};
		System.out.print(possiblepath(input));
		System.exit(0);

	}

}
