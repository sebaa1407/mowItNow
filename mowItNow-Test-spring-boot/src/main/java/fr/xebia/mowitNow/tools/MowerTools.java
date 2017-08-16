package fr.xebia.mowitNow.tools;

import java.util.ArrayList;

import fr.xebia.mowitNow.constant.Direction;
import fr.xebia.mowitNow.constant.GrassInfo;
import fr.xebia.mowitNow.constant.Instruction;
import fr.xebia.mowitNow.exception.MowerException;
import fr.xebia.mowitNow.model.Mower;
import fr.xebia.mowitNow.model.MowerMovingItinerary;
import fr.xebia.mowitNow.model.Position;
import fr.xebia.mowitNow.model.ProcessView;

/**
 * 
 * @author sebaa  
 *                    =+1 NORTH 
 *         =-1 WEST ----|---- =+1 EAST
 *                    =-1 SOUTH
 * 
 * 
 *
 */
public class MowerTools implements IMowerTools{
	/**
	 * If the position is inside the lawn.
	 * 
	 * 
	 * @param new
	 *            Position newPosition.
	 * @param
	 * @return
	 */
	@Override
	public boolean isPositionInsideLawn(Position pNewPosition, GrassInfo pGrassInfo) {
		if (pNewPosition == null || pGrassInfo == null)
			return false;
		else {
			return pNewPosition.getX() > pGrassInfo.getWidth() || pNewPosition.getX() < 0
					|| pNewPosition.getY() > pGrassInfo.getLength() || pNewPosition.getX() < 0 ? false : true;
		}
	 
	}

	/**
	 * Allows to advance the mower
	 * 
	 * @param pMower
	 * @param pdirection
	 * @param pGrassInfo
	 * @throws MowerException 
	 */
	@Override
	public Mower goAdvanced(Mower pMower, String pdirection, GrassInfo pGrassInfo) throws MowerException {
		if (pMower == null)
			throw new MowerException("Mower is null");
		if (pdirection == null)
			throw new MowerException("Direction is null");
		if (pGrassInfo == null)
			throw new MowerException("GrassInfo is null");
 
		int newX = pMower.getPosition().getX();
		int newY = pMower.getPosition().getY();
		Position newPosition = new Position();

		if (Direction.NORTH.equals(pdirection)) {
			newY = newY + 1;

		} else if (Direction.EAST.equals(pdirection)) {
			newX = newX + 1;
		} else if (Direction.SOUTH.equals(pdirection)) {
			newY = newY - 1;
		} else if (Direction.WEST.equals(pdirection)) {
			newX = newX - 1;
		} else {
			return pMower;
		}
		pMower.setDirection(pdirection);
		newPosition.setX(newX);
		newPosition.setY(newY);
		if (isPositionInsideLawn(newPosition, pGrassInfo)) {
			pMower.setPosition(newPosition);
		}
 
		return pMower;
	}

	/**
	 * Go the mower to the right
	 * 
	 * @param pMower
	 * @param pdirection
	 * @param pGrassInfo
	 * @throws MowerException 
	 */
	@Override
	public Mower goRight(Mower pMower, String pdirection, GrassInfo pGrassInfo) throws MowerException {

		if (pMower == null)
			throw new MowerException("Mower is null");
		if (pdirection == null)
			throw new MowerException("Direction is null");
		if (pGrassInfo == null)
			throw new MowerException("GrassInfo is null");

		String newDirection =pMower.getDirection();

		if (Direction.NORTH.equals(pdirection)) {
			newDirection=Direction.EAST;

		} else if (Direction.EAST.equals(pdirection)) {
			newDirection=Direction.SOUTH;
		} else if (Direction.SOUTH.equals(pdirection)) {
			newDirection=Direction.WEST;
		} else if (Direction.WEST.equals(pdirection)) {
			newDirection=Direction.NORTH;
		} else {
			return pMower;
		}


			pMower.setDirection(newDirection);

		return pMower;
	}

	/**
	 * Go the mower to the left
	 * 
	 * @param pMower
	 * @param pdirection
	 * @param pGrassInfo
	 * @throws MowerException 
	 */
	@Override
	public Mower goLeft(Mower pMower, String pdirection, GrassInfo pGrassInfo) throws MowerException {
		if (pMower == null)
			throw new MowerException("Mower is null");
		if (pdirection == null)
			throw new MowerException("Direction is null");
		if (pGrassInfo == null)
			throw new MowerException("GrassInfo is null");
		String newDirection =pMower.getDirection();
		if (Direction.NORTH.equals(pdirection)) {
			newDirection=Direction.WEST;
		} else if (Direction.EAST.equals(pdirection)) {
			newDirection=Direction.NORTH;
		} else if (Direction.SOUTH.equals(pdirection)) {
			newDirection=Direction.EAST;
		} else if (Direction.WEST.equals(pdirection)) {
			newDirection=Direction.SOUTH;
		} else {
			return pMower;
		}

			pMower.setDirection(newDirection);

		return pMower;
	}

	/**
	 * Execution of process
	 * 
	 * @param instructions
	 * @param pMower
	 * @param pdirection
	 * @param pGrassInfo
	 * @return
	 * @throws MowerException 
	 */
	@Override
	public MowerMovingItinerary runProcess(Mower pMower,GrassInfo pGrassInfo) throws MowerException {
		MowerMovingItinerary mowerMovingItinerary = new MowerMovingItinerary();
		mowerMovingItinerary.setRun(false);
	
		if (pMower == null || pMower.getDirection() == null || pMower.getInstructions() == null|| pMower.getInstructions().isEmpty() ||pGrassInfo==null) {
			return mowerMovingItinerary;
		} else {
			mowerMovingItinerary.setFirstDirection(pMower.getDirection());
			mowerMovingItinerary.setEndDirection(pMower.getDirection());
			ArrayList<Position> mowerMovingItineraryPosition =new ArrayList<Position> ();
			
			String pInstructions =pMower.getInstructions();
			char[] pInstructionsArray = pInstructions.toCharArray();
			boolean first =false ;
			String lDirection = pMower.getDirection();
			for (char pInstruction : pInstructionsArray) {
				if(!first ) {
					mowerMovingItinerary.setFirstPosition(pMower.getPosition());
					first =true;
				}
				
					if (Instruction.ADVANCED == pInstruction) {
						  
						  lDirection = goAdvanced(pMower, lDirection, pGrassInfo).getDirection();
						 if(pMower !=null && pMower.getPosition()!=null) {
							 mowerMovingItineraryPosition.add(pMower.getPosition());
							 mowerMovingItinerary.setRun(true);
						 }
					} else if (Instruction.RIGHT ==pInstruction) {
						 lDirection = goRight(pMower, lDirection, pGrassInfo).getDirection();
						 if(pMower !=null && pMower.getPosition()!=null) {
							 mowerMovingItineraryPosition.add(pMower.getPosition());
							 mowerMovingItinerary.setRun(true);
						 }
					} else {
						// Instruction.LEFT.equals(oneChar)
						 lDirection = goLeft(pMower, lDirection, pGrassInfo).getDirection();
						 if(pMower !=null && pMower.getPosition()!=null) {
							 mowerMovingItineraryPosition.add(pMower.getPosition());
							 mowerMovingItinerary.setRun(true);
						 }
					}
					    mowerMovingItinerary.setEndDirection(lDirection);
						mowerMovingItinerary.setEndPosition(pMower.getPosition());
 
			}
			
			
			mowerMovingItinerary.setMowerMovingItineraryPosition(mowerMovingItineraryPosition);
			return mowerMovingItinerary;
		}

	}

	@Override
	/**
	 * runAllProcess
	 */
	public ArrayList<MowerMovingItinerary> runAllProcess(ArrayList<Mower> pMowers, GrassInfo pGrassInfo) throws MowerException {
		if (pMowers == null ||pMowers.isEmpty()||pGrassInfo == null) {
			return null;

		} else {
			ArrayList<MowerMovingItinerary> mowersMovingItineraryList = new ArrayList<MowerMovingItinerary>();
			MowerMovingItinerary mowerMovingItinerary = null;
		
			for (Mower mower : pMowers) {
				mowerMovingItinerary = runProcess(mower,pGrassInfo);
				if(mowerMovingItinerary !=null){
					mowersMovingItineraryList.add(mowerMovingItinerary);
					
				}
 
			}
			return mowersMovingItineraryList;
		}
		
	}
	 
	@Override
	/**
	 * runAllProcess 
	 */
	public  ArrayList<MowerMovingItinerary> runAllProcess(ProcessView processView) throws MowerException {
		if (processView == null ||processView.getGrassInfo() ==null||processView.getMowersList()== null ||processView.getMowersList().isEmpty()) {
			return null;

		} else {
			ArrayList<MowerMovingItinerary> mowersMovingItineraryList = new ArrayList<MowerMovingItinerary>();
			mowersMovingItineraryList =runAllProcess(processView.getMowersList(),processView.getGrassInfo());
			return mowersMovingItineraryList;
		}
		
	}


}
