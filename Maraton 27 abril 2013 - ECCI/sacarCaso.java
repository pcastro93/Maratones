import java.io.*;
import java.util.*;
public class sacarCaso{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdjkfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nc = atoi(br.readLine());
		for(int k=0;k<nc;k++){
			int n = atoi(br.readLine());
			StringBuilder input = new StringBuilder(n+"\n"+br.readLine()+"\n");//dimensiones
			for(int i=0;i<n;i++){
				input.append(br.readLine()).append("\n");
			}
			if(k==8505)
				System.out.println("Caso :"+(k+1)+"\n"+input);
		}
	}
}
