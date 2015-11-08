import java.io.*;
import java.util.*;

public class dark {
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
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner br = new Scanner();
		String input = "";
		//long ini = System.currentTimeMillis();
		StringBuilder s = new StringBuilder();
		while (true) {
			//String imparr[] = input.split(" ");
			int n = br.nextInt();//atoi(imparr[0]);
			int a = br.nextInt();//atoi(imparr[1]);
			if(a==0 && n==0)break;
			HashMap<Integer, ArrayList<Edge>> g = new HashMap<Integer, ArrayList<Edge>>();
			long total = 0;
			int initialVert = -1;
			int min = Integer.MAX_VALUE;
			for(int i=0;i<a;i++){
				//imparr = br.readLine().split(" ");
				int desde = br.nextInt();//atoi(imparr[0]);
				int hasta = br.nextInt();//atoi(imparr[1]);
				int dist =  br.nextInt();//atoi(imparr[2]);
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
			//System.out.print(total-mst(g, initialVert)+"\n");
			//System.out.println(res);
		}
		System.out.print(s);
		//long fini = System.currentTimeMillis();
		//System.out.println((fini-ini));
	}
	
		static class Scanner{
                BufferedReader br;
                StringTokenizer st;
                
                public Scanner(){
                System.setOut(new PrintStream(new BufferedOutputStream(System.out), true));
                        br = new BufferedReader(new InputStreamReader(System.in));
                }
                
                public String next(){
                        while(st == null || !st.hasMoreTokens()){
                                try { st = new StringTokenizer(br.readLine()); }
                                catch(Exception e) { throw new RuntimeException(); }
                        }
                        return st.nextToken();
                }


                public int nextInt(){
                        return Integer.parseInt(next());
                }
                
                public double nextDouble(){
                        return Double.parseDouble(next());
                }
                
                public String nextLine(){
                        st = null;
                        try { return br.readLine(); }
                        catch(Exception e) { throw new RuntimeException(); }
                }
                
                public boolean endLine(){
                        try 
                        {
                                String next = br.readLine();
                                while(next != null && next.trim().isEmpty())
                                        next = br.readLine();
                                if(next == null)
                                        return true;
                                st = new StringTokenizer(next);
                                return st.hasMoreTokens();
                        }
                        catch(Exception e) { throw new RuntimeException(); }
                }
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