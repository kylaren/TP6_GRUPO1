package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Turnos")
public class Turno implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public enum EstadoTurno {
	    PENDIENTE,
	    PRESENTE,
	    AUSENTE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTurno;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMedico")
	private Medico medico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPaciente", nullable = false)
	private Paciente paciente;
	private LocalDate fecha;
	private Time hora;
	private String observacion;
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
	private String EstadoTurno;
	
	public Turno() {
		super();
	}
	public Turno(Medico medico, Paciente paciente, LocalDate fecha, Time hora, String observacion,
			String estadoTurno) {
		super();
		this.medico = medico;
		this.paciente = paciente;
		this.fecha = fecha;
		this.hora = hora;
		this.observacion = observacion;
		EstadoTurno = estadoTurno;
	}
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getEstadoTurno() {
		return EstadoTurno;
	}
	public void setEstadoTurno(String estadoTurno) {
		EstadoTurno = estadoTurno;
	}

}