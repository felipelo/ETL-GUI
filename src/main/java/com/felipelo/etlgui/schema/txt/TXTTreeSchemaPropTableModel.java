package com.felipelo.etlgui.schema.txt;

import br.com.saxes.suite.model.txt.TXTTreeSchema;
import com.felipelo.etlgui.schema.PropertyTableModel;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author felipe.lorenz
 */
public class TXTTreeSchemaPropTableModel extends PropertyTableModel {
	
	public TXTTreeSchemaPropTableModel( DefaultTreeModel treeModel ) {
		super(treeModel);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		 if (aValue == null)
			return;
		
		TXTTreeSchema _treeSchema = (TXTTreeSchema)treeNode;
			
		switch(rowIndex) {
			case 2:
				_treeSchema.getFileRef().setFilePath(String.valueOf(aValue));
				break;
			case 3:
				_treeSchema.setFieldQualifier(String.valueOf(aValue));
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
					return "File Reference";
				case 3:
					return "Field Qualifier";
					
			}
		}
		
		if( treeNode == null ) {
			return value;
		}
		
		TXTTreeSchema _treeSchema = (TXTTreeSchema)treeNode;
		
		switch(rowIndex) {
			case 2:
				value = _treeSchema.getFileRef().getFilePath();
				break;
			case 3:
				value = _treeSchema.getFieldQualifier();
				break;
				
		}
		
		return value;
	}
	
}
