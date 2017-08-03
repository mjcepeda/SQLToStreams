package edu.rit.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import edu.rit.dao.impl.store.access.UserDTO;

public class Utils {
	
	final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	final static java.util.Random rand = new java.util.Random();

	final static Set<String> identifiers = new HashSet<String>();

	public static String randomIdentifier(String name) {
		 StringBuilder builder = new StringBuilder(name);
		 builder.append(randomIdentifier());
		 return builder.toString();
	}
		
	/**
	 * Object to maps. Transform user objects to Map<String, Object> String =
	 * attribute name Object = attribute value
	 *
	 * @param objs
	 *            the objs
	 * @return the list
	 */
	public static List<Map<String, Object>> objectToMaps(List<?> objs) {
		List<Map<String, Object>> schema = new ArrayList<>();
		for (Object bean : objs) {
			Map<String, Object> tmpTable = beanProperties(bean);
			if (tmpTable != null) {
				schema.add(tmpTable);
			}
		}
		return schema;
	}

	public static Map<String, Map<String, String>> schemaDescriptor(List<UserDTO> userObjs) {
		Map<String, Map<String, String>> schema = new HashMap<>();
		for (UserDTO bean : userObjs) {
			Map<String, String> tableDesc = tableDescriptor(bean.getDto());
			if (tableDesc != null) {
				schema.put(bean.getName(), tableDesc);
			}
		}
		return schema;
	}

	/**
	 * Bean properties. Extract all the attributes names and their values from
	 * the bean and store them in a Map<String, Object> String = attribute name
	 * Object = attribute value
	 *
	 * @param bean
	 *            the bean
	 * @return the map
	 */
	public static Map<String, Object> beanProperties(Object bean) {
		try {
			Map<String, Object> map = new HashMap<>();
			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> {
						try {
							Object value = pd.getReadMethod().invoke(bean);
							map.put(pd.getName(), value);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
			return map;
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return Collections.emptyMap();
		}
	}

	public static Map<String, String> tableDescriptor(Object bean) {
		try {
			Map<String, String> map = new HashMap<>();
			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> {
						try {
							Object value = pd.getReadMethod().invoke(bean);
							String typeVar = "varchar(100)";
							//TODO MJCG Include date type?
							//TODO MJCG Error when data type is BigDecimal
							//TODO MJCG All numeric data cannot be defined as int, they must float or double
							if (Number.class.isInstance(value)) {
								typeVar = "numeric";
							}
//							value instanceof java.math.BigDecimal
//							typeVar = "numeric";
							map.put(pd.getName(), typeVar);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
			return map;
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return Collections.emptyMap();
		}
	}
	
	public static Field getField(Class<?> cl, String fieldName) {
		Field field = null;
		try {
			field = cl.getDeclaredField(fieldName);
			field.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return field;
	}
	
	private static String randomIdentifier() {
	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(5)+5;
	        for(int i = 0; i < length; i++) {
	            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
	        }
	        if(identifiers.contains(builder.toString())) {
	            builder = new StringBuilder();
	        }
	    }
	    return builder.toString();
	}

}
