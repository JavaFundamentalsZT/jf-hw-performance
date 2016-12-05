package org.zeroturnaround.jf.performance.bus.search.finder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.zeroturnaround.jf.performance.bus.ActionStoppedException;
import org.zeroturnaround.jf.performance.bus.StopDemander;
import org.zeroturnaround.jf.performance.bus.search.Estimator;
import org.zeroturnaround.jf.performance.bus.search.Finder;
import org.zeroturnaround.jf.performance.bus.search.Production;

public class AStarFinder<T> implements Finder<T> {
	
	private final Production<T>[] productions;
	private final Estimator<T> estimator;
	
	public AStarFinder(Production<T>[] productions, Estimator<T> estimator) {
		this.productions = productions;
		this.estimator = estimator;
	}

	public List<T> find(T start, T goal, StopDemander stop) {
		Node<T> startNode = new Node<T>(start, null, estimator.estimate(start, goal));
		if (start.equals(goal)) {
			return startNode.getPathElements();
		}
		
		Map<Integer,Node<T>> queue = new TreeMap<Integer,Node<T>>();
		Map<T,Node<T>> visited = new HashMap<T,Node<T>>();
		
		queue.put(startNode.getPathPriority(), startNode);
		visited.put(start, startNode);		
		
		while (!queue.isEmpty()) {
			Iterator<Node<T>> it = queue.values().iterator();
			Node<T> node = it.next();
			it.remove();
			
			List<T> successors = ProductionUtil.getSuccessors(node.getElement(), productions);
			for (T successor : successors) {
				if (stop.isDemandingStop()) {
					throw new ActionStoppedException();
				}
				
				Node<T> subNode = new Node(successor, node, estimator.estimate(successor, goal));
				Node<T> existing = visited.get(successor);
				int priority = subNode.getPathPriority();
				
				if (existing == null || existing.getPathPriority() > priority) {
					visited.put(successor, subNode);
					if (existing == null && successor.equals(goal)) {
						// The goal is found
						return subNode.getPathElements();
					}
					queue.put(priority, subNode);
				}
			}
		}
		// Not found
		return null;
	}
}
