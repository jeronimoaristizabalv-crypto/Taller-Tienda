package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.*;

public class Tienda {

    private final String nombre;
    private final String nit;
    private String telefono;

    private ArrayList<Cliente> listaClientes= new ArrayList<>();
    private List<Factura> listaFacturas= new LinkedList<>();
    private Map<String,Producto> listaProductos= new HashMap<>();

    public Tienda(String nombre, String nit, String telefono) {
        this.nombre=nombre;
        this.nit=nit;
        this.telefono=telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String registrarCliente(Cliente cliente){
        Cliente clienteEncontrado= buscarCliente(cliente.getDocumentoIdentidad());
        if(clienteEncontrado==null){
            listaClientes.add(cliente);
            return "El cliente fue registrado exitosamente";
        }
        else return "No se puede registrar el cliente, yaexite un cliente con esos detalles";
    }

    //Cambiar if(clienteEncontrado==null){ por un Optional
    //Hacer el metodo buscar cliente usando un Optional

    public Optional<Factura> obtenerFactura(String codigo){
        return listaFacturas.stream().filter(f -> f.codigo().equals(codigo)).findFirst();
    }

    public Optional<Cliente> buscarCliente3(String documentoIdentidad) {
        return listaClientes.stream().filter(cliente ->
                documentoIdentidad.equals(cliente.getDocumentoIdentidad())).findFirst();
    }

    //CRUD Cliente, Producto, Factura,


    //Taller:

    // 1. Obtener la liusta de los productos con una cantidad disponible mayor igual a 10

    public List<Producto> obtenerMayoresDiez() {
        List<Producto> productosAdecuado = new ArrayList<>();

        for(Producto productosBuenos: listaProductos.values()) {

            if (productosBuenos.getCantidadDisponible() >= 10) {
                productosAdecuado.add(productosBuenos);
            }
        }
        return productosAdecuado;
    }

    // 2. obtener la lista de  codigos de los productos con un cantidad disponible mayor igual a 10 y menor que 50

    public ArrayList<String> obtenerCodigosProductosAgotados2(int limiteInferior, int limiteSuperior){
        ArrayList<String> resultado = new ArrayList<>();
        for (Producto productoAux : listaProductos.values()) {
            if (productoAux.getCantidadDisponible() >= 10 && productoAux.getCantidadDisponible() < 50) {
                resultado.add(productoAux.getCodigo());
            }
        }
        return resultado;
    }


    // 3. Obtener la lista de clientes que hayan comprado el 07 de Octubre de 2026

    public ArrayList<Cliente> obtenerClientesCompras3(LocalDate fechaConsulta){
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        for(Cliente clienteAux : listaClientes){
            if(clienteAux.isCompraEnFecha(fechaConsulta) == true){
                listaClientes.add(clienteAux);
            }
        }
        return listaClientes;
    }

    // 4. Obtener las facturas que tenga un cleinte donde su nombre empiece por R

    public ArrayList<Factura> obtenerFacturasClienteR(){
        ArrayList<Factura> resultado = new ArrayList<>();

        for (Factura factura : listaFacturas){
            if(factura.tieneClienteConR()){
                listaClientes.add(factura.cliente());
            }
        }
        return resultado;
    }

    // 5. Obtener las





}
