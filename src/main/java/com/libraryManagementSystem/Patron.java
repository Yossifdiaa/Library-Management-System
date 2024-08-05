package com.libraryManagementSystem;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Patron {

    @Id
    @SequenceGenerator(
            name = "patron_id_sequence",
            sequenceName = "patron_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patron_id_sequence"
    )
    private Integer ID;
    private String name;
    private String contactInformation;

    public Patron(int ID, String contactInformation, String name) {
        this.ID = ID;
        this.contactInformation = contactInformation;
        this.name = name;
    }

    public Patron() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return ID == patron.ID && Objects.equals(name, patron.name) && Objects.equals(contactInformation, patron.contactInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, contactInformation);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                '}';
    }
}
