import java.io.*;
import java.util.*;
public class handgun{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null){
			int lines = atoi(input.trim());
			if(lines==0)return;
			Lin l[] = new Lin[lines];
			for(int i=0;i<lines;i++){
				String div[] = br.readLine().trim().split(" ");
				double x1 = atoi(div[0]);
				double y1 = atoi(div[1]);
				double x2 = atoi(div[2]);
				double y2 = atoi(div[3]);
				l[i] = new Lin(x1, y1, x2, y2);
			}
			Arrays.sort(l);
			int i=0, nec = 0;//nec es la cantidad de disparos necesarios
			while(i<l.length){
				double f = l[i++].a2;
				while(i<l.length && l[i].a1>=f){
					if(l[i].a2>f)
						f = l[i].a2;
					i++;
					}
				nec++;
			}
			System.out.println(nec);
		}
	}
}

class Lin implements Comparable<Lin>{
	double x1,y1,x2,y2, a1, a2;//coordenadas linea y angulo de inicio y fin respectivamente
	
	public Lin(double x1, double y1, double x2, double y2){
		double aa[] = new double[2];
		aa[0] = Math.atan2(y1, x1);
		aa[1] = Math.atan2(y2, x2);
		Arrays.sort(aa);
		a1 = aa[1];
		a2 = aa[0];
	}
	public int compareTo(Lin o){
		return (int)-Math.signum(this.a1 - o.a1);
	}
}