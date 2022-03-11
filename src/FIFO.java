import java.util.ArrayList;

public class FIFO {
	private ArrayList<Proceso> procesos;

	public FIFO(ArrayList<Proceso> procesos) {
		this.procesos = procesos;
	}
	
	public FIFO() {
		procesos = new ArrayList<Proceso>();
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
	}
	
	public void mostrarTabla() {
		for (int i = 0; i < procesos.size(); i++) {
			System.out.println(procesos.get(i).toString());
		}
	}
	
}
