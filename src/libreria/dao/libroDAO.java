/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;

/**
 *
 * @author salmonIT
 */
public class libroDAO {
    
     private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();
    
    public void guardarLibro(Libro libro) {
        try {
            if (libro == null) {
                throw new Exception("Objeto nulo");
            }
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizarLibro(Libro libro) {
        try {
            if (libro == null) {
                throw new Exception("Objeto nulo");
            }
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Libro> listarTodos() {

        List<Libro> libro = null;

        try {
            libro = em.createQuery("SELECT l FROM Libro l").getResultList();
            return libro;
        } catch (Exception e) {
            e.printStackTrace();
            return libro;
        }
    }
    
     public List<Libro> listarBusqueda(String titulo) {

        List<Libro> libro = null;

        try {
            libro = em.createQuery("SELECT l FROM Libro l where l.titulo like '%"+titulo+"%' ").getResultList();
            return libro;
        } catch (Exception e) {
            e.printStackTrace();
            return libro;
        }
    }
    
     public Libro buscarISBN(long isbn) {

        Libro libro = null;

        try {
            libro = (Libro) em.createQuery("SELECT l FROM Libro l where l.isbn = "+isbn).getSingleResult();
            return libro;
        } catch (NoResultException e) {            
            return null;
        }
    }
    
}
