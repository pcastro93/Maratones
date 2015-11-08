import java.io.*;
import java.util.*;

public class tennis{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jfkdfd[]){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null){
			StringTokenizer st = new StringTokenizer(input);
			int a = atoi(st.nextToken()), b = atoi(st.nextToken()), c = atoi(st.nextToken());
			int cont =0;
			do{
				if((b&1)==1){b>>=1;b++;}
				else b>>=1;
				if((c&1)==1){c>>=1;c++;}
				else c>>=1;
				cont++;
			}while(c!=b);
			System.out.println(cont);
		}
	}
}

