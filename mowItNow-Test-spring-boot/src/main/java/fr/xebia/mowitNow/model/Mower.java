package fr.xebia.mowitNow.model;

 

/**
 * 
 * @author sebaa
 *
 */
public class Mower {
	private Position position;
	private String direction;
	private  String instructions;
 
	 public Mower(int xPosition,int yPosition,String direction,String instructions) {
	    	
			Position lPosition=  new Position();
			lPosition.setX(xPosition);
			lPosition.setY(yPosition);
			this.position = lPosition;
			this.direction = direction;
			this.instructions=instructions;
			

		}

	public Mower() {
		 
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

 
 
}
