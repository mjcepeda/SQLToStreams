package edu.rit.test.result;

import edu.rit.test.data.Professor;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FilterStream {
  public List<Professor> salaryNull1(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professor9J4BF = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> selectZREFRC3C = () ->professor9J4BF.get().filter(bean -> bean.get("salary")==null);
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> projectionXYWLSDPXE = () ->selectZREFRC3C.get().map(bean -> {Map<String, Object> tmp = new java.util.HashMap<>();tmp.put("name", bean.get("name"));tmp.put("lastName", bean.get("lastName"));return tmp; });
    return mapToprofessor(projectionXYWLSDPXE.get());
  }

  public List<Professor> salaryNull2(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professor8KJ7IW = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> select46S8OA = () ->professor8KJ7IW.get().filter(bean -> bean.get("salary")==null);
    return mapToprofessor(select46S8OA.get());
  }

  public List<Professor> salaryNotNull1(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professorP7SBQ = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> select60OIB = () ->professorP7SBQ.get().filter(bean -> !(bean.get("salary")==null));
    return mapToprofessor(select60OIB.get());
  }

  public List<Professor> wildcards(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professorK9233ADEH = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> select9OA7YDV = () ->professorK9233ADEH.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("name"),(Comparable)"R%")==0);
    return mapToprofessor(select9OA7YDV.get());
  }

  public List<Professor> salaryTypeError(final Collection<Professor> professor) {
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> professor2TL97TT3 = () -> professorToMap(professor).stream();
    java.util.function.Supplier<java.util.stream.Stream<Map<String, Object>>> select3GKB43 = () ->professor2TL97TT3.get().filter(bean -> org.apache.commons.lang3.ObjectUtils.compare((Comparable)bean.get("salary"),(Comparable)"12")>0);
    return mapToprofessor(select3GKB43.get());
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

  private List<Professor> mapToprofessor(final Stream<Map<String, Object>> professor) {
    List<edu.rit.test.data.Professor> userData = new java.util.ArrayList<>();
    professor.forEach(m -> { edu.rit.test.data.Professor bean = new edu.rit.test.data.Professor();
    if (m.containsKey("age")) { bean.setAge((int) m.get("age"));}if (m.containsKey("dept")) { bean.setDept((int) m.get("dept"));}if (m.containsKey("gender")) { bean.setGender((String) m.get("gender"));}if (m.containsKey("id")) { bean.setId((int) m.get("id"));}if (m.containsKey("lastName")) { bean.setLastName((String) m.get("lastName"));}if (m.containsKey("name")) { bean.setName((String) m.get("name"));}if (m.containsKey("salary")) { bean.setSalary((Integer) m.get("salary"));} userData.add(bean); }); return userData;
  }
}
