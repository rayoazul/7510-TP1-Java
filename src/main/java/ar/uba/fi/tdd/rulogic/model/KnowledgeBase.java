package main.java.ar.uba.fi.tdd.rulogic.model;
import java.util.ArrayList;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class KnowledgeBase {

	public boolean answer(String query) 
	{
		if(!parsear())
		{
			return false;
		}
		else
		{
			String nombre = query.substring(0,query.indexOf("("));
			Matcher matcheador = patronHecho.matcher(query);
			if(matcheador.matches())
			{
				if(existeHecho(query))
				{
					return true;
				}
				else
				{
					String reglaBuscada = encontrarRegla(nombre);
					if(reglaBuscada != null)
					{
						return determinarArgumentos(reglaBuscada,query);
					}
					else
					{
						System.out.println("La regla o el hecho no se encuentra en la base de datos");
						return false;
					}
				}
			}
			else
			{
				System.out.println("La entrada está mal formulada");
				return false;
			}
		}
		
		
	}
	private boolean determinarArgumentos(String regla, String consulta)
	{
		String separados = regla.substring(regla.indexOf("(") +1,regla.indexOf(")"));
		String[] argumentos = separados.split(",");
		
		String separadosEntrada = consulta.substring(consulta.indexOf("(") +1,consulta.indexOf(")"));
		String[] argumentosEntrada = separadosEntrada.split(",");
		
		return colocarArgumentos(argumentos,argumentosEntrada,regla);
	}
	
	private boolean colocarArgumentos(String[] argumentos, String[] argumentosentrada, String regla)
	{
		for(int i = 0; i < argumentos.length;i++)
		{
			String encontrar = argumentos[i];
			regla = regla.replace(encontrar, argumentosentrada[i]);
		}
		return obtenerHechos(regla);
	}
	
	private boolean obtenerHechos(String regla)
	{
		String cadenaHechos = regla.substring(regla.indexOf("-") + 2, regla.indexOf("."));
		String[] hechos = cadenaHechos.split(", ");
		return comprobarHechos(hechos);
	}
	
	private boolean comprobarHechos(String[] hechos)
	{
		boolean seCumple = true;
		for(int i = 0; i < hechos.length;i++)
		{
			seCumple = seCumple && existeHecho(hechos[i]);
		}
		return seCumple;
	}
		
	private String encontrarRegla(String nombre)
	{
		String elementoBuscado = null;
		for(int i = 0; i < contenido.size();i++)
		{
			if(contenido.get(i).startsWith(nombre) && contenido.get(i).contains(":-"))
			{
				elementoBuscado = contenido.get(i);
				return elementoBuscado;
			}
		}
		return elementoBuscado;
	}
	private boolean existeHecho(String consulta)
	{
		if(!consulta.contains("."))
		{
			return contenido.contains(consulta + ".");
		}
		else
		{
			return contenido.contains(consulta);
		}
	}
	
	private Pattern patronHecho = Pattern.compile("^[a-zA-Z]+[(](([a-z0-9],)*[a-z0-9]{1})+[)].$");
	
	public boolean parsear()
	{
		int cantHechos = 0;
		int cantReglas = 0;
		if(contenido.size() == 1)
		{
			System.out.println("Ha habido un error en la inicialización de la base");
		}
		boolean correcta = true;
		
		Matcher matcheadorHecho;
		for(int i = 0; i < contenido.size();i++)
		{
			matcheadorHecho = patronHecho.matcher(contenido.get(i));
			correcta = matcheadorHecho.matches() || chequearFormatoRegla(contenido.get(i));
			if (!correcta)
			{
				System.out.println("Ha habido un error en la inicialización de la base");
				return false;
			}
			//Si es una regla verifico que esten los hechos definidos en la base
			if(chequearFormatoRegla(contenido.get(i)))
			{
				String actual = contenido.get(i);
				String[] hechos = (actual.substring(actual.indexOf("-")+2,actual.indexOf("."))).split(", ");
				if(!comprobarHechosDeRegla(hechos,matcheadorHecho))
				{
					System.out.println("Ha habido un error: todos los hechos que conforman una regla no se encuentran definidos en la base.");
					return false;
				}
				cantReglas++;
			}
			else
			{
				cantHechos++;
			}
		}
		if (cantHechos == 0 || cantReglas == 0)
		{
			System.out.println("Ha habido un error: a la base le falta por lo menos un hecho o una regla");
			return false;
		}	
		 return true;
		
	}
	private boolean comprobarHechosDeRegla(String[] hechos, Matcher matcheador)
	{
		for(int j = 0; j < hechos.length; j++)
		{
			String nombreHecho = hechos[j].substring(0,hechos[j].indexOf("("));
			if(!existeHechoParseo(nombreHecho,matcheador))
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean existeHechoParseo(String nombreHecho,Matcher matcheador)
	{
		for(int i = 0; i < contenido.size();i++)
		{
			matcheador = patronHecho.matcher(contenido.get(i));
			if(matcheador.matches() && contenido.get(i).contains(nombreHecho))
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean chequearFormatoRegla(String regla)
	{
		Pattern patronRegla = Pattern.compile("^[a-zA-Z]+[(](([A-Z],)*[A-Z]{1})[)] :- (([a-z]+[(](([A-Z],)*[A-Z]{1})[)], )*([a-z]+[(](([A-Z],)*[A-Z]{1})[)]){1}).$");
		Matcher matcheadorRegla = patronRegla.matcher(regla);
		boolean reglaValida = matcheadorRegla.matches();
		return reglaValida;
	}
	
	private ArrayList<String> contenido;
	public void cargarBase(String ruta)
	{
		contenido = new ArrayList<String>();
		try
		{
			FileInputStream archivo= new FileInputStream(ruta);
			DataInputStream datos = new DataInputStream(archivo);
			BufferedReader lector = new BufferedReader(new InputStreamReader(datos));
			String linea;
			while((linea = lector.readLine()) != null)
			{
				contenido.add(linea);
			}
			datos.close();
		}
		catch(Exception e)
		{
			System.out.println("No se ha encontrado el archivo");
		}
		}

}
