package edu.rit.dao.impl.stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.squareup.javapoet.MethodSpec;

import adipe.translate.ra.Schema;
import edu.rit.dao.iapi.Database;
import edu.rit.dao.iapi.relational.RelationalAlgebra;
import edu.rit.dao.impl.DatabaseImpl;
import edu.rit.dao.impl.store.access.ClassDescriptor;
import edu.rit.dao.impl.store.access.MethodDescriptor;
import edu.rit.dao.impl.store.access.UserDTO;
import edu.rit.utils.Utils;

public class Translator {

	private static Database db = new DatabaseImpl();
	private static ExecutionPlanParser parser = new ExecutionPlanParser();
	private static CodeGenerator generator = new CodeGenerator();

	private static final String CLASSNAME = "class";
	private static final String METHOD = "method";
	private static final String PARAMS = "params";
	private static final String RETURN = "returnType";
	private static final String VOID_TYPE = "void";
	private static final String QUERY = "query";

	//TODO MJCG Test queries with null and not null
	private static ClassDescriptor readFile(String fileName) {
		ClassDescriptor classDescriptor = null;
		Configuration config = null;
		List<String> list = new ArrayList<>();
		Parameters params = new Parameters();
		int counter = 0;
		List<MethodDescriptor> methods = new ArrayList<>();
		try {
			// open and load the file
			FileInputStream file = new FileInputStream(fileName);
			FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
					PropertiesConfiguration.class).configure(params.properties().setFileName(fileName));
			// .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
			try {
				config = builder.getConfiguration();
				// verifying the info into the file is correct
				String error = checkingForErrors(config);
				if (error != null) {
					System.err.println(error);
				} else {
					classDescriptor = new ClassDescriptor();
					// setting absolute class name
					classDescriptor.setAbsoluteName(config.getString(CLASSNAME));
					// getting method keys
					List<Object> methodList = config.getList(METHOD);
					for (Object m : methodList) {
						MethodDescriptor methodDesc = new MethodDescriptor();
						// setting method name
						methodDesc.setMethodName((String) m);
						// setting input params
						String[] inputParams = config.getStringArray(PARAMS)[counter].split(",");
						List<String> input = new ArrayList<>();
						for (String param : inputParams) {
							input.add(param);
						}
						methodDesc.setInputParams(input);
						// setting return type
						methodDesc.setOutputParam((String) config.getList(RETURN).get(counter));
						// setting query
						methodDesc.setQuery((String) config.getList(QUERY).get(counter));
						methods.add(methodDesc);
						counter++;
					}
					classDescriptor.setMethods(methods);
				}
			} catch (ConfigurationException cex) {
				cex.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classDescriptor;
	}

	//TODO MJCG Test negation
	public static void translate(String fileName) {
		List<MethodSpec> methodsSpec = new ArrayList<>();
		// reading the user file
		ClassDescriptor classDefinition = readFile(fileName);
		// iterate over every method
		for (MethodDescriptor method : classDefinition.getMethods()) {
			try {
				// getting the list of input parameters
				List<UserDTO> params = getInputParameters(method);
				// creating the schema description with the input params
				Map<String, Map<String, String>> schemaDescriptor = Utils.schemaDescriptor(params);
				// creating the database schema with the user dtos as tables
				Schema schema = db.createSchema(schemaDescriptor);
				// executing the query and getting the execution plan for that
				// query
				RelationalAlgebra plan = db.getExecutionPlan(method.getQuery(), schema);
				// parsing the plan and getting the stream java code pipeline
				// statements
				List<String> stmts = getCode(plan, method.getOutputParam());
				String returnType = method.getOutputParam();
				Object returnObj = null;

				Class c = Class.forName(returnType.trim());
				returnObj = c.newInstance();

				// generating the stream java code for this method
				MethodSpec ms = generator.createMethod(method.getMethodName(), stmts, params, returnObj);
				// adding the method to the list
				methodsSpec.add(ms);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// extracting the package
		int i = classDefinition.getAbsoluteName().lastIndexOf(".");
		String pck = classDefinition.getAbsoluteName().substring(0, i > -1 ? i : 0);
		
		String cls = classDefinition.getAbsoluteName()
				.substring(classDefinition.getAbsoluteName().lastIndexOf(".") + 1);
		// creating the class
		try {
			generator.createClass(pck, cls, methodsSpec);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<UserDTO> getInputParameters(MethodDescriptor methodDescriptor) {
		return methodDescriptor.getInputParams().stream().map(m -> {
			String inputName = m.substring(m.lastIndexOf(".") + 1).toLowerCase();
			Class c;
			UserDTO udto = null;
			try {
				//TODO MJCG Ver si puedo crear objecto cuando no tiene constructor por defecto
				c = Class.forName(m.trim());
				Object obj = c.newInstance();
				udto = new UserDTO(inputName, obj);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return udto;
		}).collect(Collectors.toList());
	}

	private static String checkingForErrors(Configuration config) {
		String error = null;
		List<Object> classList = config.getList(CLASSNAME);
		List<Object> methodList = config.getList(METHOD);
		List<Object> paramList = config.getList(PARAMS);
		List<Object> returnList = config.getList(RETURN);
		List<Object> queryList = config.getList(QUERY);
		if (classList.size() > 1) {
			error = "Multiple class keys found";
		} else if (classList.size() == 0) {
			error = "class key not found";
		}
		if (methodList.size() == 0) {
			error = "method key not found";
		}
		if (paramList.size() == 0) {
			error = "params key not found";
		}
		if (returnList.size() == 0) {
			error = "return key not found";
		} else {
			for (Object returnType : returnList) {
				if (((String) returnType).equals(VOID_TYPE)) {
					error = "void return type not permitted";
				}
			}
		}
		if (queryList.size() == 0) {
			error = "query key not found";
		}
		if (!(methodList.size() == paramList.size() && paramList.size() == returnList.size()
				&& returnList.size() == queryList.size())) {
			error = "The number of method, params, return and query keys do not match";
		}

		return error;
	}

	/**
	 * Translate the query execution plan into Stream Java code
	 * 
	 * @param plan
	 * @return
	 */
	private static List<String> getCode(RelationalAlgebra plan, String returnType) {
		List<String> stmts = new ArrayList<>();
		parser.parser(plan, stmts);
		// reversing the order of the statements
		Collections.reverse(stmts);
		// adding as a final statement the conversion from temporary maps to
		// return type specified into the file
		String returnStmt = "return " + plan.getReturnVar() + ".get().map(map -> {" + returnType + " bean = new "
				+ returnType + "(); try { org.apache.commons.beanutils.BeanUtils.copyProperties(bean, map); } "
				+ "catch (IllegalAccessException | java.lang.reflect.InvocationTargetException e) { e.printStackTrace(); return null; } return bean; })"
				+ ".collect(java.util.stream.Collectors.toList())";
		stmts.add(returnStmt);
		return stmts;
	}
}
