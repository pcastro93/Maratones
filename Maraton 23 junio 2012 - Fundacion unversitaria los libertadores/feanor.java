
import java.io.*;

public class feanor {

    public static void main(String fjdkfjd[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while (!(input = br.readLine()).equals("-1 -1")) {
            double b = Integer.parseInt(input.split(" ")[0]);
            double h = Integer.parseInt(input.split(" ")[1]);
            double v = (b*Math.sqrt(b*b+19.6*h))/9.8;
            v*=Math.pow(10, 6);
            v = Math.round(v);
            String res = String.format("%.6f\n",v/1e6);
            System.out.println(res.replaceAll(",", "."));
        }
    }
}
