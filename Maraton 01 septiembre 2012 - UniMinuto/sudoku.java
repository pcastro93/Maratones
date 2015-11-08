import java.io.*;
import java.util.*;
public class sudoku{
	public static boolean funca = false;
	public static ArrayList<String> zeros;

	public static boolean funciona(int mat[][]){
		for(int i=0;i<mat.length;i++){
			HashSet<Integer> set1 = new HashSet<Integer>();
			HashSet<Integer> set2 = new HashSet<Integer>();
			for(int j=0;j<mat.length;j++){
				set1.add(mat[i][j]);
				set2.add(mat[j][i]);
			}
			if(set1.size()<9 || set2.size()<9)return false;
		}
		for(int i=0;i<9;i++){
			HashSet<Integer> set3 = new HashSet<Integer>();
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					set3.add(mat[(i%3)*3+j][(i/3)*3 + k]);
					//				System.out.println(((i%3)*3+j) + " " + ((i/3)*3 + k) + " " + i);
				}
			}
			if(set3.size()<9)return false;
		}
		return true;
	}
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static int pos1(String n){
		return atoi(n.split(" ")[0]);
	}
	public static int pos2(String n){
		return atoi(n.split(" ")[1]);
	}
	public static void imp(int mat[][]){
		for(int i=0;i<mat.length;i++){
			for(int j=0;j<mat[i].length;j++)
				System.out.print(mat[i][j]);
			System.out.println();
		}
	}
	public static void llenar(int pos, int mat[][]){
		if(pos==zeros.size()-1){
			for(int i=1;i<10 && !funca;i++){
				mat[pos1(zeros.get(pos))][pos2(zeros.get(pos))] = i;
				if(funciona(mat)){
					funca = true;
					return;
				}
			}
			return;
		}
		for(int i=1;i<10 && !funca;i++){
			mat[pos1(zeros.get(pos))][pos2(zeros.get(pos))] = i;
			llenar(pos+1, mat);
		}
	}
	public static void main(String jfkdfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int n = atoi(input=br.readLine());
		for(int ll=0;ll<n;ll++){
			int mat[][] = new int[9][9];
			zeros = new ArrayList<String>();
			funca = false;
			for(int i=0;i<mat.length;i++){
				input = br.readLine();
				for(int j=0;j<input.length();j++){
					mat[i][j] = (input.charAt(j))-'0';
					if(mat[i][j]==0)
						zeros.add(i+" "+j);
				}
			}
			llenar(0,mat);
			if(funca)
				for(int i=0;i<mat.length;i++){
					for(int j= 0;j<mat[i].length;j++)
						System.out.print(mat[i][j]);
					System.out.println();
				}
			else
				System.out.println("Could not complete this grid.");
			System.out.println();
		}
	}
}
