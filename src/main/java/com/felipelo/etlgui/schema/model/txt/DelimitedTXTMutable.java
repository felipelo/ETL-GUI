package com.felipelo.etlgui.schema.model.txt;

import br.com.saxes.suite.model.txt.DelimitedTXTTreeSchema;
import com.felipelo.etlgui.schema.model.TreeNodeMutable;
import com.felipelo.etlgui.schema.txt.DelimitedTXTTreeSchemaPropTableModel;

/**
 *
 * @author felipe.lorenz
 */
public class DelimitedTXTMutable extends TreeNodeMutable {
	
	private final static int ROW_COUNT = 6;
	
	public DelimitedTXTMutable( DelimitedTXTTreeSchema treeSchema, DelimitedTXTTreeSchemaPropTableModel propTableModel ) {
		super(ROW_COUNT, treeSchema, propTableModel);
	}
	
}
