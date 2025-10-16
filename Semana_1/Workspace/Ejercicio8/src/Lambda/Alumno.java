package Lambda;

public class Alumno {
	private int matricula;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private int edad;
	private String carrera;
	private String universidad;
	
	public Alumno() {
		
	}

	public Alumno(int matricula, String nombre, String aPaterno, String aMaterno, int edad, String carrera,
			String universidad) {
		super();
		this.matricula = matricula;
		this.nombre = nombre;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.edad = edad;
		this.carrera = carrera;
		this.universidad = universidad;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getaMaterno() {
		return aMaterno;
	}

	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	@Override
	public String toString() {
		return "Alumno [matricula=" + matricula + ", nombre=" + nombre + ", aPaterno=" + aPaterno + ", aMaterno="
				+ aMaterno + ", edad=" + edad + ", carrera=" + carrera + ", universidad=" + universidad + "]\n";
	}
	
	

}
