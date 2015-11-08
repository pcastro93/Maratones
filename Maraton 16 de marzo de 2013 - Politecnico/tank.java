import java.io.*;
import java.util.*;
public class tank{
	static ArrayList<HashMap<Integer,Integer>> g = new ArrayList<HashMap<Integer, Integer>>();;
	static int gas[];
	static int atoi(String n){return Integer.parseInt(n);}
	static int dijkstra(int a, int b, int c){
		int min[][] = new int[c+1][g.size()];
		for(int i=0;i<min.length;i++)
			Arrays.fill(min[i], Integer.MAX_VALUE);
		min[0][a] = 0;
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.offer(new Node(a,0,0));
		while(!q.isEmpty()){
			Node act = q.poll();
			if(act.n == b)
				return act.dist;
			//me quedo y compro uno de gasolina si puedo echarle mas
			if(act.gas+1<=c && act.dist+gas[act.n]<min[act.gas+1][act.n]){
				min[act.gas+1][act.n] = act.dist+gas[act.n];
				q.offer(new Node(act.n,act.dist+gas[act.n], act.gas+1));
				}
			for(int v: g.get(act.n).keySet()){
				//si puedo viajar y es mejor
				int gasnuevo = act.gas-g.get(act.n).get(v);
				if(act.gas >= g.get(act.n).get(v) && min[gasnuevo][v] > act.dist){
					min[gasnuevo][v] = act.dist;
					q.offer(new Node(v,act.dist,gasnuevo));
				}
			}
		}
		return Integer.MAX_VALUE;
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nodos, aristas;
		String inp[] = br.readLine().split(" ");
		nodos = atoi(inp[0]);
		aristas = atoi(inp[1]);
		gas = new int[nodos];
		inp = br.readLine().split(" ");
		for(int i=0;i<nodos;i++)
			gas[i] = atoi(inp[i]);
		for(int i=0;i<nodos;i++)
			g.add(new HashMap<Integer, Integer>());
		while(aristas-->0){
			int a, b, d;
			String imparr[] = br.readLine().split(" ");
			a = atoi(imparr[0]);
			b = atoi(imparr[1]);
			d = atoi(imparr[2]);
			g.get(a).put(b, d);
			g.get(b).put(a, d);
		}
		
		int querys = atoi(br.readLine());
		while(querys-->0){
			String imparr[] = br.readLine().split(" ");
			int a, b, c;
			c = atoi(imparr[0]);
			a = atoi(imparr[1]);
			b = atoi(imparr[2]);
			int d = dijkstra(a, b, c);
			if(d==Integer.MAX_VALUE)
				System.out.println("impossible");
			else
				System.out.println(d);
		}
	}
}

class Node implements Comparable<Node>{
	int n, dist, gas;//dist es lo que ha gastado en dinero, gas lo que le queda de gasolina
	public Node(int n, int dist, int gas){
		this.n = n;
		this.dist = dist;
		this.gas = gas;
	}
	public int compareTo(Node o){
		return (int)Math.signum(this.dist - o.dist);
	}
}