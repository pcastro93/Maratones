import java.io.*;
import java.util.*;
public class bender{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jkfdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0")){
			String div[] = br.readLine().split(" ");
			char eje = 'x';
			char signo = '-';
			for(String c: div){
				if(c.equals("No"))continue;
				char arr[] = c.toCharArray();
				char e = arr[1];
				char s = arr[0];
				if(eje =='x' || e==eje){//si no no cambia en nada!
					if(eje =='x'){
						eje = e;
						if(signo =='-'){
							if(s =='-')
								signo = '+';
							else
								signo = '-';
						} else
							signo = s;
					}
					else {
						eje = 'x';
						if(signo =='-')
							signo = s;
						else if(s =='-')
							signo = '+';
						else
							signo = '-';
					}
				}
			}
			//lo invierto porque por alguna *** razon dan invertidos los signos!
			if(signo=='-')
				signo ='+';
			else
				signo ='-';
			System.out.println(signo+""+eje);
		}
	}
}