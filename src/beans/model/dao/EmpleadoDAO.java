package beans.model.dao;

import beans.model.utilidades.PersonasUtilidades;
import beans.model.vo.EmpleadoVO;

public class EmpleadoDAO {

	public EmpleadoVO consultarLogin(String documento,String password) {
		EmpleadoVO empleado=null;

		for (EmpleadoVO obj : PersonasUtilidades.empleadosList) {
			if (obj.getDocumento().equals(documento) && obj.getPassword().equals(password)) {
				empleado=obj;
				break;
			}
		}

		return empleado;
	}

	public String registrarPersona(EmpleadoVO empleadoVo) {
		String mensaje="";
		boolean existe=false;
		
		for (EmpleadoVO obj : PersonasUtilidades.empleadosList) {
			if (obj.getDocumento().equals(empleadoVo.getDocumento())) {
				existe=true;
				break;
			}
		}
		
		if (existe==false) {
			empleadoVo.setPassword(empleadoVo.getDocumento());
			PersonasUtilidades.empleadosList.add(empleadoVo);
			mensaje="Registro Exitoso";
		}else {
			mensaje="no registra";	
		}

		return mensaje;
	}

	public EmpleadoVO consultarPersonaIndividual(String documento) {
		EmpleadoVO empleado=null;
		for (EmpleadoVO obj : PersonasUtilidades.empleadosList) {
			if (obj.getDocumento().equals(documento)) {
				//No podemos decir que empleado=obj; ya que si hacemos eso estariamos usando siempre
				//el objeto obtenido de la lista, por lo tanto cualquier modificación a dicho objeto
				//modificaria el almacenado en dicha lista.
				empleado=new EmpleadoVO();
				empleado.setDocumento(obj.getDocumento());
				empleado.setNombre(obj.getNombre());
				empleado.setDireccion(obj.getDireccion());
				empleado.setTipo(obj.getTipo());
				empleado.setPassword(obj.getPassword());
				empleado.setSalario(obj.getSalario());
				empleado.setSexo(obj.getSexo());
				empleado.setTelefono(obj.getTelefono());
				break;
			}
		}
		return empleado;
	}

	public String actualizarPersona(EmpleadoVO empleadoVo) {
		String resp="";
		
		for (EmpleadoVO obj : PersonasUtilidades.empleadosList) {
			if (obj.getDocumento().equals(empleadoVo.getDocumento())) {
				obj.setNombre(empleadoVo.getNombre());
				obj.setDireccion(empleadoVo.getDireccion());
				obj.setTipo(empleadoVo.getTipo());
				obj.setPassword(empleadoVo.getPassword());
				obj.setSalario(empleadoVo.getSalario());
				obj.setSexo(empleadoVo.getSexo());
				obj.setTelefono(empleadoVo.getTelefono());
				resp="ok";
				break;
			}
		}
		return resp;
	}

	public String eliminarPersona(String documento) {
		String resp="";
		
		for (EmpleadoVO obj : PersonasUtilidades.empleadosList) {
			if (obj.getDocumento().equals(documento)) {
				PersonasUtilidades.empleadosList.remove(obj);
				resp="ok";
				break;
			}
		}		
		return resp;
	}
	
}
