package frgp.utn.edu.ar.negocioImp;

import frgp.utn.edu.ar.daoImp.DaoEspecialidad;
import frgp.utn.edu.ar.entidad.Especialidad;
import frgp.utn.edu.ar.negocio.IEspecialidadNegocio;


public class EspecialidadNegocio implements IEspecialidadNegocio {
	
    private DaoEspecialidad daoEspecialidad;

    public void setDaoEspecialidad(DaoEspecialidad daoEspecialidad) {
        this.daoEspecialidad = daoEspecialidad;
    }

    public boolean Add(Especialidad especialidad) {
        return daoEspecialidad.Add(especialidad);
    }

}