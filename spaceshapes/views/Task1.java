package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spaceshapes.*;

public class Task1 implements TreeModel {

	private ShapeModel _shapeModel;
	
	//stores treeModel listeners
	protected List<TreeModelListener> _l =new ArrayList<TreeModelListener>();
	
	
	public Task1(ShapeModel model) {
		_shapeModel=model;
	}
	

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		_l.add(l);
		
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof CarrierShape) {
			try {
				return((CarrierShape)parent).shapeAt(index);
			}catch(IndexOutOfBoundsException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof CarrierShape) {
			return ((CarrierShape)parent).shapeCount();
		}
		return 0;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent instanceof CarrierShape && child instanceof Shape) {
			return ((CarrierShape)parent).indexOf((Shape)child);
		}
		return -1;
	}

	@Override
	public Object getRoot() {
		return _shapeModel.root();
	}

	@Override
	public boolean isLeaf(Object node) {
		return !(node instanceof CarrierShape);//carriershape is our composite, if something is carriershape, then it is not a leaf
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {	
		_l.remove(l);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

}
