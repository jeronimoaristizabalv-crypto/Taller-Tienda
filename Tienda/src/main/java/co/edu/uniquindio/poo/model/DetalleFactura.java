package co.edu.uniquindio.poo.model;

public class DetalleFactura {

    private final int cantidadComprada;
    private final double subTotal;
    private final Producto producto;
    private final Factura ownedByFactura;

    public DetalleFactura(int cantidadComprada, double subTotal, Producto producto, Factura ownedByFactura) {
        this.cantidadComprada = cantidadComprada;
        this.subTotal = subTotal;
        this.producto = producto;
        this.ownedByFactura = ownedByFactura;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public Factura getOwnedByFactura() {
        return ownedByFactura;
    }

    public float calcularSubtotal(){
        return (float) (cantidadComprada * getProducto().getValor());
    }

    public boolean esProducto(String codigoProducto) {
        return producto.getCodigo().equals(codigoProducto);
    }
}
