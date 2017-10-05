package solution;
import java.util.*;


public class longest {
	
	/* Assumption:
	 * Case 1: either s or t is empty, then the common string is empty
	 * Case 2: s and t is not empty:
	 * 
	 * Induction rules:
	 * 2.1: Fill the 2D DP array and return the longest common string.
	 * 		Need index to mark the start and end point of longest common string.
	 * 		m[i][j] : if s[i - 1] == t[j - 1], then if i == 0 || j == 0
	 * 				  we have m[i][j] = 1, if not, then m[i][j] = [i - 1][j - 1] + 1;
	 * 			      if current m[i][j] is greater than the globalMax, then update the globalMax
	 * 				  else, m[i][j] is 0. 
	 * 
	 * Time Complexity is O(n*m). Space is O(n*m)
	 */

	public static String longestCommon(String s, String t) {
		// Write your solution here.
		if(s == null || t == null || s.length() == 0 || t.length() == 0){
			return "";
		}
		int[][] commons = new int[s.length()][t.length()];
		int globalMax = 0;
		int start = 0;
		int end = 0;
		for(int i = 0; i < s.length(); i++){
			for(int j = 0; j < t.length(); j++){
				if(s.charAt(i) == t.charAt(j)){
					if(i == 0 || j == 0){
						commons[i][j] = 1;
					} else {
						commons[i][j] = commons[i - 1][j - 1] + 1;
					}
					if(commons[i][j] > globalMax){
						globalMax = commons[i][j];
						//globalMax cannot greater than i
						start = i - globalMax + 1;
						end = start + globalMax;	
					}
				}
			}
		}
		return s.substring(start, end);
	}
	
	/*
	 * Assumptions:
	 * Case 1: Either string s or string t is null or empty, then the common subsequence is empty.
	 * 		   then we return 0. 
	 * Case 2: s and t both are not empty, then fill a 2D array to find the longest common subsequence
	 * 		   of them.
	 * 
	 * Inductions rules:
	 * Case 1: if s[i] == t[j] ,then M[i][j] = M[i - 1][j - 1] + 1 if i != 0 && j != 0. M[i][j] = 1 if i or j == 0.
	 * 		   else we take the max value from M[i - 1[j], M[i][j - 1] or M[i - 1][j - 1] if i and j both != 0.
	 * Case 2: We need to maintain a globalMax. if M[i][j] greater than the globalMax, then we update it.
	 * 
	 * return the last element of M.
	 * Time Complexity is O(n*m). Space Complexity is O(n*m).
	 */
	
	public static int longestSubsequence(String s, String t) {
		// Write your solution here.
		if(s.length() == 0 || t.length() == 0){
			return 0;
		}
		int[][] commons = new int[s.length()][t.length()];
		for(int i = 0; i < s.length(); i++){
			for(int j = 0; j < t.length(); j++){
				if(s.charAt(i) == t.charAt(j)){
					if(i == 0 || j == 0){
						commons[i][j] = 1;
					} else {
						commons[i][j] = commons[i - 1][j - 1] + 1;
					}
				} else {
					if(i != 0 && j != 0){
						commons[i][j] = Math.max(commons[i - 1][j], commons[i][j - 1]);
						commons[i][j] = Math.max(commons[i][j], commons[i - 1][j - 1]);
					}
				}
			}
		}
		return commons[s.length() - 1][t.length() - 1];
	}
	
	/*
	 * Assumptions:
	 * Case 1: If array is null or array is empty, then longest ascending subsequence is 0.
	 * Case 2: If array is not empty, then we fill the 1D array to find the longest ascending subsequence.
	 * 
	 * Induction rules: M[i] represent the 0th to ith the length of longest ascending subsequence in array.
	 * Case 1: if(array[i] > array[j]), m[i] = Max(m[j]) + 1. m[i] = 1 otherwise.
	 * 
	 * Return the last element of m.
	 * Time Complexity is o(n*n) Space Complexity is o(n*n).
	 */

	public static int longest(int[] array) {
		// Write your solution here.
		if(array == null || array.length == 0){
			return 0;
		}
		int[] las = new int[array.length];
		int max = 1;
		for(int i = 0; i < array.length; i++){
			las[i] = 1;
			for(int j = 0; j < i; j++){
				if(array[i] > array[j]){
					las[i] = Math.max(las[i], las[j] + 1);
				} 
			}
			max = Math.max(las[i], max);
		}
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {1,1,1,3,5,2};
		int s = longest(a);
		System.out.print(s + " ");
		System.exit(0);
	}
	
	
}
