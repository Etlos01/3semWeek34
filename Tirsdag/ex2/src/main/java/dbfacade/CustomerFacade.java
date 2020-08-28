/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entities.Customer;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nyxis
 */
public class CustomerFacade {
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {}

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer findById(int id){
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        }finally {
            em.close();
        }
    }
    
    public Customer findByLastName(String name){
        EntityManager em = emf.createEntityManager();
        Customer c = new Customer();
        try {
            TypedQuery<Customer> query = em.createQuery("Select c FROM Customer c WHERE c.lastName = :name",Customer.class);
            List<Customer> lis = query.setParameter("name", name).getResultList();
            for(Customer cus : lis){
                c = cus; 
            }
        }finally {
            em.close();
        }
        return c;
    }
    
    public int getNumberOfCumstomers(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("COUNT(*) from Customer", Customer.class);
            return query.getFirstResult();
        } finally{
            em.close();
        }
    }
    
    public List<Customer> allCustomers(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        } finally{
            em.close();
        }
    }
    
    public Customer addCustomer(String fName, String lName){
        Customer customer = new Customer(fName,lName, new Date());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
    CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    //Customer cus1 = facade.addCustomer("Buller","Duller");
    //Customer cus2 = facade.addCustomer("Nik", "Jay");
    //Find book by ID
    System.out.println("Customer1: "+facade.findById(2).getFirstName());
    System.out.println("Customer2: "+facade.findById(1).getFirstName());
    //Find all books
    System.out.println("Number of customers: "+facade.allCustomers().size());
    System.out.println(facade.findByLastName("Duller").getFirstName());
        System.out.println(facade.getNumberOfCumstomers());

    }
    
    



}
