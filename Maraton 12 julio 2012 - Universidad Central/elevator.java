import java.io.*;
import java.util.*;
public class elevator {

	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static int solve(int tot,int empieza, int termina, int up, int down){
		ArrayList<Integer> visitar = new ArrayList<Integer>();
		Set<Integer> visitados = new HashSet<Integer>();
		int botones[] = new int[tot+1];
		visitar.add(empieza);
		visitados.add(empieza);
		while(!visitar.isEmpty()){
			int actual = visitar.remove(0);
			visitados.add(actual);
			if(actual==termina)return botones[termina];
			if(actual+up<=tot && !visitados.contains(actual+up) && !visitar.contains(actual+up)){
				botones[actual+up] = botones[actual]+1;
				visitar.add(actual+up);
				}
			if(actual+down>=1 && !visitados.contains(actual+down) && !visitar.contains(actual+down)){
				visitar.add(actual+down);
				botones[actual+down] = botones[actual]+1;
				}
		}
		return -1;
	}
	public static void main(String fdfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0 0 0 0 0")){
			int tot = atoi(input.split(" ")[0]);
			int empieza = atoi(input.split(" ")[1]);
			int termina = atoi(input.split(" ")[2]);
			int up = atoi(input.split(" ")[3]);
			int down= -1*atoi(input.split(" ")[4]);
			int res = -1;
			if((res = solve(tot, empieza, termina, up, down))==-1)
				System.out.println("use the stairs");
			else
				System.out.println(res);
		}
	}
}
