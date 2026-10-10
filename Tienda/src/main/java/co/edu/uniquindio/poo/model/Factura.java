package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;

public record Factura(String codigo, LocalDate fecha, double total, EstadoFactura estadoFactura, MetodoPago metodoPago, Cliente cliente, ArrayList<DetalleFactura> listaDetallesFactura, Tienda ownedByTienda) {//Los records son clases inmutables

    public boolean tieneClienteConR(){
        boolean resultado = false;

        resultado = cliente.verificarNombreConR();

        return resultado;
    }

    public boolean contieneProducto(String codigoProducto) {
        for (DetalleFactura detalle : listaDetallesFactura) {
            if (detalle.esProducto(codigoProducto)) {
                return true;
            }
        }
        return false;
    }




}
