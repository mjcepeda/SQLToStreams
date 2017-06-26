package edu.rit.dao.test.stream;

import java.lang.Object;
import java.lang.String;
import java.util.List;
import java.util.Map;

public class StreamQuery {
  public void testSelect(final List<Map<String, Object>> professors) {
    professors.stream().filter(bean -> ((Integer)bean.get("age")).compareTo(34)>=0 && ((String)bean.get("gender")).compareTo("F")==0).forEach(System.out::println);
  }

  public void testProjection(final List<Map<String, Object>> professors) {
    professors.stream().flatMap(bean1 ->departments.stream().filter(bean2 -> Objects.equals(bean1.get("dept"), bean2.get("id"))).map(bean2 -> {Map<String, Object> tmp = new HashMap<String, Object>(); tmp.putAll(bean1); tmp.putAll(bean2); return tmp; })).filter(bean -> ((String)bean.get("code")).compareTo("CSCI")==0).map(bean -> {Map<String, Object> tmp = new HashMap<>();try {tmp.put("name", BeanUtils.getProperty(bean, "name"));tmp.put("lastName", BeanUtils.getProperty(bean, "lastName"));} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {System.err.println(e.getMessage());return null; }return tmp; }).forEach(System.out::println);
  }
}
