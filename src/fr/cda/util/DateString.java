package fr.cda.util;

import java.util.*;
import java.text.*;

// TODO: Auto-generated Javadoc
/**
 * The Class DateString.
 */
public class DateString
{
    // Teste si date1 est inferieure strict a date2
    /**
     * Inferieur strict.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if successful
     */
    //
    public static boolean inferieurStrict(String date1,String date2)
    {
        int r = toCalendar(date1).compareTo(toCalendar(date2));
        return (r==-1);
    }
    
    // Teste si date1 est inferieure ou egale a date2
    /**
     * Inferieur ou egal.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if successful
     */
    //
    public static boolean inferieurOuEgal(String date1,String date2)
    {
        int r = toCalendar(date1).compareTo(toCalendar(date2));
        return ((r==-1)||(r==0));
    }
    
    // Teste si date1 est inferieure ou egale a date2
    /**
     * Superieur strict.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if successful
     */
    //
    public static boolean superieurStrict(String date1,String date2)
    {
        int r = toCalendar(date1).compareTo(toCalendar(date2));
        return (r==1);
    }
    
    // Teste si date1 est superieure ou egale a date2
    /**
     * Superieur ou egal.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if successful
     */
    //
    public static boolean superieurOuEgal(String date1,String date2)
    {
        int r = toCalendar(date1).compareTo(toCalendar(date2));
        return ((r==1)||(r==0));
    }
    
    // Retourne la date suivante
    /**
     * Date suivante.
     *
     * @param date the date
     * @return the string
     */
    //
    public static String dateSuivante(String date)
    {
        Calendar cal = DateString.toCalendar(date);
        cal.set(Calendar.SECOND,86400);
        return toString(cal);
    }

    // Retourne la date precedente
    /**
     * Date precedente.
     *
     * @param date the date
     * @return the string
     */
    //
    public static String datePrecedente(String date)
    {
        Calendar cal = DateString.toCalendar(date);
        cal.set(Calendar.SECOND,-86400);
        return toString(cal);
    }


    // Retourne la date Calendar d'une date au format "JJ/MM/AAAA"
    /**
     * To calendar.
     *
     * @param date the date
     * @return the calendar
     */
    //
    public static Calendar toCalendar(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse(date,
                           new ParsePosition(0));
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(d);
        return dateCal;
    }

    // Retourne la date au format "JJ/MM/AAAA" d'une date Calendar
    /**
     * To string.
     *
     * @param dateCal the date cal
     * @return the string
     */
    //
    public static String toString(Calendar dateCal)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuffer sb = new StringBuffer();
        sdf.format(dateCal.getTime(),sb,new FieldPosition(0));
        
        return sb.toString();
    }
}