import java.io.*;
import java.util.*;

public class movie {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static int hamming(String cad1, String cad2){
		int dif = 0;
		for(int i=0;i<cad1.length();i++)
			if(cad1.charAt(i)!=cad2.charAt(i))
				dif++;
		return dif;
	}
	public static void main(String jkfdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("movie.in"));
		String input = "";
		input = br.readLine();
		int tot = atoi(input.split(" ")[0]);
		int q = atoi(input.split(" ")[1]);
		ArrayList<String> db = new ArrayList<String>(tot);
		for(int i=0;i<tot;i++)
			db.add(br.readLine());
		for(int i=0;i<q;i++){
			input = br.readLine();
			int min = Integer.MAX_VALUE;
			int first = -1;
			for(int j=0;j<db.size();j++){
				if(db.get(j).length()>=input.length()){
					for(int k=0;k<=db.get(j).length()-input.length();k++){
						int tmp =hamming(input, db.get(j).substring(k, k+input.length()));
						if(min>tmp){
							first = j;
							min = tmp;
						}
					}
				}
			}
			System.out.println(first+1);
		}
		
	}
}
