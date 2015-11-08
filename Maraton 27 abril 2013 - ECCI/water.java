import java.util.*;
import java.io.*;
public class water{
	public static boolean ent[][];
	public static char mat[][];
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[]) throws IOException{
		BufferedReader lector= new BufferedReader(new InputStreamReader(System.in));
		String tmp = "";
		while((tmp = lector.readLine())!=null){
			String div[] = tmp.split(" ");
			int a = atoi(div[0]);
			int b = atoi(div[1]);
			int s = 0;
			int v = 1;
			int c = a*b;
			double p = 1.0;
			ent = new boolean[a][b];
			mat = new char[a][b];
			ent[0][0]=true;
			for(int n = 0;n<a;n++){
				tmp = lector.readLine();
				for(int m = 0;m<tmp.length();m++){
					mat[n][m]=tmp.charAt(m);
					if(mat[n][m]=='X')c--;
				}
			}
			int d[][] = {{0,1,0,-1},{1,0,-1,0}};//movimientos
			while(v<c){
				Set<Integer> x = new HashSet<Integer>();
				boolean entroo = false;
				int min = Integer.MAX_VALUE;
				for(int n = 0;n<a;n++)
					for(int m = 0;m<b;m++){
						if(!ent[n][m])continue;
						if(x.contains(n+51*m))continue;
						for(int k = 0;k<d[0].length;k++){
bbb:
							if(n+d[0][k]<a && n+d[0][k]>-1 && m+d[1][k]>-1 && m+d[1][k]<b && !ent[n+d[0][k]][m+d[1][k]]
								&& mat[n+d[0][k]][m+d[1][k]]!='X'){
								
								if(x.contains(n+d[0][k]+51*(m+d[1][k])))
									break bbb;
								min = Math.min(min,mat[n+d[0][k]][m+d[1][k]]-'0');
								if(p>=mat[n+d[0][k]][m+d[1][k]]-'0'){
									ent[n+d[0][k]][m+d[1][k]]=true;
									x.add(n+d[0][k]+51*(m+d[1][k]));
									v++;
								}
								entroo = true;
							}
						}
					}
				if(x.isEmpty())
					s = v*min-2;
				s++;
				p = (s+1)/(double)v;
				if(!entroo){
					s = 0;
					break;
				}
			}

			System.out.println(s-1);
		}

	}
}
