import java.io.*;
import java.util.*;

public class sokoban{

	static int df[] = {1,0,-1,0};
	static int dc[] = {0,-1,0,1};
	static String movs = "DLUR";
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jkdfdp[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int cont = 0;
		while((input = br.readLine())!=null && !input.equals("0 0")){
			StringTokenizer st = new StringTokenizer(input);
			int fils = atoi(st.nextToken()),cols = atoi(st.nextToken());
			int bs = 0;
			char mat[][] = new char[fils][cols];
			int wi=0, wj=0;
			for(int i=0;i<mat.length;i++){
				input = br.readLine();
				for(int j=0;j<mat[i].length;j++){
					mat[i][j] = input.charAt(j);
					if(mat[i][j]=='b')
						bs++;
					if(mat[i][j]=='w' || mat[i][j]=='W'){
						wi = i;
						wj = j;
						if(mat[i][j]=='W')
							mat[i][j] = '+';
					}
				}
			}
			String cam = br.readLine();
			for(int i=0;i<cam.length();i++){
				int posf = wi+df[movs.indexOf(cam.charAt(i))], posc=wj+dc[movs.indexOf(cam.charAt(i))];
				int posfs = wi+df[movs.indexOf(cam.charAt(i))]*2, poscs=wj+dc[movs.indexOf(cam.charAt(i))]*2;
				if(posf<mat.length && posc<mat[0].length && posfs<mat.length && poscs< mat[0].length){
					if(mat[posf][posc]=='.' || mat[posf][posc]=='+'){
						mat[wi][wj] = (mat[wi][wj]=='w'?'.':'+');
						wi = posf;
						wj = posc;
						if(mat[posf][posc]=='+')
							mat[posf][posc] = 'W';
						else
							mat[posf][posc] = 'w';
					}
					else if(mat[posf][posc]=='b' || mat[posf][posc]=='B'){
						if(mat[posfs][poscs]=='.' || mat[posfs][poscs]=='+'){
							char aa = mat[posf][posc];
							char bb = mat[posfs][poscs];
							mat[wi][wj] = (mat[wi][wj]=='w'?'.':'+');
							wi = posf;
							wj = posc;
							if(mat[posf][posc]=='B')
								mat[posf][posc] = 'W';
							else
								mat[posf][posc] = 'w';
							mat[posfs][poscs] = (mat[posfs][poscs]=='.'?'b':'B');
							if(aa=='b' && bb=='+')
								bs--;
							else if(aa=='B' && bb=='.')
								bs++;
							if(bs==0)
								break;
						}
					}
				}
			}
			boolean comp = true;
			for(int i=0;comp && i<mat.length;i++)
				for(int j=0;comp && j<mat[i].length;j++)
					if(mat[i][j]=='b')
						comp = false;
			System.out.println("Game "+ ++cont + ": "+(comp?"":"in")+"complete");
			StringBuilder sb = new StringBuilder("");
			for(int i=0;i<mat.length;i++){
				for(int j=0;j<mat[i].length;j++)
					sb.append(mat[i][j]);
				sb.append("\n");
				}
			System.out.println(sb.deleteCharAt(sb.length()-1));
		}
	}
}