package labyrinth.builder;

/**
 * This class builds natural maps.
 * @author Carlos Bailón Pérez
 *
 */
public class Mapbuilder
{
	private int[] size = new int[2];
	
	int posY, posX, contwater = 0;
	float posib, posibM, posibW, iter, total;
	
	Terrain [][] map;
	Terrain [][] nextmap;
	
	/**
	 * Constructor method of the class. Initializes the variables of the class.
	 * @param map_type Type of map -> pangea, islands, continent.
	 * @param size size of the map.
	 */
	public Mapbuilder (int map_type, int[] size)
	{
		this.size = size;
		
		//Create the maps
		map = new Terrain [size[1]][size[0]];
		nextmap = new Terrain [size[1]][size[0]];
		
		// Initialize the map with all sections as water
		for (int j = 0 ; j < size[1] ; j++ )
		{
			for (int i = 0; i < size[0]; i++ )
			{
				map [j] [i] = new Terrain ();
				nextmap [j] [i] = new Terrain ();
			}
		}
		
		if(map_type == 1)
			pangea();
		else if(map_type == 2)
			islands();
		else
			continents();
	}
	
	/**
	 * Method that creates a pangea map.
	 */
	private void pangea()
	{
		System.out.println("PANGEA");

		// Random place of the map
		posY = size[0]/3 + (int)(Math.random() * (((size[0] - size[0]/3) - size[0]/3)  +1));
		posX = size[1]/3 + (int)(Math.random() * (((size[1] - size[1]/3) - size[1]/3) + 1));
		
		//Conversion probability...
		posib = 0.25f; // ...to land
		posibM = 0.08f; // ...to mountain
		posibW = 0.08f;
		contwater = 0;
		
		//Assign the land to that random place
		nextmap [posX][posY].setType(2);
		map [posX][posY].setType(2);
		
		//Generation loop
		iter = (size[0] * size[1])/(size[1]/4 + size[1]); // rule of the number of generations
		for (int k = 0 ; k < iter ; k++)
		{
			
			//Count water sections
			for (int j = 0 ; j < size[1]  ; j++ )
			{
				for (int i = 0; i < size[0] ; i++ )
				{
					if (map [j][i].getType() ==1)
					{
						contwater++;
					}
				}
			}
			
			// Let only one amount of allowed water
			total = size[1] * size[0];
			total = total / 1.8f;
			
			
			if (contwater > total)
			{		
				//if there is a land around a water section, the water section becomes a land
				for (int j = 0 ; j < size[1]  ; j++ )
				{
					for (int i = 0; i < size[0] ; i++ )
					{
						
						
						if (j == 0 && i == 0)
						{
							if (map [j + 1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
									nextmap [j][i].setType(2);
							}
						}
						
						if (j == 0 && i != 0 && i != size[0] - 1)
						{
							if ( map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (i == 0 && j != 0 && j != size[1] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j + 1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j == 0 && i == size[0] - 1)
						{
							if (map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j == size[1] - 1  && i == 0)
						{
							if (map [j-1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (i == size[0] - 1 && j != 0 && j != size[1] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						
						if (j == size[1] - 1 && i != 0 && i != size[0] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j][i - 1].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						
						if (j == size[1] - 1 && i == size[0] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j != 0 && i != 0 && j != size[1] - 1 && i != size[0] - 1 )
						{
							if (map [j-1][i].getType() == 2 || map [j+1][i].getType() == 2 || map [j][i - 1].getType() == 2 ||  map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
					}
				}
			}
			
			//land/plain to mountain
			if (k == iter - 1)
			{
				for (int j = 0 ; j < size[1]  ; j++ )
				{
					for (int i = 0; i < size[0] ; i++ )
					{
						if (map [j][i].getType() ==2)
						{
							float one = 0 + (float) (Math.random() *(0-1)+1);
							if (one < posibM)
							{
								nextmap [j][i].setType(3);
							}
						}
					}
				}
			}
			
			if (k == iter - 1)
			{
				for (int j = 0 ; j < size[1]  ; j++ )
				{
					for (int i = 0; i < size[0] ; i++ )
					{
						if (map [j][i].getType() ==2)
						{
							float one = 0 + (float) (Math.random() *(0-1)+1);
							if (one < posibW)
							{
								nextmap [j][i].setType(1);
							}
						}
					}
				}
			}
			
			//equal both arrays
			for ( int  ch = 0 ; ch < size[1]; ch++ )
			{
				for ( int cw = 0; cw < size[0]; cw++ )
				{
					map [ch] [cw] = new Terrain (nextmap [ch][cw]);
				}
			}
		}
	}
	
	/**
	 * Method that creates an islands map
	 */
	private void islands()
	{
		System.out.println("Islas");
		contwater= 0;
		
		//4 random points
		for (int f = 0; f < 140 ; f++)
		{
		posY = 0 + (int)(Math.random() * (((size[0] - 1) - 0)  +1));
		posX = 0 + (int)(Math.random() * (((size[1] - 1) - 0) + 1));
		
		//plain to random points
		nextmap [posX][posY].setType(2);
		map [posX][posY].setType(2);
		}
		
		//Conversion probability
		posib = 0.25f;
		posibM = 0.08f;
		contwater = 0;
		
		//Generation loop
		iter = (size[0] * size[1])/((size[0] * size[1])/2); //Generation rule
		
		for (int k = 0 ; k < iter ; k++)
		{
			
			//Couunt water sections
			for (int j = 0 ; j < size[1]  ; j++ )
			{
				for (int i = 0; i < size[0] ; i++ )
				{
					if (map [j][i].getType() ==1)
					{
						contwater++;
					}
				}
			}
			
			//Only 1.5 water sections allowed
			total = size[1] * size[0];
			total =  total / 1.3f;
			
			if (contwater > total)
			{		
				for (int j = 0 ; j < size[1]  ; j++ )
				{
					for (int i = 0; i < size[0] ; i++ )
					{
						
						
						if (j == 0 && i == 0)
						{
							if (map [j + 1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
									nextmap [j][i].setType(2);
							}
						}
						
						if (j == 0 && i != 0 && i != size[0] - 1)
						{
							if ( map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (i == 0 && j != 0 && j != size[1] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j + 1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j == 0 && i == size[0] - 1)
						{
							if (map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j == size[1] - 1  && i == 0)
						{
							if (map [j-1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (i == size[0] - 1 && j != 0 && j != size[1] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						
						if (j == size[1] - 1 && i != 0 && i != size[0] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j][i - 1].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						
						if (j == size[1] - 1 && i == size[0] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j != 0 && i != 0 && j != size[1] - 1 && i != size[0] - 1 )
						{
							if (map [j-1][i].getType() == 2 || map [j+1][i].getType() == 2 || map [j][i - 1].getType() == 2 ||  map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
					}
				}
			}
			
			//Mountains
			if (k == iter - 1)
			{
				for (int j = 0 ; j < size[1]  ; j++ )
				{
					for (int i = 0; i < size[0] ; i++ )
					{
						if (map [j][i].getType() ==2)
						{
							float one = 0 + (float) (Math.random() *(0-1)+1);
							if (one < posibM)
							{
								nextmap [j][i].setType(3);
							}
						}
					}
				}
			}
			
			for ( int  ch = 0 ; ch < size[1]; ch++ )
			{
				for ( int cw = 0; cw < size[0]; cw++ )
				{
					map [ch] [cw] = new Terrain (nextmap [ch][cw]);
				}
			}
		}
	}
	
	/**
	 * Method that creates a continents map
	 */
	private void continents()
	{
		System.out.println("CONTINENTES");
		contwater= 0;
		
		// 2 random points
		for (int f = 0; f < 2 ; f++)
		{
			
			//one above one below
			if (f == 0)
			{
				posX = 0 + (int)(Math.random() * (((size[1]/2 - size[1]/4) - 0) + 1));
				posY = 0;
			}
			else
			{
				posX = (size[1]/2 + size[1]/4)  + (int)(Math.random() * (((size[1] - 1) - (size[1]/2 + size[1]/4)) + 1));
				posY = size[0] - 1;
			}
				
			//assign plain to random sections
			nextmap [posX][posY].setType(2);
			map [posX][posY].setType(2);
		}
		
		//Conversion probability
		posib = 0.25f;
		posibM = 0.08f;
		posibW = 0.08f;
		contwater = 0;
		
		//Generation loop
		iter = (size[0] * size[1])/(size[1] - size[1]/4); //Generation rule
		
		for (int k = 0 ; k < iter ; k++)
		{
			
			//Count water sections
			for (int j = 0 ; j < size[1]  ; j++ )
			{
				for (int i = 0; i < size[0] ; i++ )
				{
					if (map [j][i].getType() ==1)
					{
						contwater++;
					}
				}
			}
			
			//Only 1.5 water sections allowed
			total = size[1] * size[0];
			total =  total / 2f;
			
			if (contwater > total)
			{		
				for (int j = 0 ; j < size[1]  ; j++ )
				{
					for (int i = 0; i < size[0] ; i++ )
					{
						
						
						if (j == 0 && i == 0)
						{
							if (map [j + 1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
									nextmap [j][i].setType(2);
							}
						}
						
						if (j == 0 && i != 0 && i != size[0] - 1)
						{
							if ( map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (i == 0 && j != 0 && j != size[1] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j + 1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j == 0 && i == size[0] - 1)
						{
							if (map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j == size[1] - 1  && i == 0)
						{
							if (map [j-1][i].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (i == size[0] - 1 && j != 0 && j != size[1] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j + 1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						
						if (j == size[1] - 1 && i != 0 && i != size[0] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j][i - 1].getType() == 2 || map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						
						if (j == size[1] - 1 && i == size[0] - 1)
						{
							if (map [j-1][i].getType() == 2 || map [j][i - 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
						
						if (j != 0 && i != 0 && j != size[1] - 1 && i != size[0] - 1 )
						{
							if (map [j-1][i].getType() == 2 || map [j+1][i].getType() == 2 || map [j][i - 1].getType() == 2 ||  map [j][i + 1].getType() == 2)
							{
								float one = 0 + (float) (Math.random() *(0-1)+1);
								if (one < posib)
								nextmap [j][i].setType(2);
							}
						}
					}
				}
			}
			
			//Mountain
			if (k == iter - 1)
			{
				for (int j = 0 ; j < size[1]  ; j++ )
				{
					for (int i = 0; i < size[0] ; i++ )
					{
						if (map [j][i].getType() ==2)
						{
							float one = 0 + (float) (Math.random() *(0-1)+1);
							if (one < posibM)
							{
								nextmap [j][i].setType(3);
							}
						}
					}
				}
			}
			
			if (k == iter - 1)
			{
				for (int j = 0 ; j < size[1]  ; j++ )
				{
					for (int i = 0; i < size[0] ; i++ )
					{
						if (map [j][i].getType() ==2)
						{
							float one = 0 + (float) (Math.random() *(0-1)+1);
							if (one < posibW)
							{
								nextmap [j][i].setType(1);
							}
						}
					}
				}
			}
			
			for ( int  ch = 0 ; ch < size[1]; ch++ )
			{
				for ( int cw = 0; cw < size[0]; cw++ )
				{
					map [ch] [cw] = new Terrain (nextmap [ch][cw]);
				}
			}

		}		
	}
	
	/**
	 * Getter of the map.
	 * @return the map.
	 */
	public Terrain[][] getMap()
	{
		return map;
	}
}
