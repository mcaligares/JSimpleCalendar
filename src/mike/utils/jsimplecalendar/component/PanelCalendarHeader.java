package mike.utils.jsimplecalendar.component;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import mike.utils.DateUtil;
import mike.utils.jsimplecalendar.event.DateChangedEvent;
import mike.utils.jsimplecalendar.event.DateChangedListener;

/**
 *
 * @author mike
 */
public class PanelCalendarHeader extends JPanel{
    private static final long serialVersionUID = -4588111893984685254L;
    private Font fontCalendarHeader;
    
    private JButton nextMonth;
    private JButton prevMonth;
    private JButton nextYear;
    private JButton prevYear;
    private JLabel cabecera;
    
    private PanelCalendar jcalendar;
    
    public PanelCalendarHeader(PanelCalendar calendar) {
        this.jcalendar = calendar;
//        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.drawHead();
        this.add(prevYear);
        this.add(prevMonth);
        this.add(cabecera);
        this.add(nextMonth);
        this.add(nextYear);
    }
    public void setCalendarHeader(Date date) {
        cabecera.setText(new DateUtil().formatoFullMesAnio(date));
    }
    public ActionListener actionMonthYearChanged() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Calendar calend = (Calendar) jcalendar.calendar.clone();
                JButton buttonSelected = (JButton) event.getSource();
                int buttonType = -1;
                if(buttonSelected == nextMonth) {
                    calend.add(Calendar.MONTH, 1);
                    buttonType = 0;
                }
                if(buttonSelected == nextYear) {
                    calend.add(Calendar.YEAR, 1);
                    buttonType = 1;
                }
                if(buttonSelected==prevMonth) {
                    calend.add(Calendar.MONTH, -1);
                    buttonType = 2;
                }
                if(buttonSelected==prevYear) {
                    calend.add(Calendar.YEAR, -1);
                    buttonType = 3;
                }
                jcalendar.fireDateChange(buttonType);
                cabecera.setText(new DateUtil().formatoFullMesAnio(calend.getTime()));
                jcalendar.setCalendar(calend,jcalendar.days);
            }
        };
    }

    private void drawHead() {
        Dimension dimension1 = new Dimension(17, 20);
        Dimension dimension2 = new Dimension(30, 20);
        Insets inset = new Insets(0, 0, 0, 0);
        nextMonth = new JButton(">");
        nextMonth.setFont(fontCalendarHeader);
        nextMonth.setMargin(inset);
        nextMonth.setPreferredSize(dimension1);
        nextMonth.addActionListener(actionMonthYearChanged());
        
        prevMonth = new JButton("<");
        prevMonth.setFont(fontCalendarHeader);
        prevMonth.setMargin(inset);
        prevMonth.setPreferredSize(dimension1);
        prevMonth.addActionListener(actionMonthYearChanged());
        
        nextYear = new JButton(">>");
        nextYear.setFont(fontCalendarHeader);
        nextYear.setMargin(inset);
        nextYear.setPreferredSize(dimension2);
        nextYear.addActionListener(actionMonthYearChanged());
        
        prevYear = new JButton("<<");
        prevYear.setFont(fontCalendarHeader);
        prevYear.setMargin(inset);
        prevYear.setPreferredSize(dimension2);
        prevYear.addActionListener(actionMonthYearChanged());
        
        cabecera = new JLabel();
        cabecera.setMaximumSize(new Dimension(140, 20));
        cabecera.setHorizontalAlignment(SwingConstants.CENTER);
        cabecera.setText(new DateUtil().formatoFullMesAnio(jcalendar.calendar.getTime()));
    }
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("sadsda");
        PanelCalendarHeader a = new PanelCalendarHeader(new PanelCalendar());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(a);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
