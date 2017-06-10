package com.scm.locking;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.OptimisticLockException;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


@Component
public class OptimisticLockChecker<T> {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    public void check(T entity) {
    	Field fieldVersion = null;
    	Integer submittedVersion=null;
    	Object version =null;
    	
    	Field fieldId = null;
    	String submittedId=null;
    	Object id =null;
    	
    	try {
			fieldVersion = entity.getClass().getDeclaredField("version");
			fieldVersion.setAccessible(true);
			version = fieldVersion.get(entity);
			submittedVersion = (Integer) version;
			
			fieldId = entity.getClass().getDeclaredField("id");
			fieldId.setAccessible(true);
			id = fieldId.get(entity);
			submittedId = (String) id;
			
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      
        if (submittedVersion == null) {
            throw new RuntimeException("Submitted entity must have a version");
        }

        Class<?> entityClass = entity.getClass();

        Annotation tableAnn = AnnotationUtils.findAnnotation(entityClass, Table.class);
        String tableName = (String) AnnotationUtils.getValue(tableAnn, "name");

        Field idField = ReflectionUtils.findField(entityClass, "id");
        Annotation idColAnn = idField.getAnnotation(Column.class);
        String idColName = (String) AnnotationUtils.getValue(idColAnn, "name");

        String sql = "select version from " + tableName
                + " where " + idColName + "=" + id;
            Integer latestVersion = jdbcTemplate.queryForObject(sql, Integer.class);

            if (submittedVersion != latestVersion) {
                throw new OptimisticLockException(
                        "Stale entity: submitted version " + submittedVersion
                        + ", but latest version is " + latestVersion);
            }

         

        
    }
}