import java.io.*;
import java.util.*;

public class raggedright {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static void main(String fjkdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		ArrayList<String> parr = new ArrayList<String>(); 
		while ((input = br.readLine()) != null) 
			parr.add(input);
		int max = -1;
		for(String c: parr)
			max = (max<c.length())?c.length():max;
		int sum = 0;
		for(int i=0;i<parr.size()-1;i++){
			sum+=(int)Math.pow((max-parr.get(i).length()),2);
		}
		System.out.println(sum);
	}
}