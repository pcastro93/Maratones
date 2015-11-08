import java.io.*;
import java.util.*;

public class dark{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while((input = br.readLine())!=null){
			int n, v;
			StringTokenizer st = new StringTokenizer(input);
			n = atoi(st.nextToken());
			v = atoi(st.nextToken());
			if(n==0 && v==0)break;
			HashMap<String, ArrayList<Edge>> g = new HashMap<String, ArrayList<Edge>>();
			long tot = 0;
			for(int i=0;i<v;i++){
				st = new StringTokenizer(br.readLine());
				String ss = st.nextToken(), se = st.nextToken();
				int w = atoi(st.nextToken());
				if(!g.containsKey(ss))
					g.put(ss, new ArrayList<Edge>());
				if(!g.containsKey(se))
					g.put(se, new ArrayList<Edge>());
				g.get(ss).add(new Edge(ss,se,w));
				g.get(se).add(new Edge(se,ss,w));
				tot+=w;
			}
			sb.append(tot-mst(g)).append("\n");
		}
		System.out.print(sb);
	}
	static long mst(HashMap<String, ArrayList<Edge>> g){
		ArrayList<Edge> res = new ArrayList<Edge>();
		HashSet<String> visited = new HashSet<String>();
		String initialV = g.keySet().iterator().next();
		visited.add(initialV);
		PriorityQueue<Edge> edgesq = new PriorityQueue<Edge>();
		edgesq.addAll(g.get(initialV));
		long wt=0;
		while(!edgesq.isEmpty()){
			Edge e = edgesq.remove();
			if(!visited.contains(e.target)){
				res.add(e);
				wt+=e.w;
				visited.add(e.target);
				List<Edge> newEd = g.get(e.target);
				edgesq.addAll(newEd);
			}
		}
		return wt;
	}
}
class Edge implements Comparable<Edge>{
	String source;
	String target;
	int w;
	public Edge(String source, String target, int w){
		this.source = source;
		this.target = target;
		this.w = w;
	}
	public int compareTo(Edge o){
		return this.w - o.w;
	}
}