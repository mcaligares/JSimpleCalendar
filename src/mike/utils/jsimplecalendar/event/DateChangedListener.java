package mike.utils.jsimplecalendar.event;

import java.util.EventListener;

/**
 * DateChangedListener.java
 * Created on 02/04/2012, 16:31:59
 * 
 * @author Miguel Augusto Caligares
 * @email mcaligares@gmail.com
 */
public interface DateChangedListener extends EventListener{
    public void nextMonthChanged(DateChangedEvent event);
    public void prevMonthChanged(DateChangedEvent event);
    public void nextYearChanged(DateChangedEvent event);
    public void prevYearChanged(DateChangedEvent event);
}
