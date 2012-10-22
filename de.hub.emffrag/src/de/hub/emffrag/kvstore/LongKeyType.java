package de.hub.emffrag.kvstore;

import java.nio.ByteBuffer;

import de.hub.emffrag.datastore.KeyType;

class LongKeyType implements KeyType<Long> {
	private byte[] longToByte(long l) {
		return ByteBuffer.allocate(Long.SIZE / 8).putLong(l).array();
	}

	private long byteToLong(byte[] b, int offset) {
		return ByteBuffer.wrap(b).getLong(offset);
	}

	@Override
	public Long next(Long key) {
		return key + 1;
	}

	@Override
	public byte[] serialize(Long key) {
		return longToByte(key);
	}

	@Override
	public Long deserialize(byte[] keyString, int offset) {
		return byteToLong(keyString, offset);
	}

	@Override
	public Long nullKey() {	
		return 0l;
	}
}