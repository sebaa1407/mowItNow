package fr.xebia.mowitNow.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import fr.xebia.mowitNow.exception.MowerException;
import fr.xebia.mowitNow.model.MowerMovingItinerary;
import fr.xebia.mowitNow.model.ProcessView;
import fr.xebia.mowitNow.storage.StorageService;
import fr.xebia.mowitNow.tools.FileTools;
import fr.xebia.mowitNow.tools.MowerTools;


/**
 * 
 * @author sebaa
 *
 */

@Controller
public class FileUploadController {

	private static final String UPLOAD_DIR = "upload-dir/";

	private static final String GRASS_INFO = "grassInfo";

	private static final String UPLOAD_FORM = "uploadForm";

	private static final String FAIL_UPLOAD = "FAIL to upload ";

	private static final String SUCCESSFULLY_UPLOADED = "You successfully uploaded ";

	private static final String MESSAGE = "message";

	private static final String FILE = "file";

	private static final String RESULT = "mowersMovingItineraryList";

	@Autowired
	StorageService storageService;
	MowerTools mowerTools = new MowerTools();
	FileTools fileTools = new FileTools();;

	List<String> files = new ArrayList<String>();

	@GetMapping("/")
	public String listUploadedFiles(Model model) {
		return UPLOAD_FORM;
	}

	@PostMapping("/uploadForm")
	public String runMowerAllProcess(@RequestParam(FILE) MultipartFile file, Model model) {
		try {
			storageService.store(file);
			model.addAttribute(MESSAGE, SUCCESSFULLY_UPLOADED + file.getOriginalFilename() + "");
			files.add(file.getOriginalFilename());
			String path =UPLOAD_DIR+file.getOriginalFilename();
		    Path rootLocation = Paths.get(path);
			ProcessView process = fileTools.wrapMowersFromFile(rootLocation.toString());
			ArrayList<MowerMovingItinerary> mowersMovingItineraryList = mowerTools.runAllProcess(process);
			model.addAttribute(RESULT, mowersMovingItineraryList);
			model.addAttribute(GRASS_INFO,process.getGrassInfo());
		} catch (Exception e) {
			model.addAttribute(MESSAGE, FAIL_UPLOAD + file.getOriginalFilename() + "! If the file exist rename the file(.txt)");
		}
		return UPLOAD_FORM;
	}

 
}