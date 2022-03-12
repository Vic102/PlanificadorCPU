import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class RoundRobin {
	private int quantum;
	Stack<Proceso> procesosPila;
	ArrayList<Proceso> procesos;

	public RoundRobin(ArrayList<Proceso> procesos) {
		this.procesos = new ArrayList<Proceso>();
		this.procesosPila = new Stack<Proceso>();
		// Se supone que los procesos se han metido con un orden de llegada razonable
		// (ordenado naturalmente) en el ArrayList
		for (Proceso proceso : procesos) {
			this.procesos.add(proceso);
		}
	}

	public RoundRobin() {
		this.procesos = new ArrayList<Proceso>();
		this.procesosPila = new Stack<Proceso>();
	}

	public void pintarRR() {
		int duracion = 0;
		for (Proceso proceso : procesos) {
			duracion += proceso.getDuracion();
		}

	}

	public void calcular() {
		int duracion = 0;
		int tiempo = 0;
		int cont = 0;
		String arr = "";
		procesosPila.add(procesos.get(0));
		for (int i = 0; i < procesosPila.size(); i++) {
			duracion = procesosPila.get(i).getDuracion();

			// modificamos el tiempo que le queda al proceso
			if (duracion >= 4) {
				procesosPila.get(i).setDuracion(duracion - 4);
				arr += String.valueOf(4);
			} else if (duracion < 4 && duracion > 0) {
				procesosPila.get(i).setDuracion(duracion - duracion);
				arr += String.valueOf(duracion);
			}

			// para establecer cuanto tiempo ha llevado este proceso
			if (duracion >= 4) {
				tiempo += 4;
			} else if (duracion < 4 && duracion > 0) {
				tiempo += duracion;
			}
			
			//crear un bucle o algo para mirar si hay procesos con tiempo d llegada < tiempo para añadirlos a la pila
			for (int j = (i+1); j < procesos.size(); j++) {
				if (procesos.get(j).getLlegada() <= tiempo) {
					procesosPila.add(procesos.get(j));
					procesos.remove(j);
					cont++;
				}
			}
			
			//mirar si lo vuelvo a meter en la pila
			if (procesosPila.get(i).getDuracion() == 0) {
				procesosPila.remove(i);
			} else if (procesosPila.get(i).getDuracion() > 0) {
				Proceso aux = procesosPila.get(i);
				procesosPila.remove(i);
				procesosPila.add(aux);
			}
			i -= cont;
			if (i < -1) {
				i = -1;
			}
		}
		System.out.println(arr);
	}

}
