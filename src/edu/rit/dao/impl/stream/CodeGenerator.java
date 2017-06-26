package edu.rit.dao.impl.stream;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

public class CodeGenerator {
	// TODO MJCG Libraries for generating java code: CodeModel, AST or JavaPoet

	/**
	 * Temporary method created only for testing
	 * @param name
	 * @param methodContent
	 * @param returnType
	 * @param lists
	 * @return
	 */
	//TODO MJCG remove
	public MethodSpec createMethod2(String name, String methodContent, Class<?> returnType, List<?>... lists) {
		// creating parameter List<Map<String,Object>
		TypeName object = ClassName.get(Object.class);
		TypeName string = ClassName.get(String.class);
		TypeName mapOfStringAndObject = ParameterizedTypeName.get(ClassName.get(Map.class), string, object);
		ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(ClassName.get(List.class),
				mapOfStringAndObject);
		ParameterSpec parameterSpec = ParameterSpec.builder(parameterizedTypeName, "professors", Modifier.FINAL)
				.build();

		// creating method
		MethodSpec.Builder mb = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).returns(void.class);
		mb.addParameter(parameterSpec);
		mb.addStatement(methodContent);
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
	public List<Map<String, Object>> objectToMaps(List<?> objs) {
		List<Map<String, Object>> schema = new ArrayList<>();
		for (Object bean : objs) {
			Map<String, Object> tmpTable = beanProperties(bean);
			if (tmpTable != null) {
				schema.add(tmpTable);
			}
		}
		return schema;
	}

	/**
	 * Creates the class.
	 *
	 * @param packageName
	 *            the package name
	 * @param name
	 *            the class name
	 * @param methods
	 *            the methods class
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void createClass(String packageName, String name, MethodSpec... methods) throws IOException {
		TypeSpec.Builder streamClass = TypeSpec.classBuilder(name).addModifiers(Modifier.PUBLIC);
		for (MethodSpec m : methods) {
			streamClass.addMethod(m);
		}
		JavaFile javaFile = JavaFile.builder(packageName, streamClass.build()).build();
		javaFile.writeTo(new File("."));
	}
	
	/**
	 * Bean properties. Extract all the attributes names and their values from
	 * the bean and store them in a Map<String, Object> 
	 * String = attribute name 
	 * Object = attribute value
	 *
	 * @param bean
	 *            the bean
	 * @return the map
	 */
	private Map<String, Object> beanProperties(Object bean) {
		try {
			Map<String, Object> map = new HashMap<>();
			Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors()).stream()
					// filter out properties with setters only
					.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> {
						try {
							Object value = pd.getReadMethod().invoke(bean);
							// if (value != null) {
							map.put(pd.getName(), value);
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

	/*
	 * public JDefinedClass createClass(JCodeModel codeModel, String
	 * packageName, String name) {
	 * 
	 * JPackage jp = codeModel._package(packageName); JDefinedClass jc; try { jc
	 * = jp._class(name); } catch (JClassAlreadyExistsException e) {
	 * System.err.println(e.getMessage()); return null; } return jc; }
	 * 
	 * public void buildCode(JCodeModel codeModel) { try { codeModel.build(new
	 * File(".")); } catch (IOException e) { System.err.println(e.getMessage());
	 * } }
	 * 
	 * public void createMethod(String methodName, String bodyContent, Class<?>
	 * returnType, JDefinedClass jc) { JMethod m = jc.method(JMod.PUBLIC,
	 * returnType, methodName); JBlock block = m.body();
	 * block._return(JExpr.lit(bodyContent)); }
	 */
}
