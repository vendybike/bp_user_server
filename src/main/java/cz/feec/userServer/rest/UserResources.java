/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.rest;

import cz.feec.userServer.ejb.ServiceBean;
import cz.feec.userServer.entity.AddressEntity;
import cz.feec.userServer.entity.DataEntity;
import cz.feec.userServer.entity.UserEntity;
import cz.feec.userServer.exceptions.ResourceExceptions;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author vendy
 */
@Path("users")
@Produces("application/json")
@Consumes("application/json")
public class UserResources {

    @Inject
    private ServiceBean service;

    
    @GET
    public List<UserEntity> getUsers() {
        return service.getUsers();
    }
    

    @GET
    @Path("{id}")
    public UserEntity getUserByUserId(@PathParam("id") long userId) throws ResourceExceptions {
        try {
            return service.getUserById(userId);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }
    
    @GET
    @Path("/search")
    public UserEntity getUserByUserEmail(@QueryParam("email") String userEmail) throws ResourceExceptions.ResourceNotFoundException{
        try{
            return service.getUserByEmail(userEmail);
        }catch(ResourceExceptions.ResourceNotFoundException e){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("/{id}/address")
    public AddressEntity getUserAddress(@PathParam("id") long userId) throws ResourceExceptions {
        try {
            return service.getUserById(userId).getAddress();
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("/{id}/data")
    public Set<DataEntity> getUserData(@PathParam("id") long userId) throws ResourceExceptions {
        try {
            return service.getUserById(userId).getData();
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("/{id}/data/{dataId}")
    public DataEntity getUserDataByUserId(@PathParam("id") long userId, @PathParam("dataId") long dataId) throws ResourceExceptions {
        DataEntity data;
        try {
            data = service.getDataByUser(userId, dataId);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
        if (data != null) {
            return data;
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    

    @POST
    public String setNewUser(UserEntity user) throws ResourceExceptions, NoSuchAlgorithmException {

        try {
            return service.createNewUser(user);
        } catch (ResourceExceptions.BadResourceExeption e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
        } catch (ResourceExceptions.ResourceAlreadyExistException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }
    }

    @DELETE
    @Path("{id}")
    public String deleteUser(@PathParam("id") long userId) throws ResourceExceptions {
        try {
            return service.deleteUser(userId);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }

    }

    @PUT
    @Path("{id}")
    public void updateUser(@PathParam("id") long id, UserEntity newUser) throws ResourceExceptions {

        newUser.setId(id);
        try {
            service.updateUser(newUser);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (ResourceExceptions.ResourceAlreadyExistException e) {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }

    }
}
