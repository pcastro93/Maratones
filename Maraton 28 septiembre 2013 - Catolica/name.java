import java.io.*;
import java.util.*;

public class name{

	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String jkdfdp[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null && !input.equals("END")){
			int pos=0, pos2 =0;
			int c =0;
			for(int i=0;i<input.length();i++)
				if(input.charAt(i)=='\"')
					c++;
			if(c!=2){
				System.out.println("not a quine");
				continue;
			}
			String a1 = input.substring(pos = input.indexOf("\"")+1,pos2 = input.lastIndexOf("\""));
			if(pos2+2>=input.length()){
				System.out.println("not a quine");
				continue;
			}
			String a2 = input.substring(pos2+2);
			if(a1.length()<=0 || a2.length()<=0){
				System.out.println("not a quine");
				continue;
			}
			//System.out.println(a1);
			//System.out.println(a2);
			if(a1.equals(a2) && input.charAt(pos2+1)==' ')
				System.out.println("Quine("+a2+")");
			else
				System.out.println("not a quine");
		}
	}
}