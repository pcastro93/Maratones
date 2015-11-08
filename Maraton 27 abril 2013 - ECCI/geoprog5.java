import java.io.*;
import java.util.*;
public class geoprog{
	static int p[];
	static HashSet<Integer> ff = new HashSet<Integer>();
	static int atoi(String n){return Integer.parseInt(n);}
	
	static int[] facto(int n){
		//System.out.println("factorizo "+n);
		int[] facs = new int[p.length];
		int fin = (int)Math.sqrt(n);
		int n2 = 0;
		if(n==1 || n==0)return facs;
		while((n&1)==0){
			n2++;
			n>>=1;
		}
		if(n2!=0)
			facs[m(2)] =  n2;
		int div = 3;
		int ccc = 0;
		while(n!=1 && div<=fin){
			if(n%div==0){
				//System.out.println(n+" div "+n);
				ccc++;
				n/=div;
				}
			else{
				//System.out.println(div+" no div "+n);
				if(ccc!=0){
					//System.out.println("pongo "+div+" "+ccc+" veces");
					facs[m(div)]=ccc;
					}
				ccc=0;
				div+=2;
			}
		}
		if(n==1 && ccc!=0)
			facs[m(div)]=ccc;
		if(n!=1)
			facs[m(n)]= 1;
		return facs;
	}
	static boolean raro(int a, int b){
		return (a==0 || b<=1);//genera una constante para ese conjunto.
	}
	static String toString(int arr[]){
		StringBuilder sb =new StringBuilder("");
		for(int i=0;i<arr.length;i++)
			sb.append(p[i]).append(arr[i]);
		return sb.toString();
	}
	static void pos(int n){
		//System.out.println("pos "+n);
		int fin = (int)Math.sqrt(n);
		int n2 = 0;
		if(n==1 || n==0)return;
		while((n&1)==0){
			n2++;
			n>>=1;
		}
		if(n2!=0)
			ff.add(2);
		int div = 3;
		int ccc = 0;
		while(n!=1 && div<=fin){
			if(n%div==0){
				n/=div;
				ccc++;
				}
			else{
				if(ccc!=0)
					ff.add(div);
				ccc=0;
				div+=2;
			}
		}
		if(n==1 && ccc!=0)
			ff.add(div);
		if(n!=1)
			ff.add(n);
	}
	static void agregar(int[] pb1, int[] pq1, HashSet<String> conj, int n1){
		//System.out.println("pb "+pb1);
		//System.out.println("pq "+pq1);
		for(int i=0;i<n1;i++){
					conj.add(toString(pb1));
					//System.out.println("anado" +pb1);
					//exponenciamos
					for(int k=0;k<pb1.length;k++)
						pb1[k]+=pq1[k];
				}
		//System.out.println("conjunto al final \n"+conj);
	}
	static void ini(){
		ArrayList<Integer> pp = new ArrayList<Integer>(ff);
		Collections.sort(pp);
		p = new int[pp.size()];
		for(int i=0;i<pp.size();i++)
			p[i] = pp.get(i);
	}
	static int m(int v){
		return Arrays.binarySearch(p, v);
	}
	public static void main(String jkfdfd[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		//int f[] = (facto(130));
		//for(int c:f)
		//	System.out.println(c);
		int cont = 0;
		StringBuilder sb = new StringBuilder("");
		while((input = br.readLine())!=null && !input.equals("")){
			//System.out.println("Caso "+ ++cont);
			ff = new HashSet<Integer>();
			ArrayList<Integer> pp = new ArrayList<Integer>();
			int b1, q1, n1, b2, q2, n2;
			String div[] = input.split(" ");
			b1 = atoi(div[0]);
			q1 = atoi(div[1]);
			n1 = atoi(div[2]);
			b2 = atoi(div[3]);
			q2 = atoi(div[4]);
			n2 = atoi(div[5]);
			//System.out.println(pp);
			//bonito el if :) me lo quedo
			if(b2==0 || q2<=1){
				//magic swap
				b2^=b1;b1^=b2;b2^=b1;
				q2^=q1;q1^=q2;q2^=q1;
				n2^=n1;n1^=n2;n2^=n1;
			}
			if(raro(b1, q1)){
				boolean ya = false;
				HashSet<Long> conj = new HashSet<Long>();
				conj.add((long)b1);
				if(n1>1)conj.add((long)b1*(long)q1);
				long act = b2;
				for(int i=0;i<n2 && !ya;i++){
					conj.add(act);
					act*=q2;
					if(act>500000000){//la constante generada no es mas grande que 500 000 000
						sb.append(n2-i-1+conj.size()).append("\n");//los que faltan mas lo que llevaba
						ya = true;
						}
				}
				if(!ya){
					ya = true;
					sb.append(conj.size()).append("\n");
				}
			}else{
				//con Strings para que sea mas rapido el hashset con HashMap es muy lento :(
				HashSet<String> conj = new HashSet<String>();
				pos(b1);pos(q1);pos(n1);
				pos(b2);pos(q2);pos(n2);
				ini();
				
				//System.out.println(ff);
				//for(int c:p)
				//	System.out.print(c+" ");
				
				int[] pb1 = facto(b1);
				int[] pq1 = facto(q1);
				int[] pb2 = facto(b2);
				int[] pq2 = facto(q2);
				
				//System.out.println("111");
				agregar(pb1, pq1, conj, n1);
				//System.out.println("222");
				agregar(pb2, pq2, conj, n2);
				//StringBuilder tt = new StringBuilder("");
				//for(String c: conj)
				//	tt.append(c).append("\n");
				//System.out.println(tt);
				sb.append(conj.size()).append("\n");
			}
		}
		System.out.print(sb);
	}
}