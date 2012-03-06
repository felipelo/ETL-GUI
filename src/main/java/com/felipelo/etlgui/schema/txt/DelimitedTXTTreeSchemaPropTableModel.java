package com.felipelo.etlgui.schema.txt;

import br.com.saxes.suite.model.txt.DelimitedTXTTreeSchema;
import br.com.saxes.suite.model.txt.TXTTreeSchema;
import com.felipelo.etlgui.schema.PropertyTableModel;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author felipe.lorenz
 */
public class DelimitedTXTTreeSchemaPropTableModel extends PropertyTableModel {
	
	public DelimitedTXTTreeSchemaPropTableModel( DefaultTreeModel treeModel ) {
		super(treeModel);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		 if (aValue == null)
			return;
		
		DelimitedTXTTreeSchema _treeSchema = (DelimitedTXTTreeSchema)treeNode;
			
		switch(rowIndex) {
			case 2:
				_treeSchema.getFileRef().setFilePath(String.valueOf(aValue));
				break;
			case 3:
				_treeSchema.setFieldQualifier(String.valueOf(aValue));
				break;
			case 4:
				_treeSchema.setLineDelimiter(String.valueOf(aValue));
				break;
			case 5:
				_treeSchema.setColumnDelimiter(String.valueOf(aValue));
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
					return "File Reference";
				case 3:
					return "Field Qualifier";
				case 4:
					return "Line Delimiter";
				case 5:
					return "Column Delimiter";
			}
		}
		
		if( treeNode == null ) {
			return value;
		}
		
		DelimitedTXTTreeSchema _treeSchema = (DelimitedTXTTreeSchema)treeNode;
		
		switch(rowIndex) {
			case 2:
				value = _treeSchema.getFileRef().getFilePath();
				break;
			case 3:
				value = _treeSchema.getFieldQualifier();
				break;
			case 4:
				value = _treeSchema.getLineDelimiter();
				break;
			case 5:
				value = _treeSchema.getColumnDelimiter();
				break;
		}
		
		return value;
	}
	
}
