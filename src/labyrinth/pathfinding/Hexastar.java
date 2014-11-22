package labyrinth.pathfinding;

import labyrinth.builder.Hexcell;
import labyrinth.builder.Hexlabbuilder;

/*
 * Class that find the path between two cells of a hexagonal cell based labyrinth.
 */
public final class Hexastar extends Astar<Hexcell, Hexlabbuilder>
{
	public Hexastar(Hexlabbuilder mylab, int[] a_start, int[] a_end)
	{
		super(mylab, a_start, a_end);
	}

	protected void setArrayLenghts(Hexlabbuilder mylab)
	{
		this.path = new boolean[mylab.getX()][mylab.getY()];
		this.open_array = new Node[mylab.getX()][mylab.getY()];
		closed_array = new boolean[mylab.getX()][mylab.getY()];
	}
	
	protected void checkNeighbours(Node actual, Hexlabbuilder mylab)
	{
		//look for reachable neighbor
		//up
		if(actual.getY() > 0)
		{
			Hexcell newhexcell = mylab.getCell(actual.getX(),actual.getY()-1);
			
			//if its reachable and isn't in the closed array
			if(!newhexcell.infoWall("down") && !closed_array[newhexcell.getX()][newhexcell.getY()])
				openListCheck(actual, newhexcell);
		}
		
		//down
		if(actual.getY() < mylab.getY() - 1)
		{
			Hexcell myhexcell = mylab.getCell(actual.getX(),actual.getY());
			Hexcell newhexcell = mylab.getCell(actual.getX(),actual.getY()+1);
			
			//if its reachable and isn't in the closed array
			if(!myhexcell.infoWall("down") && !closed_array[newhexcell.getX()][newhexcell.getY()])
				openListCheck(actual, newhexcell);
		}
		
		if(actual.getX() % 2 == 0)
		{
			//right
			if(actual.getX() < mylab.getX() - 1)
			{
				Hexcell myhexcell = mylab.getCell(actual.getX(),actual.getY());
				Hexcell newhexcell = mylab.getCell(actual.getX()+1,actual.getY());
				
				//if its reachable and isn't in the closed array
				if(!myhexcell.infoWall("right") && !closed_array[newhexcell.getX()][newhexcell.getY()])
					openListCheck(actual, newhexcell);
			}
			
			//left
			if(actual.getX() > 0)
			{
				Hexcell myhexcell = mylab.getCell(actual.getX(),actual.getY());
				Hexcell newhexcell = mylab.getCell(actual.getX()-1,actual.getY());
				
				//if its reachable and isn't in the closed array
				if(!myhexcell.infoWall("left") && !closed_array[newhexcell.getX()][newhexcell.getY()])
					openListCheck(actual, newhexcell);
			}
			
			//up right
			if(actual.getX() < mylab.getX() - 1 && actual.getY() > 0)
			{
				Hexcell newhexcell = mylab.getCell(actual.getX()+1,actual.getY()-1);
				
				//if its reachable and isn't in the closed array
				if(!newhexcell.infoWall("left") && !closed_array[newhexcell.getX()][newhexcell.getY()])
					openListCheck(actual, newhexcell);
			}
			
			//up left
			if(actual.getX() > 0 && actual.getY() > 0)
			{
				Hexcell newhexcell = mylab.getCell(actual.getX()-1,actual.getY()-1);
				
				//if its reachable and isn't in the closed array
				if(!newhexcell.infoWall("right") && !closed_array[newhexcell.getX()][newhexcell.getY()])
					openListCheck(actual, newhexcell);
			}
		}
		else
		{
			//right
			if(actual.getX() < mylab.getX() - 1)
			{
				Hexcell newhexcell = mylab.getCell(actual.getX()+1,actual.getY());
				
				//if its reachable and isn't in the closed array
				if(!newhexcell.infoWall("left") && !closed_array[newhexcell.getX()][newhexcell.getY()])
					openListCheck(actual, newhexcell);
			}
			
			//left
			if(actual.getX() > 0)
			{
				Hexcell newhexcell = mylab.getCell(actual.getX()-1,actual.getY());
				
				//if its reachable and isn't in the closed array
				if(!newhexcell.infoWall("right") && !closed_array[newhexcell.getX()][newhexcell.getY()])
					openListCheck(actual, newhexcell);
			}
			
			//down right
			if(actual.getX() < mylab.getX() - 1 && actual.getY() < mylab.getY() - 1)
			{
				Hexcell myhexcell = mylab.getCell(actual.getX(),actual.getY());
				Hexcell newhexcell = mylab.getCell(actual.getX()+1,actual.getY()+1);
				
				//if its reachable and isn't in the closed array
				if(!myhexcell.infoWall("right") && !closed_array[newhexcell.getX()][newhexcell.getY()])
					openListCheck(actual, newhexcell);
			}
			
			//down left
			if(actual.getX() > 0 && actual.getY() < mylab.getY() - 1)
			{
				Hexcell myhexcell = mylab.getCell(actual.getX(),actual.getY());
				Hexcell newhexcell = mylab.getCell(actual.getX()-1,actual.getY()+1);
				
				//if its reachable and isn't in the closed array
				if(!myhexcell.infoWall("left") && !closed_array[newhexcell.getX()][newhexcell.getY()])
					openListCheck(actual, newhexcell);
			}
		}
	}
	
	protected void openListCheck(Node actual, Hexcell newhexcell)
	{
		if(open_array[newhexcell.getX()][newhexcell.getY()] == null || open_array[newhexcell.getX()][newhexcell.getY()].getG() > actual.getG() + 1)
		{
			Node mypathcell = new Node(newhexcell.getPosition(), actual);
			if(mypathcell.getH() == 0)
			{
				closed_list.add(mypathcell);
				astarloop = false;
			}
			else
			{
				open_array[newhexcell.getX()][newhexcell.getY()] = mypathcell;
				open_list.add(open_array[newhexcell.getX()][newhexcell.getY()]);
			}
		}
	}
}
