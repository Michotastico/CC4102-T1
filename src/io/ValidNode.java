package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import algorithm.Cons;

public class ValidNode implements AdaptiveNode {
	
	private final int RADIX = Cons.RADIX;
	
	private final String NEW_LINE = Cons.NEW_LINE;
	
	private String path;
	private int size;
	private BufferedReader reader;
	
	public ValidNode(String path, int size) {
		this.path = path;
		this.size = size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void open() throws IOException {
		reader = new BufferedReader(new FileReader(this.path));
		
	}

	@Override
	public void close() throws IOException{
		reader.close();
		
	}

	@Override
	public int getValue() throws IOException {
		String next_line = reader.readLine();
		String line = next_line.replace(this.NEW_LINE, "");
		int number = Integer.parseInt(line, this.RADIX);
		return number;
	}

}
