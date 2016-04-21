package algorithm;

public class Merge {
	public static int[] apply(int[] a, int [] b){
		int size = a.length + b.length;
		int [] returnlist = new int [size];
		
		int i = 0, j = 0, counter = 0;
		
		while(i < a.length && j < b.length){
			int _a = a[i], _b = b[i];
			if(_a > _b){
				returnlist[counter] = _b;
				j++;
			}
			else{
				returnlist[counter] = _a;
				i++;
			}
			counter ++;
		}
		if(i < a.length){
			for(int c = i; c < a.length; c++){
				returnlist[counter] = a[c];
				counter ++;
			}
		}
		else if(j < b.length){
			for(int c = j; c < b.length; c++){
				returnlist[counter] = b[c];
				counter ++;
			}
		}
		
		return returnlist;
	}
}
