/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.ejb;

import cz.feec.userServer.entity.AddressEntity;
import cz.feec.userServer.entity.DataEntity;
import cz.feec.userServer.entity.UserEntity;
import cz.feec.userServer.exceptions.ResourceExceptions;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vendy
 */
@LocalBean
@Stateless
public class ServiceBean {

    @Inject
    UserBean userBean;

    @Inject
    AddressBean addressBean;

    @Inject
    DataBean dataBean;

    @Inject
    RoleBean roleBean;

    public List<UserEntity> getUsers() {
        return userBean.getAll();
    }

    public UserEntity getUserById(long id) throws ResourceExceptions {
        return userBean.getUserById(id);
    }
    
    public UserEntity getUserByEmail(String email) throws ResourceExceptions.ResourceNotFoundException{
        UserEntity user = userBean.getUserByEmail(email);
        if(userBean == null) throw new ResourceExceptions.ResourceNotFoundException("Uživatel nenalezen");
        return user;
    }
    

    public String createNewUser(UserEntity user) throws ResourceExceptions, NoSuchAlgorithmException {

        UserEntity newUser;

        if ((user.getAddress()) != null) {
            user = setUserAddress(user);
        }

        newUser = userBean.createNewUser(user);
 
        return newUser.getEmail();
    }

    public String deleteUser(long id) throws ResourceExceptions {

        UserEntity user = getUserById(id);
        AddressEntity userAddress = user.getAddress();
        userBean.deleteUser(id);
        String response = user.getEmail();

        response += addressCleaner(userAddress);

        return response;
    }

    public String updateUser(UserEntity newUser) throws ResourceExceptions {
        AddressEntity oldAddress = null;
       
        String email = getUserById(newUser.getId()).getEmail();
        String email2 = newUser.getEmail();
        System.out.println(email + " = " + email2);
        
        if (!getUserById(newUser.getId()).getEmail().equals(newUser.getEmail())) {
            if (!userBean.validateNewUser(newUser)) {
                throw new ResourceExceptions.ResourceAlreadyExistException("Email already exist");
            }
        }
        
        

        if ((newUser.getAddress()) != null) {
            oldAddress = userBean.getUserById(newUser.getId()).getAddress();

            newUser = setUserAddress(newUser);
        }
        userBean.updateUser(newUser);

        if (!(newUser.getAddress() == oldAddress) && oldAddress != null) {
            addressCleaner(oldAddress);
        }

        return newUser.getEmail();
    }

    private UserEntity setUserAddress(UserEntity user) throws ResourceExceptions {

        AddressEntity address = addressBean.getExistAddress(user.getAddress());
        if (address == null) {
            address = addressBean.createNewAddress(user.getAddress());
        }
        user.setAddress(address);
        return user;
    }

    private String addressCleaner(AddressEntity address) throws ResourceExceptions {

        if ((address.getUsers().size() - 1) <= 0) {
            addressBean.deleteAddress(address.getId());
            return ", " + address.toString();
        }
        return "";
    }

    public void addUserData(long id, DataEntity newData) throws ResourceExceptions {
        UserEntity user = getUserById(id);
        
        newData.setUser(user);
        System.out.println(newData.toString());
        dataBean.createNewData(newData);
    }

    public void addUserDataSet(long id, Set<DataEntity> newDataSet) throws ResourceExceptions {
        UserEntity user = getUserById(id);
        for (DataEntity newData : newDataSet) {
            newData.setUser(user);
            dataBean.createNewData(newData);
        }
    }
    
    public DataEntity deleteDataById(long id) throws ResourceExceptions{
       return dataBean.deleteData(id);
    }
    
    public DataEntity getDataByUser(long userId, long dataId) throws ResourceExceptions{
        Set<DataEntity> dataSet = getUserById(userId).getData();
        for(DataEntity data : dataSet){
            if(data.getId() == dataId){
                return data;
            }
        }
        return null;
    }
    
    public List<DataEntity> getDates(){
        return dataBean.getDates();
    }
    
    public DataEntity getData(long dataId) throws ResourceExceptions.ResourceNotFoundException{
        return dataBean.getData(dataId);
    }

}
