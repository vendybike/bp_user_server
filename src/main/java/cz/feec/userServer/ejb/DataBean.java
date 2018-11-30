/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.ejb;

import cz.feec.userServer.entity.DataEntity;
import cz.feec.userServer.exceptions.ResourceExceptions;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vendy
 */
@LocalBean
@Stateless
public class DataBean {

    @PersistenceContext
    EntityManager em;

    public DataEntity createNewData(DataEntity newData) throws ResourceExceptions {
        try {
            em.persist(newData);
        } catch (Exception e) {
            throw new ResourceExceptions();
        }
        return newData;
    }

    public DataEntity deleteData(long id) throws ResourceExceptions {

        DataEntity data = em.find(DataEntity.class, id);
        if (data == null) {
            throw new ResourceExceptions.ResourceNotFoundException();
        }
        em.remove(data);
        return data;
    }

    public List<DataEntity> getDates() {
        return em.createQuery("SELECT data FROM DataEntity", DataEntity.class).getResultList();
    }

    public DataEntity getData(long dataId) throws ResourceExceptions.ResourceNotFoundException {

        DataEntity data = em.find(DataEntity.class, dataId);
        if (data == null) {
            throw new ResourceExceptions.ResourceNotFoundException();
        }
        return data;
    }

}
