package org.zeroturnaround.jf2012.perf.bus.search.finder;

import java.util.LinkedList;
import java.util.List;

class Node<T> {

	private final T element;
	private final Node<T> parent;
	private final int priority;

	public Node(T element, Node<T> parent, int priority) {
		this.element = element;
		this.parent = parent;
		if (parent == null) {
			this.priority = priority;
		} else {
			this.priority = priority + parent.priority;
		}
	}

	public Node(T element, Node<T> parent) {
		this(element, parent, 0);
	}

	public T getElement() {
		return element;
	}

	public int getPathPriority() {
		return priority;
	}

	public List<T> getPathElements() {
		List<T> result = new LinkedList<T>();
		Node<T> node = this;
		result.add(node.element);
		while (node.parent != null) {
			node = node.parent;
			result.add(0, node.element);
		}
		return result;
	}

	// ----

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Node)) {
			return false;
		}
		return element.equals(((Node) o).element);
	}

	public int hashCode() {
		return element.hashCode();
	}

	public String toString() {
		return element.toString();
	}
}
