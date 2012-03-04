/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mike.utils.jsimplecalendar.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.*;
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
public class JCalendarComboField extends JPanel {
    private static final long serialVersionUID = -8599879307600944308L;
    // Properties name
    public static final String PROPERTY_FIELD_NAME = "fieldName";
    public static final String PROPERTY_STATE = "state";
    // Properties
    private String fieldName;
    private boolean state;
    private Color color;
    private Date date;
    // Componentes
    private JTextField txtCalendar;
    private JToggleButton btnCalendar;
    private PanelCalendar jcalendar;
    private JPopupMenu popup;
    // Controla el estado del popupmenu
    private boolean isVisiblePopup;

    public JCalendarComboField() {
        isVisiblePopup = true;
        state = true;
        color = getForeground();
        date = null;
        
        txtCalendar = new JTextField();
        txtCalendar.setPreferredSize(new Dimension(100, 20));
        txtCalendar.addKeyListener(keylistenerCalendar());
        
        btnCalendar = new JToggleButton();
        btnCalendar.setText(".");
        btnCalendar.setMargin(new Insets(0, 0, 0, 0));
        btnCalendar.setMaximumSize(new Dimension(30, 19));
        btnCalendar.setMinimumSize(new Dimension(30, 19));
        btnCalendar.addActionListener(popupShow());
        
        jcalendar = new JCalendarCombo();
        jcalendar.setActionListenerButton(actionlistenerCalendar());
        
        popup = new JPopupMenu(){
            private static final long serialVersionUID = 3109256773218160485L;
            @Override
            public void setVisible(boolean b) {
                super.setVisible(isVisiblePopup);
            }
            
        };
        popup.add(jcalendar);
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(100, 19));
        this.add(txtCalendar);
        this.add(btnCalendar);
    }
    //<editor-fold defaultstate="collapsed" desc="Getters Setter methods">
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
//            if(date!=null)
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
    // Al accionar el boton calendario
    private ActionListener popupShow() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // Si el texto es válido
                if(state) {
                    // Si el date es distinto de null
                    if(date!=null)
                        // Cambio la fecha seleccionada del PanelCalendar
                        jcalendar.setDateSelected(date);
                }
                // Si el popup esta visible
                if(popup.isVisible()) {
                    // Oculto el popup
                    isVisiblePopup = false;
                    popup.setVisible(isVisiblePopup);
                }
                // Si el popup no esta visible
                else {
                    // Muestro el popup
                    isVisiblePopup = true;
                    popup.show(btnCalendar, btnCalendar.getX()-140, btnCalendar.getY()+30);
                }
            }
        };
    }
    // Action al seleccionar un dia del PanelCalendar
    private ActionListener actionlistenerCalendar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Obtengo el boton que fue seleccionado
                JToggleButton button = (JToggleButton) event.getSource();
                // Cambio la fecha del calendar
                jcalendar.calendar.set(Calendar.DATE, Integer.parseInt(button.getText()));
                // Cambia la fecha seleccionada del calendar
                jcalendar.dateSelected.setTime(jcalendar.calendar.getTime());
                // Ejecuta la accion SelectionChange
                jcalendar.fireSelectionChange();
                // Cambio la fecha del combofield
                date = jcalendar.getDateSelected();
                // Escribo la fecha con el formato en el campo de texto
                txtCalendar.setText(new DateUtil().formatoDiaMesAnio(jcalendar.getDateSelected()));
                // Oculto el popup
                isVisiblePopup = false;
                popup.setVisible(isVisiblePopup);
                // Deselecciono el boton del calendar
                btnCalendar.setSelected(isVisiblePopup);
            }
        };
    }
    // Key Listener del Campo
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
    // Verifico el contenido del campo
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
            // Si ocurre algun error cambiamos el color de la fuente a rojo y el estado a falso
            txtCalendar.setForeground(Color.RED);
            state = false;
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("JSimpleCalendarComboField");
        JCalendarComboField a = new JCalendarComboField();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(a);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
}
