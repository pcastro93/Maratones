import java.io.*;
import java.util.*;

public class maps{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdjkfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0 0 0 0 0 0")){
			StringTokenizer st = new StringTokenizer(input);
			double w = atoi(st.nextToken()),h= atoi(st.nextToken()),x= atoi(st.nextToken()),y= atoi(st.nextToken()),s= 100.0/atoi(st.nextToken()),r= atoi(st.nextToken());
			double a = Math.cos(Math.toRadians(r));
			double b = Math.sin(Math.toRadians(r));
			double yy = -1.0*(y-(b*x)/(a-s))/(Math.pow(b, 2.0)/(a*s-Math.pow(s,2.0)) + a/s - 1.0);
			double xx = (b*yy - s*x)/(a-s);
			String res = String.format("%.2f %.2f", xx,yy).replaceAll(",", ".");
			System.out.println(res);
		}
	}
}