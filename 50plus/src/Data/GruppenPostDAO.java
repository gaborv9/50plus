package Data;
import java.util.ArrayList;

import Personen.GruppePost;

public interface GruppenPostDAO{
	public void speichereGruppenPost(GruppePost p);
	public ArrayList<GruppePost> getAlleGruppen();
	public ArrayList<GruppePost> getPostsbyGroupname(String gruppenname);
}
