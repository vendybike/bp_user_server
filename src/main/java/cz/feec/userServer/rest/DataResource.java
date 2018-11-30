/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.rest;

import cz.feec.userServer.ejb.ServiceBean;
import cz.feec.userServer.entity.DataEntity;
import cz.feec.userServer.exceptions.ResourceExceptions;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author vendy
 */
@Path("Dates")
@Produces("application/json")
@Consumes("application/json")
public class DataResource {

    @Inject
    ServiceBean service;

    @POST
    @Path("{id}")
    public String addData(@PathParam("id") long id, DataEntity data) throws ResourceExceptions {
        try {
            service.addUserData(id, data);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return "Data OK";
    }

    @DELETE
    @Path("{id}")
    public DataEntity deleteData(@PathParam("id") long id) throws ResourceExceptions {
        try {
            return service.deleteDataById(id);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @GET
    @Path("{id}")
    public DataEntity getData(@PathParam("id") long dataId) throws ResourceExceptions.ResourceNotFoundException{
        return service.getData(dataId);
    }
}
