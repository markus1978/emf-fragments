package de.hub.emffrag.fragmentation;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PooledStackMultiMap<K,V> {

	public interface Nullable<T> {
		public T self();
	}

	private Stack<Stack<Nullable<V>>> stackPool = new Stack<Stack<Nullable<V>>>();
	private Map<K, Stack<Nullable<V>>> map = new HashMap<K, Stack<Nullable<V>>>();
	
	public V peek(K key) {
		Stack<Nullable<V>> stack = map.get(key);
		if (stack != null) {
			return stack.peek().self();
		} else {
			return null;
		}
	} 
	
	public V pop(K key) {
		Stack<Nullable<V>> stack = map.get(key);
		V result = null;
		if (stack != null) {
			result = stack.pop().self();
			if (stack.isEmpty()) {
				deleteStack(stack);
			}			
		}
		return result;
	}
	
	public void push(K key, Nullable<V> value) {
		Stack<Nullable<V>> stack = map.get(key);
		if (stack == null) {
			stack = newStack();
			map.put(key, stack);
		}
		stack.push(value);
	}
	
	private Stack<Nullable<V>> newStack() {
		if (!stackPool.isEmpty()) {
			return stackPool.pop();
		} else {
			return new Stack<Nullable<V>>();
		}
	}
	
	private void deleteStack(Stack<Nullable<V>> stack) {
		stackPool.push(stack);
	}
}
