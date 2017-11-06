package solution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.lang.reflect.Array;
import java.util.*;

public class DFSRecursion {
	/*
	 * Simple reverse a string using recursion
	 */
	public String reverse(String word) {
		if (word == null || word.length() == 0) {
			return word;
		}
		char[] array = word.toCharArray();
		recursion(array, 0, word.length() - 1);
		return new String(array, 0, array.length);
	}
	private void recursion(char[] array, int start, int end) {
		if (start >= end) {
			return;
		}
		recursion(array, start + 1, end - 1);
		swap(array, start, end);
	}
	private static void swap(char[] array, int start, int from) {
		char temp = array[from];
		array[start] = array[from];
		array[from] = temp;
	}
	
	/*
	 * nqueens problems using o(1) check
	 * Assumptions: if n is 0, then return a empty list.
	 * 
	 * Steps: we start from the first row. if we put a queen on first row,
	 * 		  after our check function, we move to the next row until all queens
	 * 		  are placed.
	 * 
	 * Time: o(n!)
	 * 
	 */

	public static List<List<Integer>> nqueens(int n) {
		// Write your solution here.
		List<List<Integer>> sol = new ArrayList<>();
		if (n == 0)
			return sol;
		boolean[] columns = new boolean[n];
		boolean[] diag = new boolean[2 * n - 1];
		boolean[] revDiag = new boolean[2 * n - 1];
		nQueens(sol, n, 0, new ArrayList<Integer>(), columns, diag, revDiag);
		return sol;
	}

	private static void nQueens(List<List<Integer>> sol, int n, int row, List<Integer> cases, boolean[] columns,
			boolean[] diag, boolean[] revDiag) {
		if (row == n) {
			sol.add(new ArrayList<Integer>(cases));
			return;
		}
		for (int i = 0; i < n; i++) {
			cases.add(i);
			if (checkIfPass(i, row, columns, diag, revDiag, n)) {
				mark(i, row, columns, diag, revDiag, n);
				nQueens(sol, n, row + 1, cases, columns, diag, revDiag);
				unmark(i, row, columns, diag, revDiag, n);
			}
			cases.remove(cases.size() - 1);
		}
	}
	private static boolean checkIfPass(int col, int row, boolean[] columns, boolean[] diag, boolean[] revDiag, int n) {
		return !columns[col] && !diag[row + col] && !revDiag[col - row + n - 1];

	}
	private static void mark(int col, int row, boolean[] columns, boolean[] diag, boolean[] revDiag, int n) {
		columns[col] = true;
		diag[row + col] = true;
		revDiag[col - row + n - 1] = true;
	}
	private static void unmark(int col, int row, boolean[] columns, boolean[] diag, boolean[] revDiag, int n) {
		columns[col] = false;
		;
		diag[row + col] = false;
		revDiag[col - row + n - 1] = false;
	}
	
	/*
	 * Find all subset in input word
	 * Assumptions: if word is null or empty, then return an empty list.
	 * 
	 * Steps:
	 * 		Consider every character in our word, we can either add or 
	 * 		not add this character into this subset, so we do this two
	 * 		case recursively to get every subset of our input word.
	 * 
	 * Return a list with all subset of out input word.
	 * Time: 2^n, Space: n
	 */

	public List<String> subset(String word) {
		List<String> sol = new ArrayList<>();
		if (word == null || word.length() == 0) {
			return sol;
		}
		StringBuilder builder = new StringBuilder();
		findSubset(word, builder, 0, word.length(), sol);
		return sol;
	}
	private void findSubset(String word, StringBuilder builder, int start, int end, List<String> sol) {
		if (start == end) {
			sol.add(builder.toString());
			return;
		}
		builder.append(word.charAt(start));
		findSubset(word, builder, start + 1, end, sol);
		builder.deleteCharAt(builder.length() - 1);
		findSubset(word, builder, start + 1, end, sol);
	}
	
	/*
	 * Another similar way to do subset.
	 */
	
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> sol = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return sol;
		}
		List<Integer> cases = new ArrayList<>();
		findSubset(nums, cases, 0, sol);
		return sol;
	}
	private void findSubset(int[] nums, List<Integer> cases, int start, List<List<Integer>> sol) {
		sol.add(new ArrayList<Integer>(cases));
		for (int i = start; i < nums.length; i++) {
			cases.add(nums[i]);
			findSubset(nums, cases, i + 1, sol);
			cases.remove(cases.size() - 1);
		}
	}
	
	/*
	 * Find subset with duplicate characters. 
	 * 
	 * Assumptions: if input word is null or empty, then return a empty list
	 * 
	 * Step: Consider every character in our input word. because we have duplicate
	 * 		 characters. Once we decide not to add this character into our subset, we
	 * 		 skip all this characters 
	 * 
	 * Return a list with all subset of out input word.
	 * Time: 2^n, Space: n
	 */

	public static List<String> subSets(String set) {
		// Write your solution here.
		if (set == null)
			return new ArrayList<String>();
		List sol = new ArrayList<String>();
		StringBuilder helper = new StringBuilder();
		char[] setArray = set.toCharArray();
		Arrays.sort(setArray);
		findSubSet(setArray, helper, 0, sol);
		return sol;
	}
	private static void findSubSet(char[] set, StringBuilder helper, int index, List<String> sol) {
		if (index == set.length) {
			sol.add(helper.toString());
			return;
		}
		helper.append(set[index]);
		findSubSet(set, helper, index + 1, sol);
		helper.deleteCharAt(helper.length() - 1);
		while (index < set.length - 1 && set[index] == set[index + 1]) {
			index++;
		}
		findSubSet(set, helper, index + 1, sol);

	}
	
	
	/*
	 * Find all permutations in our input word.
	 * Assumptions:  if input word is null or empty, then return a empty list
	 * 
	 * Steps: swap each character can recursively count each permutation
	 * 
	 * return a list with all permutation
	 * 
	 * Time: o(n!) space: o(n)
	 */

	public static List<String> permutations(String set) {
		if (set == null) {
			return new ArrayList<String>();
		}
		List sol = new ArrayList<String>();
		char[] setArray = set.toCharArray();
		findPermutations(setArray, 0, sol);

		return sol;
	}
	private static void findPermutations(char[] set, int index, List<String> sol) {
		if (index == set.length) {
			sol.add(new String(set));
			return;
		}

		for (int i = index; i < set.length; ++i) {
			swap(set, index, i);
			findPermutations(set, index + 1, sol);
			swap(set, index, i);
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
