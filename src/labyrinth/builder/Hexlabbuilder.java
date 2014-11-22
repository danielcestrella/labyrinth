package labyrinth.builder;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Class that inherits from Labbuilder and creates the labyrinth with hexagonal cells.
 */
public final class Hexlabbuilder extends Labbuilder<Hexcell>
{
	public Hexlabbuilder(int[]size, int[]start)
	{	
		this.size = size;

		mylab = new Hexcell[size[0]][size[1]];
	
		for(int i = 0 ; i < size[0] ; i++) //X
		{	
			for(int j = 0 ; j < size[1] ; j++) //Y
			{
				mylab[i][j] = new Hexcell(i,j,size,3);
			}
		}
		
		dfs(mylab[start[0]][start[1]]);
	}

	protected void dfs(Hexcell myhexcell)	//cannot be private because inherits from an abstract method
	{
		ArrayList<Hexcell> neighbours = new ArrayList<Hexcell>();
		myhexcell.setVisited();

		//look if it has neighbors and put them in an arraylist
		//up
		if(myhexcell.getY() > 0)
		{
			neighbours.add(mylab[myhexcell.getX()][myhexcell.getY()-1]);
		}
		
		//right
		if(myhexcell.getX() < size[0] - 1)
		{
			neighbours.add(mylab[myhexcell.getX()+1][myhexcell.getY()]);
		}
		
		//down
		if(myhexcell.getY() < size[1] - 1)
		{
			neighbours.add(mylab[myhexcell.getX()][myhexcell.getY()+1]);
		}
		
		//left
		if(myhexcell.getX() > 0)
		{
			neighbours.add(mylab[myhexcell.getX()-1][myhexcell.getY()]);
		}
		
		//shuffle the order of the neighbors
		Collections.shuffle(neighbours);
		
		for(int i = 0 ; i < neighbours.size() ; i++)
		{
			//if it hasn't been visited yet, look at the position of the neighbor
			if(!neighbours.get(i).getVisited())
			{
				//above
				if(neighbours.get(i).getY() < myhexcell.getY())
				{
					//overthrow the down wall of the neighbor cell
					neighbours.get(i).overthrowWall("down");
				}
				
				//right and I'm in pair position
				else if(neighbours.get(i).getX() > myhexcell.getX() && myhexcell.getX() % 2 == 0)
				{
					//overthrow the right wall of the current cell
					myhexcell.overthrowWall("right");
				}
				
				//right and I'm in odd position
				else if(neighbours.get(i).getX() > myhexcell.getX() && myhexcell.getX() % 2 != 0)
				{
					///overthrow the left wall of the neighbor cell
					neighbours.get(i).overthrowWall("left");
				}
				
				//below
				else if(neighbours.get(i).getY() > myhexcell.getY())
				{
					//overthrow the down wall of the current cell
					myhexcell.overthrowWall("down");
				}
				
				//left and I'm in pair position
				else if(neighbours.get(i).getX() < myhexcell.getX() && myhexcell.getX() % 2 == 0)
				{
					//overthrow the left wall of the current cell
					myhexcell.overthrowWall("left");
				}
				
				//left and I'm in odd position
				else if(neighbours.get(i).getX() < myhexcell.getX() && myhexcell.getX() % 2 != 0)
				{
					//overthrow the right wall of the neighbor cell
					neighbours.get(i).overthrowWall("right");
				}
				
				dfs(neighbours.get(i));
			}
		}
			
	}
	
	public Hexcell getCell(int x, int y){return mylab[x][y];}
	
	/*
	public String toString() {
		String a = "\nmylab= ";

		for(int i = 0 ; i < size[0] ; i++)
		{
			for(int j = 0 ; j < size[1] ; j++)
			{
				a += "\nwalls x: " + i + "    y: "+ j + "      right: " + mylab[i][j].infoWall("right") + " down: " + mylab[i][j].infoWall("down") + " left: " + mylab[i][j].infoWall("left");
			}
		}
		return a;
	}
	*/
}
