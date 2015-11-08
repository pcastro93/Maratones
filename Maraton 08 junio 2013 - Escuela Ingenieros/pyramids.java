import java.io.*;
import java.util.*;
public class pyramids{
	static int atoi(String n){return Integer.parseInt(n);}
	
	static int h[];
	static int l[];
	static HashSet<String> r = new HashSet<String>();
	//alturas: si es baja es ceil(n/2) n es la posicion en el arreglo
	//si es alta es n
	static void altas(){
		//blah blah n*.../6...blah blah...
		h = new int[144];
		int sum =0;
		for(int i=0;i<h.length;i++){
			sum+=Math.pow(i,2);
			h[i] = sum;
		}
	}
	static void bajas(){
		l = new int[181];
		int sum =0;
		for(int i=1;i<l.length;i+=2){
			sum+=(int)Math.pow(i,2);
			l[i] = sum;
		}
		sum =0;
		for(int i=0;i<l.length;i+=2){
			sum+=(int)Math.pow(i,2);
			l[i] = sum;
		}
	}
	static void ini(){
		altas();
		bajas();
	}
	static void imp(int arr[]){
		for(int c:arr)
			System.out.print(c+" ");
		System.out.println();
	}
	static void solve(int n){
		if(n==0)
			return;
		int maxh = -1;//cantidad de cubos
		int indice = -1;
		boolean alta = false;
		boolean entro = false;
		for(int i=h.length-1;i>0;i--){//no me interesa el 0
			if(h[i]<=n && h[i]>maxh && !r.contains(i+"H")){
				alta = true;
				maxh = h[i];
				indice = i;
				entro =true;
				}
		}
		for(int i=l.length-1;i>-1;i--){
			int alt = (int)Math.ceil(i/2.0);
			if(l[i]<=n && l[i]>maxh && !r.contains(i+"L")){
				alta = false;
				entro = true;
				indice = i;
				maxh = l[i];
			}
		}
		if(!entro){
			r = null;
			return;
		}
		int hi = indice;
		r.add(hi+(alta?"H":"L"));
		System.out.println("Agrego "+hi+(alta?"H":"L"));
		solve(n-(alta?h[indice]:l[indice]));
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ini();
		imp(h);
		imp(l);
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0")){
			int n = atoi(input);
			solve(n);
			if(r==null)
				System.out.println("impossible");//is nothing
			else
				System.out.println(r);
		}
	}
}
