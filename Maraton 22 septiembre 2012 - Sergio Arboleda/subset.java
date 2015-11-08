import java.io.*;
import java.util.*;

public class subset {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}
	public static void main(String fjkdfdfd[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int tot = atoi(input = br.readLine());
		for(int i=0;i<tot;i++){
			long m = Long.parseLong((input = br.readLine()).split(" ")[0]);
			int totnums = atoi(input.split(" ")[1]);
			long nums[] = new long[totnums];
			for(int k=0;k<nums.length;k++)
				nums[k] = Long.parseLong(input = br.readLine());
			long min = Long.MAX_VALUE;
			for(int k=0;k<(1<<totnums);k++){
				long l =k;
				long a = 0;
				int b = 0;
				do{
					if((l&1)==1)
						a+=nums[b];
					b++;
				}while((l>>=1)>0);
				if(a>=m)
					min = (long)Math.min(min, a);
			}
			if(min==Long.MAX_VALUE)
				System.out.println("IMPOSSIBLE");
			else
			System.out.println(min);
		}
	}
}