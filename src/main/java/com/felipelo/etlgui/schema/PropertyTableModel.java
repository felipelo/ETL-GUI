package com.felipelo.etlgui.schema;

import br.com.saxes.suite.model.TreeNode;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.felipelo.etlgui.schema.model.TreeNodeMutable;
		
/**
 *
 * @author felipe
 */
public class PropertyTableModel extends AbstractTableModel {
	
	private final static int NAME = 0;
	private final static int DESCRIPTION = 1;
	
	protected TreeNodeMutable mutableTreeNode;
	protected TreeNode treeNode;
	
	private String columns[] = {"Property", "Value"};
	private int rowCount;
	
	protected DefaultTreeModel treeModel;
	
	public PropertyTableModel( DefaultTreeModel treeModel ) {
		super();
		rowCount = 0;
		this.treeModel = treeModel;
	}
	
	public void setTreeNode( DefaultMutableTreeNode mutableTreeNode ) {
		if( mutableTreeNode == null ) {
			rowCount = 0;
			this.treeNode = null;
		} else {
			treeNode = (TreeNode) mutableTreeNode.getUserObject();
			this.mutableTreeNode = (TreeNodeMutable) mutableTreeNode;
			rowCount = this.mutableTreeNode.getRowCount();
		}
		
		fireTableRowsInserted(0, rowCount);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if( aValue == null )
			return;
		
		switch(rowIndex) {
			case NAME:
				treeNode.setName( String.valueOf(aValue) );
				treeModel.reload( mutableTreeNode );
				break;
			case DESCRIPTION:
				treeNode.setDescription( String.valueOf(aValue) );
				break;
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
    }

	public int getRowCount() {		
		return rowCount;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object value = null;
		
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
				break;
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
