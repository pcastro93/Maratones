import java.io.*;
import java.util.*;

public class mult{

	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jkdfdp[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int cont =0;
		while((input = br.readLine())!=null && !input.equals("0 0")){
			StringTokenizer st = new StringTokenizer(input);
			System.out.println("Problem "+ ++cont);
			long a = atoi(st.nextToken()), b = atoi(st.nextToken());
			String aa = a+"";
			String bb = b+"";
			ArrayList<Pair> res = new ArrayList<Pair>();
			int esp = (int)Math.log10(a*b)+1;
			res.add(new Pair(esp-aa.length(), a, aa.length(),0));
			res.add(new Pair(esp-bb.length(), b, bb.length(),0));
			int c=0;
			for(int j=bb.length()-1,d=0;j>-1;j--,d++){
				if(bb.charAt(j)=='0'){
					c++;
					continue;
					}
				long val = (long)(bb.charAt(j)-'0')*a;
				int lval = (int)(Math.log10(val))+1;
				val*=Math.pow(10, c);
				res.add(new Pair(esp - lval - d, val, lval,c));
				c=0;
			}
			StringBuilder sb = new StringBuilder("");
			for(int i=0;i<res.size();i++){
				Pair pact = res.get(i);
				for(int j=0;j<pact.esp;j++)
					sb.append(" ");
				sb.append(pact.num);
//				for(int j=0;j<esp-pact.lval-pact.esp-pact.c;j++)
//					sb.append(" ");
				if(i==1){
					sb.append("\n");
					for(int j=0;j<esp;j++)
						sb.append("-");
					}
				sb.append("\n");
			}
		if(res.size()>3){
			for(int j=0;j<esp;j++)
				sb.append("-");
		System.out.println(sb);
		System.out.println(a*b);
		}
		else
			System.out.println(sb.deleteCharAt(sb.length()-1));
		}
	}
}

class Pair{
	int esp, c;
	long num, lval;
	public Pair(int esp, long num, long lval, int c){
		this.esp = esp;
		this.num = num;
		this.lval = lval;
		this.c = c;
	}
}