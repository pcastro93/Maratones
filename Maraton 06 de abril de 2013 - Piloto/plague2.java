import java.io.*;
import java.util.*;

public class plague{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jfkdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0 0 0 0")){
			String imparr[] = input.split(" ");
			int mut = atoi(imparr[0]);
			int ini = atoi(imparr[1]);
			int fin= atoi(imparr[2]);
			int cota = atoi(imparr[3]);
			HashMap<String, String> mutas = new HashMap<String, String>();
			HashMap<String, Integer> inic= new HashMap<String, Integer>();
			HashMap<String, Integer> finic= new HashMap<String, Integer>();
			boolean nodeter =  false;
			for(int k=0;k<mut;k++){
				imparr = br.readLine().split(" ");
				if(mutas.containsKey(imparr[0]) && !mutas.get(imparr[0]).equals(imparr[1]))
					nodeter = true;
				mutas.put(imparr[0], imparr[1]);
				}
			if(nodeter){
				for(int k=0;k<ini+fin;k++)
					br.readLine();
				System.out.println("Protein mutations are not deterministic");
				continue;
			}
			for(int k=0;k<ini;k++){
				imparr = br.readLine().split(" ");
				inic.put(imparr[0], atoi(imparr[1]));
				}
			for(int k=0;k<fin;k++){
				imparr = br.readLine().split(" ");
				finic.put(imparr[0], atoi(imparr[1]));
				if(inic.containsKey(imparr[0])){//resto
					int borr = (int)Math.min(inic.get(imparr[0]), finic.get(imparr[0]));
					inic.put(imparr[0], inic.get(imparr[0])-borr);
					finic.put(imparr[0], finic.get(imparr[0])-borr);
					if(inic.get(imparr[0])==0)
						inic.remove(imparr[0]);
					if(finic.get(imparr[0])==0)
						finic.remove(imparr[0]);
					}
				}
//			System.out.println(inic);
//			System.out.println(finic);
//			System.out.println("------");
			if(inic.isEmpty() && finic.isEmpty())
				System.out.println("Cure found in "+0+" mutations(s)");
			int lleva = 0;
			while(lleva<=cota && !inic.isEmpty()){
				ArrayList<String> quit = new ArrayList<String>();
				ArrayList<String> quit2 = new ArrayList<String>();
				ArrayList<String> add = new ArrayList<String>();
//				lleva++;
				for(String s: inic.keySet()){
//					System.out.println(s);
					if(finic.containsKey(s) && finic.get(s)>0){
						int borr = (int)Math.min(inic.get(s), finic.get(s));
						finic.put(s, finic.get(s)-borr);
						inic.put(s, inic.get(s)-borr);
						if(inic.get(s)<=0)
							quit.add(s);
						else
							add.add(s);
						if(finic.get(s)<=0)
							quit2.add(s);
					}else
						add.add(s);
				}
//				System.out.println(quit);
//				System.out.println(quit2);
//				System.out.println(add);
				for(String s: quit)
					inic.remove(s);
				for(String s: quit2)
					finic.remove(s);
				boolean entra = !add.isEmpty();
				if(entra)
					lleva++;
				for(String s: add){
					int cant = inic.get(s);
					inic.remove(s);
					int mas = (inic.containsKey(mutas.get(s))?inic.get(mutas.get(s)):);//TODO seguir
					inic.put(mutas.get(s), cant);
				}
			}
			if(lleva>cota)
				System.out.println("Nostalgia for Infinity is doomed");
			else if(lleva==-1)
				System.out.println("Cure found in "+0+" mutations(s)");
			else
				System.out.println("Cure found in "+lleva+" mutations(s)");
		}
	}
}

/*
3 2 2 4
a b
c a
b d
a 1
c 1
d 1
a 1

*/