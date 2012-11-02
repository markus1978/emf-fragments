package de.hub.emffrag.fragmentation;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

import com.google.common.base.Throwables;

import de.hub.emffrag.fragmentation.FObjectImpl;

public class CacheController extends Thread {
	private final ReferenceQueue<FObjectImpl> userObjectsReferenceQueue = new ReferenceQueue<FObjectImpl>();
	
	public CacheController() {
		super("EMF-Fragments cache controller");
	}

	@Override
	public void run() {
		try {
			Reference<? extends FObjectImpl> reference;
			while ((reference = userObjectsReferenceQueue.remove()) != null) {
				UserObjectsCache.delegateRemovedReference(reference);
			}
		} catch (InterruptedException e) {
			Throwables.propagate(e);
		}
	}		
	
	private static CacheController instance = null;
	
	public static CacheController instance() {
		if (instance == null) {
			instance = new CacheController();
			instance.start();
		}
		return instance;
	}

	public ReferenceQueue<FObjectImpl> getAllUserObjectsReferenceQueue() {	
		return userObjectsReferenceQueue;
	}

}
