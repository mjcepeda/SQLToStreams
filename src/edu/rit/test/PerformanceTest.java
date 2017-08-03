package edu.rit.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import edu.rit.test.data.DataSet;
import edu.rit.test.data.Department;
import edu.rit.test.data.Professor;
import edu.rit.test.data.Section;

public class PerformanceTest {

	private List<Professor> professors;

	@Test
	public void test() {
		generateProfessors(10000);
		int[] limits = { 10, 50, 100, 5000, 10000 };
		DataSet data = new DataSet();
		UniversityStreams u = new UniversityStreams();
		for (int limit : limits) {
			List<Professor> l = professors.stream().limit(limit).collect(Collectors.toList());
			testJoin(u, l, data.getDepartments(), limit);
			testSelect(u, l, limit);
			testUnion(u, l, data.getDepartments(), limit);
			testDifference(u, data.sections, limit);
		}
	}

	public void testJoin(UniversityStreams u, List<Professor> p, List<Department> d, int limit) {
		// executing code generated for my solution
		long startTime = System.nanoTime();
		List<Professor> result = u.testJoin(p, d);
		long estimatedTime = System.nanoTime() - startTime;
		// executing time of equivalent code
		long ustartTime = System.nanoTime();
		List<Professor> uresult = userJoin(p, d);
		long uestimatedTime = System.nanoTime() - ustartTime;
		System.out.println("testJoin: Num of records: " + limit + " , SQLToStream time: " + estimatedTime / 1e9
				+ " , num of results:" + result.size() + ", User time: " + uestimatedTime / 1e9 + " , num of results: "
				+ uresult.size());
	}

	public void testSelect(UniversityStreams u, List<Professor> p, int limit) {
		// executing code generated for my solution
		long startTime = System.nanoTime();
		List<Professor> result = u.testSelect(p);
		long estimatedTime = System.nanoTime() - startTime;
		// executing time of equivalent code
		long ustartTime = System.nanoTime();
		List<Professor> uresult = userSelect(p);
		long uestimatedTime = System.nanoTime() - ustartTime;
		System.out.println("testSelect: Num of records: " + limit + " , SQLToStream time: " + estimatedTime / 1e9
				+ " , num of results:" + result.size() + ", User time: " + uestimatedTime / 1e9 + " , num of results: "
				+ uresult.size());
	}

	public void testUnion(UniversityStreams u, List<Professor> p, List<Department> d, int limit) {
		// executing code generated for my solution
		long startTime = System.nanoTime();
		List<Professor> result = u.testUnion(p);
		long estimatedTime = System.nanoTime() - startTime;
		// executing time of equivalent code
		long ustartTime = System.nanoTime();
		List<Professor> uresult = userUnion(p, d);
		long uestimatedTime = System.nanoTime() - ustartTime;
		System.out.println("testUnion: Num of records: " + limit + " , SQLToStream time: " + estimatedTime / 1e9
				+ " , num of results:" + result.size() + ", User time: " + uestimatedTime / 1e9 + " , num of results: "
				+ uresult.size());
	}
	
	public void testDifference(UniversityStreams u, List<Section> s, int limit) {
		// executing code generated for my solution
		long startTime = System.nanoTime();
		List<Section> result = u.testDifference(s);
		long estimatedTime = System.nanoTime() - startTime;
		// executing time of equivalent code
		long ustartTime = System.nanoTime();
		List<Section> uresult = userDifference(s);
		long uestimatedTime = System.nanoTime() - ustartTime;
		System.out.println("testUnion: Num of records: " + limit + " , SQLToStream time: " + estimatedTime / 1e9
				+ " , num of results:" + result.size() + ", User time: " + uestimatedTime / 1e9 + " , num of results: "
				+ uresult.size());
//		result.forEach(System.out::println);
//		uresult.forEach(System.out::println);
	}

	public List<Professor> userJoin(final Collection<Professor> professor, final Collection<Department> department) {
		return professor.stream().flatMap(
				p -> department.stream().filter(d -> p.dept == d.getId() && d.getCode().equals("CSCI")).map(d -> p))
				.collect(Collectors.toList());
	}

	public List<Professor> userSelect(final Collection<Professor> professor) {
		return professor.stream().filter(p -> p.getSalary() > 95000 && p.getAge() <= 45).collect(Collectors.toList());
	}

	public List<Professor> userUnion(final Collection<Professor> professor, final Collection<Department> department) {
		return Stream
				.concat(professor.stream()
						.flatMap(p -> department.stream().filter(d -> p.dept == d.getId() && d.getCode().equals("CSCI"))
								.map(d -> p)),
						professor.stream()
								.flatMap(p -> department.stream()
										.filter(d -> p.dept == d.getId() && d.getCode().equals("CPET")).map(d -> p)))
				.collect(Collectors.toList());
	}
	
	public List<Section> userDifference(final Collection<Section> section) {
		List<Section> fall = section.stream().filter(s -> s.getYear()==2009 && s.getSemester()=="Fall").collect(Collectors.toList());
		List<Section> spring = section.stream().filter(s -> s.getYear()==2010 && s.getSemester()=="Spring").collect(Collectors.toList());
		return fall.stream().filter(s -> !spring.contains(s)).collect(Collectors.toList());
	}

	private void generateProfessors(int limit) {
		professors = new ArrayList<>();
		// int third = limit / 2;
		for (int i = 0; i <= limit; i++) {
			int salary = 75000;
			int department = 2;
			int count = i % 2;
			int age = 60;
			if (count == 0) {
				salary = 98000;
				department = 1;
				age = 38;
			}
			professors.add(new Professor("name" + i, "lastName" + i, age, null, department, i, salary));
		}
	}
	
	
}
