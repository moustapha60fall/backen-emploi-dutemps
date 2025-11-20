package sn.ucad.lacgaa.emploi_du_temps.dto.response;

public class UploadResponse {

    private String message;
    private String fileName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
