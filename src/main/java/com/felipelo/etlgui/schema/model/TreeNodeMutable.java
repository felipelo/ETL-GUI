package com.felipelo.etlgui.schema.model;

import br.com.saxes.suite.model.TreeNode;
import com.felipelo.etlgui.schema.PropertyTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author felipe.lorenz
 */
public class TreeNodeMutable extends DefaultMutableTreeNode {
	
	private final static int ROW_COUNT = 2;
	
	private int rowCount;
	private PropertyTableModel propTableModel;
	
	public TreeNodeMutable(TreeNode object, PropertyTableModel propTableModel) {
		this(ROW_COUNT, object, propTableModel);
	}
	
	protected TreeNodeMutable(int rowCount, TreeNode object, PropertyTableModel propTableModel) {
		super();
		this.rowCount = rowCount;
		this.userObject = object;
		this.propTableModel = propTableModel;
	}
	
	public int getRowCount() { return rowCount; }
	
	public PropertyTableModel getPropTableModel() { return propTableModel; }
	
}
