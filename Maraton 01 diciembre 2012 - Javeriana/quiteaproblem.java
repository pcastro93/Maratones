import java.io.*;
import java.util.*;

public class quiteaproblem {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static void main(String fjkdfd[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while ((input = br.readLine()) != null) {
			boolean si = input.toLowerCase().indexOf("problem")!=-1;
			System.out.println(((si)?"yes":"no"));
		}
	}
}