import java.util.Scanner;

public class SJF_ayuda {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter no of process:");
		int n = sc.nextInt();
		int pid[] = new int[n];
		int llegada[] = new int[n]; // at means arrival time
		int duracion[] = new int[n]; // bt means burst time
		int fin[] = new int[n]; // ct means complete time
		int T[] = new int[n]; // ta means turn around time
		int wt[] = new int[n]; // wt means waiting time
		int f[] = new int[n]; // f means it is flag it checks process is completed or not
		int st = 0, tot = 0; //st es el tiempo actual, tot son la cantidad de tiempo completados
		float avgwt = 0, avgta = 0;

		for (int i = 0; i < n; i++) {
			System.out.println("enter process " + (i + 1) + " arrival time:");
			llegada[i] = sc.nextInt();
			System.out.println("enter process " + (i + 1) + " brust time:");
			duracion[i] = sc.nextInt();
			pid[i] = i + 1;
			f[i] = 0;
		}
		while (true) {
			int c = n, min = 999;
			if (tot == n) // total no of process = completed process loop will be terminated
				break;
			for (int i = 0; i < n; i++) {
				/*
				 * If i'th process arrival time <= system time and its flag=0 and burst<min That
				 * process will be executed first
				 */
				if ((llegada[i] <= st) && (f[i] == 0) && (duracion[i] < min)) {
					min = duracion[i];
					c = i;
				}
			}
			/*
			 * If c==n means c value can not updated because no process arrival time< system
			 * time so we increase the system time
			 */
			if (c == n)
				st++;
			else {
				fin[c] = st + duracion[c];
				st += duracion[c];
				T[c] = fin[c] - llegada[c];
				wt[c] = T[c] - duracion[c];
				f[c] = 1;
				tot++;
			}
		}
		System.out.println("\npid  arrival brust  complete turn waiting");
		for (int i = 0; i < n; i++) {
			avgwt += wt[i];
			avgta += T[i];
			System.out.println(pid[i] + "\t" + llegada[i] + "\t" + duracion[i] + "\t" + fin[i] + "\t" + T[i] + "\t" + wt[i]);
		}
		System.out.println("\naverage tat is " + (float) (avgta / n));
		System.out.println("average wt is " + (float) (avgwt / n));
		sc.close();
	}
}