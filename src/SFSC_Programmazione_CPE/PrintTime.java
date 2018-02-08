package SFSC_Programmazione_CPE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.ParseException;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/**
 * @author paolocielo
 */

public class PrintTime {
    
    /***************************************************************************
    * CONSTRUCTORS PrintTime
    ***************************************************************************/
    public PrintTime() 
    {}
    

    /***************************************************************************
    * CONSTRUCTORS CommManager
    * 
     * @return 
     * @throws java.text.ParseException
    * 
    * Description
    * Returns date and time in the format YYYY/MM/DD - HH:MM:SS
    ***************************************************************************/
    public String getCurrentTime() throws ParseException 
    {
                
        GregorianCalendar gc = new GregorianCalendar();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
                
        return sdf.format(gc.getTime());
    }
    
}
