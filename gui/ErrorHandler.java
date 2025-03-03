package game.gui;

public class ErrorHandler {
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    private boolean error;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public ErrorHandler(boolean error, String errorMessage) {
        this.error = error;
        this.errorMessage = errorMessage;
    }


}
