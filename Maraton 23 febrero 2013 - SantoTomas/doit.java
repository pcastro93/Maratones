import java.io.*;
import java.util.*;

public class doit{
	public static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdjfkd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!= null && !input.equals("0 0")){
			String ent[] = input.split(" ");
			double b = atoi(ent[0]);
			double m = atoi(ent[1]);
			String res = "",resa2="";
			ArrayList<F> efes = new ArrayList<F>();//fraccionarios
			for(int i=0;i<b;i++){
				double d = (Math.sqrt(b*(b-i))*m)/b;
				if(Math.floor(d)==d){
					efes.add(new F(i, (int)(m+d)));
					if(m-d >0)
						efes.add(new F(i, (int)(m-d)));
					resa2+=(i+"/"+(m+d)+" "+i+"/"+(int)(m-d))+" ";
					}
			}
			Collections.sort(efes);
//			System.out.println(efes);
			for(int i=0;i<efes.size();i++){
				res+=efes.get(i).n+"/"+efes.get(i).d+" ";
				int ii = i+1;
				while(ii<efes.size() && efes.get(ii).equals(efes.get(i)))ii++;
				i=ii-1;
				}
//			System.out.println(resa2);
			System.out.println(res.substring(0, res.length()-1));
		}
		
	}
}

class F implements Comparable<F>{
	int n, d;
	double p;
	public F(int n, int d){
		int dcg = 1;//gcd(n,d);
		this.n = n/dcg;
		this.d = d/dcg;
		p = n/(double)d;
	}
	
	int gcd(int a, int b){
		return(b==0)?a:gcd(b, a%b);
	}
	public int compareTo(F f2){
		return (int)Math.signum(this.p-f2.p);
	}
	public boolean equals(F f2){
		return this.p==f2.p;
	}
	public String toString(){
		return this.n+"/" + this.d;
	}
}