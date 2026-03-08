package org.example;

public record Message(String content) {

    public void printMessage() {
        System.out.println("Message: " + this.content);
    }
}
