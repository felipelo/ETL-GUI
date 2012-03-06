package com.felipelo.etlgui.schema.txt;

import br.com.saxes.suite.converter.ValueType;
import br.com.saxes.suite.model.DateTreeNode;
import br.com.saxes.suite.model.NumericTreeNode;
import br.com.saxes.suite.model.TextTreeNode;
import com.felipelo.etlgui.schema.PropertyTableModel;
import com.felipelo.etlgui.schema.model.DateMutable;
import com.felipelo.etlgui.schema.model.NumericMutable;
import com.felipelo.etlgui.schema.model.TextMutable;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Felipe
 */
public class DateNodePropTableModel extends PropertyTableModel {
	
	public DateNodePropTableModel( DefaultTreeModel treeModel ) {
		super(treeModel);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		 if (aValue == null)
			return;
		 
		 DateTreeNode _dateTreeNode = (DateTreeNode) treeNode;
		 
		 switch(rowIndex) {
			case 2:
				if( _dateTreeNode.getValueType() != aValue) {
					DefaultMutableTreeNode _parent = (DefaultMutableTreeNode) mutableTreeNode.getParent();
					int _index = treeModel.getIndexOfChild( _parent, mutableTreeNode );
					DefaultMutableTreeNode _oldMutable = mutableTreeNode;
					
					switch((ValueType)aValue) {
						case TEXT:
							TextTreeNode _textTN = new TextTreeNode();
							_textTN.setId( _dateTreeNode.getId() );
							_textTN.setName( _dateTreeNode.getName() );
							_textTN.setDescription( _dateTreeNode.getDescription() );
							_textTN.setParentTreeNode( _dateTreeNode.getParentTreeNode() );

							mutableTreeNode = new TextMutable( _textTN );
							this.treeNode = _textTN;

							break;
						case NUMERIC:
							NumericTreeNode _numTN = new NumericTreeNode();
							_numTN.setId( _dateTreeNode.getId() );
							_numTN.setName( _dateTreeNode.getName() );
							_numTN.setDescription( _dateTreeNode.getDescription() );
							_numTN.setParentTreeNode( _dateTreeNode.getParentTreeNode() );
							
							mutableTreeNode = new NumericMutable( _numTN );
							this.treeNode = _numTN;
							
							break;
					}
					
					treeModel.insertNodeInto( mutableTreeNode, _parent, _index );
					treeModel.removeNodeFromParent( _oldMutable );
				}
				break;
			case 3:
				_dateTreeNode.setFieldDateFormat( String.valueOf(aValue) );
				break;
		 }
		 super.setValueAt(aValue, rowIndex, columnIndex);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value;
		
		value = super.getValueAt(rowIndex, columnIndex);
		if( value != null )
			return value;
		
		if( columnIndex == 0 ) {
			switch(rowIndex) {
				case 2:
					return "Value Type";
				case 3:
					return "Date Format";
					
			}
		}
		
		if( treeNode == null ) {
			return value;
		}
		
		DateTreeNode _dateTN = (DateTreeNode)treeNode;
		
		switch(rowIndex) {
			case 2:
				value = _dateTN.getValueType();
				break;
			case 3:
				value = _dateTN.getFieldDateFormat();
				break;
		}
		
		return value;
	}
}
