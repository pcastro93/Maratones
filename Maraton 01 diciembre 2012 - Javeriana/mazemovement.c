#include <stdio.h>
//Basic Definitions
#define WHITE 0
#define GRAY 1
#define BLACK 2
#define MAX_NODES 1000
#define oo 2147483647
//Declarations
int n;  // number of nodes
int e;  // number of edges
int capacity[MAX_NODES][MAX_NODES]; // capacity matrix
int flow[MAX_NODES][MAX_NODES];     // flow matrix
int color[MAX_NODES]; // needed for breadth-first search               
int pred[MAX_NODES];  // array to store augmenting path
int nnn[MAX_NODES];
int mmax = -1, mmin = 2147483647, posmax = -1, posmin = -1;
int min (int x, int y) {
    return x<y ? x : y;  // returns minimum of x and y
}
//A Queue for Breadth-First Search
int head,tail;
int q[MAX_NODES+2];

void enqueue (int x) {
    q[tail] = x;
    tail++;
    color[x] = GRAY;
}

int dequeue () {
    int x = q[head];
    head++;
    color[x] = BLACK;
    return x;
}
//Breadth-First Search for an augmenting path
int bfs (int start, int target) {
    int u,v;
    for (u=0; u<n; u++)
	color[u] = WHITE;
    head = tail = 0;
    enqueue(start);
    pred[start] = -1;
    while (head!=tail) {
	u = dequeue();
        // Search all adjacent white nodes v. If the capacity
        // from u to v in the residual network is positive,
        // enqueue v.
	for (v=0; v<n; v++)
	    if (color[v]==WHITE && (capacity[u][v]-flow[u][v])>0) {
		enqueue(v);
		pred[v] = u;
	    }
    }
    // If the color of the target node is black now,
    // it means that we reached it.
    return color[target]==BLACK;
}
//Ford-Fulkerson Algorithm
int max_flow (int source, int sink) {
    int i,j,u;
    // Initialize empty flow.
    int max_flow = 0;
    for (i=0; i<n; i++)
	for (j=0; j<n; j++)
	    flow[i][j] = 0;
    // While there exists an augmenting path,
    // increment the flow along this path.
    while (bfs(source,sink)) {
        // Determine the amount by which we can increment the flow.
	int increment = oo;
	for (u=sink; pred[u]>=0; u=pred[u]) {
	    increment = min(increment,capacity[pred[u]][u]-flow[pred[u]][u]);
	}
        // Now increment the flow.
	for (u=sink; pred[u]>=0; u=pred[u]) {
	    flow[pred[u]][u] += increment;
	    flow[u][pred[u]] -= increment;
	}
	max_flow += increment;
    }
    // No augmenting path anymore. We are done.
    return max_flow;
}
int gcd(int a, int b){return (b==0)?a:gcd(b,a%b);}
//Reading the input file and the main program
void read_input_file() {
    int a,b,c,i,j;
    // read number of nodes and edges
    scanf("%d",&n);
    // initialize empty capacity matrix 
    for (i=0; i<n; i++) {
	for (j=0; j<n; j++) {
	    capacity[i][j] = 0;
	}
    }
    int llevo = 0;
    // read edge capacities
    for(i=0;i<n;i++){
    	scanf("%d", &a);
    	if(mmin>a){
    		mmin = a;
    		posmin = llevo;
    	}
    	if(mmax<a){
    		mmax = a;
    		posmax = llevo;
    	}
    	nnn[llevo] = a;
    	for(j=0;j<llevo;j++){
    		int dcg = gcd(a,nnn[j]);
    		if(dcg>1){
		    	capacity[llevo][j] = dcg;
		    	capacity[j][llevo] = dcg;
		    	}
	}
	llevo++;
    }
}

int main () {
    read_input_file();
    printf("%d\n",max_flow(posmin,posmax));
    return 0;
}
