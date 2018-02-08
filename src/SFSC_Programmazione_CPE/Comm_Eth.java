/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package SFSC_Programmazione_CPE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


/**
 *
 * @author paolocielo
 */

public class Comm_Eth {
    
    
    /***************************************************************************
     * VARIABLE
     ***************************************************************************/
    private String OS_Type;

    
    /***************************************************************************
     * CONSTANT
     ***************************************************************************/
    private static final int MAX_TIME = 100;
    
    
    /***************************************************************************
     * CONSTRUCTUR
     *
     * @param OS -> String that represent the OS on the PC
     *
     ***************************************************************************/
    public Comm_Eth(String OS)
    {
        OS = OS.toLowerCase();
        
        if(OS.contains("mac")){
            OS_Type = "MAC";
        }
        if(OS.contains("win")){
            OS_Type = "WIN";
        }
        else{
            OS_Type="LINUX";
        }
        

    }
    
    
    /***************************************************************************
     * setStaticIp
     *
     * @param Eth_Interface
     * @throws java.io.IOException
     *
     * Description:
     * Sets a static IP on the Ethernet Interface passed
     ***************************************************************************/
    public void setStaticIp(String Eth_Interface) throws IOException
    {
        String Static_IP = "192.168.88.4";
        String Mask      = "255.255.255.0";
        String Router_IP = "192.168.88.1";
        if(OS_Type.equals("MAC"))
        {
            String[] command1 = { "networksetup",
                "-setmanual",
                Eth_Interface,
                Static_IP,
                Mask,
                Router_IP,
            };
            
            Process pp = java.lang.Runtime.getRuntime().exec(command1);
        }
        
        if(OS_Type.equals("WIN"))
        {
            String[] command1 = {   "netsh",
                "interface",
                "ip",
                "set",
                "address",
                "name=",
                Eth_Interface,
                "source=static",
                "addr=",
                Static_IP,
                "mask=",
                Mask
            };
            
            Process pp = java.lang.Runtime.getRuntime().exec(command1);
        }
        
    }
    
    
    /***************************************************************************
     * setStaticIp
     *
     * @param Eth_Interface
     * @throws java.io.IOException
     *
     * Description:
     * Sets a static IP on the Ethernet Interface passed
     ***************************************************************************/
    public void setStaticIpTEST(String Eth_Interface) throws IOException
    {
        String Static_IP = "130.192.164.117";
        String Mask      = "255.255.255.0";
        String Router_IP = "130.192.164.254";
        if(OS_Type.equals("MAC"))
        {
            String[] command1 = { "networksetup",
                "-setmanual",
                Eth_Interface,
                Static_IP,
                Mask,
                Router_IP,
            };
            
            Process pp = java.lang.Runtime.getRuntime().exec(command1);
        }
        
        if(OS_Type.equals("WIN"))
        {
            String[] command1 = {   "netsh",
                "interface",
                "ip",
                "set",
                "address",
                "name=",
                Eth_Interface,
                "source=static",
                "addr=",
                Static_IP,
                "mask=",
                Mask
            };
            
            Process pp = java.lang.Runtime.getRuntime().exec(command1);
        }
        
    }
    
    
    /***************************************************************************
     * setDHCP
     *
     * @param Eth_Interface
     * @throws java.io.IOException
     *
     * Description:
     * Sets in DHCP the Ethernet Interface passed
     ***************************************************************************/
    public void setDHCP(String Eth_Interface) throws IOException
    {
        
        if(OS_Type.equals("MAC"))
        {
            String[] command1 = {   "networksetup",
                "-setdhcp",
                Eth_Interface,
            };
            
            Process pp = java.lang.Runtime.getRuntime().exec(command1);
        }
        
        if(OS_Type.equals("WIN"))
        {
            String[] command1 = {   "netsh",
                "interface",
                "ip",
                "set",
                "address",
                "name=",
                Eth_Interface,
                "source=dhcp",
            };
            
            Process pp = java.lang.Runtime.getRuntime().exec(command1);
        }
        
    }
    
    
    
    /***************************************************************************
     * findInterface
     *
     * @return
     * @throws java.io.IOException
     *
     * Description:
     * Returns an ArrayList with all Ethernet Interfaces available
     ***************************************************************************/
    public ArrayList<String> findInterface() throws IOException
    {
        ArrayList<String> portsList = new ArrayList<>();
        Process exec_com = null;
        
        if(OS_Type.equals("MAC"))
        {
            
            String[] command = {    "networksetup",
                "-listallnetworkservices"
            };
            exec_com = java.lang.Runtime.getRuntime().exec(command);
            
        }
        
        if(OS_Type.equals("WIN"))
        {
            String[] command1 = {   "netsh",
                "interface",
                "show",
                "interface",
                
            };
            
            exec_com = java.lang.Runtime.getRuntime().exec(command1);
        }
        
        BufferedReader response = new BufferedReader(new InputStreamReader(exec_com.getInputStream()));
        
        String responseLine = null;
        while ((responseLine = response.readLine()) != null)
        {
            portsList.add(responseLine);
        }
        
        return portsList;
    }
    
    
    
    /***************************************************************************
     * ping
     *
     * @param IP_Address
     * @throws java.net.UnknownHostException
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @return
     *
     * Description:
     * Returns an ArrayList with all Ethernet Interfaces available
     ***************************************************************************/
    @SuppressWarnings("SleepWhileInLoop")
    public boolean ping(String IP_Address) throws UnknownHostException, IOException, InterruptedException
    {
        int contPing = 0;
        boolean reach = false;
        
        InetAddress inet = InetAddress.getByName(IP_Address);
        
        long startTime = System.currentTimeMillis(), pingtime = 0, endTime;
        
        if(OS_Type.equals("WIN"))
        {
            while(pingtime<MAX_TIME)
            {
                Process p1 = java.lang.Runtime.getRuntime().exec("ping -n 1 "+inet.getHostAddress());
                int returnVal = p1.waitFor();
                if(returnVal == 0)
                {
                    contPing++;
                    sleep(500);
                    if(contPing == 5)
                    {
                        reach = true;
                        break;
                    }
                }
                else
                {
                    contPing=0;
                    sleep(2000);
                }
                endTime = System.currentTimeMillis();
                pingtime = (endTime - startTime)/1000;
            }
        } else {
            if(OS_Type.equals("MAC"))

            {
                while(pingtime<MAX_TIME)
                {
                    if(inet.isReachable(2000))
                    {
                        contPing++;
                        sleep(500);
                        if(contPing == 5)
                        {
                            reach = true;
                            break;
                        }
                    }
                    else
                    {
                        contPing=0;
                        sleep(2000);
                    }
                    endTime = System.currentTimeMillis();
                    pingtime = (endTime - startTime)/1000;
                }

            } else {
                while(pingtime<MAX_TIME)
                {
                    Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 "+inet.getHostAddress());
                    int returnVal = p1.waitFor();
                    if(returnVal == 0){
                        contPing++;
                        sleep(500);
                        if(contPing == 5)
                        {
                            reach = true;
                            break;
                        }
                    }
                    else
                    {
                        contPing=0;
                        sleep(2000);
                    }
                    endTime = System.currentTimeMillis();
                    pingtime = (endTime - startTime)/1000;
                }
            }
        }
        
        return reach;
        
        
        
    }
    
    
    
}
