import java.io.*;
import java.util.*;
import java.awt.geom.Point2D;
public class skyline{

	public static double cross(Point2D p1, Point2D p2, Point2D p3){
		return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
	}

	public static class Comp implements Comparator <Point2D>{
		@Override
		public int compare(Point2D p1, Point2D p2) {
			if(p1.getY() < p2.getY()) return -1;
			if(p1.getY() > p2.getY()) return 1;
			if(p1.getX() < p2.getX()) return -1;
			return 1;
		}
	}

	public static final double EPSILON = 1e-12;

	public static void convexHull(List <Point2D> points, List <Point2D> result){
		int n = points.size();
		Point2D[] p2 = new Point2D[points.size() + 1];
		Collections.sort(points, new Comp());
		int top = 0;
		p2[top++] = points.get(0);
		p2[top++] = points.get(1);
		for (int i = 2; i < n; i++){
			while (top >= 2 && cross(p2[top - 2], p2[top - 1], points.get(i)) <= -EPSILON)
				--top;
			p2[top++] = points.get(i);
		}
		int r = top;
		for (int i = n - 2; i >= 0; i--){
			while (top > r && cross(p2[top - 2], p2[top - 1], points.get(i)) <= -EPSILON)
				--top;
			if (i != 0)
				p2[top++] = points.get(i);
		}
		for(int i = 0; i < top; i++)
			result.add(p2[i]);
	}
	static int atoi(String n){return Integer.parseInt(n);}
	public static void main(String args[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Point2D[] puntos = new Point2D[8];
		puntos[0] = new Point2D.Double(0.0,2.0);
		puntos[1] = new Point2D.Double(2.0,3.0);
		puntos[2] = new Point2D.Double(2.0,5.0);
		puntos[3] = new Point2D.Double(3.0,6.0);
		puntos[4] = new Point2D.Double(4.0,6.0);
		puntos[5] = new Point2D.Double(4.0,4.0);
		puntos[6] = new Point2D.Double(5.0,5.0);
		puntos[7] = new Point2D.Double(5.0,0.0);
		List<Point2D> in = Arrays.asList(puntos);
		List<Point2D> out = new ArrayList<Point2D>();
		convexHull(in, out);
		for(Point2D p:out)
			System.out.println(p);
	}
}
