package com.mycompany.samplehospital.WebSocket;

import java.util.Date;

public class ChatMessage {
//model class for chat
    //contains the property message sender image if present and the time when it has been sent

    private String message;
    private String sender;
    private String image;
    private Date received;

    public final String getMessage() {
        return message;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }

    public final String getSender() {
        return sender;
    }

    public final void setSender(final String sender) {
        this.sender = sender;
    }

    public final Date getReceived() {
        return received;
    }

    public final void setReceived(final Date received) {
        this.received = received;
    }

    @Override
    public String toString() {
        return "ChatMessage [message=" + message + ", sender=" + sender
                + ", received=" + received + "]";
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

}
