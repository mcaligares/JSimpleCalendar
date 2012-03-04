package mike.utils.jsimplecalendar.component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import mike.utils.DateUtil;
import mike.utils.jsimplecalendar.event.SelectionChangedEvent;
import mike.utils.jsimplecalendar.event.SelectionChangedListener;

public class PanelCalendar extends JPanel {

    private static final long serialVersionUID = 4376178331835211369L;
    public static final String PROP_FONT = "fontCalendar";
    public static final String PROP_DIMENSION = "dimensionCalendar";
    public static final String PROP_DATE_SELECTED = "dateSelected";
    
    protected Font fontCalendar;
    protected Dimension dimensionCalendar;
    protected Calendar dateSelected;
    
    protected Calendar calendar;
    
    private JToggleButton[] daysButtons;
    private JLabel[] daysName;
    private JPanel panelCalendar;
    private ButtonGroup groupCalendar;
    
    private int totaldays = 42;
    
    public PanelCalendar() {
        this(Calendar.getInstance().getTime(),new java.awt.Font("Dialog", 0, 10),new Dimension(48, 20));
    }

    public PanelCalendar(Date date, Font font, Dimension dimension) {
        // fecha actual
        calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        
        dateSelected = (Calendar) calendar.clone();
        
        if(date!=null)
            dateSelected.setTime(date);
        if(font!=null)
            fontCalendar = font;
        else
            fontCalendar = new java.awt.Font("Dialog", 0, 10);
        if(dimension!=null)
            dimensionCalendar = dimension;
        else
            dimensionCalendar = new Dimension(48, 20);
        
        // instanciando componentes
        daysButtons = new JToggleButton[totaldays];
        daysName = new JLabel[8];
        groupCalendar = new ButtonGroup();
        //Init jlabels
        for (int i = 0; i < 7; i++) {
            daysName[i] = new JLabel();
            daysName[i].setFont(new java.awt.Font("Dialog", 0, 11));
            daysName[i].setHorizontalAlignment(JLabel.CENTER);
        }
        //Init jbuttons
        for (int i = 0; i < totaldays; i++) {
            daysButtons[i] = new JToggleButton("");
            daysButtons[i].setFont(fontCalendar);
            daysButtons[i].setMargin(new Insets(0, 0, 0, 0));
            daysButtons[i].setPreferredSize(dimensionCalendar);
            daysButtons[i].addActionListener(actionButton());
            groupCalendar.add(daysButtons[i]);
        }
        panelCalendar = new JPanel();
        panelCalendar.setLayout(new GridLayout(7, 6));
        drawDays();
        
        setLayout(new BorderLayout());
        add(panelCalendar, BorderLayout.CENTER);
        setPreferredSize(new Dimension(227, 140));
    }
    
    //<editor-fold defaultstate="collapsed" desc="FONT CALENDAR">
    public Font getFontCalendar() {
        return fontCalendar;
    }
    public void setFontCalendar(Font buttonFont) {
        this.fontCalendar = buttonFont;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="DIMENSION CALENDAR">
    public Dimension getDimensionCalendar() {
        return dimensionCalendar;
    }
    public void setDimensionCalendar(Dimension dimensionCalendar) {
        this.dimensionCalendar = dimensionCalendar;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="DATE SELECTED">
    public Date getDateSelected() {
        return dateSelected.getTime();
    }
    public void setDateSelected(Date dateSelected) {
        //TODO hacer que al cambiar el dateselected se actualiza el calendario y el boton
        // de ese dia este este seleccionado
        this.dateSelected.setTime(dateSelected);
        calendar.setTime(dateSelected);
        drawDays();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="SELECTION CHANGED LISTENER">
    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listenerList.add(SelectionChangedListener.class, listener);
    }
    public void removeSelectionChangedListener(SelectionChangedListener listener) {
        listenerList.remove(SelectionChangedListener.class, listener);
    }
    public void fireSelectionChange() {
        SelectionChangedEvent evt = new SelectionChangedEvent(this);
        SelectionChangedListener[] listeners = listenerList.getListeners(SelectionChangedListener.class);
        for (SelectionChangedListener listener : listeners)
            listener.onSelectionChanged(evt);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CALENDAR">
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        drawDays();
    }
    //</editor-fold>

    public void setActionListenerButton(ActionListener actionListener) {
        for (int i = 0; i < totaldays; i++)
            daysButtons[i].addActionListener(actionListener);
    }
    
    private void drawCalendar() {
        // Dibujo los nombres
        String[] nombrDias = new String[]{"lun", "mar", "mié", "jue", "vie", "sab", "dom"};
        for (int i = 0; i < 7; i++) {
            daysName[i].setText(nombrDias[i]);
//            daysName[i].setFont(fontCalendar);
            // Si el dia es domingo marcar con rojo
            if (i == 6)
                daysName[i].setForeground(Color.RED);
            else
                daysName[i].setForeground(Color.BLACK);
            panelCalendar.add(daysName[i]);
        }
        // Limpio la seleccion del grupo de botones Calendar
        groupCalendar.clearSelection();
        // Dibujo los dias
        Calendar tmpCalendar = (Calendar) calendar.clone();
        tmpCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int dia = tmpCalendar.get(Calendar.DAY_OF_YEAR);
        int firstDay = tmpCalendar.get(Calendar.DAY_OF_WEEK) - 2; // first Day of Week Lunes = 2
        // Si la resta me deja un numero negativo le sumo una semana (7)
        if (firstDay < 0) firstDay += 7;
        int i = 0;
        // oculto los dias que del mes anterior
        for (int g = 0; g < firstDay; g++) {
            daysButtons[g].setText("");
            daysButtons[g].setVisible(false);
            panelCalendar.add(daysButtons[g]);
            i++;
        }
        // Primer dia del proximo mes
        tmpCalendar.add(Calendar.MONTH, 1);
        // Menos 1 dia = ultimo dia del mes actual
        tmpCalendar.set(Calendar.DATE, tmpCalendar.get(Calendar.DATE) - 1);
        // Cargo los dias del mes
        for (int j = 1; j <= tmpCalendar.get(Calendar.DATE); j++) {
            if(dia==dateSelected.get(Calendar.DAY_OF_YEAR)&&dateSelected.get(Calendar.YEAR)==tmpCalendar.get(Calendar.YEAR))
                daysButtons[i].setSelected(true);
            daysButtons[i].setText(Integer.toString(j));
            daysButtons[i].setVisible(true);
            panelCalendar.add(daysButtons[i]);
            i++;
            dia++;
        }
        // Oculto los primeros dias del proximo mes
        for (int k = i; k < totaldays; k++) {
            daysButtons[k].setVisible(false);
            daysButtons[k].setText("");
            panelCalendar.add(daysButtons[k]);
        }
        panelCalendar.repaint();
    }
    private void drawDays() {
        // Limpio el calendario
        panelCalendar.removeAll();
        drawCalendar();
    }
    
    private ActionListener actionButton() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JToggleButton button = (JToggleButton) event.getSource();
                calendar.set(Calendar.DATE, Integer.parseInt(button.getText()));
                dateSelected.setTime(calendar.getTime());
                fireSelectionChange();
            }
        };
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        PanelCalendar a = new PanelCalendar();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(a);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
