package labyrinth.builder;

/*
 * Class with walls and visited boolean for triangular cells.
 * It also inherits position variables and methods from place class.
 */
public final class Tricell extends Cell
{   	
	public Tricell(int x, int y, int[] size, int num_walls)
	{
		super(x, y, size, num_walls);
	}

	protected void setWalls(int x, int y, int[] size)
	{
		if(x == size[0] -1) 	//if you are in the final column
			walls[0] = false;	//right
		
		if(y == size[1] -1) 	//if you are in the final row
			walls[1] = false;	//down
		
		if((x + y)% 2 != 0)		//if you are a reverse triangle
			walls[1] = false;	//down
	}
	
	public void overthrowWall(String wall)
	{
		if(wall == "right")
			walls[0] = false;
		else
			walls[1] = false;
	}
	
	public boolean infoWall(String wall)
	{
		if(wall == "right")
			return walls[0];
		else
			return walls[1];
	}
}
