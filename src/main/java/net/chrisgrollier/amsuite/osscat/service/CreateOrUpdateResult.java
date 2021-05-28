package net.chrisgrollier.amsuite.osscat.service;

import java.util.concurrent.atomic.AtomicBoolean;

public class CreateOrUpdateResult<T> {
	
	private boolean created;
	private T result;
	
	public static <S> CreateOrUpdateResult<S> of(boolean created, S result) {
		return new CreateOrUpdateResult<S>(created, result);
	}
	
	public static <S> CreateOrUpdateResult<S> of(AtomicBoolean created, S result) {
		return new CreateOrUpdateResult<S>(created.get(), result);
	}
	
	private CreateOrUpdateResult(boolean created, T result) {
		super();
		this.created = created;
		this.result = result;
	}

	public boolean isCreated() {
		return created;
	}

	public T getResult() {
		return result;
	}
	
}