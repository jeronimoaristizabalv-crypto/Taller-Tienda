package co.edu.uniquindio.poo.model;

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

    // 1.
    



}
