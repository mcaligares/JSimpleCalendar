package mike.utils.jsimplecalendar.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import mike.utils.DateUtil;
import mike.utils.jsimplecalendar.exception.CalendarFormatException;

/**
 *
 * @author mike
 */
public class JCalendarHeaderField extends JPanel{
    private static final long serialVersionUID = 6169821566686676341L;
    // Properties name
    public static final String PROPERTY_FIELD_NAME = "fieldName";
    public static final String PROPERTY_NULLABLE = "nullable";
    // Properties
    private String fieldName;
    private boolean state;
    private boolean nullable;
    private Color color;
    private Date date;
    
    private JTextField txtCalendar;
    private JButton btnCalendar;
    private JCalendarHeader jcalendar;
    private JPopupMenu popup;
    
    public JCalendarHeaderField() {
        state = true;
        color = getForeground();
        date = null;
        
        txtCalendar = new JTextField();
        txtCalendar.setFont(new Font("Dialog", 0, 11));
        txtCalendar.setPreferredSize(new Dimension(100, 20));
        txtCalendar.addKeyListener(keylistenerCalendar());
        
        btnCalendar = new JButton();
        btnCalendar.setIcon(new ImageIcon(getClass().getResource("/mike/utils/jsimplecalendar/resources/JSimpleCalendarColor16.gif")));
        btnCalendar.setMargin(new Insets(10, 4, 10, 4));
        btnCalendar.setMaximumSize(new Dimension(20, 20));
        btnCalendar.setMinimumSize(new Dimension(10, 20));
        btnCalendar.addActionListener(popupShow());
        
        jcalendar = new JCalendarHeader();
        jcalendar.setActionListenerButton(actionlistenerCalendar());
        
        popup = new JPopupMenu();
        popup.add(jcalendar);
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(130, 20));
        this.add(txtCalendar);
        this.add(btnCalendar);
    }
    //<editor-fold defaultstate="collapsed" desc="Getters Setters methods">
    public String getFieldName() {return fieldName;}
    public void setFieldName(String newValue) {
        String oldValue = this.fieldName;
        this.fieldName = newValue;
        super.firePropertyChange(PROPERTY_FIELD_NAME, oldValue, newValue);
    }
    public boolean getNullable() {return nullable;}
    public void setNullable(boolean newValue){
        boolean oldValue = nullable;
        nullable = newValue;
        super.firePropertyChange(PROPERTY_NULLABLE, oldValue, newValue);
    }
    //</editor-fold>
    // GET DATE
    public Date getDate() throws CalendarFormatException {
        if(state) {
            //si se puede permitir nulo
            if(nullable)
                //devuelvo la fecha
                return date;
            //si no se puede permitir nulo
            else {
                //pregunto si la fecha es distinto de nulo
                if(date!=null)
                    //devuelvo la fecha
                    return date;
            }
        }
        throw new CalendarFormatException(fieldName);
    }
    public void setDate(Date date) {
        if(date!=null) {
            this.date = date;
            jcalendar.setDateSelected(date);
            txtCalendar.setText(new DateUtil().formatoDiaMesAnio(date));
        }
    }
    private ActionListener popupShow() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(state) {
                    if(date!=null)
                        jcalendar.setDateSelected(date);
                }
                popup.show(btnCalendar, btnCalendar.getX()-190, btnCalendar.getY()+25);
            }
        };
    }

    private ActionListener actionlistenerCalendar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //obtengo el boton que se presiono
                JToggleButton button = (JToggleButton) event.getSource();
                //cambio el dia del calendar por el dia del boton
                jcalendar.calendar.set(Calendar.DATE, Integer.parseInt(button.getText()));
                jcalendar.dateSelected.setTime(jcalendar.calendar.getTime());
                jcalendar.fireSelectionChange();
                date = jcalendar.getDateSelected();
                //lleno el campo con la fecha que se selecciono
                txtCalendar.setText(new DateUtil().formatoDiaMesAnio(jcalendar.getDateSelected()));
                //cambio de color si esta en rojo
                if(txtCalendar.getForeground()==Color.RED)
                    txtCalendar.setForeground(color);
                //si el estado es falso lo cambio a true
                if(!state)
                    state = true;
                //oculto el popup
                popup.setVisible(false);
            }
        };
    }
    private KeyListener keylistenerCalendar() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}
            @Override
            public void keyPressed(KeyEvent arg0) {}
            @Override
            public void keyReleased(KeyEvent arg0) {
                checkInput();
            }
        };
    }
    private void checkInput() {
        try {
            // Si el texto tiene tamaño de 10 caracteres
            if (txtCalendar.getText().length() == 10) {
                // Trato de parsear el año
                int year = Integer.parseInt(txtCalendar.getText().substring(6, 10));
                // Si el año esta entre estos años es valida
                if(year > 1950 && year < 3000) {
                    date = new DateUtil().getFecha(txtCalendar.getText());
                    txtCalendar.setForeground(color);
                    state = true;
                }
            }
            else
                throw new ParseException(fieldName, 0);
        }
        catch (ParseException ex) {
            txtCalendar.setForeground(Color.RED);
            state = false;
        }
        catch (NumberFormatException ex) {
            txtCalendar.setForeground(Color.RED);
            state = false;
        }
    }
    @Override
    public void setToolTipText(String text) {
        txtCalendar.setToolTipText(text);
        super.setToolTipText(text);
    }
    public boolean isEmpty() {
        return txtCalendar.getText().isEmpty();
    }
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("JSimpleCalendarField");
        JCalendarHeaderField a = new JCalendarHeaderField();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(a);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
