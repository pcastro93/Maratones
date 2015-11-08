import java.io.*;
import java.util.*;
public class honey2{
	static int dp[][][] = new int[100][100][17];
	static int ii = dp.length>>1, jj=dp[0].length>>1;
	static int atoi(String n){return Integer.parseInt(n);}
	static int solve(int f, int c, int p){
		if(p==0 || dp[f][c][p]!=0)
			return dp[f][c][p];
		dp[f][c][p] = solve(f-1,c-1,p-1)+solve(f,c-1,p-1)+solve(f-1,c,p-1)+solve(f,c+1,p-1)+solve(f+1,c+1,p-1)+solve(f+1,c,p-1);
		return dp[f][c][p];
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dp[ii][jj][0] = 1;
		solve(ii,jj,16);
		int arr[] = dp[ii][jj];
		int casos = atoi(br.readLine());
		while(casos-->0)
			System.out.println(arr[atoi(br.readLine())]);
	}
}
