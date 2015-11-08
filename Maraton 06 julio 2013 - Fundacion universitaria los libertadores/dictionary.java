import java.io.*;
import java.util.*;
public class dictionary {
	
	public static void main(String dsgsg[]){
		Scanner entrada= new Scanner(System.in);
		
		String linea="";
		
		while(!(linea=entrada.nextLine()).equals("END")){
			ArrayList<String> palabras= new ArrayList<String>();
			String out="";
			String l[]= linea.split(" ");
			for(String m: l)
				palabras.add(m);
		
			Collections.sort(palabras, new comparador());
			for(String li: palabras)
				out+=(li+" ");
			
			System.out.println(out.substring(0,out.length()-1));
				
		}
		
		
	}
	
}
class comparador implements Comparator<String>{

	String a="a b k d e g h i l m n ng o p r s t u w y";
	public int compare(String o1, String o2) {
		int j=0;
		int n=0,cont=0,cont2=0;
		while((n=o1.indexOf("ng",n))!=-1){
			cont++;
			n++;
		}
		n=0;
		while((n=o2.indexOf("ng", n))!=-1){
			cont2++;
			n++;
		}
		
		int l1=o1.length();
		int l2= o2.length();
		for(int i=0; i< l1 && j<l2;i++,j++){
			int aa,bb;
			aa=a.indexOf(o1.charAt(i));
			bb=a.indexOf(o2.charAt(j));
			
				if(i+1<o1.length() && o1.substring(i, i+2).equals("ng")){
					i++;
					aa=a.indexOf("ng");
				}
				if(j+1<o2.length() && o2.substring(j, j+2).equals("ng")){
					j++;
					bb=a.indexOf("ng");
				}
			
			
			if(aa>bb)
				return 1;
			if(aa<bb)
				return -1;
			
		}
		
		return (int)Math.signum(l1-cont - l2+cont2);
	}
	
	
}
