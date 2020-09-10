/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.List;
import java.util.UUID;
import libreria.dao.clienteDAO;
import libreria.entidades.Cliente;

/**
 *
 * @author salmonIT
 */
public class clienteService {
    
    clienteDAO dao = new clienteDAO();
    
    public Cliente crearCliente(Long dni, String nombre, String apellido, String domicilio, String telefono) {
        Cliente a = new Cliente();

        try {
            a.setId(UUID.randomUUID().toString().substring(0, 8));
            a.setDocumento(dni);
            a.setNombre(nombre);
            a.setApellido(apellido);
            a.setDomicilio(domicilio);
            a.setTelefono(telefono);
            dao.guardarCliente(a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return a;
        }
    }
    
    public List<Cliente> listarClientes() throws Exception {
        try {
            return dao.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Cliente buscarCliente(Long dni) throws Exception {
        try {
            return dao.buscarCliente(dni);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
