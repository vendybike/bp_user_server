/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.ejb;

import cz.feec.userServer.entity.UserEntity;
import cz.feec.userServer.exceptions.ResourceExceptions;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

/**
 *
 * @author vendy
 */
@LocalBean
@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public List<UserEntity> getAll() {

        em.flush();
        List<UserEntity> list = em.createQuery("SELECT users FROM UserEntity users LEFT JOIN FETCH users.address LEFT JOIN FETCH users.roles LEFT JOIN FETCH users.data", UserEntity.class)
                .getResultList();
        em.flush();
        for (UserEntity user : list) {
            System.out.println(user.toString());
        }
        return list;
    }

    public UserEntity getUserById(long id) throws ResourceExceptions {

        UserEntity user = em.find(UserEntity.class, id);
        if (user == null) {
            throw new ResourceExceptions.ResourceNotFoundException();
        }
        return user;

    }

    public UserEntity deleteUser(long id) throws ResourceExceptions {

        UserEntity user = em.find(UserEntity.class, id);

        if (user == null) {
            throw new ResourceExceptions();
        }

        em.remove(user);
        return user;

    }

    public UserEntity createNewUser(UserEntity newUser) throws ResourceExceptions, NoSuchAlgorithmException {

        newUser.setPassword(sha1(newUser.getPassword() + newUser.getEmail()));

        try {
            em.persist(newUser);
        } catch (ValidationException e) {
            throw new ResourceExceptions.BadResourceExeption(e.getMessage());
        } catch (Exception e) {
            throw new ResourceExceptions.ResourceAlreadyExistException("Resource already exist");
        }

        return newUser;
    }

    public UserEntity updateUser(UserEntity newUser) throws ResourceExceptions {

        UserEntity user = em.find(UserEntity.class, newUser.getId());

        if (user == null) {
            throw new ResourceExceptions.ResourceNotFoundException();
        }
        try {
            em.merge(newUser);
        } catch (EJBTransactionRolledbackException e) {
            throw new ResourceExceptions.ResourceAlreadyExistException();
        }

        return newUser;

    }

    public UserEntity getUserByEmail(String userEmail) {
        UserEntity user;
        try {
            user = em.createQuery("SELECT user FROM UserEntity user WHERE (user.email = :email)", UserEntity.class)
                    .setParameter("email", userEmail)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }

    public boolean validateNewUser(UserEntity user) {

        try {
            UserEntity newUser = em.createQuery("SELECT user FROM UserEntity user WHERE (user.email = :email)", UserEntity.class)
                    .setParameter("email", user.getEmail())
                    .getSingleResult();
        } catch (NoResultException e) {
            return true;
        }
        return false;
    }

    private String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
