import java.io.*;
import java.util.*;

public class game{
	static int atoi(String n){return Integer.parseInt(n);}
	static boolean mat[][][];
	static int m,n;
	static int dx[] = {-1,0,0,1};
	static int dy[] = {0,-1,1,0};
	static int dz[] = {1,1,-1,-1};
	
	static boolean uno(int x,int y, int z){
		int tiene = 0;
		for(int i=0;i<dx.length;i++){
			int posx = x+dx[i], posy = y+dy[i], posz = z+dz[i];
			if(posx+posy+posz==n &&
					posx>-1 && 
					posx<mat.length &&
					posy>-1 && 
					posy<mat.length &&
					posz>-1 && 
					posz<mat.length &&
					mat[posx][posy][posz])
				tiene++;
		}
		return tiene==1;
	}
	static boolean cumple(int x, int y, int z){
		int tiene = 0;
//		System.out.println(x+" "+y+" "+z);
		for(int i=0;i<2;i++){
			int posx = x+dx[i], posy = y+dy[i], posz = z+dz[i];
			//if(posx+posy+posz==mat.length)
//			System.out.println(posx+" "+posy+" "+posz+" "+mat.length);
				//System.out.println(mat[posx][posy][posz]+" "+posx+" "+posy+" "+posz);
			if(posx+posy+posz==n && posx>-1 && posx<mat.length && posy>-1 && posy<mat.length && posz>-1 && posz<mat.length && mat[posx][posy][posz] && uno(posx, posy, posz))
				tiene++;
		}
		if(tiene!=2)return false;
		tiene =0;
		for(int i=2;i<3;i++){
			int posx = x+dx[i], posy = y+dy[i], posz = z+dz[i];
			//if(posx+posy+posz==mat.length)
//			System.out.println(posx+" "+posy+" "+posz+" "+mat.length);
				//System.out.println(mat[posx][posy][posz]+" "+posx+" "+posy+" "+posz);
			if(posx+posy+posz==n && posx>-1 && posx<mat.length && posy>-1 && posy<mat.length && posz>-1 && posz<mat.length && mat[posx][posy][posz] && uno(posx, posy, posz))
				tiene++;
		}
		if(tiene==1)return true;
		return false;
//		System.out.println("tiene "+tiene);
	}
	public static void main(String fd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while((input = br.readLine())!=null){
			StringTokenizer st = new StringTokenizer(input);
			n = atoi(st.nextToken());
			m = atoi(st.nextToken());
			if(n==0 && m==0)break;
			mat = new boolean[n+1][n+1][n+1];
			ArrayList<int[]> unos = new ArrayList<int[]>();
			for(int i=0;i<m;i++){
				st = new StringTokenizer(br.readLine());
				int x = atoi(st.nextToken()), y = atoi(st.nextToken()),z = atoi(st.nextToken());
				mat[x][y][z] = true;
				int tmp[] = {x, y, z};
				unos.add(tmp);
			}
			boolean benny = false;
			for(int i=0;i<unos.size() && !benny;i++){
//				System.out.println(cumple(unos.get(i)[0], unos.get(i)[1],unos.get(i)[2]));
				if(cumple(unos.get(i)[0], unos.get(i)[1],unos.get(i)[2]))
					benny = true;
			}
			if(benny)
				sb.append("Benny\n");
			else
				sb.append("Willy\n");
		}
		System.out.println(sb);
	}
}