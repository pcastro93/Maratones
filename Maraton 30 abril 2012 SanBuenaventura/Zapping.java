import java.io.*;
public class Zapping{
	public static void main(String args[]) throws IOException{
		//Sabra Diego que hizo aca
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String tmp ="";
		while((tmp = lector.readLine())!=null && !tmp.equals("-1 -1"))
			System.out.println(tal(Integer.parseInt(tmp.substring(0,tmp.indexOf(" "))),Integer.parseInt(tmp.substring(tmp.indexOf(" ")+1))));
	}
	public static int tal(int a,int b){
		return Math.min(Math.abs(b-a)%100,100-Math.abs(b-a)%100);
	}
}
