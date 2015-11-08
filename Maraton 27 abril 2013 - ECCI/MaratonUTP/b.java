import java.io.*;
import java.util.*;
public class b{
	static long expomod(long a, long b,long mod){
	    long res = 1;
	    while(b>0){
	        if((b&1)==1)
	            res=(a*res)%mod;
	    	b>>=1;
		a=((a%mod)*(a%mod))%mod;
	    }
	    return res;
	}
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = atoi(br.readLine()),a,b;
		StringBuilder sb = new StringBuilder("");
		while(t-->0){
			String div[] = br.readLine().split(" ");
			a = atoi(div[0]);
			b = atoi(div[1]);
			sb.append(expomod(a,b,1000000000)).append("\n");
		}
		System.out.print(sb);
	}
}
