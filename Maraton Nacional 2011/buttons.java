import java.io.*;
import java.util.*;
public class buttons{
	public static long total = 0,bott = 0;
	public static Vector<Integer> tipos;
	public static void main(String args[]) throws IOException{
		BufferedReader lector = new BufferedReader(new FileReader("buttons.in"));
		String tmp = "";
		while((tmp = lector.readLine())!=null){
			if(tmp.equals("0 0"))return;
			int bot = Integer.parseInt(tmp.substring(0,tmp.indexOf(" "))),tod = Integer.parseInt(tmp.substring(tmp.indexOf(" ")+1,tmp.length()));
			tipos = new Vector<Integer>();
			total =0;
			bott = bot;
			for(int n = 0;n<tod;n++)
			tipos.add(Integer.parseInt(lector.readLine()));
			particion(bot,new Vector<Integer>(),0,-1);
			System.out.println(total);
			}
		
		}
	public static void particion(int cant,Vector<Integer> sumandos,int t,int z){
		if(t==tipos.size() && cant==0){
			if(z>-1)
			sumandos.add(z);
			long r = 1,tmp = bott;
			for(int n = 0;n<sumandos.size();n++){
			r*=comb((int)tmp,sumandos.get(n));
			tmp-=sumandos.get(n);
		}
			total+=r;
			return;
			}
		if(z>-1)sumandos.add(z);
		for(int n = 0;n<tipos.get(t) && (cant-n)>=0;n++)
			particion(cant-n,sumandos,t++,n);
		}
	public static long comb(int a,int b){
		long res = 1;
		for(int n = Math.max(b,a-b)+1;n<a;n++)
		res*=n;
		long resta= 1;
		for(int t = Math.min(b,a-b);t>0;t--)resta*=t;
		return res/resta;
		}
	
	}