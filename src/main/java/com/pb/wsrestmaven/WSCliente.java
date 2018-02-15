/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.wsrestmaven;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author pedro
 */
@Path("/wscliente")
public class WSCliente {

    @Path("/teste/{teste}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String teste(@PathParam("teste") String teste){
        return teste;
    }
    
}
