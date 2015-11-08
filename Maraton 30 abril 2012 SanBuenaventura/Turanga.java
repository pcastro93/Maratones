import java.util.*;
import java.io.*;
import java.math.*;

public class Turanga{

	public static long solve(int ress){
		int fin = (int)(Math.sqrt(ress))+1;
		long res = 1;
		int cont =0;
		while((ress&1)==0){
			ress>>=1;
			cont++;
		}
		res*=(cont+1);
		int div = 3;
		cont =0;
		while(div<=fin){
			if(ress%div==0){
				ress/=div;
				cont++;
			}else{
				res*=(cont+1);
				div+=2;
				cont=0;
			}
		}
		if(ress!=1){res*=2;}
		return res;
	}
	public static void main(String args[]) throws IOException{
		//Se hace la funcion tao de euler del valor absoluto de la diferencia de los numeros

		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String tmp ="";
		while((tmp = lector.readLine())!=null && !tmp.equals("0 0")){
			String tal[] = tmp.split(" ");
			int ress = Math.abs(Integer.parseInt(tal[1])-Integer.parseInt(tal[0]));
			System.out.println(solve(ress));
		}

	}
}
