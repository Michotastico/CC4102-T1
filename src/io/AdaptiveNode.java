package io;

import java.io.IOException;

public interface AdaptiveNode {
	public int size();
	public void open() throws IOException;
	public void close() throws IOException;
	public int getValue() throws IOException;
}
