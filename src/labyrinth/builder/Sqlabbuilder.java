package labyrinth.builder;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Class that inherits from Labbuilder and creates the labyrinth with square cells.
 */
public class Sqlabbuilder extends Labbuilder<Sqcell>
{
	public Sqlabbuilder(int[]size, int[]start)
	{	
		this.size = size;

		mylab = new Sqcell[size[0]][size[1]];
	
		for(int i = 0 ; i < size[0] ; i++) //X
		{	
			for(int j = 0 ; j < size[1] ; j++) //Y
			{
				mylab[i][j] = new Sqcell(i,j,size,2);
			}
		}
		
		dfs(mylab[start[0]][start[1]]);
	}

	protected void dfs(Sqcell mycell)
	{
		ArrayList<Sqcell> neighbours = new ArrayList<Sqcell>();
		mycell.setVisited();

		//look if it has neighbors and put them in an arraylist
		//up
		if(mycell.getY() > 0)
		{
			neighbours.add(mylab[mycell.getX()][mycell.getY()-1]);
		}
		
		//right
		if(mycell.getX() < size[0] - 1)
		{
			neighbours.add(mylab[mycell.getX()+1][mycell.getY()]);
		}
		
		//down
		if(mycell.getY() < size[1] - 1)
		{
			neighbours.add(mylab[mycell.getX()][mycell.getY()+1]);
		}
		
		//left
		if(mycell.getX() > 0)
		{
			neighbours.add(mylab[mycell.getX()-1][mycell.getY()]);
		}
		
		//shuffle the order of the neighbors
		Collections.shuffle(neighbours);
		
		for(int i = 0 ; i < neighbours.size() ; i++)
		{
			//if it hasn't been visited yet, look at the position of the neighbor
			if(!neighbours.get(i).getVisited())
			{
				//above
				if(neighbours.get(i).getY() < mycell.getY())
				{
					//overthrow the down wall of the neighbor cell
					neighbours.get(i).overthrowWall("down");
				}
				
				//below
				else if(neighbours.get(i).getY() > mycell.getY())
				{
					//overthrow the down wall of the current cell
					mycell.overthrowWall("down");
				}
				
				//on the right
				if(neighbours.get(i).getX() > mycell.getX())
				{
					//overthrow the right wall of the current cell
					mycell.overthrowWall("right");
				}

				//on the left
				else if(neighbours.get(i).getX() < mycell.getX())
				{
					//overthrow the right wall of the neighbor cell
					neighbours.get(i).overthrowWall("right");
				}			
				
				dfs(neighbours.get(i));
			}
		}
			
	}
	
	public Sqcell getCell(int x, int y){return mylab[x][y];}
	
	/*
	public String toString() {
		String a = "\nmylab= ";

		for(int i = 0 ; i < size[0] ; i++)
		{
			for(int j = 0 ; j < size[1] ; j++)
			{
				a += "\nwalls x: " + i + "    y: "+ j + "      right: " + mylab[i][j].infoWall("right") + " down: " + mylab[i][j].infoWall("down");
			}
		}
		return a;
	}
	*/
}
