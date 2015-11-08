import java.io.*;
import java.util.*;
public class AspenAvenue{
	public static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tot = atoi(br.readLine());
		String input = "";
		int l = atoi((input = br.readLine()).split(" ")[0]);
		int d = atoi(input.split(" ")[1]);
		int pars[] = new int[tot];
		for(int i=0;i<tot;i++)
			pars[i] = atoi(br.readLine());
		Arrays.sort(pars);
		double min = 0;
		double t = l/(tot/2.0-1);
		for(int i=0;i<pars.length;i+=2){
			double p = pars[i];
			double q = pars[(i+1)];
			//System.out.println(p+" "+q);
			double dist1 = Math.abs(p-t*i/2.0)+Math.sqrt(Math.pow(d,2)+Math.pow(q-t*i/2.0,2));
			double dist2 = Math.abs(q-t*i/2.0)+Math.sqrt(Math.pow(d,2)+Math.pow(p-t*i/2.0,2));
			min+=(Math.min(dist1, dist2));
			//System.out.println(dist1+" "+dist2);
		}
		System.out.println(min);
	}
}
