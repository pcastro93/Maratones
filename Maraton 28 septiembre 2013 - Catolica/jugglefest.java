import java.io.*;
import java.util.*;

public class jugglefest{

	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jkdfdp[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0")){
			StringTokenizer st = new StringTokenizer(input);
			int tot = atoi(st.nextToken());
			int arr[] = new int[tot];
			for(int i=0;i<tot;i++)
				arr[i] = atoi(st.nextToken());
			char res[] = new char[20];
			char c='A';
			int aux = 0;
			boolean panic = false;
			for(int i=0;i<res.length;i++){
//				for(int k=0;k<res.length;k++)
//					System.out.print(res[k]+" ");
//				System.out.println();
				if(res[i]==0){
					res[i] = c;
//					System.out.println(c+ " "+i);
					int j=i+arr[aux];
					if(j<res.length){
						if(res[j]!=0)
							panic = true;
						else{
							res[j] = c;
							}
					}
					c++;
					}
				else{
					int j=i+arr[aux];
					if(j<res.length){
						if(res[j]!=0)
							panic = true;
						else
							res[j] = res[i];
					}
				}
				aux = (aux+1)%arr.length;
			}
			if(!panic){
				StringBuilder sb = new StringBuilder("");
				for(int i=0;i<res.length;i++)
					sb.append(res[i]);
				System.out.println(sb);
				}
			else
				System.out.println("CRASH");
		}
	}
}