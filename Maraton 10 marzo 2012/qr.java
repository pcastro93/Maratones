import java.util.*;
import java.io.*;
public class qr{
	public static String charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";
	public static void main(String args[]) throws IOException{
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String tmp = "";
int vece =Integer.parseInt(lector.readLine());
for(int mmm = 0;mmm<vece;mmm++){//		while((tmp = lector.readLine())!=null){
String mpp = lector.readLine();
int tun = 0;
tmp = mpp.substring(mpp.indexOf(" ")+1);
			String bina = toDecc(tmp);
			String res = "";
			int vec =0;
			while(bina.length()>=4 && !bina.substring(0,4).equals("0000")){
				String tal = bina.substring(0,4);
				switch(tal.indexOf("1")){
					case 0://kanji
//tun++;
						vec = toDec(bina.substring(4,12),1);
						for(int n = 0,c=12;n<vec;n++,c+=13,tun++)
							res+="#"+toBin(toDec(bina.substring(c,c+13),1),4,16);
						bina = bina.substring(12+13*vec,bina.length());
						break;
					case 1://8 bit byte
//tun++;
						vec = toDec(bina.substring(4,12),1);
						for(int n = 0,c=12;n<vec;n++,c+=8,tun++){
							if(toDec(bina.substring(c,c+8),1)<0x7e && toDec(bina.substring(c,c+8),1)>=0x20){
								res+=(char)toDec(bina.substring(c,c+8),1);
							}else
								res+="\\"+toBin(toDec(bina.substring(c,c+8),1),2,16);
						}
						bina = bina.substring(12+8*vec,bina.length());
						break;
					case 2:// alphanum
						vec = toDec(bina.substring(4,13),1);
						for(int n = 0,c=13;n<(vec>>1);n++,c+=11,tun+=2){
							int tmpp = toDec(bina.substring(c,c+11),1);
							res+=charset.charAt(tmpp/45)+""+charset.charAt(tmpp%45);
						}
						if((vec&1)==1){
							res+=charset.charAt(toDec(bina.substring(13+11*(vec>>1),13+11*(vec>>1)+6),1));
tun++;
}
						bina = bina.substring(13+(vec>>1)*11+(vec&1)*6,bina.length());
						break;
					case 3://num
						vec = toDec(bina.substring(4,14),1);
						int vect = vec/3;
						int vecc=vec%3;
						for(int n = 0,c=14;n<vect;n++,c+=10){
int le= res.length();
String jod = ""+toDec(bina.substring(c,c+10),1);
if(jod.length()<3)jod = "0"+jod;
							res+=jod;
tun+=res.length()-le;
//System.out.println(res);
}
int le = res.length();
						res+=toDec(bina.substring(14+vect*10,14+vect*10+((vecc==1)?4:((vecc==2)?7:0))),1);
tun+=res.length()-le;
						bina = bina.substring(14+vect*10+((vecc==1)?4:((vecc==2)?7:0)),bina.length());
						break;
					case -1:
						System.out.println("joder!");
						break;
				}
			}
			System.out.println(mpp.substring(0,mpp.indexOf(" ")+1)+tun+" "+res);
		}
	}
	public static String toDecc(String a){
		String res = "";
		for(int n= 0,c=0;n+2<=a.length();n=n+2,c++)
			res+=toBin(toDec(a.substring(n,n+2),4),8);
		return res;
	}
	public static String toBin(int a,int b){
		return toBin(a,b,2);
	}
	public static String toBin(int a,int b,int c){
		String res = "";
		do{
			res = charset.charAt(a&(c-1))+res;
		}while((a>>=((c==2)?1:4))>0);
		while(res.length()<b)res="0"+res;
		return res;
	}
	public static int toDec(String a,int b){
		int res = 0;
		for(int n = a.length()-1,c=0;n>-1;n--,c++)
			res+=charset.indexOf(a.charAt(n))*(1<<(b*c));
		return res;
	}
}
