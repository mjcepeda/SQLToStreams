package edu.rit.test;

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
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> department4J0P6BY = () -> departmentToMap(department).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professorP0QTYKF6 = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> cartprodB3RI95R = () ->professorP0QTYKF6.get().flatMap(bean1 -> department4J0P6BY.get().map(bean2 -> { Object idZ7GA7EXG = bean1.get("id"); bean1.remove("id"); bean1.put("professor_id", idZ7GA7EXG); Object idHF3QOB4 = bean2.get("id"); bean2.remove("id"); bean2.put("department_id", idHF3QOB4);Map<String, Object> tmp = new java.util.HashMap<>(); tmp.putAll(bean1); tmp.putAll(bean2); return tmp; }));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectF5UWHE = () ->cartprodB3RI95R.get().filter(bean -> (org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("department_id"),(Comparable)bean.get("dept"))==0 && org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("deptName"),(Comparable)"Computer Science")==0));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> projectionCQIMEO7Z = () ->selectF5UWHE.get().map(bean -> {Map<String, Object> tmp = new java.util.HashMap<>();try {tmp.put("name", org.apache.commons.beanutils.BeanUtils.getProperty(bean, "name"));tmp.put("lastName", org.apache.commons.beanutils.BeanUtils.getProperty(bean, "lastName"));} catch (IllegalAccessException | java.lang.reflect.InvocationTargetException | NoSuchMethodException e) {System.err.println(e.getMessage());return null; }return tmp; });
    return mapToprofessor(projectionCQIMEO7Z.get());
  }

  public List<Professor> testSelect(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professorQ4N4Y = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> select8984X = () ->professorQ4N4Y.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("salary"),(Comparable)95000)>0);
    return mapToprofessor(select8984X.get());
  }

  public List<Professor> testUnion(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professor0ZO814E6N = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectT7VBLH = () ->professor0ZO814E6N.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("dept"),(Comparable)2)==0);
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professor8ISWSAT = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectAT80749WY = () ->professor8ISWSAT.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("dept"),(Comparable)1)==0);
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> unionGLLKRY8 = () ->java.util.stream.Stream.concat(selectAT80749WY.get(), selectT7VBLH.get());
    return mapToprofessor(unionGLLKRY8.get());
  }

  public List<Section> testDifference(final Collection<Section> section) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> sectionJ0NGWEMI = () -> sectionToMap(section).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> select6I1GFG = () ->sectionJ0NGWEMI.get().filter(bean -> (org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("semester"),(Comparable)"Spring")==0 && org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("year"),(Comparable)2010)==0));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> projectionVUKQT935R = () ->select6I1GFG.get().map(bean -> {Map<String, Object> tmp = new java.util.HashMap<>();try {tmp.put("courseId", org.apache.commons.beanutils.BeanUtils.getProperty(bean, "courseId"));} catch (IllegalAccessException | java.lang.reflect.InvocationTargetException | NoSuchMethodException e) {System.err.println(e.getMessage());return null; }return tmp; });
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> sectionY5FUMFT = () -> sectionToMap(section).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectCETWG = () ->sectionY5FUMFT.get().filter(bean -> (org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("semester"),(Comparable)"Fall")==0 && org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("year"),(Comparable)2009)==0));
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> projectionHC2NW = () ->selectCETWG.get().map(bean -> {Map<String, Object> tmp = new java.util.HashMap<>();try {tmp.put("courseId", org.apache.commons.beanutils.BeanUtils.getProperty(bean, "courseId"));} catch (IllegalAccessException | java.lang.reflect.InvocationTargetException | NoSuchMethodException e) {System.err.println(e.getMessage());return null; }return tmp; });
    List<Map<String,Object>> listprojectionVUKQT935R = projectionVUKQT935R.get().collect(java.util.stream.Collectors.toList());java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> diffsetBHM41 = () ->projectionHC2NW.get().filter(bean -> !listprojectionVUKQT935R.contains(bean));
    return mapTosection(diffsetBHM41.get());
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
