package com.felipelo.etlgui.schema;

import br.com.saxes.suite.converter.ValueType;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author felipe
 */
public class TextPropertyCellEditor extends PropertyCellEditor {
	
	private JComboBox comp;
	
	@Override
	public Object getCellEditorValue() {
		if( comp != null ) {
			Object _value = comp.getSelectedItem();
			comp = null;
			return _value;
		} else {
			return super.getCellEditorValue();
		}
	}
	
	@Override
	public Component getTableCellEditorComponent(
			JTable table, 
			Object value, 
			boolean isSelected, 
			int row, 
			int column) 
	{
		if( row == 2 ) {
			comp = new JComboBox();
			comp.setFont(table.getFont());
			comp.setBorder(null);
			for( ValueType _valueType : ValueType.values() ) {
				comp.addItem( _valueType );
			}
			comp.setSelectedItem(value);
			comp.addItemListener( new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					stopCellEditing();
				}
			});
			
			return comp;
		} else {
			return super.getTableCellEditorComponent(table, value, isSelected, row, column);
		}
	}
	
}
