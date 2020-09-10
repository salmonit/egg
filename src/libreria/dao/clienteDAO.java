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
import libreria.entidades.Cliente;

/**
 *
 * @author salmonIT
 */
public class clienteDAO {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();
    
    public void guardarCliente(Cliente cliente) {
        try {
            if (cliente == null) {
                throw new Exception("Objeto nulo");
            }
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Cliente> listarTodos() {

        List<Cliente> cliente = null;

        try {
            cliente = em.createQuery("SELECT e FROM Cliente e").getResultList();
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            return cliente;
        }
    }
    
    public Cliente buscarCliente(Long dni) {

        Cliente cliente = null;

        try {
            cliente = (Cliente) em.createQuery("SELECT e FROM Cliente e where e.documento = '"+dni+"'").getSingleResult();
            return cliente;
        } catch (NoResultException e) {            
            return null;
        }
    }
    
}
