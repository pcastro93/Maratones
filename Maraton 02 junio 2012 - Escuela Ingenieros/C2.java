import java.awt.*;
import java.io.*;
import java.util.*;

public class C {
	public static double eps = 1e-9;
	public static int atoi(String n){
		return Integer.parseInt(n);
	}
	public static int diff(double l, double r){
		if(l - eps<r && r<l + eps)return 0;
		return (l<r)?-1:1;
	}
	public static int ccw(Point a, Point b, Point c){
		return diff(a.x*b.y + b.x*c.y +c.x*a.y - a.y*b.x - b.y * c.x - c.y * a.x,0);
	}
	public static double dist2(Point a, Point b){
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		return (dx*dx + dy*dy);
	}
	public static ArrayList<Point> ch(ArrayList<Point> puntos){
		if(puntos.size()<=3)
			return puntos;
		Collections.sort(puntos, new Comparar());
		ArrayList<Point> res = new ArrayList<Point>();
		res.add(puntos.get(0));
		res.add(puntos.get(1));
		for(int i=2;i<puntos.size();i++){
			while(res.size()>1 && ccw(res.get(res.size()-2), res.get(res.size()-1), puntos.get(i))>=0)
				res.remove(res.size()-1);
			res.add(puntos.get(i));
		}
		return res;
	}
	public static void main(String fjdkfjdk[])throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int cas = 0;
		while(!(input = br.readLine()).equals("0 0")){
			int d = atoi(input.split(" ")[0]);
			int p = atoi(input.split(" ")[1]);
			if(d==1 && p==1){
				System.out.println("Case "+ ++cas + ": It is "+((false)?"not ":"") + "possible to separate the two groups of vendors.");
				System.out.println();
				input = br.readLine();
				input = br.readLine();
				continue;
			}
			boolean dddd = false;
			if(d==1){dddd = true;}
			ArrayList<Point> des = new ArrayList<Point>();
			ArrayList<Point> pes = new ArrayList<Point>();
			for(int i=0;i<d;i++){
				int xi = atoi((input = br.readLine()).split(" ")[0]);
				int yi = atoi(input.split(" ")[1]);
				int xf = atoi(input.split(" ")[2]);
				int yf = atoi(input.split(" ")[3]);
				for(int k=xi;k<=xf;k++)
					for(int l=yi;l<=yf;l++)
						des.add(new Point(k,l));
			}
			for(int i=0;i<p;i++){
				int xi = atoi((input = br.readLine()).split(" ")[0]);
				int yi = atoi(input.split(" ")[1]);
				int xf = atoi(input.split(" ")[2]);
				int yf = atoi(input.split(" ")[3]);
				for(int k=xi;k<=xf;k++)
					for(int l=yi;l<=yf;l++){
						Point temp = new Point(k,l);
						pes.add(temp);
						}
			}
			ArrayList<Point> rdes = ch((dddd?pes:des));
			int x[] = new int[rdes.size()];
			int y[] = new int[rdes.size()];
			for(int k=0;k<x.length;k++){
				x[k] = rdes.get(k).x;
				y[k] = rdes.get(k).y;
			}
			Polygon t = new Polygon(x, y, x.length);
			ArrayList<Point> reco = (ArrayList<Point>)(dddd?des.clone():pes.clone());
			boolean dd= false;
			for(int k=0;k<reco.size();k++)
				if(t.contains(reco.get(k))){
					dd=true;
					break;
					}
			System.out.println("Case "+ ++cas + ": It is "+((dd)?"not ":"") + "possible to separate the two groups of vendors.");
			System.out.println();
		}
	}
}
class Comparar implements Comparator<Point>{

	@Override
	public int compare(Point o1, Point o2) {
		if(o2.x>o1.x)return -1;
		if(o2.x<o1.x)return 1;
		if(o2.y>o1.y)return -1;
		if(o2.y<o1.y)return -1;
		return 0;
	}
	
}
