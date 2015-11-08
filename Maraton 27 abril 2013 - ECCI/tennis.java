import java.io.*;
import java.util.*;
public class tennis{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jfkdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("")){
			char arr[] = input.toCharArray();
			int jact = 0;
			int ga = 0;
			int cont[] = new int[2];
			int tot[] = new int[2];
			for(int i=0;i<arr.length;i++){
				if(arr[i]=='S')
					cont[jact]++;
				else
					cont[jact^1]++;
				if(cont[0]>=4 && cont[0]-cont[1]>=2){
					jact^=1;
					tot[0]++;
					cont = new int[2];
					}
				else if(cont[1]>=4 && cont[1]-cont[0]>=2){
					jact^=1;
					tot[1]++;
					cont = new int[2];
				}
				if((tot[0]>=6 && tot[0]-tot[1]>=2) || (tot[1]>=6 && tot[1]-tot[0]>=2))
					break;
			}
			System.out.println(tot[0]+"-"+tot[1]);
		}
	}
}