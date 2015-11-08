import java.io.*;
import java.util.*;

public class classsch {
	public static int atoi(String n) {
		return Integer.parseInt(n);
	}

	public static void main(String fjkdfdfd[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int tot = atoi(br.readLine());
		for(int i=0;i<tot;i++){
			int c,t,l;
			c = atoi((input = br.readLine()).split(" ")[0]);
			t = atoi((input).split(" ")[1]);
			l = atoi((input).split(" ")[2]);
			Point mat[][] = new Point[c][t];
			for(int k=0;k<c;k++){
				for(int m=0;m<t;m++){
					mat[k][m] = new Point(atoi((input = br.readLine()).split(" ")[0]),atoi(input.split(" ")[1]));
						}
			}
			Point p = new Point(l,0);
			int energia = 0;
			for(int tt=mat.length-1;tt>-1;tt--){
				Point tmp = new Point(0, Integer.MAX_VALUE/2);
				for(int ttt=mat[tt].length-1;ttt>-1;ttt--){
					tmp = max(tmp,mat[tt][ttt], p);
				}
//				System.out.println((int)Math.abs(tmp.x-p.x));
				energia+=tmp.y+(int)Math.abs(tmp.x-p.x);
				p = tmp;
			}
			System.out.println(energia+p.x);
		}
	}
	public static Point max(Point p1, Point p2, Point p){
		if(p2.y+(int)Math.abs(p.x - p2.x)>=p1.y+(int)Math.abs(p.x - p1.x)){
//			System.out.println(p1);
			return p1;
			}
//		System.out.println(p2);
		return p2;
	}
}
class Point{
	int x, y;
	Point(int x, int y){
		this.x =x;
		this.y = y;
	}
}