import java.io.*;
import java.util.*;
public class plague {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static void main(String fdjfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0 0 0 0")){
			String tmp[] = input.split(" ");
			int cambios = atoi(tmp[0]);
			int inicial = atoi(tmp[1]);
			int cfinal = atoi(tmp[2]);
			int k= atoi(tmp[3]);
			HashMap<String, String> maps = new HashMap<String, String>();
			HashMap<String, Integer> ginicial= new HashMap<String, Integer>();
			HashMap<String, Integer> gfinal= new HashMap<String, Integer>();
			int g1tot = 0,g2tot = 0;
			boolean nodet = false;
			for(int i=0;i<cambios;i++){
				input = br.readLine();
				if(nodet)continue;
				String tmpo[] = input.split(" ");
				if(maps.containsKey(tmpo[0]) && !maps.get(tmpo[0]).equals(tmpo[1])){
					nodet = true;
				}else
					maps.put(tmpo[0], tmpo[1]);
			}
			for(int i=0;i<inicial;i++){
				input = br.readLine();
				if(nodet)continue;
				String tmpo[] = input.split(" ");
				ginicial.put(tmpo[0], atoi(tmpo[1]));
				g1tot+=atoi(tmpo[1]);
			}
			for(int i=0;i<cfinal;i++){
				input = br.readLine();
				if(nodet)continue;
				String tmpo[] = input.split(" ");
				gfinal.put(tmpo[0], atoi(tmpo[1]));
				g2tot+=atoi(tmpo[1]);
			}
			if(nodet){
				System.out.println("Protein mutations are not deterministic");
				continue;
			}
			if(g1tot !=g2tot){
//				System.out.println(g1tot);
//				System.out.println(g2tot);
				System.out.println("Nostalgia for Infinity is doomed");
				continue;
			}
//			System.out.println(ginicial);
//			System.out.println(gfinal);
			int copiak = k,d=0;
			do{
boolean entro = false;
				ArrayList<String> cads = new ArrayList<String>(ginicial.keySet());
			for(String sinicial: cads){
				if(ginicial.containsKey(sinicial)&& gfinal.containsKey(sinicial)){
					if(ginicial.get(sinicial)>gfinal.get(sinicial)){
						int val = gfinal.get(sinicial);
						ginicial.put(sinicial, ginicial.get(sinicial)-val);
						gfinal.remove(sinicial);
					}
					else if(ginicial.get(sinicial)==gfinal.get(sinicial)){
					ginicial.remove(sinicial);
					gfinal.remove(sinicial);
					}else if(ginicial.get(sinicial)<gfinal.get(sinicial)){
						int val = ginicial.get(sinicial);
						gfinal.put(sinicial, gfinal.get(sinicial)-val);
						ginicial.remove(sinicial);
					}
				}
				if(maps.containsKey(sinicial) && ginicial.containsKey(sinicial)){
					int valor = ginicial.remove(sinicial);
					ginicial.put(maps.get(sinicial), valor);entro=true;
				}
			}
if(gfinal.isEmpty() || ginicial.isEmpty()){if(d==0 && entro)d=1;break;}
//			System.out.println(ginicial);
//			System.out.println(gfinal);
d++;

			}while(copiak-->0);
			if(copiak<0){
				System.out.println("Nostalgia for Infinity is doomed");
				continue;
			}
			System.out.println("Cure found in "+d +" mutation(s)");
		}
	}
}
