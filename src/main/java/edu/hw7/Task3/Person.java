package edu.hw7.Task3;

public record Person(int id, String name, String address, String phoneNumber) {

    public boolean isValid() {
        return name != null && address != null && phoneNumber != null;
    }

}
