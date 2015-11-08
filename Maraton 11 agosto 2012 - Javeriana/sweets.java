import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class sweets {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static char[] p1 = {'>','o','<'};
	public static char[] p2 = {'v','o','^'};
	public static void main(String fdjfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
			int t = atoi(input = br.readLine());
			for(int k=0;k<t;k++){
				br.readLine();
				input = br.readLine();
				int filas = atoi(input.split(" ")[0]);
				int columnas = atoi(input.split(" ")[1]);
				char mat[][] = new char[filas][columnas];
				
				for(int i=0;i<filas;i++){
					input = br.readLine();
					for(int j=0;j<columnas;j++){
						mat[i][j] = input.charAt(j);
					}
				}
				int tot = 0;
				for(int i=0;i<mat.length;i++){
					for(int j=0;j<mat[i].length;j++){
						if(mat[i][j]=='>' && j+2<mat[i].length && mat[i][j+1]=='o' && mat[i][j+2]=='<'){
							tot++;
							mat[i][j]=mat[i][j+1]=mat[i][j+2]='.';
						}
						if(mat[i][j]=='v' && i+2<mat.length && mat[i+1][j]=='o' && mat[i+2][j]=='^'){
							tot++;
							mat[i][j]=mat[i+1][j]=mat[i+2][j]='.';
						}
					}
				}
				System.out.println(tot);
			}
	}
}
