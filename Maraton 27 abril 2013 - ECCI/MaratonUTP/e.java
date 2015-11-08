import java.io.*;
import java.util.*;
import java.math.*;
public class e{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = atoi(br.readLine());
		int bm=1,em=0, b,e;
		double max = -1.0;
		while(n-->0){
			String div[] = br.readLine().split(" ");
			b = atoi(div[0]);
			e = atoi(div[1]);
			double tmp = (double)(e)*Math.log(b);
			if(tmp>max){
				max = tmp;
				bm = b;
				em = e;
			}
		}
		br.readLine();
		BigInteger res = BigInteger.valueOf(bm).pow(em);
		System.out.println(res);
	}
}
