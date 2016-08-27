public interface Filter<T> {
	boolean filter(final T objects);
	void switcher(T objects);
}
