import java.io.*;
import java.util.*;

public class largestsum{
	static int atoi(String n){return Integer.parseInt(n);}
	static String cads[] = new String[7];
	static boolean fin = false;
	static int aaa[][];
	static int ccc = 0;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char p(int j, int i){
		return cads[aaa[j][0]].charAt((aaa[j][1]+i)%6);
	}
	static void f(int arr[][], int v, boolean[] coj)throws Exception{
		if(fin)return;
		aaa = arr;
		switch(v){
			case 2:
				if(p(0,0)!=p(1,3))
					return;
			break;
			case 3:
				if(p(1,2)!=p(2,5) || p(0,1)!=p(2,4))
					return;
			break;
			case 4:
				if(!(p(3,0)==p(2,3) && p(3,5)==p(0,2)))
					return;
			break;
			case 5:
				if(!(p(0,3)==p(4,0) && p(4,1)==p(3,4)))
					return;
			break;
			case 6:
				if(!(p(5,1)==p(0,4) && p(5,2)==p(4,5)))
					return;
				break;
			case 7:
				if(!(p(6,1)==p(1,4) && p(6,2)==p(0,5) && p(6,3)==p(5,0)))
					return;
				fin = true;
				return;
			break;
		}
		for(int j=0;j<cads.lengt && !fin;j++){
			if(coj[j])continue;
			int arr2[][] = (int[][])arr.clone)(;
			for(int i=0;i<6 && !fin;i++){
				boolean coj2[] = (bolean[])coj.clone();
				coj2[j] = true;
				arr2[v][0] = j;
				arr2[v][1] = i;
				f(arr2, v+1, coj2);
			}
		}
	}
	public static void main(String jfkdfd[]){
		String input = "";
		int conta = 0;
		StringBuilder sb = new StringBuilder("");
		long t1 = System.currentTimeMillis();
		while((input = br.readLine())!=null){
			cads[0] =input.replaceAll(" ", "");
			for(int i=1;i<cads.length;i++)
				cads[i] = br.readLine().replaceAll(" ", "");
			fin = false;
			int arr[][] = new int[7][2];
			boolean eee[] = new boolean[7];
			f(arr, 0, eee)
			if(fin)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
