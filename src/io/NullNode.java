package io;

public class NullNode implements AdaptiveNode {

	@Override
	public int size() {
		return 0;
	}

	@Override
	public void open() {
	}

	@Override
	public void close() {
	}

	@Override
	public int getValue() {
		return 0;
	}

}
