import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class RoundRobin {
	private int quantum;
	Stack<Proceso> procesosPila;
	ArrayList<Proceso> procesos;

	public RoundRobin(ArrayList<Proceso> procesos) {
		this.procesos = new ArrayList<Proceso>();
		// Se supone que los procesos se han metido con un orden de llegada razonable
		// (ordenado naturalmente) en el ArrayList
		for (Proceso proceso : procesos) {
			this.procesos.add(proceso);
		}
	}

	public RoundRobin() {
		procesos = new ArrayList<Proceso>();
	}

	public void pintarRR() {
		int duracion = 0;
		for (Proceso proceso : procesos) {
			duracion += proceso.getDuracion();
		}

		for (int i = 0; i < duracion; i++) {

		}
	}

	public void calcular() {
		int duracion = 0;
		int tiempo = 0;
		int cont = 0;
		for (int i = 0; i < procesos.size(); i++) {
			duracion = procesos.get(i).getDuracion();
			if ((duracion - 4) >= 0) {
				procesos.get(i).setDuracion(duracion - 4);
			}
//			tiempo += procesos.get(i).getCUM;
			if (procesos.get(i).getDuracion() == 0) {
				procesos.remove(i);
			}
			for (int j = i; j < procesos.size(); j++) {
				if (procesos.get(j).getLlegada() <= tiempo) {
					
				}
			}

		}
	}

}
