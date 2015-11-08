import java.io.*;


public class hero {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static void main(String ffdkfd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while ((input = br.readLine()) != null && !input.equals("0")){
			int f = atoi(input);
			int cont = 0;
			for(int i=0;i<f;i++)
				if(!(input = br.readLine()).contains("CD"))cont++;
			System.out.println(cont);
		}
	}
}
