import java.io.*;
import java.util.*;
public class Rectangle {
	static long atoi(String n){return Long.parseLong(n);}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("0 0")){
			int n = Integer.parseInt(input.split(" ")[0]);
			int c = Integer.parseInt(input.split(" ")[1]);
			long nums[] = new long[n+1];
			int l[] = new int[n+1];//indice max a la izquierda que puede ir
			int r[] = new int[n+1];//indice max a la derecha que puede ir
			int col[][] = new int[n+1][c+1];
			String arr[] = br.readLine().split(" ");
			for(int i=1;i<=n;i++)
				nums[i] = atoi(arr[i-1]);
			arr = br.readLine().split(" ");
			for(int i=1;i<=n;i++){
				int cc = Integer.parseInt(arr[i-1])+1;
				for(int j=1;j<=c;j++)
					col[i][j] = col[i-1][j]+((j==cc)?1:0);
				}
			//izquierdas
			Stack<Integer> st = new Stack<Integer>();
			for(int i=1;i<=n;i++){
				while(!st.isEmpty() && nums[st.peek()]>=nums[i])
					st.pop();
				if(st.isEmpty())
					l[i] = 1;
				else
					l[i] = st.peek()+1;
				st.push(i);
			}
			//derechas
			st.clear();
			for(int i=n;i>0;i--){
				while(!st.isEmpty() && nums[st.peek()]>=nums[i])
						st.pop();
				if(st.isEmpty())
					r[i]=n;
				else
					r[i] = st.peek()-1;
				st.push(i);
			}
			long max = 0;
			for(int i=1;i<=n;i++){
				boolean puede = true;
				int izq = l[i],der = r[i];
				for(int j=1;j<=c && puede;j++)
					puede = (col[der][j]-col[izq-1][j]>0);
				if(puede && ((der-izq+1)*nums[i])>max)
					max = (der-izq+1)*nums[i];
			}
			System.out.println(max);
		}
	}
}
