package com.felipelo.etlgui;

import br.com.saxes.suite.model.TreeNode;
import br.com.saxes.suite.model.TreeSchema;
import br.com.saxes.suite.model.txt.LineTreeNode;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author felipe
 */
public class DelimitedTXTTreeCellRenderer extends DefaultTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		DefaultMutableTreeNode _treeNode = (DefaultMutableTreeNode) value;
		Object _userObj = _treeNode.getUserObject();
		
		if( _userObj instanceof TreeNode ) {
			setText( ((TreeNode)_userObj).getName() );
		}
		
		
		return this;
	}
	
}
