package edu.rit.dao.impl.store.access;

import java.util.List;

public class TableDescriptor {

	private String tableName;
	private List<ColumnDescriptor> columnsDescriptor;
	
	public TableDescriptor(String tableName, List<ColumnDescriptor> columnsDescriptor) {
		this.tableName = tableName;
		this.columnsDescriptor = columnsDescriptor;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the columnsDescriptor
	 */
	public List<ColumnDescriptor> getColumnsDescriptor() {
		return columnsDescriptor;
	}
	/**
	 * @param columnsDescriptor the columnsDescriptor to set
	 */
	public void setColumnsDescriptor(List<ColumnDescriptor> columnsDescriptor) {
		this.columnsDescriptor = columnsDescriptor;
	}
	
}
