import java.io.*;
import java.util.*;

public class nonogram{
	public static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String fdjfkd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!= null && !input.equals("0")){
			int tam = atoi(input);
			boolean mat[][] = new boolean[tam][tam];
			for(int i=0;i<tam;i++){
				input = br.readLine();
				for(int j=0;j<tam;j++)
					mat[i][j] = input.charAt(j)=='X';
						}
			String res = "";
			for(int i=0;i<tam;i++){
				int lleva = 0;
				boolean d = false;
				for(int j=0;j<tam;j++)
					if(!mat[i][j] ){
						if(lleva!=0)
						res+=(lleva+" ");
						lleva =0;
					}else{
						d = true;
						lleva++;
						}
				if(lleva!=0 || !d)
					res+=(lleva);
				System.out.println(res.trim());
				res="";
			}
			res="";
			for(int i=0;i<tam;i++){
				int lleva = 0;
				boolean d = false;
				for(int j=0;j<tam;j++)
					if(!mat[j][i] ){
						if(lleva!=0)
						res+=(lleva+" ");
						lleva =0;
					}else{
						d = true;
						lleva++;}
				if(lleva!=0 || !d)
					res+=(lleva);
				System.out.println(res.trim());
				res="";
			}
			
				
		}
		
	}
}