import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private ArrayList<Proceso> procesos;

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
				//fun
				System.out.println("Ha terminado.");
				break;
			case 2:
				System.out.println("Has elegido Shortest Job First");
				//fun
				System.out.println("Ha terminado.");
				break;
			case 3:
				System.out.println("Has elegido Round Robi");
				//fun
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

}
