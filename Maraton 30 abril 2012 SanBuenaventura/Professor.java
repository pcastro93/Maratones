import java.io.*;

public class Professor {
	public static void main(String fjdkfjdk[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while(!(input = br.readLine()).equals("0 0 0")){
			long arr[] = new long[5];
			arr[0] = Long.parseLong(input.split(" ")[0]);
			arr[1] = Long.parseLong(input.split(" ")[1]);
			arr[2] = (1 + arr[1])/arr[0];
			arr[3] = (arr[0]+arr[1]+1)/(arr[0]*arr[1]);
			arr[4] = (arr[0]+1)/arr[1];
			System.out.println(arr[(int)(Long.parseLong(input.split(" ")[2])%5)]);
		}
		}
}
