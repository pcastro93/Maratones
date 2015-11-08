import java.io.*;
import java.util.*;
public class AdvancedEditDistance {

	public static int DamerauLevenshteinDistance(String source, String target){
	    int m = source.length();
	    int n = target.length();
	    int[][] H = new int[m + 2][n + 2];
	    
	    int INF = m + n;
	    H[0][0] = INF;
	    for (int i = 0; i <= m; i++) { H[i + 1][ 1] = i; H[i + 1][ 0] = INF;}
	    for (int j = 0; j <= n; j++) { H[1][ j + 1] = j; H[0][j + 1] = INF;}
	    HashMap<Character, Integer> sd = new HashMap<Character, Integer>();
	    for (char Letter: (source + target).toCharArray()){
	        if (!sd.containsKey(Letter))
	            sd.put(Letter, 0);
	    }
	    for (int i = 1; i <= m; i++){
	        int DB = 0;
	        for (int j = 1; j <= n; j++){
	            int i1 = sd.get(target.toCharArray()[j - 1]);
	            int j1 = DB;
	            if (source.toCharArray()[i - 1] == target.toCharArray()[j - 1]){
	                H[i + 1][j + 1] = H[i][j];
	                DB = j;
	            }
	            else{
	                H[i + 1] [j + 1] = Math.min(H[i][j], Math.min(H[i + 1][ j], H[i][ j + 1])) + 1;
	            }
	            H[i + 1] [j + 1] = Math.min(H[i + 1][j + 1], H[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1));
	        }
	        sd.put(source.toCharArray()[i - 1], i);
	    }
	    return H[m + 1][n + 1];
	}
	public static void main(String jfkd[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		while((input=br.readLine())!=null && !(input).equals("* *"))
			System.out.println(DamerauLevenshteinDistance(input.split(" ")[0], input.split(" ")[1]));
	}
}
