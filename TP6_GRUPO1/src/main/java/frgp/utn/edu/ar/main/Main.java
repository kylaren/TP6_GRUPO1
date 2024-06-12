package frgp.utn.edu.ar.main;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import frgp.utn.edu.ar.entidad.Especialidad;
import frgp.utn.edu.ar.entidad.Horario;
import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.entidad.Turno;
import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocioImp.EspecialidadNegocio;
import frgp.utn.edu.ar.negocioImp.MedicoNegocio;
import frgp.utn.edu.ar.negocioImp.PacienteNegocio;
import frgp.utn.edu.ar.negocioImp.TurnoNegocio;
import frgp.utn.edu.ar.negocioImp.UsuarioNegocio;


public class Main {
	
	private final static String MENSAJE_AGREGADO = "AGREGADO CORRECTAMENTE";
	private final static String MENSAJE_YA_EXISTE = "YA EXISTE EN LA BASE DE DATOS";
	private final static String MENSAJE_LISTADO_TODOS_LOS_USUARIOS = "LA INFORMACIÓN ES: ";
	
	public static void main(String[] args) {
		
		boolean estado = false;
		
		// Se inicializan las variables junto con los beans
		ApplicationContext appContext = new ClassPathXmlApplicationContext("frgp/utn/edu/ar/resources/Beans.xml");
		//Medico
		MedicoNegocio medicoNegocio = (MedicoNegocio) appContext.getBean("beanMedicoNegocio");
		Medico medico1 = (Medico) appContext.getBean("beanMedico");		
		//Especialidad
		EspecialidadNegocio especialidadNegocio = (EspecialidadNegocio) appContext.getBean("beanEspecialidadNegocio");
		Especialidad especialidad1 = (Especialidad) appContext.getBean("beanEspecialidad");	
		//Paciente
		PacienteNegocio pacienteNegocio = (PacienteNegocio) appContext.getBean("beanPacienteNegocio");
		Paciente paciente1 = (Paciente) appContext.getBean("beanPaciente");	
		//Turno
		TurnoNegocio turnoNegocio = (TurnoNegocio) appContext.getBean("beanTurnoNegocio");
		Turno turno1 = (Turno) appContext.getBean("beanTurno");	
		//Usuario
		UsuarioNegocio usuarioNegocio = (UsuarioNegocio) appContext.getBean("beanUsuarioNegocio");
		Usuario usuario1 = (Usuario) appContext.getBean("beanUsuario");	
		//Horario
		Horario horario1= (Horario) appContext.getBean("beanHorario");
		
		//Horario
		horario1.setHorarioDetails("Monday",  LocalTime.of(9, 0), LocalTime.of(12, 0), medico1);
		
		//Usuario
		usuario1.setNombreUser("pepe");
		usuario1.setContrasenia("pepo");
		usuarioNegocio.Add(usuario1);
        
        //Especialidad
        especialidad1.setNombre("Odontología");
        especialidadNegocio.Add(especialidad1);
        
        //Paciente
        paciente1.setPacienteDetails(11214589, "Juan Carlos", "Jiménez Rufino", "123456", "Siempre viva 742", 
                "Massachusetts", "Springfield", LocalDate.of(1990, 5, 15), "juan.carlos@mail.com");
                
        estado = pacienteNegocio.Exist(11214589);        
	     if(estado == false){
	         pacienteNegocio.Add(paciente1);
	 	      	System.out.println(MENSAJE_AGREGADO);	 
	     }
	     else
	    	 System.out.println("Paciente " + MENSAJE_YA_EXISTE);
	                     
        //Medico
        medico1.setMedicoDetails("John", "Doe", "M", LocalDate.of(1980, 1, 1), "street 123", "Glendale",
                "john@connor.com", "9999", usuario1, especialidad1);		   
        
        
	     estado = medicoNegocio.Exist("john123");
	     if(estado == false){
	    	 	medicoNegocio.Add(medico1);
	 	      	System.out.println(MENSAJE_AGREGADO);	 
	     }
	     else {
	    	 System.out.println(MENSAJE_YA_EXISTE);
	     }
	     
	     	     
	     //Turno
	     turno1.setTurnoDetails(medico1, paciente1, LocalDate.now(), new Time(System.currentTimeMillis()), "Todo en orden", Turno.EstadoTurno.PENDIENTE.name());     
	     estado = turnoNegocio.Exist(1);
	     if(estado == false){
	    	 	turnoNegocio.Add(turno1);
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
	     
		((ConfigurableApplicationContext)(appContext)).close();
	}

}
