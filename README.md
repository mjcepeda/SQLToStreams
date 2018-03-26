# SQLToStreams
The aim of this project is to evaluate Java 8 Streams API versatility to simulate different SQL queries. Given a SQL query, this tool translates such query into a pipeline of relational algebra operations and generate Java code using Streams API that simulate the same behavior of the input query.
Currently, our system implements all the fundamental relational algebra operations except **rename**. These operations perform table **join**,
**union**, **difference**, **projection**, and **selection**.

# Usage
Specify a Data Manipulation File with the folowing information:
```
class=edu.rit.test.result.UniversityStreams

method=professorSalary
params=edu.rit.test.data.Professor
returnType=edu.rit.test.data.Professor
query=select * from professor where salary > 95000
```
Then, invoke Translator.translate using the name of DMF as an input parameter.
```
Translator.translate("\\DMF.txt");
```
The result would be the generation of the java file UniversityStreams with a professorSalary method. 
This method will simulate the query 'select * from professor where salary > 95000' using stream operations.
```
public List<Professor> professorSalary(final Collection<Professor> professor) {
    Supplier<Stream<Map<String, Object>>> professorIWJ5LB6 = () -> professorToMap(professor).stream();
    Supplier<Stream<Map<String, Object>>> select1GIKYJB = () -> professorIWJ5LB6.get().filter(bean -> 
        ObjectUtils.compare((Comparable) bean.get("salary"),(Comparable) 50000) > 0);
    Supplier<Stream<Map<String, Object>>> projectionAPVW6L = () -> select1GIKYJB.get().map(bean -> {
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("name", bean.get("name"));
        tmp.put("lastName", bean.get("lastName"));
        return tmp;
    });
    return mapToprofessor(projectionAPVW6L));
}
```
# Limitations
The list of operations to be implemented is as follows:
* Rename
* Natural join
* Left outer join
* Right outer join
* Full outer join
* Sorting
* Aggregate functions
* Nested queries
* Used of wild-cards in predicates such as \%, \_. 
