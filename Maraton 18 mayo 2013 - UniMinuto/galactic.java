import java.io.*;
import java.util.*;
public class galactic{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int w, n;
		StringBuilder sb = new StringBuilder("");
		while((input = br.readLine())!=null && !input.equals("0 0")){
			String div[] = input.split(" ");
			w = atoi(div[0]);
			n = atoi(div[1]);
			ArrayList<L> lines = new ArrayList<L>();
			for(int i=0;i<n;i++){
				div = br.readLine().split(" ");
				int ar[] = new int[div.length];
				for(int j=0;j<ar.length;j++)
					ar[j] = atoi(div[j]);
				L tmp = new L(ar[0],ar[1],ar[2],ar[3]);
				boolean contains = false;
				for(int j=0;j<lines.size() && !contains;j++)
					contains = lines.get(j).equals(tmp);
				if(!contains)
					lines.add(tmp);
			}
			boolean todas = true;
			ciclo:for(int i=0;i<lines.size();i++){
				for(int j=i+1;j<lines.size();j++)
					if(!lines.get(i).paralelas(lines.get(j))){
						todas = false;
						break ciclo;
						}
			}
			int res = 0;
			int cant = (todas?lines.size()+1:2*lines.size());
			if(cant>=w)
				sb.append(res).append("\n");
			else{
				cant = 2*(lines.size()+1);
				res++;
				while(cant<w){
					res++;
					cant+=2;
				}
				sb.append(res).append("\n");
			}
		}
		System.out.print(sb);
	}
}

class L{
	int x1, x2, y1, y2;
	F m;//pendiente
	F b;//corte con eje y
	F c;//corte con eje x
	public L(int x1, int y1, int x2, int y2){
		this.m = new F(y1 -y2, x1 - x2);
		this.b = m.multiplicar(new F(1,-1)).multiplicar(new F(x1,1)).sumar(new F(y1, 1));
		this.c = (m.den==0)?new F(x1,1):b.multiplicar(new F(1,-1)).dividir(m);
	}
	public boolean equals(Object o){
		L l = (L)o;
		return this.m.equals(l.m) && this.b.equals(l.b) && this.c.equals(l.c);
	}
	public boolean paralelas(L o){
		return this.m.equals(o.m);
	}
	public String toString(){
		return "m = "+m + " b = "+b;
	}
}

class F{
	int num, den;
	public static final F ZERO = new F(0,1);
	public static final F ONE = new F(1,1);
	F(){
		this.num = 0;
		this.den = 1;
	}
	F(int num, int den){
		int gcd = gcd(num ,den);
		this.num = num/gcd;
		this.den = den/gcd;
	}
	F(F f){
		int gcd = gcd(f.num ,f.den);
		this.num = f.num/gcd;
		this.den = f.den/gcd;
	}
	public F simplificar(){
		int gcd = gcd(this.num, this.den);
		return new F(this.num/gcd, this.den/gcd);
	}
	public int gcd(int a, int b){
		if(a==b && b==0)return 1;
		return (b==0)?a:gcd(b, a%b);
	}
	public F sumar(F b){
		return new F(this.num * b.den + this.den * b.num, this.den * b.den).simplificar();
	}
	public F restar(F b){
		return new F(this.num * b.den - this.den * b.num, this.den * b.den).simplificar();
	}
	public F multiplicar(F b){
		F t = new F(this.num * b.num, this.den * b.den).simplificar();
		return new F(this.num * b.num, this.den * b.den).simplificar();
	}
	public F dividir(F b){
		return new F(this.num * b.den, this.den * b.num).simplificar();
	}
	public String toString(){
		return (this.den!=1)?this.num+"/"+this.den:String.valueOf(this.num);
	}
	public boolean equals(Object o){
		F t = (F)o;
		return (this.num==t.num && this.den==t.den);
	}
	public int signo(){
		return (int)(Math.signum(this.num*this.den));
	}
}