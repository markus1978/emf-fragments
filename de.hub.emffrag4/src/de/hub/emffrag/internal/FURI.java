package de.hub.emffrag.internal;

import java.util.List;

import org.eclipse.emf.common.util.URI;

/**
 * Stores segments from last to first.
 */
public interface FURI {

	public int fragment();
	public List<Integer> segment();
	public URI fragmentation();
	
	public static boolean equals(FURI self, Object obj) {
		if (obj == null || !(obj instanceof FURI)) {
			return false;
		} else {
			FURI other = (FURI)obj;
			if (other.fragment() == self.fragment() && other.segment().equals(self.segment())) {
				if (self.fragmentation() == null) {
					return other.fragmentation() == null;
				} else {					
					return self.fragmentation().toString().equals(other.fragmentation().toString());
				}
			} else {
				return false;
			}
		}
	}
	
	public static int hashCode(FURI uri) {
		return 31*(31*Integer.hashCode(uri.fragment()+1) + uri.segment().hashCode()) + (uri.fragmentation() == null ? 0 : uri.fragmentation().hashCode());
	}
	public static FURI copy(FURI uri) {
		if (uri instanceof FURIImpl) {
			return uri;
		} else {
			FURIImpl result = new FURIImpl();
			result.setFragmentation(uri.fragmentation());
			result.setFragment(uri.fragment());
			for (int i = 0; i < uri.segment().size(); i = i + 2) { // TODO remove
				result.addFeatureToSegment(uri.segment().get(i), uri.segment().get(i+1));
			}
			// result.segment().addAll(uri.segment());
			return result;
		}
	}
	public static String toString(FURI uri) {
		StringBuilder builder = new StringBuilder();
		if (uri.fragmentation() != null) {
			builder.append(uri.fragmentation().toString());
			builder.append("/");
		}
		builder.append(uri.fragment());
		for (int i = 0; i < uri.segment().size(); i = i + 2) {
			builder.append("/");
			builder.append(uri.segment().get(i));
			int index = uri.segment().get(i+1);
			if (index >= 0) {
				builder.append(":");
				builder.append(index);
			}
		}
		return builder.toString();
	}
}
