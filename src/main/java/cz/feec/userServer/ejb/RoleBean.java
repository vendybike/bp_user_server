/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.ejb;

import cz.feec.userServer.entity.RoleEntity;
import cz.feec.userServer.exceptions.ResourceExceptions;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vendy
 */
@Stateless
@LocalBean
public class RoleBean {

    @PersistenceContext
    EntityManager em;

    public List<RoleEntity> getRoles() {
        return em.createQuery("SELECT roles FROM RoleEntity roles ", RoleEntity.class)
                .getResultList();
    }

    public RoleEntity setRole(RoleEntity newRole) throws ResourceExceptions {
        try {
            em.persist(newRole);
        } catch (Exception e) {
            throw new ResourceExceptions();
        }
        return newRole;
    }

    public RoleEntity getRoleByName(String roleName) {
        try {
            return em.createQuery("SELECT role FROM RoleEntity role WHERE role.roleName:roleName", RoleEntity.class)
                    .setParameter("roleName", roleName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
