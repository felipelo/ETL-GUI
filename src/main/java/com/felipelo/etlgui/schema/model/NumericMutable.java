package com.felipelo.etlgui.schema.model;

import br.com.saxes.suite.model.NumericTreeNode;
import com.felipelo.etlgui.schema.txt.NumericNodePropTableModel;

/**
 *
 * @author felipe.lorenz
 */
public class NumericMutable extends TreeNodeMutable {
	
	private static final int ROW_COUNT = 4;
	
	public NumericMutable( NumericTreeNode numericTreeNode, NumericNodePropTableModel propTableModel ) {
		super(ROW_COUNT, numericTreeNode, propTableModel);
	}
	
}
