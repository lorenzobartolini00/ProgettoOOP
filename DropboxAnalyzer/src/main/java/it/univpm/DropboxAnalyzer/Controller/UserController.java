package it.univpm.DropboxAnalyzer.Controller;

import java.net.MalformedURLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.DropboxAnalyzer.Model.User;
import it.univpm.DropboxAnalyzer.Service.HTTPSRequest;
import it.univpm.DropboxAnalyzer.Service.UserService;
import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.configuration.ListFileMembersBody;

public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private HTTPSRequest httpsReq;
	
	//list-file-member API call
	@GetMapping("/list_file_members")
	public String POSTGetListFileMembers(@RequestParam(name="token") String token) throws MalformedURLException{
		
		Configuration config = new Configuration("https://api.dropboxapi.com/2/sharing/list_file_members", new ListFileMembersBody("/Uni",10), "POST", token);
		Vector<User> users=userService.getUserList(httpsReq.rootCall(config));
		return users.get(1).getEmail();
		
	}
}
