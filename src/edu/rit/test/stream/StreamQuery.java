package edu.rit.test.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class StreamQuery {
	public Stream<Map<String, Object>> test(final List<Map<String, Object>> professors,
			final List<Map<String, Object>> departments) {
		java.util.function.Supplier<Stream<Map<String, Object>>> department = () -> departments.stream();
		java.util.function.Supplier<Stream<Map<String, Object>>> professor = () -> professors.stream();
		java.util.function.Supplier<Stream<Map<String, Object>>> cartprod0JR2DEO = () -> professor.get()
				.flatMap(bean1 -> department.get().map(bean2 -> {
					Map<String, Object> tmp = new java.util.HashMap<>();
					tmp.putAll(bean1);
					tmp.putAll(bean2);
					return tmp;
				}));
		java.util.function.Supplier<Stream<Map<String, Object>>> select5UX4PN = () -> cartprod0JR2DEO.get()
				.filter(bean -> ((Comparable) bean.get("id")).compareTo(bean.get("dept")) == 0
						&& ((Comparable) bean.get("code")).compareTo("CSCI") == 0
						&& ((Comparable) bean.get("age")).compareTo(50) > 0);
		java.util.function.Supplier<Stream<Map<String, Object>>> projection4CJEXZL0F = () -> select5UX4PN.get()
				.map(bean -> {
					Map<String, Object> tmp = new java.util.HashMap<>();
					try {
						tmp.put("name", org.apache.commons.beanutils.BeanUtils.getProperty(bean, "name"));
						tmp.put("lastName", org.apache.commons.beanutils.BeanUtils.getProperty(bean, "lastName"));
					} catch (IllegalAccessException | java.lang.reflect.InvocationTargetException
							| NoSuchMethodException e) {
						System.err.println(e.getMessage());
						return null;
					}
					return tmp;
				});
		return projection4CJEXZL0F.get();
	}
}
