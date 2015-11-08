import java.io.*;
import java.util.*;
public class triangle{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jfkdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb = new StringBuilder();
		while((input = br.readLine())!=null && !input.equals("")){
			int n = atoi(input);
			long sum = 0;
			for(int i=1;i<n+1;i++){
				int fin = (i*(i+1))/2;
				for(int j=0;j<i;j++){
					for(int k=j;k<i;k++){
//						System.out.println("i "+i+" j "+j+" k "+k);
						if(k-j+i<n){
							sum++;
//							System.out.println("sum++");
							}
						if(j-k+i>0){
							sum++;
//							System.out.println("sum++");
							}
					}
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.print(sb);
	}
}