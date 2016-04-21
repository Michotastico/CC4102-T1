package algorithm;

import io.AdaptiveNode;
import io.NullNode;
import io.ReaderNode;
import io.ValidNode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.crypto.NullCipher;

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
			
			

			running = true;
			
			while(running){
				
				if(files.size() == 1){
					running = false;
					break;
				}
				
				Collections.sort(files);
				
				AdaptiveNode first_node = files.remove(0);
				AdaptiveNode second_node = files.remove(0);
				
				AdaptiveNode new_node = merge(first_node, second_node);
				files.add(new_node);
				
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private AdaptiveNode merge(AdaptiveNode a, AdaptiveNode b) throws IOException{
		a.open();
		b.open();
		
		AdaptiveNode return_node = new NullNode();
		
		String tmp_path = this.PATH+"TEMP.txt";
		
		File file = new File(tmp_path);
		if (!file.exists()) {
			file.createNewFile();
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(tmp_path));
		
		String line1 = "";
		String line2 = "";
		
		String oline = "";
		
		while(true){
			if(line1 == "")
				line1 = a.getLine();
			if(line2 == "")
				line2 = b.getLine();
			
			if(line1 == null || line2 == null){
				break;
			}
			
			int i1 = Integer.parseInt(line1, this.RADIX);
			int i2 = Integer.parseInt(line2, this.RADIX);
			
			if(i1 < i2){
				oline = Cons.toString(i1);
				line1 = "";
			}
			else{
				oline = Cons.toString(i2);
				line2 = "";
			}
			
			
			writer.write(oline);
		}
		
		if(line1 != null && line2 == null){
			oline = line1 + this.NEW_LINE;
			writer.write(oline);
			oline = "";
			while(true){
				oline = a.getLine();
				if(oline == null)
					break;
				oline = oline + this.NEW_LINE;
				writer.write(oline);
			}
		}
		
		else if(line2 != null && line1 == null){
			oline = line2 + this.NEW_LINE;
			writer.write(oline);
			oline = "";
			while(true){
				oline = b.getLine();
				if(oline == null)
					break;
				oline = oline + this.NEW_LINE;
				writer.write(oline);
			}
		}
		
				
		writer.close();

		a.close();
		b.close();
		
		File f1 = new File(a.getPath());
		File f2 = new File(b.getPath());
		
		if(!f1.delete() || !f2.delete())
			throw new IOException();

		file.renameTo(f1);
		
		return_node = new ValidNode(a.getPath(), (a.size() + b.size()));
		
		
		
		
		return return_node;
		
	}

}
