/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mike.utils.jsimplecalendar.component;

import java.awt.BorderLayout;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author mike
 */
public class JCalendarHeader extends PanelCalendar {
    private static final long serialVersionUID = 3066784166130227661L;
    private PanelCalendarHeader calendarHeader;
    
    public JCalendarHeader() {
        calendarHeader = new PanelCalendarHeader(this);
        super.add(calendarHeader,BorderLayout.PAGE_START);
    }

    @Override
    public void setDateSelected(Date dateSelected) {
        calendarHeader.setCalendarHeader(dateSelected);
        super.setDateSelected(dateSelected);
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        JCalendarHeader a = new JCalendarHeader();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(a);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
