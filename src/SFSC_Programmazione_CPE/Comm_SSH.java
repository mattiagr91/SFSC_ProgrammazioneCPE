/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SFSC_Programmazione_CPE;

//import ch.ethz.ssh2.*;

import com.jcraft.jsch.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;


/**
 * @author paolocielo
 */

public class Comm_SSH 
{
    /***************************************************************************
    * OBJECT
    ***************************************************************************/
    private JSch     jsch;
    private Session  session;
    private UserInfo ui;
    private Channel  channel;
    
    
    /***************************************************************************
    * CONSTRUCTOR Comm_SSH
    ***************************************************************************/
    public Comm_SSH() 
    {
        jsch = new JSch();
    }
    
    
    /***************************************************************************
    * PUBLIC METHODS
    ***************************************************************************/
    
    /***************************************************************************
    * connect
    * 
     * @param User
     * @param IP
     * @param Port
     * @throws java.io.IOException
     * @throws com.jcraft.jsch.JSchException
    * 
    * Description
    * Creates a connection
    ***************************************************************************/
    public void createConnection( String User, String IP, int Port ) throws IOException, JSchException 
    {
        session=jsch.getSession(User, IP, Port);
        
        ui = new MyUserInfo()
        {
            /*@Override
            public void showMessage(String message)
            {
                JOptionPane.showMessageDialog(null, message);
            }*/
            @Override
            public boolean promptYesNo(String message) { return true; }
        };
        
        session.setUserInfo(ui);
        
    }
    
    /***************************************************************************
    * connect
    * 
     * @param User
     * @param IP
     * @param PWD
     * @param Port
     * @throws java.io.IOException
     * @throws com.jcraft.jsch.JSchException
    * 
    * Description
    * Creates a connection
    ***************************************************************************/
    public void createConnectionWithPWD( String User, String IP, String PWD,  int Port ) throws IOException, JSchException 
    {
        session=jsch.getSession(User, IP, Port);
        session.setPassword(PWD);
        
        ui = new MyUserInfo()
        {
            /*@Override
            public void showMessage(String message)
            {
                JOptionPane.showMessageDialog(null, message);
            }*/
            @Override
            public boolean promptYesNo(String message) { return true; }
        };
        
        session.setUserInfo(ui);
        
    }
    
    
    /***************************************************************************
    * connect
    * 
     * @throws com.jcraft.jsch.JSchException
     * @throws java.io.IOException
    * 
    * Description
    * Creates a SSH communication and authenticate with user and password
    ***************************************************************************/
    public void connect() throws IOException, JSchException 
    {
        session.connect();
        
        channel = session.openChannel("exec");
    }
    
    /***************************************************************************
    * execute
    * 
     * @param command
     * @throws com.jcraft.jsch.JSchException
     * @throws java.io.IOException
    * 
    * Description
    * Executes a SSH command and return the result
    ***************************************************************************/
    public void execute(String command) throws IOException, JSchException 
    {
        ((ChannelExec)channel).setCommand(command);
        channel.connect();
    }
    
    
    /***************************************************************************
    * execute
    * 
     * @param command
     * @return 
     * @throws com.jcraft.jsch.JSchException
     * @throws java.io.IOException
    * 
    * Description
    * Executes a SSH command and return the result
    ***************************************************************************/
    public String executeAndRead(String command) throws IOException, JSchException 
    {
        String answer;
        
        ((ChannelExec)channel).setCommand(command);
        channel.setInputStream(null);
        InputStream stdout = channel.getInputStream();
        channel.connect();
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
        answer = bufferedReader.readLine();
        //readAll(bufferedReader, result);
        
        return answer;
    }
    

    /***************************************************************************
    * close
    * 
    * Description
    * Closes the communication
    ***************************************************************************/
    public int close() 
    {
        int ret=0;
        if(channel.isConnected()){
            channel.disconnect();
        }else{
            ret++;
        }
        if(session.isConnected()){
            session.disconnect();
        }else{
            ret++;
        }
        return ret;
    }
    
    
    /***************************************************************************
    * PRIVATE METHODS
    ***************************************************************************/
    private static abstract class MyUserInfo implements UserInfo, UIKeyboardInteractive
    {
        @Override
        public String getPassword(){ return null; }
        @Override
        public boolean promptYesNo(String str){ return false; }
        @Override
        public String getPassphrase(){ return null; }
        @Override
        public boolean promptPassphrase(String message){ return false; }
        @Override
        public boolean promptPassword(String message){ return false; }
        @Override
        public void showMessage(String message){ }
        @Override
        public String[] promptKeyboardInteractive(  String destination, 
                                                    String name, 
                                                    String instruction, 
                                                    String[] prompt, 
                                                    boolean[] echo) 
                                                    {
                                                        return null;
                                                    }
    }    
}

