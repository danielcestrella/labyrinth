package labyrinth;

import java.util.Scanner;

import labyrinth.builder.Hexlabbuilder;
import labyrinth.builder.Mapbuilder;
import labyrinth.builder.Sqlabbuilder;
import labyrinth.builder.Trilabbuilder;
import labyrinth.pathfinding.Hexastar;
import labyrinth.pathfinding.Sqastar;
import labyrinth.pathfinding.Triastar;

/**
 * Class to execute the app.
 * It contains the menus, variables of the different maps and print methods.
 * @author  Carlos Bailón Pérez y Daniel Castaño Estrella
 *
 */
public class Executor
{
	private Scanner keyboard;
	private int[] start = new int[2];
	
	private int[] size = new int[2];
	
	private Mapbuilder mymap;
	private Sqlabbuilder mysqlab;
	private Hexlabbuilder myhexlab;
	private Trilabbuilder mytrilab;
	private Sqastar mysqastar;
	private Hexastar myhexastar;
	private Triastar mytriastar;
	private boolean astarexists;
	private int lab_type = 0;
	
	/**
	 * The first method. The menu with the options.
	 */
	void init()
	{
		keyboard = new Scanner(System.in);
		int option = 0;
		
		while(true)
		{
			astarexists = false;
			System.out.println("\n\n\n/////__GENERADOR DE MAPAS__/////\n");
			System.out.println("Opciones:");
			System.out.println("1. Crear mapa mediante algoritmo dfs");
			System.out.println("2. Crear mapa mediante autómata celular");
			System.out.println("3. Salir");
			
			boolean correct_input = false;
			while(!correct_input)
			{
				System.out.println("\nIntroduzca el número de su opción deseada:");
				correct_input = true;
				String input = keyboard.next();
				try
				{
				   option = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
			
			
			 switch (option)
			 {
		         case 1:  	createlab();
		         			break;
		         case 2:  	createmap();
		         			printmap();
							break;
							
		         case 3:  	System.exit(0);
							break;
		         default:
		        	 		break;
			 }
		}
		
	}
	
	/**
	 * That methods let you chose what kind of map do you want to create.
	 */
	void createmap()
	{
		boolean correct_input = false;
		do
		{
			System.out.println("Tipo de mapa:");
			System.out.println("1. Pangea");
			System.out.println("2. Islas");
			System.out.println("3. Grandes continentes");
			
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("\nIntroduzca el número de su opción deseada:");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					lab_type = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(lab_type < 1 || lab_type > 3);
		
		do
		{
			size[0] = 0;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Columnas? (Tamaño en X, mínimo 25)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					size[0] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(size[0] < 25);
		
		do
		{
			size[1] = 0;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Filas? (Tamaño en Y, mínimo 25)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					size[1] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(size[1] < 25);
		
		mymap = new Mapbuilder(lab_type, size);
	}
	
	/**
	 * Method that collects the variables to creating the labyrinths.
	 */
	void createlab()
	{
		boolean correct_input;
		do
		{
			System.out.println("\nTipo de laberinto:");
			System.out.println("1. Habitaciones cuadradas");
			System.out.println("2. Habitaciones hexagonales");
			System.out.println("3. Habitaciones triangulares");
			
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("\nIntroduzca el número de su opción deseada:");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					lab_type = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(lab_type < 1 || lab_type > 3);
		
		do
		{
			size[0] = 0;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Columnas? (Tamaño en X, mínimo 2)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					size[0] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(size[0] < 2);
		
		do
		{
			size[1] = 0;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Filas? (Tamaño en Y, mínimo 2)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					size[1] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(size[1] < 2);
		
		do
		{
			start[0] = -1;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Columna de salida? (X, 0 válido)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					start[0] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(start[0] < 0 || start[0] >= size[0]);
		
		do
		{
			start[1] = -1;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Fila de salida? (Y, 0 válido)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					start[1] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(start[1] < 0 || start[1] >= size[1]);
		
		if(lab_type == 1)
			mysqlab = new Sqlabbuilder(size,start);
		else if(lab_type == 2)
			myhexlab = new Hexlabbuilder(size,start);
		else if(lab_type == 3)
			mytrilab = new Trilabbuilder(size,start);
		
		char opt;
		boolean astar = true;
		
		while(astar)
		{
			System.out.println("\n\n¿Quiere resolver hallar un camino entre dos celdas? y/n");
			opt = keyboard.next().charAt(0);
			switch (opt)
			{
		        case 'y':
		        case 'Y': 	astar();
		        			if(lab_type == 1)
								printsquare();
							else if(lab_type == 2)
								printhex();
							else if(lab_type == 3)
								printtri();
		        			init();
		        			break;
		        case 'n':
		        case 'N':	astar = false;
					        if(lab_type == 1)
								printsquare();
							else if(lab_type == 2)
								printhex();
							else if(lab_type == 3)
								printtri();
							break;
							
		        default:
		        	 		break;
			 }
		}
	}
	
	/**
	 * Method that collect the variables to find the path.
	 */
	void astar()
	{
		boolean correct_input = false;
		astarexists = true;
		int[] a_start = new int[2];
		int[] a_end = new int[2];
		
		do
		{
			a_start[0] = -1;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Columna de salida? (X, 0 válido)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					a_start[0] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(a_start[0] < 0 || a_start[0] >= size[0]);
		
		do
		{
			a_start[1] = -1;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Fila de salida? (Y, 0 válido)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					a_start[1] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(a_start[1] < 0 || a_start[1] >= size[1]);
		
		do
		{
			a_end[0] = -1;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Columna de llegada? (X, 0 válido)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					a_end[0] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(a_end[0] < 0 || a_end[0] >= size[0]);
		
		do
		{
			a_end[1] = -1;
			correct_input = false;
			while(!correct_input)
			{
				System.out.println("¿Fila de llegada? (Y, 0 válido)");
				correct_input = true;
				String input = keyboard.next();
				try
				{
					a_end[1] = Integer.parseInt(input);
				} catch (Exception e)
				{
				   System.out.println("Opción inválida.");
				   correct_input = false;
				}
			}
		}while(a_end[1] < 0 || a_end[1] >= size[1]);
		
		if(lab_type == 1)
			mysqastar = new Sqastar(mysqlab,a_start,a_end);
		else if(lab_type == 2)
			myhexastar = new Hexastar(myhexlab,a_start,a_end);
		else if(lab_type == 3)
			mytriastar = new Triastar(mytrilab,a_start,a_end);
	}
	
	/**
	 * Method that prints labyrinths of square cells.
	 */
	void printsquare()
	{
		char pathchar;
		int[] start = new int[2];
		int[] end = new int[2];
		
		if(astarexists)
		{
			start = mysqastar.getStart();
			end = mysqastar.getEnd();
		}
		
		for(int i = 0 ; i < (3*size[0] +1) ; i++)
		{
			System.out.printf("_");
		}
		System.out.printf("%n");
		for(int j = 0 ; j < size[1] ; j++)
		{
			for(int i = 0 ; i < size[0] ; i++)
			{
				if(astarexists && mysqastar.getPos(i, j))
				{
					if(i == start[0] && j == start[1])
						pathchar = 'O';
					else if(i == end[0] && j == end[1])
						pathchar = 'X';
					else
						pathchar = '*';
				}
				else
					pathchar = ' ';
				
				//si estoy en la primera columna...
				if(i == 0)
				{
					System.out.printf("|");
				}
				//Si estoy en la última columna...
				if(i == size[0] -1)
				{
					if(mysqlab.getCell(i, j).infoWall("down") == false) //si no hay pared abajo
						System.out.printf(" " + pathchar);
					else //si hay pared abajo
					{
						if(pathchar == ' ')
							pathchar = '_';
						System.out.printf("_" + pathchar);
					}
					
					if(mysqlab.getCell(i, j).infoWall("right") == true) //si hay pared derecha
						System.out.printf("|");
				}
				else
				{
					if(mysqlab.getCell(i, j).infoWall("down") == false) //si no hay pared abajo
					{
						System.out.printf(" " + pathchar);
						if(mysqlab.getCell(i, j).infoWall("right") == true) //si hay pared derecha
							System.out.printf("|");
						else
							System.out.printf(" ");
					}
					else //si hay pared abajo
					{
						if(pathchar == ' ')
							pathchar = '_';
						System.out.printf("_" + pathchar);
						if(mysqlab.getCell(i, j).infoWall("right") == true) //si hay pared derecha
							System.out.printf("|");
						else
							System.out.printf("_");
					}					
				}
				//si estoy en la última columna
				if(i == size[0] -1)
				{
					System.out.println("|");
				}
			}
		}
		for(int i = 0 ; i < (3*size[0] +1) ; i++)
		{
			System.out.printf("¯");
		}
		
		//System.out.println(mysqastar.toString());
	}
	
	/**
	 * Method that prints labyrinths of hexagonal cells.
	 */
	void printhex()
	{
		char pathchar = ' ';
		int[] start = new int[2];
		int[] end = new int[2];
		
		if(astarexists)
		{
			start = myhexastar.getStart();
			end = myhexastar.getEnd();
		}
		
		//roof
		for(int i = 0; i < size[0] ; i++)
		{
			if(i % 2 == 0)
				System.out.printf(" ___");
			else
				System.out.printf("    ");
		}
		
		System.out.printf("%n");
		
		for(int j = 0 ; j < size[1]; j++)
		{
			for(int k = 0 ; k < 2 ; k++)
			{
				for(int i = 0 ; i < size[0] ; i++)
				{

					if(astarexists && myhexastar.getPos(i, j))
					{
						
						if(i == start[0] && j == start[1])
							pathchar = 'O';
						else if(i == end[0] && j == end[1])
							pathchar = 'X';
						else
						
							pathchar = '*';
					}
					else
						pathchar = ' ';
					
					
					if(i == 0)
					{
						if(k == 0)
							System.out.printf("/");
						else
							System.out.printf("\\");
					}
					
					if((k+i)%2 == 0)
						System.out.printf(" " + pathchar + " ");
					else
					{
						if(j == 0 && k == 0)
						{
								if(i != size[0]-1)
									System.out.printf("\\___/");
								else
									System.out.printf("\\___");
						}
						else if(i%2 == 0)
						{
							if (myhexlab.getCell(i,j).infoWall("left") == true)
								System.out.printf("\\");
							else if( i != 0)
								System.out.printf(" ");
							if (myhexlab.getCell(i,j).infoWall("down") == true || j == size[1]-1)
								System.out.printf("___");
							else
								System.out.printf("   ");
							if (myhexlab.getCell(i,j).infoWall("right") == true)
								System.out.printf("/");
							else if( i != size[0]-1)
								System.out.printf(" ");
						}
						else
						{
							if (myhexlab.getCell(i,j-1).infoWall("left") == true)
								System.out.printf("\\");
							else
								System.out.printf(" ");
							if (myhexlab.getCell(i,j-1).infoWall("down") == true || j-1 == size[1]-1)
								System.out.printf("___");
							else
								System.out.printf("   ");
							if (myhexlab.getCell(i,j-1).infoWall("right") == true)
								System.out.printf("/");
							else if( i != size[0]-1)
								System.out.printf(" ");
						}
					}
					
					if(i == size[0]-1)
					{
						if((k+i)%2 != 0)
						{
							if(i % 2 == 0 || j != 0)
								System.out.printf("/");
						}
						else
							System.out.printf("\\");
					}
					
				}
				
				System.out.printf("\n");
			}
			
		}

		for(int i = 0; i < size[0] ; i++)
		{
			if(i == 0)
				System.out.printf(" ");
			
			if(i % 2 == 0)
				System.out.printf("   ");
			else
				System.out.printf("\\___/");
		}
		
		//System.out.println(myhexastar.toString());
		//System.out.println(myhexlab.toString());
	}
	
	/**
	 * Method that prints labyrinths of triangular cells.
	 */
	void printtri()
	{
		char pathchar = ' ';
		int[] start = new int[2];
		int[] end = new int[2];
		
		if(astarexists)
		{
			start = mytriastar.getStart();
			end = mytriastar.getEnd();
		}
		
		//roof
		System.out.printf("   ");
		for(int i = 0; i < size[0]/2 ; i++)
		{
				System.out.printf("·-----");
		}
		System.out.printf("·");
		
		System.out.printf("%n");
		
		for(int j = 0 ; j < size[1]; j++)
		{
			for(int k = 0 ; k < 3 ; k++)
			{
				for(int i = 0 ; i < size[0] ; i++)
				{

					if(astarexists && mytriastar.getPos(i, j))
					{
						
						if(i == start[0] && j == start[1])
							pathchar = 'O';
						else if(i == end[0] && j == end[1])
							pathchar = 'X';
						else
						
							pathchar = '*';
					}
					else
						pathchar = ' ';
					
					if((i+j) % 2 == 0)
					{
						if(i == 0)
						{
							if(k == 0)
								System.out.printf("  /");
							else if(k == 1)
								System.out.printf(" /");
						}
						
					}
					else
					{
						if(i == 0)
						{
							if(k == 0)
								System.out.printf(" \\");
							else if(k == 1)
								System.out.printf("  \\");
						}
					}
					
					if(k == 0)
					{
						if((j+i)%2 == 0)
						{
							System.out.printf(" ");
							if (mytrilab.getCell(i,j).infoWall("right") == true || i == size[0]-1)
								System.out.printf("\\");
							else
								System.out.printf(" ");
						}
						else
						{
							System.out.printf(" " + pathchar + " ");
							if (mytrilab.getCell(i,j).infoWall("right") == true || i == size[0]-1)
								System.out.printf("/");
							else
								System.out.printf(" ");
						}
					}
					else if(k == 1)
					{
						if((j+i)%2 == 0)
						{
							System.out.printf(" " + pathchar + " ");
							if (mytrilab.getCell(i,j).infoWall("right") == true || i == size[0]-1)
								System.out.printf("\\");
							else
								System.out.printf(" ");
						}
						else
						{
							System.out.printf(" ");
							if (mytrilab.getCell(i,j).infoWall("right") == true || i == size[0]-1)
								System.out.printf("/");
							else
								System.out.printf(" ");
						}
					}
					else if(k == 2)
					{
						
						if(j % 2 != 0 && i == 0)
							System.out.printf("   ");

						if((i+j) % 2 == 0)
						{

							System.out.printf("·");
							if (mytrilab.getCell(i,j).infoWall("down") == true || j == size[1]-1)
								System.out.printf("-----");
							else
								System.out.printf("     ");

						}
						if(i == size[0]-1)
							System.out.printf("·");
						
				
					}
				}
				
				
				System.out.printf("\n");
			}
		}
		
		//System.out.println(mytrilab.toString());
		//System.out.println("\n\n\n");
		//System.out.println(mytriastar.toString());
	}
	
	/**
	 * Method that prints maps.
	 */
	void printmap()
	{
		for (int j = 0 ; j < size[1] ; j++)
		{
			for (int i = 0 ; i < size[0] ; i++)
			{
				if (mymap.getMap()[j][i].getType() == 3)
				{
					System.out.print("^^^");
				}
				
				if (mymap.getMap()[j][i].getType() == 2)
				{
					System.out.print("---");
				}
				
				if (mymap.getMap()[j][i].getType() == 1)
				{
					System.out.print("~~~");
				}
				
				
			}
			
			System.out.println("");
		}
	}
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main (String args[])
	{
		Executor mysqlab = new Executor();
		mysqlab.init();
	}
}
