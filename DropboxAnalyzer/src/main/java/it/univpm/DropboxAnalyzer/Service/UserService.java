package it.univpm.DropboxAnalyzer.Service;

import java.util.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Model.*;

@Service
public interface UserService {

	public Vector<User> getUserList(JSONObject jsonObj);
}
