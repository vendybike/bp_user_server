package cz.feec.userServer.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AddressEntity.class)
public abstract class AddressEntity_ {

	public static volatile SingularAttribute<AddressEntity, String> country;
	public static volatile SingularAttribute<AddressEntity, String> city;
	public static volatile SingularAttribute<AddressEntity, String> street;
	public static volatile SingularAttribute<AddressEntity, Integer> houseNumber;
	public static volatile SingularAttribute<AddressEntity, Long> id;
	public static volatile SingularAttribute<AddressEntity, String> region;
	public static volatile SetAttribute<AddressEntity, UserEntity> users;

}

