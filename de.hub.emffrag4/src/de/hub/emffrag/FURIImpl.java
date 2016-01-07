package de.hub.emffrag;

import java.util.ArrayList;
import java.util.List;

public class FURIImpl implements FURI {

	private final List<Integer> segment = new ArrayList<Integer>();
	private int fragment = -1;
		
	public int fragment() {
		return fragment;
	}
	
	public void setFragment(int id) {
		this.fragment = id;
	}
	
	public List<Integer> segment() {
		return segment;
	}
	
	public void addFeatureToSegment(int featureID, int slotIndex) {
		segment.add(featureID);
		segment.add(slotIndex);
	}
	
	@Override
	public int hashCode() {
		return FURI.hashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return FURI.equals(this, obj);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(fragment());
		for (int i = 0; i < segment().size(); i = i + 2) {
			builder.append("/");
			builder.append(segment().get(i));
			int index = segment().get(i+1);
			if (index >= 0) {
				builder.append(":");
				builder.append(index);
			}
		}
		return builder.toString();
	}
}