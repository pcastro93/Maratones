import java.io.*;
import java.util.*;
public class edgetown{
	static int costos[][];
	static int costos2[][];
	static int atoi(String n){return Integer.parseInt(n);}
	static void floydWarshall(int mat[][]){
		for(int k=0;k<mat.length;k++)
			for(int i=0;i<mat.length;i++)
				for(int j=0;j<mat.length;j++){
					//Si hay camino entre i-k y entre k-j, i!=j y se cumple la relajacion
					//La relajacion es que haya una mejor distancia o que la actual sea infinita(0)
					if(mat[i][k]*mat[k][j]!=0 && i!=j && (mat[i][j]>mat[i][k]+mat[k][j] || mat[i][j]==0))
						mat[i][j] = mat[i][k]+mat[k][j];
				}
	}
	public static void main(String args[])throws IOException{
		//BufferedReader br = new BufferedReader(new FileReader("edgetown.in"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input="";
		while(!(input=br.readLine()).equals("0")){
			int intersecciones = atoi(input);
			costos = new int[intersecciones][intersecciones];
			costos2 = new int[intersecciones][intersecciones];
			for(int i=0;i<intersecciones;i++){
				input = br.readLine();
				String div[]=input.split(" ");
				int pos = atoi(div[0]);
				for(int j=1;j<div.length;j++)
					costos[pos-1][atoi(div[j])-1]=1;
			}
			for(int i=0;i<intersecciones;i++){
				input = br.readLine();
				String div[]=input.split(" ");
				int pos = atoi(div[0]);
				for(int j=1;j<div.length;j++)
					costos2[pos-1][atoi(div[j])-1]=1;
			}
			input = br.readLine();
			int a = atoi(input.substring(0,input.indexOf(" ")));
			int b = atoi(input.substring(input.indexOf(" ")+1,input.length()));
			floydWarshall(costos);
			floydWarshall(costos2);
			boolean cumple=true;
			for(int i=0;i<costos.length && cumple;i++){
				for(int j=0;j<costos.length && cumple;j++){
					if(costos[i][j]*a+b < costos2[i][j] && i!=j){
						cumple=false;
						System.out.println("No");
					}
				}
			}if(cumple)
			System.out.println("Yes");
		}
	}
}
