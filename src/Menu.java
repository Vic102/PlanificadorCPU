import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static ArrayList<Proceso> procesos;
	static Scanner in = new Scanner(System.in);
	static Scanner text = new Scanner(System.in);
	
	public static void main(String[] args) {
		short opcion;


		do {
			System.out.println("=============== Procesos de la CPU ===============");
			System.out.println("1. First In First Out");
			System.out.println("2. Shortest Job First");
			System.out.println("3. Round Robin");
			System.out.println("0. Salir");
			opcion = in.nextShort();
			switch (opcion) {
			case 1:
				System.out.println("Has elegido First In First Out");
				FIFO fifo = new FIFO(pedirDatos());
				fifo.calcularTabla();
				fifo.mostrarTabla();
				fifo.mostrarGrafica();
				System.out.println("Ha terminado.");
				break;
			case 2:
				System.out.println("Has elegido Shortest Job First");
				SJF sjf = new SJF(pedirDatos());
				sjf.ordenarProcesos();
				sjf.mostrarTabla();
				sjf.mostrarGrafica();
				System.out.println("Ha terminado.");
				break;
			case 3:
				System.out.println("Has elegido Round Robi");
				RoundRobin rr = new RoundRobin(iniciarDatos());
				rr.calcular();
				rr.mostrarGrafica();

				System.out.println("Ha terminado.");
				break;
			case 0:
				System.out.println("Has elegido Salir");
				System.out.println("Hasta que le vuelvas a dar al run.");
				break;

			default:
				System.out.println("Eso no es una opción. Elige una válida.");
				break;
			}
		} while (opcion != 0);
	}

	public static ArrayList<Proceso> iniciarDatos() {
		ArrayList<Proceso> proc = new ArrayList<Proceso>();
		Proceso proceso1 = new Proceso("A", 0, 5);
		Proceso proceso2 = new Proceso("B", 2, 6);
		Proceso proceso3 = new Proceso("C", 8, 4);
		Proceso proceso4 = new Proceso("D", 10, 5);
		Proceso proceso5 = new Proceso("E", 12, 2);
		proc.add(proceso1);
		proc.add(proceso2);
		proc.add(proceso3);
		proc.add(proceso4);
		proc.add(proceso5);
		return proc;
	}
	
	public static ArrayList<Proceso> pedirDatos() {
		ArrayList<Proceso> proc = new ArrayList<Proceso>();
		int numProcs;
		System.out.print("Introduzca el numero de procesos que quiere introducir: ");
		numProcs = in.nextInt();
		for (int i = 0; i < numProcs; i++) {
			Proceso proceso = new Proceso();
			System.out.print("Introduzca el nombre del proceso " + (i+1) + ": ");
			proceso.setNombre(text.nextLine());
			System.out.print("Introduzca ahora el momento en el que llega: ");
			proceso.setLlegada(in.nextInt());
			System.out.print("Introduzca finalmente, la duracion del " + (i+1) +"º proceso: ");
			proceso.setDuracion(in.nextInt());
			proc.add(proceso);
			System.out.println();
		}
		return proc;
	}
}
