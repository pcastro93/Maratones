import java.io.*;
import java.util.*;

public class digit{

	static int atoi(String n){return Integer.parseInt(n);}
	static int mult(int n){
		int res = 1;
		while(n>0){
			res*=n%10;
			n/=10;
		}
		return res;
	}
	public static void main(String jkdfdp[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0")){
			HashSet<Integer> nums = new HashSet<Integer>();
			ArrayList<Integer> res = new ArrayList<Integer>();
			int n = atoi(input);
			while(!nums.contains(n)){
				nums.add(n);
				res.add(n);
				if(n<10)break;
				n = mult(n);
			}
			StringBuilder sb = new StringBuilder("");
			for(int i=0;i<res.size();i++)
				sb.append(res.get(i)).append(" ");
			sb.deleteCharAt(sb.length()-1);
			System.out.println(sb);
		}
	}
}