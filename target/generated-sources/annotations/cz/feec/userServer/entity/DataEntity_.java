package cz.feec.userServer.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DataEntity.class)
public abstract class DataEntity_ {

	public static volatile SingularAttribute<DataEntity, Double> stress;
	public static volatile SingularAttribute<DataEntity, Double> fat;
	public static volatile SingularAttribute<DataEntity, Double> pulse;
	public static volatile SingularAttribute<DataEntity, Double> weight;
	public static volatile SingularAttribute<DataEntity, Long> id;
	public static volatile SingularAttribute<DataEntity, UserEntity> user;

}

