package mike.utils.jsimplecalendar.component;

import java.awt.Dimension;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author mike
 */
public class PanelCalendarSpinner extends JPanel{
    private static final long serialVersionUID = -5161505885146313488L;
    private JSpinner monthSpinner;
    private JSpinner yearSpinner;
    private PanelCalendar calendar;
    private String[] months = new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
    
    public PanelCalendarSpinner(PanelCalendar calendar) {
        Dimension dimensionMonth = new Dimension(50, 20);
        Dimension dimensionMonthS = new Dimension(100, 20);
        Dimension dimensionYear = new Dimension(60, 20);
        
        this.calendar = calendar;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // Spiner mes
        monthSpinner = new JSpinner(new SpinnerListModel(months));
        monthSpinner.addChangeListener(changeListener());
        monthSpinner.setMaximumSize(dimensionMonthS);
        // Spinner año
        yearSpinner = new JSpinner(new SpinnerNumberModel(calendar.calendar.get(Calendar.YEAR), 1950, 3000, 1));
        yearSpinner.addChangeListener(changeListener());
        yearSpinner.setMaximumSize(dimensionYear);
        // Label mes
        JLabel month = new JLabel("Mes: ");
        month.setHorizontalAlignment(SwingConstants.CENTER); // SwingConstants.CENTER = 0
        month.setMaximumSize(dimensionMonth);
        // Label año
        JLabel year = new JLabel("Año: ");
        year.setHorizontalAlignment(SwingConstants.CENTER); // SwingConstants.CENTER = 0
        year.setMaximumSize(dimensionYear);
        // Agrego los componentes
        this.add(month);
        this.add(monthSpinner);
        this.add(year);
        this.add(yearSpinner);
    }
    
    private ChangeListener changeListener() {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                // Obtengo el spinner que se selecciono
                JSpinner spinner = (JSpinner) event.getSource();
                // Obtengo la fecha del calendar y pungo el 1er dia del mes
                Calendar calend = Calendar.getInstance();
                calend.setTime(calendar.getDateSelected());
                calend.set(Calendar.DAY_OF_MONTH, 1);
                // Segun el mes o el año modifico la fecha y la cambio en el calendar.
                if(spinner==monthSpinner)
                    calend.set(Calendar.MONTH, getMonth(monthSpinner.getValue()));
                if(spinner==yearSpinner)
                    calend.set(Calendar.YEAR, Integer.valueOf(yearSpinner.getValue().toString()));
                calendar.setCalendar(calend);
            }
        };
    }
    private int getMonth(Object obj) {
        // Recorro los meses y devuelvo su posicion 
        // Enero=0; 
        // Febrero=1;
        // Marzo=2; ...
        for(int i=0; i<months.length;i++)
            if(obj.equals(months[i]))
                return i;
        return 0;
    }
}
