package solution;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Trees {

	/*
	 * Assumptions: if root is null, then return null
	 * 
	 * Steps: recursively call function until node is null, then
	 * 		  exchange the left and right child and go back to the 
	 * 		  last level.
	 * 
	 * Return the root of tree
	 * 
	 * Time: o(n)	Space: o(logn)
	 */
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;
		return root;
	}

	/*
	 * Pre-order a tree using iteration.
	 * Assumption: if root is null, then retur a empty list
	 * 
	 * Steps: Create a stack to save the root.
	 * 		  Pop the top of stack, add it to the list, and then save
	 * 		  its right child and left child. do this repeatedly until 
	 * 		  stack is empty
	 * 
	 * Time: o(n). Space: o(n)
	 */

	public List<Integer> preOrder(TreeNode root) {
		List<Integer> sol = new ArrayList<>();
		if (root == null) {
			return sol;
		}

		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode curr = stack.pop();
			if (curr.right != null) {
				stack.push(curr.right);
			}
			if (curr.left != null) {
				stack.push(curr.left);
			}
			sol.add(curr.key);
		}
		return sol;
	}

	/*
	 * Pre-order a tree using recursion
	 * Assumptions: if the root is null, then return a empty list.
	 * 
	 * Steps: add the tree node in current level, and then recursively
	 * 		  call function on its left and right child until root is null
	 * 
	 * Return the list
	 * 
	 * Time: o(n)	Space: o(logn)
	 */
	public List<Integer> preOrderRecursion(TreeNode root) {
		// Write your solution here.
		ArrayList<Integer> sol = new ArrayList<Integer>();
		if (root != null) {
			sol.add(root.key);
			sol.addAll(preOrder(root.left));
			sol.addAll(preOrder(root.right));
		}
		return sol;
	}

	/*
	 * In-order a tree using iteration.
	 * Assumptions: if a root is empty, then return a empty list.
	 * 
	 * Steps: create a TreeNode and let it point to root. Create a Stack
	 * 		  to save the treeNode and let treeNode always go to left until
	 * 	      it is null, then pop the top of stack, add it to the list and
	 * 		  save its right child into stack. make treeNode point to its
	 * 		  right child.
	 * 
	 * Return the in order list
	 * 
	 * Time: o(n)	Space: o(n)
	 */
	public List<Integer> inOrder(TreeNode root) {
		List<Integer> sol = new ArrayList<>();
		if (root == null) {
			return sol;
		}
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode curr = root;
		while (curr != null || !stack.isEmpty()) {
			if (curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				TreeNode add = stack.pop();
				sol.add(add.key);
				curr = add.right;
			}
		}
		return sol;
	}

	/*
	 * In-order a tree recursively. 
	 * Assumption: if root is null, then return an empty list
	 * 
	 * Steps: recursively call function on left subtree until the node is 
	 * 		  empty, then return the last level. add the node of current level
	 * 		  into list. Call function recursively on node's right subtree.
	 * 
	 * 
	 * Time: o(n)	Space: o(logn)
	 */

	public void inorderRecursion(TreeNode node, ArrayList<Integer> sol) {
		if (node == null)
			return;
		inorderRecursion(node.left, sol);
		sol.add(node.key);
		inorderRecursion(node.right, sol);

	}

	/*
	 * post order a tree using iteration
	 * Assumptions: if the root is null, then return an empty list
	 * 
	 * Steps: Create a empty stack, then add root.right into stack
	 * 		  add root to stack, then go though the left subtree until
	 * 		  root is null.
	 * 		  then pop the top of stack, let it be the new root
	 * 		  if the new root has right child and this right child is on the
	 * 		  top of stack, pop the top of stack, otherwise add root into list
	 * 	      and set root to null
	 * 		  repeatedly do the steps above until stack is empty.
	 * 
	 * Return a post order list. 
	 * 
	 * Time: o(n)	space: o(n)
	 * 
	 */
	public List<Integer> postOrder(TreeNode root){
		List<Integer> sol = new ArrayList<>();
		if (root == null){
			return sol;
		}
		Deque<TreeNode> stack = new LinkedList<> ();
		do{
			while(root != null){
				if(root.left != null){
					stack.push(root.left);
				}
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if(root.right != null && root.right == stack.peek()){
				stack.pop();
				stack.push(root);
				root = root.right;
			} else {
				sol.add(root.key);
				root = null;
			}
		}while(!stack.isEmpty());
		return sol;
	}

	/*
	 * Post-order a tree recursively
	 * Assumptions: if root is null, then return the empty list
	 * 
	 * Steps: if left subtree is not null, then call function recursively
	 * 	      on left subtree until left child is null. and then do same
	 * 	      thing on current node's right subtree. Add current node into
	 * 		  list
	 */

	private void postOrderRecursion(TreeNode curr, ArrayList<Integer> sol) {
		if (curr.left != null) {
			postOrderRecursion(curr.left, sol);
		}
		if (curr.right != null) {
			postOrderRecursion(curr.right, sol);
		}

		sol.add(curr.key);
	}

	/*
	 * Print tree layer by layer
	 * Assumptions: if root is null, then return an empty list
	 * 
	 * Steps: Create an empty queue, and add the root into queue
	 * 		  save the size of current queue, then iterate the queue
	 * 		  with current size. poll the queue and save it into list
	 * 		  add its left and right child into queue if it has.
	 * 		  Do the steps above until queue is empty
	 * 
	 * return the list
	 * 
	 * Time: o(n)	Space: o(n)
	 */

	public static List<List<Integer>> layerByLayer(TreeNode root) {
		List<List<Integer>> sol = new ArrayList<List<Integer>>();
		if (root == null) {
			return sol;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			List<Integer> layer = new ArrayList<Integer>();
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode curr = q.poll();
				layer.add(curr.key);
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
			sol.add(layer);
		}
		return sol;
	}

	/*
	 * Print the tree by zigzag.
	 * 
	 * when level % 2 != 0 then we only do : pollLast, offerFirst
	 * 	    level % 2 == 0 then we only do:  pollFirst, offerLast.
	 * 
	 * There is only left right right left(Normal one) and right left left right
	 * 
	 * Time: o(n)	space: o(n)
	 */
	public static List<Integer> zigZag(TreeNode root) {
		List<Integer> order = new ArrayList<Integer>();
		if (root == null) {
			return order;
		}
		Deque<TreeNode> q = new LinkedList<>();
		q.offer(root);
		int level = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				if (level % 2 != 0) {
					TreeNode curr = q.pollLast();
					if (curr.left != null) {
						q.offerFirst(curr.left);
					}
					if (curr.right != null) {
						q.offerFirst(curr.right);
					}
					order.add(curr.key);
				} else {
					TreeNode curr = q.pollFirst();
					if (curr.right != null) {
						q.offerLast(curr.right);
					}
					if (curr.left != null) {
						q.offerLast(curr.left);
					}
					order.add(curr.key);
				}
			}
			level = level == 1 ? 0 : 1;
		}
		return order;
	}

	/*
	 * Check if a tree is balanced o(n) solution
	 * 
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}

		return heightDiff(root) != -1;
	}

	private int heightDiff(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = heightDiff(root.left);
		int right = heightDiff(root.right);
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}

	/*
	 * Check if a tree is a binary search tree.
	 * Assumptions: if the root is null, then we return true.
	 * 
	 * Steps: First step, take the int_max and int_min as boundary
	 * 		  if root value smaller then int_min or greater than init_max, then
	 * 		  return false,
	 *        Otherwise take root.value as min and max, go though the left subtree and 
	 *        right subtree. if all treeNodes have been checked, then return true. if any
	 *        in valid, return false;
	 * 
	 * Return true if a tree is a valid BST, otherwise return false.
	 * 
	 * Time: O(n). Space:(logn)
	 * 
	 */
	public boolean isBST(TreeNode root) {
		return checkBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);

	}
	private static boolean checkBST(TreeNode curr, int max, int min) {
		if (curr == null){
			return true;
		}
		if (curr.key < min || curr.key > max){
			return false;
		}
		return checkBST(curr.left, curr.key - 1, min) && checkBST(curr.right, max, curr.key + 1);

	}

	/*
	 * Check if a tree is a complete bst. 
	 * The key concept of bst: If a treeNode does not a full TreeNode, then
	 * 						   the following treeNodes must be the leaf nodes.
	 * 		 				   If a treeNode does not have left child, then its
	 * 					       right child must be null. If not, then return false.
	 * 
	 * Steps: go through the whole tree level by level.
	 * 		  Case 1: if we see a node does not have right child, then other nodes
	 * 		  must be leaf node. If any other node has children, return false.
	 * 	      Case 2: If we see a node does not have left child, then it must be a leaf node
	 * 		  if it has right child, or any other following node in same level has children,
	 * 	      return false.
	 * 		  After all node have been checked, return true.
	 * 
	 * Return a boolean
	 * 
	 * Time: O(n). Space: o(n)
	 * 
	 */
	public boolean isCompleteBST(TreeNode root){
		if(root == null){
			return true;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode> ();
		boolean noChild = false;
		q.offer(root);

		while(!q.isEmpty()){
			TreeNode curr = q.poll();
			if(curr.left == null){
				noChild = true;
			} else if(noChild){
				return false;
			} else if(curr.left != null){
				q.offer(curr.left);
			}
			if(curr.right == null){
				noChild = true;
			} else if(noChild){
				return false;
			} else if(curr.right != null){
				q.offer(curr.right);
			}
		}
		return true;
	}

	/*
	 * two sum in binary tree.
	 * Method one: use a hashset to save the visited value,
	 * 			   Once we found the hashSet contains(target - current node),
	 * 			   then return true. After all values have checked, return false.
	 * 
	 * Mathod two: two directions in-order
	 * 			   Use a normal in-order and reversed in-order, get the left value and
	 * 		       right value. if left + right == target, teturn true, if smeller than
	 * 	   		   left value, then run normal in-order to get a bigger value. if greater
	 * 			   then target, then run reversed in-order to get smaller value. After all
	 * 			   values have been checked, which is left value > right value, return false.
	 * 
	 * Return a boolean
	 *
	 *
	 */
	public boolean twoSumBSTInoder(TreeNode root, int target){
		if(root == null){
			return false;
		}
		Deque<TreeNode> leftStack = new LinkedList<> ();
		Deque<TreeNode> rightStack = new LinkedList<> ();
		TreeNode leftCurr = root;
		TreeNode rightCurr = root;
		boolean stopLeft = false;
		boolean stopRight = false;
		int leftVal = 0, rightVal = 0;
		while(true){
			while(!stopLeft){
				if(leftCurr != null){
					leftStack.push(leftCurr);
					leftCurr = leftCurr.left;
				} else {
					if(leftStack.isEmpty()){
						stopLeft = true;
					} else {
						leftCurr = leftStack.pop();
						leftVal = leftCurr.key;
						leftCurr = leftCurr.right;
						stopLeft = true;
					}
				}
			}
			while(!stopRight){
				if(rightCurr != null){
					rightStack.push(rightCurr);
					rightCurr = rightCurr.right;
				} else{
					if(rightStack.isEmpty()){
						stopRight = true;
					}else {
						rightCurr = rightStack.pop();
						rightVal = rightCurr.key;
						rightCurr = rightCurr.left;
						stopRight = true;
					}
				}
			}
			if(leftVal != rightVal && leftVal + rightVal == target){
				return true;
			} else if(leftVal + rightVal < target){
				stopLeft = false;
			} else if(leftVal + rightVal > target) {
				stopRight = false;
			}
			if(leftVal >= rightVal){
				return false;
			}
		}
	}

	public boolean twoSumBSTHashSet(TreeNode root, int target){
		if (root == null){
			return false;
		}
		Set<Integer> set = new HashSet<> ();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			TreeNode curr = queue.poll();
			if(set.contains(target - curr.key)){
				return true;
			} else {
				set.add(curr.key);
			}
			if(curr.left != null){
				queue.offer(curr.left);
			}
			if(curr.right != null){
				queue.offer(curr.right);
			}
		}
		return false;
	}

	/*
	 * Remove all TreeNode that is not in the range
	 * 
	 * Assumptions: if root is null, then return root,
	 * 
	 * Steps: Case 1: if root.key < min, then we recursively call functions on
	 * 		          root.right
	 * 		  Case 2: if toot.key > max, then we recursively call functions on
	 * 				  root.left.
	 * 		  Case 3: we recursively call functions on left and right.
	 * 
	 *  Return root.
	 *  
	 *  Time: o(n) Space: (logn)
	 */
	public TreeNode removeRange(TreeNode root, int min, int max){
		if(root == null){
			return root;
		}
		if(root.key <= min){
			return removeRange(root.right, min, max);
		}
		if(root.key >= max){
			return removeRange(root.left, min, max);
		}

		root.left = removeRange(root.left, min, max);
		root.right = removeRange(root.right, min, max);
		return root;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
