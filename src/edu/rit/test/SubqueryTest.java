package edu.rit.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.rit.test.data.DataSet;
import edu.rit.test.data.Professor;
import edu.rit.test.result.SubqueryStream;

/**
 * The Class SubqueryTest.
 */
public class SubqueryTest {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		DataSet data = new DataSet();
		SubqueryStream fs = new SubqueryStream();
		//first query: dept='CSCI'
		List<Professor> p1 = fs.subqueryFrom(data.getProfessors(), data.getDepartments());
		//assertions
		assertTrue(p1.size() > 0);
		p1.forEach(p -> assertTrue(p.getDept()==1));
	}
}
