import java.io.*;
import java.util.*;

public class ls {
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = "";
		while ((tmp= br.readLine()) != null) {
			int tt = Integer.parseInt(br.readLine());
			for (int n = 0; n < tt; n++) {
				String tmpp = br.readLine();
				int r = 0, rr = 0;
				boolean joder = true;
				for (int m = 0; m < tmp.length() && joder; m++) {
					if (r >= tmpp.length())
						joder = false;
					String jod = "";
					int p = m;
					while (p < tmp.length() && tmp.charAt(p) != '*')
						jod += tmp.charAt(p++);
					if (tmp.charAt(m) == '*')
						continue;
					if(p==tmp.length() && !tmpp.endsWith(jod))
						joder = false;
					r = tmpp.indexOf(jod, r);
					if (m == 0 && r > 0)
						joder = false;
					if (r == -1)
						joder = false;
					r += jod.length();
//					System.out.println(jod +  " " + r);
					m = p;
				}
				if (joder)
					System.out.println(tmpp);
			}
		}
	}
}