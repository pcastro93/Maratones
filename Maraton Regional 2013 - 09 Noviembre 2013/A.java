import java.io.*;
import java.util.*;
public class A{
	static int WHITE = 0;
	static int GRAY = 1;
	static int BLACK = 2;
	static int MAX_NODES = 10000;
	static int oo = 2147483647;
	static int n; // number of nodes
	static int e; // number of edges
	static int capacity[][] = new int[MAX_NODES][MAX_NODES]; // capacity matrix
	static int flow[][] = new int[MAX_NODES][MAX_NODES]; // flow matrix
	static int color[] = new int[MAX_NODES]; // needed for breadth-first search
	static int pred[] = new int[MAX_NODES]; // array to store augmenting path
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
	static int max_flow(int source, int sink) {
		int i, j, u;
		int max_flow = 0;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				flow[i][j] = 0;
		while (bfs(source, sink)) {
			int increment = oo;
			for (u = sink; pred[u] >= 0; u = pred[u])
				increment = min(increment, capacity[pred[u]][u]- flow[pred[u]][u]);
			for (u = sink; pred[u] >= 0; u = pred[u]) {
				flow[pred[u]][u] += increment;
				flow[u][pred[u]] -= increment;
			}
			max_flow += increment;
		}
		return max_flow;
	}

	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jfkd[])throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null){
		int tam = atoi(input);
		char[][]mat = new char[tam][tam];
		for(int i=0;i<tam;i++){
			input = br.readLine();
			for(int j=0;j<input.length();j++){
				mat[i][j] = input.charAt(j);
			}
		}
		int cont =0;
		int sal[][] = new int[mat.length][mat.length];//bipartite conjunto 1
		int totsal = 0, totent = 0;
		for(int i=0;i<tam;i++){
			for(int j=0;j<tam;j++)
				if(mat[i][j]=='X' && (sal[i][j]=-1)==-1)cont+=(j==tam-1?0:1);//lo aumenta despues del for :)
				else sal[i][j] = totsal = cont;
			cont++;
		}
		int ent[][] = new int[mat.length][mat.length];//bipartite conjunto 2
		for(int i=0;i<tam;i++){
			for(int j=0;j<tam;j++)
				if(mat[j][i]=='X' && (ent[j][i]=-1)==-1)cont += (j==tam-1?0:1);//lo aumenta despues del for :)
				else ent[j][i] = totent = cont;
			cont++;
		}
		/*for(int i=0;i<tam;i++){
			for(int j=0;j<tam;j++)
				System.out.print(sal[i][j]+","+ent[i][j]+" ");
				//System.out.print(ent[i][j]);
			System.out.println();
		}*/
		//System.out.println("totsal "+totsal+" totent "+totent);
		totsal++;
		totent++;
		totent -=totsal;
		n = totsal+totent-1+2;//nodos :)
		//System.out.println("n es "+n+" "+totsal+" "+totent);
		capacity = new int[n][n];//source, sink
		flow = new int[n][n];//flujo
		for(int i=0;i<tam;i++){
			for(int j=0;j<tam;j++)
				if(ent[i][j]!=-1 && sal[i][j]!=-1)
					capacity[sal[i][j]][ent[i][j]] = 1;
		}
		for(int i=0;i<totsal;i++)
			capacity[n-2][i] = 1;//source
		for(int i=0;i<totent;i++)
			capacity[i+totsal][n-1] = 1;//sink
		/*for(int i=0;i<capacity.length;i++){
			for(int j=0;j<capacity[i].length;j++){
				System.out.print(capacity[i][j]+" ");
			}
			System.out.println();
		}*/
		System.out.println(max_flow(n-2, n-1));
		}
	}
}
