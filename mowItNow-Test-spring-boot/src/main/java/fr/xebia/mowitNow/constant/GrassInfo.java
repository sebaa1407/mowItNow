package fr.xebia.mowitNow.constant;

/**
 * 
 * @author sebaa
 *
 */
public class GrassInfo {

	/**
	 * The maximum ordinate of the ground
	 */
	private int length;

	/**
	 * Maximum abscissa of the ground
	 */
	private int width;
	
	public GrassInfo(int length, int width) {
		this.length = length;
		this.width = width;
	}

	public GrassInfo() {
	 
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

 
}
