import java.io.*;
import java.util.*;
public class eleven{
	static int atoi(String n){return Integer.parseInt(n);}
	static void print(int arr[]){for(int i=0;i<arr.length;i++)System.out.print(arr[i]+" ");System.out.println();}
	static void print(int arr[][]){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[j].length;j++)
				System.out.print(arr[i][j]+" ");
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String jfkd[])throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int coins[] = {9,9,9,5};//new int[100];
		//for(int i=0;i<coins.length;i++)coins[i] = 9;
		int maxsum = 0, maxval = -1;
		for(int i:coins){
			maxsum+=i;
			maxval = Math.max(maxval, i);
		}
		//formas[i][j] es formas de hacer el numero i con j elementos
		int formas[][] = new int[2*(maxsum+maxval)+1][coins.length+1];
		int formasant[][] = new int[2*(maxsum+maxval)+1][coins.length+1];
		int cero = formas.length/2;
		formas[cero][0] = 1;
		formasant[cero][0] = 1;
		System.out.println("Inicial");
		print(formas);
		//print(formasant);
		System.out.println();
		for(int c:coins){
			for(int i=formas.length/2+maxsum;i>=(-maxsum+formas.length/2);i--){//sin repetir el mismo elemento
				for(int l=0;l<formas[i].length-1;l++){
					formas[i][l+1]+=formas[i-c][l];
					formas[i][l+1]+=formasant[i+c][l];
				}
			}
			for(int i=0;i<formas.length;i++)
				formasant[i] = Arrays.copyOf(formas[i],formas[i].length);
			System.out.println(c);
			print(formas);
			//print(formasant);
			System.out.println();
		}
		for(int i=0;i<formas.length;i++){
			System.out.println((i-cero));
			print(formas[i]);
		}
		int subset = 0;
		for(int i=0;i<formas.length;i++)
			if((i-cero)%11==0)
				subset+=formas[i][formas[i].length-1];
		System.out.println("subset "+subset);
	}
}
