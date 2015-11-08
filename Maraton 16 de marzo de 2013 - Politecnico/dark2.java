import java.io.*;
import java.util.*;

public class dark2 {
	static int atoi(String n) {
		return Integer.parseInt(n);
	}

	static long mst(HashMap<Integer, ArrayList<Edge>> g, int initialVertex){
		long rrr = 0;
		LinkedList<Edge> res = new LinkedList<Edge>();
		HashSet<Integer> visitedV = new HashSet<Integer>();
		visitedV.add(initialVertex);
		PriorityQueue<Edge> edgesQ = new PriorityQueue<Edge>();
		edgesQ.addAll(g.get(initialVertex));
		while(!edgesQ.isEmpty()){
			Edge e = edgesQ.poll();
			if(!visitedV.contains(e.t)){
				res.add(e);
				rrr+=e.w;
				visitedV.add(e.t);
				if(g.containsKey(e.t)){
				List<Edge> newEdges = g.get(e.t);
				edgesQ.addAll(newEdges);
				}
				}
		}
		return rrr;
	}
	public static void main(String jfkdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		long ini = System.currentTimeMillis();
		StringBuilder s = new StringBuilder();
		while ((input = br.readLine()) != null && !input.equals("0 0")) {
			String imparr[] = input.split(" ");
			int n = atoi(imparr[0]);
			int a = atoi(imparr[1]);
			HashMap<Integer, ArrayList<Edge>> g = new HashMap<Integer, ArrayList<Edge>>();
			long total = 0;
			int initialVert = -1;
			int min = Integer.MAX_VALUE;
			for(int i=0;i<a;i++){
				imparr = br.readLine().split(" ");
				int desde = atoi(imparr[0]);
				int hasta = atoi(imparr[1]);
				int dist = atoi(imparr[2]);
				if(!g.containsKey(desde))
					g.put(desde, new ArrayList<Edge>());
				if(!g.containsKey(hasta))
					g.put(hasta, new ArrayList<Edge>());
				g.get(desde).add(new Edge(desde, hasta, dist));
				g.get(hasta).add(new Edge(hasta, desde, dist));
				total+=dist;
				if(min>dist){
					min = dist;
					initialVert = desde;
				}
			}
			s.append(total-mst(g, initialVert)+"\n");
		}
		System.out.print(s);
		long fini = System.currentTimeMillis();
		System.out.println((fini-ini));
	}
}

class trio implements Comparable<trio>{
	int a, b,c;
	public trio(int a, int b, int c){
		this.a =a;
		this.b = b;
		this.c = c;
	}
	public int compareTo(trio otro){
		return (int)Math.signum(this.c - otro.c);
	}
	public String toString(){
		return a+" "+b+" "+c;
	}
}

class Edge implements Comparable<Edge>{
	int s;
	int t;
	int w;
	public Edge(int s, int t, int w){
		this.s = s;
		this.t = t;
		this.w = w;
	}
	public String toString(){
		return s+" "+t+" "+w;
	}
	public int compareTo(Edge o){
		return (this.w - o.w);
	}
}