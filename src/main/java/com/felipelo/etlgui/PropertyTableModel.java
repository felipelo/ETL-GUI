package com.felipelo.etlgui;

import br.com.saxes.suite.model.TreeNode;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;
import javax.swing.JTree;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author felipe
 */
public class PropertyTableModel extends AbstractTableModel {
	
	private final static int NAME = 0;
	private final static int DESCRIPTION = 1;
	
	private TreeNode treeNode;
	
	private String columns[] = {"Property", "Value"};
	
//	private DefaultTreeModel treeModel;
	
	public PropertyTableModel( JTree treeModel ) {
		super();
		treeModel.
	}
	
	public void setTreeNode( TreeNode treeNode ) {
		this.treeNode = treeNode;
		fireTableRowsInserted(0, 2);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch(rowIndex) {
			case NAME:
				treeNode.setName( (String)aValue );
				break;
			case DESCRIPTION:
				treeNode.setDescription( (String)aValue );
		}
		fireTableRowsInserted(rowIndex, rowIndex);
		
    }

	public int getRowCount() {
		return ( treeNode == null ) ? 0 : 2;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		String value = "";
		
		if( columnIndex == 0 ) {
			switch(rowIndex) {
				case NAME:
					return "Name";
				case DESCRIPTION:
					return "Description";
			}
		}
		
		if( treeNode == null ) {
			return value;
		}
		
		switch(rowIndex) {
			case NAME:
				value = treeNode.getName();
				break;
			case DESCRIPTION:
				value = treeNode.getDescription();
		}
		
		return value;
	}

	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex > 0);
	}
	
}
