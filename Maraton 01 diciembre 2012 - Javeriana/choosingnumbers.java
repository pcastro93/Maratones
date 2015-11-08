import java.io.*;
import java.util.*;

public class choosingnumbers {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}
	
	public static int gcd(int a,int b){
		return (b==0)?a:gcd(b,a%b);
	}

	public static void main(String fjkdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while ((input = br.readLine()) != null) {
			String arr[] = input.split(" ");
			int nums[] = new int[arr.length-1];
			for(int i=1;i<arr.length;i++)
				nums[i-1] = atoi(arr[i]);
			Arrays.sort(nums);
			int max = -1;
			for(int i=nums.length-1;i>-1 && max==-1;i--){
				int tmp = nums[i];
//				System.out.println("i = "+i);
				boolean coptod = true;
				for(int j=0;j<nums.length && coptod;j++){
//					System.out.println("j = "+j);
					if(j!=i&&gcd(nums[j],tmp)!=1)
						coptod = false;
					}
				if(coptod){
					max = tmp;
//					System.out.println("ccc");
					}
			}
			System.out.println(max);
		}
	}
}
