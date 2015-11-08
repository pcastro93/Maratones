import java.io.*;
import java.util.*;

public class paintme{
	public static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdjfkd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!= null && !input.equals("0 0 0 0 0 0")){
			double n, w,l,h,a,m;
			String ent[] = input.split(" ");
			n = atoi(ent[0]);
			w = atoi(ent[1]);
			l = atoi(ent[2]);
			h = atoi(ent[3]);
			a = atoi(ent[4]);
			m = atoi(ent[5]);
			double sum = 0;
			for(int i=0;i<m;i++){
				input = br.readLine();
				ent = input.split(" ");
				double aa = atoi(ent[0]);
				double bb = atoi(ent[1]);
				sum+=aa*bb;
			}
			double res = n*(2*l*h + 2*h*w + l*w - sum)/a;
			System.out.println((int)Math.ceil(res));
		}
		
	}
}