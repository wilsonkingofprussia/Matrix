package linearalgebra;

public class NewLinkedList <S> {
	public Node<S> head;
	public void add(S data) {
		Node<S> temp = new Node<S>(data);
		if (head != null) {
			temp.setNext(head);
		}
		head = temp;
	}
	public String toString() {
		String output = new String();
		Node<S> curr = head;
		while(curr != null) {
			output += curr + ", ";
			curr = curr.getNext();
		}
		return output;
	}
	public static void main(String[] args) {
		NewLinkedList<Double> bob = new NewLinkedList();
		bob.add(12.3);
		bob.add(12.4);
		bob.add(18.3);
		bob.add(21.3);
		bob.add(37.3);
		bob.add(40.3);
		System.out.println(bob);
	}
}
