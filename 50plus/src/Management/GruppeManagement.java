package Management;

import Data.Gruppe_Serialisierung;
import Management.GruppeClass;

public class GruppeManagement {

    Gruppe_Serialisierung ser = new Gruppe_Serialisierung();

    public int add(GruppeClass a) {
        if (ser.speichereGruppe(a) == 0) {
            return 0; //username existiert bereits
        } else {
            return 1; //gespeichert
        }
    }

    public int delete(String name) {
        if (ser.loescheGruppe(ser.getGruppebyName(name)) == 0) {
            return 0; //gruppe existiert nicht
        } else {
            return 1; //geloescht
        }
    }

}
