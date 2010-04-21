package com.jigsforjava.persistence.eclipselink;

import org.eclipse.persistence.descriptors.RelationalDescriptor;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectToFieldMapping;
import org.eclipse.persistence.mappings.OneToManyMapping;
import org.eclipse.persistence.mappings.OneToOneMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.mappings.converters.ObjectTypeConverter;

@SuppressWarnings("serial")
public abstract class AbstractEclipseLinkDescriptor
extends RelationalDescriptor
{
	// SECTION: CONSTRUCTORS

	public AbstractEclipseLinkDescriptor()
	{
		super();
		configure();
	}


	// SECTION: CONFIGURATION

	protected void configure()
	{
		configureBasics();
		configureCachingPolicy();
		configureLockingPolicy();
		configureQueryManager();
		configureEventManager();
		configureMappings();
		configureCacheSynchronization();
	}

	protected void configureBasics()
	{
	}

	protected void configureCachingPolicy()
	{
	}

	protected void configureLockingPolicy()
	{
	}

	protected void configureQueryManager()
	{
	}

	protected void configureEventManager()
	{
	}

	protected void configureMappings()
	{
	}

	protected void configureCacheSynchronization()
	{
	}


	// SECTION: CONVENIENCE
	
	protected DirectToFieldMapping addDirectToFieldMapping(
		String attributeName, String databaseFieldName)
	{
		DirectToFieldMapping mapping = newDirectToFieldMapping(attributeName,databaseFieldName);
		addMapping(mapping);
		return mapping;
	}

	protected DatabaseMapping addReadOnlyDirectMapping(String attributeName,
	    String fieldName)
	{
		DatabaseMapping mapping = addDirectMapping(attributeName, fieldName);
		mapping.readOnly();
		return mapping;
	}

	protected DirectToFieldMapping addDirectToFieldConversionMapping(
	    String attributeName, String databaseFieldName, Converter converter)
	{
		DirectToFieldMapping mapping = newDirectToFieldMapping(attributeName,
		    databaseFieldName);
		mapping.setConverter(converter);
		addMapping(mapping);
		return mapping;
	}

	protected OneToOneMapping addForeignKeyOneToOneMapping(
	    String attributeName, Class<?> targetClass, String foreignKeyFieldName,
	    String targetPrimaryKeyFieldName)
	{
		OneToOneMapping mapping = newForeignKeyOneToOneMapping(attributeName,
		    targetClass, foreignKeyFieldName, targetPrimaryKeyFieldName);
		addMapping(mapping);
		return mapping;
	}
	
	protected OneToManyMapping addForeignKeyOneToManyMapping(
	    String attributeName, Class<?> targetClass, String foreignKeyFieldName,
	    String targetPrimaryKeyFieldName)
	{
		OneToManyMapping mapping = newForeignKeyOneToManyMapping(attributeName,
			                                                     targetClass,
			                                                     foreignKeyFieldName,
			                                                     targetPrimaryKeyFieldName);
		mapping.useTransparentList();
		addMapping(mapping);
		return mapping;
	}

	protected DirectToFieldMapping addYesNoBooleanConversionMapping(
	    String attributeName, String databaseFieldName)
	{
		return addDirectToFieldConversionMapping(attributeName,
		    databaseFieldName, newYesNoConverter());
	}

	protected DirectToFieldMapping addOneZeroBooleanConversionMapping(
	    String attributeName, String databaseFieldName)
	{
		return addDirectToFieldConversionMapping(attributeName,
		    databaseFieldName, newOneZeroConverter());
	}

	protected DirectToFieldMapping newDirectToFieldMapping(
	    String attributeName, String databaseFieldName)
	{
		DirectToFieldMapping mapping = new DirectToFieldMapping();
		mapping.setAttributeName(attributeName);
		mapping.setFieldName(databaseFieldName);
		return mapping;
	}

	protected OneToOneMapping newForeignKeyOneToOneMapping(
	    String attributeName, Class<?> targetClass, String foriegnKeyFieldName,
	    String targetPrimaryKeyFieldName)
	{
		OneToOneMapping mapping = new OneToOneMapping();
		mapping.setAttributeName(attributeName);
		mapping.setReferenceClass(targetClass);
		mapping.addForeignKeyFieldName(foriegnKeyFieldName,
		    targetPrimaryKeyFieldName);
		mapping.useBasicIndirection();
		return mapping;
	}

	protected OneToManyMapping newForeignKeyOneToManyMapping(
	    String attributeName, Class<?> targetClass, String foreignKeyFieldName,
	    String targetPrimaryKeyFieldName)
	{
		OneToManyMapping mapping = new OneToManyMapping();
		mapping.setAttributeName(attributeName);
		mapping.setReferenceClass(targetClass);
		mapping.addTargetForeignKeyFieldName(foreignKeyFieldName,
		    targetPrimaryKeyFieldName);
		return mapping;
	}

	protected ObjectTypeConverter newOneZeroConverter()
	{
		ObjectTypeConverter converter = new ObjectTypeConverter();
		converter.setDefaultAttributeValue(Boolean.FALSE);
		converter.addConversionValue("0", Boolean.FALSE);
		converter.addConversionValue("1", Boolean.TRUE);
		return converter;
	}

	protected ObjectTypeConverter newYesNoConverter()
	{
		ObjectTypeConverter converter = new ObjectTypeConverter();
		converter.setDefaultAttributeValue(Boolean.FALSE);
		converter.addConversionValue("", Boolean.FALSE);
		converter.addConversionValue("N", Boolean.FALSE);
		converter.addConversionValue("Y", Boolean.TRUE);
		return converter;
	}

	protected void useSendObjectChangesCacheSynchronization()
	{
		setCacheSynchronizationType(RelationalDescriptor.SEND_OBJECT_CHANGES);
	}

	protected void useInvalidateChangedObjectCacheSynchronization()
	{
		setCacheSynchronizationType(RelationalDescriptor.INVALIDATE_CHANGED_OBJECTS);
	}

	protected void useSendNewObjectsWithChangesCacheSynchronization()
	{
		setCacheSynchronizationType(RelationalDescriptor.SEND_NEW_OBJECTS_WITH_CHANGES);
	}

	protected void useDoNotSendChangesCacheSynchronization()
	{
		setCacheSynchronizationType(RelationalDescriptor.DO_NOT_SEND_CHANGES);
	}
}
