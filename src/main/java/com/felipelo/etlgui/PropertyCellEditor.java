package com.felipelo.etlgui;

import java.awt.Component;
import java.awt.Insets;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author felipe
 */
public class PropertyCellEditor extends AbstractCellEditor implements TableCellEditor {

	private Component value;
	
	public Object getCellEditorValue() {
		return ((JTextField)value).getText();
	}


	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		System.out.println(row + "::" + column);
		
		JTextField _jTextField = new JTextField();
		_jTextField.setBorder(null);
		_jTextField.setFont(table.getFont());
		_jTextField.setText((String)value);
		
		this.value = _jTextField;
		
		return this.value;
	}
	
}
