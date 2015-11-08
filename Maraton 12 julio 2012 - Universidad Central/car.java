import java.io.*;
import java.util.*;
public class car {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static ArrayList<ArrayList<Integer>> adyacencias;
	
	public static boolean bfs(int nodo1, int nodo2, HashMap<Integer,Integer> hacer){
		Set<Integer> visitados = new HashSet<Integer>();
		ArrayList<Integer> visitar= new ArrayList<Integer>();
		visitar.add(nodo1);
		visitados.add(nodo1);
		while(!visitar.isEmpty()){
			int actual = visitar.get(0);
			visitar.remove(0);
			visitados.add(actual);
			for(int adyacente:adyacencias.get(actual)){
				if(!visitar.contains(hacer.get(adyacente)) && !visitados.contains(hacer.get(adyacente))){
					visitar.add(hacer.get(adyacente));
					}
				if(hacer.get(adyacente)==nodo2)
					return true;
				}
		}
		return false;
	}
	public static void main(String fdjkfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0")){
			HashMap<Integer,Integer> hacer = new HashMap<Integer,Integer>();
			ArrayList<Integer> hacerLista = new ArrayList<Integer>();
				int f = atoi(input);
			adyacencias = new ArrayList<ArrayList<Integer>>(f);
			for(int i=0;i<f;i++)
				adyacencias.add(new ArrayList<Integer>());
			for(int i=0;i<f;i++){
				input = br.readLine();
				int nodo = atoi(input.split(" ")[0]);
				hacer.put(nodo,i);
				hacerLista.add(nodo);
				int enlaces = atoi(input.split(" ")[1]);
				for(int j=0;j<enlaces;j++)
					adyacencias.get(hacer.get(nodo)).add(atoi(input.split(" ")[j+2]));
			}
			if(hacerLista.contains(0))
				hacerLista.remove(hacerLista.indexOf(0));
			boolean entro = false;
			for(int nodo:hacerLista)
				if(!bfs(hacer.get(nodo), hacer.get(0), hacer)){
					entro = true;
					System.out.println("TRAPPED " + nodo);
					}
			for(int nodo:hacerLista)
				if(!bfs(hacer.get(0), hacer.get(nodo),hacer)){
					entro = true;
					System.out.println("UNREACHABLE " + nodo);
					}
			if(!entro)
				System.out.println("NO PROBLEMS");
			
		}
	}
}
