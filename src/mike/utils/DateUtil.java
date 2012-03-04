/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mike.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateUtil esta clase nos ayuda a manejar Fechas.
 * @author Miguel Augusto Caligares
 * @email mcaligares@gmail.com
 * @version 1.1
 */
public class DateUtil {
    private final String FORMAT_MONTH = "MMMM";
    private final String FORMAT_MONTH_YEAR = "MM/yyyy";
    private final String FORMAT_FULL_MONTH_YEAR = "MMMM yyyy";
    private final String FORMAT_DAY_MONTH_YEAR = "dd/MM/yyyy";
    private final String FORMAT_DAY_MONTH_YEAR_TIME = "dd/MM/yyyy H:m:s";
    private final String FORMAT_HOUR_MINUTE = "HH:mm";
    private final String FORMAT_OUTPUT_LOG = "yyyy_MM_dd_Hms";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    private Calendar calendar = Calendar.getInstance();

    public DateUtil() {}

    /**
     * Devuelve el tiempo actual.
     * @return long
     * @deprecated getFechaActual
     */
    public static long getTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }
    /**
     * Comparo dos fechas.
     * @param fecha1 Date fecha.
     * @param fecha2 Date fecha.
     * @return 
     */
    public static boolean compararFecha(Date fecha1, Date fecha2) {
        return fecha1.equals(fecha2);
    }
    /**
     * Comparo dos fechas.
     * @param fecha1 Date fecha.
     * @param fecha2 Date fecha.
     * @return 
     */
    public static boolean antesFecha(Date fecha, Date comparar) {
        return fecha.before(comparar);
    }
    /**
     * Devuelve la fecha actual.
     * @return Date
     */
    public static Date getFechaActual() {
        return Calendar.getInstance().getTime();
    }
    /**
     * Devuelve una fecha de un texto. Formato dd/MM/yyyy
     * @param fecha String fecha
     * @return Date
     * @throws ParseException Si no se puede parsear
     */
    public Date getFecha(String fecha) throws ParseException {
        simpleDateFormat.applyPattern(FORMAT_DAY_MONTH_YEAR);
        return simpleDateFormat.parse(fecha);
    }
    /**
     * Devuelve una fecha con el tiempo de un texto. Formato dd/MM/yyyy, HH:mm
     * @param fecha String fecha
     * @param tiempo String tiempo
     * @return Date
     * @throws ParseException Si no se puede parsear
     */
    public Date getFechaTiempo(String fecha, String tiempo) throws ParseException {
        Calendar c = Calendar.getInstance();
        simpleDateFormat.applyPattern(FORMAT_HOUR_MINUTE);
        c.setTime(simpleDateFormat.parse(tiempo));
        
        simpleDateFormat.applyPattern(FORMAT_DAY_MONTH_YEAR);
        calendar.setTime(simpleDateFormat.parse(fecha));
        
        calendar.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
        
        return calendar.getTime();
    }
    
    /**
     * Formatea una fecha y devuelve la hora y minuto.
     * @param date Date fecha
     * @return String Hora:Minuto (HH:mm)
     */
    public String formatoHora(Date date) {
        simpleDateFormat.applyPattern(FORMAT_HOUR_MINUTE);
        return simpleDateFormat.format(date);
    }
    /**
     * Formatea una fecha y devuelve el mes.
     * @param date Date fecha
     * @return String Mes (MMMM)
     */
    public String formatoMes(Date date) {
        simpleDateFormat.applyPattern(FORMAT_MONTH);
        return simpleDateFormat.format(date);
    }
    /**
     * Formatea una fecha y devuelve mes y año.
     * @param date Date fecha
     * @return String MesAño(MM/yyyy)
     */
    public String formatoMesAnio(Date date) {
        simpleDateFormat.applyPattern(FORMAT_MONTH_YEAR);
        return simpleDateFormat.format(date);
    }
    /**
     * Formatea una fecha y devuelve dia mes y año.
     * Si el valor es null devuelve "" (cadena vacia)
     * @param date Date fecha
     * @return String DiaMesAño(dd/MM/yyyy)
     */
    public String formatoDiaMesAnio(Date date) {
        if(date!=null) {
            simpleDateFormat.applyPattern(FORMAT_DAY_MONTH_YEAR);
            return simpleDateFormat.format(date);
        }
        else
            return "";
    }
    /**
     * Formatea una fecha y devuelve dia mes año y tiempo.
     * @param date Date fecha
     * @return String DiaMesAñoTiempo(dd/MM/yyyy H:m:s)
     */
    public String formatoDiaMesAnioTiempo(Date date) {
        simpleDateFormat.applyPattern(FORMAT_DAY_MONTH_YEAR_TIME);
        return simpleDateFormat.format(date);
    }
    /**
     * Formatea una fecha y devuelve mes y año.
     * @param date Date fecha
     * @return String DiaMesAñoTiempo(MMMM yyyy)
     */
    public String formatoFullMesAnio(Date date) {
        simpleDateFormat.applyPattern(FORMAT_FULL_MONTH_YEAR);
        return simpleDateFormat.format(date);
    }
    /**
     * Devuelve la fecha actual sin espacios año mes dia hora minuto y segundos
     * @return String DiaMesAño(yyyy_MM_dd_Hms)
     */
    public String formatoOutputLog() {
        simpleDateFormat.applyPattern(FORMAT_OUTPUT_LOG);
        return simpleDateFormat.format(getFechaActual());
    }
    /**
     * Devuelve una fecha inicial.
     * @param date Date
     * @return Date
     */
    public Date fechaInicial (Date date) {
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        //calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
        return calendar.getTime();
    }
    /**
     * Devuelve una fecha final.
     * @param date Date
     * @return Date
     */
    public Date fechaFinal(Date date) {
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
        return calendar.getTime();
    }
    /**
     * Devuelvo el proximo mes de la fecha.
     * @param fecha Date
     * @return Date
     */
    public Date nextMes(Date fecha) {
        calendar.setTime(fecha);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
        return calendar.getTime();
    }
    /**
     * Devuelvo el dia 15 de la fecha.
     * @param fecha Date
     * @return Date
     */
    public Date nextDia(Date fecha) {
        calendar.setTime(fecha);
        calendar.set(Calendar.DATE, 15);
        return calendar.getTime();
    }
    /**
     * Devuelve la fecha seteada con la prmer hora.
     * @param fecha Date a cambiar
     * @return Date cambiada
     */
    public Date firstHora(Date fecha) {
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }
    /**
     * Devuelve la fecha seteada con la ultima hora.
     * @param fecha Date a cambiar
     * @return Date cambiada
     */
    public Date lastHora(Date fecha) {
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        return calendar.getTime();
    }
    /**
     * Devuelve el dia de la fecha enviada.
     * @param d Date fecha
     * @return int Dia
     */
    public int getDia(Date d) {
        calendar.setTime(d);
        return calendar.get(Calendar.DATE);
    }
    /**
     * Devuelve el mes de la fecha enviada.
     * @param d Date fecha
     * @return int Mes
     */
    public int getMes(Date d){
        calendar.setTime(d);
        return calendar.get(Calendar.MONTH);
    }
    /**
     * Devuelve el año de la fecha enviada.
     * @param d Date fecha
     * @return int Año
     */
    public int getAnio(Date d){
        calendar.setTime(d);
        return calendar.get(Calendar.YEAR);
    }
    public Date getFecha(int month, int year) {
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }
    public int getDayOfYear(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }
}
