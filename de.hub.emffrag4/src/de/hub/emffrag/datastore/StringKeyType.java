package de.hub.emffrag.datastore;

public class StringKeyType implements KeyType<String> {

	public static final StringKeyType instance = new StringKeyType();
	
	private StringKeyType() {
	}

	@Override
	public String next(String key) {
		return key + (char)0;
	}

	@Override
	public byte[] serialize(String key) {
		return key.getBytes();
	}

	@Override
	public String deserialize(byte[] keyString, int offset) {
		return new String(keyString).substring(offset);
	}

	@Override
	public String nullKey() {
		return "a";
	}

	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
}
