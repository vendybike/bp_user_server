/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.ejb;

import cz.feec.userServer.entity.AddressEntity;
import cz.feec.userServer.exceptions.ResourceExceptions;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vendy
 */
@LocalBean
@Stateless
public class AddressBean {

    @PersistenceContext
    private EntityManager em;

    public AddressEntity createNewAddress(AddressEntity address) throws ResourceExceptions {
        try {
            em.persist(address);
        } catch (Exception e) {
            throw new ResourceExceptions.BadResourceExeption();
        }   
        return address;
    }

    public AddressEntity getExistAddress(AddressEntity address) {
        try {
            address = em.createQuery("SELECT address FROM AddressEntity address WHERE (address.city = :city) AND (address.country = :country) AND (address.street = :street) and (address.houseNumber = :houseNumber)", AddressEntity.class)
                    .setParameter("city", address.getCity())
                    .setParameter("country", address.getCountry())
                    .setParameter("street", address.getStreet())
                    .setParameter("houseNumber", address.getHouseNumber())
                    .getSingleResult();
            return address;
        } catch (NoResultException e) {
            return null;
        } 

    }

    public AddressEntity deleteAddress(long id) throws ResourceExceptions {

        AddressEntity address = em.find(AddressEntity.class, id);
        if (address == null) {
            throw new ResourceExceptions();
        }

        em.remove(address);

        return address;
    }

}
