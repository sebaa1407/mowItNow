package fr.xebia.mowitNow.model;

import java.util.ArrayList;
import fr.xebia.mowitNow.model.Position;

/**
 * 
 * @author sebaa
 *
 */
public class MowerMovingItinerary {
	private Position firstPosition;
	private String firstDirection;

	private Position endPosition;
	private String EndDirection;

	private ArrayList<Position> mowerMovingItineraryPosition;
	private boolean isRun; 

	public Position getFirstPosition() {
		return firstPosition;
	}

	public void setFirstPosition(Position firstPosition) {
		this.firstPosition = firstPosition;
	}

	public String getFirstDirection() {
		return firstDirection;
	}

	public void setFirstDirection(String firstDirection) {
		this.firstDirection = firstDirection;
	}

	public String getEndDirection() {
		return EndDirection;
	}

	public void setEndDirection(String endDirection) {
		EndDirection = endDirection;
	}

	public ArrayList<Position> getMowerMovingItineraryPosition() {
		return mowerMovingItineraryPosition;
	}

	public void setMowerMovingItineraryPosition(ArrayList<Position> mowerMovingItineraryPosition) {
		this.mowerMovingItineraryPosition = mowerMovingItineraryPosition;
	}

	public Position getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(Position endPosition) {
		this.endPosition = endPosition;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

}
