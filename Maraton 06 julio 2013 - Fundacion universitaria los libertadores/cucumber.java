import java.io.*;
import java.util.*;
public class cucumber {
public static void main(String args[]) throws IOException{
BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
String tmp = "";
while((tmp = lector.readLine())!=null && !tmp.equals("0 0 0")){
String tmpp = lector.readLine();
String ttt[] = tmp.split(" ");
String tt[] = tmpp.split(" ");
int max[] = new int[Integer.parseInt(ttt[2])];
long maxx = 0;
int p [] = new int[tt.length];
for(int  n = 0;n<tt.length;n++)
p[n]=Integer.parseInt(tt[n]);
Arrays.sort(p);
for(int m = p.length-1,c=0;m>-1 && c<Integer.parseInt(ttt[2]);m--,c++)
maxx+=p[m];
//System.out.println(maxx+" "+Integer.parseInt(ttt[1]));
if(maxx>Integer.parseInt(ttt[1]))System.out.println("NO");
else
System.out.println("YES");
}
}
}