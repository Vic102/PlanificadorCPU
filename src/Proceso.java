
public class Proceso {
	private String nombre;
	private int llegada;
	private int duracion;

	public Proceso(String nombre, int llegada, int duracion) {
		this.nombre = nombre;
		this.llegada = llegada;
		this.duracion = duracion;
	}

	public Proceso() {
		this("", 0, 0);
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

}
