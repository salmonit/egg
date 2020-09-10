/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import java.util.UUID;
import libreria.dao.libroDAO;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;

/**
 *
 * @author salmonIT
 */
public class libroServicio {
    
    libroDAO dao = new libroDAO();
    
     public Libro crearLibro(long isbn, String titulo, Integer anio, Integer ejemplares, Autor autor, Editorial editorial) {
        Libro l = new Libro();

        try {
            l.setId(UUID.randomUUID().toString().substring(0, 8));
            l.setIsbn(isbn);
            l.setTitulo(titulo);
            l.setAnio(anio);
            l.setEjemplares(ejemplares);
            l.setPrestados(0);
            l.setAutor(autor);
            l.setEditorial(editorial);
            dao.guardarLibro(l);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return l;
        }
    }
     
     public void actualizarLibro(Libro libro) {

        try {            
            dao.actualizarLibro(libro);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {;
        }
    }
    
    public List<Libro> listarLibros() throws Exception {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean validarISBN(long isbn) throws Exception {
        boolean encontrar = false;
         try {            
            if(dao.buscarISBN(isbn)!= null){encontrar=true;};
            return encontrar;
        } catch (Exception e) {
            e.printStackTrace();
            return encontrar;
        }
    }
    
    public List<Libro> buscarLibro(String titulo) throws Exception {
        try {
            return dao.listarBusqueda(titulo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Libro traerLibro(long isbn) throws Exception {
        try {
            return dao.buscarISBN(isbn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
     
    
    
}
