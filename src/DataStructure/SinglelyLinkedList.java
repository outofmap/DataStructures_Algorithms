package DataStructure;

public class SinglelyLinkedList<T> {

	private Node head;
	private Node tail;

	class Node {
		private T data;
		Node next;

		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private void addFirst(T data) {
		// 왜 final을 사용하나요?
		final Node newNode = new Node(data, null);
		// newNode를 head로
		newNode.data = data;
		newNode.next = head;
		head = newNode;
		// tail이 없었다면, head이자 tail인 노드가 1개 추가된 상태이므로
		if (tail == null) {
			tail = head;
		}
	}

	private void add(T data, Node node) {
		final Node newNode = new Node(data, null);
		newNode.data = data;
		newNode.next = node.next;
		node.next = newNode;
	}

	private void addTail(T data) {
		final Node newNode = new Node(data, null);
		newNode.data = data;

		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}
}
