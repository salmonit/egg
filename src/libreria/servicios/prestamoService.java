/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import libreria.dao.prestamoDAO;
import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;

/**
 *
 * @author salmonIT
 */
public class prestamoService {
    
    prestamoDAO dao = new prestamoDAO();
    
     public Prestamo crearPrestamo(Date fecha, Cliente cliente, List<Libro> libros) {
        Prestamo a = new Prestamo();

        try {
            a.setId(UUID.randomUUID().toString().substring(0, 8));
            a.setFecha(new Date());
            a.setCliente(cliente);
            a.setLibros(libros);
            dao.guardarPrestamo(a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return a;
        }
    }
    
}
