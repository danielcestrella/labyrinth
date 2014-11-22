package labyrinth.builder;

/**
 * Abstract class that implements the logics of the cells.
 * @author Daniel Castaño Estrella
 *
 */
public abstract class Cell extends Place
{   
	protected Boolean[] walls;
	protected boolean visited;
	
	/**
	 * Constructor method. Initializes the variables.
	 * @param x X position.
	 * @param y Y position.
	 * @param size Size of the labyrinth.
	 * @param num_walls Num of the walls that this cell can have if its closed.
	 */
	public Cell(int x, int y, int[] size, int num_walls)
	{
		super(x,y);
		
		visited = false;
		
		walls = new Boolean[num_walls];	//position legend --> 0: right, 1: down, 2: left
		
		for(int i = 0 ; i < walls.length ; i++)
		{
			walls[i] = true;
		}
		
		setWalls(x, y, size);
	}
	
	/**
	 * Method that calculate the active walls of the cell.
	* @param x: X position.
	 * @param y: Y position.
	 * @param size: Size of the labyrinth.
	 */
	protected abstract void setWalls(int x, int y, int[] size);
	
	/**
	 * Method that overthrow the wall one specific wall of the cell
	 * @param wall: The wall you want to overthrow.
	 */
	protected abstract void overthrowWall(String wall);
	
	/**
	 * Method that allows you know if a specific wall is active.
	 * @param wall: The wall about you want to know if is active.
	 * @return It's active or overthrown.
	 */
	protected abstract boolean infoWall(String wall);
	
	/**
	 * Method to know if the cell has been visited.
	 * @return It's been visited or not.
	 */
	public boolean getVisited(){return visited;}
	
	/**
	 * Method to set a specific cell visited.
	 */
	public void setVisited(){visited = true;}
}
