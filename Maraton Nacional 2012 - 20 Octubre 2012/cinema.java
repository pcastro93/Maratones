import java.io.*;
import java.util.*;

public class cinema {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}
public static void imp(boolean mat[][]){
	for(int i=0;i<mat.length;i++){
		for(int j=0;j<mat[i].length;j++){
			System.out.print(mat[i][j]+" ");
		}
		System.out.println();
	}
}
	public static void main(String jkfdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("cinema.in"));
		String input = "";
		
		while ((input = br.readLine()) != null && !input.equals("0 0")) {
			int filas = atoi(input.split(" ")[0]);
			int columnas = atoi(input.split(" ")[1]);
			boolean mat[][] = new boolean[filas][columnas+1];
			int fijas = atoi(br.readLine());
			for(int i=0;i<fijas;i++){
				input = br.readLine();
				boolean izquierda = (input.split(" ")[1].equals("-"));
				int fila = input.split(" ")[0].charAt(0)-'A';
				int columna = atoi(input.split(" ")[0].substring(1))-1;
				mat[fila][columna+(izquierda?0:1)] = true;
			}
			int reserv = atoi(br.readLine());
			ArrayList<String>arr = new ArrayList<String>(reserv);
			for(int i=0;i<reserv;i++){
				input = br.readLine();
				arr.add(input);
			}
			Collections.sort(arr, new comp());
			boolean puede = true;
			
			for(int i=0;i<arr.size()&& puede;i++){
				input = arr.get(i);
				int fila = input.split(" ")[0].charAt(0)-'A';
				int columna = atoi(input.split(" ")[0].substring(1))-1;
//				System.out.println(input);
//				System.out.println(fila+" "+columna);
				if(mat[fila][columna]){//si a la izquierda esta ocupado
					if(mat[fila][columna+1])//si derecha
						puede = false;
					else
						mat[fila][columna+1] = true;
				}else
				mat[fila][columna] = true;
			}
			if(puede)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
class comp implements Comparator<String>{

	@Override
	public int compare(String arg0, String arg1) {
		int let = arg0.charAt(0);
		int let2 = arg1.charAt(0);
		if(let==let2){
			int columna = Integer.parseInt(arg0.substring(1));
			int columna2 = Integer.parseInt(arg1.substring(1));
			return columna-columna2;
		}
		return let-let2;
	}
	}
