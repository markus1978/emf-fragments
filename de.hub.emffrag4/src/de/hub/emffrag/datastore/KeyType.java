package de.hub.emffrag.datastore;

public interface KeyType<KT> {

	public KT next(KT key);
	
	public byte[] serialize(KT key);

	public KT deserialize(byte[] keyString, int offset);

	/**
	 * @return The null elements of this key type and order, i.e. the smallest possible key.
	 */
	public KT nullKey();

	public int compare(KT o1, KT o2);
}
