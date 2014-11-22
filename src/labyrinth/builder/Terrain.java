package labyrinth.builder;

/**
 * Class that contains the information of the terrain for the maps.
 * @author Carlos Bailón Pérez
 *
 */
public class Terrain
{
	private int type;	//1: water	2: plain	3: mountain

	/**
	 * Constructor method of the class.
	 * Set the terrain type.
	 */
	public Terrain ()
	{	
		setType(1);
	}
	
	/**
	 * Alternative constructor that sets the terrain of the same type as the incoming terrain.
	 * @param myTerrain Incoming variable.
	 */
	public Terrain (Terrain myTerrain)
	{
		setType(myTerrain.getType());
	}

	/**
	 * Getter of the terrain type.
	 * @return the terrain type.
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * Setter of the terrain type.
	 * @param type to set.
	 */
	public void setType(int type)
	{
		this.type = type;
	}
}
