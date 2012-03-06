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
public class NumericNodePropTableModel extends PropertyTableModel {
	
	public NumericNodePropTableModel( DefaultTreeModel treeModel ) {
		super(treeModel);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		 if (aValue == null)
			return;
		 
		 NumericTreeNode _numericTreeNode = (NumericTreeNode) treeNode;
		 
		 switch(rowIndex) {
			case 2:
				if( _numericTreeNode.getValueType() != aValue) {
					DefaultMutableTreeNode _parent = (DefaultMutableTreeNode) mutableTreeNode.getParent();
					int _index = treeModel.getIndexOfChild( _parent, mutableTreeNode );
					DefaultMutableTreeNode _oldMutable = mutableTreeNode;
					
					switch((ValueType)aValue) {
						case TEXT:
							TextTreeNode _textTN = new TextTreeNode();
							_textTN.setId( _numericTreeNode.getId() );
							_textTN.setName( _numericTreeNode.getName() );
							_textTN.setDescription( _numericTreeNode.getDescription() );
							_textTN.setParentTreeNode( _numericTreeNode.getParentTreeNode() );

							mutableTreeNode = new TextMutable( _textTN );
							this.treeNode = _textTN;

							break;
							case DATE:
							DateTreeNode _dateTN = new DateTreeNode();
							_dateTN.setId( _numericTreeNode.getId() );
							_dateTN.setName( _numericTreeNode.getName() );
							_dateTN.setDescription( _numericTreeNode.getDescription() );
							_dateTN.setParentTreeNode( _numericTreeNode.getParentTreeNode() );
							
							mutableTreeNode = new DateMutable( _dateTN );
							this.treeNode = _dateTN;
							
							break;
					}
					
					treeModel.insertNodeInto( mutableTreeNode, _parent, _index );
					treeModel.removeNodeFromParent( _oldMutable );
				}
				break;
			case 3:
				_numericTreeNode.setFieldNumFormat( String.valueOf(aValue) );
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
					return "Number Format";
			}
		}
		
		if( treeNode == null ) {
			return value;
		}
		
		NumericTreeNode _numericTN = (NumericTreeNode)treeNode;
		
		switch(rowIndex) {
			case 2:
				value = _numericTN.getValueType();
				break;
			case 3:
				value = _numericTN.getFieldNumFormat();
		}
		
		return value;
	}
	
}
