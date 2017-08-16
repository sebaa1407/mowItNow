package fr.xebia.mowitNow.unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import fr.xebia.mowitNow.constant.GrassInfo;
import fr.xebia.mowitNow.exception.MowerException;
import fr.xebia.mowitNow.model.Mower;
import fr.xebia.mowitNow.model.MowerMovingItinerary;
import fr.xebia.mowitNow.model.ProcessView;
import fr.xebia.mowitNow.tools.FileTools;
import fr.xebia.mowitNow.tools.MowerTools;

/**
 * 
 * @author sebaa
 *
 */
public class MowerTest {

	GrassInfo grassInfo;
	MowerTools mowerTools = new MowerTools();
	FileTools fileTools = new FileTools();

	@Before
	public void SetUp() throws Exception {
		grassInfo = new GrassInfo(5, 5);
	}

	@Test
	public void initializationgrassInfo() {
		assertEquals(5, grassInfo.getWidth());
		assertEquals(5, grassInfo.getLength());

	}

	@Test
	public void initializationMowerN() throws Exception {

		Mower mowerN = new Mower(1, 2, "N", "GAGAGAGAA");
		assertEquals(1, mowerN.getPosition().getX());
		assertEquals(2, mowerN.getPosition().getY());
		assertEquals("N", mowerN.getDirection());
	}

	@Test
	public void initializationMowerE() throws Exception {

		Mower mowerE = new Mower(3, 3, "E", "GAGAGAGAA");
		assertEquals(3, mowerE.getPosition().getX());
		assertEquals(3, mowerE.getPosition().getY());
		assertEquals("E", mowerE.getDirection());
	}

	@Test
	public void initializationMowerS() throws Exception {

		Mower mowerS = new Mower(3, 4, "S", "GAGAGAGAA");
		assertEquals(3, mowerS.getPosition().getX());
		assertEquals(4, mowerS.getPosition().getY());
		assertEquals("S", mowerS.getDirection());
	}

	@Test
	public void initializationMowerW() throws Exception {

		Mower mowerS = new Mower(1, 5, "W", "GAGAGAGAA");
		assertEquals(1, mowerS.getPosition().getX());
		assertEquals(5, mowerS.getPosition().getY());
		assertEquals("W", mowerS.getDirection());
	}

	@Test
	public void runMowerN() {
		Mower mowerN = new Mower(1, 2, "N", "GAGAGAGAA");
		MowerMovingItinerary mowerMovingItinerary = new MowerMovingItinerary();
		GrassInfo grass = new GrassInfo(5, 5);
		try {

			mowerMovingItinerary = mowerTools.runProcess(mowerN, grass);

		} catch (MowerException e) {
			fail();
		}
		if (mowerMovingItinerary != null) {
			assertEquals(1, mowerMovingItinerary.getFirstPosition().getX());
			assertEquals(2, mowerMovingItinerary.getFirstPosition().getY());
			assertEquals("N", mowerMovingItinerary.getFirstDirection());

			assertEquals(1, mowerMovingItinerary.getEndPosition().getX());
			assertEquals(3, mowerMovingItinerary.getEndPosition().getY());
			assertEquals("N", mowerMovingItinerary.getEndDirection());
		}
	}

	@Test
	public void runMowerE() {
		Mower mowerE = new Mower(3, 3, "E", "AADAADADDA");
		MowerMovingItinerary mowerMovingItinerary = new MowerMovingItinerary();
		GrassInfo grass = new GrassInfo(5, 5);
		try {

			mowerMovingItinerary = mowerTools.runProcess(mowerE, grass);

		} catch (MowerException e) {
			fail();
		}
		if (mowerMovingItinerary != null) {
			assertEquals(3, mowerMovingItinerary.getFirstPosition().getX());
			assertEquals(3, mowerMovingItinerary.getFirstPosition().getY());
			assertEquals("E", mowerMovingItinerary.getFirstDirection());

			assertEquals(5, mowerMovingItinerary.getEndPosition().getX());
			assertEquals(1, mowerMovingItinerary.getEndPosition().getY());
			assertEquals("E", mowerMovingItinerary.getEndDirection());
		}
	}

	@Test
	public void runAllMowers() {
		Mower mowerE = new Mower(3, 3, "E", "AADAADADDA");
		Mower mowerN = new Mower(1, 2, "N", "GAGAGAGAA");
		GrassInfo grass = new GrassInfo(5, 5);
		ArrayList<MowerMovingItinerary> mowerMovingItineraryList = new ArrayList<MowerMovingItinerary>();
		ProcessView processView = new ProcessView();
		ArrayList<Mower> mowersList = new ArrayList<Mower>();
		mowersList.add(mowerN);
		mowersList.add(mowerE);
		processView.setGrassInfo(grass);
		processView.setMowersList(mowersList);
		try {

			mowerMovingItineraryList = mowerTools.runAllProcess(processView);

		} catch (MowerException e) {
			fail();
		}
		MowerMovingItinerary mowerMovingItineraryN = new MowerMovingItinerary();
		MowerMovingItinerary mowerMovingItineraryE = new MowerMovingItinerary();
		if (mowerMovingItineraryList != null && mowerMovingItineraryList.size() > 1) {
			mowerMovingItineraryN = mowerMovingItineraryList.get(0);
			mowerMovingItineraryE = mowerMovingItineraryList.get(1);
			if (mowerMovingItineraryN != null)
				assertEquals(1, mowerMovingItineraryN.getFirstPosition().getX());
			assertEquals(2, mowerMovingItineraryN.getFirstPosition().getY());
			assertEquals("N", mowerMovingItineraryN.getFirstDirection());
			assertEquals(1, mowerMovingItineraryN.getEndPosition().getX());
			assertEquals(3, mowerMovingItineraryN.getEndPosition().getY());
			assertEquals("N", mowerMovingItineraryN.getEndDirection());
		}
		if (mowerMovingItineraryE != null) {
			assertEquals(3, mowerMovingItineraryE.getFirstPosition().getX());
			assertEquals(3, mowerMovingItineraryE.getFirstPosition().getY());
			assertEquals("E", mowerMovingItineraryE.getFirstDirection());
			assertEquals(5, mowerMovingItineraryE.getEndPosition().getX());
			assertEquals(1, mowerMovingItineraryE.getEndPosition().getY());
			assertEquals("E", mowerMovingItineraryE.getEndDirection());

		}
	}

	@Test
	public void wrapMowersFromFileTest() {
		String path = "upload-test/test.txt";
		ProcessView var = fileTools.wrapMowersFromFile(path);
		ArrayList<MowerMovingItinerary> mowerMovingItineraryList = new ArrayList<MowerMovingItinerary>();
		try {

			mowerMovingItineraryList = mowerTools.runAllProcess(var);

		} catch (MowerException e) {
			fail();
		}

		MowerMovingItinerary mowerMovingItineraryN = new MowerMovingItinerary();
		MowerMovingItinerary mowerMovingItineraryE = new MowerMovingItinerary();
		if (mowerMovingItineraryList != null && mowerMovingItineraryList.size() > 1) {
			mowerMovingItineraryN = mowerMovingItineraryList.get(0);
			mowerMovingItineraryE = mowerMovingItineraryList.get(1);
			if (mowerMovingItineraryN != null)
				assertEquals(1, mowerMovingItineraryN.getFirstPosition().getX());
			assertEquals(2, mowerMovingItineraryN.getFirstPosition().getY());
			assertEquals("N", mowerMovingItineraryN.getFirstDirection());
			assertEquals(1, mowerMovingItineraryN.getEndPosition().getX());
			assertEquals(3, mowerMovingItineraryN.getEndPosition().getY());
			assertEquals("N", mowerMovingItineraryN.getEndDirection());
		}
		if (mowerMovingItineraryE != null) {
			assertEquals(3, mowerMovingItineraryE.getFirstPosition().getX());
			assertEquals(3, mowerMovingItineraryE.getFirstPosition().getY());
			assertEquals("E", mowerMovingItineraryE.getFirstDirection());
			assertEquals(5, mowerMovingItineraryE.getEndPosition().getX());
			assertEquals(1, mowerMovingItineraryE.getEndPosition().getY());
			assertEquals("E", mowerMovingItineraryE.getEndDirection());

		}
	}

}