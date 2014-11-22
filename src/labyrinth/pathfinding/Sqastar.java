package labyrinth.pathfinding;

import labyrinth.builder.Sqcell;
import labyrinth.builder.Sqlabbuilder;

/*
 * Class that find the path between two cells of a square cell based labyrinth.
 */
public final class Sqastar extends Astar<Sqcell, Sqlabbuilder>
{
	public Sqastar(Sqlabbuilder mylab, int[] a_start, int[] a_end)
	{
		super(mylab, a_start, a_end);
	}

	protected void setArrayLenghts(Sqlabbuilder mylab)
	{
		this.path = new boolean[mylab.getX()][mylab.getY()];
		this.open_array = new Node[mylab.getX()][mylab.getY()];
		closed_array = new boolean[mylab.getX()][mylab.getY()];
	}
	
	protected void checkNeighbours(Node actual, Sqlabbuilder mylab)
	{
		//look for reachable neighbors
		//up
		if(actual.getY() > 0)
		{
			Sqcell newcell = mylab.getCell(actual.getX(),actual.getY()-1);
			
			//if its reachable and isn't in the closed array
			if(!newcell.infoWall("down") && !closed_array[newcell.getX()][newcell.getY()])
				openListCheck(actual, newcell);
		}
		
		//right
		if(actual.getX() < mylab.getX() - 1)
		{
			Sqcell mycell = mylab.getCell(actual.getX(),actual.getY());
			Sqcell newcell = mylab.getCell(actual.getX()+1,actual.getY());
			
			//if its reachable and isn't in the closed array
			if(!mycell.infoWall("right") && !closed_array[newcell.getX()][newcell.getY()])
				openListCheck(actual, newcell);
		}
		
		//down
		if(actual.getY() < mylab.getY() - 1)
		{
			Sqcell mycell = mylab.getCell(actual.getX(),actual.getY());
			Sqcell newcell = mylab.getCell(actual.getX(),actual.getY()+1);
			
			//if its reachable and isn't in the closed array
			if(!mycell.infoWall("down") && !closed_array[newcell.getX()][newcell.getY()])
				openListCheck(actual, newcell);
		}
		
		//left
		if(actual.getX() > 0)
		{
			Sqcell newcell = mylab.getCell(actual.getX()-1,actual.getY());
			
			//if its reachable and isn't in the closed array
			if(!newcell.infoWall("right") && !closed_array[newcell.getX()][newcell.getY()])
				openListCheck(actual, newcell);
		}
	}
	
	protected void openListCheck(Node actual, Sqcell newcell)
	{
		if(open_array[newcell.getX()][newcell.getY()] == null || open_array[newcell.getX()][newcell.getY()].getG() > actual.getG() + 1)
		{
			Node mypathcell = new Node(newcell.getPosition(), actual);
			if(mypathcell.getH() == 0)
			{
				closed_list.add(mypathcell);
				astarloop = false;
			}
			else
			{
				open_array[newcell.getX()][newcell.getY()] = mypathcell;
				open_list.add(open_array[newcell.getX()][newcell.getY()]);
			}
		}
	}
}
