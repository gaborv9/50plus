package Management;

import java.util.ArrayList;

import Personen.Post;

/**PinnwandDAO ist ein Interface und stellt Methoden zur Verwaltung von Posts zur Verfuegung.
 * 
 * @author master
 *
 */

public interface PinnwandDAO 
{
	public void speicherePost(Post p, int postNumber);
	public ArrayList<Post> getOwnpostlist(String username);
	public void loeschePost(int postNumber);
    public ArrayList<Integer> getPostNumbers(String username);
    public Post getPostbyNumber (int postNumber);
    public void changeFlag(boolean flag, int postNumber);
    public  ArrayList<Post> getFlaggedPostlist();
    public void loescheAllPosts(String username);
	
	
	
	
	
}
