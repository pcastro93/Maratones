import java.io.*;
import java.util.*;

public class anagram{
	static String sorted(String p){
		char[] c = p.toCharArray();
		Arrays.sort(c);
		String res = "";
		for(char cc:c)
			res+=cc;
		return res;
	}
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		HashMap<String, TreeSet<String>> tods = new HashMap<String, TreeSet<String>>();
		ArrayList<P> cont = new ArrayList<P>();
		HashMap<String, Integer> au = new HashMap<String, Integer>();
		while((input = br.readLine())!=null && !input.equals("")){
			String sor = sorted(input);
			if(!tods.containsKey(sor)){
				tods.put(sor, new TreeSet<String>());
				au.put(sor, 0);
				}
			tods.get(sor).add(input);
			au.put(sor, au.get(sor)+1);
		}
		for(String s: au.keySet())
			cont.add(new P(tods.get(s).first(), au.get(s)));
		Collections.sort(cont);
		for(int i=0;i<5 && i<cont.size();i++){
			TreeSet<String> t = tods.get(sorted(cont.get(i).p));
			System.out.print("Group of size "+cont.get(i).cont+": ");
			while(!t.isEmpty())
				System.out.print(t.pollFirst()+" ");
			System.out.println(".");
		}
	}
}
class P implements Comparable<P>{
	String p;
	int cont;
	public P(String p, int cont){
		this.p = p;
		this.cont = cont;
	}
	public int compareTo(P o){
		if(this.cont==o.cont)
			return this.p.compareTo(o.p);
		return -(this.cont-o.cont);
	}
	public String toString(){
		return this.p+" "+this.cont;
	}
}