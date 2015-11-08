import java.io.*;
import java.util.*;
public class honey{
	static int atoi(String n){return Integer.parseInt(n);}
	static int mod(int a, int b, int c){
		if(a+b<=0)return c+a+b;
		if(a+b>c)return a+b-c;
		return (a+b);
	}
	static void con(int a, int b, boolean mat[][]){
		mat[a][b] = mat[b][a] = true;
	}
	static boolean mat[][] = new boolean[300][300];
	static ArrayList<p> nodos;
	static int jo[][];
	static int llega = 8;
	
	static void imp(){
		int fin = 20;
		for(int i=0;i<fin;i++){
			for(int j=0;j<jo[i].length;j++)
				System.out.print(jo[i][j]+" ");
			System.out.println();
			}
	}
	static void crear(){
		nodos= new ArrayList<p>();
		for(int i=1;i<=8;i++){
			if(i==1)
				nodos.add(new p(1,1));
			for(int k=1;k<=6*i-6;k++){
				nodos.add(new p(i,k));
			}
		}
		for(int i=1;i<7;i++)
			mat[0][i] = mat[i][0] =true;
		for(int i=2,c=1;i<=7;i++){
			for(int k=1;k<=6*i-6;k++,c++){
				int n1 = nodos.indexOf( new p(i,mod(k,1,6*i-6)) );
				int n2 = nodos.indexOf( new p(i,mod(k,-1,6*i-6)) );
				//mat[c][n1] = mat[n1][c] = true;
				//mat[c][n2] = mat[n2][c] = true;
				con(n1, c, mat);
				con(n2, c, mat);
				if((k-1)%(i-1)==0){
					int algo = ((k-1)/(i-1))-1;
					for(int u=0;u<3;u++){
					int n3 =nodos.indexOf(new p(i+1,mod(k,algo+u,6*i))); 
					con(n3, c,mat);
					}
				}else{
					int algo = (k-1)/(i-1);
					for(int u=0;u<2;u++){
						int n3 =nodos.indexOf(new p(i+1,mod(k,algo+u, 6*i))); 
						con(n3, c,mat);
						}
				}
			}
		}
		jo = new int[nodos.size()][15];
		for(int i=0;i<jo.length;i++)
			Arrays.fill(jo[i], 0);
	}
	
	static int solve(int n, int p){
		if(p==0 || jo[n][p]!=0)
			return jo[n][p];
		int sum = 0;
		for(int i=0;i<mat.length;i++)
			if(mat[i][n])
				sum+=solve(i, p-1);
		jo[n][p] = sum;
		return jo[n][p];
	}
	
	public static void main(String jfkdfd[])throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String input= "";
		crear();
		jo[0][0] = 1;//caso base
		solve(0,14);
		solve(0,13);
		int arr[] = jo[0];
		int casos = atoi(br.readLine());
		while(casos-->0){
			int n=atoi(br.readLine());
			System.out.println(arr[n]);
		}
	}
}

class p{
	int i,k;
	public p(int i, int k){
		this.i = i;
		this.k = k;
	}
	public boolean equals(Object o){
		p po = (p)o;
		return po.i==i && po.k==k;
	}
	public String toString(){
		return this.i+" "+this.k;
	}
}