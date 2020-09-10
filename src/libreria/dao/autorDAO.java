/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

/**
 *
 * @author salmonIT
 */
public class autorDAO {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();
    
    public void guardarAutor(Autor autor) {
        try {
            if (autor == null) {
                throw new Exception("Objeto nulo");
            }
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Autor> listarTodos() {

        List<Autor> autor = null;

        try {
            autor = em.createQuery("SELECT a FROM Autor a").getResultList();
            return autor;
        } catch (Exception e) {
            e.printStackTrace();
            return autor;
        }
    }
    
     public Autor buscarAutor(String nombre) {

        Autor autor = null;

        try {
            autor = (Autor) em.createQuery("SELECT l FROM Autor l where l.nombre = '"+nombre+"'").getSingleResult();
            return autor;
        } catch (NoResultException e) {            
            return null;
        }
    }
    
}
