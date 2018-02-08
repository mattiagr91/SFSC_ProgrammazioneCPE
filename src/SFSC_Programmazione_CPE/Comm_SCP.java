/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SFSC_Programmazione_CPE;

import com.jcraft.jsch.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author paolocielo
 */
public class Comm_SCP {
    
    /***************************************************************************
    * OBJECT
    ***************************************************************************/
    private JSch ssh_conn = new JSch();
    private Session session = null;
    private ChannelSftp channel = null;
    
    
    /***************************************************************************
    * CONSTRUCTOR Comm_SCP
    * 
     * @param IP
     * @param Password
     * @param User
     * @throws com.jcraft.jsch.JSchException
    * 
    * Description
    * Creates a SCP communication to transfer file
    ***************************************************************************/
    public Comm_SCP(String IP, String Password, String User) throws JSchException 
    {
        session = ssh_conn.getSession(User, IP, 22);
        session.setPassword(Password);
        session.setConfig("StrictHostKeyChecking", "no");   
    }
    
    
    /***************************************************************************
    * PUBLIC METHODS
    ***************************************************************************/
    
    /***************************************************************************
    * connect
    * 
     * @throws com.jcraft.jsch.JSchException
    * 
    * Description
    * Opens communication
    ***************************************************************************/
    public void connect() throws JSchException 
    {
        
        session.connect();
        channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
    }
    
    
    /***************************************************************************
    * putFile
    * 
     * @param local_directory
     * @param Check -> If Check == 1 
     *                 checks which is the correct directory to put the file
     * @return 
     * @throws com.jcraft.jsch.SftpException
     * @throws java.io.FileNotFoundException
    * 
    * Description
    * Sends the file stored in the directory passed.
    ***************************************************************************/
    public int putFile(String local_directory, int Check) throws SftpException, FileNotFoundException 
    {
        File localFile = new File(local_directory);
        String remote_directory = null;
        
        remote_directory = checkDirectory(Check); 
        if("Err".equals(remote_directory)) 
            return 1;
        
        channel.cd(remote_directory);
          if(FilenameUtils.getName(local_directory).equals("utente_ac.json")){
            remote_directory = remote_directory + "/utente.json";
        }
          
        channel.put(local_directory, remote_directory);
        //channel.put( new FileInputStream(localFile), localFile.getName() );
        
        return 0;
    }
    

    /***************************************************************************
    * close
    * 
    * Description
    * Closes the communication
    ***************************************************************************/
    public void close() 
    {
        channel.disconnect();
        session.disconnect();
    }
    
    
    /***************************************************************************
    * PRIVATE METHOD
    ***************************************************************************/
    
    /***************************************************************************
    * checkDirectory
    * 
    * Description
    * Checks which is the correct directory. If none exist report error
    ***************************************************************************/
    private String checkDirectory(int typeOfFile) throws SftpException 
    {
        String SXT_skins = "/skins";
        String LHG_skins = "/flash/skins";
        String SXT = "/";
        String LHG = "/flash";
        SftpATTRS  attrs = null;
        
        if(typeOfFile == 1){
            
          try{
                attrs = channel.stat(LHG_skins);
            } catch(Exception e) {
                
            }
            if(attrs != null){
                return LHG_skins;
            } else {
                return SXT_skins;
            }
        }
        if(typeOfFile == 2){
            try{
                attrs = channel.stat(LHG);
            } catch(Exception e) {
                
            }
            if(attrs != null){
                return LHG;
            } else {
                return SXT;
            }
        }
       if(typeOfFile == 3){
            try{
                attrs = channel.stat(LHG);
            } catch(Exception e) {
                
            }
            if(attrs != null){
                return SXT;
            } else {
                return SXT;
            }
        }
        return null;
    }
      
}
