package com.felipelo.etlgui.schema;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author felipe
 */
public class PropertyCellEditor 
		extends AbstractCellEditor 
		implements TableCellEditor 
{
	private JTextField value;
	
	public Object getCellEditorValue() {		
		return value.getText();
	}


	public Component getTableCellEditorComponent(
			JTable table, 
			Object value, 
			boolean isSelected, 
			int row, 
			int column) 
	{
		JTextField _jTextField = new JTextField();
		_jTextField.setBorder(null);
		_jTextField.setFont(table.getFont());
		_jTextField.setText((String)value);
		_jTextField.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopCellEditing();
			}
		});
		this.value = _jTextField;
		
		return this.value;
	}
	
}
