package Management;

import Data.Serialisierung;
import Personen.Person;

public class PersonManagement {

    Serialisierung ser = new Serialisierung();

    //1 2 oder 3..

    public void setRole(String username, int role) {
        ser.getPersonbyid(username).setRole(role);
    }
/*
    public int add(Person a) {
        if (ser.speicherePerson(a) == 0) {
            return 0; //username existiert bereits
        } else {
            return 1; //Hat geklappt
        }
    }*/

    public int delete(String username) {
        if (ser.loeschePerson(ser.getPersonbyid(username)) == 0) {
            return 0; //username existiert bereits
        } else {
            return 1; //Hat geklappt
        }
    }
    

}
