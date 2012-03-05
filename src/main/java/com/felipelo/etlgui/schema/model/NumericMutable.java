package com.felipelo.etlgui.schema.model;

import br.com.saxes.suite.model.NumericTreeNode;

/**
 *
 * @author felipe.lorenz
 */
public class NumericMutable extends TreeNodeMutable {
	
	private static final int ROW_COUNT = 4;
	
	public NumericMutable( NumericTreeNode numericTreeNode ) {
		super(ROW_COUNT, numericTreeNode);
	}
	
}
