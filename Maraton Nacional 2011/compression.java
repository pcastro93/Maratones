import java.io.*;
import java.util.*;
public class compression{
	static int atoi(String n){return Integer.parseInt(n);}
	static int arr[];//documentos
	static int d[];//distancias
	static void bfs(){
		Queue<Integer> cola = new LinkedList<Integer>();
		for(int i=0;i<arr.length;i++){
			d[arr[i]] = 1;//solo necesita un documento base
			cola.offer(arr[i]);//iniciamos el bfs desde cada documento base
			}
		while(!cola.isEmpty()){
			int act = cola.poll();
			for(int i=0;i<arr.length;i++){
				int sig = act|arr[i];//una combinacion posible
				if(d[sig]==0){
					d[sig]= d[act]+1;
					cola.offer(sig);
					}
			}
		}
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0 0")){
			String div[] = input.split(" ");
			int docs = atoi(div[0]);
			int buscar = atoi(div[1]);
			arr = new int[docs];
			d = new int[1<<16];
			int c=0;
			while(docs-->0){
				div = br.readLine().split(" ");
				for(int i=1;i<div.length;i++)
					arr[c]|=(1<<(atoi(div[i])-1));
				c++;
			}
			//de todos a todos
			bfs();
			while(buscar-->0){
				div = br.readLine().split(" ");
				int doc = 0;
				for(int i=1;i<div.length;i++)
					doc|=(1<<(atoi(div[i])-1));
				System.out.print(d[doc]);
				if(buscar>0)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}