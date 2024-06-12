package frgp.utn.edu.ar.main;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import frgp.utn.edu.ar.entidad.Especialidad;
import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocioImp.EspecialidadNegocio;
import frgp.utn.edu.ar.negocioImp.MedicoNegocio;


public class Main {
	
	private final static String MENSAJE_AGREGADO = "AGREGADO CORRECTAMENTE";
	private final static String MENSAJE_YA_EXISTE = "YA EXISTE EN LA BASE DE DATOS";
	private final static String MENSAJE_LISTADO_TODOS_LOS_USUARIOS = "LA INFORMACIÓN ES: ";
	
	public static void main(String[] args) {
		
		boolean estado = false;
		
		// Se inicializan las variables junto con los beans
		ApplicationContext appContext = new ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/Beans.xml");
		MedicoNegocio medicoNegocio = (MedicoNegocio) appContext.getBean("beanMedicoNegocio");
		Medico medico1 = (Medico) appContext.getBean("beanMedico");		
		EspecialidadNegocio especialidadNegocio = (EspecialidadNegocio) appContext.getBean("beanEspecialidadNegocio");
		Especialidad especialidad1 = (Especialidad) appContext.getBean("beanEspecialidad");	
		
		
		//Usuario de prueba (falta hacer capas, beans, etc)
        Usuario user1 = new Usuario("john123","qwerty");
        //Especialidad
        especialidad1.setNombre("Odontología");
        especialidadNegocio.Add(especialidad1);
        medico1.setMedicoDetails("John", "Doe", "M", LocalDate.of(1980, 1, 1), "street 123", "Glendale",
                "john@connor.com", "9999", user1, especialidad1);		
        
	     estado = medicoNegocio.Exist("john123");
	     if(estado == false){
	    	 	medicoNegocio.Add(medico1);
	 	      	System.out.println(MENSAJE_AGREGADO);	 
	     }
	     else {
	    	 System.out.println(MENSAJE_YA_EXISTE);
	     }
	     
	     //LEE TODOS:
	     List<Medico> medicos = medicoNegocio.ReadAll();
	     for (Medico medico: medicos) {
			System.out.println(MENSAJE_LISTADO_TODOS_LOS_USUARIOS + medico.toString());
		}
	    
	     medicoNegocio.Delete(1);
	     
	   //LEE TODOS:
	     System.out.println("Se lee de nuevo la lista de medicos!");
	     List<Medico> listaMedicos = medicoNegocio.ReadAll();
	     for (Medico medico: listaMedicos) {
			System.out.println(MENSAJE_LISTADO_TODOS_LOS_USUARIOS + medico.toString());
		}
	     
	}

}
