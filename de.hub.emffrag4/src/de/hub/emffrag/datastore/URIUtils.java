package de.hub.emffrag.datastore;

import org.apache.commons.codec.binary.Base64;

public class URIUtils {

	private static final Base64 base64url = new Base64(Integer.MAX_VALUE, new byte[] {}, true);
	
	public static byte[] decode(String string) {
		// return base64url.decode(string);
		// TODO
		return base64url.decode(string.substring(0, string.lastIndexOf("_")));
	}
	
	public static String encode(byte[] bytes) {
		// return base64url.encodeAsString(bytes);
		// TODO
		return base64url.encodeAsString(bytes) + "_" + bytes[bytes.length - 1];
	}
}
