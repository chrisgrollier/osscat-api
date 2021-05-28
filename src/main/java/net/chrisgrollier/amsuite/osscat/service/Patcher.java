package net.chrisgrollier.amsuite.osscat.service;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Patcher {

	private boolean hasChanged = false;

	public static Patcher start() {
		return new Patcher();
	}

	public boolean finish() {
		return this.hasChanged;
	}

	private Patcher() {
		super();
		this.hasChanged = false;
	}

	public <S> Patcher acceptIfNotNullNotEquals(Consumer<S> consumer, S source, Supplier<S> target) {
		if (source != null && !source.equals(target.get())) {
			consumer.accept(source);
			this.hasChanged = true;
		}
		return this;
	}

	public <S> Patcher acceptIf(Consumer<S> consumer, Predicate<S> predicate, Supplier<S> target) {
		S source = target.get();
		if (predicate.test(source)) {
			consumer.accept(source);
		}
		return this;
	}

	public <S> Patcher acceptIfHasChanged(Consumer<S> consumer, S source) {
		if (this.hasChanged) {
			consumer.accept(source);
		}
		return this;
	}
}