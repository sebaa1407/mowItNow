package fr.xebia.mowitNow.tools;

import java.util.ArrayList;
import fr.xebia.mowitNow.constant.GrassInfo;
import fr.xebia.mowitNow.exception.MowerException;
import fr.xebia.mowitNow.model.Mower;
import fr.xebia.mowitNow.model.MowerMovingItinerary;
import fr.xebia.mowitNow.model.Position;
import fr.xebia.mowitNow.model.ProcessView;

public interface IMowerTools {
	public boolean isPositionInsideLawn(Position pNewPosition, GrassInfo pGrassInfo);

	public Mower goAdvanced(Mower pMower, String pdirection, GrassInfo pGrassInfo) throws MowerException;

	public Mower goRight(Mower pMower, String pdirection, GrassInfo pGrassInfo) throws MowerException;

	public Mower goLeft(Mower pMower, String pdirection, GrassInfo pGrassInfo) throws MowerException;

	public MowerMovingItinerary runProcess(Mower pMower,GrassInfo pGrassInfo) throws MowerException; 
	public ArrayList<MowerMovingItinerary> runAllProcess(ArrayList<Mower> pMowers,GrassInfo pGrassInfo) throws MowerException;
	public ArrayList<MowerMovingItinerary> runAllProcess(ProcessView processView) throws MowerException;

}
