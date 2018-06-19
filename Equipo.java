package formandoEquipo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Equipo {
	
	private int cantPreguntas=0;
	private int cantColaboradores=0;
	private int maximaAfinidad=0;
	private List<Colaborador> colaboradores;
	private String respuestas = "";
	
	public Equipo(String path) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File(path));
		
		this.cantPreguntas=sc.nextInt(); //Me importa para el while de abajo pero ¯\_(ü)_/¯ 
		this.cantColaboradores = sc.nextInt();
		this.colaboradores = new ArrayList<>();
		sc.nextLine();
		int id=1;
		while(sc.hasNextLine())
		{
			String linea = sc.nextLine();
			Colaborador c = new Colaborador(id,linea);
			this.colaboradores.add(c);
			id++;
		}
		sc.close();		
	}
	
	public void resolver()
	{
		//Esta ht podría ser hasta de String e Integer, pero bueno, hay que usar POO, no?
		Hashtable<String, List<Colaborador>> equipo = new Hashtable<>(); 
		for(int i=0;i<this.cantColaboradores;i++)
		{
			Colaborador c1 = this.colaboradores.get(i);
			for(int j=i+1;j<this.cantColaboradores;j++)
			{
				Colaborador c2 = this.colaboradores.get(j);
				int cantRes = c1.respuestasIguales(c2);
				if(cantRes!=0)
				{
					String respuesta = c2.getRespuestas().toString().substring(0, cantRes);
					List<Colaborador> c = new ArrayList<>();
					if(equipo.containsKey(respuesta))
					{
						if(!equipo.get(respuesta).contains(c2))
						{
							c.addAll(equipo.get(respuesta));
							c.add(c2);
							equipo.put(respuesta, c);
						}

						if(!equipo.get(respuesta).contains(c1))
						{
							c.addAll(equipo.get(respuesta));
							c.add(c1);
							equipo.put(respuesta, c);							
						}
					}
					else
					{
						c.add(c1);
						c.add(c2);
						equipo.put(respuesta, c);
					}
				}
			}
		}
		
		//Ya tengo todos los equipos, ahora tengo que ver cuál tiene mayor afinidad
		
		if(!equipo.isEmpty())
		{
			for(String key : equipo.keySet())
			{
				int cantColaboradores = (equipo.get(key)).size();
				int cantPreguntas = key.length();
				int afinidad= cantColaboradores * cantPreguntas * cantPreguntas;
				
				if(afinidad > this.maximaAfinidad)
				{
					this.maximaAfinidad = afinidad;
					this.respuestas = key;
				}
			}
		}
	}
	
	public void mostrarResultado()
	{
		System.out.println(this.maximaAfinidad);
		System.out.println(this.respuestas);		
	}

}
