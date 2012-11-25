package org.zeroturnaround.jf2012.perf.bus.search.finder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.zeroturnaround.jf2012.perf.bus.StopDemander;
import org.zeroturnaround.jf2012.perf.bus.search.Finder;
import org.zeroturnaround.jf2012.perf.bus.search.Production;

public class DepthFirstFinder<T> implements Finder<T> {
	
	private final Production<T>[] productions;
	
	public DepthFirstFinder(Production<T>[] productions) {
		this.productions = productions;
	}

	public List<T> find(T start, T goal, StopDemander stop) {
		Node<T> startNode = new Node<T>(start, null);
		if (start.equals(goal)) {
			return startNode.getPathElements();
		}
		
		List<Node<T>> queue = new LinkedList<Node<T>>();
		Set<T> visited = new HashSet<T>();
		
		queue.add(startNode);
		visited.add(start);
		
		while (!queue.isEmpty()) {
			Node<T> node = queue.remove(0);
			
			List<T> successors = ProductionUtil.getSuccessors(node.getElement(), productions);
			for (T successor : successors) {
				if (!visited.contains(successor)) {
					Node<T> subNode = new Node(successor, node);
					if (successor.equals(goal)) {
						// The goal is found
						return subNode.getPathElements();
					}
					queue.add(subNode);
					visited.add(successor);					
				}
			}
		}
		// Not found
		return null;
	}
}
