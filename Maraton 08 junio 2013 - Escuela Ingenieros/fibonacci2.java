import java.io.*;
import java.util.*;
public class fibonacci{
	public static long ff[] = new long[101];
	public static void main(String args[]) throws IOException{
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String tmp = "";
		ff[0]=ff[1]=1;
		for(int n = 2;n<ff.length;n++)
			ff[n]=ff[n-1]+ff[n-2];
		while(!(tmp = lector.readLine()).equals("-1")){
			int a = Integer.parseInt(tmp);
			String p  = lector.readLine();
			System.out.println(f(a,p));
		}
	}
	public static long f(int a,String p){
		long b = 0;
		if(a<2 && p.equals(""+a))return 1;
		else if(a<2)return 0;
		if(p.equals("11") && a<4)return 0;
		if(p.indexOf("00")!=-1 || p.indexOf("111")!=-1)return 0;
		if(p.equals("1"))return ff[a-1];
		if(p.equals("0"))return ff[a-2];
		if(p.charAt(0)=='0')p = "1"+p;
		if(p.endsWith("1") && p.length()>1)b = f(a,p+"0");
		//String pp = "",ppp = "";
		Vector<StringBuilder> joder = new Vector<StringBuilder>();
		joder.add(new StringBuilder(""));
		while(p.length()>0){
			Vector<StringBuilder> joder2 = new Vector<StringBuilder>();
			for(int n = 0;n<joder.size();n++){
				StringBuilder pp = joder.get(n);
				if(p.startsWith("10")){
					pp.append("1");
					joder2.add(pp);
					if(n==joder.size()-1)
						p = p.substring(2);
				}else if(p.startsWith("1")){
					pp.append("0");
					joder2.add(pp);
					if(n==joder.size()-1)
						p=p.substring(1);
				}
			}
			joder =  joder2;
			//System.out.println(p);
		}
		//long b = 0;
		//System.out.println((a-1)+" "+joder);
		for(int n = 0;n<joder.size();n++)
			b+=f(a-1,joder.get(n).toString());
		return b;
	}
}
