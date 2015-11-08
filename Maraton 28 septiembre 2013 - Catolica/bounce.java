import java.io.*;
import java.util.*;

public class bounce{

	static char mat[][];
	static int lon;
	static String camino;
	static int atoi(String n){return Integer.parseInt(n);}
	static int dx[] = {1,0,0,-1};
	static int dy[] = {0,1,-1,0};
	
	static int dxp[] = {-1,1};
	static int dyp[] = {1,1};
	
	static int dxi[] = {-1,1};
	static int dyi[] = {-1,-1};
	
	static int empj;
	static boolean[][] clonar(boolean mat[][]){
		boolean nn[][] = new boolean[mat.length][mat[0].length];
		for(int i=0;i<nn.length;i++)
			for(int j=0;j<nn[0].length;j++)
			nn[i][j] = mat[i][j];
		return nn;
	}
	static void dfs(int i, int j, boolean v[][], StringBuilder sb){
		if(v[i][j])return;
		if(mat[i][j]==0)return;
		v[i][j] = true;
//		System.out.println("estoy "+i+" "+j+ " "+sb);
		if(sb.length()>lon && sb.charAt(sb.length()-1)!=sb.charAt(sb.length()-lon-1))
				return;
		boolean visito = false;
		for(int k=0;!visito && k<mat[mat.length-1].length;k++)
			visito = v[mat.length-1][k];
		if(sb.length()%lon==0 && sb.length()/lon>1 && i==0 && j>empj && visito){
			camino = (sb.length()<camino.length() || camino.equals("")?sb.toString():camino);
//			System.out.println(camino);
			return;
		}
		for(int k=0;k<dx.length;k++){
			int posx = dx[k]+i, posy = dy[k]+j;
//			System.out.println("intento "+posx+" "+posy+" estoy "+i+" "+j+" cad "+sb);
			if(posx< mat.length && posx>-1 && posy <mat[0].length && posy > -1){
				StringBuilder sb2= new StringBuilder(sb);
				boolean vv[][] = clonar(v);
				dfs(posx, posy, vv,sb2.append(mat[posx][posy]));
			}
		}
		int evx[], evy[];
		if((i&1)==0){
			evx = dxp;
			evy = dyp;
		}
		else{
			evx = dxi;
			evy = dyi;
		}
		for(int k=0;k<evx.length;k++){
			int posx = evx[k]+i, posy = evy[k]+j;
//			System.out.println("intento "+posx+" "+posy+" estoy "+i+" "+j+" cad "+sb);
			if(posx< mat.length && posx>-1 && posy <mat[0].length && posy > -1){
				StringBuilder sb2= new StringBuilder(sb);
				boolean vv[][] = clonar(v);
				dfs(posx, posy, vv,sb2.append(mat[posx][posy]));
			}
		}
	}
	
	public static void main(String jkdfdp[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0")){
			StringTokenizer st = new StringTokenizer(input);
			int fils = atoi(st.nextToken()), cols = atoi(st.nextToken());
			lon = atoi(st.nextToken());
			mat = new char[fils][cols+1];
			camino = "";
			for(int i=0;i<fils;i++){
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<cols+((i&1)==0?0:1);j++){
					String ttt = st.nextToken();
					mat[i][j] = ttt.charAt(0);
					}
				}
//			for(int i=0;i<mat.length;i++){
//				for(int j=0;j<mat[i].length;j++)
//					System.out.print(mat[i][j]+" ");
//				System.out.println();
//				}
			for(int j=0;j<mat.length;j++){
				boolean v[][] = new boolean[mat.length][mat[0].length];
				StringBuilder sb = new StringBuilder("");
				empj = j;
				dfs(0,j,v, sb.append(mat[0][j]));
			}
			if(!camino.equals(""))
				System.out.println(camino);
			else
				System.out.println("no solution");
		}
	}
}