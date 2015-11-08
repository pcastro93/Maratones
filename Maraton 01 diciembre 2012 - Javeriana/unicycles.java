import java.io.*;
import java.util.*;
public class unicycles{
	public static int min,max;
	public static int num[];
	public static Vector<Vector<Integer>> restas;
	public static Set<Set<Integer>> todosSon;
	public static Set<Integer> numeros;
	public static void main(String args[]) throws IOException{
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String tmp = "";
		while((tmp = lector.readLine())!=null){
			String joder[] = tmp.split(" ");
			min = Integer.parseInt(joder[1]);
			max = Integer.parseInt(joder[0]);
			restas = new Vector<Vector<Integer>>();
			numeros = new HashSet<Integer>();
			num = new int[Integer.parseInt(joder[1])];
			//uso = new boolean[Integer.parseInt(joder[1])];
			for(int n = 2;n<joder.length;n++){
				num[n-2] = Integer.parseInt(joder[n]);
				numeros.add(num[n-2]);
			}
			Arrays.sort(num);
			for(int n = 0;n<num.length;n++){
				Vector<Integer> retal = new Vector<Integer>();
				for(int m = n+1;m<num.length;m++)
					if(n!=m)retal.add(num[m]-num[n]);
				restas.add(retal);
			}
			todosSon = new HashSet<Set<Integer>>();
			dale(0,0, new HashSet<Integer>(),new HashSet<Set<Integer>>());
			System.out.println(min);//+"  "+todosSon);
		}
	}
	public static void dale(int voy,int unici,Set<Integer> uso, Set<Set<Integer>> grup){
		if(uso.size()==num.length){
			min = Math.min(min,unici);
			if(min==unici){
				//System.out.println(grup);
				todosSon = new HashSet<Set<Integer>>();
				todosSon.addAll(grup);
				Iterator rt = todosSon.iterator();
				Set<Set<Integer>> yanomas = new HashSet<Set<Integer>>();
				while(rt.hasNext()){
					Iterator rrt = todosSon.iterator();
					Set<Integer> yy = (Set<Integer>)rt.next();
					while(rrt.hasNext()){
						Set<Integer> yyy = (Set<Integer>)rrt.next();
						if(yy.size()>yyy.size()){
							Iterator comp = yyy.iterator();
							boolean u = true;
							while(comp.hasNext() && u)u = yy.contains((Integer)comp.next());
							if(u && !yanomas.contains(yyy)){
								min--;
								yanomas.add(yyy);
							}
						}
					}
				}
			}
			return;
		}
		if(uso.contains(num[voy]))return;
		int vec = 0;
		for(int n = 0;n<restas.get(voy).size();n++){
			Set<Integer> uso2 = new HashSet<Integer>();
			Set<Set<Integer>> grup2 = new HashSet<Set<Integer>>();
			Set<Integer> mientras = new HashSet<Integer>();
			grup2.addAll(grup);
			Iterator t = uso.iterator();
			while(t.hasNext())uso2.add((Integer)t.next());
			Vector<Integer> llevo = new Vector<Integer>();
			boolean si = true;
			for(int m = num[voy];m<max && si;m+=restas.get(voy).get(n)){
				if(!numeros.contains(m))si = false;
				else
					llevo.add(m);
			}
			boolean sisi = true;
			for(int m = num[voy];m>-1 && si;m-=restas.get(voy).get(n)){
				if(!numeros.contains(m))si = false;
				else
					llevo.add(m);
			}
			Set<Integer> ciclo = new HashSet<Integer>();
			ciclo.addAll(llevo);
			if(si && !grup.contains(ciclo)){
				uso2.addAll(llevo);
				mientras.addAll(llevo);
				grup2.add(mientras);
				dale(voy+1,unici+1,uso2,grup2);
			}else if(vec==0){
				uso2.add(num[voy]);
				mientras.add(num[voy]);
				grup2.add(mientras);
				dale(voy+1,unici+1,uso2,grup2);
				vec++;
			}
		}
	}
}
