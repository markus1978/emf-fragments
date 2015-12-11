package de.hub.emffrag.datastore;

import java.nio.ByteBuffer;

public class LongKeyType implements KeyType<Long> {
	
	public final static LongKeyType instance = new LongKeyType();
	
	private LongKeyType() {
		
	}
	
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

	@Override
	public int compare(Long o1, Long o2) {
		return o1 == o2 ? 0 : (o1 < o2 ? -1 : 1); 
	}

	
}