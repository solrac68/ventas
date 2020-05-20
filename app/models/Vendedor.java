package models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Vendedor {

    //public enum Estado {Suspendido, Activo};

    public Vendedor(){

    }

    public Vendedor(Integer id, String nombre, String numCelular, LocalDate fechaIngreso, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.numCelular = numCelular;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;



    }

    public Integer id;
    public String nombre;
    public String numCelular;
    public LocalDate fechaIngreso;
    public String estado;

    private final static Set<Vendedor> vendedores;

    static {
        vendedores = new HashSet<>();
        vendedores.add(new Vendedor(1,"Juan de Dios Oliveros", "3053385051", LocalDate.now(),"Activo"));
        vendedores.add(new Vendedor(2,"Mario Alberto Yepes", "3057885051", LocalDate.now(),"Activo"));
        vendedores.add(new Vendedor(3,"Julios Verne", "3053383451", LocalDate.now(),"Suspendido"));
        vendedores.add(new Vendedor(4,"Evariste Galois", "3053385045", LocalDate.now(),"Suspendido"));
    }

    public static Set<Vendedor> allVendedores(){
        return vendedores;
    }

    public static  Vendedor findById(Integer id){
        for (Vendedor vendedor : vendedores){
            if (id.equals(vendedor.id)){
                return  vendedor;
            }
        }
        return null;
    }

    public static void add(Vendedor vendedor){
        vendedores.add(vendedor);
    }

    public static boolean remove(Vendedor vendedor){
        return vendedores.remove(vendedor);
    }

}
