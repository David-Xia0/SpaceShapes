package spaceshapes.views;


import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelListener;

public class Task2 extends Task1 implements ShapeModelListener{

	public Task2(ShapeModel model) {
		super(model);
	}

	//update is called from within fire()  ShapeModel, 
	public void update(ShapeModelEvent event) {

		int[] index = {event.index()};//index position oft he operand
		Object[] children = {event.operand()};
		Object source = event.source();//returns the shapemodel
		List<Shape> parents= new ArrayList<Shape>();
		if(event.parent()!=null) {
		parents.addAll(event.parent().path());
		}


		//convert arraylist into array
		Object[] path = new Object[parents.size()];
		for(int i=0;i<parents.size();i++) {
			path[i]=parents.get(i);
		}


		//checks for they type of event that happeneed. and updates for all tree listeners
		if(event.eventType().toString()=="ShapeRemoved") {
			for(TreeModelListener listener:_l) {
				listener.treeNodesRemoved(new TreeModelEvent(source,path,index,children));
			}
		}else if(event.eventType().toString()=="ShapeAdded") {
			for(TreeModelListener listener:_l) {
				listener.treeNodesInserted(new TreeModelEvent(source,path,index,children));
			}
		}
	}
}
