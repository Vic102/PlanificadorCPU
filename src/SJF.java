import java.util.ArrayList;

public class SJF {
	private ArrayList<Proceso> procesos;

	public SJF(ArrayList<Proceso> procesos) {
		this.procesos = procesos;
	}

	public SJF() {
		procesos = new ArrayList<Proceso>();
	}

	public void addProcesos(Proceso proceso) {
		procesos.add(proceso);
	}

	public ArrayList<Proceso> ordenarProcesos() {
		Proceso aux;
		ArrayList<Proceso> proc = new ArrayList<Proceso>();
		ArrayList<Proceso> enCola = new ArrayList<Proceso>();
		int sumatorioDuraciones = procesos.get(0).getDuracion();
		proc.add(procesos.get(0));
		for (int i = 1; i < procesos.size(); i++) {
			//posible error con el for
			for (int j = 1; i < procesos.size(); i++) {
				if ((procesos.get(j).getLlegada() <= sumatorioDuraciones)) {
					enCola.add(procesos.get(j));
				}
			}
			for (int x = 0; x < enCola.size() - 1; x++) {
				for (int y = 0; y < enCola.size() - x - 1; y++) {
					if (enCola.get(y + 1).getDuracion() < enCola.get(y).getDuracion()) {
						aux = enCola.get(y + 1);
						enCola.set(y + 1, enCola.get(y));
						enCola.set(y, aux);
					}
				}
			}

		}
		return proc;
	}

	public void calcularInicio() {
		procesos.get(0).setInicio(procesos.get(0).getLlegada());
		for (int i = 1; i < procesos.size(); i++) {

		}
	}

	public void calcularFin() {
		for (int i = 0; i < procesos.size(); i++) {

		}
	}

	public void calcularT() {
		for (int i = 0; i < procesos.size(); i++) {
			procesos.get(i).setT(procesos.get(i).getFin() - procesos.get(i).getLlegada());
		}
	}

	public void calcularE() {
		for (int i = 0; i < procesos.size(); i++) {
			procesos.get(i).setE(procesos.get(i).getT() - procesos.get(i).getDuracion());
		}
	}

	public void calcularP() {
		double division;
		for (int i = 0; i < procesos.size(); i++) {
			division = (double) procesos.get(i).getT() / procesos.get(i).getDuracion();
			procesos.get(i).setP(division);
		}
	}

	public void calcularTabla() {
		calcularInicio();
		calcularFin();
		calcularT();
		calcularE();
		calcularP();
	}

	public void mostrarTabla() {
		for (int i = 0; i < procesos.size(); i++) {
			System.out.println(procesos.get(i).toString());
		}
	}
}
