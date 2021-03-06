import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RoundRobin {
	private int quantum;
	private Stack<Proceso> procesosCola;
	private ArrayList<Proceso> procesos;
	private ArrayList<Proceso> arrAux;

	public RoundRobin(ArrayList<Proceso> procesos) {
		this.procesos = new ArrayList<Proceso>();
		this.procesosCola = new Stack<Proceso>();
		// Se supone que los procesos se han metido con un orden de llegada razonable
		// (ordenado naturalmente) en el ArrayList
		for (Proceso proceso : procesos) {
			this.procesos.add(proceso);
		}
	}

	public RoundRobin() {
		this.procesos = new ArrayList<Proceso>();
		this.procesosCola = new Stack<Proceso>();
	}

	/**
	 * Pide el quantum por pantalla y lo guarda en el atributo
	 */
	public void setQuantum() {
		Scanner in = new Scanner(System.in);
		System.out.println("Quantum: ");
		System.err.println("(Con el 1 no funciona y la duración de los procesos no puede ser mayor que el doble del quantum)");
		System.out.print("> ");
		this.quantum = in.nextInt();
	}

	public void calcular() {
		int duracion = 0;
		int tiempo = 0;
		int cont = 0;
		String res = "";
		arrAux = new ArrayList<Proceso>();
		setQuantum();

		int[] arrDuraciones = new int[procesos.size()];

		for (int i = 0; i < procesos.size(); i++) {
			arrDuraciones[i] = procesos.get(i).getDuracion();
		}

		procesosCola.add(procesos.get(0));
		procesosCola.get(0).setInicio(procesosCola.get(0).getLlegada());
		for (int i = 0; i < procesosCola.size(); i++) {
			duracion = procesosCola.get(i).getDuracion();

			// modificamos el tiempo que le queda al proceso
			if (duracion >= quantum) {
				procesosCola.get(i).setDuracion(duracion - quantum);
			} else if (duracion < quantum && duracion > 0) {
				procesosCola.get(i).setDuracion(duracion - duracion);
			}

			// para establecer cuanto tiempo ha llevado este proceso
			if (duracion >= quantum) {
				tiempo += quantum;
				if (procesosCola.get(i).getInicio() == 0 && procesosCola.get(i).getLlegada() != 0) {
					procesosCola.get(i).setInicio(tiempo - quantum);
				}
			} else if (duracion < quantum && duracion > 0) {
				tiempo += duracion;
				if (procesosCola.get(i).getInicio() == 0 && procesosCola.get(i).getLlegada() != 0) {
					procesosCola.get(i).setInicio(tiempo - duracion);
				}
			}

			// crear un bucle o algo para mirar si hay procesos con tiempo d llegada <
			// tiempo para añadirlos a la pila
			for (int j = (i + 1); j < procesos.size(); j++) {
				if (procesos.get(j).getLlegada() <= tiempo) {
					procesosCola.add(procesos.get(j));
					procesos.remove(j);
					cont++;
				}
			}

			// mirar si lo vuelvo a meter en la pila
			if (procesosCola.get(i).getDuracion() == 0) {
				procesosCola.get(i).setFin(tiempo);
				arrAux.add(procesosCola.get(i));
				procesosCola.remove(i);
			} else if (procesosCola.get(i).getDuracion() > 0) {
				Proceso aux = procesosCola.get(i);
				procesosCola.remove(i);
				procesosCola.add(aux);
			}
			i -= cont;
			if (i < -1) {
				i = -1;
			}
		}

		// Ordenar los procesos alfabéticamente

		ordenarProcesos();

		// esto es para devolver los tiempos de duracion a cada proceso
		resetDurciones(arrDuraciones);

		// Para calcular T, E y P de cada proceso
		calcularT();
		calcularE();
		calcularP();
		tabla();
	}

	/**
	 * Ordena los procesos alfabéticamente
	 */
	public void ordenarProcesos() {
		for (int x = 0; x < arrAux.size() - 1; x++) {
			for (int y = 0; y < arrAux.size() - x - 1; y++) {
				if (arrAux.get(y + 1).getNombre().compareTo(arrAux.get(y).getNombre()) < 0) {
					Proceso aux = arrAux.get(y + 1);
					arrAux.set(y + 1, arrAux.get(y));
					arrAux.set(y, aux);
				}
			}
		}
	}

	/**
	 * Devolver los tiempos de duración a cada proceso, porque se han ido editando a
	 * lo largo del programa.
	 * 
	 * @param arr
	 */
	public void resetDurciones(int[] arr) {
		for (int i = 0; i < arrAux.size(); i++) {
			arrAux.get(i).setDuracion(arr[i]);
		}
	}

	/**
	 * Calcular el Tiempo total
	 */
	public void calcularT() {
		for (Proceso proceso : arrAux) {
			proceso.setT(proceso.getFin() - proceso.getLlegada());
		}
	}

	/**
	 * Calcularel tiempo de espera
	 */
	public void calcularE() {
		for (Proceso proceso : arrAux) {
			proceso.setE(proceso.getT() - proceso.getDuracion());
		}
	}

	/**
	 * Calcular la porción de penalización
	 */
	public void calcularP() {
		for (Proceso proceso : arrAux) {
			proceso.setP((double) proceso.getT() / proceso.getDuracion());
		}
	}

	/**
	 * Mostrar la tabla
	 */
	public void tabla() {
		for (Proceso proceso : arrAux) {
			System.out.println(proceso);
		}
	}

	public void mostrarGrafica() {

		int tiempoTotal = arrAux.get(0).getFin();

		for (Proceso proceso : arrAux) {
			if (tiempoTotal < proceso.getFin()) {
				tiempoTotal = proceso.getFin();
			}
		}

		for (int i = 0; i < arrAux.size(); i++) {
			char proceso = (char) (65 + i);
			int esperas = 0;
			boolean terminado = false;
			int contQuam = 0;
			System.out.println();
			System.out.print(proceso + ": ");
			for (int j = 0; j < tiempoTotal; j++) {
				if ((j >= arrAux.get(i).getLlegada()) && (esperas != arrAux.get(i).getE())
						&& arrAux.get(i).getLlegada() != 0 && arrAux.get(i).getInicio()>j) {
					esperas++;
					System.out.print(" e ");
				} else if (((j >= arrAux.get(i).getLlegada()) && (j <= arrAux.get(i).getFin() - 1)) || arrAux.get(i).getInicio()==j)  {
					System.out.print(" x ");
					arrAux.get(i).setDuracion(arrAux.get(i).getDuracion() - 1);
					contQuam++;
					if (contQuam >= quantum) {
						contQuam = 0;
						for (int k = 0; k < arrAux.get(i).getE(); k++) {
							if (esperas != arrAux.get(i).getE()) {
								esperas++;
								contQuam++;
								System.out.print(" e ");
								j++;
							}
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
