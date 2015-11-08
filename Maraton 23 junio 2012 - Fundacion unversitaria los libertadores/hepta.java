
import java.io.*;

public class hepta {
public static String p = "0123456789ABCDEFG";
    public static void main(String args[]) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String tmp = "";
        while ((tmp = lector.readLine()) != null && !tmp.equals("* *")) {
            String tal[] = tmp.split(" ");
            //System.out.println(comp(tal[0], tal[1]));
            System.out.println(comp2(tal[0], tal[1]));
        }
    }

    public static char comp(String a, String b) {
        int aa = bas(a), bb = bas(b);
        if (aa == bb)
            return '=';
        int r = (aa - bb) / Math.abs(aa - bb);
        return (r > 0) ? '>' : '<';
    }
    
    public static char comp2(String a, String b){
        while(a.length()>0 && a.charAt(0)=='0')a = a.substring(1);
        while(b.length()>0 && b.charAt(0)=='0')b = b.substring(1);
        if(a.length()>b.length())return '>';
        if(a.length()<b.length())return '<';
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i))
                return (Math.signum(p.indexOf(a.charAt(i) -p.indexOf(b.charAt(i))))==-1)?'<':'>';
        }
        return '=';
    }

    public static int bas(String a) {
        a = a.toUpperCase();
        int res = 0;
        for (int n = a.length() - 1,  c = 0; n > -1; n--, c++)
            res += Math.pow(17, c) * p.indexOf(a.charAt(n));
        return res;
    }
}
