package it.univpm.DropboxAnalyzer.Controller;

import java.io.InputStream;
import java.net.MalformedURLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univpm.DropboxAnalyzer.Model.Content;

import org.json.simple.JSONObject; 

@Controller
public class ContentController {
	//"list-folder API call
	@GetMapping("/list")
	public @ResponseBody String POSTListFolder() throws MalformedURLException
	{
		JSONObject jsonFileList = HTTPRequests.ListFolderRequest();
		//TODO: Proper convert from json to list of files. (Now only raw conversion from json to string).
		return jsonFileList.toJSONString();
	}
	
	
}
