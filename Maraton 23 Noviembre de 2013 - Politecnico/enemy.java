import java.io.*;
import java.util.*;

public class enemy{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null){
			StringTokenizer st = new StringTokenizer(input);
			int n = atoi(st.nextToken()),m = atoi(st.nextToken());
			if(n==0 && m==0)break;
			st = new StringTokenizer(br.readLine());
			short mat[][] = new short[1050][1050];
			ArrayList<int[]> puntas = new ArrayList<int[]>(); 
			for(int i=0;i<n;i++){
				int f = atoi(st.nextToken()),c= atoi(st.nextToken());
				int tmp[] = {f,c};
				puntas.add(tmp);
			}
			for(int i=0;i<n;i++){
				int f1 = puntas.get(i)[0], c1 = puntas.get(i)[1];
				int f2 = puntas.get((i+1)%puntas.size())[0], c2 = puntas.get((i+1)%puntas.size())[1];
				mat[f1][c1] = 2;
				mat[f2][c2] = 2;
				for(int k=(f1==f2?c1:f1);k<=(c1==c2?f2:c2);k++){
					if(f1==f2){
						mat[f1][k]=1;
						if(f1-1>-1)
							mat[f1-1][k]=1;
					}else{
						mat[k][c1]=1;
						if(c1-1>-1)
						mat[k][c1-1]=1;
					}
				}
			}
		}
	}
}