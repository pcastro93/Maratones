import java.io.*;
import java.util.*;
public class flatland{
	public static double eps = 1e-16;
	public static int dimX = 0,dimY = 0;
	public static boolean vivos[];
	public static double tiempo = 0.0;
	public static int viv = 0;
	public static String ul = "";
	public static String nombres[];
	public static Tal fl[];
	public static TreeMap<Inter,Set<Integer>> PC;
	public static void main(String args[]) throws IOException{
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		int vec = Integer.parseInt(lector.readLine());
		for(int tt = 0;tt<vec;tt++){
			//System.out.println(tt+"--------");
			int f = Integer.parseInt(lector.readLine());
			String dim = lector.readLine();
			dimY = Integer.parseInt(dim.substring(dim.indexOf(" ")+1));
			dimX = Integer.parseInt(dim.substring(0,dim.indexOf(" ")));
			nombres = new String[f];
			vivos = new boolean[f];
			fl = new Tal[f];
			viv = f;
			ul = "";
			PC = new TreeMap<Inter,Set<Integer>>();
			for(int ff = 0;ff<f;ff++){
				String dat[] = lector.readLine().split(" ");
				nombres[ff] = dat[4];
				double datt[] = new double[4];
				for(int n =0;n<4;n++)
					datt[n] = Double.parseDouble(dat[n]);
				fl[ff] = new Tal(datt);
				//aca haz los puntos de interseccion con el mundo.verticales bla bla bla
				Set<Integer> so = new HashSet<Integer>();
				so.add(ff);
				if(fl[ff].verti){
					if(fl[ff].dire==1){
						PC.put(new Inter(Math.abs(fl[ff].y-dimY),dimY,fl[ff].x),so);
					}else{
						PC.put(new Inter(fl[ff].y,0,fl[ff].x),so);
					}
					//System.out.println("meti a "+ff+" "+fl[ff].dire);
				}else{
					if(fl[ff].dire==1){
						double p = fl[ff].m*dimX+fl[ff].b;
						//System.out.println("::::"+ff+"  "+p);
						if(p>0 && p<dimY){
							PC.put(new Inter(Math.hypot(fl[ff].x-dimX,fl[ff].y-p),dimX,p),so);
						}else{
							if(p>=dimY){
								double pp= (dimY-fl[ff].b)/fl[ff].m;
								PC.put(new Inter(Math.hypot(fl[ff].x-pp,fl[ff].y-dimY),pp,dimY),so);
							}else{
								double pp= (0-fl[ff].b)/fl[ff].m;
								PC.put(new Inter(Math.hypot(fl[ff].x-pp,fl[ff].y),pp,0),so);
								//System.out.println("Ahi puse "+ff+" "+Math.hypot(fl[ff].x-pp,fl[ff].y)+"-->"+pp+","+0+"   "+so);
							}
						}
					}else if(fl[ff].dire==-1){
						double p = fl[ff].b;
						//System.out.println("<> "+p+" "+dimY);
						if(p>0 && p<dimY){
							//System.out.println("ahhh");
							PC.put(new Inter(Math.hypot(fl[ff].x,fl[ff].y-p),0,p),so);
						}else{
							if(p>=dimY){
								double pp= (dimY-fl[ff].b)/fl[ff].m;
								PC.put(new Inter(Math.hypot(fl[ff].x-pp,fl[ff].y-dimY),pp,dimY),so);
							}else{
								double pp= (0-fl[ff].b)/fl[ff].m;
								PC.put(new Inter(Math.hypot(fl[ff].x-pp,fl[ff].y),pp,0),so);
								//System.out.println("Ahi puse "+ff+" "+Math.hypot(fl[ff].x-pp,fl[ff].y)+"-->"+pp+","+0+"   "+so);
							}
						}
					}
				}

				for(int n=0;n<ff;n++){
					if(fl[n].m==0 && fl[ff].m ==0 && fl[ff].b==fl[n].b && fl[ff].dire!=fl[n].dire && !fl[n].verti && !fl[ff].verti){
						if(Math.signum(fl[ff].x-fl[n].x)!=fl[ff].dire){
							//System.out.println("JOder");
							Inter ver = new Inter(Math.abs((fl[n].x-fl[ff].x)/2.0),(fl[n].x+fl[ff].x)/2.0,fl[n].b);
							Set<Integer> mien = PC.get(ver);
							if(mien==null)
								mien = new HashSet<Integer>();
							mien.add(n);
							mien.add(ff);
							PC.put(ver,mien);
							continue;
						}
					}
					if(fl[ff].m==fl[n].m && !fl[n].verti && !fl[ff].verti && fl[ff].b==fl[n].b){
						if(fl[ff].dire!=fl[n].dire && Math.signum(fl[ff].x-fl[n].x)!=fl[ff].dire){
							Inter ver = new Inter(Math.hypot((fl[n].x-fl[ff].x)/2.0,(fl[n].y-fl[ff].y)/2.0),(fl[n].x+fl[ff].x)/2.0,(fl[ff].y+fl[n].y)/2.0);
							Set<Integer> mien = PC.get(ver);
							if(mien==null)
								mien = new HashSet<Integer>();
							mien.add(n);
							mien.add(ff);
							PC.put(ver,mien);
							continue;
						}
					}
					if(fl[ff].m==fl[n].m && !fl[n].verti && !fl[ff].verti)continue;
					if(fl[ff].verti && fl[n].verti){
						//System.out.println(n+" "+fl[n].x+" "+fl[n].y+" "+fl[n].dire);
						//System.out.println(ff+" "+fl[ff].x+" "+fl[ff].y+" "+fl[ff].dire);
						if(fl[ff].x==fl[n].x && fl[ff].dire!=fl[n].dire && Math.signum(fl[ff].y-fl[n].y)!=fl[ff].dire){
							//System.out.println("JOder"); 
							Inter ver = new Inter(Math.abs((fl[n].y-fl[ff].y)/2.0),fl[n].x,(fl[n].y+fl[ff].y)/2.0);
							Set<Integer> mien = PC.get(ver);
							if(mien==null)
								mien = new HashSet<Integer>();
							mien.add(n);
							mien.add(ff);
							PC.put(ver,mien);
							continue;
						}
						continue;
					}
					if(fl[ff].verti){
						double  y = fl[n].m*fl[ff].x+fl[n].b;
						//System.out.println(ff+" es vertical "+n+" "+y);
						if(Math.signum(y-fl[ff].y)==fl[ff].dire){
							double val = Math.pow(10,9);
							double dist1 = Math.floor( Math.hypot(y-fl[n].y,fl[ff].x-fl[n].x)*val )/val;
							double dist2 = Math.floor( Math.abs(y-fl[ff].y)*val )/val;
							//System.out.println(y+" "+dist1+" "+dist2);
							if(dist1==dist2){
								Inter ver = new Inter(dist1,fl[ff].x,y);
								Set<Integer> mien = PC.get(ver);
								if(mien==null)
									mien = new HashSet<Integer>();
								mien.add(n);
								mien.add(ff);
								PC.put(ver,mien);
							}
						}
					}else if(fl[n].verti){
						double  y = fl[ff].m*fl[n].x+fl[ff].b;
						//System.out.println(n+" es vertical "+ff+" "+y);
						if(Math.signum(y-fl[n].y)==fl[n].dire){
							double val = Math.pow(10,9);
							double dist1 = Math.floor( Math.hypot(y-fl[ff].y,fl[n].x-fl[ff].x)*val )/val;
							double dist2 = Math.floor( Math.abs(y-fl[n].y)*val )/val;
							//System.out.println(y+" "+dist1+" "+dist2);
							if(dist1==dist2){
								Inter ver = new Inter(dist1,fl[n].x,y);
								Set<Integer> mien = PC.get(ver);
								if(mien==null)
									mien = new HashSet<Integer>();
								mien.add(n);
								mien.add(ff);
								PC.put(ver,mien);
							}
						}
					}
					double xx = (fl[n].b-fl[ff].b)/(fl[ff].m-fl[n].m);
					double yy = fl[n].m*xx+fl[n].b;
					if(xx<=0 || xx>=dimX || yy<=0 || yy>=dimY)continue;
					double val = Math.pow(10,9);
					double dpc = Math.floor( Math.hypot(fl[n].x-xx,fl[n].y-yy)*val )/val;
					double dpc1 = Math.floor( Math.hypot(fl[ff].x-xx,fl[ff].y-yy)*val )/val;
					if(fl[n].dire!=Math.signum(xx-fl[n].x))continue;
					if(fl[ff].dire!=Math.signum(xx-fl[ff].x))continue;
					//if(n==10 && ff==34)
					//	System.out.println(dpc+" "+dpc1);
					if(dpc!=dpc1)continue;
					//System.out.println("Interseccion entre "+ff+" y "+n);
					Inter vv = new Inter(dpc,xx,yy);
					Set<Integer> inv = PC.get(vv);
					if(inv==null){
						vv.ang  = Math.acos(-1*(((Math.pow(fl[n].x-fl[ff].x,2)+Math.pow(fl[ff].y-fl[ff].y,2))-2.0*dpc*dpc)/(2.0*dpc*dpc)));
						Set<Integer> tmp = new HashSet<Integer>();
						tmp.add(ff);tmp.add(n);
						PC.put(vv,tmp);
					}else{
						inv.add(ff);
						inv.add(n);
						PC.put(vv,inv);
					}
				}
			}
			double tiempo = 0.0;
			while(viv>0 && !PC.isEmpty()){
				Inter tm = PC.firstKey();
				Set<Integer> jo = PC.get(tm);
				//System.out.println(jo+" "+tm.t+" "+tm.x+","+tm.y);
				String har = "";
				int ru =0;
				Iterator rej = jo.iterator();
				Vector<Integer> zombies = new Vector<Integer>();
				while(rej.hasNext()){
					int uuu = (Integer)(rej.next());
					//System.out.println(jo+" "+uuu);
					if(vivos[uuu]){
						ru++;
						zombies.add(uuu);
					}
				}
				if(jo.size()-ru<2 && ru>0){
					PC.remove(tm);
					continue;
				}
				for(int rt = 0;rt<zombies.size();rt++)
					jo.remove(zombies.get(rt));

				boolean entrada = false;
				int entradaVar = -1;
				//System.out.println(jo+" "+tm.t+" "+tm.x+","+tm.y);
				//4 300 481 105 N  300 481 283 q
				for(int n =0;n<fl.length;n++){
					if(vivos[n])continue;
					fl[n].x = nuevoX(fl[n].x,fl[n].y,fl[n].m,fl[n].b,fl[n].dire,tm.t-tiempo);
					fl[n].y = fl[n].m*fl[n].x+fl[n].b;
					if(jo.contains(n)){
						if(jo.size()==2){
							if(!entrada){
								entrada = true;
								entradaVar = n;
							}else{
								String fm = nombres[entradaVar];
								nombres[entradaVar]=nombres[n];
								nombres[n]=fm;
							}
							/*
							   System.out.println("Ang "+tm.ang+" pen "+Math.atan(fl[n].m));

							   double nuevoan = Math.tan(Math.PI/2.0+tm.ang-Math.atan(fl[n].m));
							   System.out.println("Aho "+(Math.PI/2.0+tm.ang-Math.atan(fl[n].m)));
							   double nuevoB = tm.y-nuevoan*tm.x;
							   System.out.println("Antes "+fl[n].m+"*x+"+fl[n].b+"  ahora "+nuevoan+"*x+"+nuevoB);
							   fl[n].m= nuevoan;
							   fl[n].b = nuevoB;
							   fl[n].dir = 
							   */
							//quitar en PC donde haya n, buscar nuevas intersecciones
						}else{
							if(jo.size()==1){
								vivos[n]=true;
								viv--;
								if(Math.abs(tm.t-tiempo)<=1e-6)
									ul = (nombres[n].compareTo(ul)>0)?nombres[n]:ul;
								else
									ul = nombres[n];

								//System.out.println("Muere solo "+ul);
							}else{
								//System.out.println("Mas de 3");
								vivos[n]=true;
								viv--;
								if(har.length()==0)
									har = nombres[n];
								else
									har = (har.compareTo(nombres[n])<0)?nombres[n]:har;
								//System.out.println("Muere es "+nombres[n]);
							}
						}
						//System.out.println("Nueva posicion de "+n+" "+fl[n].x+" "+fl[n].y);
					}
					if(har.length()>0)ul = har;
				}
				PC.remove(tm);
				tiempo = tm.t;
			}
			System.out.println(ul);
		}
	}
	public static double nuevoX(double x,double y,double m,double b,int d,double c){
		return (x+m*y-m*b+d*Math.sqrt((m*m+1)*c*c+2.0*b*(y-b)))/(m*m+1);
	}
}
class Inter implements Comparable{
	double eps = 1e-8;
	double t = 0.0;
	double x = 0.0;
	double y = 0.0;
	double ang = 0.0;
	boolean solo = false;
	public Inter(double t,double x,double y){
		double val = Math.pow(10,9);
		double tmp = t*val;
		tmp = Math.round(tmp);
		this.t = tmp/val;
		tmp = Math.round(x*val);
		this.x = tmp/val;
		tmp = Math.round(y*val);
		this.y = tmp/val;
	}
	public int compareTo(Object a){
		Inter aa = (Inter)a;
		//if(t==aa.t){
		if(Math.abs(t-aa.t)<=eps){
			if(x>aa.x)return 1;
			else if(x<aa.x)return -1;
			else{
				if(y>aa.y)return 1;
				else if(y<aa.y)return -1;
				else return 0;
			}
		}
		return (int)((t-aa.t)/Math.abs(t-aa.t));
		/*if(t-aa.t>0)return 1;
		  if(aa.t-t>0)return -1;
		  return 0;*/
	}
	public boolean equals(Inter a){
		return Math.abs(a.t-t)<=eps && x==a.x && y==a.y;
	}
	public String toString(){
		return t+"-("+x+","+y+")";
	}
}
class Tal{
	public double eps = 1e-16;
	public boolean verti = false;
	public int dire = 0;
	public double m = 0.0;
	public double b = 0.0;
	public double x = 0.0;
	public double y = 0.0;
	public Tal(double a[]){
		this.x = a[0];
		this.y = a[1];
		if(a[2]==a[0]){
			verti=true;
			if(a[3]>a[1])dire=1;
			else dire=-1;
			return;
		}
		double val = Math.pow(10,9);
		double tmp = (a[3]-a[1])/(a[2]-a[0]);
		double tmp2 = Math.round(tmp*val);
		this.m = tmp2/val;
		//this.m = (a[3]-a[1])/(a[2]-a[0]);
		//tmp = a[1]-m*a[0];
		//tmp2 = Math.round(tmp*val);
		//this.b = tmp2/val;
		this.b = a[1]-m*a[0];
		dire = a[2]>a[0]?1:((a[2]==a[0])?0:-1);
		//System.out.println(this.m+"*x+"+this.b+" "+dire);
	}
	public double dist(Tal a){
		double val = Math.pow(10,9);
		return Math.round(val*Math.hypot(x-a.x,y-a.y))/val;
	}
}
