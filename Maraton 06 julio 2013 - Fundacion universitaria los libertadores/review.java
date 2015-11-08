import java.io.*;
import java.util.*;
public class review{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0 0")){
			String div[] = input.split(" ");
			int k = atoi(div[0]) ,n = atoi(div[1]);
			boolean prob[] = new boolean[n+1];
			int rev[][] = new int[n][k];
			String univ[]= new String[n+1];
			for(int i=0;i<n;i++){
				div = br.readLine().split(" ");
				univ[i+1] = div[0];
				for(int j=1;j<k+1;j++)
					rev[i][j-1] = atoi(div[j]);
			}
			int veces[] = new int[n+1];
			for(int i=0;i<n;i++){
				boolean ya[] = new boolean[n+1];
				for(int j=0;j<k;j++){
					if(ya[rev[i][j]])//si ya lo reviso
						prob[rev[i][j]] = true;
					veces[rev[i][j]]++;
					ya[rev[i][j]] = true;
					if(univ[rev[i][j]].equals(univ[i+1]))//si se revisa a si mismo
						prob[rev[i][j]] = true;
				}
			}
			//for(boolean c: prob)
			//	System.out.print(c+" ");
			//System.out.println();
			int tot =0;
			for(int i=1;i<n+1;i++)
					if(prob[i] || veces[i]!=k)
						tot++;
			System.out.println((tot==0?"NO":tot)+" PROBLEMS FOUND");
		}
	}
}
