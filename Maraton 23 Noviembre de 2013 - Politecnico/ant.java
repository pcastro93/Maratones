import java.io.*;
import java.util.*;
import java.math.*;

public class ant{
	static int atoi(String n){return Integer.parseInt(n);}
	static int dir =2;
	static int df[]={-1,0,1,0};
	static int dc[]={0,1,0,-1};
	public static void main(String fd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder("");
		while((input = br.readLine())!=null){
			StringTokenizer st = new StringTokenizer(input);
			int n = atoi(st.nextToken());
			BigInteger c = new BigInteger(st.nextToken());
			int y = atoi(st.nextToken())-1,x= atoi(st.nextToken())-1;
			if(n==0 && c.equals(BigInteger.ZERO)&& x==-1 && y==-1)break;
			boolean mat[][] = new boolean[n][n];
			String binc = "";
			dir =2;
			do{
				binc = c.and(BigInteger.ONE)+binc;
				c = c.shiftRight(1);
			}while(!c.equals(BigInteger.ZERO));
			while(binc.length()<n*n)binc = "0"+binc;
			int j=0;
			for(int i=0;i<mat.length;i++){//for(int i=mat.length-1;i>-1;i--){
				for(int cc=0;cc<mat.length;j++, cc++)
					mat[i][cc] = binc.charAt(j)=='1';
			}
//			System.out.println(binc);
//			for(int i=0;i<mat.length;i++){
//				for(j=0;j<mat.length;j++)
//					System.out.print((mat[i][j]?"1":"0"));
//				System.out.println();
//			}
			boolean llego = false;
			while(true){
				if(x==n-1 && y==n-1){
					llego = true;
					break;
				}
				if(mat[x][y]){
					dir = (dir-1<0?3:dir-1);
				}else
					dir = (dir+1)%4;
				mat[x][y] = !mat[x][y];
				x+=df[dir];y+=dc[dir];
//				System.out.println(x+" "+y);
				if(x<=-1 || x>=mat.length || y<=-1 || y>=mat.length){
					llego = false;
					break;
					}
			}
			if(llego)
				sb.append("Yes\n");
			else
				sb.append("Kaputt!\n");
		}
		System.out.print(sb);
	}
}