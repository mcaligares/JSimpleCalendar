/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mike.utils.jsimplecalendar.exception;

/**
 *
 * @author mike
 */
public class CalendarFormatException extends Exception{
    private static final long serialVersionUID = 2877938010807950057L;

    public CalendarFormatException(Throwable cause) {
        super(cause);
    }

    public CalendarFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalendarFormatException(String message) {
        super(message);
    }

    public CalendarFormatException() {
    }
    
}
