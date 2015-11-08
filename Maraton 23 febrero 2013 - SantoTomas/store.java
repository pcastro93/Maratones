import java.io.*;
import java.util.*;
public class store{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdjkfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int n, m, w;
		while((input =br.readLine())!=null && !input.equals("0 0.00")){
			String ent[]= input.split(" ");
			n = atoi(ent[0]);
			//tamano de la bolsa
			w = m = (int)(Double.parseDouble(ent[1])*100);
			int items[] = new int[n];
			int pesos[] = new int[n];

			for(int i=0;i<n;i++){
				ent = br.readLine().split(" ");
				items[i] = atoi(ent[0]);//valor //cals
				pesos[i] = (int)(Double.parseDouble(ent[1])*100);//peso //cost
			}
			
			int maxcal = 0;
			int best[] = new int[w+1];
			for(int i=0;i<=w;i++)
				for(int j=0;j<n;j++){
					int totalcost = i+pesos[j];
					int totalcal = best[i]+ items[j];
					if(totalcost<=w && totalcal >best[totalcost]){
						best[totalcost] = totalcal;
						if(totalcal > maxcal)
							maxcal = totalcal;
					}
				}
			System.out.println(maxcal);
		}
	}
}
