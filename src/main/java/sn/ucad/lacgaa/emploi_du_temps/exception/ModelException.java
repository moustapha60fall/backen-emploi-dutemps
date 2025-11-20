package sn.ucad.lacgaa.emploi_du_temps.exception;

import lombok.Getter;

@Getter
public class ModelException extends RuntimeException {
    private final String errorCode;

    public ModelException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}

