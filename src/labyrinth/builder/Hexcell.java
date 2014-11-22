package labyrinth.builder;

/*
 * Class with walls and visited boolean for hexagonal cells.
 * It also inherits position variables and methods from place class.
 */
public final class Hexcell extends Cell
{
	public Hexcell(int x, int y, int[] size, int num_walls)
	{
		super(x, y, size, num_walls);
	}

	protected void setWalls(int x, int y, int[] size)
	{
		if(x == size[0] -1) 		//if you are in the final column
			walls[0] = false;		//right
		
		if(y == size[1] -1) 		//if you are in the final row
		{
			walls[1] = false;		//down
			
			if(x % 2 != 0)			//if you are in a pair column
			{
				walls[0] = false;	//right
				walls[2] = false;	//left
			}
		}
		
		if(x == 0) 					//if you are in the first column
		walls[2] = false;			//left
	}
	
	public void overthrowWall(String wall)
	{
		if(wall == "right")
			walls[0] = false;
		else if(wall == "down")
			walls[1] = false;
		else
			walls[2] = false;
	}	
	
	public boolean infoWall(String wall)
	{
		if(wall == "right")
			return walls[0];
		else if(wall == "down")
			return walls[1];
		else
			return walls[2];
	}
}
