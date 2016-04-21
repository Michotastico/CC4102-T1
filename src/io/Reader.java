package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
	private String file;
	private BufferedReader br;
	private int counter;
	
	public Reader(String filename){
		this.counter = 0;
		this.file = filename;		
	}
	
	public int[] getLines(int amount) throws FileNotFoundException, IOException{
		int[] returnlist = new int[amount];
		this.br = new BufferedReader(new FileReader(this.file));
		
		for(int i = 0; i < counter; i++){
			this.br.readLine();
		}
		int internal_counter = 0;
		for(int i = counter; i < counter + amount; i++){
			String line = this.br.readLine();
			if(line == null){
				break;
			}
			returnlist[internal_counter] = Integer.valueOf(line);
			internal_counter++;
		}
		this.counter += internal_counter;
		this.br.close();
				
		return returnlist;
	}
}
