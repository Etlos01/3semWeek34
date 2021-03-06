/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

/**
 * REST Web Service
 *
 * @author Nyxis
 */
@Path("animals")
public class AnimalsDemo {
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalsDemo
     */
    public AnimalsDemo() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalsDemo
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        return "Vuf";
    }
    @GET
    @Path("/animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
        return "[\"Dog\", \"Cat\", \"Mouse\", \"Bird\"]";
    }
    
    @Path("/animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        AnimalNoDB animal = new AnimalNoDB("Duck", "Quack");
        String jsonString = GSON.toJson(animal);
        return jsonString;
        
    }
    
    

    /**
     * PUT method for updating or creating an instance of AnimalsDemo
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
