package linearalgebra;

public class ILinkedList {
	int size = 0;
	Node tail;
	Node head = null;
	
	public class Node { //nested class
		int data;
		Node nextNode;
		
		public Node (int data) { //constructor overload
			this.data = data;
			nextNode = null;
		}
	}
	public void append(int data) { //add data to the back of the list
		Node newNode = new Node(data);
		if (head == null) { //if the list is empty
			head = newNode;
			tail = newNode;
			size++;
		} else { //if the list has elements
			tail.nextNode = newNode;
			tail = newNode;
			size++;
		}
	}
	public void prepend(int data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
			size++;
		} else {
			newNode.nextNode = head;
			head = newNode;
			size++;
		}
	}
	public void printList() {
		Node currNode = head;
	    // Traverse through the LinkedList
	    while (currNode != null) {
	    // Print the data at current node
	    System.out.println(currNode.data);
	    // Go to next node
	    currNode = currNode.nextNode;
	    }
	}
	public int getSize() { return size; }
	public static void main(String[] args) {
		ILinkedList bob = new ILinkedList();
		bob.append(33);
		bob.append(69);
		bob.append(420);
		bob.prepend(20);
		bob.printList();
		System.out.println("Size: " + bob.getSize());
		System.out.println("this compiles!");
	}
}
