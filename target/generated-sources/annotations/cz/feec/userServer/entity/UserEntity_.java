package cz.feec.userServer.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ {

	public static volatile SingularAttribute<UserEntity, char[]> password;
	public static volatile SingularAttribute<UserEntity, String> workPosition;
	public static volatile SingularAttribute<UserEntity, AddressEntity> address;
	public static volatile SetAttribute<UserEntity, DataEntity> data;
	public static volatile SetAttribute<UserEntity, RoleEntity> roles;
	public static volatile SingularAttribute<UserEntity, Long> id;
	public static volatile SingularAttribute<UserEntity, String> email;
	public static volatile SingularAttribute<UserEntity, Integer> age;

}

