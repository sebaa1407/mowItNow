package fr.xebia.mowitNow.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import fr.xebia.mowitNow.constant.GrassInfo;
import fr.xebia.mowitNow.model.Mower;
import fr.xebia.mowitNow.model.ProcessView;
import fr.xebia.mowitNow.model.Position;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author sebaa
 *
 */
public class FileTools  implements IFileTools{
	// String pathFichier="D:\\fichier.txt";
	/**
	 * get Mower from file
	 * 
	 * @param path
	 *            :path file
	 */
	@Override
	public ProcessView wrapMowersFromFile(String path) {
		ProcessView processView = new ProcessView();
		if(path== null ) return processView;
		List<String> fileToLines = fileToLines(path);
	
		ArrayList<Mower> mowersList = new ArrayList<Mower>();
		int first = 0;
		GrassInfo grassInfo =new GrassInfo();
		if (!fileToLines.isEmpty()) {
			Mower mower =null;
			boolean isNewMawer =true;
			for (String line : fileToLines) {
		
				if (line != null) {

					String pLine = line.trim();
					String[] elements = pLine.split(" ");
					// GrassInfo
					// The maximum ordinate of the ground
					// Maximum abscissa of the ground

					if (first == 0) {
						if(elements.length==2) {
							String widthElement =elements[0].trim();
							String lengthElement =elements[1].trim();
							if (isStringInt(widthElement) && isStringInt(lengthElement)) {
									int width = Integer.parseInt(widthElement);
									int length = Integer.parseInt(lengthElement);
									grassInfo.setLength(length);
									grassInfo.setWidth(width);
							}
						}
					}else {
					
						if(isNewMawer) {
							mower =new Mower();
						}
						if(elements.length==3) {
							isNewMawer =false;
							String XpositionElement =elements[0].trim();
							String YpositionElement =elements[1].trim();
							String directionElement =elements[2].trim();
							if (isStringInt(XpositionElement) && isStringInt(YpositionElement)) {
								Position position =new Position();
								int Xposition = Integer.parseInt(XpositionElement);
								int Yposition = Integer.parseInt(YpositionElement);
								position.setX(Xposition);
								position.setY(Yposition);
								mower.setPosition(position);
								mower.setDirection(directionElement);
								 
					      	}
						}else {
							mower.setInstructions(pLine);
							isNewMawer =true;
						}
						if(mower !=null && mower.getInstructions() !=null && mower.getPosition()!=null ) {
							 mowersList.add(mower);
						}
					 
					}
					first++;
				

				}
			}
			processView.setGrassInfo(grassInfo);
			processView.setMowersList(mowersList);
		}
		return processView;
	}
	@Override
	public List<String> fileToLines(String file) {
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
		} catch (IOException e) {

		}

		return lines;
	}

	public boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
}
