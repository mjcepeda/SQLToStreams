package edu.rit.dao.impl.stream;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.lang.model.element.Modifier;

import org.apache.commons.collections.map.HashedMap;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;

import edu.rit.dao.impl.store.access.UserDTO;

public class CodeGenerator {
	// Libraries for generating java code: CodeModel, AST or JavaPoet

	public MethodSpec createMethod(String name, List<String> stmts, List<UserDTO> params, Object returnParam) {
		// creating parameter List<Map<String,Object>
		TypeName object = ClassName.get(Object.class);
		TypeName string = ClassName.get(String.class);
		TypeName returnType = ClassName.get(returnParam.getClass());
		TypeName mapOfStringAndObject = ParameterizedTypeName.get(ClassName.get(Map.class), string, object);
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(List.class),
				mapOfStringAndObject);
		// the return type is a List
		ParameterizedTypeName returnTypeName = ParameterizedTypeName.get(ClassName.get(List.class), returnType);

		// creating method
		MethodSpec.Builder mb = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).returns(returnTypeName);
		for (UserDTO param : params) {
			TypeName paramType = ClassName.get(param.getDto().getClass());
			// input parameters type is Collection<UserDTO>
			ParameterizedTypeName parameterizedList = ParameterizedTypeName.get(ClassName.get(Collection.class),
					paramType);
			ParameterSpec parameterSpec = ParameterSpec.builder(parameterizedList, param.getName(), Modifier.FINAL)
					.build();
			mb.addParameter(parameterSpec);
		}

		for (String stmt : stmts) {
			mb.addStatement(stmt);
		}

		MethodSpec m = mb.build();
		return m;
	}

	public MethodSpec userDTOtoMap(String dtoName) {
		// extracting the package
		int i = dtoName.lastIndexOf(".");
		String pck = dtoName.substring(0, i > -1 ? i : 0);
		String simpleName = dtoName.substring(dtoName.lastIndexOf(".") + 1).toLowerCase();
		// building return type List<Map<String,Object>>
		TypeName object = ClassName.get(Object.class);
		TypeName string = ClassName.get(String.class);
		TypeName mapOfStringAndObject = ParameterizedTypeName.get(ClassName.get(Map.class), string, object);
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(List.class),
				mapOfStringAndObject);
		// defining method signature
		MethodSpec.Builder mb = MethodSpec.methodBuilder(simpleName + "ToMap").addModifiers(Modifier.PRIVATE)
				.returns(parameterizedTypeName);
		try {
			// input parameters type is Collection<UserDTO>
			Class c = Class.forName(dtoName);
			TypeName paramType = ClassName.get(c);
			Object bean = c.newInstance();
			ParameterizedTypeName parameterizedList = ParameterizedTypeName.get(ClassName.get(Collection.class),
					paramType);
			ParameterSpec parameterSpec = ParameterSpec.builder(parameterizedList, simpleName, Modifier.FINAL).build();
			mb.addParameter(parameterSpec);
			// adding the statements into the method
			mb.addStatement("List<Map<String, Object>> tableData = new java.util.ArrayList<>()");
			mb.addStatement(simpleName + ".forEach(bean -> { Map<String, Object> m = new java.util.HashMap()");
			beanProperties(bean, mb, simpleName);
			mb.addStatement("tableData.add(m); }); return tableData");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		// building the method
		MethodSpec m = mb.build();
		return m;
	}

	/**
	 * Object to maps. Transform user objects to Map<String, Object> String =
	 * attribute name Object = attribute value
	 *
	 * @param objs
	 *            the objs
	 * @return the list
	 */
	// public List<Map<String, Object>> objectToMaps(List<?> objs) {
	// List<Map<String, Object>> schema = new ArrayList<>();
	// for (Object bean : objs) {
	// Map<String, Object> tmpTable = beanProperties(bean);
	// if (tmpTable != null) {
	// schema.add(tmpTable);
	// }
	// }
	// return schema;
	// }

	public void createClass(String packageName, String name, List<MethodSpec> methods) throws IOException {
		TypeSpec.Builder streamClass = TypeSpec.classBuilder(name).addModifiers(Modifier.PUBLIC);
		for (MethodSpec m : methods) {
			streamClass.addMethod(m);
		}
//		MethodSpec m = createMethodUserObjToMaps();
//		streamClass.addMethod(m);
//		MethodSpec methodBeanProperties = createMethodBeanProperties();
//		streamClass.addMethod(methodBeanProperties);
		JavaFile javaFile = JavaFile.builder(packageName, streamClass.build()).skipJavaLangImports(true).build();
		javaFile.writeTo(new File("./src"));
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
	private Map<String, Object> beanProperties(Object bean, MethodSpec.Builder mb, String name) {
		try {
			Map<String, Object> map = new HashMap<>();
			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> {
						try {
							Object value = pd.getReadMethod().invoke(bean);
							// if (value != null) {
							map.put(pd.getName(), value);
							mb.addStatement("m.put(\"" + pd.getName() + "\", bean." + pd.getReadMethod().getName() + "())");
							// }
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					});
			return map;
		} catch (IntrospectionException e) {
			System.err.println(e.getMessage());
			return Collections.emptyMap();
		}
	}

	private MethodSpec createMethodUserObjToMaps() {
		// building return type List<Map<String,Object>>
		TypeName object = ClassName.get(Object.class);
		TypeName string = ClassName.get(String.class);
		TypeName mapOfStringAndObject = ParameterizedTypeName.get(ClassName.get(Map.class), string, object);
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(List.class),
				mapOfStringAndObject);
		// defining method signature
		MethodSpec.Builder mb = MethodSpec.methodBuilder("userObjToMap").addModifiers(Modifier.PRIVATE)
				.returns(parameterizedTypeName);
		// input parameters type is Collection<UserDTO>
		ParameterizedTypeName parameterizedList = ParameterizedTypeName.get(ClassName.get(Collection.class),
				WildcardTypeName.subtypeOf(Object.class));
		ParameterSpec parameterSpec = ParameterSpec.builder(parameterizedList, "table", Modifier.FINAL).build();
		// adding the parameter
		mb.addParameter(parameterSpec);
		// adding the statements into the method
		mb.addStatement("List<Map<String, Object>> tableData = new java.util.ArrayList<>()");
		mb.addStatement(
				"for (Object bean : table) { Map<String, Object> row = beanProperties(bean); if (row != null) { tableData.add(row); } } return tableData");
		// building the method
		MethodSpec m = mb.build();
		return m;
	}

	private MethodSpec createMethodBeanProperties() {
		// building return type List<Map<String,Object>>
		TypeName object = ClassName.get(Object.class);
		TypeName string = ClassName.get(String.class);
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(Map.class), string,
				object);

		// defining method signature
		MethodSpec.Builder mb = MethodSpec.methodBuilder("beanProperties").addModifiers(Modifier.PRIVATE)
				.returns(parameterizedTypeName);
		// creating input parameter Object
		// ParameterizedTypeName parameterizedObject =
		// ParameterizedTypeName.OBJECT;
		ParameterSpec parameterSpec = ParameterSpec.builder(object, "bean", Modifier.FINAL).build();
		// adding the parameter
		mb.addParameter(parameterSpec);
		mb.addStatement("Map<String, Object> map = new java.util.HashMap<>(); "
				+ "try { java.util.Arrays.asList(java.beans.Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()"
				+ ".filter(pd -> java.util.Objects.nonNull(pd.getReadMethod())).forEach(pd -> {"
				+ "try { Object value = pd.getReadMethod().invoke(bean); map.put(pd.getName(), value); } catch (Exception e) { System.err.println(e.getMessage()); }})");
		mb.addStatement(
				"} catch (java.beans.IntrospectionException e) { System.err.println(e.getMessage()); return java.util.Collections.emptyMap(); } return map");
		// building the method
		MethodSpec m = mb.build();
		return m;
	}

}
