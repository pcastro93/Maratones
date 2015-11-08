import java.io.*;
import java.util.*;
public class code{
	int matches(String a, String b){
		//int fin = (int)Math.min(a.length(), b.length());
		//for(int i=0;i<;i++)
		return 0;
	}
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tot = atoi(br.readLine());
		String code[][] = new String[tot][10000];
		int cont = 0;
		String input = "";
		while(tot-->0){
			String cod = "";
			while(!(input = br.readLine()).equals("***END***")){
				input = input.trim();
				input = input.replaceAll("( )+", " ");
				cod+=input;
				}
			code[cont++]= cod;
		}
		String frag = "";
		while(!(input = br.readLine()).equals("***END***")){
			
		}
	}
}
