package edu.rit.test.result;

import edu.rit.test.data.Department;
import edu.rit.test.data.Professor;
import edu.rit.test.data.Section;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class UniversityStreams {
  public List<Professor> testJoin(final Collection<Professor> professor,
      final Collection<Department> department) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> department5NYJAS = () -> departmentToMap(department).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professor2JQM9 = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> cartprodKG4N4Z = () ->professor2JQM9.get().flatMap(bean1 -> department5NYJAS.get().map(bean2 -> { Object idJXXB0Q = bean1.get("id"); bean1.remove("id"); bean1.put("professor_id", idJXXB0Q); Object id2228BS = bean2.get("id"); bean2.remove("id"); bean2.put("department_id", id2228BS);Map<String, Object> tmp = new java.util.HashMap<>(); tmp.putAll(bean1); tmp.putAll(bean2); return tmp; }));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectWAUAGR = () ->cartprodKG4N4Z.get().filter(bean -> (org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("department_id"),(Comparable)bean.get("dept"))==0 && org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("deptName"),(Comparable)"Computer Science")==0));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> projectionFZD030W = () ->selectWAUAGR.get().map(bean -> {Map<String, Object> tmp = new java.util.HashMap<>();tmp.put("name", bean.get("name"));tmp.put("lastName", bean.get("lastName"));tmp.put("dept", bean.get("dept"));return tmp; });
    return mapToprofessor(projectionFZD030W.get());
  }

  public List<Professor> testSelect(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professorTXHIXJ = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> select229A4 = () ->professorTXHIXJ.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("salary"),(Comparable)95000)>0);
    return mapToprofessor(select229A4.get());
  }

  public List<Professor> testUnion(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professorFDDDMN23J = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectL6IWD07 = () ->professorFDDDMN23J.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("dept"),(Comparable)2)==0);
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professorUO0Q69 = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectYXXP2 = () ->professorUO0Q69.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("dept"),(Comparable)1)==0);
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> unionZRQJJC5 = () ->java.util.stream.Stream.concat(selectYXXP2.get(), selectL6IWD07.get());
    return mapToprofessor(unionZRQJJC5.get());
  }

  public List<Section> testDifference(final Collection<Section> section) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> section409P85 = () -> sectionToMap(section).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectI8J7YBQRC = () ->section409P85.get().filter(bean -> (org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("semester"),(Comparable)"Spring")==0 && org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("year"),(Comparable)2010)==0));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> projection3R36O = () ->selectI8J7YBQRC.get().map(bean -> {Map<String, Object> tmp = new java.util.HashMap<>();tmp.put("courseId", bean.get("courseId"));return tmp; });
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> section71V6B5 = () -> sectionToMap(section).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectY2SARMWT = () ->section71V6B5.get().filter(bean -> (org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("semester"),(Comparable)"Fall")==0 && org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("year"),(Comparable)2009)==0));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> projectionVI0ZWNK = () ->selectY2SARMWT.get().map(bean -> {Map<String, Object> tmp = new java.util.HashMap<>();tmp.put("courseId", bean.get("courseId"));return tmp; });
    List<Map<String,Object>> listprojection3R36O = projection3R36O.get().collect(java.util.stream.Collectors.toList());java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> diffsetVV0TX = () ->projectionVI0ZWNK.get().filter(bean -> !listprojection3R36O.contains(bean));
    return mapTosection(diffsetVV0TX.get());
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

  private List<Map<String, Object>> sectionToMap(final Collection<Section> section) {
    List<Map<String, Object>> tableData = new java.util.ArrayList<>();
    section.forEach(bean -> { Map<String, Object> m = new java.util.HashMap();
    m.put("courseId", bean.getCourseId());
    m.put("secId", bean.getSecId());
    m.put("semester", bean.getSemester());
    m.put("year", bean.getYear());
    tableData.add(m); }); return tableData;
  }

  private List<Professor> mapToprofessor(final Stream<Map<String, Object>> professor) {
    List<edu.rit.test.data.Professor> userData = new java.util.ArrayList<>();
    professor.forEach(m -> { edu.rit.test.data.Professor bean = new edu.rit.test.data.Professor();
    if (m.containsKey("age")) { bean.setAge((int) m.get("age"));}if (m.containsKey("dept")) { bean.setDept((int) m.get("dept"));}if (m.containsKey("gender")) { bean.setGender((String) m.get("gender"));}if (m.containsKey("id")) { bean.setId((int) m.get("id"));}if (m.containsKey("lastName")) { bean.setLastName((String) m.get("lastName"));}if (m.containsKey("name")) { bean.setName((String) m.get("name"));}if (m.containsKey("salary")) { bean.setSalary((Integer) m.get("salary"));} userData.add(bean); }); return userData;
  }

  private List<Section> mapTosection(final Stream<Map<String, Object>> section) {
    List<edu.rit.test.data.Section> userData = new java.util.ArrayList<>();
    section.forEach(m -> { edu.rit.test.data.Section bean = new edu.rit.test.data.Section();
    if (m.containsKey("courseId")) { bean.setCourseId((String) m.get("courseId"));}if (m.containsKey("secId")) { bean.setSecId((String) m.get("secId"));}if (m.containsKey("semester")) { bean.setSemester((String) m.get("semester"));}if (m.containsKey("year")) { bean.setYear((int) m.get("year"));} userData.add(bean); }); return userData;
  }
}
