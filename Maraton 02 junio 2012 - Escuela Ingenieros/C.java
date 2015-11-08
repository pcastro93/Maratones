import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class C {
	static int atoi(String n){
		return Integer.parseInt(n);
	}
	static double cross(Point2 p1, Point2 p2, Point2 p3){
		return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
	}

	public static class Comp implements Comparator <Point2>{
		@Override
		public int compare(Point2 p1, Point2 p2) {
			if(p1.getY() < p2.getY()) return -1;
			if(p1.getY() > p2.getY()) return 1;
			if(p1.getX() < p2.getX()) return -1;
			return 1;
		}
	}

	public static final double EPSILON = 1e-12;

	public static void convexHull(ArrayList <Point2> points, ArrayList <Point2> result){
		int n = points.size();
		Point2[] p2 = new Point2[points.size() + 1];
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
	
	static void agregarEsquinas(int xi, int yi, int xf, int yf,ArrayList<Point2>puntos){
		puntos.add(new Point2(xi,yi));//esquina southwest
		puntos.add(new Point2(xf,yf));//esquina northeast
		puntos.add(new Point2(xi, yf));//esquina northwest
		puntos.add(new Point2(xf, yi));//esquina southeast
	}
	
	static boolean contiene(Polygon t, int x[], int y[], ArrayList<Point2> puntos){
		//Que no este dentro del poligono
		for(int k=0;k<puntos.size();k++)
				if(t.contains(puntos.get(k).x, puntos.get(k).y))
					return true;
		//Que no este en las lineas del poligono
		for(int k=0;k<puntos.size();k++){
			for(int i=0;i<x.length;i++){
				Line2D l = new Line2D.Double(x[i], y[i], x[(i+1)%x.length], y[(i+1)%x.length]);
				if(l.ptSegDist(puntos.get(k).x, puntos.get(k).y)<=0.0001)
					return true;
			}
		}
		return false;
	}
	static boolean intersectan(int x1[], int y1[], int x2[], int y2[]){
		//verificar que los segmentos de los poligonos no se intersecten con Line2D.intersectsLine(Line2D) o Line2D.intersectsLine(x1,y1, x2, y2)
		for(int i=0;i<x1.length;i++){
			Line2D l1 = new Line2D.Double(x1[i], y1[i], x1[(i+1)%x1.length], y1[(i+1)%x1.length]);
			for(int j=0;j<x2.length;j++){
				Line2D l2 = new Line2D.Double(x2[j], y2[j], x2[(j+1)%x2.length], y2[(j+1)%x2.length]);
				if(l1.intersectsLine(l2))
					return true;
			}
		}
		return false;
	}
	public static void main(String fjdkfjdk[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		StringBuilder sb= new StringBuilder("");
		int cas = 0;
		while(!(input = br.readLine()).equals("0 0")){
			String duv[] = input.split(" ");
			int d = atoi(duv[0]);
			int p = atoi(duv[1]);
			if(d==1 && p==1){
				sb.append("Case "+ ++cas + ": It is "+((false)?"not ":"") + "possible to separate the two groups of vendors.").append("\n");
				sb.append("\n");
				input = br.readLine();
				input = br.readLine();
				continue;
			}
			ArrayList<Point2> des = new ArrayList<Point2>();
			ArrayList<Point2> pes = new ArrayList<Point2>();
			for(int i=0;i<d;i++){
				String div[] = br.readLine().split(" ");
				int xi = atoi(div[0]);//esquina southwest
				int yi = atoi(div[1]);//esquina southwest
				int xf = atoi(div[2]);//esquina northeast
				int yf = atoi(div[3]);//esquina northeast
				agregarEsquinas(xi, yi, xf, yf, des);
			}
			for(int i=0;i<p;i++){
				String div[] = br.readLine().split(" ");
				int xi = atoi(div[0]);
				int yi = atoi(div[1]);
				int xf = atoi(div[2]);
				int yf = atoi(div[3]);
				agregarEsquinas(xi, yi, xf, yf, pes);
			}
			ArrayList<Point2> rdes = new ArrayList<Point2>();
			ArrayList<Point2> rpes = new ArrayList<Point2>();
			convexHull(pes,rpes);
			convexHull(des,rdes);
			int xd[] = new int[rdes.size()];
			int yd[] = new int[rdes.size()];
			int xp[] = new int[rpes.size()];
			int yp[] = new int[rpes.size()];
			
			for(int k=0;k<xd.length;k++){
				xd[k] = (int)rdes.get(k).x;
				yd[k] = (int)rdes.get(k).y;
			}
			for(int k=0;k<xp.length;k++){
				xp[k] = (int)rpes.get(k).x;
				yp[k] = (int)rpes.get(k).y;
			}
			Polygon td = new Polygon(xd, yd, xd.length);//poligono convex hull doors
			Polygon tp = new Polygon(xp, yp, xp.length);//poligono convex hull penguins

			boolean  cont = contiene(td,xd, yd, pes);
			if(!cont)cont = contiene(tp, xp, yp, des);
			if(!cont)cont = intersectan(xd, yd, xp, yp);
			sb.append("Case "+ ++cas + ": It is "+((cont)?"not ":"") + "possible to separate the two groups of vendors.").append("\n");
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
class Point2{
	double x,y;
	public Point2(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double getX(){return x;}
	public double getY(){return y;}
	public String toString(){
		return this.x+" "+this.y;
	}
}
