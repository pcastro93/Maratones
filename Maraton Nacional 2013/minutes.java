import java.io.*;
import java.util.*;
public class minutes{
	static int WHITE = 0;
	static int GRAY = 1;
	static int BLACK = 2;
	static int MAX_NODES = 1000;
	static int oo = 5000000;
	static int n; // number of nodes
	static int e; // number of edges
	static int capacity[][] = new int[MAX_NODES][MAX_NODES]; // capacity matrix
	static int flow[][] = new int[MAX_NODES][MAX_NODES]; // flow matrix
	static int color[] = new int[MAX_NODES]; // needed for breadth-first search
	static int pred[] = new int[MAX_NODES]; // array to store augmenting path
	static int d[] = new int[MAX_NODES];
	static int head, tail;
	static int q[] = new int[MAX_NODES + 2];
	
	static int min(int x, int y) {
		return x < y ? x : y;
	}
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
	static boolean dijkstra(int start, int target){
		int u,v;
		for(u=0;u<n;u++)
			color[u] = WHITE;
		PriorityQueue<Par> cola = new PriorityQueue<Par>();
		cola.offer(new Par(start, 0));
		d = new int[capacity.length];
		boolean v[] = new boolean[capacity.length];
		Arrays.fill(d, oo);
		pred[start] = -1;
		while (!cola.isEmpty()){
			u = cola.pop();
			for(int i=0;i<capacity.length;i++)
				if(capacity[u][i][1]-flow[u][i]>0 && // se puede enviar algo por ahi
					d[i]>d[u]+capacity[u][i][0]//relajacion
				){
					pred[i] = u;
					d[i] = d[u]+capacity[u][i][0];
				}
		}
	}
	static int max_flow(int source, int sink) {
		int i, j, u;
		int max_flow = 0;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				flow[i][j] = 0;
		while (bfs(source, sink)) {
			int increment = oo;
			int incrementc = d[sink];//incremento del costo
			for (u = sink; pred[u] >= 0; u = pred[u])
				increment = min(increment, capacity[pred[u]][u]- flow[pred[u]][u]);
			for (u = sink; pred[u] >= 0; u = pred[u]) {
				flow[pred[u]][u] += increment;
				flow[u][pred[u]] -= increment;
			}
			max_flow += incrementc;
		}
		return max_flow;
	}
	
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));	
	static int atoi(String n){return Integer.parseInt(n);}
	
	public static void main(String jfkd[])throws Exception{
		String input = "";
		while((input = br.readLine())!=null){
			StringTokenizer st = new StringTokenizer(input);
			int r = atoi(st.nextToken()),nn = atoi(st.nextToken());
			int mat[][][] = new int[nn+r+2][nn+r+2][2];//0 costo //1 capacidad
			int res[][] = new int[r][2];
			int ord[][] = new int[nn][2];
			for(int i=0;i<mat.length;i++)
				for(int j=0;j<mat[i].length;j++){
					mat[i][j][0] = oo;
					mat[i][j][1] = 0;
				}
			for(int i=0;i<r;i++){
				st = new StringTokenizer(br.readLine());
				res[i][0] = atoi(st.nextToken());
				res[i][1] = atoi(st.nextToken());
			}
			for(int i=0;i<nn;i++){
				st = new StringTokenizer(br.readLine());
				ord[i][0] = atoi(st.nextToken());
				ord[i][1] = atoi(st.nextToken());
			}
			//de res a ord
			for(int i=0;i<r;i++)
				for(int j=0;j<nn;j++){
					mat[i][r+j][0] = Math.abs(res[i][0]-ord[j][0])+Math.abs(res[i][1]-ord[j][1]);//Manhattan //costo
					mat[i][r+j][1] = 1;//capacidad
					//System.out.println("res "+i+" con ord "+j+" "+mat[i][r+j][0]);
				}
			int fuente = r+nn;
			int sumidero = r+nn+1;
			//fuente con cada res
			for(int i=0;i<r;i++){
				mat[fuente][i][0] = 0;
				mat[fuente][i][1] = 1;
			}
			//cada ord con sumidero
			for(int i=0;i<nn;i++){
				mat[r+i][sumidero][0] = 0;
				mat[r+i][sumidero][1] = 1;
			}
			//for(int i=0;i<mat.length;i++){
			//	for(int j=0;j<mat[i].length;j++)
			//		System.out.print(mat[i][j][0]+"-"+mat[i][j][1]+" ");
			//	System.out.println();
			//}
			//GREEDY NO funciona...
			int ans = max_flow(fuente, sumidero);
			System.out.println(ans);
		}
	}
}
class Par implements Comparable<Par>{
	int nod, d;
	public Par(int nod, int d){
		this.nod = nod;
		this.d = d;
	}
	public int compareTo(Par o){
		return (int)Math.sign(this.d-o.d);
	}
}

