package fr.xebia.mowitNow.tools;

import java.util.List;

import fr.xebia.mowitNow.model.ProcessView;

public interface IFileTools {
	public ProcessView wrapMowersFromFile(String path);
	public List<String> fileToLines(String file) ;
}
