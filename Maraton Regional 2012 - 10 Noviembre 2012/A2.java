import java.io.*;
import java.util.*;
public class A2{
	static int solve1(boolean nums[]){
		int tot = 0;
			for(int i=0;i<nums.length-1;i++)
				if(!nums[i] && !nums[i+1]){
					tot++;
					nums[i+1] = true;
				}
			if(nums[0]==nums[nums.length-1] && !nums[0])
				tot++;
		return tot;
	}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input = br.readLine())!=null){
			int n = Integer.parseInt(input);
			boolean nums[] = new boolean[n];
			String tmp[] = br.readLine().split(" ");
			for(int i=0;i<n;i++)
				nums[i] = (Integer.parseInt(tmp[i])==1);
			int d1 = solve1(nums);
			System.out.println(d1);
		}
	}
}
