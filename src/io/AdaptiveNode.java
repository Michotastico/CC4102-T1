package io;

import java.io.IOException;

public interface AdaptiveNode extends Comparable<AdaptiveNode>{
	public int size();
	public void open() throws IOException;
	public void close() throws IOException;
	public String getLine() throws IOException;
	public String getPath();
}
