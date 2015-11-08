import java.io.*;
public class Nephew{
	public static void main(String args[]) throws IOException{
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String tmp ="";
		while((tmp = lector.readLine())!=null && !tmp.equals("0 0 0 0 0")){
			String tal[] = tmp.split(" ");
			long res = 1;
			for(int n=0;n<tal.length;n++)
				res*=(Integer.parseInt(tal[n])*((n>=tal.length-2)?Integer.parseInt(tal[n]):1));
			System.out.println(res);
		}
	}
}
