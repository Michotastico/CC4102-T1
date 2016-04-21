package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import algorithm.Cons;

public class ReaderNode {
	
	private final int RADIX = Cons.RADIX;
	
	private final String NEW_LINE = Cons.NEW_LINE;
	
	private BufferedReader reader;
	private String next_line;
	
	public ReaderNode(String file) throws IOException{
		this.reader = new BufferedReader(new FileReader(file));
		this.next_line = this.reader.readLine();
	}
	
	public boolean isValid(){
		return (this.next_line != null);
	}
	
	public int getValue(){
		String line = this.next_line.replace(this.NEW_LINE, "");
		int number = Integer.parseInt(line, this.RADIX);
		return number;
	}
	
	public void next() throws IOException{
		this.next_line = this.reader.readLine();
	}
	
	public void close() throws IOException{
		this.reader.close();
	}
}
