import java.io.*;
import java.util.*;

public class jack{

	public static int atoi(String n) {
		return Integer.parseInt(n);
	}
	public static String trunc(double n){
		String ddd = ""+Math.round(n*Math.pow(10,5))/Math.pow(10,5);
		while(ddd.length()-ddd.indexOf(".")<6)
			ddd+="0";
		if(ddd.charAt(0)!='0')
			ddd = ddd.substring(0, ddd.length()-1);
		return ddd;
	}
	public static String solve(ArrayList<String> nodos , ArrayList<HashMap<Integer, ArrayList<Double>>> adyacencias, String ini, String fin ){
		int formas[][]=new int[nodos.size()][nodos.size()];
		for(int y=0;y<nodos.size();y++){
			for(int x = 0;x<nodos.size();x++){
				if(!adyacencias.get(x).containsKey(y))continue;
				for(int ll = 0;ll<adyacencias.get(x).get(y).size();ll++){
				for(int j = 0;j<nodos.size();j++){
					if(!adyacencias.get(y).containsKey(j))continue;
					for(int kk = 0;kk<adyacencias.get(y).get(j).size();kk++){
						double pes = adyacencias.get(x).get(y).get(ll) * adyacencias.get(y).get(j).get(kk);
//						System.out.println(x+" "+y+" " + j + " "+pes);
//						System.out.println(adyacencias.get(x).get(y).get(ll) + " " +  adyacencias.get(y).get(j).get(kk));
						for(int mm = 0;mm<adyacencias.get(x).get(j).size();mm++)
							if(pes>adyacencias.get(x).get(j).get(mm)){
								adyacencias.get(x).get(j).set(mm, pes);
								formas[x][j]=0;
//								System.out.println("=>"+x+" "+j);
							}
							else if(pes==adyacencias.get(x).get(j).get(mm)){
								formas[x][j]++;
//								System.out.println("========>"+x+" "+j);
							}
							}
					}
			}
			}
		}
		double dist = Double.MAX_VALUE;
		for(int i=0;i<adyacencias.get(nodos.indexOf(ini)).get(nodos.indexOf(fin)).size();i++)
			if(adyacencias.get(nodos.indexOf(ini)).get(nodos.indexOf(fin)).get(i)<dist)
				dist =adyacencias.get(nodos.indexOf(ini)).get(nodos.indexOf(fin)).get(i); 
//		return trunc(1/dist) +" "+ ++formas[nodos.indexOf(ini)][nodos.indexOf(fin)];
		return trunc(1/dist) +" "+ (int)(formas[nodos.indexOf(ini)][nodos.indexOf(fin)]/adyacencias.get(nodos.indexOf(ini)).get(nodos.indexOf(fin)).size() +adyacencias.get(nodos.indexOf(ini)).get(nodos.indexOf(fin)).size());
	}
	public static void main(String fjdkf[]) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("jack.in"));
//		PrintWriter pw = new PrintWriter(new FileWriter("jack.out"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int cas = 0;
		int n = atoi(input = br.readLine());
		for(int i=0;i<n;i++){
			ArrayList<HashMap<Integer, ArrayList<Double>>> adyacencias= new ArrayList<HashMap<Integer, ArrayList<Double>>>();
			ArrayList<String> nodos = new ArrayList<String>();
			String tmp[] = (input = br.readLine()).split(" ");
			String ini = tmp[1];
			String fin = tmp[0];
			try{
			int aristas = atoi(tmp[2]);
			for(int k=0;k<aristas;k++){
				input = br.readLine();
				double pes = atoi(input.split(" ")[0])/(double)atoi(input.split(" ")[2]);
				if(!nodos.contains(input.split(" ")[1])){
					nodos.add(input.split(" ")[1]);
					adyacencias.add(new HashMap<Integer, ArrayList<Double>>());
					}
				if(!nodos.contains(input.split(" ")[3])){
					nodos.add(input.split(" ")[3]);
					adyacencias.add(new HashMap<Integer, ArrayList<Double>>());
					}
//				System.out.println(nodos);
				if(adyacencias.get(nodos.indexOf(input.split(" ")[3])).get(nodos.indexOf(input.split(" ")[1]))==null)
					adyacencias.get(nodos.indexOf(input.split(" ")[3])).put(nodos.indexOf(input.split(" ")[1]),new ArrayList<Double>());
					adyacencias.get(nodos.indexOf(input.split(" ")[3])).get(nodos.indexOf(input.split(" ")[1])).add(pes);
			}
			}catch(Exception efd){
				System.out.println("fdfdfde");
				continue;
			}
			String res = solve(nodos, adyacencias, ini, fin);
			System.out.println("Case "+ ++cas + ": " + res);
//			pw.println("Case "+ ++cas + ": " + res);
		}
	}

}