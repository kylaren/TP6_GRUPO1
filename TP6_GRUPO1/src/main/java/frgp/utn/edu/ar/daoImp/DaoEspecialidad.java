package frgp.utn.edu.ar.daoImp;

import org.hibernate.Session;

import frgp.utn.edu.ar.dao.IdaoEspecialidad;
import frgp.utn.edu.ar.entidad.Especialidad;

public class DaoEspecialidad implements IdaoEspecialidad {
    private ConfigHibernate conexion;
    
	public DaoEspecialidad() {
	}
	
	public DaoEspecialidad(ConfigHibernate conexion) {
		this.conexion = conexion;
	}

    public boolean Add(Especialidad especialidad) {
        boolean estado = true;
        conexion = new ConfigHibernate();
        Session session = null;

        try {
            session = conexion.abrirConexion();
            session.beginTransaction();
            session.save(especialidad);
            session.flush();
            session.getTransaction().commit();
            Especialidad savedEspecialidad = (Especialidad) session.get(Especialidad.class, especialidad.getId());
            
            if (savedEspecialidad == null) {
                estado = false;
            }
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            estado = false;
        } finally {
            if (session != null) {
                conexion.cerrarSession();
            }
        }

        return estado;
    }
}