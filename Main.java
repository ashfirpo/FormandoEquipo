package formandoEquipo;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Equipo e = new Equipo("equipo_sinAfinidad.in");
		e.resolver();
		e.mostrarResultado();
	}

}
