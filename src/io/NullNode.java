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
	public String getLine() {
		return "";
	}

	@Override
	public int compareTo(AdaptiveNode o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
