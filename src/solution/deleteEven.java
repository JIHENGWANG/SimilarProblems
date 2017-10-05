package solution;



public class deleteEven {
	
	public static ListNode delete(ListNode head){
		if(head == null){
			return head;
		}
		while(head != null && head.value % 2 == 0){
			head = head.next;
		}
		ListNode curr = head;
		ListNode pre = null;
		while(curr != null){
			if(curr.value % 2 == 0){
				ListNode next = curr.next;
				pre.next = next;
				curr.next = null;
				curr = pre.next;
			} else {
				pre = curr;
				curr = curr.next;
			}
		}
		return head;
	}
	
	public static void buildNode(ListNode one){
		ListNode curr = one;
		for(int i = 0; i < 12; i += 2){
			curr.next = new ListNode(i);
			curr = curr.next;
		}
		for(int i = 12; i <= 29; i++){
			curr.next = new ListNode(i);
			curr = curr.next;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode test = new ListNode(0);
		ListNode curr = test;
		curr.next = new ListNode(4);
		curr = curr.next;
		curr.next = new ListNode(6);
		curr = curr.next;
		curr.next = new ListNode(7);
		curr = curr.next;
		curr.next = new ListNode(3);
		curr = curr.next;
		curr.next = new ListNode(2);
		curr = curr.next;
		curr.next = new ListNode(5);
		curr = curr.next;
		curr.next = new ListNode(9);
		curr = curr.next;
		curr.next = new ListNode(10);
		curr = curr.next;
		curr.next = new ListNode(19);
		curr = curr.next;
		//buildNode(test);
		test = delete(test);
		while(test != null){
			System.out.print(test.value + " ");
			test = test.next;
		}
		System.exit(0);
	}

}
