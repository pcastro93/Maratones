import java.io.*;
import java.util.*;

public class numbers {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static void main(String fjkdfdfd[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		ArrayList<String> nums = new ArrayList<String>();
		nums.add("ABC");
		nums.add("DEF");
		nums.add("GHI");
		nums.add("JKL");
		nums.add("MNO");
		nums.add("PQRS");
		nums.add("TUV");
		nums.add("WXYZ");
		int tot = atoi(input = br.readLine());
		for(int i=0;i<tot;i++){
			String cad = "";
			input = br.readLine();
			input = input.toUpperCase();
			for(int j=0;j<input.length();j++){
				for(int k=0;k<nums.size();k++)
					if(nums.get(k).contains(""+input.charAt(j))){
						cad+=""+k;
						break;
						}
			}
			boolean si = true;
			for(int l=0;l<cad.length()/2 && si;l++)
				if(cad.charAt(l)!=cad.charAt(cad.length()-l-1))
					si = !si;
			if(si)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}