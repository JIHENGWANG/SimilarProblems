package solution;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.*;

class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
      this.key = key;
    }
 }

public class ConstructTreeWithAnyOrder {
	public static TreeNode reconstructInAndPre(int[] in, int[] pre) {
		// Write your solution here.
		if(in.length == 0 && pre.length == 0){
			return null;
		}
		Map<Integer, Integer> subTree = buildMap(in);
		return buildTreeInAndPre(in, 0, in.length - 1, pre, 0, pre.length - 1,subTree);
	}
	
	public static TreeNode reconstructInAndLevel(int[] in, int[] level) {
		// Write your solution here.
		if(in.length == 0 && level.length == 0){
			return null;
		}
		Map<Integer, Integer> subTree = buildMap(in);
		List<Integer> levelList = buildList(level);
		return buildTreeInAndLevel(levelList, subTree);
	}
	
	public static TreeNode reconstructPost(int[] post) {
		// Write your solution here.
		if(post.length == 0){
			return null;
		}
		List<Integer> postList = buildList(post);
		return buildTreePost(postList, postList.size() - 1);
	}

	private static TreeNode buildTreePost(List<Integer> post, int right){
		if(post.size() == 0){
			return null;
		}
		TreeNode root = new TreeNode (post.get(right));
		List<Integer> leftList = new ArrayList<> ();
		List<Integer> rightList = new ArrayList<> ();
		for(int num : post){
			if(num < root.key){
				leftList.add(num);
			} else if(num > root.key){
				rightList.add(num);
			} else {
				continue;
			}
		}
		root.left = buildTreePost(leftList, leftList.size() - 1);
		root.right = buildTreePost(rightList, rightList.size() - 1);
		return root;
	}
	
	private static TreeNode buildTreeInAndPre(int[] in, int inLeft, int inRight, int[] pre,
			int preLeft, int preRight, Map<Integer, Integer> map){
		if(inLeft > inRight){
			return null;
		}
		TreeNode root = new TreeNode(pre[preLeft]);
		int leftSize = map.get(root.key) - inLeft;
		root.left = buildTreeInAndPre(in, inLeft, inLeft + leftSize - 1, pre, preLeft + 1, preLeft + leftSize, map);
		root.right = buildTreeInAndPre(in, inLeft + leftSize + 1, inRight, pre, preLeft +leftSize + 1, preRight, map);
		return root;
	}
	
	private static TreeNode buildTreeInAndLevel(List<Integer> level, Map<Integer, Integer> map){
		if(level.isEmpty()){
			return null;
		}
		TreeNode root = new TreeNode(level.remove(0));
		List<Integer> leftLevel = new ArrayList<> ();
		List<Integer> rightLevel = new ArrayList<> ();
		for(int num : level){
			if(map.get(num) > map.get(root.key)){
				rightLevel.add(num);
			} else {
				leftLevel.add(num);
			}
		}
		root.left = buildTreeInAndLevel(leftLevel, map);
		root.right = buildTreeInAndLevel(rightLevel, map);
		return root;
	}

	private static Map<Integer, Integer> buildMap(int[] array){
		Map<Integer, Integer> hashMap = new HashMap<> ();
		for(int i = 0; i < array.length; i++){
			Integer pos = hashMap.get(array[i]);
			if(pos == null){
				hashMap.put(array[i], i);
			}
		}
		return hashMap;
	}

	private static List<Integer> buildList(int[] array){
		List<Integer> sol = new ArrayList<> ();
		for(int num : array){
			sol.add(num);
		}
		return sol;
	}
	
	public static List<Integer> inOrder(TreeNode root) {
	    // Write your solution here.
	    if(root == null)
	      return new ArrayList<Integer>();
	    List<Integer> sol =  new ArrayList<Integer>();
	    Stack<TreeNode> node = new Stack<TreeNode> ();
	    TreeNode curr = root;
	    while(!node.isEmpty() || curr != null){
	      if(curr != null){
	        node.push(curr);
	        curr = curr.left;
	      }
	      else{
	        TreeNode child = node.pop();
	        sol.add(child.key);
	        curr = child.right;
	      }
	    }
	    return sol;
	  }
	
	public static List<Integer> preOrder(TreeNode root) {
	    // Write your solution here.
	    ArrayList<Integer> sol = new ArrayList<Integer>();
	    if(root != null){
	      sol.add(root.key);
	      sol.addAll(preOrder(root.left));
	      sol.addAll(preOrder(root.right));
	    }
	    return sol;
	  }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.exit(0);

	}

}
