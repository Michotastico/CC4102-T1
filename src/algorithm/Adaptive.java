package algorithm;

import io.AdaptiveNode;
import io.ValidNode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Adaptive implements Mergesort {

	private String file_path;
	private int file_counter;
	
	private final int RADIX = Cons.RADIX;
	
	private final String NEW_LINE = Cons.NEW_LINE;
	
	private final String PATH = "Data/adaptive/f";
	
	public Adaptive(String path) {
		this.file_path = path;
		this.file_counter = 0;
	}
	
	@Override
	public void sort() {
		
		try {
			BufferedWriter writer;
			BufferedReader reader = new BufferedReader(new FileReader(this.file_path));
			String line = reader.readLine();
			
			if(line == null)
				throw new IOException();
			
			ArrayList<Integer> run = new ArrayList<Integer>();
			ArrayList<AdaptiveNode> files = new ArrayList<AdaptiveNode>();
			
			line = line.replace(this.NEW_LINE, "");
			int number = Integer.parseInt(line, this.RADIX);
			
			run.add(number);
			
			int currentNumber;
			boolean running = true;
			String path;
			File file;
			
			while(running){
				line = reader.readLine();
				if(line == null){
					running = false;
					break;
				}
				line = line.replace(this.NEW_LINE, "");
				currentNumber = Integer.parseInt(line, this.RADIX);
				if(currentNumber >= number){
					run.add(currentNumber);
					number = currentNumber;
				}
				else{
					number = currentNumber;
					
					path = this.PATH + this.file_counter + ".txt";
					this.file_counter++;
					
					file = new File(path);
					if (!file.exists()) {
						file.createNewFile();
					}
					
					writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
					String new_line = "";
					for(int i : run){
						new_line = Cons.toString(i);
						writer.write(new_line);
					}
					writer.close();
					
					files.add(new ValidNode(path, run.size()));
					
					run.clear();
					
					run.add(number);
					
				}
			}
			
			path = this.PATH + this.file_counter + ".txt";
			this.file_counter++;
			
			file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			String new_line = "";
			for(int i : run){
				new_line = Cons.toString(i);
				writer.write(new_line);
			}
			writer.close();
			
			files.add(new ValidNode(path, run.size()));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
