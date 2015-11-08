import java.io.*;
import java.util.*;

public class jumps{
	static int atoi(String n){return Integer.parseInt(n);}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int res =0;
	static int todas =0;
	static boolean seguir = true;
	static int df[] = {-2,-2,-1,-1,1,1,2,2};
	static int dc[] = {-1,1,-2,2,-2,2,-1,1};
	static ArrayList<int[]> tab;
	static void dfs(int f, int c, boolean vis[][], int van)throws Exception{
//		if(vis[f][c])return;
//		System.out.println("visito "+f+" "+c);
//		br.readLine();
//		vis[f][c] = true;
		res = Math.max(res, van);
		if(res==todas)seguir = false;
		for(int i=0;i<df.length && seguir;i++){
			int posf = f+df[i], posc=c+dc[i];
			if(posf>-1 && posf<vis.length && posc>-1 && posc<vis[posf].length && posc>=tab.get(posf)[0] && posc<=tab.get(posf)[1] && !vis[posf][posc]){
//				System.out.println("llamo "+posf+" "+posc+" viene de "+f+" "+c);
				vis[posf][posc] = true;
				dfs(posf, posc, vis, van+1);
				vis[posf][posc] = false;
			}
		}
	}
	public static void main(String fd[])throws Exception{
		String input = "";
		StringBuilder sb = new StringBuilder("");
		int cas =0;
		while((input = br.readLine())!=null){
			StringTokenizer st = new StringTokenizer(input);
			int t = atoi(st.nextToken());
			if(t==0)break;
			todas = 0;
			seguir = true;
			res =0;
			boolean [][]mat=new boolean[t][10];
			tab = new ArrayList<int[]>();
			for(int i=0;i<t;i++){
				int a = atoi(st.nextToken()), b = atoi(st.nextToken());
				int tmp[] = {a,a+b-1};
				todas+=b;
				tab.add(tmp);
			}
			mat[0][tab.get(0)[0]] = true;
			dfs(0, tab.get(0)[0], mat,1);
			sb.append("Case "+ ++cas + ", ").append((todas-res)).append(" square"+(todas-res==1?"":"s")+" can not be reached.").append("\n");
		}
		System.out.print(sb);
	}
}

