package de.hub.emffrag.datastore;

public class StringKeyType implements KeyType<String> {

	public static final StringKeyType instance = new StringKeyType();
	
	private StringKeyType() {
	}

	@Override
	public String next(String key) {
		int lastChar = key.charAt(key.length() - 1);
		if (lastChar < 'z') {
			lastChar++;
			return key.substring(0, key.length() - 1) + (char)lastChar;
		} else {
			return key + 'a';
		}
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

}
