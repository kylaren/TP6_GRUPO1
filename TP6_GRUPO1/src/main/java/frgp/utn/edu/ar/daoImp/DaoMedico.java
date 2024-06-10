package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Session;

import frgp.utn.edu.ar.dao.IdaoMedico;
import frgp.utn.edu.ar.entidad.Medico;
import frgp.utn.edu.ar.entidad.Medico.Estado;
import frgp.utn.edu.ar.entidad.Usuario;

public class DaoMedico implements IdaoMedico {
	private ConfigHibernate conexion;
	
	public DaoMedico() {
	}
	
	public DaoMedico(ConfigHibernate conexion) {
		this.conexion = conexion;
	}
	
	public boolean Add(Medico medico) {
		boolean estado = true;
	    conexion = new ConfigHibernate();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        session.save(medico);
	        session.flush();
	        session.getTransaction().commit();
	        Medico savedMedico = (Medico) session.get(Medico.class, medico.getUsuario());
	        
	        if (savedMedico == null) {
	            estado = false;
	        }
	        
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    }
	    
	    return estado;
	}
	
	public Medico ReadOne(String nombreMedico) {
		Session session = conexion.abrirConexion();
		session.beginTransaction();
        Medico medico = (Medico)session.get(Medico.class,nombreMedico);
        return medico;
	}

	
	public boolean Update(Medico medico) {
		boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        session.update(medico);
	        session.flush();
	        session.getTransaction().commit();
	        Medico savedMedico = (Medico) session.get(Medico.class, medico.getUsuario());
	        
	        if (savedMedico.equals(medico) == false) {
	            estado = false;
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    }
	    
		return estado;
	}
	
	public boolean Delete(Medico medico){	
		boolean estado = true;
		conexion = new ConfigHibernate();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        medico.setEstado(Estado.INACTIVO);
	        session.update(medico);
	        session.flush();
	        session.getTransaction().commit();
	        
	        Medico savedMedico = (Medico) session.get(Medico.class, medico.getUsuario());
	        if (savedMedico.getEstado() != Estado.INACTIVO) {
	            estado = false;
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    
		return estado;
	}

	public List<Medico> ReadAll() {		
		conexion = new ConfigHibernate();
	    Session session = conexion.abrirConexion();
        session.beginTransaction();
        List<Medico> medicos = session.createQuery("FROM Medico").list();
        return medicos;
	}
	
	public ConfigHibernate getConexion() {
		return conexion;
	}

	public void setConexion(ConfigHibernate conexion) {
		this.conexion = conexion;
	}
}
