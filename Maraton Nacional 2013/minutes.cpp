#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int maxn=230;

struct edge{int v, c, w, f, r, next;};

void addEdge(int g[], edge e[], int &m, int u, int v, int c, int w){
	e[m].f=0;e[m].v=v;e[m].c=c;e[m].w=+w;e[m].r=m+1;e[m].next=g[u];g[u]=m++;
	e[m].f=0;e[m].v=u;e[m].c=0;e[m].w=-w;e[m].r=m-1;e[m].next=g[v];g[v]=m++;
}

bool bellmanFord(int g[], edge e[], int n, int s, int dis[]){
	bool u[maxn] = {0};
	int q[maxn], c[maxn]={0}, h =0, d=1;
	for(int i=0;i<n;i++)dis[i] = INT_MAX;
	dis[s]=0;u[s]=1;q[0]=s;
	while(h!=d){
		int i=q[h];
		if(++h==n+1)h=0;
		u[i] =0;
		if(c[i]++==n)return 0;
		for(int j=g[i],k;j!=-1;j=e[j].next)
			if(dis[k=e[j].v]>dis[i]+e[j].w){
				dis[k] = dis[i]+e[j].w;
				if(!u[k]){u[k]=1;q[d++]=k;if(d==n+1)d=0;}
			}
	}
	return 1;
}
int minCost(int g[], edge e[], int n, int src, int dst, int &flow){
	static int v[maxn], q[maxn], f[maxn], p[maxn];
	static bool u[maxn];
	int cost =0;
	flow = 0;
	if(!bellmanFord(g, e, n, dst, v)|| v[src]==INT_MAX)return 0;
	while(1){
		while(1){
			memset(u, 0, sizeof(u));
			u[src] = 1;
			f[src] = INT_MAX;
			q[0] = src;
			int h =0,d=1;
			while(h<d && !u[dst]){
				int i=q[h++];
				for(int j=g[i];j!=-1;j=e[j].next)
					if(!u[e[j].v] && e[j].c>e[j].f){
						if(v[e[j].v]+e[j].w==v[i]){
							f[e[j].v] = min(f[i], e[j].c-e[j].f);
							u[e[j].v] = 1;
							p[e[j].v] = e[j].r;
							q[d++] = e[j].v;
						}
					}
			}
			if(!u[dst])break;
			flow+=f[dst];
			cost+=f[dst]*v[src];
			for(int i=dst;i!=src;i=e[p[i]].v){
				e[p[i]].f-=f[dst];
				e[e[p[i]].r].f+=f[dst];
			}
		}
		int delta = INT_MAX;
		for(int i=0;i<n;i++)
			if(u[i])for(int j=g[i];j!=-1;j=e[j].next)
				if(e[j].c>e[j].f && !u[e[j].v])delta = min(delta, v[e[j].v]+e[j].w-v[i]);
		if(delta==INT_MAX)return cost;
		for(int i=0;i<n;i++)if(u[i])v[i]+=delta;
	}
	int main(){
		int r, nn, nodos;
		while(cin>>r>>nn){
			int mat[nodos=(nn+r+2)][nn+r+2][2];//0 costo //1 capacidad
			int g[nodos];
			for(int i=0;i<nodos;i++)g[i] = i;
			edge[nodos];
			int res[r][2];
			int ord[nn][2];
			for(int i=0;i<nodos;i++)
				for(int j=0;j<nodos;j++){
					e[i]
					mat[i][j][0] = oo;
					mat[i][j][1] = 0;
				}
			for(int i=0;i<r;i++)
				cin>>res[i][0]>>res[i][1];
			for(int i=0;i<nn;i++)
				cin>>ord[i][0]>>ord[i][1];
			//de res a ord
			for(int i=0;i<r;i++)
				for(int j=0;j<nn;j++){
					mat[i][r+j][0] = abs(res[i][0]-ord[j][0])+abs(res[i][1]-ord[j][1]);//Manhattan //costo
					mat[i][r+j][1] = 1;//capacidad
					cout<<"res "<<i<<" con ord "<<j<<" "<<mat[i][r+j][0]<<'\n';
				}
			int fuente = r+nn;
			int sumidero = r+nn+1;
			//fuente con cada res
			for(int i=0;i<r;i++){
				mat[fuente][i][0] = 0;
				mat[fuente][i][1] = oo;
			}
			//cada ord con sumidero
			for(int i=0;i<nn;i++){
				mat[r+i][sumidero][0] = 0;
				mat[r+i][sumidero][1] = oo;
			}
			for(int i=0;i<mat.length;i++){
				for(int j=0;j<mat[i].length;j++)
					cout<<mat[i][j][0]<<"-"<<mat[i][j][1]<<" ";
				cout<<'\n';
			}
			capacity = mat;
			int ans = minCost();
			System.out.println(ans);
		}
	
		return 0;
	}
}
