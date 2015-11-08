import java.io.*;
import java.util.*;

public class largestsum{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jfkdfd[]){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null){
			ArrayList<Integer> nums =new ArrayList<Integer>();
			StringTokenizer st = new StringTokenizer(input);
			while(st.hasMoreTokens())
				nums.add(atoi(st.nextToken()));
			int maxtot = 0, maxhastaaca = 0;
			for(int i=0;i<nums.size();i++){
				maxhastaaca = Math.max(nums.get(i), maxhastaaca + nums.get(i));
				maxtot = Math.max(maxhastaaca, maxtot);
			}
			if(maxtot<0)maxtot =0;
			System.out.println(maxtot);
		}
	}
}
