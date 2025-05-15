package admintechinventory.Models;

public class Client {

    private int id;
    private Person person;

    public Client (int id, Person person) {
        this.id = id;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }
}
