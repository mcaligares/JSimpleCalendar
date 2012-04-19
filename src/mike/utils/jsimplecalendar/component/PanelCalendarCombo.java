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
    /**
     * Cambio la fecha del calendario
     * @param date Date nueva fecha del calendario
     */
    public void setDate(Date date) {
        //Creo un nuevo calendario
        Calendar calend = Calendar.getInstance();
        //cambio la fecha del calendario por la nueva fecha
        calend.setTime(date);
        
        //algunas veces cuando se usa un skin al cargar un valor en el combo por
        //item seleccionado (año) arroja un error de fuera de rango. Es poe eso
        //que se pone en un try catch, en caso de error no se hace nada.
        try{
            //cambio la seleccion del combo del mes y año por la nueva fecha
            monthCombo.setSelectedIndex(calend.get(Calendar.MONTH));
            yearCombo.setSelectedItem(Integer.valueOf(calend.get(Calendar.YEAR)));
        }
        catch(Exception ex) {}
//        int j = 0;
//        for(int i=1950;i<3000;i++){
//            if(i==calend.get(Calendar.YEAR))
//                break;
//            j++;
//        }
//        yearCombo.setSelectedIndex(j);
    }
    private ActionListener actionlistenerCalendar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Calendar calend = Calendar.getInstance();
                calend.setTime(calendar.getDateSelected());
                calend.set(Calendar.DAY_OF_MONTH, 1);
                JComboBox combo = (JComboBox) event.getSource();
                if(combo==monthCombo) {
                    calend.set(Calendar.MONTH, combo.getSelectedIndex());
                    calend.set(Calendar.YEAR, (Integer) yearCombo.getSelectedItem());
                }
                if(combo==yearCombo) {
                    calend.set(Calendar.YEAR, (Integer) combo.getSelectedItem());
                    calend.set(Calendar.MONTH, monthCombo.getSelectedIndex());
                }
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
