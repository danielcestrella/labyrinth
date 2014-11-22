package labyrinth.pathfinding;

import labyrinth.builder.Place;

/**
 * This class save the node information.
 * Save the parent node, calculates the values of the node and allow other classes to know them.
 * The end variable allow the class to calculate the heuristic value.
 * @author Daniel Castaño Estrella
 *
 */
public class Node extends Place
{
	private Node father;
	private int g;
	private int h;
	private int f;
	
	private int[] end;
	
	/**
	 * Constructor of the class. Initializes the variables.
	 * @param pos position.
	 * @param parent parent node.
	 */
	public Node(int[] pos, Node parent)
	{
		super(pos[0],pos[1]);
		
		this.father = parent;
		end = parent.getEnd();
		
		position[0] = pos[0];	//column
		position[1] = pos[1];	//row
		
		g = parent.getG() + 1;
		h = Math.abs(end[0] - pos[0]) + Math.abs(end[1] - pos[1]);
		f = g+h;
	}
	
	/**
	 * Constructor of the first node without parent. Initializes the variables
	 * @param pos position.
	 * @param end end position of the path.
	 */
	public Node(int[] pos, int[] end)
	{
		super(pos[0],pos[1]);
		
		this.end = end;
		
		position[0] = pos[0];	//column
		position[1] = pos[1];	//row
		
		g = 0;
		h = Math.abs(end[0] - pos[0]) + Math.abs(end[1] - pos[1]);
		f = g+h;
	}
	
	/**
	 * Getter of the H value.
	 * @return H
	 */
	public int getH(){return h;}
	
	/**
	 * Getter of the G value.
	 * @return G
	 */
	public int getG(){return g;}
	
	/**
	 * Getter of the F value.
	 * @return F
	 */
	public int getF(){return f;}
	
	/**
	 * Getter of the parent node.
	 * @return the parent node
	 */
	public Node getParent(){return father;}
	
	/**
	 * Getter of the end position of the path.
	 * @return the end place
	 */
	public int[] getEnd(){return end;}
}
