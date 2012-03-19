package com.felipelo.etlgui.schema.txt;

import br.com.saxes.suite.model.TextTreeNode;
import br.com.saxes.suite.model.TreeSchema;
import br.com.saxes.suite.model.txt.DelimitedTXTTreeSchema;
import br.com.saxes.suite.model.txt.FileModelStrategy;
import br.com.saxes.suite.model.txt.LineTreeNode;
import com.felipelo.etlgui.schema.PropertyTableModel;
import com.felipelo.etlgui.schema.TreeSchemaEditor;
import com.felipelo.etlgui.schema.model.TextMutable;
import com.felipelo.etlgui.schema.model.TreeNodeMutable;
import com.felipelo.etlgui.schema.model.txt.DelimitedTXTMutable;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author felipe.lorenz
 */
public class TXTTreeSchemaEditor extends TreeSchemaEditor {
	
	private Object[] possibilities = {
		FileModelStrategy.DELIMITED, 
		FileModelStrategy.FIXED_LENGHT };
	
	public TXTTreeSchemaEditor() {
		super();
	}

	@Override
	public TreeNodeMutable addNode(DefaultMutableTreeNode selectedNode) {
		Object _userObj = selectedNode.getUserObject();
		
		if( _userObj instanceof LineTreeNode ) {
			LineTreeNode _line = (LineTreeNode) _userObj;
			
			TextTreeNode _newTextNode = new TextTreeNode();
			_newTextNode.setName("New Node");
			
			_line.addChild( _newTextNode );
			
			TextMutable _newNode = new TextMutable(_newTextNode, new TextNodePropTableModel(treeModel));
			
			return _newNode;
		} else if( _userObj instanceof TreeSchema ) {
			TreeSchema _treeSchema = (TreeSchema) _userObj;
			
			LineTreeNode _line = new LineTreeNode();
			_line.setName("Line");
			
			_treeSchema.setRoot(_line);
			
			TreeNodeMutable _mLine = new TreeNodeMutable(_line, new PropertyTableModel(treeModel));
			
			return _mLine;
		}
		
		return null;
	}
	
	@Override
	public TreeNodeMutable startNewSchema() {
		FileModelStrategy strategy = (FileModelStrategy) JOptionPane.showInputDialog(
				this, 
				"Select the file model stategy:", 
				"File Model Strategy", 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				possibilities, 
				possibilities[0]);
		
		if( strategy == null )
			return null;
		
		TreeNodeMutable _treeNodeMutable = null;
		
		switch(strategy) {
			case DELIMITED:
				DelimitedTXTTreeSchema _treeRoot = new DelimitedTXTTreeSchema();
				_treeRoot.setName("Delimited Txt Schema");
				
				_treeNodeMutable = new DelimitedTXTMutable(_treeRoot, new DelimitedTXTTreeSchemaPropTableModel(treeModel));
				break;
			case FIXED_LENGHT:
				break;
		}
		
		return _treeNodeMutable;
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(TreeSchemaEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {				
				TreeSchemaEditor d = new TXTTreeSchemaEditor();
				d.setLocationRelativeTo(null);
				d.setVisible(true);
			}
		});
	}
	
}
