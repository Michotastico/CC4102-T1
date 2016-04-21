package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TwoWay implements Mergesort {
	
	private String file_path;
	private final String PATH_F1 = "Data/twoway/f1.txt";
	private final String PATH_F2 = "Data/twoway/f2.txt";
	
	private final String PATH_G1 = "Data/twoway/g1.txt";
	private final String PATH_G2 = "Data/twoway/g2.txt";
	
	private final int RADIX = 10; // 2 is binary.
	
	private final String NEW_LINE = System.getProperty("line.separator");
	
	public TwoWay(String path){
		this.file_path = path;
	}
	@Override
	public void sort() {
		try {
			//Read the file
			BufferedReader reader = new BufferedReader(new FileReader(this.file_path));
			
			//Create files f1 and f2
			File f1 = new File(this.PATH_F1);
			File f2 = new File(this.PATH_F2);

			if (!f1.exists()) {
				f1.createNewFile();
			}
			if(!f2.exists()){
				f2.createNewFile();
			}
			
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(f1.getAbsoluteFile()));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(f2.getAbsoluteFile()));
			
			int counter = 1;
			while(true){
				String line = reader.readLine();
				if(line == null){
					break;
				}
				line = line + this.NEW_LINE;
				if(counter%2 != 0){
					writer1.write(line);
				}
				else{
					writer2.write(line);
				}
				counter++;
			}
			writer1.close();
			writer2.close();
			reader.close();
			File g1, g2;
			long s1, s2;
			for(int k = 1; k < counter; k++){
				mergesort(k);
				
				g1 = new File(this.PATH_G1);
				g2 = new File(this.PATH_G2);
				
				if(!f1.delete() || !f2.delete())
					throw new IOException();

				s1 = g1.length();
				s2 = g2.length();
				g1.renameTo(f1);
				g2.renameTo(f2);
				
				if(s1 == 0 || s2 == 0)
					break;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void mergesort(int k) throws IOException{
		BufferedReader reader1 = new BufferedReader(new FileReader(this.PATH_F1));
		BufferedReader reader2 = new BufferedReader(new FileReader(this.PATH_F2));
		
		//Create files g1 and g2
		File g1 = new File(this.PATH_G1);
		File g2 = new File(this.PATH_G2);

		if (!g1.exists()) {
			g1.createNewFile();
		}
		if(!g2.exists()){
			g2.createNewFile();
		}
		
		BufferedWriter writer1 = new BufferedWriter(new FileWriter(g1.getAbsoluteFile()));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter(g2.getAbsoluteFile()));
		
		BufferedWriter writer;
		
		String line1 = "", line2 = "";
		String oline;
		boolean running = true;
		int counter, counter1, counter2;
		counter = 1;
		
		while(running){

			if(counter%2 != 0){
				writer = writer1;
			}
			else{
				writer = writer2;
			}
			
			counter1 = 0;
			counter2 = 0;
			
			while(counter1 < k && counter2 < k){
				
				if(line1 == "")
					line1 = reader1.readLine();
				if(line2 == "")
					line2 = reader2.readLine();
				
				if(line1 == null || line2 == null){
					break;
				}
				
				//The lines have \n
				line1 = line1.replace(this.NEW_LINE, "");
				line2 = line2.replace(this.NEW_LINE, "");
				
				//To integer
				
				int i1 = Integer.parseInt(line1, this.RADIX);
				int i2 = Integer.parseInt(line2, this.RADIX);
				

				if(i1 < i2){
					oline = Integer.toString(i1) + this.NEW_LINE; //Integer.toBinaryString(i1);
					line1 = "";
					counter1++;
				}
				else{
					oline = Integer.toString(i2) + this.NEW_LINE; //Integer.toBinaryString(i2);
					line2 = "";
					counter2++;
				}
				
				writer.write(oline);
			}
			
			if(line1 == null && line2 == null){
				running = false;
				break;
			}
			
			
			else if(line1 != null && (line2 == null || line2 == "") && counter1 < k){
				oline = line1.replace(this.NEW_LINE, "") + this.NEW_LINE;
				line1 = "";
				writer.write(oline);
				counter1++;
				while(counter1 < k){
					oline = reader1.readLine();
					if(oline == null)
						break;
					
					counter1++;
					writer.write(oline);
				}
			}
			else if(line2 != null && (line1 == null || line1 == "") && counter2 < k){
				oline = line2.replace(this.NEW_LINE, "") + this.NEW_LINE;
				line2 = "";
				writer.write(oline);
				counter2++;
				while(counter2 < k){
					oline = reader2.readLine();
					if(oline == null)
						break;

					counter2++;
					writer.write(oline);
				}
			}
			
			
			
			
			counter++;
		}
		
		
		
		reader1.close();
		reader2.close();
		writer1.close();
		writer2.close();
		
	}

}
