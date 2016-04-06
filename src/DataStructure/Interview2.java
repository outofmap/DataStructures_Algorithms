package DataStructure;

import java.util.HashSet;

import DataStructure.SinglelyLinkedList.Node;

public class Interview2 {
	
	
	public void deleteDup(Node head){
		HashSet<Node> dataSet = new HashSet<Node>(); 
		Node current = null;
		while(head != null){
			if(dataSet.contains(head)){
				current.next = current.next.next;
			}
		}
	}

}
