package de.hub.emffrag.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;

import com.google.common.collect.Lists;

public class FStreamURIImpl implements FURI {
	private final List<Integer> reversedSegment = new ArrayList<Integer>();
	private final List<Integer> segment = Lists.reverse(reversedSegment);
	private final int fragmentID;
	
	public FStreamURIImpl(int fragmentID) {
		super();
		this.fragmentID = fragmentID;
	}

	@Override
	public int fragment() {
		return fragmentID;
	}

	@Override
	public List<Integer> segment() {
		return segment;
	}
	
	void onDown(int featureID, int index) {
		reversedSegment.add(index);
		reversedSegment.add(featureID);
	}
	
	void onUp() {
		int size = reversedSegment.size();
		reversedSegment.remove(size-1);
		reversedSegment.remove(size-2);
	}

	@Override
	public URI fragmentation() {
		return null;
	}

	@Override
	public int hashCode() {
		return FURI.hashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return FURI.equals(this, obj);
	}
	
}