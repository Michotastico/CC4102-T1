package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import algorithm.*;

public class Main {

	public static void main(String[] args) {
		//power 5
		int exponent = 20;
		int k_upper = (int) (exponent * (2.0/3.0));
		int k = (int) Math.pow(2, k_upper);
		try {
			testMerge(exponent, k);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void testMerge(int power, int k) throws IOException{
		
		String path = "Data/Dataset/Power"+power+"/";
		String[] data_files = {"data0.2.txt", "data0.5.txt", "data0.8.txt"};
		
		Mergesort merge;
		BufferedWriter writer;
		File file;
		
		long initial_time, final_time, delta;
		String line1, line2, line3;
		for(String data : data_files){
			//TWO WAY
			initial_time = System.currentTimeMillis();
			merge = new TwoWay(path+data);
			merge.sort();
			final_time = System.currentTimeMillis();
			delta = final_time - initial_time;
			line1 = "Initial_time : "+initial_time+Cons.NEW_LINE;
			line2 = "Final_time : "+final_time+Cons.NEW_LINE;
			line3 = "Delta : "+delta;
			
			
			file = new File("Data/twoway"+power+data);
			if(!file.exists())
				file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			writer.write(line1);
			writer.write(line2);
			writer.write(line3);
			writer.close();
			
			//ADAPTIVE
			initial_time = System.currentTimeMillis();
			merge = new Adaptive(path+data);
			merge.sort();
			final_time = System.currentTimeMillis();
			delta = final_time - initial_time;
			line1 = "Initial_time : "+initial_time+Cons.NEW_LINE;
			line2 = "Final_time : "+final_time+Cons.NEW_LINE;
			line3 = "Delta : "+delta;
			
			
			file = new File("Data/adaptive"+power+data);
			if(!file.exists())
				file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			writer.write(line1);
			writer.write(line2);
			writer.write(line3);
			writer.close();
			
			//TWOPHASE
			initial_time = System.currentTimeMillis();
			merge = new TwoPhaseMultiway(path+data, k);
			merge.sort();
			final_time = System.currentTimeMillis();
			delta = final_time - initial_time;
			line1 = "Initial_time : "+initial_time+Cons.NEW_LINE;
			line2 = "Final_time : "+final_time+Cons.NEW_LINE;
			line3 = "Delta : "+delta;
			
			
			file = new File("Data/twophase"+power+data);
			if(!file.exists())
				file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			writer.write(line1);
			writer.write(line2);
			writer.write(line3);
			writer.close();
		}
		
	}
	

}
