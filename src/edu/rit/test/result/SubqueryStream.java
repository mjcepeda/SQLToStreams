package edu.rit.test.result;

import edu.rit.test.data.Department;
import edu.rit.test.data.Professor;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SubqueryStream {
  public List<Professor> subqueryFrom(final Collection<Professor> professor,
      final Collection<Department> department) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> departmentE2RMBX = () -> departmentToMap(department).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectC2XBN = () ->departmentE2RMBX.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("code"),(Comparable)"CSCI")==0);
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> projectionYEIYCO5I = () ->selectC2XBN.get().map(bean -> {Map<String, Object> tmp = new java.util.HashMap<>();tmp.put("id", bean.get("id"));return tmp; });
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professorFQJP5Z9L9 = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> cartprodKNV5J = () ->professorFQJP5Z9L9.get().flatMap(bean1 -> projectionYEIYCO5I.get().map(bean2 -> { Object idBG95S = bean1.get("id"); bean1.remove("id"); bean1.put("professor_id", idBG95S); Object idWE2POVO4 = bean2.get("id"); bean2.remove("id"); bean2.put("department_id", idWE2POVO4);Map<String, Object> tmp = new java.util.HashMap<>(); tmp.putAll(bean1); tmp.putAll(bean2); return tmp; }));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectSRMW5Q = () ->cartprodKNV5J.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("department_id"),(Comparable)bean.get("dept"))==0);
    return mapToprofessor(selectSRMW5Q.get());
  }

  private List<Map<String, Object>> professorToMap(final Collection<Professor> professor) {
    List<Map<String, Object>> tableData = new java.util.ArrayList<>();
    professor.forEach(bean -> { Map<String, Object> m = new java.util.HashMap();
    m.put("age", bean.getAge());
    m.put("dept", bean.getDept());
    m.put("gender", bean.getGender());
    m.put("id", bean.getId());
    m.put("lastName", bean.getLastName());
    m.put("name", bean.getName());
    m.put("salary", bean.getSalary());
    tableData.add(m); }); return tableData;
  }

  private List<Map<String, Object>> departmentToMap(final Collection<Department> department) {
    List<Map<String, Object>> tableData = new java.util.ArrayList<>();
    department.forEach(bean -> { Map<String, Object> m = new java.util.HashMap();
    m.put("code", bean.getCode());
    m.put("deptName", bean.getDeptName());
    m.put("id", bean.getId());
    tableData.add(m); }); return tableData;
  }

  private List<Professor> mapToprofessor(final Stream<Map<String, Object>> professor) {
    List<edu.rit.test.data.Professor> userData = new java.util.ArrayList<>();
    professor.forEach(m -> { edu.rit.test.data.Professor bean = new edu.rit.test.data.Professor();
    if (m.containsKey("age")) { bean.setAge((int) m.get("age"));}if (m.containsKey("dept")) { bean.setDept((int) m.get("dept"));}if (m.containsKey("gender")) { bean.setGender((String) m.get("gender"));}if (m.containsKey("id")) { bean.setId((int) m.get("id"));}if (m.containsKey("lastName")) { bean.setLastName((String) m.get("lastName"));}if (m.containsKey("name")) { bean.setName((String) m.get("name"));}if (m.containsKey("salary")) { bean.setSalary((Integer) m.get("salary"));} userData.add(bean); }); return userData;
  }
}
