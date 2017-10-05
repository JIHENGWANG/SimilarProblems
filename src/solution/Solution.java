package solution;



import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.*;

class ListNode {
	public int value;
	public ListNode next;
	public ListNode(int value){
		this.value = value;
		next = null;
	}
}

class TreeNodeP {
	   public int key;
	   public TreeNodeP left;
	   public TreeNodeP right;
	   public TreeNodeP parent;
	   public TreeNodeP(int key, TreeNodeP parent) {
	     this.key = key;
	     this.parent = parent;
	   }
	 }
 
  
  class GraphNode {
	    public int key;
	     public List<GraphNode> neighbors;
	     public GraphNode(int key) {
	       this.key = key;
	       this.neighbors = new ArrayList<GraphNode>();
	     }
}
  
  
  class RandomListNode {
	     public int value;
	     public RandomListNode next;
	     public RandomListNode random;
	     public RandomListNode(int value) {
	       this.value = value;
	     }
	   }

 /*static class Cell implements Comparable<Cell> {
		int row;
		int column;
		int value;
		Cell(int row, int column, int value){
			this.row = row;
			this.column = column;
			this.value = value;
		}

		public int compareTo(Cell cell){
			if(this.value == cell.value){
				return 0;
			}

			return this.value < cell.value ? -1 : 1;
		}

	}*/
  
  
 
public class Solution<K, V> {
	public static boolean canMerge(String a, String b, String c) {
		// Write your solution here.
		if (a.length() + b.length() != c.length()) {
			return false;
		}
		boolean[][] canMerge = new boolean[a.length() + 1][b.length() + 1];
		for (int i = 0; i <= a.length(); i++) {
			for (int j = 0; j <= b.length(); j++) {
				if (i == 0 && j == 0) {
					canMerge[i][j] = true;
				}
				if (a.charAt(i - 1) == c.charAt(i + j - 1)) {
					canMerge[i][j] |= canMerge[i - 1][j];
				}
				if (b.charAt(j - 1) == c.charAt(j + i - 1)) {
					canMerge[i][j] |= canMerge[i][j - 1];
				}
			}
		}
		return canMerge[a.length()][b.length()];
	}
	private static int quickSelect(int a[], int start, int end, int median){
		int pivot = partition(a, start, end);
		if(pivot != median){
			if(pivot > median){
				return quickSelect(a, start, pivot - 1, median);
			} else {
				return quickSelect(a, pivot + 1, end, median);
			}
		}
		return a[pivot];
	}

	private static int partition(int[] array, int left, int right){
		int leftIndex = left;
		int rightIndex = right - 1;
		int pivot = array[right];
		while (leftIndex <= rightIndex){
			if(array[leftIndex] <= pivot)
				++leftIndex;
			else if(array[rightIndex] >= pivot)
				--rightIndex;
			else{
				//swap
				swap(array, leftIndex++, rightIndex--);
			}
		}
		//swap
		swap(array, leftIndex, right);
		return leftIndex;
	}

	private static void swap(int[] array, int left, int right){
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	static class Cell implements Comparable<Cell> {
		int row;
		int column;
		int value;
		Cell(int row, int column, int value){
			this.row = row;
			this.column = column;
			this.value = value;
		}
		public int compareTo(Cell cell){
			if(this.value == cell.value){
				return 0;
			}
			return this.value < cell.value ? -1 : 1;
		}
	}
	
	static class MyComparator implements Comparator<ListNode>{
		@Override
		public int compare (ListNode node1, ListNode node2){
			if(node1.value == node2.value){
				return 0;
			}
			return node1.value < node2.value ? -1 : 1;
		}
	}
	public ListNode merge(List<ListNode> listOfLists) {
		// Write your solution here/
		PriorityQueue<ListNode> minHeap = new PriorityQueue(11, new MyComparator());
		for(int i = 0; i < listOfLists.size(); i++){
			minHeap.offer(listOfLists.get(i));
		}
		ListNode sol = new ListNode(0);
		ListNode curr = sol;
		while(!minHeap.isEmpty()){
			ListNode temp = minHeap.poll();
			curr.next = temp;
			if(temp.next != null){
				minHeap.offer(temp.next);
			}
			curr = curr.next;
		}
		return sol.next;
	}

	public static int[] merge(int[][] arrayOfArrays) {
		// Write your solution here.
		PriorityQueue<Cell> minHeap = new PriorityQueue(11, new Comparator<Cell>(){
			@Override
			public int compare (Cell c1, Cell c2){
				return c1.compareTo(c2);
			}
		});
		int newLength = 0;
		for(int i = 0; i < arrayOfArrays.length; i++){
			newLength += arrayOfArrays[i].length;
			if(arrayOfArrays[i].length != 0){
				minHeap.offer(new Cell(i, 0, arrayOfArrays[i][0]));
			}
		}
		int[] sol = new int[newLength];
		int index = 0;
		while(!minHeap.isEmpty()){
			Cell cur = minHeap.poll();
			sol[index++] = cur.value;
			if(cur.column + 1 < arrayOfArrays[cur.row].length){
				Cell next = new Cell(cur.row, cur.column + 1, arrayOfArrays[cur.row][cur.column + 1]);
				minHeap.offer(next);
			}
		}
		return sol;
	}
	
	
	
	 
	public static int minCuts(String input) {
		// Write your solution here.
		if (input == null || input.length() == 0) {
			return 0;
		}
		boolean[][] findPd = new boolean[input.length() + 1][input.length() + 1];
		findPd[0][0] = true;
		int[] minCut = new int[input.length() + 1];
		for (int i = 1; i <= input.length(); i++) {
			minCut[i] = i;
			for (int j = i; j > 0; j--) {
				if (input.charAt(i - 1) == input.charAt(j - 1)) {
					if (i == j || findPd[j + 1][i - 1]){
						findPd[j][i] = true;
					}
				}
				if (findPd[j][i]) {
					minCut[i] = Math.min(minCut[i], minCut[j - 1] + 1);
				}
			}
		}
		return minCut[input.length() - 1];
	}
	
	public static boolean exist(TreeNode root, int target) {
	    // Write your solution here.
		List<Integer> sol = new ArrayList<> ();
		boolean[] found = new boolean[1];
		findPathSum(root, target, sol, found);
	    return found[0];
	}
	private static void findPathSum(TreeNode root, int target, List<Integer> sol, boolean[] found){
		if(root == null){
			return;
		}
		if(target == findSum(sol)){
			found[0] = true;
			return;
		}
		sol.add(root.key);
		findPathSum(root.left, target, sol, found);
		findPathSum(root.right, target, sol, found);
		sol.remove(sol.size() - 1);
	}
	
	private static int findSum(List<Integer> sol){
		int sum = 0;
		for(int num : sol){
			sum += num;
		}
		return sum;
	}
	
	public static int longestValidParentheses(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		int max = 0;
		char[] array = s.toCharArray();
		Deque<Integer> bracketStack = new LinkedList<> ();
		bracketStack.push(-1);
		for(int i = 0; i < array.length; i++){
			if(array[i] == '('){
				bracketStack.push(i);
			} else {
				bracketStack.pop();
				if(bracketStack.isEmpty()){
					bracketStack.push(i);
				}
				max = Math.max(max, i - bracketStack.peek());
			}
		}
		return max;
	}
	
	public boolean existSum(int[] array, int target) {
	    // Write your solution here.
		if(array.length == 2){
			return array[0] + array[1] == target ? true : false;
		}
		
		HashSet<Integer> set = new HashSet<> ();
		for(int num: array){
			if(set.contains(target - num)){
				return true;
			}
			set.add(num);
			
		}
	    return false;
	}
	
	
	private static HashSet<Integer> buildHash (int[] input){
		HashSet<Integer> set = new HashSet<> ();
		for(int i = 0; i < input.length; i++){
			set.add(input[i]);
		}

		return set;
	}
	
	public TreeNode search(TreeNode root, int key) {
	    
	    if(root == null){
	      return null;
	    }
	    
	    TreeNode curr = root;
			while(curr != null){
				if(curr.key == key){
					return curr;
				}
				else if(curr.key < key){
					curr = curr.right;
				}
				else{
					curr = curr.left;
				}
			}
			
			return null;
			
		}
	
	public static int closest(TreeNode root, int target) {
		// Write your solution here.
		int minDiff = Integer.MAX_VALUE;
		TreeNode curr= root;
		int closet = 0;
		while(curr != null){
			if(curr.key == target){
				return curr.key;
			}else if(Math.abs(curr.key - target) < minDiff){
				closet = curr.key;
				minDiff = Math.abs(curr.key - target);
			}

			if(curr.key < target){
				curr = curr.right;
			} else if(curr.key > target){
				curr = curr.left;
			} 
		}
		return closet;
	}
	
	public static int largestSmaller(TreeNode root, int target) {
		// Write your solution here.
		int ls = Integer.MIN_VALUE;
		TreeNode curr= root;
		while(curr != null){
			if(curr.key < target && curr.key > ls){
				ls = curr.key;
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}
		return ls;
	}
	
	
	
	
	
	

	private static void reverse(int[] array, int left, int right){
		if(left >= right){
			return;
		}

		reverse(array, left + 1, right - 1);
		swap(array, left, right);
	}
	
	public static int findHeight(TreeNodeP node) {
		// Write your solution here.
		if(node == null){
			return 0;
		}

		

		return 1 + Math.max(findHeight(node.parent), findHeight(node.parent));
	}
	
	private static boolean findNode(TreeNode node, TreeNode target) {
		// Write your solution here.
		if(node == null || target == null){
			return false;
		}
		if(node == target){
			return true;
		}
		return findNode(node.left, target) || findNode(node.right, target);
	}
	
	
	private static Map<Integer, Integer> buildHashMap(int[] array){
		Map<Integer, Integer> hashMap = new HashMap<> ();
		for(int num: array){
			Integer value = hashMap.get(num);
			if(value == null){
				hashMap.put(num, 1);
			} else {
				hashMap.put(num, value + 1);
			}
		}
		return hashMap;
	}

	private static int[] buildArray(int[] a, int[] b){
		int[] array = new int[a.length + b.length];
		for(int i = 0; i < a.length; i++){
			array[i] = a[i];
		}
		for(int i = 0; i < b.length; i++){
			array[a.length + i] = b[i];
		}
		return array;
	}
	
	
	public static void rotate(int[][] matrix) {
	    // Write your solution here.
		doRotate(matrix, 0, matrix.length);
		//doRotate(matrix, 0, matrix.length); Rotate 180
		//doRotate(matrix, 0, matrix.length); Rotate 270
		
	  }
	
	private static void doRotate(int[][] matrix, int offset, int size){
		if(size == 0){
			return;
		}
		if(size == 1){
			return;
		}
		int[] temp = new int [size];
		for(int i = 0; i < size; i++){
			temp[i] = matrix[offset][offset + i];
		}
		for(int i = 0; i < size - 1; i++){
			int num = matrix[i + offset][size - 1 + offset];
			matrix[i + offset][size - 1 + offset] = temp[i];
			temp[i] = num;
		}
		for(int i = size - 1; i > 0; i--){
			int num = matrix[size - 1 + offset][i + offset];
			matrix[size - 1 + offset][i + offset] = temp[size - 1 - i];
			temp[size - 1 - i] = num;
		}
		for(int i = size - 1; i >= 0; i--){
			int num = matrix[i + offset][offset];
			matrix[i + offset][offset] = temp[size - 1 - i];
			temp[size - 1 - i] = num;
		}
		for(int i = 0; i < size - 1; i++){
			matrix[offset][offset + i] = temp[i];
		}
		doRotate(matrix, offset + 1, size - 2);
	}
	
	
	public static List<String> subSets(String set) {
		// Write your solution here.
		if(set == null)
			return new ArrayList<String> ();
		
	    List sol = new ArrayList<String> ();
		StringBuilder helper = new StringBuilder();
		char[] setArray = set.toCharArray();
		Arrays.sort(setArray);
		findSubSet(setArray, helper, 0, sol); 
		return sol;
	}

	private static void findSubSet(char[] set, StringBuilder helper, int index, List<String> sol){
		if(index == set.length){
			sol.add(helper.toString());
			return;
		}
		findSubSet(set, helper.append(set[index]), index + 1, sol);
		helper.deleteCharAt(helper.length() - 1);
		while(index < set.length - 1 && set[index] == set[index + 1]){
		  index++;
		}
		findSubSet(set, helper, index + 1, sol);
		
	}

	private static HashSet<Character> buildHash (char[] input){
		HashSet<Character> set = new HashSet<> ();
		for(int i = 0; i < input.length; i++){
			set.add(input[i]);
		}

		return set;
	}
	
	private static void toIntArray(Integer[] input, int[] sol){
		for(int i = 0; i < input.length; i++){
			sol[i] =  input[i] ;
		}
	}


	private static void toIntegerArray(int[] array, Integer[] inputArray){
		for(int i = 0; i < array.length; i++){
			inputArray[i] = array[i];
		}
	}
	 
	
	public static List<Integer> zigZag(TreeNode root) {
		// Write your solution here.
		List<Integer> order = new ArrayList<Integer> ();
		if(root == null){
			return order;
		}
		Deque <TreeNode> q = new LinkedList<> (); 
		q.offer(root);
		int level = 0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int i = 0; i < size; i++){
				if(level == 0){
					TreeNode curr = q.pollLast();
					if(curr.left != null){
						q.offerLast(curr.left);
					}
					if(curr.right != null){
						q.offerLast(curr.right);
					}
					order.add(curr.key);
				} else {
					TreeNode curr = q.pollFirst();
					if(curr.left != null){
						q.offerLast(curr.left);
					}
					if(curr.right != null){
						q.offerLast(curr.right);
					}
					order.add(curr.key);
				}
			}
			if(level == 0){
				level = 1;
			} else {
				level = 0;
			}
		}
		return order;
	}


	public static int[] quickSort(int[] array) {
		// Write your solution here
		if(array.length <= 1)
			return array;
		quickSort(array, 0, array.length - 1);
		return array;

	}

	public static void quickSort(int[] array, int left, int right){
		if(left < right){
			int pivot = partition (array, left, right);
			quickSort (array, left, pivot - 1);
			quickSort(array, pivot + 1, right);
		}
	}


	
	
	
	
	/*public static int largest(int[][] matrix) {
		// Write your solution here.
		if(matrix.length == 0 || matrix[0].length == 0){
			return 0;
		}
		int[] prefixSum =new int [matrix.length];
		int result = Integer.MIN_VALUE;
		int[] SUM = new int [matrix[0].length];
		for(int i = 0; i < matrix[0].length; i++){
			for(int j = 0; j < matrix.length; j++){
				prefixSum[j] += matrix[j][i];
				
			}
			SUM[i] = longestSum(prefixSum);
			result = Math.max(result, SUM[i]);
		}
		return result;
	}

	private static int longestSum(int[] array){
		if(array == null || array.length == 0){
			return 0;
		}

		int lastMax = 0;
		int globalMax = Integer.MIN_VALUE;
		for(int i = 0; i < array.length; i++){
			lastMax = Math.max(array[i], lastMax + array[i]);
			globalMax = Math.max(globalMax, lastMax);

		}
		

		return globalMax;
	}*/

	



	/*Public ListNode reverse(ListNode head){
		if(head == null || head.next == null){
			Return head;
		}
		ListNode next = head.next;
		ListNode newHead = reverse(next);
		Next.next = head;
		head.next = null;
		Return newHead;
	}*/

	
	/*public static boolean canJump(int[] array) {
	    // Write your solution here.
		if(array == null || array.length == 0){
			return false;
		}
		
		boolean[] reachable = new boolean[array.length];
		for(int i = array.length - 2; i >= 0; i--){
			if(array[i] + i >= array.length - 1){
				reachable[i] = true;
			} else{
				for(int j = array[i]; j >= 1; j--){
					if(reachable[i + j]){
						reachable[i] = true;
						break;
					}
				}
			}
	  }
		return reachable[0];
	}*/
	
	public static boolean canJump(int[] array) {
	    // Write your solution here.
		if(array == null || array.length == 0){
			return false;
		}
		boolean[] reachable = new boolean[array.length];
		for(int i = 0; i < array.length - 1; i++){
			if(array[i] + i >= array.length - 1){
				reachable[array.length - 1] = true;
			} else {
				reachable[i + array[i]] = true;
			}
	  }
		return reachable[array.length - 1];
	}
	
	/*public static boolean canJump(int[] array) {
	    // Write your solution here.
		if(array == null || array.length == 0){
			return false;
		}
		if(array.length == 1){
		  return true;
		}
		boolean[] reachable = new boolean[array.length];
		for(int i = array.length - 2; i >= 0; i--){
			if(array[i] + i >= array.length - 1){
				reachable[i] = true;
			} else{
				for(int j = array[i]; j >= 1; j--){
					if(reachable[i + j]){
						reachable[i] = true;
						break;
					}
				}
			}
	  }
		return reachable[0];
  }*/

	
	
	/*public static String replace(String input, String s, String t) {
		// Write your solution here.
		char[] array = input.toCharArray();
		char[] arrayS = s.toCharArray();
		char[] arrayT = t.toCharArray();
		if(s.length() >= t.length()){
			return simpleReplace(array, arrayS, arrayT);
		} else {
			return longReplace(array, arrayS, arrayT);
		}
	}

	private static String longReplace(char[] input, char[] s, char[] t){
		ArrayList<Integer> ocurrence = findAllOcurrences(input, s);
		int extra = ocurrence.size() * (t.length - s.length);
		char[] newInput = new char[input.length + extra];

		int slow, fast;
		slow = newInput.length - 1;
		fast = input.length - 1;
		int lastOcurrence = ocurrence.size() - 1;
		while(fast >= 0){
			if(lastOcurrence >= 0 && fast == ocurrence.get(lastOcurrence)){		
				for(int i = t.length - 1; i >= 0; i--){
					newInput[slow--] = t[i];		
				}
				fast -= s.length;
				lastOcurrence--;
			} else{
				newInput[slow--] = input[fast--];
			}
		}

		return new String(newInput);

	}

	private static boolean equalSubstring (char[] input, int from, char[] s){
		for(int i = 0; i < s.length; i++){
			if(input [from + i] != s[i]){
				return false;
			}
		}
		
		return true;
	}
	private static ArrayList<Integer> findAllOcurrences(char [] input, char[] s){
		ArrayList<Integer> matches = new ArrayList<Integer> ();
		int i = 0;
		while(i <= input.length - s.length){
			if(equalSubstring(input, i, s)){
				matches.add(i + s.length - 1);
				i += s.length;
			} else {
				i++;
			}
			
		}
		
		return matches;
	}

	private static String simpleReplace(char[] input, char[] s, char[] t){
		int slow, fast;
		slow = fast = 0;


		while(fast < input.length){
			if(fast + s.length <= input.length && equalSubstring(input, fast, s)){
				for(int i = 0; i < t.length; i++){
					input[slow++] = t[i];
				}

				fast += s.length;
			} else {
				input[slow++] = input[fast++];
			}
		}

		return new String(input, 0, slow);
	}

	private static HashSet<Character> buildHash (char[] input){
		HashSet<Character> set = new HashSet<> ();
		for(int i = 0; i < input.length; i++){
			set.add(input[i]);
		}

		return set;
	}*/

	


	private static  void swap(char[] set, int from, int to) {
		char temp = set[from];
		set[from] = set[to];
		set[to] = temp;
	}
	public static TreeNode insert(TreeNode root, int key){
		TreeNode add = new TreeNode (key);
		if(root == null){
			return add;
		}
		TreeNode curr = root;
		while(curr != null){
			if(curr.key > key){
				if(curr.left != null){
					curr = curr.left;
				} else {
					curr.left = add;
					return root;
				}
			} else if(curr.key < key){
				if(curr.right != null){
					curr = curr.right;
				} else{
					curr.right = add;
					return root;
				}
			}
			else {
				break;
			}
		}
		return root;		
	}
	
	public static TreeNodeP insert(TreeNodeP root, int key){
		if(root == null){
			
			return new TreeNodeP(key, null);
		}
		TreeNodeP curr = root;
		while(curr != null){
			if(curr.key > key){
				if(curr.left != null){
					curr = curr.left;
				} else {
					TreeNodeP add = new TreeNodeP(key, curr);
					curr.left = add;
					return root;
				}
			} else if(curr.key < key){
				if(curr.right != null){
					curr = curr.right;
				} else{
					TreeNodeP add = new TreeNodeP(key, curr);
					curr.right = add;
					return root;
				}
			}
			else {
				break;
			}
		}
		return root;	
	}
	/*[12, 2, 6, 9, 3, 1, 2, 5, 2, -1, 3, 6]*/
	public static void main(String[] args){
		
		//int num = minCuts("ababbbabbababa");
		//System.out.print(num);
		int [] a = {4, 1, 6};
		int[] b = {3, 2};
		//System.out.println(longestPalindrome("abacbbcabcb"));;
		
		List<Integer> list = new ArrayList<> ();
		
		
		
		
		TreeNode root = null;
		
		
		root = insert(root, -5); 
		root = insert(root, 11);
		root = insert(root, 14);
		root = insert(root, 6);
		root = insert(root, 3);
		
		
		TreeNode curr = root;
		TreeNode cur2 = root;
		//TreeNode test = new TreeNode(8);
		TreeNode fuck = null;
		//System.out.println(exist(root, 21));
		//System.out.println(largestSmaller(root, 4));
		
		
		
		
		System.exit(0);
	}
}



/*ListNode add1 = new ListNode (2);
curr.next = add1;
curr = curr.next;
ListNode add2 = new ListNode (6);
curr.next = add2;
curr = curr.next;
ListNode add3 = new ListNode (9);
curr.next= add3;
curr = curr.next;
ListNode add4 = new ListNode (3);
curr.next = add4;
curr = curr.next;
ListNode add5 = new ListNode (1);
curr.next = add5;
curr = curr.next;
ListNode add6 = new ListNode (2);
curr.next = add6;
curr = curr.next;
ListNode add7 = new ListNode (5);
curr.next = add7;
curr = curr.next;
ListNode add8 = new ListNode (2);
curr.next = add8;
curr = curr.next;
ListNode add9 = new ListNode (-1);
curr.next = add9;
curr = curr.next;
ListNode add10 = new ListNode (3);
curr.next = add10;
curr = curr.next;
ListNode add11 = new ListNode (6);
curr.next = add11;
curr = curr.next;*/


/*for (int i = 0; i <= 4; ++i){
ListNode add = new ListNode (rand.nextInt(40) + 1);
curr.next = add;
curr = curr.next;
}*/



//ListNode sol = test;

/*while(sol != null){
	System.out.print(sol.value + " ");
	sol = sol.next;
}*/

//ListNode sol = cycleNode(test);
//System.out.print(sol.value + " ");
/*while(sol != null){
	System.out.print(sol.value + " ");
	sol = sol.next;
}*/


//System.out.print((-10)/(100));
/*List<Integer> list = new ArrayList<Integer> ();

list.add(1);
list.add(2);
System.out.print(list.get(0) + " " + list.get(1));
System.out.print(list.get(0) + " " + list.get(1));*/

//reeNode root = null;

//root = insert(root, 5);
//root = insert(root, 3);
//root = insert(root, 8);
//root = insert(root, 2);
//root = insert(root, 1);
//root = insert(root, 5);
//root = insert(root, 6);
//System.out.print(root.key + " ");

/*if(isCompleted(root)){
	System.out.println("It is completed ");
} else{
	System.out.println("It is not completed ");
}*/







//ArrayList<Integer> sol = getRange (root, 0,0);

/*for (int i = 0; i < sol.size(); ++i){
	System.out.print(sol.get(i) + " ");
}*/

/*
	private static void reverseEveryWord(char[] array){
		int slow, fast;
		slow = fast = 0;

		while(fast < array.length){
			while(fast < array.length && array[fast] !=' '){
				fast++;
			}
			reverseWholeSentence(array, slow, fast - 1);
			slow = ++fast;
		}
	6 9 4 10 11 5 7 3
			5 6 9 10 11
	}

	private static void reverseWholeSentence(char[] array, int left, int right){
		if(left >= right){
			return;
		}

		reverseWholeSentence(array, left + 1, right - 1);
		swap(array, left, right);
	}

	private static void swap(char[] set, int from, int to) {
		char temp = set[from];
		set[from] = set[to];
		set[to] = temp;
	} */


