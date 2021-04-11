/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultis;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class DateConvert {

    final static Logger log = Logger.getLogger(DateConvert.class.getName());

    public DateConvert() {
    }

    public static Date convertStringToDate(String date, String pattern) {
        Date dateC = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            dateC = format.parse(date);
        } catch (Exception e) {
            //log
            log.info(e.toString());
        }
        return dateC;
    }

    public static String convertDateToString(Date date, String pattern) {
        String dateString = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            dateString = format.format(date);
        } catch (Exception e) {
            //log
            log.info(e.toString());
        }
        return dateString;
    }

    static public String addOneDay(String date) {
        return LocalDate.parse(date).plusDays(1).toString();
    }
}
