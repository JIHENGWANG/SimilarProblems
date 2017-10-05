package solution;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
	static class node<K, V> {
		node<K, V> next;
		node<K, V> pre;
		K key;
		V value;
		
		public node(K key, V value){
			this.key = key;
			this.value =  value;
			pre = null;
			next = null;
		}
		
		public void update (K key, V value){
			this.key = key;
			this.value =  value;
		}
	}
	
	private Map<K, node<K, V>> LRUMap;
	private node<K, V> head;
	private node<K, V> tail;
	private int limit;
	
	public LRUCache(int limit) {
		this.limit = limit;
		this.LRUMap = new HashMap<> ();
	}

	public void set(K key, V value) {
		node<K,V> cur = null;
		if(LRUMap.containsKey(key)){
			cur =LRUMap.get(key);
			cur.value = value;
			delete(cur);
		} else if(LRUMap.size() < limit){
			cur = new node<K, V>(key, value);
		} else {
			cur = tail;
			delete(cur);
			cur.update(key, value);
		}
		insert(cur);
	}

	public V get(K key) {
		if(LRUMap.get(key) == null){
			return null;
		}
		
		node<K,V> cur = LRUMap.get(key);
		delete(cur);
		insert(cur);
		return cur.value;
	}
	
	private node<K, V> delete(node<K, V> cur){
		LRUMap.remove(cur.key);
		if(cur.pre != null){
			cur.pre.next = cur.next;
			
		}
		if(cur.next != null) {
			cur.next.pre = cur.pre;
		}
		if(cur == head){
			head = head.next;
		}
		if(cur == tail){
			tail = tail.pre;
		}
		
		cur.pre = cur.next = null;
		return cur;
	}
	
	private void insert(node<K, V> cur){
		LRUMap.put(cur.key, cur);
		if(head == null){
			head = tail = cur;
		} else {
			cur.next = head;
			head.pre = cur;
			head = head.pre;
		}
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
