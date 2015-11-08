import java.io.*;
import java.util.*;
public class trains {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static int inf = Integer.MAX_VALUE;
	
	public static double costo(double a, int mata[][], int matb[][], int nodo1, int nodo2){
		double costo = a*mata[nodo1][nodo2]+(1-a)*matb[nodo1][nodo2];
		if(costo>=inf)
			return inf;
		return costo;
	}
	public static double solve(int mata[][], int matb[][], double a){
		Queue<Integer> visitar = new LinkedList<Integer>();
		visitar.offer(0);
		Set<Integer> visitados = new HashSet<Integer>();
		double minimos[] = new double[mata.length];
		for(int i=0;i<minimos.length;i++){
			minimos[i] = costo(a,mata,matb,0,i);
		}
		while(!visitar.isEmpty()){
			int act = visitar.poll();
			visitados.add(act);
			for(int i=0;i<mata.length;i++){
				try{
				double costotmp = minimos[act]+costo(a,mata,matb,act,i);
				if(minimos[i]>costotmp){
					minimos[i] = costotmp;
					}
				}catch(IndexOutOfBoundsException e){
					return -50;
				}
				if((mata[act][i]!=inf || matb[act][i]!=inf) && !visitados.contains(i) && !visitar.contains(i))
					visitar.offer(i);
			}
		}
		return minimos[mata.length-1];
	}
	
	public static void main(String fdjfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("-1 -1 -1 -1")){
			int nodos = atoi(input.split(" ")[0]);
			int aristasA= atoi(input.split(" ")[1]);
			int aristasB= atoi(input.split(" ")[2]);
			int k= atoi(input.split(" ")[3]);
			int mata[][] = new int[nodos][nodos];
			int matb[][] = new int[nodos][nodos];
			for(int i=0;i<mata.length;i++)
				Arrays.fill(mata[i],inf);
			for(int i=0;i<matb.length;i++)
				Arrays.fill(matb[i],inf);
			for(int i=0;i<aristasA;i++){
				int nodoi = atoi((input = br.readLine()).split(" ")[0]);
				int nodof = atoi(input.split(" ")[1]);
				mata[nodoi][nodof] = atoi(input.split(" ")[2]);
				mata[nodof][nodoi] = atoi(input.split(" ")[2]);
			}
			for(int i=0;i<aristasB;i++){
				int nodoi = atoi((input = br.readLine()).split(" ")[0]);
				int nodof = atoi(input.split(" ")[1]);
				matb[nodoi][nodof] = atoi(input.split(" ")[2]);
				matb[nodof][nodoi] = atoi(input.split(" ")[2]);
			}
			for(int i=0;i<k;i++){
				double a = Double.parseDouble(input = br.readLine());
				System.out.println((int)solve(mata, matb,a));
			}
		}
	}
}
