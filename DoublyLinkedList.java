import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements List<T> {
	private Node head, tail;
	private int numberOfElements;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		numberOfElements = 0;
	}
	
	@Override
	public void addLast(T item) {
		//step 5b about the node
		Node newNode = new Node(item);
		if (isEmpty()) { //if list has nothing, this new node becomes both, head and taill
			head = tail = newNode;
		}
		else {
			tail.next = newNode; //point tail's next to new node and vice versa
			newNode.previous = tail;
			tail = newNode;// TODO 
		}
		numberOfElements++;
	}

	@Override
	public void addFirst(T item) {
		//step 4b: put a new node at the frontt
		Node newNode = new Node(item);
		if (isEmpty()) {
			head = tail = newNode;
		}
		else
		{
			newNode.next = head; //linking new node to current head
			head.previous = newNode;
			head = newNode;
		}
		numberOfElements++;
		
		
			
	}

	@Override
	public T get(int position) {
		//step3: check index and loop it
		if (position < 0 || position >= numberOfElements) {
			return null; 
		}
		Node current = head;
		for (int i = 0, i < position; i++){
			current = current.next;
		}
		return current.data;
	}

	@Override
	public void print() {
		//step1: start at head and fillow next	
		Node current = head;
		while (current !=null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println();

				
	}

	@Override
	public void printBackwards() {
		//step2: start at tail and go previous
		Node current = tail;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.previous;
		}
		System.out.println();
			
	}

	@Override
	public boolean remove(T item) {
		Node current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                // If it's the head node
                if (current == head) {
                    head = head.next;
                    if (head != null) head.previous = null;
                    else tail = null; 
                } 
                // If it's the tail node
                else if (current == tail) {
                    tail = tail.previous;
                    tail.next = null;
                } 
                // If it's somewhere in the middle
                else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                numberOfElements--;
                return true;
            }
            current = current.next;
        }
	
		return false;
	}

	@Override
	public boolean isEmpty() {
		//step6: simple check if size is zerp
		return numberOfElements == 0;
		return true; 
	}

	@Override
	public int getLength() {
		//step6: return the element counter
		return numberOfElements;
	
	}

	//step8: iterator here
	public Iterator<T> iterator() {
		return new DLLIterator();

		private class DLLIterator implements Iterator<T> {
			private Node current = head;

			public boolean hasNext() {
				return current != null;
			}

			public T next() {
				if (!hasNext()) throw new NoSuchElementException();
				T data = current.data;
				current = current.next;
				return data;
			}
		}


	private class Node
	{
		private T data;
		private Node next, previous;

		private Node(T data) {
			this(data,null,null);
		}

		private Node (T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.previous = prev;
		}
	}


}

