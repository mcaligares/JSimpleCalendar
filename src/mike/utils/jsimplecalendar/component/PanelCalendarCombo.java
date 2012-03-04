package mike.utils.jsimplecalendar.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author mike
 */
public class PanelCalendarCombo extends JPanel{
    private static final long serialVersionUID = 2874934376886520268L;
    private JComboBox monthCombo;
    private JComboBox yearCombo;
    private PanelCalendar calendar;
    private String[] months = new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

    public PanelCalendarCombo(PanelCalendar calendar) {
        java.awt.Font font = new java.awt.Font("Dialog", 0, 11);
        
        this.calendar = calendar;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        monthCombo = new JComboBox(months);
        monthCombo.addActionListener(actionlistenerCalendar());
        monthCombo.setFont(font);
        
        yearCombo = new JComboBox();
        for(int i=1950; i<=3000;i++)
            yearCombo.addItem(i);
        yearCombo.addActionListener(actionlistenerCalendar());
        yearCombo.setFont(font);
        
        JLabel month = new JLabel(" Mes: ");
        month.setFont(font);
        JLabel year = new JLabel(" Año: ");
        year.setFont(font);
        // Agrego los componentes
        this.add(month);
        this.add(monthCombo);
        this.add(year);
        this.add(yearCombo);
    }
    public void setDate(Date date) {
        Calendar calend = Calendar.getInstance();
        calend.setTime(date);
        monthCombo.setSelectedIndex(calend.get(Calendar.MONTH));
        yearCombo.setSelectedItem(calend.get(Calendar.YEAR));
    }
    private ActionListener actionlistenerCalendar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Calendar calend = Calendar.getInstance();
                calend.setTime(calendar.getDateSelected());
                calend.set(Calendar.DAY_OF_MONTH, 1);
                JComboBox combo = (JComboBox) event.getSource();
                if(combo==monthCombo)
                    calend.set(Calendar.MONTH, combo.getSelectedIndex());
                if(combo==yearCombo)
                    calend.set(Calendar.YEAR, (Integer) combo.getSelectedItem());
                calendar.setCalendar(calend);
            }
        };
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("JSimpleCalendarField");
        PanelCalendar a = new PanelCalendar();
        PanelCalendarCombo s = new PanelCalendarCombo(a);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(s);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
}
