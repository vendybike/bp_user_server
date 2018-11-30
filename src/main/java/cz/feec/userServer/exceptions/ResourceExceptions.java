/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.feec.userServer.exceptions;

/**
 *
 * @author vendy
 */
public class ResourceExceptions extends Exception {

    public ResourceExceptions() {
    }

    public ResourceExceptions(String message) {
        super(message);
    }

    public ResourceExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceExceptions(Throwable cause) {
        super(cause);
    }

    public ResourceExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static class ResourceAlreadyExistException extends ResourceExceptions {

        public ResourceAlreadyExistException() {
        }

        public ResourceAlreadyExistException(String message) {
            super(message);
        }

        public ResourceAlreadyExistException(String message, Throwable cause) {
            super(message, cause);
        }

        public ResourceAlreadyExistException(Throwable cause) {
            super(cause);
        }

        public ResourceAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

    }

    public static class ResourceNotFoundException extends ResourceExceptions {

        public ResourceNotFoundException() {
        }

        public ResourceNotFoundException(String message) {
            super(message);
        }

        public ResourceNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public ResourceNotFoundException(Throwable cause) {
            super(cause);
        }

        public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

    }
    
    public static class BadResourceExeption extends ResourceExceptions{

        public BadResourceExeption() {
        }

        public BadResourceExeption(String message) {
            super(message);
        }

        public BadResourceExeption(String message, Throwable cause) {
            super(message, cause);
        }

        public BadResourceExeption(Throwable cause) {
            super(cause);
        }

        public BadResourceExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
        
    }
}
