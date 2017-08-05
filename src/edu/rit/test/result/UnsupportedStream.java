package edu.rit.test.result;

import edu.rit.test.data.Department;
import edu.rit.test.data.Professor;
import edu.rit.test.data.Section;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class UnsupportedStream {
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

  private List<Map<String, Object>> sectionToMap(final Collection<Section> section) {
    List<Map<String, Object>> tableData = new java.util.ArrayList<>();
    section.forEach(bean -> { Map<String, Object> m = new java.util.HashMap();
    m.put("courseId", bean.getCourseId());
    m.put("secId", bean.getSecId());
    m.put("semester", bean.getSemester());
    m.put("year", bean.getYear());
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

  private List<Section> mapTosection(final Stream<Map<String, Object>> section) {
    List<edu.rit.test.data.Section> userData = new java.util.ArrayList<>();
    section.forEach(m -> { edu.rit.test.data.Section bean = new edu.rit.test.data.Section();
    if (m.containsKey("courseId")) { bean.setCourseId((String) m.get("courseId"));}if (m.containsKey("secId")) { bean.setSecId((String) m.get("secId"));}if (m.containsKey("semester")) { bean.setSemester((String) m.get("semester"));}if (m.containsKey("year")) { bean.setYear((int) m.get("year"));} userData.add(bean); }); return userData;
  }
}
