package org.example;

public class Message {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void printMessage() {
        System.out.println("Message: " + this.content);
    }
}
