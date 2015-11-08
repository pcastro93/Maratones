import java.io.*;
import java.util.*;
public class pursuit{
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int us[]= {1,0,-1};
		int vs[] = {1,0,-1};
		StringBuilder sb = new StringBuilder();
		while((input = br.readLine())!=null && !input.equals("")){
			input = input.replaceAll("( )+", " ");
			String div[]= input.split(" ");
			int a = atoi(div[0]);
			int u = atoi(div[1]);
			int v = atoi(div[2]);
			if(a==0){
				sb.append("0").append("\n");
				continue;
			}
			int px=0, py=1,lx=a,ly=v,contx = 0, conty=1;
			int t = 0;
			while (px < lx){
				++t;
				px  += t;
				lx  += u;
			}
			int min = t;
			py = 1;
			ly = v;
			t = 1;
			while (py < ly){
				++t;
				py += t;
				ly += v;
			}
			if (t > min) min = t;
			//int ans = (int)Math.max(contx, conty);
			sb.append(min).append("\n");
		}
		System.out.print(sb);
	}
}
