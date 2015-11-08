import java.io.*;
import java.util.*;

public class pythagoras{
	static HashSet<Long> getDivs(long n){
		//factorization
		int end = (int)Math.sqrt(n)+1, cont=0, div =3;
		TreeMap<Long, Integer> facts = new TreeMap<Long, Integer>();
		while((n&1)==0){
			n>>=1;
			cont++;
		}
		facts.put(2L, cont);
		cont=0;
		while(div<end){
			while(n%div==0){
				n/=div;
				cont++;
			}
			if(cont!=0){
				facts.put((long)div, cont);
				cont=0;
			}
			div+=2;
		}
		if(n!=1)
			facts.put(n, 1);
		HashSet<Long> divs = new HashSet<Long>();
		divs.add(1L);
		for(long i: facts.keySet()){
			HashSet<Long> tmp = new HashSet<Long>();
			//exponent
			for(int k=0;k<=facts.get(i);k++)
				for(long m: divs)
					tmp.add((long)(Math.pow(i,k))*m);
			divs.addAll(tmp);
		}
		return divs;
	}
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jkfdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0")){
			long a = (long)atoi(input);
			long z2 = a*a;
			HashSet<Long> divs = getDivs(z2);
			int res =0;
			for(long d:divs)
				if(((d-z2/d)&1)==0 && ((d-z2/d)>>1)>a)
					res++;
			System.out.println(res);
		}
	}
}

			/*
			como d debe dividir a z2 entonces d es un divisor de z2...
			encuentro los divisores de z2...
			veo cuantos (d-c)/2 son mayores que z y cuento...
			z2 = x2 - y2
			z2 = (x - y)(x+y)
			c = x - y
			d = x + y
			
			x - y = c
			x + y = d
			------------
			2x = c + d
			
			x = (c+d)/2
			y = d - x
			y = d - (c+d)/2
			y = d - c/2 -  d/2
			y = d/2 - c/2
			y = (d-c)/2
			
			z2 = dc
			c = z2/d
			
			y>z es lo mismo que (d-c)/2>z
			*/
