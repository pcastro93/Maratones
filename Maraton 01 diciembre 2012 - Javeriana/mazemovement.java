import java.io.*;
import java.util.*;

public class mazemovement {

	// Basic Definitions
	static int WHITE = 0;
	static int GRAY = 1;
	static int BLACK = 2;
	static int MAX_NODES = 1000;
	static int oo = 2147483647;
	// Declarations
	static int n; // number of nodes
	static int e; // number of edges
	static int capacity[][] = new int[MAX_NODES][MAX_NODES]; // capacity matrix
	static int flow[][] = new int[MAX_NODES][MAX_NODES]; // flow matrix
	static int color[] = new int[MAX_NODES]; // needed for breadth-first search
	static int pred[] = new int[MAX_NODES]; // array to store augmenting path
	static int nnn[] = new int[MAX_NODES];
	static int mmin = 2000000001, mmax = -1, posmmin = -1, posmmax = -1;

	static int min(int x, int y) {
		return x < y ? x : y;
	}

	// A Queue for Breadth-First Search
	static int head, tail;
	static int q[] = new int[MAX_NODES + 2];

	static int atoi(String n) {
		return Integer.parseInt(n);
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

	// Breadth-First Search for an augmenting path
	static boolean bfs(int start, int target) {
		int u, v;
		for (u = 0; u < n; u++)
			color[u] = WHITE;
		head = tail = 0;
		enqueue(start);
		pred[start] = -1;
		while (head != tail) {
			u = dequeue();
			// Search all adjacent white nodes v. If the capacity
			// from u to v in the residual network is positive,
			// enqueue v.
			for (v = 0; v < n; v++)
				if (color[v] == WHITE && (capacity[u][v] - flow[u][v]) > 0) {
					enqueue(v);
					pred[v] = u;
				}
		}
		// If the color of the target node is black now,
		// it means that we reached it.
		return color[target] == BLACK;
	}

	// Ford-Fulkerson Algorithm
	static int max_flow(int source, int sink) {
		int i, j, u;
		// Initialize empty flow.
		int max_flow = 0;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				flow[i][j] = 0;
		// While there exists an augmenting path,
		// increment the flow along this path.
		while (bfs(source, sink)) {
			// Determine the amount by which we can increment the flow.
			int increment = oo;
			for (u = sink; pred[u] >= 0; u = pred[u]) {
				increment = min(increment, capacity[pred[u]][u]
						- flow[pred[u]][u]);
			}
			// Now increment the flow.
			for (u = sink; pred[u] >= 0; u = pred[u]) {
				flow[pred[u]][u] += increment;
				flow[u][pred[u]] -= increment;
			}
			max_flow += increment;
		}
		// No augmenting path anymore. We are done.
		return max_flow;
	}

	// Reading the input file and the main program
	static int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

	static void read_input_file() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			int a, i, j;
			// read number of nodes and edges
			n = atoi(br.readLine());
			// initialize empty capacity matrix
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					capacity[i][j] = 0;
				}
			}
			int llevo = 0;
			for (i = 0; i < n; i++) {
				a = atoi(br.readLine());
				if (mmin > a) {
					mmin = a;
					posmmin = llevo;
				}
				if (mmax < a) {
					mmax = a;
					posmmax = llevo;
				}
				int kk = 0;
				nnn[llevo] = a;
				llevo++;
				for (kk = 0; kk < llevo - 1; kk++) {// no tomo en cuenta el que
													// acabo de agregar
					int dcg = gcd(a, nnn[kk]);
					if (dcg != 1) {
						capacity[llevo - 1][kk] = dcg;// llevo-1 es la posicion
														// del que lei en la
														// lista de nodos
						capacity[kk][llevo - 1] = dcg;
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public static void main(String fjdk[]) {
		read_input_file();
		System.out.println(max_flow(posmmin, posmmax));
	}
}
