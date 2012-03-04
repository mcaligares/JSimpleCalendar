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
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
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
    private ActionListener actionMonthYear() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Calendar calend = (Calendar) jcalendar.calendar.clone();
                JButton button = (JButton) event.getSource();
                if(button == nextMonth)
                    calend.add(Calendar.MONTH, 1);
                if(button == nextYear)
                    calend.add(Calendar.YEAR, 1);
                if(button==prevMonth)
                    calend.add(Calendar.MONTH, -1);
                if(button==prevYear)
                    calend.add(Calendar.YEAR, -1);
                cabecera.setText(new DateUtil().formatoFullMesAnio(calend.getTime()));
                jcalendar.setCalendar(calend);
            }
        };
    }

    private void drawHead() {
        Dimension dimension = new Dimension(15, 20);
        Insets inset = new Insets(0, 0, 0, 0);
        nextMonth = new JButton(">");
        nextMonth.setFont(fontCalendarHeader);
        nextMonth.setMargin(inset);
        nextMonth.setPreferredSize(dimension);
        nextMonth.addActionListener(actionMonthYear());
        
        prevMonth = new JButton("<");
        prevMonth.setFont(fontCalendarHeader);
        prevMonth.setMargin(inset);
        prevMonth.setPreferredSize(dimension);
        prevMonth.addActionListener(actionMonthYear());
        
        nextYear = new JButton(">>");
        nextYear.setFont(fontCalendarHeader);
        nextYear.setMargin(inset);
        nextYear.setPreferredSize(dimension);
        nextYear.addActionListener(actionMonthYear());
        
        prevYear = new JButton("<<");
        prevYear.setFont(fontCalendarHeader);
        prevYear.setMargin(inset);
        prevYear.setPreferredSize(dimension);
        prevYear.addActionListener(actionMonthYear());
        
        cabecera = new JLabel();
        cabecera.setMaximumSize(new Dimension(140, 20));
        cabecera.setHorizontalAlignment(SwingConstants.CENTER);
        cabecera.setText(new DateUtil().formatoFullMesAnio(jcalendar.calendar.getTime()));
    }
}
