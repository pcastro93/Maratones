import java.io.*;
import java.util.*;
public class MoneyMatters{
	static boolean visited[];
	static boolean ppp;
	static int sum = 0;
	static boolean dfs(int nodi, ArrayList<Integer>mat[], int cost[]){
		Stack<Integer> pila = new Stack<Integer>();
		pila.push(nodi);
		int suma = 0;
		while(!pila.isEmpty()){
		int act = pila.pop();
		visited[act] = true;
		suma+= cost[act];
			for(int i=0;i<mat[act].size();i++)
				if(!visited[mat[act].get(i)] && !pila.contains(mat[act].get(i)))
					pila.push(mat[act].get(i));
		}
		return (suma==0);
	}
	static void dfs2(int nodi, ArrayList<Integer>mat[], int cost[]){
		if(visited[nodi])return;
		visited[nodi] = true;
		sum+=cost[nodi];
		for(int i=0;i<mat[nodi].size();i++)
			if(!visited[mat[nodi].get(i)])
				dfs2(mat[nodi].get(i), mat, cost);
		ppp = (sum==0);
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String input = br.readLine();
		String input = "";
		int test = Integer.parseInt(br.readLine());
		while(test-->0){
		ppp = true;
		input = br.readLine();
		int nodos = Integer.parseInt(input.split(" ")[0]);
		int aristas = Integer.parseInt(input.split(" ")[1]);
		int nods[] = new int[nodos];
		visited = new boolean[nodos];
		ArrayList<Integer> mat[] = new ArrayList[nodos];
		for(int i=0;i<nodos;i++){
			mat[i] = new ArrayList<Integer>();
			nods[i] = Integer.parseInt(br.readLine());
			}
		for(int i=0;i<aristas;i++){
			input = br.readLine();
			int nod1 = Integer.parseInt(input.split(" ")[0]);
			int nod2 = Integer.parseInt(input.split(" ")[1]);
			mat[nod1].add(nod2);
			mat[nod2].add(nod1);
		}
		boolean puede = true;
		//for(int i=0;i<nodos && (puede = (!visited[i]?dfs(i,mat, nods):puede));i++);
		for(int i=0;i<nodos && (puede = ppp);i++){
			sum = 0;
			if(!visited[i])
				dfs2(i, mat, nods);
				}
		System.out.println((puede?"POSSIBLE":"IMPOSSIBLE"));
		}
	}
}
