import java.io.*;
import java.util.*;
public class geoprog{
	static int p[];
	static HashSet<B> c2 = new HashSet<B>();
	static HashSet<Integer> ff = new HashSet<Integer>();
	static int atoi(String n){return Integer.parseInt(n);}
	
	static int[] facto(int n){
		int[] facs = new int[p.length];
		for(int i=0;i<p.length;i++){
			int cont = 0;
			while(n%p[i]==0){
				n/=p[i];
				cont++;
				}
			facs[i]=cont;
			}
		return facs;
	}
	static boolean raro(int a, int b){
		return (a==0 || b<=1);//genera una constante para ese conjunto.
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
	
	static void agregar(int[] pb1, int[] pq1,int n1){
		int apb1[] = Arrays.copyOf(pb1, pb1.length);
		int apq1[] = Arrays.copyOf(pq1, pq1.length);
		B b1 = new B(apb1);
		B b2 = new B(apq1);
		for(int i=0;i<n1;i++){
					B b3 = new B(Arrays.copyOf(b1.a, b1.a.length));
					c2.add(b3);
					//System.out.println("anado" +pb1);
					//exponenciamos
					for(int k=0;k<pb1.length;k++)
						b1.a[k]+=b2.a[k];
				}
	}
	
	static void ini(){
		ArrayList<Integer> pp = new ArrayList<Integer>(ff);
		Collections.sort(pp);
		p = new int[pp.size()];
		for(int i=0;i<pp.size();i++)
			p[i] = pp.get(i);
	}
	
	public static void main(String jkfdfd[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while((input = br.readLine())!=null && !input.equals("")){
			ff = new HashSet<Integer>();
			ArrayList<Integer> pp = new ArrayList<Integer>();
			int b1, q1, n1, b2, q2, n2;
			StringTokenizer st = new StringTokenizer(input);
			b1 = atoi(st.nextToken());
			q1 = atoi(st.nextToken());
			n1 = atoi(st.nextToken());
			b2 = atoi(st.nextToken());
			q2 = atoi(st.nextToken());
			n2 = atoi(st.nextToken());
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
				c2 = new HashSet<B>();
				pos(b1);pos(q1);pos(n1);
				pos(b2);pos(q2);pos(n2);
				ini();
				
				int[] pb1 = facto(b1);
				int[] pq1 = facto(q1);
				int[] pb2 = facto(b2);
				int[] pq2 = facto(q2);
				
				agregar(pb1, pq1, n1);
				agregar(pb2, pq2, n2);
				sb.append(c2.size()).append("\n");
			}
		}
		System.out.print(sb);
	}
}

class B{
	int a[];
	public B(int arr[]){
		this.a = arr;
	}
	public boolean equals(Object oo){
		B o = (B)oo;
		return Arrays.equals(this.a, o.a);
	}
	public int hashCode(){
		return Arrays.hashCode(a);
	}
}
