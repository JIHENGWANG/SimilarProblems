package solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FirstNonRepeating {
	static class node {
		node pre;
		node next;
		char ch;
		node(char ch){
			this.ch = ch;
			this.next = null;
			this.pre = null;
		}
		
	}
	private node head;
	private node tail;
	private Map<Character, node> single;
	private Set<Character> repeated;
	
	public FirstNonRepeating() {
		// TODO Auto-generated constructor stub
		this.single = new HashMap<> ();
		this.repeated = new HashSet<> ();
		head = tail;
	}
	
	public void read(char ch){
		if(repeated.contains(ch)){
			return;
		}
		node newNode = single.get(ch);
		if(newNode == null){
			newNode = new node(ch);
			insert(newNode);
		} else {
			delete(newNode);
		}
	}
	
	private void insert(node newNode){
		single.put(newNode.ch, newNode);
		tail.next = newNode;
		newNode.pre = tail;
		tail = tail.next;
	}
	
	private void delete(node node){
		repeated.add(node.ch);
		single.remove(node.ch);
		node.pre.next = node.next;
		node.next.pre = node.pre;
		if(node == tail){
			tail = node.pre;
		}
		node.next = node.pre = null;
	}
	
	public Character firstNonRepeated(){
		if(head == tail){
			return null;
		}
		return head.next.ch;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
