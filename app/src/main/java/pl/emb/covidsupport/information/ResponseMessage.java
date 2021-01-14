package pl.emb.covidsupport.information;

public class ResponseMessage {

    private String textMessage;             // text which we show
    boolean isUser;                         // variable to check if it is user's or bot's message

    // constructor with parameters
    public ResponseMessage(String textMessage, boolean isUser) {
        this.textMessage = textMessage;
        this.isUser = isUser;
    }

    // getters and setter
    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}
