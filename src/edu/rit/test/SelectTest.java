package edu.rit.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.rit.test.data.DataSet;
import edu.rit.test.data.Professor;
import edu.rit.test.result.FilterStream;

/**
 * The Class FilterTest.
 */
public class SelectTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {

		DataSet data = new DataSet();
		FilterStream fs = new FilterStream();
		//first query: salary is null
		List<Professor> p1 = fs.salaryNull1(data.getProfessors());
		//second query: salary = null
		List<Professor> p2 = fs.salaryNull2(data.getProfessors());
		//assertions
		assertTrue(p1.size() > 0);
		assertTrue(p1.size() == p2.size());
		p1.forEach(p -> assertTrue(p.getSalary()==null));
//		System.out.println("Professor with null values");
//		p1.forEach(System.out::println);
		//third query: salary is not null
		List<Professor> p3 = fs.salaryNotNull1(data.getProfessors());
		//assertions
		assertTrue(p3.size()> 0);
		p3.forEach(p -> assertTrue(p.getSalary()!=null));
//		System.out.println("Professor with not null values");
//		p3.forEach(System.out::println);
		List<Professor> p4 = fs.wildcards(data.getProfessors());
		assertTrue(p4.size()== 0);
//		p4.forEach(System.out::println);
	}
	
	/**
	 * Test error.
	 */
	@Test(expected = ClassCastException.class) 
	public void testError() {
		DataSet data = new DataSet();
		FilterStream fs = new FilterStream();
		//comparing int against a String
		fs.salaryTypeError(data.getProfessors());
	}
}
