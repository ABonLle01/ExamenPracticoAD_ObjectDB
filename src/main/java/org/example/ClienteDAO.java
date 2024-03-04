package org.example;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteDAO {

    static EntityManager em = Util.getEntityManagerFactory().createEntityManager();

    public static void insertarCliente(Cliente c){
        EntityManager em = Util.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }

    public static void getCliente(Long id){
        TypedQuery<Cliente> q = em.createQuery("select c from Cliente c where c.id =: id", Cliente.class);
        q.setParameter("id",id);
        Cliente cliente = q.getSingleResult();
        System.out.println(cliente.toString());
    }

    public static void listarMejoresClientes(Long cantidad){
        TypedQuery<Cliente> q = em.createQuery("select c from Cliente c where c.total>:cantidad", Cliente.class);
        q.setParameter("cantidad",cantidad);
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) q.getResultList();

        for (Cliente c : clientes){
            System.out.println(c);
        }
    }

    public static void estadisticas(){
        TypedQuery<Cliente> q = em.createQuery("select c from Cliente c", Cliente.class);
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) q.getResultList();

        Long suma = 0L;
        for (Cliente cliente : clientes) {
            suma += cliente.getTotal();
        }
        System.out.println("Total de ventas entre todos los clientes: "+suma);

        suma=0L;
        int inactivos = 0;
        for (Cliente cliente : clientes) {
            if (Objects.equals(cliente.getEstado().toLowerCase(), "activo")) {
                suma += cliente.getTotal();
            }else{
                if (cliente.getTotal()>0){
                    inactivos++;
                }
            }
        }

        System.out.println("El promedio de ventas de los clientes activos es: "+suma/clientes.size());
        System.out.println("Cantidad de clientes inactivos que tienen total de ventas mayor a 0: "+inactivos);

    }

}
