import java.io.*;
import java.util.*;
public class{
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input="";
		while((input = br.readLine())!=null){
			if(Integer.parseInt(input)%6==0)
				System.out.println("Y");
			else
				System.out.println("N");
		}
	}
}
