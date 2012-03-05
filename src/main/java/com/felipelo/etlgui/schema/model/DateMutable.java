package com.felipelo.etlgui.schema.model;

import br.com.saxes.suite.model.DateTreeNode;

/**
 *
 * @author felipe.lorenz
 */
public class DateMutable extends TreeNodeMutable {
	
	private final static int ROW_COUNT = 4;
	
	public DateMutable( DateTreeNode dateTreeNode ) {
		super(ROW_COUNT, dateTreeNode);
	}
}
