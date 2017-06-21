package edu.rit.utils;

import java.lang.reflect.Field;

import org.apache.derby.iapi.services.io.FormatableProperties;
import org.apache.derby.iapi.sql.execute.ResultSetStatistics;

//TODO MJCG - Class in progress, everything define here may change in the future
/**
 *
 * @author Maria
 *
 */
public class ExecutionPlanParser {
	enum RESULTSETS {
	    RealTableScanStatistics,
	    ProjectRestrictResultSet;
	}
	
	public static String parser(ResultSetStatistics resultSetStatistics) {
		StringBuilder statement = new StringBuilder();
		//RealNoRowsResultSetStatistics implement ResulSetStatistics
		//but for delete, insert and update operations
		//NoPutResultSetImpl implement ResultSetStatistics
		//ScanResultSet
		//keep track of this class RealResultSetStatisticsFactory method getResultSetStatistics(NoPutResultSet rs)
		RESULTSETS rss = RESULTSETS.valueOf(resultSetStatistics.getClass().getSimpleName());
		switch (rss) {
			case RealTableScanStatistics:
				String tableName = (String)getProperty(rss, "tableName");
				//qualifiers tells you if there is a select operation
				//if there are several filters, all of them appear in this variable
				String qualifiers = (String)getProperty(rss, "qualifiers");
				if (!qualifiers.equals("None")) {
					//call select operation
				}
				//scanproperties tells you if there is a projection operation
				FormatableProperties scanProperties= (FormatableProperties) getProperty(rss, "scanProperties");
				
				Object columnsFetched = scanProperties.get("Bit set of columns fetched");
					if (columnsFetched.equals("All")) {
						
					}
				break;
			case ProjectRestrictResultSet:
				break;
		}
		
		return statement.toString();
	}

	
	public static Object getProperty(Object bean, String propertyName) {
		Object value = null;
		try {
			Field field = bean.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			value = field.get(bean);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		} catch (IllegalAccessException e) {
			System.err.println(e.getMessage());
		} catch (NoSuchFieldException e) {
			System.err.println(e.getMessage());
		} catch (SecurityException e) {
			System.err.println(e.getMessage());
		}
		return value;
	}
}
