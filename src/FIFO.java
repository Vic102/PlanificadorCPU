import java.util.ArrayList;

public class FIFO {
	private ArrayList<Proceso> procesos;
	private int tiempoTotal;
	
	public FIFO(ArrayList<Proceso> procesos) {
		this.procesos = procesos;
		tiempoTotal = 0;
	}
	
	public FIFO() {
		procesos = new ArrayList<Proceso>();
		tiempoTotal = 0;
	}
	
	public void addProceso(Proceso proceso) {
		procesos.add(proceso);
	}
	
	public void calcularInicio() {
		procesos.get(0).setInicio(procesos.get(0).getLlegada());
		for (int i = 1; i < procesos.size(); i++) {
			procesos.get(i).setInicio(procesos.get(i-1).getDuracion() + procesos.get(i-1).getInicio());
		}
	}
	
	public void calcularFin() {
		for (int i = 0; i < procesos.size(); i++) {
			procesos.get(i).setFin(procesos.get(i).getDuracion() + procesos.get(i).getInicio());
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
		tiempoTotal = procesos.get(procesos.size()-1).getFin();
	}
	
	public void mostrarTabla() {
		for (int i = 0; i < procesos.size(); i++) {
			System.out.println(procesos.get(i).toString());
		}
	}
	
	public void mostrarGrafica() {
		for (int i = 0; i < procesos.size(); i++) {
			int esperas = 0;
			System.out.println();
			System.out.print(procesos.get(i).getNombre() + " ");
			for (int j = 0; j < tiempoTotal; j++) {
				if ((j >= procesos.get(i).getLlegada()) && (esperas != procesos.get(i).getE())) {
					esperas++;
					System.out.print(" e ");
				} else if ((j >= procesos.get(i).getLlegada()) && (j <= procesos.get(i).getFin() -1)) {
					System.out.print(" x ");
				} else {
					System.out.print(" - ");
				}
			}
		}
		System.out.println();
		System.out.println();
	}
}
