import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements List<T>, Iterable<T> {

	private Node head, tail;
	private int numberOfElements;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		numberOfElements = 0;
	}

	@Override
	public void addLast(T item) {
		Node newNode = new Node(item);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.previous = tail;
			tail = newNode;
		}
		numberOfElements++;
	}

	@Override
	public void addFirst(T item) {
		Node newNode = new Node(item);

		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
		}

		numberOfElements++;
	}

	@Override
	public T get(int position) {
		if (position < 0 || position >= numberOfElements) {
			throw new IndexOutOfBoundsException();
		}

		Node current = head;
		int index = 0;

		while (current != null) {
			if (index == position) {
				return current.data;
			}
			current = current.next;
			index++;
		}

		return null; // should never reach here
	}

	@Override
	public void print() {
		Node current = head;

		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}

		System.out.println();
	}

	@Override
	public void printBackwards() {
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

				if (current == head) {
					head = current.next;
					if (head != null) {
						head.previous = null;
					} else {
						tail = null; // list became empty
					}
				}
				else if (current == tail) {
					tail = current.previous;
					if (tail != null) {
						tail.next = null;
					}
				}
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
		return numberOfElements == 0;
	}

	@Override
	public int getLength() {
		return numberOfElements;
	}

	// =====================================================
	// ITERATOR SECTION
	// =====================================================

	@Override
	public Iterator<T> iterator() {
		return new DoublyLinkedListIterator();
	}

	private class DoublyLinkedListIterator implements Iterator<T> {

		private Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			T data = current.data;
			current = current.next;
			return data;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	

	private class Node {
		private T data;
		private Node next, previous;

		private Node(T data) {
			this(data, null, null);
		}

		private Node(T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.previous = prev;
		}
	}
}