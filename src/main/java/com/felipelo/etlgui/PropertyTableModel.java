package com.felipelo.etlgui;

import br.com.saxes.suite.converter.ValueType;
import br.com.saxes.suite.model.DateTreeNode;
import br.com.saxes.suite.model.TextTreeNode;
import br.com.saxes.suite.model.TreeNode;
import br.com.saxes.suite.model.txt.DelimitedTXTTreeSchema;
import br.com.saxes.suite.model.txt.TXTTreeSchema;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author felipe
 */
public class PropertyTableModel extends AbstractTableModel {
	
	private final static int NAME = 0;
	private final static int DESCRIPTION = 1;
	
	private DefaultMutableTreeNode mutableTreeNode;
	private TreeNode treeNode;
	
	private String columns[] = {"Property", "Value"};
	private int rowCount;
	
	private DefaultTreeModel treeModel;
	
	public PropertyTableModel( DefaultTreeModel treeModel ) {
		super();
		rowCount = 0;
		this.treeModel = treeModel;
	}
	
	public void setTreeNode( DefaultMutableTreeNode mutableTreeNode ) {
		this.mutableTreeNode = mutableTreeNode;
		
		TreeNode _treeNode = (TreeNode) mutableTreeNode.getUserObject();
		this.treeNode = _treeNode;
		
		if( treeNode == null ) {
			rowCount = 0;
		} else if( treeNode instanceof DelimitedTXTTreeSchema ) {
			rowCount = 6;
		} else if( treeNode instanceof TXTTreeSchema ) {
			rowCount = 4;
		} else if( treeNode instanceof DateTreeNode ) {
			rowCount = 4;
		} else if( treeNode instanceof TextTreeNode ) {
			rowCount = 3;
		} else {
			rowCount = 2;
		}
		
		fireTableRowsInserted(0, rowCount);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if( aValue == null )
			return;
		
		switch(rowIndex) {
			case NAME:
				treeNode.setName( (String)aValue );
				break;
			case DESCRIPTION:
				treeNode.setDescription( (String)aValue );
				break;
			case 2:
				if( treeNode instanceof TXTTreeSchema ) {
					((TXTTreeSchema)treeNode).getFileRef().setFilePath((String)aValue);
				} else if( treeNode instanceof TextTreeNode ) {					
					TextTreeNode _textTreeNode = (TextTreeNode) treeNode;
					if( _textTreeNode.getValueType() != aValue) {
						DefaultMutableTreeNode _parent = (DefaultMutableTreeNode) mutableTreeNode.getParent();
						int _index = treeModel.getIndexOfChild( _parent, mutableTreeNode );
						treeModel.removeNodeFromParent( mutableTreeNode );
						switch((ValueType)aValue) {
							case TEXT:
								TextTreeNode _textTN = new TextTreeNode();
								_textTN.setId( _textTreeNode.getId() );
								_textTN.setName( _textTreeNode.getName() );
								_textTN.setDescription( _textTreeNode.getDescription() );
								_textTN.setParentTreeNode( _textTreeNode.getParentTreeNode() );
								mutableTreeNode = new DefaultMutableTreeNode( _textTN );
								this.treeNode = _textTN;
								break;
							case DATE:
								DateTreeNode _dateTN = new DateTreeNode();
								_dateTN.setId( _textTreeNode.getId() );
								_dateTN.setName( _textTreeNode.getName() );
								_dateTN.setDescription( _textTreeNode.getDescription() );
								_dateTN.setParentTreeNode( _textTreeNode.getParentTreeNode() );
								mutableTreeNode = new DefaultMutableTreeNode( _dateTN );
								this.treeNode = _dateTN;
								break;
						}
						
						treeModel.insertNodeInto( mutableTreeNode, _parent, _index );
					}					
				}
				break;
			case 3:
				if( treeNode instanceof TXTTreeSchema ) {
					((TXTTreeSchema)treeNode).setFieldQualifier((String)aValue);
				} else if( treeNode instanceof DateTreeNode ) {
					DateTreeNode _dateTN = (DateTreeNode) treeNode;
					_dateTN.setFieldDateFormat( (String)aValue );
				}
				break;
			case 4:
				if( treeNode instanceof DelimitedTXTTreeSchema )
					((DelimitedTXTTreeSchema)treeNode).setLineDelimiter((String)aValue);
				break;
			case 5:
				if( treeNode instanceof DelimitedTXTTreeSchema )
					((DelimitedTXTTreeSchema)treeNode).setColumnDelimiter((String)aValue);
				break;
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
		treeModel.reload( mutableTreeNode );
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
				case 2:
					if( treeNode instanceof TXTTreeSchema ) {
						return "File Reference";
					} else if( treeNode instanceof TextTreeNode ) {
						return "Value Type";
					}
				case 3:
					if( treeNode instanceof TXTTreeSchema ) {
						return "Field Qualifier";
					} else if( treeNode instanceof DateTreeNode ) {
						return "Date Format";
					}
				case 4:
					if( treeNode instanceof DelimitedTXTTreeSchema )
						return "Line Delimiter";
				case 5:
					if( treeNode instanceof DelimitedTXTTreeSchema )
						return "Column Delimiter";
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
			case 2:
				if( treeNode instanceof TXTTreeSchema ) {
					value = ((TXTTreeSchema)treeNode).getFileRef().getFilePath();
				} else if( treeNode instanceof TextTreeNode ) {
					value = ((TextTreeNode)treeNode).getValueType();
				}
				break;
			case 3:
				if( treeNode instanceof TXTTreeSchema ) {
					value = ((TXTTreeSchema)treeNode).getFieldQualifier();
				} else if( treeNode instanceof DateTreeNode ) {
					value = ((DateTreeNode)treeNode).getFieldDateFormat();
				}
				break;
			case 4:
				if( treeNode instanceof DelimitedTXTTreeSchema )
					value = ((DelimitedTXTTreeSchema)treeNode).getLineDelimiter();
				break;
			case 5:
				if( treeNode instanceof DelimitedTXTTreeSchema )
					value = ((DelimitedTXTTreeSchema)treeNode).getColumnDelimiter();
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
