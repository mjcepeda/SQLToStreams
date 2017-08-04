package edu.rit.dao.impl.stream;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import edu.rit.dao.impl.store.access.UserDTO;

/**
 * The Class CodeGenerator.
 */
public class CodeGenerator {

	/**
	 * Creates the method.
	 *
	 * @param name the name
	 * @param stmts the stmts
	 * @param params the params
	 * @param returnParam the return param
	 * @return the method spec
	 */
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
		//inserting the stream operations into the method
		for (String stmt : stmts) {
			mb.addStatement(stmt);
		}
		MethodSpec m = mb.build();
		return m;
	}

	/**
	 * User DTO to map.
	 *
	 * @param dtoName the dto name
	 * @return the method spec
	 */
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
	 * Map to user DTO.
	 *
	 * @param dtoName the dto name
	 * @return the method spec
	 */
	public MethodSpec mapToUserDTO(String dtoName) {
		MethodSpec m = null;
		// extracting the package
		int i = dtoName.lastIndexOf(".");
		String pck = dtoName.substring(0, i > -1 ? i : 0);
		String simpleName = dtoName.substring(dtoName.lastIndexOf(".") + 1).toLowerCase();
		// building input type Stream<Map<String,Object>>
		TypeName object = ClassName.get(Object.class);
		TypeName string = ClassName.get(String.class);
		TypeName mapOfStringAndObject = ParameterizedTypeName.get(ClassName.get(Map.class), string, object);
		ParameterizedTypeName inputTypeName = ParameterizedTypeName.get(ClassName.get(Stream.class),
				mapOfStringAndObject);

		try {
			// building return type List<UserDTO>
			Class c = Class.forName(dtoName);
			TypeName paramType = ClassName.get(c);
			Object bean = c.newInstance();
			ParameterizedTypeName returnList = ParameterizedTypeName.get(ClassName.get(List.class), paramType);

			// defining method signature
			MethodSpec.Builder mb = MethodSpec.methodBuilder("mapTo" + simpleName).addModifiers(Modifier.PRIVATE)
					.returns(returnList);

			ParameterSpec parameterSpec = ParameterSpec.builder(inputTypeName, simpleName, Modifier.FINAL).build();
			mb.addParameter(parameterSpec);
			// adding the statements into the method
			mb.addStatement("List<" + dtoName + "> userData = new java.util.ArrayList<>()");
			mb.addStatement(simpleName + ".forEach(m -> { " + dtoName + " bean = new " + dtoName + "()");
			String stmts = mapToDTO(bean);
			mb.addStatement(stmts + " userData.add(bean); }); return userData");
			// building the method
			m = mb.build();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return m;
	}

	/**
	 * Creates the class.
	 *
	 * @param packageName the package name
	 * @param name the name
	 * @param methods the methods
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void createClass(String packageName, String name, List<MethodSpec> methods) throws IOException {
		TypeSpec.Builder streamClass = TypeSpec.classBuilder(name).addModifiers(Modifier.PUBLIC);
		for (MethodSpec m : methods) {
			streamClass.addMethod(m);
		}
		JavaFile javaFile = JavaFile.builder(packageName, streamClass.build()).skipJavaLangImports(true).build();
		javaFile.writeTo(new File("./src"));
	}

	/**
	 * Bean properties. Extract all the attributes names and their values from
	 * the bean and store them in a Map<String, Object> String = attribute name
	 * Object = attribute value
	 *
	 * @param bean            the bean
	 * @param mb the mb
	 * @param name the name
	 * @return the map
	 */
	private void beanProperties(Object bean, MethodSpec.Builder mb, String name) {
		try {
			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> {
						try {
							Object value = pd.getReadMethod().invoke(bean);
							// if (value != null) {
							// map.put(pd.getName(), value);
							mb.addStatement(
									"m.put(\"" + pd.getName() + "\", bean." + pd.getReadMethod().getName() + "())");
							// }
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					});
		} catch (IntrospectionException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Map to DTO.
	 *
	 * @param bean the bean
	 * @return the string
	 */
	private String mapToDTO(Object bean) {
		StringBuilder sb = new StringBuilder();
		try {
			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with getters only
					.filter(pd -> Objects.nonNull(pd.getWriteMethod())).forEach(pd -> {
						try {
							// Object value = pd.getWriteMethod().invoke(bean);
							Class<?> classType = pd.getPropertyType();

							sb.append("if (m.containsKey(\"" + pd.getName() + "\")) { bean."
									+ pd.getWriteMethod().getName() + "((" + classType.getSimpleName() + ") m.get(\""
									+ pd.getName() + "\"));}");
							// }
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					});
		} catch (IntrospectionException e) {
			System.err.println(e.getMessage());
		}
		return sb.toString();
	}
}
