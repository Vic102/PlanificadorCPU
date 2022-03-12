import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	public void calcular() {
		int duracion = 0;
		int tiempo = 0;
		int cont = 0;
		//en este caso, pero hay que pedirlo por consola o algo.
		quantum = 4;
		int[] arrDuraciones = new int[procesos.size()];

		for (int i = 0; i < procesos.size(); i++) {
			arrDuraciones[i] = procesos.get(i).getDuracion();
		}

		ArrayList<Proceso> arrAux = new ArrayList<Proceso>();
		String arr = "";
		procesosPila.add(procesos.get(0));
		procesosPila.get(0).setInicio(procesosPila.get(0).getLlegada());
		for (int i = 0; i < procesosPila.size(); i++) {
			//Esto se supone que era para calcular el inicio, no me sale
//			if (procesosPila.contains(procesosPila.get(i))) {
//				procesosPila.get(i).setInicio(tiempo);				
//			}
			duracion = procesosPila.get(i).getDuracion();

			// modificamos el tiempo que le queda al proceso
			if (duracion >= quantum) {
				procesosPila.get(i).setDuracion(duracion - quantum);
				arr += String.valueOf(quantum);
			} else if (duracion < quantum && duracion > 0) {
				procesosPila.get(i).setDuracion(duracion - duracion);
				arr += String.valueOf(duracion);
			}

			// para establecer cuanto tiempo ha llevado este proceso
			if (duracion >= quantum) {
				tiempo += quantum;
				if (procesosPila.get(i).getInicio() == 0 && procesosPila.get(i).getLlegada() != 0) {
					procesosPila.get(i).setInicio(tiempo - quantum);					
				}
			} else if (duracion < quantum && duracion > 0) {
				tiempo += duracion;
				if (procesosPila.get(i).getInicio() == 0 && procesosPila.get(i).getLlegada() != 0) {
					procesosPila.get(i).setInicio(tiempo - duracion);					
				}
			}

			// crear un bucle o algo para mirar si hay procesos con tiempo d llegada < tiempo para añadirlos a la pila
			for (int j = (i + 1); j < procesos.size(); j++) {
				if (procesos.get(j).getLlegada() <= tiempo) {
					procesosPila.add(procesos.get(j));
					procesos.remove(j);
					cont++;
				}
			}

			// mirar si lo vuelvo a meter en la pila
			if (procesosPila.get(i).getDuracion() == 0) {
				procesosPila.get(i).setFin(tiempo);
				arrAux.add(procesosPila.get(i));
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
		
		//TODO QUITAR
		System.out.println(arr);
		
		//Ordenar los procesos alfabéticamente
		for (int x = 0; x < arrAux.size() - 1; x++) {
            for (int y = 0; y < arrAux.size() - x - 1; y++) {
                if (arrAux.get(y + 1).getNombre().compareTo(arrAux.get(y).getNombre()) < 0) {
                    Proceso aux = arrAux.get(y + 1);
                    arrAux.set(y + 1, arrAux.get(y));
                    arrAux.set(y, aux);
                }
            }
        }

		
		// esto es para devolver los tiempos de duracion a cada proceso
		for (int i = 0; i < arrAux.size(); i++) {
			arrAux.get(i).setDuracion(arrDuraciones[i]);
		}
		
		
		//Para calcular T, E y P de cada proceso
		for (int i = 0; i < arrAux.size(); i++) {
			Proceso proc = arrAux.get(i);
			proc.setT(proc.getFin() - proc.getLlegada());
			proc.setE(proc.getT() - proc.getDuracion());
			proc.setP((double)proc.getT() / proc.getDuracion());
		}
		System.out.println(arrAux);
	}

}
