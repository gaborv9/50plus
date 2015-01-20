package Data;
import java.util.ArrayList;

import Management.GruppeClass;

public interface GruppeDAO{

	public void speichereGruppe(GruppeClass gr);
	public ArrayList<GruppeClass> getGruppenlist();
	public ArrayList<GruppeClass> getGruppenbyAdmin(String username);
	public GruppeClass getGruppebyName(String gn);
	public void loescheGruppe(GruppeClass gr);
	public ArrayList<GruppeClass> getOwnGruppenlist(String username);
	
}
