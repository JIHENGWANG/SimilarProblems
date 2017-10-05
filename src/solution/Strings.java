package solution;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import solution.Solution.Cell;

public class Strings {
	/*
	 * Assumptions:
	 * Assume that both strings are not null
	 * Case 1: either s or t is empty, if s is empty ,then return true. if t is empty, then return false.
	 * 
	 * Steps: Using hashMap to save each characters in s. Each character in t will be the value of characters in s
	 * 		  if found there is two different characters share the same key, then return false. If found one character has two
	 * 		  keys, return false;
	 * 	      After all characters are checked, we can return true.
	 * 
	 * Return a boolean to indicate whether two strings are isomophic. 
	 * 
	 * Time complexity:O(n)  Space complecity:O(n) 
	 */
	public static boolean isomorphic(String s, String t) {
	    // Write your solution here.
		if(s.length() != t.length()){
			return false;
		}
		Map<Character, Character> map = new HashMap<> ();
		for(int i = 0; i < s.length(); i++){
			if(map.containsKey(s.charAt(i))){
				if(!map.get(s.charAt(i)).equals(t.charAt(i))){
					return false;
				}
			} else if(map.containsValue(t.charAt(i))){
				return false;
			} else {
				map.put(s.charAt(i), t.charAt(i));
			}
		}
		
	    return true;
	}
	
	/*
	 * Assumptions:
	 * Case 1: if the input string is null or empty, then there is zero way of decode
	 * Case 2: if input string is not empty
	 * 
	 * Step: Fill out a 1d array to find out the total way of decoding. array[i] represents the total
	 * 		 way of decoding from 0th character to ith character in input string. Note that only 1-26 can
	 * 		 be decoded.
	 * 
	 * Inductions Rule:
	 * 		Base Case: array[0] = 1 and array[1] = 1 when there is only one character in string.
	 *      when ith characters in string, check i - ith and i - 2th character, if i - 1th within[1 - 9],
	 *      then array[i] += array[i - 1] if i - 2th within [10 - 26], then array[i] += array[i - 2]
	 *      
	 * Return array[s.length() - 1]
	 * 
	 * Time Complexity: o(n). Space Complexity: O(n)
	 * 
	 */
	
	public static int numDecodeWay(String s) {
	    //Input your code here
		if(s == null || s.length() == 0){
			return 0;
		}
		int[] array = new int[s.length() + 1];
		array[0] = 1;
		array[1] = s.charAt(0) != '0' ? 1 : 0;
		for(int i = 2; i <= s.length(); i++){
			if(Integer.parseInt(s.substring(i - 1, i)) != 0){
				array[i] += array[i - 1];
			}
			if(Integer.parseInt(s.substring(i - 2, i)) >= 10 && 
					Integer.parseInt(s.substring(i - 2, i)) <= 26){
				array[i] += array[i - 2];
			}
		}
	    return array[s.length()];
	}
	
	/*
	 * Assumptions: Dict does at least contain two strings
	 * 
	 * Steps: Use BFS to explore all length of two valid strings and find the maximum one.
	 * 	      Define initial states: <s1, s2>
	 * 	      Explore all valid neighbors from <s1, and s2>
	 *        when current vertex is <si, sj>, then <si+1, sj+1> and <si, sj+1> are its neighbor.
	 *        if  neighbor is not valid, then set product to 0.
	 *        insert neighors into pq.
	 *        Notes: valid means not comment char in two strings.
	 *        
	 * 
	 * When found a valid product, return it, otherwise when loop ends, return 0.
	 * 
	 * Time Complexity: O(n^2(logn^2 + time of compare). Space: O(size of pq n^2)
	 */
	
	static class State {
		private int index1;
		private int index2;
		private int product;
		
		public State(int index1, int index2, int product){
			this.index1 = index1;
			this.index2 = index2;
			this.product = product;
		}
		
		public void update (int product){
			this.product = product;
		}
		
	}
	public static int largestProduct(String[] dict) {
	    // Write your solution here.
		Arrays.sort(dict, new Comparator<String>() {
			@Override
			public int compare(String one, String two){
				if(one.length() == two.length()){
					return 0;
				}
				return one.length() > two.length() ? -1 : 1;
			}
		});
		State initial = new State(0, 1, dict[0].length() * dict[1].length());
		if(!compare(dict[0], dict[1])){
			initial.update(0);
		}
		PriorityQueue<State> maxHeap = new PriorityQueue(11, new Comparator<State>(){
			@Override
			public int compare (State c1, State c2){
				if(c1.product == c2.product){
					return 0;
				}
				return c1.product > c2.product ? -1 : 1;
			}
		});
		maxHeap.offer(initial);
		while(!maxHeap.isEmpty()){	
			State curr = maxHeap.poll();
			if(curr.product > 0){
				return curr.product;
			}
			if(curr.index1 + 1 < dict.length){
				State next1 = new State(curr.index1 + 1, curr.index2, 
						dict[curr.index1 + 1].length() * dict[curr.index2].length());
				if(!compare(dict[curr.index1 + 1], dict[curr.index2])){
					next1.update(0);
				}
				maxHeap.offer(next1);
			}
			if(curr.index2 + 1 < dict.length){
				State next2 = new State(curr.index1, curr.index2 + 1, 
						dict[curr.index1].length() * dict[curr.index2 + 1].length());
				if(!compare(dict[curr.index1], dict[curr.index2 + 1])){
					next2.update(0);
				}
				maxHeap.offer(next2);
			}
		}
	    return 0;
	}
	
	private static boolean compare(String s1, String s2){
		for(int i = 0; i < s1.length(); i++){
			for(int j = 0; j < s2.length(); j++){
				if(s1.charAt(i) == s2.charAt(j)){
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean exitCircle(String[] strings){
		boolean[] found = new boolean[1];
		findCircle(strings, 0, found);
		return found[0];
	}
	private static void findCircle(String[] strings, int index, boolean[] found){
		//Base Case
		if(index == strings.length){
			found[0] = strings[index - 1].charAt(strings[index - 1].length() - 1) == strings[0].charAt(0); 
			return;
		}
		for(int i = index; i < strings.length; i++){
			if(index == 0 || strings[index - 1].charAt(strings[index - 1].length() - 1) == strings[i].charAt(0)){
				swap(strings, index, i);
				findCircle(strings, index + 1, found);
				swap(strings, index, i);
			}
		}
	}
	
	private static<E> void swap(E[] array, int from, int to){
		E temp = array[from];
		array[from] = array[to];
		array[to] = temp;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] dic = {"aaa","bbb","abb","bba"};
		System.out.print(exitCircle(dic));
		System.exit(0);

	}

}
