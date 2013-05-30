package de.hub.emffrag.datastore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base32;

import com.google.common.base.Throwables;

/**
 * A file system based implementation of {@link IDataStore}. It stores all
 * fragments in a given directory.
 * 
 * It has one major flaw: to provide features based on key order, this
 * implementation maintains a {@link TreeMap} and does not rely on (non
 * existing) file system features. Thus this implementation will consume main
 * memory proportionally to number of fragments.
 * 
 * TODO this implementation is broken. It definitely does not work in linux and
 * may also not work on other systems.
 */
public class FileSystemDataStore implements IBaseDataStore {

	private static final Base32 base32url = new Base32(Integer.MAX_VALUE, new byte[] {});

	private final File directory;
	private final TreeMap<byte[], File> fileTree = new TreeMap<byte[], File>(InMemoryDataStore.byteComparator);

	public FileSystemDataStore(String name, File directory, boolean clear) {
		this.directory = directory;

		if (clear) {
			for (File file : directory.listFiles()) {
				file.delete();
			}
		} else {
			for (File file : directory.listFiles()) {
				fileTree.put(base32url.decode(file.getName()), file);
			}
		}
	}

	@Override
	public void close() {
		// do nothing
	}
	
	@Override
	public void flush() {
		// do nothing	
	}

	@Override
	public byte[] ceiling(byte[] key) {
		return fileTree.ceilingKey(key);
	}

	@Override
	public byte[] floor(byte[] key) {
		return fileTree.floorKey(key);
	}

	@Override
	public InputStream openInputStream(byte[] key) {
		String fileName = new String(base32url.encode(key));
		File file = new File(directory, fileName);
		if (!file.exists()) {
			return null;
		}
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			Throwables.propagate(e);
			return null;
		}
	}

	@Override
	public OutputStream openOutputStream(byte[] key) {
		String fileName = new String(base32url.encode(key));
		File file = new File(directory, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
				fileTree.put(base32url.decode(file.getName()), file);
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}
		try {
			return new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			Throwables.propagate(e);
			return null;
		}
	}

	@Override
	public boolean check(byte[] key) {
		String fileName = new String(base32url.encode(key));
		File file = new File(directory, fileName);
		return !file.exists();
	}

	@Override
	public boolean checkAndCreate(byte[] key) {
		String fileName = new String(base32url.encode(key));
		File file = new File(directory, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
				fileTree.put(key, file);
				return true;
			} catch (IOException e) {
				Throwables.propagate(e);
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void delete(byte[] key) {
		String fileName = new String(base32url.encode(key));
		File file = new File(directory, fileName);
		if (file.exists()) {
			file.delete();
			fileTree.remove(key);
		}
	}

	@Override
	public void drop() {
		throw new UnsupportedOperationException("Not implemented.");
	}

}
