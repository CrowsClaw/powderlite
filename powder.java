import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;
import java.awt.image.*;
import java.util.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.io.*;




class DoubleBuffer extends Panel
{
	/*
	 *Credits to:
	 *http://www.codeproject.com/Articles/2136/Double-buffer-in-standard-Java-AWT
	 *
	 **/

	private int bufferWidth;
	private int bufferHeight;
	private Image bufferImage;
	private Graphics bufferGraphics;

	public DoubleBuffer()
	{
		super();
	}

	public void update(Graphics g)
	{
		paint(g);
	}




	public void paint(Graphics g)
	{

		if(bufferWidth!=getSize().width ||

      	bufferHeight!=getSize().height ||

     	 bufferImage==null || bufferGraphics==null)
		{
			resetBuffer();
		}




        if(bufferGraphics!=null)
        {

        //this clears the offscreen image, not the onscreen one

        bufferGraphics.clearRect(0,0,bufferWidth,bufferHeight);

        //calls the paintbuffer method with

        //the offscreen graphics as a param

        paintBuffer(bufferGraphics);

        //we finaly paint the offscreen image onto the onscreen image

        g.drawImage(bufferImage,0,0,this);

        }

	}




	public void resetBuffer()
	{
		 // always keep track of the image size

    bufferWidth=getSize().width;

    bufferHeight=getSize().height;


    //    clean up the previous image

    if(bufferGraphics!=null){

        bufferGraphics.dispose();

        bufferGraphics=null;

    }

    if(bufferImage!=null){

        bufferImage.flush();

        bufferImage=null;

    }

    System.gc();


    //    create the new image with the size of the panel

    bufferImage=createImage(bufferWidth,bufferHeight);

    bufferGraphics=bufferImage.getGraphics();

	}

	public void paintBuffer(Graphics g)
	{

	}

}


public class powder extends DoubleBuffer
{

	public static void main(String[] args)
	{

  		Frame f = new Frame();

  		f.addWindowListener(new java.awt.event.WindowAdapter() {

       		public void windowClosing(java.awt.event.WindowEvent e) {

       		System.exit(0);

       		};

     	});

  		powder window = new powder();  //change gameStart to your file name (two times on this line)

  		window.setSize(1000,650);

		f.add(window);

		f.pack();

		window.init();

		f.setSize(1000,650);/*size of frame*/

		f.show();

	}




	public powder()
	{
		super();
	}

	//declare variables here

    World map = new World();



	public void init()  //runs once at the beginning of the program
	{
		map.init();
	}



	public void paintBuffer(Graphics g)//makes pretty pictures
	{
		delay(10);

		//all paint method code should go before repaint();

		map.draw(g);

		map.Update();

		repaint();

	}

	//write additional methods here

	public static void delay(int n)	{long startDelay = System.currentTimeMillis();long endDelay = 0;while (endDelay - startDelay < n)endDelay = System.currentTimeMillis();	}

	//required for user input

	public boolean mouseMove(Event e, int x, int y){return InputHandler.mouseUpdate(x,y);}

	public boolean mouseDrag(Event e, int x, int y){return InputHandler.mouseUpdate(x,y);}

	public boolean mouseDown(Event e, int x, int y){return InputHandler.mouseChange(e,true );}

	public boolean mouseUp  (Event e, int x, int y){return InputHandler.mouseChange(e,false);}

	public boolean keyDown(Event e, int key){return InputHandler.keyChange(e,key,true);}

	public boolean keyUp  (Event e, int key){return InputHandler.keyChange(e,key,false);}


}




class InputHandler	//class to handle input
{

	public InputHandler(){	}

	//ids for keys

	public static final int id_UP		= 1004;
	public static final int id_DOWN		= 1005;
	public static final int id_LEFT		= 1006;
	public static final int id_RIGHT	= 1007;
	public static final int id_SPACE	= 32;
	public static final int id_X		= 120;
	public static final int id_W		= 119;
	public static final int id_A		= 97;
	public static final int id_S		= 115;
	public static final int id_D		= 100;
	public static final int id_I		= 105;
	public static final int id_K		= 107;
	public static final int id_J		= 106;
	public static final int id_L		= 108;
	public static final int id_P		= 112;
	public static final int id_ESC		= 27;




	//boolean values determine if key is pressed

	public static boolean UP	= false;
	public static boolean DOWN	= false;
	public static boolean LEFT	= false;
	public static boolean RIGHT	= false;
	public static boolean SPACE = false;
	public static boolean X = false;
	public static boolean W		= false;
	public static boolean A		= false;
	public static boolean S		= false;
	public static boolean D		= false;
	public static boolean I		= false;
	public static boolean J		= false;
	public static boolean K		= false;
	public static boolean L		= false;
	public static boolean P		= false;
	public static boolean ESC		= false;




	//mouse info

	public static boolean MOUSE_LEFT	= false;
	public static boolean MOUSE_RIGHT	= false;
	public static int MOUSE_X	= 0;
	public static int MOUSE_Y	= 0;




	public static boolean keyChange(Event e, int key, boolean newKeyState)
	{		//changes key state variables to correct values

		switch(key)
		{

			case InputHandler.id_UP:	//Up

				InputHandler.UP = newKeyState;

				break;

			case InputHandler.id_DOWN:	//Down

				InputHandler.DOWN = newKeyState;

				break;

			case InputHandler.id_LEFT:	//Left

				InputHandler.LEFT = newKeyState;

				break;

			case InputHandler.id_RIGHT:	//Right

				InputHandler.RIGHT = newKeyState;

				break;

			case InputHandler.id_SPACE://space

				InputHandler.SPACE=newKeyState;

				break;

			case InputHandler.id_X://space

				InputHandler.X=newKeyState;

				break;

			case InputHandler.id_W:	//Up

				InputHandler.W = newKeyState;

				break;

			case InputHandler.id_S:	//Down

				InputHandler.S = newKeyState;

				break;

			case InputHandler.id_A:	//Left

				InputHandler.A = newKeyState;

				break;

			case InputHandler.id_D:	//Right

				InputHandler.D = newKeyState;

				break;

			case InputHandler.id_I:	//Up

				InputHandler.I = newKeyState;

				break;

			case InputHandler.id_J:	//Down

				InputHandler.J = newKeyState;

				break;

			case InputHandler.id_K:	//Left

				InputHandler.K = newKeyState;

				break;

			case InputHandler.id_L:	//Right

				InputHandler.L = newKeyState;

				break;

			case InputHandler.id_P:

				InputHandler.P = newKeyState;

				break;

			case InputHandler.id_ESC:

				InputHandler.ESC = newKeyState;

				break;

			default:

				System.out.print(key);	//print any unbound key presses- may spam console - used for key mapping

				if(newKeyState){	}

				else{	}

				break;
		}

		return true;

	}

	public static boolean mouseUpdate(int x, int y)	//handles mouse movement of mouse
	{
		InputHandler.MOUSE_X = x;

		InputHandler.MOUSE_Y = y;

		return true;
	}




	public static boolean mouseChange(Event e, boolean newKeyState)	//handles mouse clicks
	{
		if(e.metaDown())

			InputHandler.MOUSE_RIGHT	= newKeyState;

		else

			InputHandler.MOUSE_LEFT		= newKeyState;

		return true;

	}

}

//write additional classes here




class brand //hacks
{
		private static Random rnd = new Random();
		public static int rand(int n)
		{
			return rnd.nextInt(n);
		}

}







class Log
{
	public static void Log(String s)
	{

		System.out.println(s);

	}

}




class Vector2 //movement class, which is essentially a collection of x and y coordinates
{
	private int x, y;

	public Vector2()
	{
		x = 0;

		y = 0;
	}



	public Vector2(int x, int y)
	{

		this.x = x;

		this.y = y;

	}


	public int getx()
	{
		return x;
	}




	public int gety()
	{
		return y;
	}


	public void move(int a, int b)
	{
		x += a;

		y += b;
	}

}




enum TileType {IMPASSABLE, FLOOR, WALL, OBJECT};

/*

 *An enum to determine the tile type

 **/





/*

 *Since this a rogue like-like game

 *and making maps in a nice way is hard

 *this class is used to make levels the old fashion way

 *

 **/







class CA
{
    private Random rnd = new Random();
    private int chance = 45;
    private int birthLimit = 4;
    private int deathLimit = 4;
    private int width, height;

    public CA() //I love the concept of this algorithm
    {
    }




    private boolean[][] initMap(boolean[][] map, int width, int height)
    {
		this.width = width;

		this.height = height;

			for(int x = 0; x < width; ++x)
			    {
					for(int y = 0; y < height; ++y)
					    {
								int random = rnd.nextInt(100);

										if(x == 0 || x == width - 1 || y == 0 || y == height - 1)
										    {
											map[x][y] = true;
										  	}

										else
										    {
											if(random < chance)
											    {

												map[x][y] = true;

											    }

											else
											    {

												map[x][y] = false;

											    }

										    }
							}

				}


			return map;

    }




    private int countNeighbors(boolean[][] map, int  gridX, int gridY)
    {
	int count = 0;


		for (int neighbourX = gridX - 1; neighbourX <= gridX + 1; neighbourX ++)
	    {
			for (int neighbourY = gridY - 1; neighbourY <= gridY + 1; neighbourY ++)
			    {

				if (neighbourX >= 0 && neighbourX < width && neighbourY >= 0 && neighbourY < height)
				    {

						if (neighbourX != gridX || neighbourY != gridY)
						{

							    if(map[neighbourX][neighbourY])
								{

								count +=1;

								}

						}

				    }

					else {

						count++;

					}

			}

		}

	return count;
   }




    private boolean[][] doSimulationStep(boolean[][] oldMap)
    {
		boolean[][] newMap = new boolean[width][height];

		for(int x = 0; x < oldMap.length; ++x)
		    {
			for(int y = 0; y < oldMap[x].length; ++y)
			    {

				int nbs = countNeighbors(oldMap, x, y);

				if(oldMap[x][y])
				    {

					if(nbs < deathLimit)
					    {

						newMap[x][y] = false;

					    }

					else
					    {

						newMap[x][y] = true;

					    }

				    }




				else
				    {
					if(nbs > birthLimit)
					    {

						newMap[x][y] = true;

					    }

					else
					    {

						newMap[x][y] = false;

					    }

				    }


			    }

		    }

		return newMap;

    }




    private boolean[][] genMap(int numSteps, int width, int height)
    {

	boolean[][] cellGrid = new boolean[width][height];

	cellGrid = initMap(cellGrid, width, height);

	for(int i = 0; i < numSteps; ++i)

	    {

		cellGrid = doSimulationStep(cellGrid);

	    }
	//	cellGrid = smoothMap(cellGrid);

	return cellGrid;
    }


    private char[][] toASCII(boolean[][] map)
    {

	char[][] thing = new char[width][height];

			for(int x = 0; x < map.length; ++x)
			    {

				for(int y = 0; y < map[x].length; ++y)
				    {

						if(map[x][y])
						    {

							thing[x][y] = '#';

						    }

						else
						    {

							thing[x][y] = '.';

						    }

					}

			    }


		return thing;
    }




    public char[][] genCharMap(int steps, int width, int height)
    {
    	boolean[][] map = genMap(steps, width, height);

    	char[][] realMap = toASCII(map);

    	return realMap;
    }

}


class Entity
{
	protected Vector2 pos;
	private BufferedImage img = null;
	private boolean canNotMove = false;
	private BufferedImage[] sheet;
	private int width = 16, height = 16;

	public Entity() //should not be called without screen coordinates
	{
		pos = new Vector2();

	}




	public Entity(int x, int y)
	{
		pos = new Vector2(x, y);
	}


	public int getx()
	{
		return pos.getx();
	}

	public int gety()
	{
		return pos.gety();
	}


	public void Update()
	{
			move();
	}

	public void Update(int x, int y)
	{

	}

	public void move(int x, int y)
	{

	}




	public void move()
	{
		if(InputHandler.W || InputHandler.UP)
		{

			pos.move(0, -16);
		}


		if(InputHandler.S || InputHandler.DOWN)
		{
			pos.move(0, 16);
		}

		if(InputHandler.A || InputHandler.LEFT)
		{
			pos.move(-16, 0);

		}

		if(InputHandler.D || InputHandler.RIGHT)
		{
			pos.move(-16, 0);
		}

	}


	public void loadImgSheet(String name)
	{
		try {
		BufferedImage temp = ImageIO.read(new File(name));
		img = temp.getSubimage(0,0, 16, 16);
		}

		catch (IOException e)
		{
			System.out.println("Image loading for the enemy has failed");
		}

	}

	public void loadSpriteSheet(String s, int rows, int cols)
	{
			BufferedImage bigImg = null;
		try{
			 bigImg = ImageIO.read(new File(s));
		}

		catch(IOException e)
		{

		}
		sheet = new BufferedImage[rows * cols];


			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        sheet[(i * cols) + j] = bigImg.getSubimage(
			            j * width,
			            i * height,
			            width,
			            height
			        );
			    }
			}

	}






	public void draw(Graphics g)
	{
		g.drawImage(img, pos.getx(), pos.gety(), null);

	}


}



/*

 *This class is the class used to get store the images for tiles

 *as well as get the tiles, based on a given tile type

 **/

class Tiles
{
	private ArrayList<BufferedImage> floors = new ArrayList<BufferedImage>(); //floor tiles
	private ArrayList<BufferedImage> nogos = new ArrayList<BufferedImage>(); //NOT GETTING THROUGH THESE
	private ArrayList<BufferedImage> walls = new ArrayList<BufferedImage>(); //walls
	private ArrayList<BufferedImage> random = new ArrayList<BufferedImage>(); //have a little bit of fun, shall we?
	private ArrayList<BufferedImage> objects = new ArrayList<BufferedImage>(); //objects

    Tiles() {}

	public void pushBackTile(String filename, TileType type)
	{
		String realName = "";

		realName += "graphics/" + filename + ".png";

		//force you to use .png
		switch(type)
		{
			case WALL:
			{

				try{
					walls.add(ImageIO.read(new File(realName)));

					Log.Log("Wall tiles loaded!");




				}

				catch(IOException e)
				{

					Log.Log("Something crapped itself, and you have no wall graphics");

				}

			}

			break;

			case FLOOR:
			{

				try{
					floors.add(ImageIO.read(new File(realName)));

					Log.Log("Floor tiles loaded!");

				}

				catch(IOException e)
				{

					Log.Log("Something crapped itself, and you have no floor graphics");

				}

			}

			break;

			case IMPASSABLE:
			{

				try{
					nogos.add(ImageIO.read(new File(realName)));
					Log.Log("Randy tiles loaded!");
				}

				catch(IOException e)
				{
					Log.Log("Something crapped itself, and you have no Impassable graphics");
				}

			}

			break;

			case OBJECT:
			{

				try{
					objects.add(ImageIO.read(new File(realName)));
					Log.Log("Object tiles loaded!");
				}

				catch(IOException e)
				{
					Log.Log("Something crapped itself, and you have no object graphics");
				}

			}

			break;

			default:
				break;

		}

	}




	public void pushBackTile(String filename)
	{

		String realName = "";

		realName += "graphics/" + filename + ".png";

		//filename += ".png";

		try{

			random.add(ImageIO.read(new File(realName)));

		}




		catch(IOException e)
		{

			Log.Log("Something crapped itself, and you have no graphics");

		}

	}







    public BufferedImage getTile(TileType type)
    {
        switch(type)

	    {
	    case IMPASSABLE: return nogos.get(brand.rand(nogos.size()));

	    case FLOOR: return floors.get(brand.rand(floors.size()));

	    case WALL: return walls.get(brand.rand(walls.size()));

	    default: return random.get(brand.rand(random.size()));
	    }

    }
}





class Tile
{
	private boolean isImpassable = false;
	private BufferedImage img = null;
	private int xCoord, yCoord;

	public Tile(boolean passable, BufferedImage img)
	{
		isImpassable = passable;
		this.img = img;
		xCoord = 0;
		yCoord = 0;

	}

	public Tile(int x, int y, boolean passable, BufferedImage img)
	{
		this.img = img;
		isImpassable = passable;
		xCoord = x;
		yCoord = y;

	}

	public Tile(int x, int y, BufferedImage img)
	{
		this.img = img;
		xCoord = x;
		yCoord = y;
	}


	public boolean isConcrete()
	{
		return isImpassable;
	}

	public int getx()
	{
		return xCoord;
	}

	public int gety()
	{
		return yCoord;
	}

	public BufferedImage getImg()
	{
		return img;
	}
}


class Enemy extends Entity
{
	private int[] movements = {-16, 0, 16 }; //the worst AI ever
	private int xmove, ymove;

	public Enemy()
	{
		super();
	}




	public Enemy(int x, int y)
	{
		super(x, y);
	}


	public void move()
	{
		int posX = brand.rand(3), posY = brand.rand(3);

		pos.move(movements[posX], movements[posY]);
	}

	public void next()
	{
		xmove = movements[brand.rand(movements.length)];
		ymove = movements[brand.rand(movements.length)];
	}

	public int nextx()
	{
		return xmove;
	}

	public int nexty()
	{
		return ymove;
	}


	public void Update(int x, int y)
	{
		move();
	//	System.out.println(x + " " + y);
	}


	public void move(int x, int y)
	{
		pos.move(x, y);
	}



	public void Update()
	{
		super.Update();
	}

}




class Player extends Entity
{

	public Player()
	{
		super();
	}




	public Player(int x, int y)
	{
		super(x, y);
	}


	public void Update(int x, int y)
	{
		move(x, y);
	}


	public void move(int x, int y)
	{
		pos.move(x, y);
	}

}



class World
{
    private Tiles t = new Tiles();
    private TileType floor = TileType.FLOOR;
    private TileType wall = TileType.WALL;
    private TileType object = TileType.OBJECT;
    private CA realMaps = new CA();
    private BufferedImage realFloor = null, realWall = null, realObject = null;
    private char[][] map;
    private char[][] objectMap;
    private int objectLimit = 5;
    private int width = 200, height = 200;
    private Entity rick = null;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>(); //little bastards
    private Tile[][] tileMap;


	private final char Floor = '.';
	private final char Wall = '#';
	private final char Object = '$';
	private final char Player = '@';




    public World()
    {

    } 




    public void init()
    {
		t.pushBackTile("floor_1", floor);
		t.pushBackTile("floor_2", floor);
		t.pushBackTile("floor_3", floor);
		t.pushBackTile("floor_4", floor);
		t.pushBackTile("wall_1", wall);
		t.pushBackTile("wall_2", wall);

	    map = realMaps.genCharMap(2, width, height);
	    tileMap = new Tile[width][height];

	    enemies.add(new Enemy(16 * 50, 16 * 12));
	    enemies.get(0).loadImgSheet("zombies.png");
	    enemies.add(new Enemy(16 * brand.rand(35), 16 * brand.rand(40)));
	    enemies.get(1).loadImgSheet("zombies.png");
	    enemies.add(new Enemy(16 * brand.rand(30), 16 * brand.rand(35)));
	    enemies.get(2).loadImgSheet("zombies.png");




	  //  copyMap(map, objectMap);




	    realFloor = t.getTile(floor);

	    realWall = t.getTile(wall);

	 //   realObject = t.getTile(object);

	 rick = new Player(20 * 16, 10 * 16);
	 rick.loadImgSheet("Player0.png");




		 for(int x = 0; x < tileMap.length; ++x)
		 {
		 	for(int y = 0; y < tileMap[x].length; ++y)
		 	{
		 		if(map[x][y] == Wall)
		 		{
		 			tileMap[x][y] = new Tile(x * 16, y * 16, true, realWall);

		 		}

		 		else
		 		{

		 			tileMap[x][y] = new Tile(x * 5, y * 5, false, realFloor);

		 		}

		 	}

		 }

    }




    public void draw(Graphics g)
    {
		drawTiles(g);
		rick.draw(g);
		for(Entity e : enemies)
		{
			e.draw(g);
		}
    }




    public void drawTiles(Graphics g)
    {
	for(int x = 0; x < tileMap.length; ++x)
	    {
		for(int y = 0; y < tileMap[x].length; ++y)
		    {
		    	g.drawImage(tileMap[x][y].getImg(), x * 16, y * 16, null);

		    }

	    }

    }




    public void drawObjects(Graphics g)
    {
    	for(int x = 0; x < objectMap.length; ++x)
    	{
    		for(int y = 0; y < objectMap[x].length; ++y)
    		{
    			if(objectMap[x][y] == Object)
    			{

    				g.drawImage(realObject,  x * 5 , y * 5, null);
    			}

    		}

    	}

    }





    public void reGen()
    {
    	map = realMaps.genCharMap(2, width, height);

    	 for(int x = 0; x < tileMap.length; ++x)
	 		{
			 	for(int y = 0; y < tileMap[x].length; ++y)
			 	{
			 		if(map[x][y] == Wall)
			 		{

			 			tileMap[x][y] = new Tile(x * 16, y * 16, true, realWall);
			 		}

			 		else
			 		{
			 			tileMap[x][y] = new Tile(x * 5, y * 5, realFloor);

			 		}

			 	}

	 		}

    //	copyMap(map, objectMap);

    }




    public void copyMap(char[][] orgMap, char[][] destMap)
    {
    	for(int x = 0; x < orgMap.length; ++x)
    	{
    		for(int y = 0; y < orgMap[x].length; ++y)
    		{
    			destMap[x][y] = orgMap[x][y];
    		}

    	}

    }




    public void placeObjects()
    {
    	int objectCount = 0;
    	int rrandIndex = brand.rand(objectMap.length), crandIndex = brand.rand(objectMap[0].length);

    	while(objectCount < objectLimit)
    	{

    		if(objectMap[rrandIndex][crandIndex] != Wall)
    		{

    			objectMap[rrandIndex][crandIndex] = Object;
    			objectCount++;

    		}

    	}

    }

    public boolean checkCollisions(int x, int y)
    {

    	if(x < 0)
    	{
    		x = 0;
    	}

    	if (y < 0)
    	{
    		y = 0;
    	}

    	if(x > width)
    	{
    		x = width;
    	}

    	if (y > height)
    	{
    		y = height;
    	}

		System.out.println(x + " " + y); //used for debugging

		return tileMap[x][y].isConcrete();


    }


	public void rickUpdate()
	{
			if(InputHandler.W || InputHandler.UP)
		{
			if(!checkCollisions(rick.getx() / 16, (rick.gety() - 16) / 16))
			{
				rick.Update(0, -16);
			}

		}




		else if(InputHandler.S || InputHandler.DOWN)
		{
			if(!checkCollisions(rick.getx() / 16, (rick.gety() + 16)/ 16))
			{
				rick.Update(0, 16);
			}

		}


		else if(InputHandler.A || InputHandler.LEFT)
		{
			if(!checkCollisions((rick.getx() - 16)/ 16 , rick.gety() / 16))
			{
				rick.Update(-16, 0);
			}

		}




		else if(InputHandler.D || InputHandler.RIGHT)
		{
			if(!checkCollisions((rick.getx() + 16) / 16 , rick.gety() / 16))
			{
				rick.Update(16, 0);
			}

		}


		else
		{
			rick.Update(0, 0);
		}
	}

	public void enemyUpdate()
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			Enemy e = enemies.get(i);
			e.next();

				if(InputHandler.W || InputHandler.UP)
				{
					if(!checkCollisions(e.nextx() / 16, (e.nexty() - 16) / 16))
					{
						e.Update(0, e.nexty());
					}

				}




				else if(InputHandler.S || InputHandler.DOWN)
				{
					if(!checkCollisions(e.nextx() / 16, (e.nexty() + 16)/ 16))
					{
						e.Update(0, e.nexty());
					}

				}


				else if(InputHandler.A || InputHandler.LEFT)
				{
					if(!checkCollisions((e.nextx() - 16)/ 16 , e.nexty() / 16))
					{
						e.Update(e.nextx(), 0);
					}

				}




				else if(InputHandler.D || InputHandler.RIGHT)
				{
					if(!checkCollisions((e.nextx() + 16) / 16 , e.nexty() / 16))
					{
						e.Update(e.nextx(), 0);
					}

				}


				else
				{
					e.Update(0, 0);
				}
			}
	}

    public void Update()
    {

		rickUpdate();
		enemyUpdate();



    	if(InputHandler.X)
		{
			reGen();
		}

	}

}