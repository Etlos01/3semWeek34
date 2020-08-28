/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maximapps.rest1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

class Country{
    String name;
    String borders;

    public Country(String name, String borders) {
        this.name = name;
        this.borders = borders;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }
    
    
}
@Path("demo")
public class DemoResource {

    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    public DemoResource() {
    }

    /**
     * Retrieves representation of an instance of com.maximapps.rest1.DemoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "[]";
        
    }
    @Path("numbers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
        return "[123,34]";
        
    }
    
    @Path("object")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson3() {
        Country c = new Country("Denmark","Germany");
        String jsonString = GSON.toJson(c);
        return jsonString;
        
    }

}
