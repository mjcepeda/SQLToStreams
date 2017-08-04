package edu.rit.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.squareup.javapoet.MethodSpec;

import edu.rit.dao.impl.store.access.ClassDescriptor;
import edu.rit.dao.impl.store.access.MethodDescriptor;
import edu.rit.dao.impl.store.access.UserDTO;
import edu.rit.dao.impl.stream.CodeGenerator;
import edu.rit.dao.impl.stream.Translator;

//TODO MJCG Delete this class
public class GeneratorTest {

	public void generateTests(String dmf) {
		CodeGenerator generator = new CodeGenerator();
		// first step, call translator to parse the SQL into stream operation
		// and generate the java file
		Translator.translate(dmf);
		// second step, create JUnit for testing the resulting code
		ClassDescriptor classDefinition = Translator.readFile(dmf);
		if (classDefinition != null) {
			// extracting the package
			int i = classDefinition.getAbsoluteName().lastIndexOf(".");
			// creating package for the test
			String pck = classDefinition.getAbsoluteName().substring(0, i > -1 ? i : 0);
			// creating test class name. Syntax className from file + "Test"
			String cls = classDefinition.getAbsoluteName()
					.substring(classDefinition.getAbsoluteName().lastIndexOf(".") + 1);

			List<MethodSpec> methodsSpec = new ArrayList<>();
			// iterate over every method
			for (MethodDescriptor method : classDefinition.getMethods()) {
				// getting the list of input parameters
				List<UserDTO> params = Translator.getInputParameters(method);
				// creating the schema description with the input params
				

				String returnType = method.getOutputParam();
				Class c;
				try {
					c = Class.forName(returnType.trim());
					Object returnObj = c.newInstance();

					// creating the content of every method
					List<String> stmts = new ArrayList<>();
					// creating instance of the class
					StringBuilder sb = new StringBuilder();
					
					StringBuilder querySb = new StringBuilder();
					querySb.append("edu.rit.dao.impl.DerbyDBImpl db = new edu.rit.dao.impl.DerbyDBImpl();");
					querySb.append("db.createDB();");
					
					sb.append(classDefinition.getAbsoluteName()).append(" ").append(cls.toLowerCase()).append("= new ")
							.append(classDefinition.getAbsoluteName()).append("()");
					stmts.add(sb.toString());
					sb = new StringBuilder();
					sb.append("List<").append(c.getName()).append("> result = ").append(cls.toLowerCase()).append(".")
							.append(method.getMethodName()).append("(");
					StringBuffer tableSb = new StringBuffer("Map<String, Map<String, String>> schemaDescriptor = new java.util.HashMap();");
					StringBuffer insertSb = new StringBuffer();
					for (UserDTO param : params) {
						if (!param.equals(params.stream().findFirst().get())) {
							sb.append(" , ");
						}
						sb.append(param.getName());
						tableSb.append("Map<String, String> table").append(param.getName()).append(" = edu.rit.utils.Utils.tableDescriptor(").append(param.getName()).append(".stream().findFirst().get());");
						tableSb.append("schemaDescriptor.put(\"").append(param.getName()).append("\", table").append(param.getName()).append(");");
						
						insertSb.append("db.insertData(\"").append(param.getName()).append("\", edu.rit.utils.Utils.objectToMaps(").append(param.getName()).append(".stream().collect(java.util.stream.Collectors.toList())));");
					}
					sb.append(")");
					stmts.add(sb.toString());
					
					querySb.append(tableSb.toString());
					querySb.append("schemaDescriptor.entrySet().forEach(t -> db.createTable(t.getKey(), t.getValue()));");
					querySb.append(insertSb.toString());
					String query = method.getQuery().replaceAll("\"", "\\\"");
					querySb.append("java.sql.ResultSet rs = db.executeQuery(\"").append(method.getQuery()).append("\");");

					querySb.append("db.shutdown();");
					querySb.append("db.dropDB()");
					
					stmts.add(querySb.toString());

					// adding return statement
					stmts.add("return result");
					// generating the stream java code for this method
					MethodSpec ms = generator.createMethod(method.getMethodName() + "Test", stmts, params, returnObj);
					// adding the method to the list
					methodsSpec.add(ms);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}

			// creating the JUnit class
			try {
				generator.createClass(pck + ".test", cls + "Test", methodsSpec);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
