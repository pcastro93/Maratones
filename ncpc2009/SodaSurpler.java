import java.io.*;
import java.util.*;
public class SodaSurpler{
	public static void main(String args[])throws IOException{
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		while(test-->0){
		int n = sc.nextInt()+sc.nextInt();
		int q = sc.nextInt();
		int tot = 0;
		while(n>=q){
			tot+=n/q;
			n=(n/q+n%q);
		}
		System.out.println(tot);
		}
	}
}
