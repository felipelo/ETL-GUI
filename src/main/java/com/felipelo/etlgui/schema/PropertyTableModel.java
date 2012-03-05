package com.felipelo.etlgui.schema;

import br.com.saxes.suite.converter.ValueType;
import br.com.saxes.suite.model.DateTreeNode;
import br.com.saxes.suite.model.NumericTreeNode;
import br.com.saxes.suite.model.TextTreeNode;
import br.com.saxes.suite.model.TreeNode;
import br.com.saxes.suite.model.txt.DelimitedTXTTreeSchema;
import br.com.saxes.suite.model.txt.TXTTreeSchema;
import com.felipelo.etlgui.schema.model.DateMutable;
import com.felipelo.etlgui.schema.model.NumericMutable;
import com.felipelo.etlgui.schema.model.TextMutable;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
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
			case 2:
				if( treeNode instanceof TextTreeNode ) {					
					TextTreeNode _textTreeNode = (TextTreeNode) treeNode;
					if( _textTreeNode.getValueType() != aValue) {
						DefaultMutableTreeNode _parent = (DefaultMutableTreeNode) mutableTreeNode.getParent();
						int _index = treeModel.getIndexOfChild( _parent, mutableTreeNode );
						DefaultMutableTreeNode _oldMutable = mutableTreeNode;
						switch((ValueType)aValue) {
							case DATE:
								DateTreeNode _dateTN = new DateTreeNode();
								_dateTN.setId( _textTreeNode.getId() );
								_dateTN.setName( _textTreeNode.getName() );
								_dateTN.setDescription( _textTreeNode.getDescription() );
								_dateTN.setParentTreeNode( _textTreeNode.getParentTreeNode() );
								mutableTreeNode = new DateMutable( _dateTN );
								this.treeNode = _dateTN;
								break;
							case NUMERIC:
								NumericTreeNode _numTN = new NumericTreeNode();
								_numTN.setId( _textTreeNode.getId() );
								_numTN.setName( _textTreeNode.getName() );
								_numTN.setDescription( _textTreeNode.getDescription() );
								_numTN.setParentTreeNode( _textTreeNode.getParentTreeNode() );
								mutableTreeNode = new NumericMutable( _numTN );
								this.treeNode = _numTN;
								break;
						}
						
						treeModel.insertNodeInto( mutableTreeNode, _parent, _index );
						treeModel.removeNodeFromParent( _oldMutable );
					}					
				}
				break;
			case 3:
				if( treeNode instanceof DateTreeNode ) {
					DateTreeNode _dateTN = (DateTreeNode) treeNode;
					_dateTN.setFieldDateFormat( String.valueOf(aValue) );
				} else if( treeNode instanceof NumericTreeNode ) {
					NumericTreeNode _numTN = (NumericTreeNode) treeNode;
					_numTN.setFieldNumFormat( String.valueOf(aValue) );
				}
				break;
			case 4:
				if( treeNode instanceof DelimitedTXTTreeSchema )
					((DelimitedTXTTreeSchema)treeNode).setLineDelimiter(String.valueOf(aValue));
				break;
			case 5:
				if( treeNode instanceof DelimitedTXTTreeSchema )
					((DelimitedTXTTreeSchema)treeNode).setColumnDelimiter(String.valueOf(aValue));
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
				case 3:
					if( treeNode instanceof DateTreeNode ) {
						return "Date Format";
					} else if( treeNode instanceof NumericTreeNode ) {
						return "Number Format";
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
			case 3:
				if( treeNode instanceof DateTreeNode ) {
					value = ((DateTreeNode)treeNode).getFieldDateFormat();
				} else if( treeNode instanceof NumericTreeNode ) {
					value = ((NumericTreeNode)treeNode).getFieldNumFormat();
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
