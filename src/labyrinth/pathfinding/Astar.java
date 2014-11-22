package labyrinth.pathfinding;

import java.util.ArrayList;

/**
 * This class is the abstract class of the A* algorithm.
 * In contains the code required to implement the pathfinding.
 * @author Daniel Castaño Estrella
 *
 * @param <T> Type of cell: square, hexagonal, triangular.
 * @param <U> Type of labyrinth builder: square, hexagonal, triangular.
 */
public abstract class Astar<T,U>
{
	protected ArrayList<Node> open_list = new ArrayList<Node>();
	protected ArrayList<Node> closed_list = new ArrayList<Node>();
	protected Node[][] open_array;
	protected boolean[][] closed_array;
	
	protected boolean[][] path;
	protected boolean astarloop = true;
	
	//protected ArrayList<Node> list = new ArrayList<Node>();
	
	/**
	 * Instantiates a new astar. The constructor initializes the class variables.
	 */
	public Astar(U mylab, int[]a_start, int[]a_end)
	{
		setArrayLenghts(mylab);
		Node current_node;
		
		open_list.add(new Node(a_start, a_end));
		
		//if you are trying to not move of your position...
		if(a_start[0] == a_end[0] && a_start[1] == a_end[1])
		{
			closed_list.add(new Node(a_start, a_end));
			path[a_end[0]][a_end[1]] = true;
			astarloop = false;
		}
		
		while(astarloop)
		{
			int pos = 0;
			int f = open_list.get(0).getF();
			
			for(int i = 0 ; i < open_list.size() ; i++)	//current_node will be the one with less F
			{
				if(open_list.get(i).getF() < f)
				{
					f = open_list.get(i).getF();
					pos = i;
				}
			}
			
			current_node = open_list.get(pos);
			closed_list.add(current_node);
			closed_array[current_node.getX()][current_node.getY()] = true;
			open_list.remove(pos);
			open_array[current_node.getX()][current_node.getY()] = null;
			
			checkNeighbours(current_node,mylab);
		}
		
		recursivePath(closed_list.get(closed_list.size()-1));
		
	}
	
	/**
	 * Method used to set the length of arrays needed for the A* algorithm.
	 * The A* subclasses implements that knowing the U type.
	 * @param mylab The labyrinth where I'm looking for the path.
	 */
	protected abstract void setArrayLenghts(U mylab);
	
	/**
	 * Method used to wee who are the neighbors of the actual node (room or cell).
	 * @param current_node Current node which neighbors seek.
	 * @param mylab The labyrinth where I'm looking for the path.
	 */
	protected abstract void checkNeighbours(Node current_node, U mylab);
	
	/**
	 * Method where we check if the current cell isn't in the open list
	 * or if the new path to that node is better. If one of those two conditions
	 * are true, we put the node into the open list.
	 * @param current_node Current node for knowing the G cost of the new possible node. That's current node G + 1.
	 * @param newcell The cell needed to do the check process and creating the node.
	 */
	protected abstract void openListCheck(Node current_node, T newcell);
	
	/**
	 * Recursive method that fills the path array. A boolean two-dimensional (places with x and y coordinates) array
	 * that set its positions to true if there is a path node in its position.
	 * @param current_node Current node which neighbors seek.
	 */
	protected void recursivePath(Node current_node)
	{
		path[current_node.getX()][current_node.getY()] = true;
		
		//list.add(current_node);
		
		if(current_node.getParent() != null)
		{
			recursivePath(current_node.getParent());
		}
	}
	
	/**
	 * Mehod that returns if in the position (X,Y) of the labyrinth is a node of the path.
	 * @param x X coordinate.
	 * @param y Y ordinate.
	 * @return Return true if it's part of the path, false if it isn't.
	 */
	public boolean getPos(int x, int y)
	{
		return path[x][y];
	}
	
	/**
	 * Getter of the start position of the path.
	 * @return The position.
	 */
	public int[] getStart(){return closed_list.get(0).getPosition();}
	
	/**
	 * Getter of the final position of the path.
	 * @return The position.
	 */
	public int[] getEnd(){return closed_list.get(closed_list.size()-1).getPosition();}
	
	/*
	@Override
	public String toString() {
		String a = "\nastar list=";

		for(int i = 0 ; i < list.size() ; i++)
		{
			a += "\npos" + i + " X: " + list.get(i).getX() + "   Y: " + list.get(i).getY();
		}
		return a;
	}
	*/
}
