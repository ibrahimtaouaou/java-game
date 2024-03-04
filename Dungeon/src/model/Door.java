package model;
public class Door extends GameObject {

	public enum DoorType {	DOOR, PORTAL;  }
	private boolean open;
	private DoorType type;

	/**
	 * Door constructor
	 * @param posX the position in the X axis of the Door
	 * @param posY the position in the Y axis of the Door
	 * @param type the type of the Door
	 */
	public Door(int posX, int posY, DoorType type) {
		super(posX, posY);
		this.type = type;
	}

	/**
	 * Open the door
	 */
	public void open(){
		setOpen(true);
	}
	
	/**
	 * Check if the door is open or not
	 * @return true if the door is open, false otherwise
	 */
	public boolean isOpen() {
		return this.open;
	}

	/**
	 * Open/close the door and set obstacle to false/true
	 * @param open true to open and false to close
	 */
	public void setOpen(boolean open) {
		this.open = open;
		this.setObstacle(!open);
	}

	/**
	 * @return the type
	 */
	public DoorType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(DoorType type) {
		this.type = type;
	}

	
} 