package labyrinth.pathfinding;

import labyrinth.builder.Tricell;
import labyrinth.builder.Trilabbuilder;

/*
 * Class that find the path between two cells of a triangle cell based labyrinth.
 */
public final class Triastar extends Astar<Tricell, Trilabbuilder>
{
	public Triastar(Trilabbuilder mylab, int[] a_start, int[] a_end)
	{
		super(mylab, a_start, a_end);
	}

	protected void setArrayLenghts(Trilabbuilder mylab)
	{
		this.path = new boolean[mylab.getX()][mylab.getY()];
		this.open_array = new Node[mylab.getX()][mylab.getY()];
		closed_array = new boolean[mylab.getX()][mylab.getY()];
	}
	
	protected void checkNeighbours(Node actual, Trilabbuilder mylab)
	{
		//look for reachable neighbors
		//up
		if(actual.getY() > 0 && (actual.getX() + actual.getY())% 2 != 0)
		{
			Tricell newtricell = mylab.getCell(actual.getX(),actual.getY()-1);
			
			//if its reachable and isn't in the closed array
			if(!newtricell.infoWall("down") && !closed_array[newtricell.getX()][newtricell.getY()])
				openListCheck(actual, newtricell);
		}
		
		//right
		if(actual.getX() < mylab.getX() - 1)
		{
			Tricell mytricell = mylab.getCell(actual.getX(),actual.getY());
			Tricell newtricell = mylab.getCell(actual.getX()+1,actual.getY());
			
			//if its reachable and isn't in the closed array
			if(!mytricell.infoWall("right") && !closed_array[newtricell.getX()][newtricell.getY()])
				openListCheck(actual, newtricell);
		}
		
		//down
		if(actual.getY() < mylab.getY() - 1 && (actual.getX() + actual.getY())% 2 == 0)
		{
			Tricell mytricell = mylab.getCell(actual.getX(),actual.getY());
			Tricell newtricell = mylab.getCell(actual.getX(),actual.getY()+1);
			
			//if its reachable and isn't in the closed array
			if(!mytricell.infoWall("down") && !closed_array[newtricell.getX()][newtricell.getY()])
				openListCheck(actual, newtricell);
		}
		
		//left
		if(actual.getX() > 0)
		{
			Tricell newtricell = mylab.getCell(actual.getX()-1,actual.getY());
			
			//if its reachable and isn't in the closed array
			if(!newtricell.infoWall("right") && !closed_array[newtricell.getX()][newtricell.getY()])
				openListCheck(actual, newtricell);
		}	
	}
	
	protected void openListCheck(Node actual, Tricell newtricell)
	{
		if(open_array[newtricell.getX()][newtricell.getY()] == null || open_array[newtricell.getX()][newtricell.getY()].getG() > actual.getG() + 1)
		{
			Node mypathcell = new Node(newtricell.getPosition(), actual);
			if(mypathcell.getH() == 0)
			{
				closed_list.add(mypathcell);
				astarloop = false;
			}
			else
			{
				open_array[newtricell.getX()][newtricell.getY()] = mypathcell;
				open_list.add(open_array[newtricell.getX()][newtricell.getY()]);
			}
		}
	}
}
