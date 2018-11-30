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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vendy
 */
@Entity
@Table(name = "address")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private long id;

    @NotNull(message = "Country don't be null")
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull(message = "City don't be null")
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "region")
    private String region;

    @NotNull(message = "Street don't be null")
    @Column(name = "street", nullable = false)
    private String street;

    @NotNull(message = "House number don't be null")
    @Column(name = "house_number", nullable = false)
    private int houseNumber;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Set<UserEntity> users;

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public Set<Long> getUsers() {
        Set<Long> userIds = new HashSet<Long>();
        for (UserEntity user : users) {
            userIds.add(user.getId());
        }
        return userIds;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        if (obj instanceof AddressEntity) {
            return false;
        }
        final AddressEntity other = (AddressEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddressEntity{" + "id=" + id + ", country=" + country + ", city=" + city + ", region=" + region + ", street=" + street + ", houseNumber=" + houseNumber + "}'";
    }

}
