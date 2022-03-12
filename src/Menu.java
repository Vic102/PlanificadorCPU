import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static ArrayList<Proceso> procesos;

	public static void main(String[] args) {
		short opcion;
		Scanner in = new Scanner(System.in);
		
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
				FIFO fifo = new FIFO(iniciarDatos());
				fifo.calcularTabla();
				fifo.mostrarTabla();
				System.out.println("Ha terminado.");
				break;
			case 2:
				System.out.println("Has elegido Shortest Job First");
				
				SJF sjf = new SJF(iniciarDatos());
				procesos = sjf.ordenarProcesos();
				for (int i = 0; i < procesos.size(); i++) {
					System.out.println(procesos.get(i).getNombre());
				}
				System.out.println("Ha terminado.");
				break;
			case 3:
				System.out.println("Has elegido Round Robi");
				//fun
				RoundRobin rr = new RoundRobin(iniciarDatos());
				rr.calcular();
				
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
}
