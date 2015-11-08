import java.util.*;
import java.io.*;
public class digitalroulette{
	public static long evaluarPolinomio(int x,int N, int K,int coef[]){
		long r = 0;
		long pot = 1;
		for(int i=0;i<=K;i++){
			if(coef[i]!=0){
				r=(r+(coef[i]*pot))%(N+1);
			}
			pot=(pot*x)%(N+1);
		}
		return r;
	}
	public static void main(String args[])throws IOException{
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(!(input = br.readLine()).equals("0 0")){
			int N = Integer.parseInt(input.split(" ")[0]);
			int M = Integer.parseInt(input.split(" ")[1]);
			int K = Integer.parseInt(br.readLine());
			int coef[]= new int[K+1];
			input = br.readLine();
			for(int i=0;i<input.split(" ").length;i++){
				coef[i]= Integer.parseInt(input.split(" ")[i]);
			}
			Set<Long>vals = new HashSet<Long>();
			for(int i=0;i<=M;i++){
				vals.add(evaluarPolinomio(i,N,K,coef));
			}
			System.out.println(vals.size());
		}
	}
}
