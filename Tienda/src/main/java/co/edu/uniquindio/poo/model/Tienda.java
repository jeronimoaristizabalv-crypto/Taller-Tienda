package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.*;

public class Tienda {

    private final String nombre;
    private final String nit;
    private String telefono;

    private ArrayList<Cliente> listaClientes= new ArrayList<>();
    private List<Factura> listaFacturas= new LinkedList<>();
    private Map<String,Producto> hashMapListaProductos= new HashMap<>();

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

    //Cambiar if(clienteEncontrado==null){ por un Optional
    //Hacer el metodo buscar cliente usando un Optional

    public Optional<Factura> obtenerFactura(String codigo){
        return listaFacturas.stream().filter(f -> f.codigo().equals(codigo)).findFirst();
    }

    public Optional<Cliente> buscarCliente3(String documentoIdentidad) { // 3ra vairacion de la función de buscarCliente con Stream
        return listaClientes.stream().filter(cliente ->
                documentoIdentidad.equals(cliente.getDocumentoIdentidad())).findFirst();
    }

    // CRUD Cliente

    public String registrarCliente(Cliente cliente){
        Cliente clienteBuscado= buscarCliente(cliente.getDocumentoIdentidad());
        if(clienteBuscado==null){
            listaClientes.add(cliente);
            return "El cliente ha sido registrado con éxito";
        }else
            return "El cliente que desea registrar ua se encuentra en el sistema";
    }

    public String registrarCliente2(Cliente cliente){ // Se utiliza el Optional
        Optional<Cliente> clienteBuscado= buscarCliente2(cliente.getDocumentoIdentidad());
        if(clienteBuscado.isEmpty()){
            listaClientes.add(cliente);
            return "El cliente fue registrado con éxito en el sistema";
        }else return "El cliente no se pudo registrar, ya bay uno existente con su ID";
    }

    public Optional<Cliente> conocerCliente(String documentoIdentidad){
        for(Cliente clienteAux: listaClientes){
            if(clienteAux.getDocumentoIdentidad().equalsIgnoreCase(documentoIdentidad)){
                return Optional.of(clienteAux);
            }
        }
        return Optional.empty();
    }

    public String borrarCliente(String documentoIdentidad){
        for(Cliente clienteAux: listaClientes){
            if(clienteAux.getDocumentoIdentidad().equalsIgnoreCase(documentoIdentidad)){
                listaClientes.remove(clienteAux);
                return "El cliente ha sido borrado correctamente";
            }
        }
        return "El cliente que desea borrar no se encuentra registrado en el sistema";
    }

    // CRUD producto

    public String registrarProducto(Producto producto){
        if (hashMapListaProductos.containsKey(producto.getCodigo())){
            return "Un producto equivalente ya existe dentro del sistema";
        }else
            hashMapListaProductos.put(producto.getCodigo(), producto);
        return "El producto ha sido registrado con éxito";
    }

    public Optional<Producto> buscarProducto(String codigo) {
        return Optional.ofNullable(hashMapListaProductos.get(codigo));
    }

    public String borrarProducto(String codigo){
        if (hashMapListaProductos.containsKey(codigo)){
            hashMapListaProductos.remove(codigo);
            return "El producto fue eliminado correctamente";
        }else
            return "No existe ningún producto posible para borrar";

    }

    // CRUD Factura

    public String crearFactura(Factura factura) {
        if (leerFactura(factura.codigo()).isPresent()) {
            return "La factura ya es existente";
        }
        listaFacturas.add(factura);
        return "La factura se ha creado correctamente";
    }

    public Optional<Factura> leerFactura(String codigo) {
        for (Factura facturaAux : listaFacturas) {
            if (facturaAux.codigo().equals(codigo)) {
                return Optional.of(facturaAux);
            }
        }
        return Optional.empty();
    }

    // Funciones adicionales al los CRUDs

    public Cliente buscarCliente(String documentoIdentidad){ //Función original
        for(Cliente clienteAux: listaClientes){
            if(clienteAux.getDocumentoIdentidad().equalsIgnoreCase(documentoIdentidad)){
                return clienteAux;
            }
        }
        return null;
    }

    public Optional<Cliente> buscarCliente2(String documentoIdentidad){ //Funcion con el Optional
        for(Cliente clienteAux: listaClientes){
            if(clienteAux.getDocumentoIdentidad().equalsIgnoreCase(documentoIdentidad)){
                return Optional.of(clienteAux);
            }
        }
        return Optional.empty();

    }




    //Taller:

    // 1. Obtener la lista de los productos con una cantidad disponible mayor igual o 10

    public List<Producto> obtenerMayoresDiez() {
        List<Producto> productosAdecuado = new ArrayList<>();

        for (Producto productosBuenos : hashMapListaProductos.values()) {
            if (productosBuenos.getCantidadDisponible() >= 10) {
                productosAdecuado.add(productosBuenos);
            }
        }
        return productosAdecuado;
    }

    // 2. obtener la lista de codigos de los productos con una cantidad disponible mayor igual a 10 y menor que 50

    public ArrayList<String> obtenerCodigosProductosAgotados(int limiteInferior, int limiteSuperior){
        ArrayList<String> resultado = new ArrayList<>();
        for(String codigo : hashMapListaProductos.keySet()){
            Producto producto = hashMapListaProductos.get(codigo);
            if(producto.getCantidadDisponible() >= 10 && producto.getCantidadDisponible() < 50 ){
                resultado.add(codigo);
            }
        }
        return resultado;
    }

    public ArrayList<String> obtenerCodigosProductosAgotados2(int limiteInferior, int limiteSuperior){
        ArrayList<String> resultado = new ArrayList<>();
        for (Producto productoAux : hashMapListaProductos.values()) {
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

    // 5. Obtener las facturas donde se haya comprado un celular de marca iPhone 16 Pro Max

    public ArrayList<Factura> obtrenerFacturaiPhone(String codigoProducto){
        ArrayList<Factura> resultado= new ArrayList<>();
        for(Factura factura: listaFacturas){
            if(factura.contieneProducto(codigoProducto)){
                resultado.add(factura);
            }
        }
        return resultado;
    }

    // 6. Obtner las fucturas que tenga un cliente donde su nombre sea juan y haya comprado un celular de marca iPhone 16 Pro Max

    // 7. impelemtar un metodo que reciba una categoría y retorne todos los productos registrados que pertenezcan a ella:

    // 8. Implementar un metodo que reciba un precio minimo y un precio máximo, y retorne los productos cuyo precio se encuentre dentro de ese rango, incluyendo ambos límites.

    // 9. implementar un metodo que retorne todos los productos registrados en la tienda, ordenados de menor a mayor según su precio

    // 10. Implementar un metodo que identifique el producto con el precio más alto de la tienda. Si no existen productos registrados, el etodo debe retornar un Optional vacío.

    // 11. Implementar un metodo que reciba el nombre de una ciudad y retorne todos los clietes que residan en ella. La búsqueda debe realizarse sin diferenciar entre mayúsculas y minúsculas.





}
