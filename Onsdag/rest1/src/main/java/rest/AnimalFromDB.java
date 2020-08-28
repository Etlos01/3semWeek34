/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import entity.Animal;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.PathParam;
import static jdk.nashorn.internal.objects.NativeJava.type;


@Path("animals_db")
public class AnimalFromDB {
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");


    @Context
    private UriInfo context;

   
    public AnimalFromDB() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalFromDB
     * @return an instance of java.lang.String
     */
    @Path("/animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
      EntityManager em = emf.createEntityManager();
      try{
      TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
      List<Animal> animals = query.getResultList();
      return new Gson().toJson(animals);
       } finally {
          em.close();
       }
    }
    
    @Path("/animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalById(@PathParam("id") int id){
        EntityManager em = emf.createEntityManager();
        try {
            Animal animal = em.find(Animal.class, id);
            return new Gson().toJson(animal);
        }finally {
            em.close();
        }
    }
    @Path("/animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a where a.type=:type",Animal.class);
            query.setParameter("type", type);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        }catch(NoResultException e){
            return null;
        }finally {
            em.close();
        }
    }
    
    @Path("/randomanimal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a order by function('RAND')", Animal.class);
            query.setMaxResults(1);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        }finally {
            em.close();
        }
    }
    

}
