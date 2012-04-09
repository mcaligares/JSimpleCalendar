/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mike.utils.jsimplecalendar.component;

import java.beans.BeanDescriptor;
import java.beans.SimpleBeanInfo;

/**
 *
 * @author mike
 */
public class JCalendarComboFieldBeanInfo extends SimpleBeanInfo {
    private static final String FILE = "/mike/utils/jsimplecalendar/resources/JSimpleCalendarField%s.gif";
    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor bd = new BeanDescriptor(JCalendarComboField.class, null);
        bd.setDisplayName("Calendar Field");
        bd.setShortDescription("A simple calendar field (JComboBox)");
        return bd;
    }
    @Override
    public java.awt.Image getIcon(int iconKind) {
        switch (iconKind) {
            case ICON_MONO_16x16: return loadImage(String.format(FILE, "Mono16"));
            case ICON_MONO_32x32: return loadImage(String.format(FILE, "Mono32"));
            case ICON_COLOR_16x16: return loadImage(String.format(FILE, "Color16"));
            case ICON_COLOR_32x32: return loadImage(String.format(FILE, "Color32"));
            default: return null;
        }
    }
}
