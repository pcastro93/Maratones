import java.io.*;
import java.util.*;

public class party{

	public static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static int comparar(Pair x, Pair y){
		if(x.a<y.a)return -1;
		if(y.a>y.a)return 1;
		if(x.b - x.a< y.b - y.a)
			return -1;
		if(x.b - x.a> y.b - y.a)
			return 1;
		return 0;
	}
	public static void ordenar(ArrayList<Pair> fiestas){
		for(int i=0;i<fiestas.size();i++){
			for(int j=i+1;j<fiestas.size();j++){
				if(comparar(fiestas.get(i), fiestas.get(j))==1){
					Pair tmp = fiestas.get(i);
					fiestas.set(i, fiestas.get(j));
					fiestas.set(j, tmp);
				}
			}
		}
	}
	public static void main(String fjdkf[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("party.in"));
//		PrintWriter pw = new PrintWriter(new FileWriter("party.out"));
		String input = "";
		int cas = 0;
		while ((input = br.readLine()) != null && !input.equals("0")) {
			int n = atoi(input);
			ArrayList<Pair> fiestas = new ArrayList<Pair>();
			for(int i=0;i<n;i++){
				String tmp[] = br.readLine().split(" ");
				fiestas.add(new Pair(atoi(tmp[0]), atoi(tmp[1])));
			}
//			try{
//			Collections.sort(fiestas, new Comparador());
				ordenar(fiestas);
//			}catch(Exception efdfd){
//				System.out.println("fdfdfd");
//				continue;
//			}
			double act = 8;
			int tot = 0;
			boolean bfiestas[] = new boolean[n];
			try{
			while(act<=23.5){
				boolean entro = false;
				for(int i=0;!entro && i<fiestas.size();i++){
					Pair tmp = fiestas.get(i);
					if(tmp.a<=act && tmp.b>=act + 0.5 && !bfiestas[i]){
						entro = true;
						bfiestas[i] = true;
						}
				}
				if(entro){
					tot++;
					}
				act+=0.5;
			}
			System.out.println("On day "+ ++cas + " Emma can attend as many as " + tot + " parties.");
//			pw.println("On day "+ ++cas + " Emma can attend as many as " + tot + " parties.");
			}catch(Exception e){
				System.out.println("hps");
			}
		}
	}

}

class Pair{
	double a,b;
	public Pair(int a, int b){
		this.a = a;
		this.b = b;
	}
	public String toString(){
		return this.a +" "+this.b;
	}
}
class Comparador implements Comparator<Pair>{

	public int compare(Pair x, Pair y) {
		if(x.a<y.a)return -1;
		if(y.a>y.a)return 1;
		if(x.b - x.a< y.b - y.a)
			return -1;
		if(x.b - x.a> y.b - y.a)
			return 1;
		return 0;
	}
	
}