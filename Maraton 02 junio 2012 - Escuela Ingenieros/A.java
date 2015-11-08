import java.io.*;
import java.util.*;
public class A {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static void main(String fjdkfjdk[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while(!(input = br.readLine()).equals("0")){
			int f = atoi(input);
			int arr[] = new int[f];
			Arrays.fill(arr,Integer.MAX_VALUE);
			boolean d[] = new boolean[f];
			int act = 1;
			for(int i=0;i<f;i++){
				int cant = atoi(input = br.readLine());
				int cont = 0;
				int pos = 0;
				while(cont<cant)
					if(act < arr[pos++])
						cont++;
				while(d[pos])pos++;
				arr[pos]=act++;
				d[pos] = true;
			}
			for(int k=0;k<arr.length-1;k++)
				System.out.print(arr[k]+",");
			System.out.println(arr[arr.length-1]);
			
		}
	}
}
