import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class RoundRobin {
	private int quantum;
	private Stack<Proceso> procesosPila;
	private ArrayList<Proceso> procesos;
	private ArrayList<Proceso> arrAux;

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
		String res = "";
		
		Scanner in = new Scanner (System.in);
		System.out.print("Quantum: ");
		quantum = in.nextInt();
				
		int[] arrDuraciones = new int[procesos.size()];

		for (int i = 0; i < procesos.size(); i++) {
			arrDuraciones[i] = procesos.get(i).getDuracion();
		}

		arrAux = new ArrayList<Proceso>();
//		String arr = "";
		procesosPila.add(procesos.get(0));
		procesosPila.get(0).setInicio(procesosPila.get(0).getLlegada());
		for (int i = 0; i < procesosPila.size(); i++) {
			duracion = procesosPila.get(i).getDuracion();

			// modificamos el tiempo que le queda al proceso
			if (duracion >= quantum) {
				procesosPila.get(i).setDuracion(duracion - quantum);
//				arr += String.valueOf(quantum);
			} else if (duracion < quantum && duracion > 0) {
				procesosPila.get(i).setDuracion(duracion - duracion);
//				arr += String.valueOf(duracion);
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
		tabla();
	}
	
	public void tabla() {
		for (Proceso proceso : arrAux) {
			System.out.println(proceso);
		}
	}
	
//	public void pintar () {
//		int duracion = arrAux.get(0).getFin();
//		
//		for (Proceso proceso : arrAux) {
//			if (duracion < proceso.getFin()) {
//				duracion = proceso.getFin();
//			}
//		}
//		
//		for (int i = 0; i < duracion + 1; i++) {
//			for (int j = 0; j < arrAux.size(); j++) {
//				String nombre = arrAux.get(j).getNombre();
//				String res = "";
//				//meter cada linea en un string
//				
//				for (Proceso proceso : arrAux) {
//					
//				}
//				
//				System.out.println(nombre + " | " + "" );
//			}
//		}
//	}
	
	public void mostrarGrafica() {
		
		int tiempoTotal = arrAux.get(0).getFin();
		
		for (Proceso proceso : arrAux) {
			if (tiempoTotal < proceso.getFin()) {
				tiempoTotal = proceso.getFin();
			}
		}
		
		for (int i = 0; i < arrAux.size(); i++) {
			int esperas = 0;
			boolean terminado = false;
			int contQuam = 0;
			System.out.println();
			for (int j = 0; j < tiempoTotal; j++) {
				if ((j >= arrAux.get(i).getLlegada()) && (esperas != arrAux.get(i).getE()) && arrAux.get(i).getLlegada()!= 0) {
					esperas++;
					System.out.print(" e ");
				} else if ((j >= arrAux.get(i).getLlegada()) && (j <= arrAux.get(i).getFin() -1)) {
					System.out.print(" x ");
					contQuam++;
					if (contQuam == quantum) {
						for (int k = 0; k < arrAux.get(i).getE(); k++) {
							contQuam=0;
							System.out.print(" e ");
							j++;
						}
					}
					if (arrAux.get(i).getDuracion() > 0) {
						terminado = false;
					} else {
						terminado = true;
					}
				} else {
					System.out.print(" - ");
				}
			}
		}
		System.out.println();
		System.out.println();
	}

}
