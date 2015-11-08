import java.io.*;
import java.util.*;
public class boxes {
	
	static int po[];
	static int izq, der, sol=0, med, maxi=-1;
	static int atoi(String n){return Integer.parseInt(n);}
	static int lb(int p, int l, int b){//poblacion ciudad i, el limite y las cajas
		//encuentro el minimo x tal que p/x<=l con x<=b
		int x=1;
		for(;p/x+(p%x==0?0:1)>l && x<=b;x++);
		return x;
	}
	static boolean puede( int limit, int remainboxes ){
	  int x, denominador, rb = remainboxes;
	  for( x = 0; x < po.length; x++){
	    denominador = lb(po[x], limit, rb);
	    remainboxes -= denominador;
	    if( remainboxes < 0) return false;
	  }
	  return true;
	}
	public static void main(String jfk[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while(!(input = br.readLine()).equals("-1 -1")){
			maxi = -1;
			StringTokenizer st= new StringTokenizer(input);
			int c = atoi(st.nextToken()), b=atoi(st.nextToken());
			po = new int[c];
			for(int i=0;i<c;i++){
				po[i] = atoi(br.readLine());
				maxi = Math.max(maxi, po[i]);
			}
			
			for( izq = 1, der = maxi; izq <= der ;  ){
		        med = (izq + der) / 2;
		        if(puede(med, b)){
		           sol = med;
		           der = med - 1; 
		        }else izq = med + 1;
	        }
			
			sb.append(sol).append("\n");
			br.readLine();
		}
		System.out.print(sb);
	}
}