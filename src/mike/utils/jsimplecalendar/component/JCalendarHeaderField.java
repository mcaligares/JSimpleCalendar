package mike.utils.jsimplecalendar.component;

import java.awt.Color;
import java.awt.Dimension;
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
    // Properties
    private String fieldName;
    private boolean state;
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
        txtCalendar.setPreferredSize(new Dimension(100, 20));
        txtCalendar.addKeyListener(keylistenerCalendar());
        
        btnCalendar = new JButton();
        btnCalendar.setText(".");
        btnCalendar.setMargin(new Insets(0, 0, 0, 0));
        btnCalendar.setMaximumSize(new Dimension(30, 19));
        btnCalendar.setMinimumSize(new Dimension(30, 19));
        btnCalendar.addActionListener(popupShow());
        
        jcalendar = new JCalendarHeader();
        jcalendar.setActionListenerButton(actionlistenerCalendar());
        
        popup = new JPopupMenu();
        popup.add(jcalendar);
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(100, 19));
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
    //</editor-fold>
    // GET DATE
    public Date getDate() throws CalendarFormatException {
        if(state) {
            if(date!=null)
                return date;
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
                JToggleButton button = (JToggleButton) event.getSource();
                jcalendar.calendar.set(Calendar.DATE, Integer.parseInt(button.getText()));
                jcalendar.dateSelected.setTime(jcalendar.calendar.getTime());
                jcalendar.fireSelectionChange();
                date = jcalendar.getDateSelected();
                txtCalendar.setText(new DateUtil().formatoDiaMesAnio(jcalendar.getDateSelected()));
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
        catch (NumberFormatException | ParseException ex) {
            txtCalendar.setForeground(Color.RED);
            state = false;
        }
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
