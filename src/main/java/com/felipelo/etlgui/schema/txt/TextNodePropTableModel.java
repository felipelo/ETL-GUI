package com.felipelo.etlgui.schema.txt;

import br.com.saxes.suite.model.TextTreeNode;
import br.com.saxes.suite.model.txt.TXTTreeSchema;
import com.felipelo.etlgui.schema.PropertyTableModel;
import com.felipelo.etlgui.schema.model.TextMutable;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author felipe.lorenz
 */
public class TextNodePropTableModel extends PropertyTableModel {
	
	public TextNodePropTableModel( DefaultTreeModel treeModel ) {
		super(treeModel);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		 if (aValue == null)
			return;
		 
		 TextTreeNode _textTreeNode = (TextTreeNode) treeNode;
		 
		 switch(rowIndex) {
			case 2:
				if( _textTreeNode.getValueType() != aValue) {
					DefaultMutableTreeNode _parent = (DefaultMutableTreeNode) mutableTreeNode.getParent();
					int _index = treeModel.getIndexOfChild( _parent, mutableTreeNode );
					DefaultMutableTreeNode _oldMutable = mutableTreeNode;
					 
					TextTreeNode _textTN = new TextTreeNode();
					_textTN.setId( _textTreeNode.getId() );
					_textTN.setName( _textTreeNode.getName() );
					_textTN.setDescription( _textTreeNode.getDescription() );
					_textTN.setParentTreeNode( _textTreeNode.getParentTreeNode() );
					 
					 mutableTreeNode = new TextMutable( _textTN );
					this.treeNode = _textTN;
					
					treeModel.insertNodeInto( mutableTreeNode, _parent, _index );
					treeModel.removeNodeFromParent( _oldMutable );
				}
				break;
				
		 }
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value;
		
		value = super.getValueAt(rowIndex, columnIndex);
		if( value != null )
			return value;
		
		if( columnIndex == 0 ) {
			switch(rowIndex) {
				case 2:
					return "Value Type";
			}
		}
		
		if( treeNode == null ) {
			return value;
		}
		
		TextTreeNode _textTN = (TextTreeNode)treeNode;
		
		switch(rowIndex) {
			case 2:
				value = _textTN.getValueType();
				break;
		}
		
		return value;
	}
	
}
