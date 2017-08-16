package fr.xebia.mowitNow.model;

import java.util.ArrayList;

import fr.xebia.mowitNow.constant.GrassInfo;

public class ProcessView {
	private GrassInfo grassInfo;
	private ArrayList<Mower>  mowersList;
	public GrassInfo getGrassInfo() {
		return grassInfo;
	}
	public void setGrassInfo(GrassInfo grassInfo) {
		this.grassInfo = grassInfo;
	}
	public ArrayList<Mower> getMowersList() {
		return mowersList;
	}
	public void setMowersList(ArrayList<Mower> mowersList) {
		this.mowersList = mowersList;
	}
}
