/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import java.util.UUID;
import libreria.dao.editorialDAO;
import libreria.entidades.Editorial;

/**
 *
 * @author salmonIT
 */
public class editorialServicio {
    
    editorialDAO dao = new editorialDAO();
    
    public Editorial crearEditorial(String nombre) {
        Editorial a = new Editorial();

        try {
            a.setId(UUID.randomUUID().toString().substring(0, 8));
            a.setNombre(nombre);
            dao.guardarEditorial(a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return a;
        }
    }
    
    public List<Editorial> listarEditoriales() throws Exception {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Editorial buscarEditorial(String nombre) throws Exception {
        try {
            return dao.buscarEditorial(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
