import java.io.*;
import java.util.*;

public class hittingtargets {
	static double eps = 1e-9;
	public static int atoi(String n) {return Integer.parseInt(n);}
	public static double atod(String n){return Double.parseDouble(n);}
	
	static boolean dentroCircle(Circle c, Punt p){
		double dx = c.x-p.x;
		double dy = c.y-p.y;
		double dist = Math.hypot(dx, dy);
		if(dist-c.r<=eps)
			return true;
		return false;
	}

	static boolean dentroRect(Rect r, Punt p){
		if(
				p.x-r.x1>=-eps && p.x-r.x2<=eps
				&&
				p.y-r.y1>=-eps && p.y-r.y2<=eps
				)
			return true;
		return false;
	}
	public static void main(String fjkdfd[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tar = atoi(br.readLine());
		ArrayList<Circle> circs = new ArrayList<Circle>();
		ArrayList<Rect> rects = new ArrayList<Rect>();
		
		for(int i=0;i<tar;i++){
			String input[] = br.readLine().split(" ");
			if(input[0].equals("rectangle")){
				rects.add(new Rect(atod(input[1]), atod(input[2]),atod(input[3]),atod(input[4])));
			}else
				circs.add(new Circle(atod(input[1]), atod(input[2]), atod(input[3])));
		}
		int nsho = atoi(br.readLine());
		Punt[] targets =new Punt[nsho];
		for(int i=0;i<targets.length;i++){
			String input[] = br.readLine().split(" ");
			targets[i] = new Punt(atod(input[0]), atod(input[1]));
			int sum = 0;
			for(int j=0;j<circs.size();j++)
				if(dentroCircle(circs.get(j), targets[i]))
					sum++;
			for(int j=0;j<rects.size();j++)
				if(dentroRect(rects.get(j), targets[i]))
					sum++;
			System.out.println(sum);
		}
	}
}

class Circle{
	double x,y, r;
	Circle(double x, double y, double r){
		this.x = x;
		this.y = y;
		this.r = r;
	}
}
class Rect{
	//x1, y1, abajo izquierda
	//x2, y2 arriba derecha
	double x1, x2, y1,y2;
	Rect(double x1, double y1, double x2, double y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}
class Punt{
	double x, y;
	Punt(double x, double y){
		this.x = x;
		this.y = y;
	}
}
