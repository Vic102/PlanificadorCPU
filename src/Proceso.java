
public class Proceso {
	private String nombre;
	private int llegada;
	private int duracion;
	private int inicio;
	private int fin;
	private int T;
	private int E;
	private double P;

	public Proceso(String nombre, int llegada, int duracion) {
		this.nombre = nombre;
		this.llegada = llegada;
		this.duracion = duracion;
		inicio = 0;
		fin = 0;
		T = 0;
		E = 0;
		P = 0;
	}

	public Proceso() {
		this("", 0, 0);
		inicio = 0;
		fin = 0;
		T = 0;
		E = 0;
		P = 0;
	}

	
	//creo q esto es completamente innecesario pero ya están puestos, siempre se pueden quitar y parece q he hecho más
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getLlegada() {
		return llegada;
	}

	public void setLlegada(int llegada) {
		this.llegada = llegada;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public int getT() {
		return T;
	}

	public void setT(int t) {
		T = t;
	}

	public int getE() {
		return E;
	}

	public void setE(int e) {
		E = e;
	}

	public double getP() {
		return P;
	}

	public void setP(double p) {
		P = p;
	}

	@Override
	public String toString() {
		return "Proceso [nombre=" + nombre + ", llegada=" + llegada + ", duracion=" + duracion + ", inicio=" + inicio
				+ ", fin=" + fin + ", T=" + T + ", E=" + E + ", P=" + P + "]";
	}

}
