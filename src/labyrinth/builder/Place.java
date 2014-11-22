package labyrinth.builder;

/**
 * Class with the basic information of a place (position).
 * @author Daniel Castaño Estrella
 *
 */
public class Place
{
	protected int[] position = new int[2];
	
	/**
	 * Constructor of the class. Initializes the variables.
	 * @param x X coordinate.
	 * @param y Y ordinate.
	 */
	public Place(int x, int y)
	{
		position[0] = x;	//columna X
		position[1] = y;	//fila Y
	}
	
	/**
	 * Getter of the X position.
	 * @return the x position.
	 */
	public int getX(){return position[0];}
	
	/**
	 * Getter of the Y position.
	 * @return the y position.
	 */
	public int getY(){return position[1];}
	
	/**
	 * Getter of the whole position.
	 * @return The array of the position.
	 */
	public int[] getPosition(){return position;}
}
