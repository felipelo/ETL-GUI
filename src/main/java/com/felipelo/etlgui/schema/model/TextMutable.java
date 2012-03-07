package com.felipelo.etlgui.schema.model;

import br.com.saxes.suite.model.TextTreeNode;
import com.felipelo.etlgui.schema.txt.TextNodePropTableModel;

/**
 *
 * @author felipe.lorenz
 */
public class TextMutable extends TreeNodeMutable {
	
	private static final int ROW_COUNT = 3;
	
	public TextMutable( TextTreeNode textTreeNode, TextNodePropTableModel propTableModel ) {
		super(ROW_COUNT, textTreeNode, propTableModel);
	}
}
