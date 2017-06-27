package edu.rit.test.stream;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.apache.commons.beanutils.BeanUtils;

public class StreamQuery {
	public Stream<Map<String, Object>> testSelect(final List<Map<String, Object>> professors) {
		Supplier<Stream<Map<String, Object>>> professorsStream = () -> professors.stream();
		Supplier<Stream<Map<String, Object>>> p = () -> professorsStream.get()
				.filter(bean -> ((Integer) bean.get("age")).compareTo(34) >= 0
						&& ((String) bean.get("gender")).compareTo("F") == 0);
		return p.get();
	}

	public Stream<Map<String, Object>> testProjection(final List<Map<String, Object>> professors,
			final List<Map<String, Object>> departments) {
		Supplier<Stream<Map<String, Object>>> departmentsStream = () -> departments.stream();
		Supplier<Stream<Map<String, Object>>> s = () -> departmentsStream.get()
				.filter(bean -> ((String) bean.get("code")).compareTo("CSCI") == 0);
		Supplier<Stream<Map<String, Object>>> professorsStream = () -> professors.stream();
		Supplier<Stream<Map<String, Object>>> pd = () -> professorsStream.get().flatMap(
				bean1 -> s.get().filter(bean2 -> Objects.equals(bean1.get("dept"), bean2.get("id"))).map(bean2 -> {
					Map<String, Object> tmp = new HashMap<String, Object>();
					tmp.putAll(bean1);
					tmp.putAll(bean2);
					return tmp;
				}));
		Supplier<Stream<Map<String, Object>>> p = () -> pd.get().map(bean -> {
			Map<String, Object> tmp = new HashMap<>();
			try {
				tmp.put("name", BeanUtils.getProperty(bean, "name"));
				tmp.put("lastName", BeanUtils.getProperty(bean, "lastName"));
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				System.err.println(e.getMessage());
				return null;
			}
			return tmp;
		});
		return p.get();
	}
}
