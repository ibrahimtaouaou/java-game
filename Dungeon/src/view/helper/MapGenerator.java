package view.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import model.Block;
import model.Door;
import model.Door.DoorType;
import model.Game;
import model.GameObject;

public class MapGenerator {

	private static final byte[][] square1 =
			{{0,0,0,1,0,0,0,0},
			 {0,0,0,1,0,0,0,0},
			 {0,1,1,1,0,0,0,0},
			 {0,0,0,1,0,1,1,0},
			 {0,0,0,1,1,1,0,0},
			 {0,0,0,0,0,0,0,0},
			 {0,0,0,1,0,0,1,1},
			 {0,0,1,1,0,0,0,0}};
	private static final byte[][] square2 =
			{{1,1,0,0,0,0,0,0},
			 {1,0,0,0,0,0,0,0},
			 {0,0,0,1,1,1,1,1},
			 {0,0,0,0,0,1,0,0},
			 {0,0,0,0,0,1,0,0},
			 {0,0,1,0,0,1,1,0},
			 {0,0,1,0,0,0,0,0},
			 {0,0,1,0,0,0,0,1}};
	private static final byte[][] square3 =
			{{0,1,1,0,0,0,0,1},
			 {0,0,1,0,0,0,0,0},
			 {0,1,1,0,0,0,1,0},
			 {0,0,0,0,0,0,1,1},
			 {0,0,0,0,0,0,0,0},
			 {0,0,1,1,1,1,0,0},
			 {0,0,0,0,1,0,0,0},
			 {0,0,0,0,1,0,0,0}};
	private static final byte[][] square4 =
			{{1,1,1,0,0,0,1,1},
			 {0,0,0,1,0,0,0,0},
			 {0,0,0,1,0,0,0,0},
			 {0,0,0,0,0,1,1,0},
			 {0,0,1,1,1,1,0,0},
			 {0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,1,1},
			 {1,1,1,0,0,0,0,0}};

	private final static int BASIC     = 0;
	private final static int ROTATE90  = 1;
	private final static int ROTATE180 = 2;
	private final static int SYMETRIEV = 3;
	private static List<GameObject> map;
	private static Random randomGenerator;
	
	/**
	 * generate the map
	 * @return the objetcs in the map
	 */
	public static List<GameObject> generate(){
		map = new ArrayList<>();
		randomGenerator = new Random();
		List<byte[][]> squares = Arrays.asList(square1,square2,square3,square4);
		/* Drawing all zones then the edge */
		for(int horizontal = 0; horizontal <= Game.getWidthMap()/8 -1; horizontal++){
			for(int vertical = 0; vertical <= 1; vertical++){
				int i = randomGenerator.nextInt(squares.size());
				draw(squares.get(i), horizontal, vertical);
			}
		}
		edgeAndDoor();
		
		return map;
	}
	
	/**
	 * draw the different possibilities
	 * @param square
	 * @param decalX
	 * @param decalY
	 */
	private static void draw(byte[][] square, int decalX, int decalY){
		switch (randomGenerator.nextInt(4)) {
		case BASIC:     basic(square, decalX, decalY);break;
		case ROTATE90:  rotate90(square, decalX, decalY);break;
		case ROTATE180: rotate180(square, decalX, decalY);break;
		case SYMETRIEV: symetrieV(square, decalX, decalY);break;
		}
	}

	/**
	 * draw the basic map
	 * @param square
	 * @param decalX
	 * @param decalY
	 */
	private static void basic(byte[][] square, int decalX, int decalY){
		for (int y = 0; y < square[0].length; y++) {
			for (int x = 0; x < square.length; x++) {
				if(square[x][y] == 1){
					map.add(new Block((y + 1) + decalX*8, (x + 1) + decalY*8));
				}
			}
		}
	}
	/**
	 * rotate the maps
	 * @param square
	 * @param decalX
	 * @param decalY
	 */
	private static void rotate90(byte[][] square, int decalX, int decalY){
		for (int x = square[0].length - 1; x >= 0; x--) {
			for (int y = 0; y < square.length ; y++) {
				if(square[x][y] == 1){
					map.add(new Block((8 - x) + decalX*8, (y + 1) + decalY*8));
				}
			}
		}
	}
	/**
	 * rotate the maps
	 * @param square
	 * @param decalX
	 * @param decalY
	 */
	private static void rotate180(byte[][] square, int decalX, int decalY){
		for (int y = 0; y < square[0].length; y++) {
			for (int x = square.length - 1; x >= 0 ; x--) {
				if(square[x][y] == 1){
					map.add(new Block((8 - y) + decalX*8, (8 - x) + decalY*8));
				}
			}
		}
	}
	/**
	 * rotate the maps
	 * @param square
	 * @param decalX
	 * @param decalY
	 */
	private static void symetrieV(byte[][] square, int decalX, int decalY){
		for (int y = square[0].length - 1; y >= 0; y--) {
			for (int x = 0; x <  square.length ; x++) {
				if(square[x][y] == 1){
					map.add(new Block((y + 1) + decalX*8, (8 - x) + decalY*8));
				}
			}
		}
	}
	/**
	 * draw borders and door
	 */
	private static void edgeAndDoor(){
		boolean set = false;
		Random r = new Random();
		List<GameObject> tmp = new ArrayList<>();
		while(!set){
			tmp.clear();
			int posX = 0;

			for(int i = 0; i < Game.getWidthMap(); i++){
				while(posX == 0){
					posX = r.nextInt(Game.getWidthMap() - 1);
				}
				for(int j = 0; j < 18; j++){
					if(i == 0 || i == Game.getWidthMap() -1 || j == 0 || j == 18 -1){
						if(!set && i == posX && isFree(posX, j)){
							tmp.add(new Door(i, j, DoorType.DOOR));
							set = true;
						}else{
							tmp.add(new Block(i, j));
						}
					}
				}
			}
		}
		map.addAll(tmp);
	}
	/**
	 * check if the position is free
	 * @param x
	 * @param y
	 * @return the free position
	 */
	private static boolean isFree(int x, int y) {
		for(GameObject o : map){
			if(o.isAtPosition(x, y + 1) || (o.isAtPosition(x, y - 1))){
				return false;
			}
		}
		return true;
	}

}
