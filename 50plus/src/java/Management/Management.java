package Management;

import Data.Serialisierung;
import Personen.Person;

public class Management {

    Serialisierung ser = new Serialisierung();

    //1 2 oder 3..

    public void setRole(Person objekt, int role) {
        objekt.setRole(role);
    }

    public int add(Person a) {
        if (ser.speicherePerson(a) == 0) {
            return 0; //username existiert bereits
        } else {
            return 1; //Hat geklappt
        }
    }

    public int delete(Person a) {
        if (ser.loeschePerson(a) == 0) {
            return 0; //username existiert bereits
        } else {
            return 1; //Hat geklappt
        }
    }
    

}
