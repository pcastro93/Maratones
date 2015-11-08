import java.io.*;
import java.util.*;

public class random {
	static int atoi(String n) {
		return Integer.parseInt(n);
	}

	static int k,n,e;
	static long mat[];
	static long tot;
	static ArrayList<ArrayList<Integer>> g;
	public static void main(String jfkdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while ((input = br.readLine()) != null && !input.equals("0 0 0")) {
			String imparr[] = input.split(" ");
			k = atoi(imparr[0]);
			n = atoi(imparr[1]);
			e = atoi(imparr[2]);
			g = new ArrayList<ArrayList<Integer>>();
			mat = new long[n];
			tot = 0;
			int fin = (1<<n);
			for(int i=0;i<fin;i++)
				g.add(new ArrayList<Integer>());
			for(int i=0;i<e;i++){
				imparr = br.readLine().split(" ");
				int desde = atoi(imparr[0]);
				int hasta = atoi(imparr[1]);
				g.get(desde).add(hasta);
				g.get(hasta).add(desde);
			}
			
			for(int i=0;i<fin;i++){
				int or = 0;
				int c = 1;
				dfs(c, or, i,""+i);
				System.out.println("----");
			}
			boolean puede = true;
			for(int i=0;i<n && puede;i++){
				System.out.println((mat[i]/(double)tot) + " mat "+mat[i]+"  "+tot);
//				if(mat[i]/(double)tot<0.25 || mat[i]/(double)tot>0.75)
//					puede = false;
				}
			if(puede)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
	static void dfs(int c, int or, int no,String cam){
		//System.out.println(no);
		if(c>k)
			return;
		if(c==k){
			or &=no;
			for(int i=0;i<n;i++)
				if(((or>>i)&1)==1)
					mat[i]++;
			tot++;
			System.out.println(cam);
			return;
		}
		for(int nodo: g.get(no))
			dfs(c+1, or|no, nodo,cam+","+nodo);
	}
}