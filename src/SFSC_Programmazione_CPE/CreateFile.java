package SFSC_Programmazione_CPE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author paolocielo
 */


/*******************************************************************************
* This class is used to manage the log file. 
* 
* This file stores all the operations carry out by the program 
* during the execution
*******************************************************************************/
public class CreateFile {
    
    /***************************************************************************
    * OBJECT
    ***************************************************************************/
    public File file;
    public FileWriter fw; 
    public PrintTime  time = new PrintTime();

    /***************************************************************************
    * CONSTRUCTORS CreateFile
    * 
     * @param file_name
     * @throws java.io.IOException
    * 
    * Description
    * Creates the file and open it
    ***************************************************************************/
    public CreateFile(String file_name) throws IOException
    {
        file = new File(file_name);
        fw = new FileWriter(file);
    }
    

    
    /***************************************************************************
    * CONSTRUCTORS CreateFile
    * 
     * @throws java.io.IOException
    * 
    * Description
    * Creates the file in a default folder and open it
    ***************************************************************************/
    public CreateFile() throws IOException
    {
        try {
            CodeSource codeSource = SCSF_Programmazione_CPE.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String dir = jarFile.getParentFile().getPath();
            
            String separator = System.getProperty("file.separator");
            //file = new File("/Users/paolocielo/Desktop/Log_File/log_SSH_INT.txt");
            file = new File(dir +separator+ "log_exec.txt");
            fw= new FileWriter(file);
        } catch (URISyntaxException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /****************************************************************************
    * initializaFile
    * 
     * @param initialization
     * @throws java.io.IOException
     * @throws java.text.ParseException
    * 
    * Description
    * Creates the file in a default folder and open it
    ***************************************************************************/
    public void initializeFile(String initialization) throws IOException, ParseException
    {
        fw.write("***********************************************************************************************\n");
        fw.write("\n");
        fw.write(initialization + "\n");
        fw.write("\n");
        fw.write("Start time: " + time.getCurrentTime() + "\n");
        fw.write("\n");        
        fw.write("***********************************************************************************************\n");
        fw.flush();
    }

    /***************************************************************************
    * append
    * 
     * @param insert
     * @throws java.io.IOException
     * @throws java.text.ParseException
    * 
    * Description
    * Writes a string on a line of the file without terminate the line 
    ***************************************************************************/
    public void append(String insert) throws IOException, ParseException 
    {
        fw.write(time.getCurrentTime() + " -> " + insert);
        fw.flush();
    };
    

    
    /***************************************************************************
    * write
    * 
     * @param insert
     * @throws java.io.IOException
     * @throws java.text.ParseException
    * 
    * Description
    * Writes a string on a line of the file and then terminate the line
    ***************************************************************************/
    public void write(String insert) throws IOException, ParseException 
    {
        fw.write(time.getCurrentTime() + " -> " +insert+"\n");
        fw.flush();
    };
    
    
    /***************************************************************************
    * addSpace
    * 
     * @throws java.io.IOException
     * @throws java.text.ParseException
    * 
    * Description
    * Add an empty line into the log file
    ***************************************************************************/
    public void addSpace() throws IOException, ParseException 
    {
        fw.write("\n");
        fw.flush();
    };
    
    
    /***************************************************************************
    * write
    * 
     * @throws java.io.IOException
     * @throws java.text.ParseException
    * 
    * Description
    * Closes file
    ***************************************************************************/    
    public void close() throws IOException, ParseException
    {
        fw.write(time.getCurrentTime() + " -> File closed\n");
        fw.close();
    }
    
}
