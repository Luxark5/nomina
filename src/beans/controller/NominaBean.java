package beans.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import beans.model.dao.EmpleadoDAO;
import beans.model.utilidades.PersonasUtilidades;
import beans.model.vo.EmpleadoVO;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class NominaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String mensaje;
	private boolean mostrar;
	private boolean mostrarError;
	
	private EmpleadoVO empleadoVo;
	private EmpleadoDAO empleadoDao;
	
	public NominaBean() {
		empleadoVo=new EmpleadoVO();
		empleadoDao=new EmpleadoDAO();
	}
	
	public void registrarPersona() {

		mensaje=empleadoDao.registrarPersona(empleadoVo);
		
		if (mensaje.equalsIgnoreCase("Registro Exitoso")) {
			mensaje="Se ha registrado exitosamente!";
			setMostrar(true);
			setMostrarError(false);
		}else{
			mensaje="Ocurrió un problema al registrar, verifique "
					+ "qué el usuario no exista e intente nuevamente";
			setMostrarError(true);
			setMostrar(false);
		}
	}
	
	public void consultarPersona() {
		setMostrarError(false);
		setMostrar(false);
		empleadoVo=empleadoDao.consultarPersonaIndividual(empleadoVo.getDocumento());
		
		if (empleadoVo==null) {
			setMostrarError(true);
			setMostrar(false);
			mensaje="No se encuentra la persona";
			empleadoVo=new EmpleadoVO();//para evitar nullPointer al momento de reiniciar la consulta
		}
	}
	
	public void actualizarPersona() {
		String resp=empleadoDao.actualizarPersona(empleadoVo);
		
		if (resp.equals("ok")) {
			mensaje="El empleado ha sido actualizado satisfactoriamente";
			setMostrar(true);
			setMostrarError(false);
		}else {
			mensaje="No se pudo actualizar";
			setMostrar(false);
			setMostrarError(true);
		}		
	}
	
	public void eliminarPersona() {
		String resp=empleadoDao.eliminarPersona(empleadoVo.getDocumento());
		
		if (resp.equals("ok")) {
			mensaje="El empleado ha sido eliminado satisfactoriamente";
			setMostrar(true);
			setMostrarError(false);
			empleadoVo=new EmpleadoVO();
		}else {
			mensaje="No se pudo eliminar";
			setMostrar(false);
			setMostrarError(true);
		}
	}
	
	public String editarPersona(EmpleadoVO empleado){

		empleado.setEditar(true);
	
		return "consultar_lista_personas";
	}
	
	public void guardarPersona(EmpleadoVO empleado){
		String resp=empleadoDao.actualizarPersona(empleado);

		if (resp.equals("ok")) {
			mensaje="El empleado ha sido actualizado satisfactoriamente";
			setMostrar(true);
			setMostrarError(false);
			empleado.setEditar(false);
		}else {
			mensaje="No se pudo actualizar";
			setMostrar(false);
			setMostrarError(true);
		}	
	}
	
	public void eliminarPersona(EmpleadoVO empleado){
		
		String resp=empleadoDao.eliminarPersona(empleado.getDocumento());
		
		if (resp.equals("ok")) {
			mensaje="El empleado ha sido eliminado satisfactoriamente";
			setMostrar(true);
			setMostrarError(false);
			empleadoVo=new EmpleadoVO();
		}else {
			mensaje="No se pudo eliminar";
			setMostrar(false);
			setMostrarError(true);
		}
	}

	
	public List<EmpleadoVO> obtenerListaEmpleados() {
		return PersonasUtilidades.empleadosList;
	}
	
	public void limpiarFormulario() {
		empleadoVo=new EmpleadoVO();
		setMostrarError(false);
		setMostrar(false);
	}

	public EmpleadoVO getEmpleadoVo() {
		return empleadoVo;
	}

	public void setEmpleadoVo(EmpleadoVO empleadoVo) {
		this.empleadoVo = empleadoVo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public boolean isMostrarError() {
		return mostrarError;
	}

	public void setMostrarError(boolean mostrarError) {
		this.mostrarError = mostrarError;
	}
	
}
