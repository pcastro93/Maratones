import java.io.*;
import java.util.*;
public class D{
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null){
			String tmp[] = input.split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			int res = b-a+1;
			for(int i=a;i<=b;i++){
				char cad[] = (""+i).toCharArray();
				HashSet<Character> ss = new HashSet<Character>();
				for(char c:cad)
					ss.add(c);
				if(ss.size()!=cad.length)
					res--;
			}
			System.out.println(res);
		}
	}
}
