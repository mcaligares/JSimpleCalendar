/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mike.utils.jsimplecalendar.event;

import java.util.EventObject;

/**
 *
 * @author mike
 */
public class SelectionChangedEvent extends EventObject{
    private static final long serialVersionUID = -2780368524800990616L;

    public SelectionChangedEvent(Object source) {
        super(source);
    }
    
}
