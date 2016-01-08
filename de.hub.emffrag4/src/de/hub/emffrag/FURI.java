package de.hub.emffrag;

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
			return other.fragment() == self.fragment() && other.segment().equals(self.segment());
		}
	}
	
	public static int hashCode(FURI uri) {
		return 31*Integer.hashCode(uri.fragment()+1) + uri.segment().hashCode();
	}
	public static FURI copy(FURI uri) {
		if (uri instanceof FURIImpl) {
			return uri;
		} else {
			FURIImpl result = new FURIImpl();
			result.setFragment(uri.fragment());
			result.segment().addAll(uri.segment());
			return result;
		}
	}
}
