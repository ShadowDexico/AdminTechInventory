package admintechinventory.Models;

public class Person {

    private String name;
    private String lastName;
    private String typeDni;
    private String dni;
    private String address;
    private String telephone;
    private String mail;

    public Person(String name, String lastName, String typeDni, String dni, String address, String telephone, String mail) {
        this.name = name;
        this.lastName = lastName;
        this.typeDni = typeDni;
        this.dni = dni;
        this.address = address;
        this.telephone = telephone;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTypeDni() {
        return typeDni;
    }

    public String getDni() {
        return dni;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

}
