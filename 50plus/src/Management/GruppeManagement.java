package Management;

import Data.Gruppe_Serialisierung;
import Management.GruppeClass;

import java.io.Serializable;
import java.util.ArrayList;


public class GruppeManagement{

	Gruppe_Serialisierung group = new Gruppe_Serialisierung();
 

    public void addGruppe(GruppeClass gr) 
    {
    	group.speichereGruppe(gr);
    }

    public ArrayList<GruppeClass> getOwnGruppelist(String username) 
    {
    	return group.getOwnGruppenlist(username);
    } 
    
}

