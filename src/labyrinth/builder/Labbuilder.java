package labyrinth.builder;

/**
 * This class is the abstract class of the DFS labyrinth builder algorithm.
 * @author Daniel Castaño Estrella
 *
 *@param <T> Type of cell: square, hexagonal, triangular.
 */
public abstract class Labbuilder<T>
{
	protected T[][] mylab;
	protected int[] size;
	
	/**
	 * Recursive method that creates the labyrinth.
	 * @param mycell The cell of the labyrinth.
	 */
	protected abstract void dfs(T mycell);
	
	/**
	 * Getter of a specific cell of the labyrinth.
	 * @param x: X position.
	 * @param y: Y position.
	 * @return the cell that we are finding.
	 */
	protected abstract T getCell(int x, int y);
	
	/**
	 * Get the size of the labyrinth.
	 * @return the size.
	 */
	public int[] getSize(){return size;}
	
	/**
	 * Get the X size of the labyrinth.
	 * @return the x size.
	 */
	public int getX(){return size[0];}
	
	/**
	 * Get the Y size of the labyrinth.
	 * @return the y size.
	 */
	public int getY(){return size[1];}

}