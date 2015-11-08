import java.util.*;
import java.io.*;
public class bottom{
	static int atoi(String n){return Integer.parseInt(n);}
	static ArrayList<ArrayList<Integer>> g;
	static ArrayList<ArrayList<Integer>> gt;//transversal graph
	static int scc[];
	static boolean v[];
	static boolean source[];
	static ArrayList<Integer> topo;
	
	static void topodfs(int n){
		v[n] = true;
		for(int j: g.get(n))
			if(!v[j])
				topodfs(j);
		topo.add(n);
	}
	static void sccdfs(int n){
		v[n] = true;
		for(int j: gt.get(n))
			if(!v[j]){
				scc[j] = scc[n];
				sccdfs(j);
			}
	}
	public static void main(String fjdkfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0")){
			int nodos, aristas;
			String imparr[] = input.split(" ");
			nodos = atoi(imparr[0]);
			aristas = atoi(imparr[1]);
			
			g = new ArrayList<ArrayList<Integer>>();
			gt = new ArrayList<ArrayList<Integer>>();
			scc =new int[nodos];
			v = new boolean[nodos];
			topo = new ArrayList<Integer>();
			source = new boolean[nodos];
			
			imparr = br.readLine().split(" ");
			for(int i=0;i<nodos;i++)
				g.add(new ArrayList<Integer>());
			for(int i=0;i<nodos;i++)
				gt.add(new ArrayList<Integer>());
			for(int i=0;i<aristas*2;i+=2){
				int a,b;
				a = atoi(imparr[i])-1;
				b = atoi(imparr[i+1])-1;
				g.get(a).add(b);
			}
			//System.out.println(g);
			//topological sort dfs
			for(int i=0;i<nodos;i++)
				if(!v[i])
					topodfs(i);
			Arrays.fill(v, false);
			Collections.reverse(topo);
			//transversal graph
			for(int i=0;i<g.size();i++)
				for(int j:g.get(i))
					gt.get(j).add(i);
			for(int i:topo)
				if(!v[i]){
					scc[i] = i;
					sccdfs(i);
				}
			for(int i=0;i<g.size();i++)
				for(int j:g.get(i))
					if(scc[i]!=scc[j])
						source[scc[i]]=true;
			//System.out.print("------");
			//for(int i=0;i<nodos;i++)
			//	System.out.print(i+" "+scc[i]+",");
			//System.out.print("------");
			String res = "";
			for(int i=0;i<nodos;i++)
				if(!source[scc[i]])
					res+=(i+1)+" ";
			System.out.println(res.substring(0, res.length()-1));
		}
	}
}