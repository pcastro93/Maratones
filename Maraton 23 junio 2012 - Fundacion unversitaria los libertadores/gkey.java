import java.io.*;
import java.util.*;
public class gkey {
	
	public static void inicializar(HashMap<String, String[]> notas,String lineas[]) {
		String arr[] = new String[11];
		arr[0] = "      ";
		arr[1] = "------";
		arr[2] = "      ";
		arr[3] = "------";
		arr[4] = "      ";
		arr[5] = "------";
		arr[6] = "      ";
		arr[7] = "--|\\--";
		arr[8] = "  |   ";
		arr[9] = "-x|---";
		arr[10] = "      ";
		notas.put("E", arr);
		
		String arr2[] = new String[11];
		arr2[0] = "      ";
		arr2[1] = "------";
		arr2[2] = "      ";
		arr2[3] = "------";
		arr2[4] = "      ";
		arr2[5] = "------";
		arr2[6] = "  |\\  ";
		arr2[7] = "--|---";
		arr2[8] = " x|   ";
		arr2[9] = "------";
		arr2[10] = "      ";
		notas.put("F", arr2);
		
		String arr3[] = new String[11];
		arr3[0] = "      ";
		arr3[1] = "------";
		arr3[2] = "      ";
		arr3[3] = "------";
		arr3[4] = "      ";
		arr3[5] = "--|\\--";
		arr3[6] = "  |   ";
		arr3[7] = "-x|---";
		arr3[8] = "      ";
		arr3[9] = "------";
		arr3[10] = "      ";
		notas.put("G", arr3);
		
		String arr4[] = new String[11];
		arr4[0] = "      ";
		arr4[1] = "------";
		arr4[2] = "      ";
		arr4[3] = "------";
		arr4[4] = "  |\\  ";
		arr4[5] = "--|---";
		arr4[6] = " x|   ";
		arr4[7] = "------";
		arr4[8] = "      ";
		arr4[9] = "------";
		arr4[10] = "      ";
		notas.put("A", arr4);
		
		String arr5[] = new String[11];
		arr5[0] = "      ";
		arr5[1] = "------";
		arr5[2] = "      ";
		arr5[3] = "--|\\--";
		arr5[4] = "  |   ";
		arr5[5] = "-x|---";
		arr5[6] = "      ";
		arr5[7] = "------";
		arr5[8] = "      ";
		arr5[9] = "------";
		arr5[10] = "      ";
		notas.put("B", arr5);
		
		String arr6[] = new String[11];
		arr6[0] = "      ";
		arr6[1] = "------";
		arr6[2] = "  |\\  ";
		arr6[3] = "--|---";
		arr6[4] = " x|   ";
		arr6[5] = "------";
		arr6[6] = "      ";
		arr6[7] = "------";
		arr6[8] = "      ";
		arr6[9] = "------";
		arr6[10] = "      ";
		notas.put("C", arr6);
		
		String arr7[] = new String[11];
		arr7[0] = "      ";
		arr7[1] = "--|\\--";
		arr7[2] = "  |   ";
		arr7[3] = "-x|---";
		arr7[4] = "      ";
		arr7[5] = "------";
		arr7[6] = "      ";
		arr7[7] = "------";
		arr7[8] = "      ";
		arr7[9] = "------";
		arr7[10] = "      ";
		notas.put("D", arr7);

		String arr9[] = new String[11];
		arr9[0] = "      ";
		arr9[1] = "------";
		arr9[2] = "      ";
		arr9[3] = "------";
		arr9[4] = "      ";
		arr9[5] = "------";
		arr9[6] = "  |\\  ";
		arr9[7] = "--|---";
		arr9[8] = "#x|   ";
		arr9[9] = "------";
		arr9[10] = "      ";
		notas.put("F#", arr9);
		
		String arr10[] = new String[11];
		arr10[0] = "      ";
		arr10[1] = "------";
		arr10[2] = "      ";
		arr10[3] = "------";
		arr10[4] = "      ";
		arr10[5] = "--|\\--";
		arr10[6] = "  |   ";
		arr10[7] = "#x|---";
		arr10[8] = "      ";
		arr10[9] = "------";
		arr10[10] = "      ";
		notas.put("G#", arr10);
		
		String arr11[] = new String[11];
		arr11[0] = "      ";
		arr11[1] = "------";
		arr11[2] = "      ";
		arr11[3] = "------";
		arr11[4] = "  |\\  ";
		arr11[5] = "--|---";
		arr11[6] = "#x|   ";
		arr11[7] = "------";
		arr11[8] = "      ";
		arr11[9] = "------";
		arr11[10] = "      ";
		notas.put("A#", arr11);

		String arr15[] = new String[11];
		arr15[0] = "      ";
		arr15[1] = "------";
		arr15[2] = "      ";
		arr15[3] = "--|\\--";
		arr15[4] = "  |   ";
		arr15[5] = "#x|---";
		arr15[6] = "      ";
		arr15[7] = "------";
		arr15[8] = "      ";
		arr15[9] = "------";
		arr15[10] = "      ";
		notas.put("B#", arr15);

		String arr12[] = new String[11];
		arr12[0] = "      ";
		arr12[1] = "------";
		arr12[2] = "  |\\  ";
		arr12[3] = "--|---";
		arr12[4] = "#x|   ";
		arr12[5] = "------";
		arr12[6] = "      ";
		arr12[7] = "------";
		arr12[8] = "      ";
		arr12[9] = "------";
		arr12[10] = "      ";
		notas.put("C#", arr12);
		String arr13[] = new String[11];
		arr13[0] = "      ";
		arr13[1] = "--|\\--";
		arr13[2] = "  |   ";
		arr13[3] = "#x|---";
		arr13[4] = "      ";
		arr13[5] = "------";
		arr13[6] = "      ";
		arr13[7] = "------";
		arr13[8] = "      ";
		arr13[9] = "------";
		arr13[10] = "      ";
		notas.put("D#", arr13);

		String arr8[] = new String[11];
		arr8[0] = "      ";
		arr8[1] = "------";
		arr8[2] = "      ";
		arr8[3] = "------";
		arr8[4] = "      ";
		arr8[5] = "------";
		arr8[6] = "      ";
		arr8[7] = "--|\\--";
		arr8[8] = "  |   ";
		arr8[9] = "#x|---";
		arr8[10] = "      ";
		notas.put("E#", arr8);
		
		lineas[0] = "      ";
		lineas[1] = "------";
		lineas[2] = "      ";
		lineas[3] = "------";
		lineas[4] = "      ";
		lineas[5] = "------";
		lineas[6] = "      ";
		lineas[7] = "------";
		lineas[8] = "      ";
		lineas[9] = "------";
		lineas[10] = "      ";
	}

	public static void main(String fjdkfjd[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		HashMap<String, String[]> notas = new HashMap<String, String[]>();
		String lineas[] = new String[11];
		inicializar(notas,lineas);
		boolean primera = true;
		StringBuilder sb = new StringBuilder("");
		while (!(input = br.readLine()).equals("-1")) {
			if(!primera)
				//System.out.println();
				sb.append("\n");
			primera = false;
			int cont = 0;
			int notastot = Integer.parseInt(input.split(" ")[0]);
			int fin = (int) Math.ceil(notastot / 16.0);
			int ccc = 0;
			String arr[] = input.split(" ");
			for (int i = 1; i < arr.length || ++cont < fin; i += 16,ccc++) {
				for (int k = 0; k < 11; k++) {
					if (i == 1)
						//System.out.print("|");
						sb.append("|");
					else
						//System.out.print(" ");
						sb.append(" ");
					//System.out.print("|");
					sb.append("|");
					int j = i;
					int veces = 16;
					int contador = 0;
					for (; j < arr.length && veces > 0; j++, veces--, contador++) {
						if (contador % 4 == 0 && contador > 0)
							//System.out.print("|");
							sb.append("|");
						//System.out.print(notas.get(arr[j])[k]);
						sb.append(notas.get(arr[j])[k]);
					}
					if (j >= arr.length) {
						while (veces-- > 0) {
							if (contador % 4 == 0 && contador > 0)
								//System.out.print("|");
								sb.append("|");
							//System.out.print(lineas[k]);
							sb.append(lineas[k]);
							contador++;
						}
					}
					if ((i - 1) % 16 == 0)
						//System.out.print("|");
						sb.append("|");
					--fin;
					if(ccc==notastot/16)
						//System.out.print("|");
						sb.append("|");
					//System.out.println();
					sb.append("\n");
				}
			}
			//System.out.println();
			//sb.append("\n");
		}
		System.out.println(sb);
	}
}
