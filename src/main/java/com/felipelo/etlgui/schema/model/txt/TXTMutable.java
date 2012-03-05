package com.felipelo.etlgui.schema.model.txt;

import br.com.saxes.suite.model.TextTreeNode;
import com.felipelo.etlgui.schema.model.TreeNodeMutable;

/**
 *
 * @author felipe.lorenz
 */
public class TXTMutable extends TreeNodeMutable {
	
	private final static int ROW_COUNT = 4;
	
	public TXTMutable( TextTreeNode textTreeNode ) {
		super(ROW_COUNT, textTreeNode);
	}
	
}
