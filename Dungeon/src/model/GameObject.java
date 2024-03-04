package model;

import java.io.Serializable;

public abstract class GameObject  implements Serializable{

	private static final long serialVersionUID = -2396383856128812416L;
	private int posX;
	private int posY;
	private boolean obstacle;

	/**
	 * GameObject constructor
	 * @param posX the position in the X axis of the gameObject
	 * @param posY the position in the Y axis of the gameObject
	 */
	public GameObject(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.obstacle = true;
	}
	
	/**
	 * Check if the current object is at the given position
	 * @param posX the position in the X axis to check
	 * @param posY the position in the Y axis to check
	 */
	public boolean isAtPosition(int posX, int posY) {
		return (this.posX == posX && this.posY == posY);
	}
	
	/**
	 * Get posX
	 * @return the position in the X axis of the gameObject
	 */
	public int getPosX() {
		return this.posX;
	}

	/**
	 * Set posX
	 * @param posX the position in the X axis of the gameObject
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Get posY
	 * @return the position in the Y axis of the gameObject
	 */
	public int getPosY() {
		return this.posY;
	}

	/**
	 * Set posY
	 * @param posY the position in the Y axis of the gameObject
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Check if the gameObject is an obstacle
	 * @return true if the gameObject is an obstacle
	 */
	public boolean isObstacle() {
		return this.obstacle;
	}

	/**
	 * @param obstacle the obstacle to set
	 */
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}
}