/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import libreria.dao.autorDAO;
import libreria.entidades.Autor;

/**
 *
 * @author salmonIT
 */
public class autorService {

    autorDAO dao = new autorDAO();

    public Autor crearAutor(String nombre) {
        Autor a = new Autor();

        try {
            a.setId(UUID.randomUUID().toString().substring(0, 8));
            a.setNombre(nombre);
            dao.guardarAutor(a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return a;
        }
    }

    public List<Autor> listarAutores() throws Exception {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Autor buscarAutor(String nombre) throws Exception {
         try {                        
            return dao.buscarAutor(nombre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
