import java.io.*;
import java.util.*;

public class duel {

	static class Node{
		public final int name;
		public final HashSet<Edge> inEdges;
		public final HashSet<Edge> outEdges;
		public Node(int name) {
			this.name = name;
			inEdges = new HashSet<Edge>();
			outEdges = new HashSet<Edge>();
		}
		public Node addEdge(Node node){
			Edge e = new Edge(this, node);
			outEdges.add(e);
			node.inEdges.add(e);
			return this;
		}
		@Override
		public String toString() {
			return name+"";
		}
	}

	static class Edge{
		public final Node from;
		public final Node to;
		public Edge(Node from, Node to) {
			this.from = from;
			this.to = to;
		}
		@Override
		public boolean equals(Object obj) {
			Edge e = (Edge)obj;
			return e.from == from && e.to == to;
		}
	}
	static int atoi(String n){return Integer.parseInt(n);}

	public static void main(String fdjfkd[])throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!= null && !input.equals("0 0")){
			String ent[] = input.split(" ");
			int nodos = atoi(ent[0]);
			int enlaces = atoi(ent[1]);
			ArrayList<Node> allNodes = new ArrayList<Node>();
			for(int i=0;i<nodos;i++)
				allNodes.add(new Node(i));
			while(enlaces-->0){
				ent = br.readLine().split(" ");
				int a = atoi(ent[0])-1;
				int b = atoi(ent[1])-1;
				allNodes.get(a).addEdge(allNodes.get(b));
			}

			//L <- Empty list that will contain the sorted elements
			ArrayList<Node> L = new ArrayList<Node>();

			//S <- Set of all nodes with no incoming edges
			HashSet<Node> S = new HashSet<Node>(); 
			for(Node n : allNodes)
				if(n.inEdges.size() == 0)
					S.add(n);
			boolean varios = S.size()>1;
			boolean cycle = false||S.isEmpty();
			//while S is non-empty do
			while(!S.isEmpty()){
				if(S.size()>1)
					varios = true;
				if(S.isEmpty()){
					cycle = true;
					break;
					}
				//remove a node n from S
				Node n = S.iterator().next();
				S.remove(n);

				//insert n into L
				L.add(n);

				//for each node m with an edge e from n to m do
				for(Iterator<Edge> it = n.outEdges.iterator();it.hasNext();){
					//remove edge e from the graph
					Edge e = it.next();
					Node m = e.to;
					it.remove();//Remove edge from n
					m.inEdges.remove(e);//Remove edge from m

					//if m has no other incoming edges then insert m into S
					if(m.inEdges.isEmpty())
						S.add(m);
				}
			}
			//Check to see if all edges are removed
			//boolean cycle = false;
			for(Node n : allNodes)
				if(!n.inEdges.isEmpty()){
					cycle = true;
					break;
				}
			if(cycle)
				System.out.println("0");
			else if(varios)
				System.out.println("2");
			else
				System.out.println("1");
		}
	}
}
