package com.felipelo.etlgui;

import br.com.saxes.suite.model.txt.DelimitedTXTTreeSchema;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author felipe
 */
public class DelimitedTXTTreeNode extends DefaultMutableTreeNode {
	
	private DelimitedTXTTreeSchema node;
	
	public DelimitedTXTTreeNode() {
		node = new DelimitedTXTTreeSchema();
		node.setName("No name");
	}
}
