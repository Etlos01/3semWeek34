/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nyxis
 */
public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer cus1 = new Customer("Andreas","Andersen", new Date());
        Customer cus2 = new Customer("Hans","Nuller", new Date());
        try {
            em.getTransaction().begin();
            em.persist(cus1);
            em.persist(cus2);
            em.getTransaction().commit();
            //Verify that books are managed and has been given a database id
            System.out.println(cus1.getId());
            System.out.println(cus2.getId());

            }finally{
                em.close();
                }
            }
        }       
    

