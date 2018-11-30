package cz.feec.userServer.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoleEntity.class)
public abstract class RoleEntity_ {

	public static volatile SingularAttribute<RoleEntity, String> roleName;
	public static volatile SingularAttribute<RoleEntity, Long> id;
	public static volatile SetAttribute<RoleEntity, UserEntity> users;

}

