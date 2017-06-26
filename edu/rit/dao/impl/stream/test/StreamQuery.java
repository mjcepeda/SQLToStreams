package edu.rit.dao.impl.stream.test;

import java.lang.Object;
import java.lang.String;
import java.util.List;
import java.util.Map;

public class StreamQuery {
  public void testSelect(final List<Map<String, Object>> professors) {
    professors.stream().filter(bean -> ((Integer)bean.get("age")).compareTo(34)>=0 && ((String)bean.get("gender")).compareTo("F")==0).forEach(System.out::println);
  }
}
