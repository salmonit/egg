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
import libreria.entidades.Autor;
import libreria.entidades.Editorial;

/**
 *
 * @author salmonIT
 */
public class editorialDAO {
    
     private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();
    
    public void guardarEditorial(Editorial editorial) {
        try {
            if (editorial == null) {
                throw new Exception("Objeto nulo");
            }
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Editorial> listarTodos() {

        List<Editorial> editorial = null;

        try {
            editorial = em.createQuery("SELECT e FROM Editorial e").getResultList();
            return editorial;
        } catch (Exception e) {
            e.printStackTrace();
            return editorial;
        }
    }
    
    public Editorial buscarEditorial(String nombre) {

        Editorial editorial = null;

        try {
            editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e where e.nombre = '"+nombre+"'").getSingleResult();
            return editorial;
        } catch (NoResultException e) {            
            return null;
        }
    }
    
}
