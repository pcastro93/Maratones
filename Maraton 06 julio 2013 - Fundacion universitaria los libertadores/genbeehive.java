import java.io.*;
import java.util.*;
public class genbeehive{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*int t = 50;
		StringBuilder sb = new StringBuilder("");
		sb.append(t).append("\n");
		while(t-->0){
			sb.append("\n");
			int n=500, m = 20000;
			sb.append(n+" "+m).append("\n");
			for(int i=0;i<n;i++){
				int k=0;
				for(int j=0;j<m/n;j++, k++){
					if(k==i)k++;
					sb.append(i+" "+k).append("\n");
						}
			}
			if(sb.length()>40000){
				System.out.print(sb);
				sb = new StringBuilder("");
				}
		}
		System.out.print(sb);*/
		int cas = 0;
		int bus = 22;
		String input = "";
		while((input = br.readLine())!=null && cas!=bus)
			if(input.equals(""))
				cas++;
		System.out.println(input);
		while((input = br.readLine())!=null && !input.equals(""))
			System.out.println(input);
	}
}
