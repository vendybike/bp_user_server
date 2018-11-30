/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.soap;

import cz.feec.userServer.ejb.ServiceBean;
import cz.feec.userServer.entity.AddressEntity;
import cz.feec.userServer.entity.DataEntity;
import cz.feec.userServer.entity.UserEntity;
import cz.feec.userServer.exceptions.ResourceExceptions;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author vendy
 */
@WebService
public class userService {

    @Inject
    private ServiceBean service;

    @WebMethod(operationName = "createUser")
    public String setNewUser(UserEntity user) throws ResourceExceptions, NoSuchAlgorithmException {

        try {
            return service.createNewUser(user);
        } catch (ResourceExceptions.BadResourceExeption e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
        } catch (ResourceExceptions.ResourceAlreadyExistException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        }
    }

    @WebMethod(operationName = "deleteUser")
    public String deleteUser(long userId) throws ResourceExceptions {
        try {
            return service.deleteUser(userId);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @WebMethod(operationName = "updateUser")
    public void updateUser(long id, UserEntity newUser) throws ResourceExceptions {

        newUser.setId(id);
        try {
            service.updateUser(newUser);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (ResourceExceptions.ResourceAlreadyExistException e) {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
    }

    @WebMethod(operationName = "getUsers")
    public List<UserEntity> getUsers() {
        return service.getUsers();
    }

    @WebMethod(operationName = "getUser")
    public UserEntity getUser(long userId) throws ResourceExceptions {
        try {
            return service.getUserById(userId);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @WebMethod(operationName = "getUserAddress")
    public AddressEntity getUserAddress(long userId) throws ResourceExceptions {
        try {
            return service.getUserById(userId).getAddress();
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @WebMethod(operationName = "getUserDates")
    public Set<DataEntity> getUserData(long userId) throws ResourceExceptions {
        try {
            return service.getUserById(userId).getData();
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
        }
    }

    @WebMethod(operationName = "getDates")
    public List<DataEntity> getDates() {
        return service.getDates();
    }

    @WebMethod(operationName = "getData")
    public DataEntity getData(long dataId) throws ResourceExceptions.ResourceNotFoundException {
        return service.getData(dataId);
    }

    @WebMethod(operationName = "createData")
    public String addData(@PathParam("id") long id, DataEntity data) throws ResourceExceptions {
        try {
            service.addUserData(id, data);
        } catch (ResourceExceptions.ResourceNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return "Data OK";
    }
    

}
