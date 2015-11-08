import java.io.*;
public class fibonacciMatrix{
	public static int mod = 1000000009;
	public static long[][] matrixMultiply(long[][] matrix1, long[][] matrix2, long mod){
		long res[][] = new long[matrix1.length][matrix2[0].length];
		for(int i=0;i<matrix1.length;i++){
			for(int j=0;j<matrix2[0].length;j++){
				for(int k=0;k<matrix1[0].length;k++){
					res[i][j]=(((matrix1[i][k]*matrix2[k][j])%mod)+res[i][j])%mod;
				}
			}
		}
		return res;
	}
	public static long[][] modexp(long[][] matrix1, long b,long mod){
		if(b==0)return new long[matrix1.length][matrix1[0].length];
		long [][]res = new long[matrix1[0].length][matrix1.length];
		for(int i=0;i<res.length;i++)res[i][i] = 1;
		while(true){
			if((b&1)==1){
				res=matrixMultiply(res,matrix1,mod);
				//res%=mod;
			}
			b/=2;
			if(b==0)break;
			matrix1=matrixMultiply(matrix1, matrix1, mod);
			//a%=mod;
		}
		return res;
	}
	public static int expomod(int a, long b,int mod){
		if(b==0)return 1;
		int res = 1;
		while(true){
			if((b&1)==1){
				res*=a;res%=mod;}
			b/=2;
			if(b==0)break;
			a*=a;
			a%=mod;
		}
		return res;
	}
	public static void imprimir(long[][] matrix){
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[i].length;j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println();}
	}
	public static void main(String fdjkfd[])throws IOException{
		long matrix1[][] = {{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
		//for(int i=1;i<10;i++){
			//int i = 4;
			//System.out.println("Matrix1  ^"+(i-1) + "%"+mod);
			//long temp[][] = modexp(matrix1,(i-1),mod);
			//temp = matrixMultiply(temp, new long[][]{{2},{1},{0}},mod);
			//imprimir(temp);
		//}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while(!(input= br.readLine()).equals("0")){
			long temp[][] = modexp(matrix1,(Long.parseLong(input)-1),mod);
			temp = matrixMultiply(temp, new long[][]{{2},{1},{0}},mod);
			System.out.println(temp[2][0]);

		}
	}
}
