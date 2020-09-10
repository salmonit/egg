/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.interfaz;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Libro;
import libreria.servicios.autorService;
import libreria.servicios.clienteService;
import libreria.servicios.editorialServicio;
import libreria.servicios.libroServicio;
import libreria.servicios.prestamoService;

/**
 *
 * @author salmonIT
 */
public class Menu {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    autorService autor = new autorService();
    editorialServicio editorial = new editorialServicio();
    libroServicio libro = new libroServicio();
    clienteService cliente = new clienteService();
    prestamoService prestamo = new prestamoService();

    public void opciones() throws Exception {

        String opcion = "";
        while (!opcion.equals("fin")) {
            System.out.println("Ingrese un una opcion del menu o 'fin' para terminar");
            System.out.println("1 - Cargar Autor");
            System.out.println("2 - Listar Autores");
            System.out.println("3 - Cargar Editorial");
            System.out.println("4 - Listar Editoriales");
            System.out.println("5 - Cargar Libro");
            System.out.println("6 - Listar Libros");
            System.out.println("7 - Realizar un prestamo");
            System.out.println("8 - Buscar un Libro");
            opcion = leer.next();
            if (!opcion.equals("fin")) {

                switch (opcion) {

                    case "1": {
                        System.out.println("Ingrese el nombre del autor: ");
                        String nombre = leer.next();
                        autor.crearAutor(nombre);
                        break;
                    }
                    case "2": {
                        System.out.println("LISTADO DE AUTORES:");
                        autor.listarAutores().forEach((n -> System.out.println("Nombre: " + n.getNombre())));
                        System.out.println("");
                        break;
                    }
                    case "3": {
                        System.out.println("Ingrese el nombre de la Editorial: ");
                        String nombre = leer.next();
                        editorial.crearEditorial(nombre);
                        break;
                    }
                    case "4": {
                        System.out.println("LISTADO DE EDITORIALES:");
                        editorial.listarEditoriales().forEach((n -> System.out.println("Nombre: " + n.getNombre())));
                        System.out.println("");
                        break;
                    }
                    case "5": {

                        System.out.println("Ingrese el numero de ISBN: ");
                        long isbn = leer.nextLong();
                        //Si ibn no existe pido el resto d elos datos
                        if (!libro.validarISBN(isbn)) {

                            //Pido y busco autor en DB
                            System.out.println("Ingrese el nombre del autor");
                            String nombreAutor = leer.next();
                            if (autor.buscarAutor(nombreAutor) == null) {
                                autor.crearAutor(nombreAutor);
                            }

                            //Pido y busco editorial en DB
                            System.out.println("Ingrese el nombre de la Editorial");
                            String nombreEditorial = leer.next();
                            if (editorial.buscarEditorial(nombreEditorial) == null) {
                                editorial.crearEditorial(nombreEditorial);
                            }

                            //Pido y valido año
                            boolean dato = false;
                            Integer year = Year.now().getValue();
                            Integer anio = 0;
                            while (!dato) {
                                System.out.println("Ingrese un año valido");
                                anio = leer.nextInt();
                                if (anio <= year) {
                                    dato = true;
                                }
                            }
                            dato = false;

                            //Pido y valido cant de ejemplares
                            Integer ejemplares = 0;
                            while (!dato) {
                                System.out.println("Ingrese cantidad de ejemplares");
                                ejemplares = leer.nextInt();
                                if (ejemplares > 0) {
                                    dato = true;
                                }
                            }
                            dato = false;

                            //Pido y valido titulo del libro
                            String titulo = "";
                            while (!dato) {
                                System.out.println("Ingrese el titulo");
                                titulo = leer.next();
                                if (titulo != "") {
                                    dato = true;
                                }
                            }

                            //Cargo el libro
                            libro.crearLibro(isbn, titulo, anio, ejemplares, autor.buscarAutor(nombreAutor), editorial.buscarEditorial(nombreEditorial));

                        } else {
                            System.out.println("El libro con ese ISBN ya esxiste");
                        }

                        break;

                    }
                    case "6": {
                        System.out.println("LISTADO DE LIBROS:");
                        libro.listarLibros().forEach((n -> System.out.println("Nombre: " + n.toString())));
                        System.out.println("");
                        break;
                    }
                    case "7": {
                        System.out.println("REALIZAR PRESTAMO:");
                        System.out.println("Ingrese el DNI del cliente: ");
                        long dni = leer.nextLong();
                        //Si DNI no existe pido el resto d elos datos
                        if (cliente.buscarCliente(dni) == null) {
                            System.out.println("Ingrese el nombre: ");
                            String nombre = leer.next();
                            System.out.println("Ingrese el apellido: ");
                            String apellido = leer.next();
                            System.out.println("Ingrese el domicilio: ");
                            String domicilio = leer.next();
                            System.out.println("Ingrese el telefono: ");
                            String telefono = leer.next();
                            cliente.crearCliente(dni, nombre, apellido, domicilio, telefono);
                        }
                        String pedirLibro = "si";
                        while (pedirLibro.equals("si")) {

                            //Pedir titulo de Libro a Prestar y buscarlo                                                   
                            System.out.println("Buscar libro por Titulo:");
                            List<Libro> resulBusqueda = libro.buscarLibro(leer.next());
                            ArrayList<Libro> libros = (ArrayList) resulBusqueda; 

                            //Creo un array para guardar el ISBN y la opcion para el usuario
                            Long vecISBN[] = new Long[libros.size()];
                            int i = 0;

                            for (Libro l : libros) {
                                if (l.getPrestados() < l.getEjemplares()) {
                                    i++;
                                    vecISBN[i] = l.getIsbn();
                                    System.out.println("Elija el num de la opcion a prestar / ingrese -1 para cancelar (solo se muestran aquellos con ejemplares disponibles):");
                                    System.out.println("Opcion-> " + i + " - " + l.getTitulo());
                                }
                            }
                            //Si la busqueda arrojo libros disponibles pido por pantalla la opcion elegida y valido
                            if (i > 0) {
                                //Creo el list de libros a prestar
                                List<Libro> libPrestamos = null;
                                int isbnElegido = 0;

                                while (isbnElegido == 0) {
                                    isbnElegido = leer.nextInt();
                                }

                                if (isbnElegido > 0 && isbnElegido <= i) {

                                    ////////////////////////////////////////////////////////////////////////    
                                    //Realizo el prestamo impactando en todas las tablas correspondientes///
                                    ////////////////////////////////////////////////////////////////////////    
                                    libPrestamos.add(libro.traerLibro(vecISBN[isbnElegido]));

                                    System.out.println("Desea realizar o pedir otro libro (si/no)");
                                    pedirLibro = leer.next();

                                    if (pedirLibro.equals("no")) {
                                        //Pregunto si no esta vacia la list de libros a prestar y acrgo el prestamo
                                        if (!libPrestamos.isEmpty()) {

                                            //Cargo el prestamos definitivo                                                                                                
                                            prestamo.crearPrestamo(new Date(), cliente.buscarCliente(dni), libPrestamos);
                                            
                                            //Actualizao el estado de los ejemplares del libro
                                            libros = (ArrayList) libPrestamos;
                                            for(Libro lp: libros){
                                             //sumo a prestamo
                                              lp.setEjemplares(lp.getPrestados()+1);
                                             libro.actualizarLibro(lp);
                                            }
                                        }
                                    }

                                } else {
                                    if (isbnElegido != -1) {
                                        System.out.println("Ingrese una opcion valida");
                                    } else {
                                        if (isbnElegido == -1) {
                                            break;
                                        }
                                    }
                                }

                            } else {
                                System.out.println("La busueda no arrojo libros dispoibles con ese titulo");
                            }

                        }
                        break;

                    }
                    case "8": {
                        System.out.println("Buscar libro por Titulo:");
                        libro.buscarLibro(leer.next()).forEach((n -> System.out.println("Nombre: " + n.getTitulo())));
                        System.out.println("");
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

}
