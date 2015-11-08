import java.io.*;
import java.util.*;
public class vocabulary{
	static int atoi(String n){return Integer.parseInt(n);}
	static int WHITE = 0;
	static int GRAY = 1;
	static int BLACK = 2;
	static int MAX_NODES = 1020;
	static int oo = 2147483647;
	static int n; // number of nodes
	static int e; // number of edges
	static int capacity[][] = new int[MAX_NODES][MAX_NODES]; // capacity matrix
	static int flow[][] = new int[MAX_NODES][MAX_NODES]; // flow matrix
	static int color[] = new int[MAX_NODES]; // needed for breadth-first search
	static int pred[] = new int[MAX_NODES]; // array to store augmenting path
	static int head, tail;
	static int q[] = new int[MAX_NODES + 2];
	
	static int min(int x, int y) {return x < y ? x : y;}
	static void enqueue(int x) {
		q[tail] = x;
		tail++;
		color[x] = GRAY;
	}
	static int dequeue() {
		int x = q[head];
		head++;
		color[x] = BLACK;
		return x;
	}
	static boolean bfs(int start, int target) {
		int u, v;
		for (u = 0; u < n; u++)
			color[u] = WHITE;
		head = tail = 0;
		enqueue(start);
		pred[start] = -1;
		while (head != tail) {
			u = dequeue();
			for (v = 0; v < n; v++)
				if (color[v] == WHITE && (capacity[u][v] - flow[u][v]) > 0) {
					enqueue(v);
					pred[v] = u;
				}
		}
		return color[target] == BLACK;
	}
	static int max_flow(int source, int sink) {
		int i, j, u;
		int max_flow = 0;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				flow[i][j] = 0;
		while (bfs(source, sink)) {
			int increment = oo;
			for (u = sink; pred[u] >=0; u = pred[u])
				increment = min(increment, capacity[pred[u]][u]- flow[pred[u]][u]);
			for (u = sink; pred[u] >= 0; u = pred[u]) {
				flow[pred[u]][u] += increment;
				flow[u][pred[u]] -= increment;
			}
			max_flow += increment;
		}
		return max_flow;
	}
	
	public static void main(String jfkd[])throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while((input = br.readLine())!=null){
			StringTokenizer st = new StringTokenizer(input);
			int dic = atoi(st.nextToken()), pals = atoi(st.nextToken());
			ArrayList<pal> diccionario = new ArrayList<pal>();
			ArrayList<pal> palabs = new ArrayList<pal>();
			for(int i=0;i<dic;i++)
				diccionario.add(new pal(br.readLine()));
			for(int i=0;i<pals;i++)
				palabs.add(new pal(br.readLine()));
			int g[][] = new int[diccionario.size()+palabs.size()+2][diccionario.size()+palabs.size()+2];
			for(int i=0;i<palabs.size();i++)
				for(int j=0;j<diccionario.size();j++)
					if(diccionario.get(j).lec(palabs.get(i)))
						g[diccionario.size()+i][j] = 1;
			//fuente es diccionario.size()+palabs.size()
			//sumidero es fuente+1 es decir, diccionario.size()+palabs.size()+1
			int fuente = diccionario.size()+palabs.size();
			int sumidero = diccionario.size()+palabs.size()+1;
			for(int i=0;i<palabs.size();i++)
				g[fuente][diccionario.size()+i] = 1;
			for(int i=0;i<diccionario.size();i++)
				g[i][sumidero] = 1;
			capacity = g;
			n = g.length;
			int res = max_flow(fuente, sumidero);
			sb.append(res).append("\n");
		}
		System.out.print(sb);
	}
}

class pal{
	String cad;
	int arr[];
	public pal(String cad){
		this.cad = cad;
		this.arr = new int[26];
		for(int i=0;i<cad.length();i++)
			arr[cad.charAt(i)-'a']++;
	}
	public boolean lec(pal otra){//si con las letras que tiene this puedo formar la que llega
		for(int i=0;i<arr.length;i++)
			if(this.arr[i]<otra.arr[i])
				return false;
		return true;
	}
	public String toString(){
		return this.cad;
	}
}
