package de.hub.emffrag.bm.benchmark.util;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.buffer.BoundedFifoBuffer;

public abstract class BreathFirstGenerator<E> {

	public abstract E create(E parent, boolean frag);
	
	private Buffer interQueue = null;
	private final int base;
	private final int fragmentSize;
	private final int modelSize;
	
	public BreathFirstGenerator(int base, int fragmentSize, int modelSize) {
		super();
		this.base = base;
		this.fragmentSize = fragmentSize;
		this.modelSize = modelSize;
	}
	
	private class Node {
		E value;
		int children = 0;
		
		Node(E value) {
			this.value = value;
		}
	}

	@SuppressWarnings("unchecked")
	public int generateFragment(E root) {		
		int size = 1;
		Buffer intraQueue = new BoundedFifoBuffer(fragmentSize);
		intraQueue.add(new Node(root));
		while (size < fragmentSize) {
			Node parent = (Node)intraQueue.get();
			while (parent.children < base && size < fragmentSize) {
				intraQueue.add(new Node(create(parent.value, false)));
				parent.children++;
				size++;
			}
			if (parent.children >= base) {
				intraQueue.remove();
			}
		}
		interQueue.addAll(intraQueue);
		return size;
	}
	
	@SuppressWarnings("unchecked")
	public int generateAll() {		
		interQueue = new BoundedFifoBuffer(modelSize);
		E root = create(null, false);
		int size = generateFragment(root);		
		while (size < modelSize) {
			Node parent = (Node)interQueue.get();
			while (parent.children < base && size < modelSize) {
				E fragmentRoot = create(parent.value, true);
				size += generateFragment(fragmentRoot);
				parent.children++;
			}
			if (parent.children >= base) {
				interQueue.remove();
			}
		}
		return size;
	}
	
	private static int count = 0;
	
	public static final void main(String args[]) {
		int size = new BreathFirstGenerator<String>(2, 10, 100) {
			@Override
			public String create(String parent, boolean frag) {
				if (frag) {
					System.out.println("");
				}
				System.out.print(++count + " (" + parent + ") ");
				return "" + count;
			}			
		}.generateAll();
		System.out.println(size);
	}
}
