package Management;

import java.util.ArrayList;

import Data.Post_Serialisierung;
import Personen.Post;

public class PinnwandManagement 
{

	Post_Serialisierung ps = new Post_Serialisierung();
 

    public void addPost(Post p) 
    {
    	ps.speicherePost(p);
    }

    public ArrayList<Post> getOwnpostlist(String username) 
    {
    	return ps.getOwnpostlist(username);
    } 
    
    public void deletePost(int postNumber) 
    {
    	ps.loeschePost(postNumber);
    }
    public ArrayList<Integer> getPostNumbers(String username)
    {
    	return ps.getPostNumbers(username);
    } 
   
    /*
    public Post getPost(int postNumber) 
    {
    	return ps.getPost(postNumber);
    
    }    
    */

}
