/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author vendy
 */
@Entity
@Table(name = "\"user\"")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @NotNull(message = "Email don't be null")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Invalid email")  
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "Password don't be null")
    @Column(name = "salted_password", nullable = false)
    private char[] password;
    
    @Column(name = "age")
    private int age;
    
    @NotNull(message = "Work position don't be null")
    @Column(name = "work_position", nullable = false)
    private String workPosition;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private AddressEntity address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<DataEntity> data = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<DataEntity> getData() {
        return this.data;
    }

    public void setData(HashSet<DataEntity> data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        String password = new String(this.password);
        return password;
    }

    public void setPassword(String password) {
        this.password =  password.toCharArray();
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserEntity)) {
            return false;
        }
        final UserEntity other = (UserEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "id=" + id + ", email=" + email + ", age=" + age + ", saltedPassword=" + password + ", workPosition=" + workPosition + ", address=" + address + ", data=" + data + ", roles=" + roles + '}';
    }

    public void setLazyLoadedField(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
