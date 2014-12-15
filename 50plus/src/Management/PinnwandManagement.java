package Management;

import java.util.ArrayList;

import Data.Post_Serialisierung;
import Personen.Post;

public class PinnwandManagement {

	Post_Serialisierung ps = new Post_Serialisierung();
 

    public void addPost(Post p) 
    {
    	ps.speicherePost(p);
    }

    public ArrayList<Post> getOwnpostlist(String username) 
    {
    	return ps.getOwnpostlist(username);
    } 
    

    /*
    public int delete(String username) {
        if (ser.loeschePerson(ser.getPersonbyid(username)) == 0) {
            return 0; //username existiert bereits
        } else {
            return 1; //Hat geklappt
        }
    }
    */

}
