/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mike.utils.jsimplecalendar.event;

import java.util.EventListener;

/**
 *
 * @author mike
 */
public interface SelectionChangedListener extends EventListener{
    public void onSelectionChanged(SelectionChangedEvent event);
}
