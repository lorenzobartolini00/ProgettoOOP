package it.univpm.DropboxAnalyzer.Service;

import java.util.*;
import org.json.simple.JSONObject;

import it.univpm.DropboxAnalyzer.Model.*;

public interface UserService {

	public Vector<User> getListUsers(JSONObject jsonObj);
}
