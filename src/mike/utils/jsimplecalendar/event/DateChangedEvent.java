package mike.utils.jsimplecalendar.event;

import java.util.EventObject;

/**
 * DateChangedEvent.java
 * Created on 02/04/2012, 16:31:46
 * 
 * @author Miguel Augusto Caligares
 * @email mcaligares@gmail.com
 */
public class DateChangedEvent extends EventObject{
    private static final long serialVersionUID = 9137793808508697820L;

    public DateChangedEvent(Object source) {
        super(source);
    }
    
}
