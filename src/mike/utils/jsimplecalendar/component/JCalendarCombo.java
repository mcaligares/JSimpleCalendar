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
public class JCalendarCombo extends PanelCalendar{
    private static final long serialVersionUID = -5161505885146313488L;
    private PanelCalendarCombo panelCombo;
    
    public JCalendarCombo() {
        panelCombo = new PanelCalendarCombo(this);
        super.add(panelCombo,BorderLayout.PAGE_START);
        panelCombo.setDate(calendar.getTime());
    }

    @Override
    public void setDateSelected(Date dateSelected) {
        panelCombo.setDate(dateSelected);
        super.setDateSelected(dateSelected);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("sadsda");
        JCalendarCombo a = new JCalendarCombo();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(a);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
