package edu.rit.test;

import java.util.ArrayList;
import java.util.List;

import edu.rit.dao.impl.relational.CartesianProduct;
import edu.rit.dao.impl.relational.Difference;
import edu.rit.dao.impl.relational.Intersect;
import edu.rit.dao.impl.relational.Projection;
import edu.rit.dao.impl.relational.Union;

public class AlgebraOpTest {

	public static void main(String args[]) {
		projection();
		union();
		intersect();
		difference();
		cartesianProduct();
	}
	
	private static void projection() {
		List<String> columnNames = new ArrayList<>();
		columnNames.add("name");
		columnNames.add("lastName");
		String tableName="professor";
		Projection p = new Projection(tableName, columnNames);
		System.out.println(p.perform());
	}
	
	private static void union() {
		Union p = new Union("professor", "teacher");
		System.out.println(p.perform());
	}
	
	private static void intersect() {
		Intersect p = new Intersect("professor", "teacher");
		System.out.println(p.perform());
	}
	
	private static void difference() {
		Difference p = new Difference("professor", "teacher");
		System.out.println(p.perform());
	}
	
	private static void cartesianProduct() {
		CartesianProduct cp = new CartesianProduct("Professor", "Department");
		System.out.println(cp.perform());
	}

}
