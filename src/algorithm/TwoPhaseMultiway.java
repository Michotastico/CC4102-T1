package algorithm;

import io.ReaderNode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TwoPhaseMultiway implements Mergesort {
	
	private String file_path;
	private int size;
	
	private final int RADIX = Cons.RADIX;
	
	private final String NEW_LINE = Cons.NEW_LINE;
	
	private final String PATH = "Data/twophase/f";
	
	public TwoPhaseMultiway(String path, int k) {
		this.file_path = path;
		this.size = k;
	}
	
	@Override
	public void sort() {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.file_path));
			BufferedWriter writer;
			int counter = 0;
			boolean running = true;
			String line = "";
			String new_path = "";
			ArrayList<Integer> inner_list = new ArrayList<Integer>();
			File file;
			
			
			while(running){
				
				inner_list.clear();
				
				for(int i = 0; i < this.size; i++){
					line = reader.readLine();
					if(line == null){
						running = false;
						break;
					}
					line = line.replace(this.NEW_LINE, "");
					int number = Integer.parseInt(line);
					inner_list.add(number);					
				}
				
				if(inner_list.size() == 0){
					running = false;
					break;
				}
				
				Collections.sort(inner_list);
				
				new_path = this.PATH + counter + ".txt";
				counter ++;
				
				file = new File(new_path);
				if (!file.exists()) {
					file.createNewFile();
				}
				
				writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
				String new_line = "";
				for(int i : inner_list){
					new_line = Cons.toString(i);
					writer.write(new_line);
				}
				writer.close();
				
			}
			
			reader.close();
			
			// INIT ARRAY OF READER NODES.
			ArrayList<ReaderNode> files = new ArrayList<ReaderNode>();
			new_path = "";
			
			for(int i = 0; i < counter; i++){
				new_path = new_path = this.PATH + i + ".txt";
				files.add(new ReaderNode(new_path));
			}
			
			File end_file = new File(this.PATH+".txt");
			if(!end_file.exists())
				end_file.createNewFile();
			
			writer = new BufferedWriter(new FileWriter(end_file.getAbsoluteFile()));
			
			running = true;
			ArrayList<ReaderNode> tmp = new ArrayList<ReaderNode>();
			
			while(running){
				//Update files with valid ReaderNode.
				for(ReaderNode node : files){
					if(node.isValid())
						tmp.add(node);
					else
						node.close();
				}
				
				files = new ArrayList<ReaderNode>(tmp);
				tmp.clear();
				
				if(files.size() == 0){
					running = false;
					break;
				}
				
				//Now search the lower one
				ReaderNode lower_node = files.get(0);
				int lower_value = lower_node.getValue();
				int current_value;
				for(ReaderNode node : files){
					current_value = node.getValue();
					if(current_value <= lower_value){
						lower_value = current_value;
						lower_node = node;
					}
				}
				
				writer.write(Cons.toString(lower_value));
				lower_node.next();
				
			}
			
			
			// FINISH ARRAY OF READER NODES.
			for(ReaderNode node : files)
				node.close();
			
			writer.close();
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
