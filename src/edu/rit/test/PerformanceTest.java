package edu.rit.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import edu.rit.test.data.DataSet;
import edu.rit.test.data.Department;
import edu.rit.test.data.Professor;
import edu.rit.test.data.Section;
import edu.rit.test.result.UniversityStreams;

/**
 * The Class PerformanceTest.
 */
public class PerformanceTest {

	/** The professors. */
	private List<Professor> professors;
	
	/** The sections. */
	private List<Section> sections;

	/**
	 * Test.
	 */
	@Test
	public void test() {
		generateProfessors(100000);
		generateSections(100000);
		int[] limits = { /* 10, 50, 100, 5000, */ 100000 };
		DataSet data = new DataSet();
		UniversityStreams u = new UniversityStreams();
		for (int limit : limits) {
			List<Professor> l = professors.stream().limit(limit).collect(Collectors.toList());
			List<Section> s = sections.stream().limit(limit).collect(Collectors.toList());
			////////////////////////////////////////////
			//uncomment the method that you want to test
			///////////////////////////////////////////
			testJoin(u, l, data.getDepartments(), limit);
			//testSelect(u, l, limit);
			//testUnion(u, l, data.getDepartments(), limit);
			//testDifference(u, s, limit);
		}
	}

	/**
	 * Test join.
	 *
	 * @param u the u
	 * @param p the p
	 * @param d the d
	 * @param limit the limit
	 */
	public void testJoin(UniversityStreams u, List<Professor> p, List<Department> d, int limit) {
		for (int i = 0; i <= 25; i++) {
			// executing code generated for my solution
			long startTime = System.nanoTime();
			List<Professor> result = u.testJoin(p, d);
			long duration = System.nanoTime() - startTime;
			long estimatedTime = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
			// executing time of equivalent code
			long ustartTime = System.nanoTime();
			List<String> uresult = userJoin(p, d);
			long uduration = System.nanoTime() - ustartTime;
			long uestimatedTime = TimeUnit.MILLISECONDS.convert(uduration, TimeUnit.NANOSECONDS);
			System.out.println("testJoin: Num of records: " + limit + " , SQLToStream time: " + estimatedTime
					+ " , num of results:" + result.size() + ", User time: " + uestimatedTime + " , num of results: "
					+ uresult.size());
			assertTrue(result.size() > 0);
			assertTrue(result.size() == uresult.size());
			//Computer Science department is id 1
			result.forEach(prof -> assertTrue(prof.getDept()==1));
			// assertSame(result, uresult);
		}
	}

	/**
	 * Test select.
	 *
	 * @param u the u
	 * @param p the p
	 * @param limit the limit
	 */
	public void testSelect(UniversityStreams u, List<Professor> p, int limit) {
		for (int i = 0; i <= 25; i++) {
			// executing code generated for my solution
			long startTime = System.nanoTime();
			List<Professor> result = u.testSelect(p);
			long duration = System.nanoTime() - startTime;
			long estimatedTime = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
			// executing time of equivalent code
			long ustartTime = System.nanoTime();
			List<Professor> uresult = userSelect(p);
			long uduration = System.nanoTime() - ustartTime;
			long uestimatedTime = TimeUnit.MILLISECONDS.convert(uduration, TimeUnit.NANOSECONDS);
			System.out.println("testSelect: Num of records: " + limit + " , SQLToStream time: " + estimatedTime
					+ " , num of results:" + result.size() + ", User time: " + uestimatedTime + " , num of results: "
					+ uresult.size());
			assertTrue(result.size() > 0);
			assertTrue(result.size() == uresult.size());
			result.forEach(prof -> assertTrue(prof.getSalary()>95000));
			// assertSame(result, uresult);
		}
	}

	/**
	 * Test union.
	 *
	 * @param u the u
	 * @param p the p
	 * @param d the d
	 * @param limit the limit
	 */
	public void testUnion(UniversityStreams u, List<Professor> p, List<Department> d, int limit) {
		for (int i = 0; i <= 25; i++) {
			// executing time of equivalent code
			long ustartTime = System.nanoTime();
			List<String> uresult = userUnion(p, d);
			long uduration = System.nanoTime() - ustartTime;
			long uestimatedTime = TimeUnit.MILLISECONDS.convert(uduration, TimeUnit.NANOSECONDS);
			// executing code generated for my solution
			long startTime = System.nanoTime();
			List<Professor> result = u.testUnion(p);
			long duration = System.nanoTime() - startTime;
			long estimatedTime = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
			System.out.println("testUnion: Num of records: " + limit + " , SQLToStream time: " + estimatedTime
					+ " , num of results:" + result.size() + ", User time: " + uestimatedTime + " , num of results: "
					+ uresult.size());
			assertTrue(result.size() > 0);
			assertTrue(result.size() == uresult.size());
			//departments 1 CSCI or 2 CPET
			result.forEach(prof -> assertTrue(prof.getDept()==1 || prof.getDept()==2));
			// assertSame(result, uresult);
		}
	}

	/**
	 * Test difference.
	 *
	 * @param u the u
	 * @param s the s
	 * @param limit the limit
	 */
	public void testDifference(UniversityStreams u, List<Section> s, int limit) {
		for (int i = 0; i <= 25; i++) {
			// executing code generated for my solution
			long startTime = System.nanoTime();
			List<Section> result = u.testDifference(s);
			long duration = System.nanoTime() - startTime;
			long estimatedTime = TimeUnit.MILLISECONDS.convert(duration, TimeUnit.NANOSECONDS);
			// executing time of equivalent code
			long ustartTime = System.nanoTime();
			List<String> uresult = userDifference(s);
			long uduration = System.nanoTime() - ustartTime;
			long uestimatedTime = TimeUnit.MILLISECONDS.convert(uduration, TimeUnit.NANOSECONDS);
			System.out.println("testUnion: Num of records: " + limit + " , SQLToStream time: " + estimatedTime
					+ " , num of results:" + result.size() + ", User time: " + uestimatedTime + " , num of results: "
					+ uresult.size());
			assertTrue(result.size() > 0);
			assertTrue(result.size() == uresult.size());
			// assertSame(result, uresult);
		}
		// result.forEach(System.out::println);
		// uresult.forEach(System.out::println);
	}

	/**
	 * User join.
	 *
	 * @param professor the professor
	 * @param department the department
	 * @return the list
	 */
	public List<String> userJoin(final Collection<Professor> professor, final Collection<Department> department) {
		return professor.stream()
				.flatMap(p -> department.stream()
						.filter(d -> p.dept == d.getId() && d.getDeptName().equals("Computer Science")).map(d -> p))
				.map(p -> p.getName() + ", " + p.getLastName()).collect(Collectors.toList());
	}

	/**
	 * User select.
	 *
	 * @param professor the professor
	 * @return the list
	 */
	public List<Professor> userSelect(final Collection<Professor> professor) {
		return professor.stream().filter(p -> p.getSalary() > 95000 && p.getAge() <= 45).map(p -> {
			Professor p2 = new Professor();
			p2.setName(p.getName());
			return p2;
		}).collect(Collectors.toList());
	}

	/**
	 * User union.
	 *
	 * @param professor the professor
	 * @param department the department
	 * @return the list
	 */
	public List<String> userUnion(final Collection<Professor> professor, final Collection<Department> department) {
		return Stream.concat(
				professor.stream().filter(p -> p.getDept() == 2).map(s -> s.getName()),
				professor.stream().filter(p -> p.getDept() == 1).map(s -> s.getName()))
				.collect(Collectors.toList());
	}

	/**
	 * User difference.
	 *
	 * @param section the section
	 * @return the list
	 */
	public List<String> userDifference(final Collection<Section> section) {
		List<String> fall = section.stream().filter(s -> s.getYear() == 2009 && s.getSemester() == "Fall")
				.map(s -> s.getCourseId()).collect(Collectors.toList());
		List<String> spring = section.stream().filter(s -> s.getYear() == 2010 && s.getSemester() == "Spring")
				.map(s -> s.getCourseId()).collect(Collectors.toList());
		return fall.stream().filter(s -> !spring.contains(s)).collect(Collectors.toList());
	}

	/**
	 * Generate professors.
	 *
	 * @param limit the limit
	 */
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

	/**
	 * Generate sections.
	 *
	 * @param limit the limit
	 */
	private void generateSections(int limit) {
		sections = new ArrayList<>();
		// int third = limit / 2;
		for (int i = 0; i <= limit; i++) {
			int aux = i % 10;
			if (aux == 0) {
				sections.add(new Section("CS620", "Fall", 2009, "1"));
				sections.add(new Section("CS620", "Spring", 2010, "1"));
			} else if (aux == 1) {
				sections.add(new Section("CS730", "Fall", 2009, "1"));
			} else if (aux > 3) {
				sections.add(new Section("CS62" + aux, "Fall", 2010, "1"));
			}
		}
	}
}
