package labyrinth.builder;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Class that inherits from Labbuilder and creates the labyrinth with trianglular cells.
 */
public final class Trilabbuilder extends Labbuilder<Tricell>
{
	public Trilabbuilder(int[]size, int[]start)
	{	
		this.size = size;

		mylab = new Tricell[size[0]][size[1]];
	
		for(int i = 0 ; i < size[0] ; i++) //X
		{	
			for(int j = 0 ; j < size[1] ; j++) //Y
			{
				mylab[i][j] = new Tricell(i,j,size,2);
			}
		}
		
		dfs(mylab[start[0]][start[1]]);
	}

	protected void dfs(Tricell mytricell)	//cannot be private because inherits from an abstract method
	{
		ArrayList<Tricell> neighbours = new ArrayList<Tricell>();
		mytricell.setVisited();

		//look if it has neighbors and put them in an arraylist
		//up
		if(mytricell.getY() > 0 && (mytricell.getX() + mytricell.getY())% 2 != 0)
		{
			neighbours.add(mylab[mytricell.getX()][mytricell.getY()-1]);
		}
		
		//right
		if(mytricell.getX() < size[0] - 1)
		{
			neighbours.add(mylab[mytricell.getX()+1][mytricell.getY()]);
		}
		
		//down
		if(mytricell.getY() < size[1] - 1 && (mytricell.getX() + mytricell.getY())% 2 == 0)
		{
			neighbours.add(mylab[mytricell.getX()][mytricell.getY()+1]);
		}
		
		//left
		if(mytricell.getX() > 0)
		{
			neighbours.add(mylab[mytricell.getX()-1][mytricell.getY()]);
		}
		
		//shuffle the order of the neighbors
		Collections.shuffle(neighbours);
		
		for(int i = 0 ; i < neighbours.size() ; i++)
		{
			//if it hasn't been visited yet, look at the position of the neighbor
			if(!neighbours.get(i).getVisited())
			{
				//above
				if(neighbours.get(i).getY() < mytricell.getY())
				{
					//overthrow the down wall of the neighbor cell
					neighbours.get(i).overthrowWall("down");
				}
				
				//below
				else if(neighbours.get(i).getY() > mytricell.getY())
				{
					//overthrow the down wall of the current cell
					mytricell.overthrowWall("down");
				}
				
				//right
				if(neighbours.get(i).getX() > mytricell.getX())
				{
					//overthrow the right wall of the current cell
					mytricell.overthrowWall("right");
				}

				//left
				else if(neighbours.get(i).getX() < mytricell.getX())
				{
					//overthrow the right wall of the neighbor cell
					neighbours.get(i).overthrowWall("right");
				}			
				
				dfs(neighbours.get(i));
			}
		}
			
	}
	
	public Tricell getCell(int x, int y){return mylab[x][y];}
	
	/*
	public String toString() {
		String a = "\nmytrilab= ";

		for(int i = 0 ; i < size[0] ; i++)
		{
			for(int j = 0 ; j < size[1] ; j++)
			{
				a += "\nwalls x: " + i + "    y: "+ j + "      right: " + mytrilab[i][j].infoWall("right") + " down: " + mytrilab[i][j].infoWall("down");
			}
		}
		return a;
	}
	*/
}
