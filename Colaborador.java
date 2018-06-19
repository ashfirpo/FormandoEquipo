package formandoEquipo;

public class Colaborador {
	
	private int id;
	private String respuestas;
	
	public Colaborador(int id, String respuestas)
	{
		this.id=id;
		this.respuestas = respuestas;
	}
	
	public String getRespuestas()
	{
		return this.respuestas;
	}
	
	public int respuestasIguales(Colaborador c2)
	{
		int cantidad=0;
		int min = Math.min(this.respuestas.length(), c2.respuestas.length()); //Se supone que son de la misma longitud
		
		for(int i=0;i<min;i++)
		{
			if(this.respuestas.charAt(i) == c2.respuestas.charAt(i))
				cantidad++;
			else
				break; //Por si no me importa las que sigan después
		}
		return cantidad;
	}
	
	@Override
	public boolean equals(Object c2) //Sobreescribo para que sepa cuándo considero que son iguales
	{
		Colaborador param = (Colaborador)c2;
		return this.id == param.id;
	}

}
