import java.io.*;
import java.util.*;
public class plague{
	static int atoi(String n){return Integer.parseInt(n);}
	static boolean acabo(HashMap<String, Integer> gi, HashMap<String, Integer> gf){
		for(String s:gf.keySet())
			if(!gi.containsKey(s) || gi.get(s)<gf.get(s))
				return false;
		return true;
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !(input.trim()).equals("0 0 0 0")){
		HashMap<String, String> mutas = new HashMap<String, String>();
		HashMap<String, Integer> gi = new HashMap<String, Integer>();
		HashMap<String, Integer> gf = new HashMap<String, Integer>();
		int m, tgi, tgf,n,totgi=0, totgf=0;
		String imparr[] = input.split(" ");
		m = atoi(imparr[0]);
		tgi = atoi(imparr[1]);
		tgf = atoi(imparr[2]);
		n = atoi(imparr[3]);
		boolean determ= true;
		for(int i=0;i<m;i++){
			imparr = br.readLine().split(" ");
			if(mutas.containsKey(imparr[0]) && !mutas.get(imparr[0]).equals(imparr[1]))
				determ = false;
			mutas.put(imparr[0], imparr[1]);
		}
		for(int i=0;i<tgi;i++){
			imparr = br.readLine().split(" ");
			int cant = atoi(imparr[1]);
			gi.put(imparr[0], gi.containsKey(imparr[0])?gi.get(imparr[0])+cant:cant);
			totgi+=cant;
		}
		for(int i=0;i<tgf;i++){
			imparr = br.readLine().split(" ");
			int cant = atoi(imparr[1]);
			gf.put(imparr[0], gf.containsKey(imparr[0])?gf.get(imparr[0])+cant:cant);
			totgf+=cant;
		}
		if(!determ){
			System.out.println("Protein mutations are not deterministic");
			continue;
		}
		int lleva = 0;
		boolean pudo = false;
		
		int i = 0;
                                for (i = 0; i < n; i++) {
                                        if(acabo(gi, gf)){
                                                System.out.println("Cure found in " + i + " mutation(s)");
                                                break;
                                        }
                                        HashMap<String, Integer> cambios = new HashMap<String, Integer>();
                                        for(String cambio: mutas.keySet())
                                                if(gi.containsKey(cambio)){
                                                        cambios.put(mutas.get(cambio), gi.get(cambio));
                                                        gi.remove(cambio);
                                                }
                                        for(String cambio: cambios.keySet())
                                                gi.put(cambio, gi.containsKey(cambio)?gi.get(cambio)+cambios.get(cambio):cambios.get(cambio));
                                }
                                if(i == n)
                                        if(acabo(gi, gf)){
                                                System.out.println("Cure found in " + n + " mutation(s)");
                                                pudo = true;
                                                }
                                        else System.out.println("Nostalgia for Infinity is doomed");
		
		
		//if(!pudo)
		//	System.out.println("Nostalgia for Infinity is doomed");
		//else
		//	System.out.println("Cure found in "+lleva+" mutation(s)");
		}
	}
}
