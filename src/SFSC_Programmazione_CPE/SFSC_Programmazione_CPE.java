/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package SFSC_Programmazione_CPE;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;
import java.nio.file.DirectoryStream;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static java.lang.Thread.sleep;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


/**
 * @author paolocielo
 */
public class SFSC_Programmazione_CPE extends javax.swing.JFrame {
    
    /**
     * Creates new form SSH_Interface
     */
    /**
     * *************************************************************************
     * OBJECT
     **************************************************************************
     */
    private CreateFile log;
    private Comm_Eth eth;
    private Comm_SCP scp;
    private Comm_SSH ssh;
    
    /**
     * *************************************************************************
     * VARIABLE
     **************************************************************************
     */
    
    private final String version = "4.1.1";
    private final String sep = System.getProperty("file.separator");
     
    private String dir = null;
    
    private int waitOrFinish = 0;
    private String lastLine = null;
    private String panelName = null;
    private String panelArchitecture = null;
    private String panelVersion= null;
    private String filePanelVersion = null;
    private String panelLabelText = null;
    private LinkedHashMap<String, String> usedPanels;
    private char[] pwd = {'S', 'F', 'S', 'C'};
    private GridBagConstraints constraints;
    private int retprogramPanel;
    private static String OS = System.getProperty("os.name").toLowerCase();
    
    /**
     * *************************************************************************
     * CONSTANT
     **************************************************************************
     */
    private static final String IP_DEF = "192.168.88.1";
    private static final String IP_SET = "192.168.22.1";
    private static final int PORT = 22;
    
    /**
     * *************************************************************************
     * SSH_Interface
     *
     * @throws java.io.IOException
     * @throws java.text.ParseException
     * @throws com.jcraft.jsch.JSchException
     *
     * Description Initializes elements
     **************************************************************************
     */
    public SFSC_Programmazione_CPE() throws IOException, ParseException, JSchException {
        try {
            
            URL iconURL = getClass().getResource("logo_sfsc_128.png");
            // iconURL is null when not found
            ImageIcon icon = new ImageIcon(iconURL);
            this.setIconImage(icon.getImage());
            
            initComponents();
            
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            
            this.setTitle("SFSC Programmazione CPE v"+version);
            this.getRootPane().setDefaultButton(ButtonProgPanel);
            
            CodeSource codeSource = SFSC_Programmazione_CPE.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            dir = jarFile.getParentFile().getPath();
            // Create file log
            log = new CreateFile();
            
            //write(log.file.toString());
            
            log.write("SFSC Programmazione CPE v"+version);
            log.addSpace();
            log.initializeFile("SSH_Interface");
            

            String encrypted = dir+sep+"encrypted";
            TextFieldFolderFiles.setText(encrypted);
            PanelUnderConfigurationLabel.setText(" ");
            CheckBoxPanel.setLayout(new GridBagLayout());
            constraints = new GridBagConstraints();
            ConfigureAllCheckBox.setVisible(false);
            
            checkFolderFileAndPopulateCheckBox();
            
            // Set auto scroll
            DefaultCaret caret = (DefaultCaret) TextAreaAll.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            
            // Initialize Comm_Eth
            this.eth = new Comm_Eth(System.getProperty("os.name"));
            
            //write(System.getProperty("os.name"));
            
            // Initialize Comm_SCP NON QUI, MEGLIO CREARNE UNA PER OGNI CONFIGURAZIONE, UNA CONNESSIONE SCP NON PUO' ESSERE RIUTILIZZATA UNA VOLTA DISCONNESSA
            //this.scp = new Comm_SCP( "192.168.88.1", "", "admin");
            // Initialize Comm_SSH_old
            this.ssh = new Comm_SSH();
            
            // Find all available ethernet interfaces
            //ArrayList<String> list = eth.findInterface();
            
            // Reset ComboBOX
//        log.write("Available Ethernet Interface:");
//        ComboBoxSelNetwork.removeAllItems();
//        for (int cont = 0; cont < list.size() - 1; cont++) {
//            ComboBoxSelNetwork.insertItemAt(list.get(cont + 1), cont);
//            log.write((cont + 1) + ") " + list.get(cont + 1));
//        }
        } catch (URISyntaxException ex) {
            Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        LabelUserFile1 = new javax.swing.JLabel();
        TextFieldFolderFiles = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaAll = new javax.swing.JTextArea();
        changeFolderButton = new javax.swing.JButton();
        LabelFilesFound = new javax.swing.JLabel();
        CheckBoxPanel = new javax.swing.JPanel();
        ConfigureAllCheckBox = new javax.swing.JCheckBox();
        PanelUnderConfigurationLabel = new javax.swing.JLabel();
        UpgradeCheckBox = new javax.swing.JCheckBox();
        ButtonProgPanel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(null);
        setState(Frame.NORMAL);

        jScrollPane2.setBorder(null);

        LabelUserFile1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelUserFile1.setText("Cartella files:");
        LabelUserFile1.setToolTipText("");

        TextFieldFolderFiles.setEditable(false);
        TextFieldFolderFiles.setText("inserisci indirizzo file");
        TextFieldFolderFiles.setFocusable(false);

        TextAreaAll.setColumns(20);
        TextAreaAll.setRows(5);
        TextAreaAll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        TextAreaAll.setFocusable(false);
        TextAreaAll.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(TextAreaAll);

        changeFolderButton.setBackground(new java.awt.Color(204, 204, 204));
        changeFolderButton.setText("Cambia Cartella");
        changeFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFolderButtonActionPerformed(evt);
            }
        });

        LabelFilesFound.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelFilesFound.setText("Files trovati:");

        CheckBoxPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout CheckBoxPanelLayout = new javax.swing.GroupLayout(CheckBoxPanel);
        CheckBoxPanel.setLayout(CheckBoxPanelLayout);
        CheckBoxPanelLayout.setHorizontalGroup(
            CheckBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        CheckBoxPanelLayout.setVerticalGroup(
            CheckBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        ConfigureAllCheckBox.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ConfigureAllCheckBox.setText("Configura tutti");
        ConfigureAllCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ConfigureAllCheckBoxItemStateChanged(evt);
            }
        });

        PanelUnderConfigurationLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PanelUnderConfigurationLabel.setForeground(new java.awt.Color(255, 0, 0));

        UpgradeCheckBox.setSelected(true);
        UpgradeCheckBox.setText("Aggiorna pannello");
        UpgradeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpgradeCheckBoxActionPerformed(evt);
            }
        });

        ButtonProgPanel.setBackground(new java.awt.Color(204, 204, 204));
        ButtonProgPanel.setText("Programma Pannello");
        ButtonProgPanel.setEnabled(false);
        ButtonProgPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonProgPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelUserFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextFieldFolderFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(changeFolderButton))
                    .addComponent(LabelFilesFound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CheckBoxPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelUnderConfigurationLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ConfigureAllCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(UpgradeCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonProgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldFolderFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelUserFile1)
                    .addComponent(changeFolderButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelFilesFound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConfigureAllCheckBox)
                .addGap(14, 14, 14)
                .addComponent(PanelUnderConfigurationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonProgPanel)
                    .addComponent(UpgradeCheckBox))
                .addGap(25, 25, 25))
        );

        UpgradeCheckBox.getAccessibleContext().setAccessibleName("UpgradeChekBox");

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//GEN-FIRST:event_ButtonProgPanelActionPerformed
 private void ButtonProgPanelActionPerformed(final java.awt.event.ActionEvent evt) {
        final Thread prog_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int configRemaining = usedPanels.size();
                String nextname = null;
                String nextsurname = null;
                changeFolderButton.setEnabled(false);
                TextAreaAll.setText(null);
                for (int j = 0; j < CheckBoxPanel.getComponentCount(); ++j) {
                    try {
                        final JCheckBox checkBoxToDisable = (JCheckBox)CheckBoxPanel.getComponent(j);
                        checkBoxToDisable.setEnabled(false);
                        if (checkBoxToDisable.getForeground() != Color.BLUE) {
                            checkBoxToDisable.setForeground(Color.gray);
                        }
                        if (!checkBoxToDisable.isSelected() && !checkBoxToDisable.getFont().isBold()) {
                            --configRemaining;
                        }
                    }
                    catch (Exception ex2) {}
                }
                ConfigureAllCheckBox.setEnabled(false);
                for (int i = 0; i < CheckBoxPanel.getComponentCount(); ++i) {
                    final JCheckBox checkBox = (JCheckBox)CheckBoxPanel.getComponent(i);
                    if (checkBox.getFont().isBold()) {
                        panelLabelText = checkBox.getText().toString();
                    }
                    else if (checkBox.isSelected() && !checkBox.getFont().isBold()) {
                        try {
                            final String surname = checkBox.getText().split(" ")[0];
                            final String name = checkBox.getText().split(" ")[1];
                            if (configRemaining != 0) {
                                --configRemaining;
                                for (int k = i + 1; k < CheckBoxPanel.getComponentCount(); ++k) {
                                    if (!CheckBoxPanel.getComponent(k).getFont().isBold()) {
                                        final JCheckBox nextcheckBox = (JCheckBox)CheckBoxPanel.getComponent(k);
                                        if (nextcheckBox.isSelected() && !nextcheckBox.getForeground().equals(Color.BLUE)) {
                                            nextsurname = nextcheckBox.getText().split(" ")[0];
                                            nextname = nextcheckBox.getText().split(" ")[1];
                                            String key = nextsurname+" "+nextname;
                                            break;
                                        }
                                    } else {
                                        
                                    }
                                }
                            }
                            else {
                                nextname = null;
                                nextsurname = null;
                            }
                            retprogramPanel = programPanel(name, surname, configRemaining, checkBox, nextname, nextsurname);
                            if (retprogramPanel == 0) {
                                write("tipo di pannello errato");
                                break;
                            }
                        }
                        catch (FileNotFoundException e) {
                            try {
                                Toolkit.getDefaultToolkit().beep();
                                JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Impossibile trovare il file", 0);
                                log.write("Configuration aborted by the program, file not found:"+e.getMessage());
                                write("Configurazione cancellata dal programma, impossibile trovare il file: " + e.getMessage());
                                break;
                            } catch (IOException ex) {
                                Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        catch (WrongPanelTypeException e2) {
                            try {
                                write("");
                                write(e2.getMessage());
                                break;
                            } catch (IOException ex) {
                                Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        catch (ConfigurationCancelledByProgramException e3) {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(new JFrame(), e3.getMessage(), "Configurazione cancellata dal programma", 0);
                            break;
                        }
                        catch (ConfigurationCancelledByOperatorException e4) {
                            try {
                                write("");
                                write(e4.getMessage());
                                break;
                            } catch (IOException ex) {
                                Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        catch (Exception ex) {
                            Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                for (int j = 0; j < CheckBoxPanel.getComponentCount(); ++j) {
                    if (CheckBoxPanel.getComponent(j) instanceof JCheckBox) {
                        final JCheckBox checkBoxToEnable = (JCheckBox)CheckBoxPanel.getComponent(j);
                        checkBoxToEnable.setEnabled(true);
                        if (checkBoxToEnable.isSelected()) {
                            if (checkBoxToEnable.getForeground() == Color.blue) {
                                checkBoxToEnable.setSelected(false);
                            }
                            if (checkBoxToEnable.getForeground() == Color.gray) {
                                checkBoxToEnable.setForeground(Color.black);
                                ButtonProgPanel.setEnabled(true);
                                changeFolderButton.setEnabled(true);
                            }
                            if (checkBoxToEnable.getForeground() == Color.orange) {
                                checkBoxToEnable.setForeground(Color.black);
                                ButtonProgPanel.setEnabled(true);
                                changeFolderButton.setEnabled(true);
                            }
                        }
                        else if (checkBoxToEnable.getForeground() == Color.gray) {
                            checkBoxToEnable.setForeground(Color.black);
                        }
                    }
                }
                ConfigureAllCheckBox.setEnabled(true);
                PanelUnderConfigurationLabel.setText("");
                UpgradeCheckBox.setEnabled(true);
            }
        });
        prog_thread.start();
    }
//GEN-LAST:event_ButtonProgPanelActionPerformed

    private void UpgradeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpgradeCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UpgradeCheckBoxActionPerformed

    private void ConfigureAllCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ConfigureAllCheckBoxItemStateChanged

        if(ConfigureAllCheckBox.isSelected())
        {
            for( int j=0; j<CheckBoxPanel.getComponentCount(); j++ ) {
                try{
                    JCheckBox checkBoxToSelect = (JCheckBox)CheckBoxPanel.getComponent( j );
                    checkBoxToSelect.setSelected(true);
                } catch (Exception e){
                    //nothing
                }
            }
        } else {
            for( int j=0; j<CheckBoxPanel.getComponentCount(); j++ ) {
                try{
                    JCheckBox checkBoxToDeselect = (JCheckBox)CheckBoxPanel.getComponent( j );
                    checkBoxToDeselect.setSelected(false);
                } catch (Exception e){
                    //nothing
                }
            }
        }

    }//GEN-LAST:event_ConfigureAllCheckBoxItemStateChanged

    private void changeFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFolderButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(this.TextFieldFolderFiles.getText()));
        chooser.setDialogTitle("Seleziona la cartella con tutti i files di configurazione");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File yourFolder = chooser.getSelectedFile();
            if (yourFolder.isDirectory()){
                this.TextFieldFolderFiles.setText(yourFolder.getAbsolutePath());
                try {
                    checkFolderFileAndPopulateCheckBox();
                } catch (IOException ex) {
                    Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Devi scegliere una cartella, non un file!", "Errore" ,JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_changeFolderButtonActionPerformed
    
    /**
     * *************************************************************************
     * PRIVATE METHODS
     **************************************************************************
     */
    /**
     * *************************************************************************
     * extractLinePwd
     *
     * Description Extracts last line of the passed file
     **************************************************************************
     */
    private String extractLine(String file_path) throws FileNotFoundException, IOException, ParseException {
        String lastLine = null;
        String currentLine;
        
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        
        while ((currentLine = br.readLine()) != null) {
            lastLine = currentLine;
        }
        br.close();
        
        lastLine = lastLine.replace("$", "\\$");
        
        return lastLine;
        
    }
    
        /**
     * *************************************************************************
     * extractPanelType
     *
     * Description Extracts the panel type from the first line of the file
     **************************************************************************
     */
    private String extractPanelType(String file_path) throws FileNotFoundException, IOException, ParseException {
        String firstLine = null;
        
        file_path = decryptFile(file_path, pwd);
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        firstLine = br.readLine();
        br.close();       
        firstLine = firstLine.replace("# ", "");
        cancelFile(file_path);
        return firstLine;
        
    }
    
    
    /**
     * *************************************************************************
     * extractPwd
     *
     * Description Extracts the password from the last word of the passed string
     **************************************************************************
     */
    private String extractLastWord(String str_line) throws FileNotFoundException, IOException, ParseException {
        String pwd = null;
        pwd = str_line.split("=")[str_line.split("=").length-1];
        pwd = pwd.replace("\"", "");
        pwd = pwd.replace("\\$", "$");

        return pwd;
    }
    
    /**
     * *************************************************************************
     * write
     *
     * Description
     *
     **************************************************************************
     */
    private void write(String str_line) throws IOException, ParseException {
        try {
            sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(new Runnable() {
            public void run() {
                 SwingUtilities.invokeLater(new Runnable() {
                     public void run() {
                         TextAreaAll.append(str_line + "\n");
                     }
                 });
            }
        }).start();
        
       
    }
    
    /**
     * *************************************************************************
     * programPanel
     *
     * Description Main program
     **************************************************************************
     */
    
    private int programPanel(String name, String surname, int configRemaining, JCheckBox checkBox, String nextname, String nextsurname) throws IOException, ParseException, UnknownHostException, InterruptedException, JSchException, SftpException, ConfigurationCancelledByOperatorException, ConfigurationCancelledByProgramException, WrongPanelTypeException {
        // Disable Button
        boolean retry = true;
        
        if(checkBox.getFont().isBold()){
            panelLabelText = checkBox.getText().toString();
            return 1;
        }
        
        ButtonProgPanel.setEnabled(false);
        
        
        UpgradeCheckBox.setEnabled(false);
        
        PanelUnderConfigurationLabel.setText("Sto configurando il pannello "+panelLabelText+" di " + surname + " " + name);
											 

        checkBox.setForeground(Color.orange);
					  
        
        log.addSpace();
        log.write("START PROGRAMMING");
        log.addSpace();
        write("Versione: "+version+"\n");
        write("Sto configurando il pannello "+panelLabelText+" di " + surname + " " + name);
        write("\nINIZIO PROGRAMMAZIONE\n\n");
		
        log.write("Ping Panel "+ IP_DEF);
        write("Ping @ " + IP_DEF + " attendere prego...");
        while(waitOrFinish != 1){
            if (!eth.ping(IP_DEF)) {
                waitOrFinish = pingNotSuccessful();
                
                if(waitOrFinish == 1){
                    UpgradeCheckBox.setEnabled(true);
                    ButtonProgPanel.setEnabled(true);
                    waitOrFinish = 0;
                    log.write("Configuration aborted by the operator, the panel was not responding.");
                    write("Configurazione pannello cancellata dall'operatore");
                    throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
                }
            } else {
                waitOrFinish = 1;
            }
        }
        waitOrFinish = 0;
        log.write("Ping successful");
        write("Pannello raggiungibile");  
		
        /**
         * *****************************************************************
         * ASK NAME TO PANEL
         ******************************************************************
         */
        
        log.addSpace();
        log.write("Identification of panel model");
				  
        write("\nIdentificazione modello del pannello");
		
        retry = true;
        while(retry == true){
            try{
                panelName = SSHAskPanelName();
                 
                log.addSpace();
                log.write("Panel name:" + panelName);
                write("Modello: " + panelName+"\n");
                
                retry = false;
                               
                if(!panelName.equals(panelLabelText) && !panelLabelText.equals("any") && !((panelName.equals("SXT 5 ac")) && (panelLabelText.equals("SXT Lite5 ac")))){
                    log.write("Configuration aborted: wrong panel type, expected:"+panelLabelText+", actual panel:"+panelName);
                    throw new ConfigurationCancelledByProgramException("Configurazione annullata a causa di pannello errato!\nPannello atteso: "+panelLabelText+"\nPannello collegato: "+panelName);
                }
                
            }catch (ConfigurationCancelledByProgramException e3) {
               throw new ConfigurationCancelledByProgramException(e3.getMessage());
            }
            catch(Exception e){
                log.addSpace();
                log.write("Exception raised in SSH connection: " + e.getClass().getCanonicalName());
                write("Problema con la comunicazione con il pannello");

                retry = askAboutCommunicationProblem();
                if(retry == false){
                     log.write("Configuration aborted by the operator, the panel was not responding.");
                    throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
                } else {
                    log.addSpace();
                    log.write("Operator decided to retry");
                    sleep(5000);
                }
            }
        }
        
        /**
         * *****************************************************************
         * SET FILE PATHS
         ******************************************************************
         */
        
        String userPath = TextFieldFolderFiles.getText() + sep +surname + "_" + name + ".sfsc";
        
        String userGraphPath = null;
        
        if ( panelName.contains("ac") && !panelName.contains("SXTsq") && !panelName.contains("LHG")) {
            userGraphPath = TextFieldFolderFiles.getText() + sep+ "utente_ac.json";
        } else{
            userGraphPath = TextFieldFolderFiles.getText() + sep+ "utente.json";
        }
              
        /**
         * *****************************************************************
         * CHECK IF FILES EXISTS
         ******************************************************************
         */
        log.addSpace();
        log.write("Checks if files exists");
        write("Controllo se i file esistono:");
        
        // User file
        File file_user = new File(userPath);
        if (file_user.exists() && !file_user.isDirectory()) {
            log.write("User: " + surname + " " + name + " file's exist");
            write("File utente " + surname + " " + name + " esiste");

        } else {
            log.write("User: " + surname + " " + name + " file's NOT found");
            write("File utente " + surname + " " + name + " NON trovato");
   
            ButtonProgPanel.setEnabled(true);
            UpgradeCheckBox.setEnabled(true);
            throw new FileNotFoundException(userPath);
        }
        
        // User Graph file
        File file_userGraph = new File(userGraphPath);
        if (file_userGraph.exists() && !file_userGraph.isDirectory()) {
            log.write("User Graph file exist");
            write("File per interfaccia grafica esiste");

        } else {
            log.write("User Graph file NOT found");
            write("File per interfaccia grafica NON trovato");

            UpgradeCheckBox.setEnabled(true);
            ButtonProgPanel.setEnabled(true);
            throw new FileNotFoundException(userGraphPath);
        }
        
        String upgradePath = "";
       
        if(UpgradeCheckBox.isSelected()){
            /**
            * *****************************************************************
            * ASK ARCHITECTURE TO PANEL
            ******************************************************************
            */
          
           log.addSpace();
           log.write("Identification of panel architecture");
           write("\nIdentificazione architettura del pannello");

           retry = true;
           while(retry == true){

               panelArchitecture = SSHAskPanelArchitectureName();

               log.addSpace();
               log.write("Panel architecture:" + panelArchitecture);
               write("Architettura: " + panelArchitecture+"\n");

               retry = false;
               File[] fileupgrade;

               switch (panelArchitecture){
                   case "mipsbe":
                       fileupgrade = listFilesMatching(new File(TextFieldFolderFiles.getText()), "upgrade_(.+)_standard.npk");
                       if(fileupgrade.length==0){
                           log.write("Upgrade file NOT found");
                           write("File aggiornamento NON trovato");

                           UpgradeCheckBox.setEnabled(true);
                           ButtonProgPanel.setEnabled(true);
                           throw new FileNotFoundException(upgradePath);
                       }
                       write("File aggiornamento esiste");
                       upgradePath=fileupgrade[0].getPath();
                       filePanelVersion = fileupgrade[0].getName().split("_")[1];
                       break;
                   case "arm":
                       fileupgrade = listFilesMatching(new File(TextFieldFolderFiles.getText()), "upgrade_(.+)_ac.npk");
                       if(fileupgrade.length==0){
                           log.write("Upgrade file NOT found");
                           write("File aggiornamento NON trovato");

                           UpgradeCheckBox.setEnabled(true);
                           ButtonProgPanel.setEnabled(true);
                           throw new FileNotFoundException(upgradePath);
                       }
                       write("File aggiornamento esiste");
                       upgradePath=fileupgrade[0].getPath();
                       filePanelVersion = fileupgrade[0].getName().split("_")[1];
                       break;
                   case "smips":
                       fileupgrade = listFilesMatching(new File(TextFieldFolderFiles.getText()), "upgrade_(.+)_hap.npk");
                       if(fileupgrade.length==0){
                           log.write("Upgrade file NOT found");
                           write("File aggiornamento NON trovato");

                           UpgradeCheckBox.setEnabled(true);
                           ButtonProgPanel.setEnabled(true);
                           throw new FileNotFoundException(upgradePath);
                       }
                       write("File aggiornamento esiste");
                       upgradePath=fileupgrade[0].getPath();
                       filePanelVersion = fileupgrade[0].getName().split("_")[1];
                       break;
                   default:
                       log.write("Not supported architecture");
                       break;
               }
           }

        }
        
               
        /**
         * *****************************************************************
         * SET STATIC IP TO LOCAL MACHINE
         ******************************************************************
         */
        log.addSpace();
//log.write("SET STATIC IP");
//write("Set static IP address");

//write("Imposto indirizzo IP al PC");

//eth.setStaticIp( ComboBoxSelNetwork.getSelectedItem().toString() );
//log.write("Set Ethernet Address");

//log.write("Ping Panel");
//write("Ping @ " + IP_DEF);
//
//while(waitOrFinish != 1){
//    if (!eth.ping(IP_DEF)) {
//
//        waitOrFinish = pingNotSuccessful();
//
//        if(waitOrFinish == 1){
//            UpgradeCheckBox.setEnabled(true);
//            ButtonProgPanel.setEnabled(true);
//            waitOrFinish = 0;
//            log.write("configuration stopped");
//            write("Configurazione pannello cancellata dall'operatore");
//            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
//        }
//    } else {
//        waitOrFinish = 1;
//    }
//}
//waitOrFinish = 0;
//log.write("Ping successful");
//write("Pannello raggiungibile");

/**
 * *****************************************************************
 * SEND FILES
 ******************************************************************
 */
// Initialize Comm_SCP

retry = true;
while(retry == true){
    try{
        log.write("Upgrade file:"+upgradePath);
        log.write("User file:"+userPath);
        log.write("JSON file:"+userGraphPath);
        userPath = sendFilesWithSCP(upgradePath, userPath, userGraphPath);
        retry = false;
    } catch(Exception e) {
        log.addSpace();
        log.write("Exception raised in SCP connection: " + e.getClass().getCanonicalName());
        write("Problema con la comunicazione con il pannello");
        retry = askAboutCommunicationProblem();
        if(retry == false){
            log.write("Configuration aborted by the operator, the panel was not responding.");
            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
        } else {
            log.addSpace();
            log.write("Operator decided to retry");
            sleep(5000);
        }
    }
}

sleep(1000);

/**
 * *****************************************************************
 * REBOOT
 ******************************************************************
 */
if (UpgradeCheckBox.isSelected()) {
    
    log.addSpace();
    log.write("UPGRADING PANEL");
    write("AGGIORNO PANNELLO");
   
    retry = true;
    while(retry == true){
        try{
            SSHOpenAndReboot();
            retry = false;
        } catch(Exception e){
            log.addSpace();
            log.write("Exception raised in SSH connection: " + e.getClass().getCanonicalName());
            write("Problema con la comunicazione con il pannello");
            retry = askAboutCommunicationProblem();
            if(retry == false){
                log.write("Configuration aborted by the operator, the panel was not responding.");
                throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
            } else {
                log.addSpace();
                log.write("Operator decided to retry");
                sleep(5000);
            }
        }
    }
    
    sleep(50000);
    
    log.write("Ping Panel");
    write("Ping @ " + IP_DEF + " attendere prego...");
    while(waitOrFinish != 1){
        if (!eth.ping(IP_DEF)) {
            
            waitOrFinish = pingNotSuccessful();
            
            if(waitOrFinish == 1){
                UpgradeCheckBox.setEnabled(true);
                ButtonProgPanel.setEnabled(true);
                waitOrFinish = 0;
                 log.write("Configuration aborted by the operator, the panel was not responding.");
                write("Configurazione pannello cancellata dall'operatore");
                throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
            }
        } else {
            waitOrFinish = 1;
        }
    }
    waitOrFinish = 0;
    log.write("Ping successful");
    write("Pannello raggiungibile");
    
    sleep(2000);
    
    log.addSpace();
    log.write("GET PANEL VERSION");
    write("\nControllo la versione aggiornata del pannello");
    
    retry = true;
    while(retry == true){
        try{
            panelVersion = SSHAskPanelVersion();
            panelVersion = panelVersion.split(" ")[0];
            log.write("panel version:"+panelVersion);
            retry = false;
        } catch(Exception e){
            log.addSpace();
            log.write("Exception raised in SSH connection: " + e.getClass().getCanonicalName());
            write("Problema con la comunicazione con il pannello");
            retry = askAboutCommunicationProblem();
            if(retry == false){
                log.write("Configuration aborted by the operator, the panel was not responding.");
                throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
            } else {
                log.addSpace();
                log.write("Operator decided to retry");
                sleep(5000);
            }
        }
    }
    
    if(!panelVersion.equals(filePanelVersion)){
        log.addSpace();
        log.write("Panel new version:" + panelVersion);
        write("Versione del pannello aggiornata: " + panelVersion+"\n");
        log.write("Configuration aborted by the program, the panel was not upgraded");
        throw new ConfigurationCancelledByProgramException("Configurazione annullata, il pannello non è stato aggiornato");
    }
    
    log.addSpace();
    log.write("Panel new version:" + panelVersion);
    write("Versione del pannello aggiornata: " + panelVersion);
    
    sleep(2000);
    
}
/**
 * *****************************************************************
 * LOAD CONFIGURATION
 ******************************************************************
 */
sleep(5000);
log.addSpace();
log.write("LOAD CONFIGURATION");
write("\nINSTALLO NUOVA CONFIGURAZIONE");

PanelUnderConfigurationLabel.setText("Sto configurando il pannello "+panelLabelText+" di " + surname + " " + name);
checkBox.setForeground(Color.orange);

String command;
log.write("Name: " + panelName);
if (panelName.contains("LHG")|| panelName.contains("SXT Lite5 ac") || panelName.contains("DISC") || panelName.contains("SXTsq")) {
   
    command = "/system reset-configuration keep-users=no no-defaults=yes skip-backup=yes run-after-reset=flash/" + surname + "_" + name + ".rsc";
    
} else {
    command = "/system reset-configuration keep-users=no no-defaults=yes skip-backup=yes run-after-reset=" + surname + "_" + name + ".rsc";
}

//String command = "/system reset-configuration keep-users=no no-defaults=yes skip-backup=yes run-after-reset="+ path + surname + "_" + name + ".rsc";
log.write("Command: " + command);

retry = true;
while(retry == true){
    try{
        SSHResetConfiguration(command);
        retry = false;
    } catch(Exception e){
        log.addSpace();
        log.write("Exception raised in SSH connection: " + e.getClass().getCanonicalName());
        write("Problema con la comunicazione con il pannello");
        retry = askAboutCommunicationProblem();
        if(retry == false){
            log.write("Configuration aborted by the operator, the panel was not responding.");
            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
        } else {
            log.addSpace();
            log.write("Operator decided to retry");
            sleep(5000);
        }
    }
}

write("Pannello raggiungibile. Riavvio il pannello");

//ssh.createConnection("admin", IP_DEF, PORT);
//ssh.connect();
//path = ssh.executeAndRead("system script run reset");

/**
 * *****************************************************************
 * SET DHCP TO LOCAL MACHINE
 ******************************************************************
 */
//log.addSpace();
//log.write("SET DHCP");
//write("");
//write("Imposto DHCP al PC");
//eth.setDHCP( ComboBoxSelNetwork.getSelectedItem().toString() );
//log.addSpace();
//log.write("Set dhcp mode on local machine");

write("");

sleep(50000);

log.write("Ping Panel");
write("Ping @ " + IP_SET + " attendere prego...");
while(waitOrFinish != 1){
    if (!eth.ping(IP_SET)) {
        
        waitOrFinish = pingNotSuccessful();
        
        if(waitOrFinish == 1){
            UpgradeCheckBox.setEnabled(true);
            ButtonProgPanel.setEnabled(true);
            waitOrFinish = 0;
            log.write("Configuration aborted by the operator, the panel was not responding.");
            write("Configurazione pannello cancellata dall'operatore");
            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
        }
    } else {
        waitOrFinish = 1;
    }
}
waitOrFinish = 0;
log.write("Ping successful");
write("Pannello raggiungibile");

/**
 * *****************************************************************
 * SET PASSWORD
 ******************************************************************
 */

sleep(10000);

log.addSpace();
log.write("SET PASSWORD");
write("");
write("FINALIZZO LA CONFIGURAZIONE");

retry = true;
while(retry == true){
    try{
        SSHSetPassword(lastLine);
        retry = false;
    } catch(Exception e){
        log.addSpace();
        log.write("Exception raised in SSH connection: " + e.getClass().getCanonicalName());
        write("Problema con la comunicazione con il pannello");
        retry = askAboutCommunicationProblem();
        if(retry == false){
            log.write("Configuration aborted by the operator, the panel was not responding.");
            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
        } else {
            log.addSpace();
            log.write("Operator decided to retry");
            sleep(5000);
        }
    }
}

sleep(5000);

log.write("Ping Panel");

write("Ping @ " + IP_SET + " attendere prego...");
while(waitOrFinish != 1){
    if (!eth.ping(IP_SET)) {
        
        waitOrFinish = pingNotSuccessful();
        
        if(waitOrFinish == 1){
            UpgradeCheckBox.setEnabled(true);
            ButtonProgPanel.setEnabled(true);
            waitOrFinish = 0;
            log.write("Configuration aborted by the operator, the panel was not responding.");
            write("Configurazione pannello cancellata dall'operatore");
            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
        }
    } else {
        waitOrFinish = 1;
    }
}
waitOrFinish = 0;
log.write("Ping successful");
write("Pannello raggiungibile");


/**
 * *****************************************************************
 * LAST REBOOT
 ******************************************************************
 */

log.addSpace();
log.write("REBOOT AFTER CONFIGURATION");
write("");
write("RIAVVIO DOPO LA CONFIGURAZIONE");

String new_pwd = extractLastWord(lastLine);
String answer = null;

//log.write("Password: " + new_pwd);

retry = true;
while(retry == true){
    try{
        answer = SSHConnectionWithPWDReboot(new_pwd);
        retry = false;
    } catch(Exception e){
        log.addSpace();
        log.write("Exception raised in SSH connection: " + e.getClass().getCanonicalName());
        write("Problema con la comunicazione con il pannello");
        retry = askAboutCommunicationProblem();
        if(retry == false){
            log.write("Configuration aborted by the operator, the panel was not responding.");
            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
        } else {
            log.addSpace();
            log.write("Operator decided to retry");
            sleep(5000);
        }
    }
}

sleep(10000);

log.write("Ping Panel");

write("Ping @ " + IP_SET + " attendere prego...");
while(waitOrFinish != 1){
    if (!eth.ping(IP_SET)) {
        
        waitOrFinish = pingNotSuccessful();
        
        if(waitOrFinish == 1){
            UpgradeCheckBox.setEnabled(true);
            ButtonProgPanel.setEnabled(true);
            waitOrFinish = 0;
            log.write("Configuration aborted by the operator, the panel was not responding.");
            write("Configurazione pannello cancellata dall'operatore");
            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
        }
    } else {
        waitOrFinish = 1;
    }
}

waitOrFinish = 0;
log.write("Ping successful");
write("Pannello raggiungibile");

/**
 * *****************************************************************
 * CHECK USERNAME AND PASSWORD
 ******************************************************************
 */
log.addSpace();
log.write("CHECK USERNAME and PASSWORD");
		  
write("\nCONTROLLO CORRETTA PROGRAMMAZIONE");

String read_identity;
String correct_identity = surname + "_" + name;

//log.write("Password: " + new_pwd);

retry = true;
while(retry == true){
    try{
        answer = SSHConnectionWithPWD(new_pwd);
        retry = false;
    } catch(Exception e){
        log.addSpace();
        log.write("Exception raised in SSH connection: " + e.getClass().getCanonicalName());
        write("Problema con la comunicazione con il pannello");
        retry = askAboutCommunicationProblem();
        if(retry == false){
            log.write("Configuration aborted by the operator, the panel was not responding.");
            throw new ConfigurationCancelledByOperatorException("Configurazione pannello cancellata dall'operatore, il pannello non rispondeva");
        } else {
            log.addSpace();
            log.write("Operator decided to retry");
            sleep(5000);
        }
    }
}



read_identity = extractLastWordAnswer(answer);

log.write("Identity: " + read_identity);

if (read_identity.equals(correct_identity)) {
    log.write("PANEL PROGRAMMED CORRECTLY");
    
    write("Pannello di " + surname + " " + name + " configurato correttamente");
    checkBox.setForeground(Color.blue);
    
    Toolkit.getDefaultToolkit().beep();
    sleep(1000);
    Toolkit.getDefaultToolkit().beep();

    if(configRemaining > 0)
    {
        List<String> optionList = new ArrayList<String>();
        String msg = null;
        if(!panelLabelText.equals(usedPanels.get(nextsurname+" "+nextname))){
            String title = "Continua con "+usedPanels.get(nextsurname+" "+nextname);
            optionList.add(title);
            msg =  "<html><b>"+panelLabelText+"</b> configurati.\n"+ "Scollegare il pannello di <b>" + surname + " " + name + "</b> e procedere con il pannello <b>"+usedPanels.get(nextsurname+" "+nextname)+"<b> di <b>"+nextsurname+" "+nextname+"</html>";
        } else {
            optionList.add("Continua");
            msg = "<html>Scollegare il pannello di <b>" + surname + " " + name + "</b>, collegare il pannello <b>"+usedPanels.get(nextsurname+" "+nextname)+"</b> di <b>" + nextsurname + " " + nextname +  "</b> e premere Continua, oppure Termina</html>";
        }
        optionList.add("Termina");
        Object[] options = optionList.toArray();
        JLabel label = new JLabel(msg);
        int n = JOptionPane.showOptionDialog(
                this,
                label,
                "Pannello configurato correttamente",
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]);
        if(n==1){
            log.write("Configuration aborted by the operator after a corret configuration.");
            throw new ConfigurationCancelledByOperatorException("Configurazione annullata dall'operatore dopo una corretta configurazione");
        }
        TextAreaAll.setText(null);
    }
    if(configRemaining == 0)
    {
        log.write("All configurations completed!");
        JOptionPane.showMessageDialog(this,
                "Configurazioni completate!",
                "Pannello configurato correttamente",
                JOptionPane.PLAIN_MESSAGE);
        PanelUnderConfigurationLabel.setText(" ");       
    }
    
    
} else {
    log.write("ERROR DURING PROGRAMMING");
    write("Errore durante programmazione. Nome e cognome non corrispondono");
}

changeFolderButton.setEnabled(true);
ButtonProgPanel.setEnabled(true);
UpgradeCheckBox.setEnabled(true);
return 1;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new SFSC_Programmazione_CPE().setVisible(true);
            } catch (IOException | ParseException | JSchException ex) {
                Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonProgPanel;
    private javax.swing.JPanel CheckBoxPanel;
    private javax.swing.JCheckBox ConfigureAllCheckBox;
    private javax.swing.JLabel LabelFilesFound;
    private javax.swing.JLabel LabelUserFile1;
    private javax.swing.JLabel PanelUnderConfigurationLabel;
    private javax.swing.JTextArea TextAreaAll;
    private javax.swing.JTextField TextFieldFolderFiles;
    private javax.swing.JCheckBox UpgradeCheckBox;
    private javax.swing.JButton changeFolderButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
    
    private int pingNotSuccessful() throws IOException, ParseException {
        int waitOrFinish = 0;
        Object[] options = {"Aspetta ancora", "Finisci"};
        log.write("Ping NOT successful");
        write("Pannello non raggiungibile");
        waitOrFinish = JOptionPane.showOptionDialog(this, "Aspettare ancora o finire la configurazione?", "Pannello non raggiungibile al momento", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return waitOrFinish;
    }
    
    private String sendFilesWithSCP(String upgradePath, String userPath, String userGraphPath) throws IOException, ParseException, JSchException, SftpException {
        this.scp = new Comm_SCP("192.168.88.1", "", "admin");
        // Opens connection with the panel
        //scp.close();
        scp.connect();
        log.addSpace();
        log.write("SEND CONFIGURATION FILES");
        write("");
        write("INVIO FILE DI CONFIGURAZIONE:");
        
        if (UpgradeCheckBox.isSelected()) {
            //decrypt upgrade file (NOT NEEDED)
            //upgradePath = decryptFile(upgradePath, pwd);
            
            // Send Upgrade File
            scp.putFile(upgradePath, 3);
            log.write("Send Upgrade file");
            write(" - File aggiornamento");
        }
        
        // Sends file for user Interface. Report error if the directory doesn't exist.
        int check = scp.putFile(userGraphPath, 1);
        if (check == 1) {
            log.write("ERROR: Directory for user file not found");
            write(" - ERRORE: Directory per il file interfaccia grafica (.json) non trovata. File NON inviato");
            UpgradeCheckBox.setEnabled(true);
            ButtonProgPanel.setEnabled(true);
            return null;
        } else {
            log.write("Send User Interface file");
            write(" - File interfaccia grafica");
        }
        
        //Decrypt User File
        userPath = decryptFile(userPath, pwd);
        
        // Sends User File.
        scp.putFile(userPath, 2);
        log.write("Send User file");
        write(" - File utente");
        // Close Connection
        scp.close();
        write("");
        
        //Extract password from the file
        lastLine = extractLine(userPath);
        cancelFile(userPath);
        return userPath;
    }
    
    private void SSHOpenAndReboot() throws JSchException, IOException, ParseException {
        ssh.createConnection("admin", IP_DEF, PORT);
        ssh.connect();
        ssh.execute("/system reboot");
        log.write("Reboot Command sent");
        write("Pannello raggiungibile. Riavvio il pannello");
        write("");
        ssh.close();
    }
    
    private String SSHAskPanelName() throws IOException, JSchException {
        String path;
        ssh.createConnection("admin", IP_DEF, PORT);
        ssh.connect();
        path = ssh.executeAndRead(":put [system resource get board-name]");
        ssh.close();
        return path;
    }
    
    private String SSHAskPanelArchitectureName() throws IOException, JSchException {
        String path;
        ssh.createConnection("admin", IP_DEF, PORT);
        ssh.connect();
        path = ssh.executeAndRead(":put [system resource get architecture-name]");
        ssh.close();
        return path;
    }
    
    private String SSHAskPanelVersion() throws IOException, JSchException {
        String path;
        ssh.createConnection("admin", IP_DEF, PORT);
        ssh.connect();
        path = ssh.executeAndRead(":put [system resource get version]");
        ssh.close();
        return path;
    }
    
    private void SSHResetConfiguration(String command) throws IOException, JSchException, ParseException {
        ssh.createConnection("admin", IP_DEF, PORT);
        ssh.connect();
        ssh.execute(command);
        ssh.close();
    }
    
    private void SSHSetPassword(String lastLine) throws IOException, JSchException {
        ssh.createConnection("admin", IP_SET, PORT);
        ssh.connect();
        ssh.execute(lastLine);
        ssh.close();
    }
    
    private String SSHConnectionWithPWD(String new_pwd) throws IOException, ParseException, JSchException {
        ssh.createConnectionWithPWD("admin", IP_SET, new_pwd, PORT);
        ssh.connect();
        write("Connessione con Username e Password nuovi");
        String answer = ssh.executeAndRead("/system identity print");
        log.write("Identity Command Sent");
        ssh.close();
        return answer;
    }
    
    private String SSHConnectionWithPWDReboot(String new_pwd) throws IOException, ParseException, JSchException {
        ssh.createConnectionWithPWD("admin", IP_SET, new_pwd, PORT);
        ssh.connect();
        String answer = ssh.executeAndRead("/system reboot");
        log.write("Identity Command Sent");
        ssh.close();
        return answer;
    }    
    
    
    private String decryptFile(String filePath, char[] pwd) {
        String ret=null;
        try {
            InputStream encrypted = new FileInputStream(filePath);
            File encryptedFile = new File(filePath);
            String fileNameWithoutExt =FilenameUtils.removeExtension(encryptedFile.getName());
            OutputStream decrypted;
            
            File decryptedFile = new File( encryptedFile.getParent()+ sep +fileNameWithoutExt+".rsc");
            decrypted = new FileOutputStream(decryptedFile);
            ret = decryptedFile.getAbsolutePath();
            
            AES.decrypt(pwd, encrypted, decrypted);
            encrypted.close();
            decrypted.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AES.InvalidPasswordException | AES.InvalidAESStreamException | AES.StrongEncryptionNotAvailableException | IOException ex) {
            Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    private void cancelFile(String path) throws IOException {
        Path p = Paths.get(path);
        Files.delete(p);
    }
    
    private void checkFolderFileAndPopulateCheckBox() throws IOException {
        String directory = TextFieldFolderFiles.getText();
        Path directoryPath = Paths.get(directory);
        DirectoryStream<Path> ds = null;
        if(Files.exists(directoryPath)){
            ds = Files.newDirectoryStream(directoryPath);
        } else {
            this.LabelFilesFound.setText("La cartella encrypted non esiste, specificare un altro path!");
            return;
        }
        boolean atLeastOneFileFound = false;
        usedPanels = new LinkedHashMap<String, String>();
        CheckBoxPanel.removeAll();
        String panelType, completename;
        
        for(Path filePath : ds)
        {
            if(!Files.isDirectory(filePath)){
                String fileNameExtension = filePath.getFileName().toString();
                if(fileNameExtension.contains(".sfsc")&& fileNameExtension.contains("_")){
                    String fileName = FilenameUtils.removeExtension(fileNameExtension);
                    String surname = fileName.split("_")[0];
                    String name = fileName.split("_")[1];
                    completename = surname+" "+name;
                    try {                    
                        panelType = extractPanelType(filePath.toString());
                        usedPanels.put(completename, panelType);                        
                    } catch (FileNotFoundException | ParseException ex) {
                        Logger.getLogger(SFSC_Programmazione_CPE.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }    
        }
        
       usedPanels = sortMapByValuesWithDuplicates(usedPanels);
       Iterator it = usedPanels.entrySet().iterator();
       String previousPanelType = "";
       int column = 0;
       int row = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            JCheckBox cb = new JCheckBox(pair.getKey().toString());
            cb.setForeground(Color.BLACK);
            cb.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(cb.isSelected() && !ButtonProgPanel.isEnabled()){
                        ButtonProgPanel.setEnabled(true);
                    }
                    if(!cb.isSelected() && ButtonProgPanel.isEnabled()){
                        boolean atLeastOneIsSelected = false;
                        for( int i=0; i<CheckBoxPanel.getComponentCount(); i++ ) {
                            JCheckBox checkBox = (JCheckBox)CheckBoxPanel.getComponent( i );
                            if( checkBox.isSelected() ) {
                                atLeastOneIsSelected = true;
                            }
                        }
                        if(!atLeastOneIsSelected){
                             ButtonProgPanel.setEnabled(false);
                        }
                    }
                }
            });
            
            if(!previousPanelType.equals(pair.getValue().toString()))
            {
                if(row!=0){
                    row++;
                }
                previousPanelType = pair.getValue().toString();
                JCheckBox typecb = new JCheckBox(pair.getValue().toString());
                typecb.setForeground(Color.BLACK);
                Font font = new Font("Tahoma", Font.BOLD,15);
                typecb.setFont(font);
                constraints.weightx = 1.0;
                constraints.gridwidth = 10;
                constraints.gridx = 0;
                constraints.gridy = row;
                constraints.ipady = 10;
                constraints.anchor =  GridBagConstraints.WEST;
                
                typecb.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        boolean aftertypecb=false;
                        if(typecb.isSelected()){
                            for(int j=0; j<CheckBoxPanel.getComponentCount(); j++) {
                                try{
                                    JCheckBox checkBoxToSelect = (JCheckBox)CheckBoxPanel.getComponent( j );
                                    if(!checkBoxToSelect.getText().equals(typecb.getText())&&checkBoxToSelect.getFont().isBold() && aftertypecb){
                                        break;
                                    }
                                    if(checkBoxToSelect.getText().equals(typecb.getText())){
                                       aftertypecb=true; 
                                    }
                                    if(aftertypecb==true){
                                        checkBoxToSelect.setSelected(true);
                                    }
                                } catch (Exception ex){
                                //nothing
                                }
                            }
                        } else {
                            for( int j=0; j<CheckBoxPanel.getComponentCount(); j++ ) {
                                try{
                                   JCheckBox checkBoxToSelect = (JCheckBox)CheckBoxPanel.getComponent( j );
                                    if(!checkBoxToSelect.getText().equals(typecb.getText())&&checkBoxToSelect.getFont().isBold() && aftertypecb){
                                        break;
                                    }
                                    if(checkBoxToSelect.getText().equals(typecb.getText())){
                                       aftertypecb=true; 
                                    }
                                    if(aftertypecb==true){
                                        checkBoxToSelect.setSelected(false);
                                    }
                                } catch (Exception ex){
                                    //nothing
                                }
                            }
                        }
                    }
                });               
                           
                CheckBoxPanel.add(typecb, constraints);
                
                row++;
                
                
                
                column = 0;
            } 
            constraints = new GridBagConstraints();
            constraints.weightx = 0.2;
            constraints.gridx = column;
            constraints.gridy = row;
            constraints.ipady = 10;
            constraints.anchor =  GridBagConstraints.WEST;
            this.CheckBoxPanel.add(cb, constraints);
            if(column == 9){
                column=0;
                row++;
            } else {
                column++;
            }
            
            
            CheckBoxPanel.revalidate();
            CheckBoxPanel.repaint();
            atLeastOneFileFound = true;
        }
        
        if(atLeastOneFileFound){
            this.LabelFilesFound.setText("Files di configurazione trovati:");
            UpgradeCheckBox.setVisible(true);
            ButtonProgPanel.setVisible(true);
            jScrollPane1.setVisible(true);
            ConfigureAllCheckBox.setVisible(true);
           // this.pack();
        } else{
            this.LabelFilesFound.setText("Non sono stati trovati files di configurazione!");
            UpgradeCheckBox.setVisible(false);
            ButtonProgPanel.setVisible(false);
            jScrollPane1.setVisible(false);
            ConfigureAllCheckBox.setVisible(false);
            CheckBoxPanel.revalidate();
            CheckBoxPanel.repaint();
            //this.pack();
        }
    }
    
    private String extractLastWordAnswer(String answer) {
        return answer.split(": ")[answer.split(": ").length-1];
    }
    
    private boolean askAboutCommunicationProblem() throws IOException, ParseException {
        int retry = 0;
        Object[] options = {"Riprova", "Interrompi la configurazione"};
        retry = JOptionPane.showOptionDialog(this, "Problema nella comunicazione con il pannello, scegliere se riprovare oppure spegnere e riaccendere il pannello." + System.lineSeparator() + "Se il problema persiste interrompere la configurazione e resettare il pannello manualmente attraverso il bottone.", "Problema di comunicazione", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if(retry == 0)
            return true;
        return false;
    }   

   private LinkedHashMap sortMapByValuesWithDuplicates(Map passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap sortedMap = new LinkedHashMap();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();

                if (comp1.equals(comp2)) {
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put((String) key, (String) val);
                    break;
                }
            }
        }
        return sortedMap;
    }
   
    public boolean isWindows() {

        return (OS.indexOf("win") >= 0);
    }

    public boolean isMac() {

	return (OS.indexOf("mac") >= 0);
    }

    public boolean isUnix() {

	return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

    public static File[] listFilesMatching(File root, String regex) {
        if(!root.isDirectory()) {
            throw new IllegalArgumentException(root+" is no directory.");
        }
        final Pattern p = Pattern.compile(regex); // careful: could also throw an exception!
        return root.listFiles(new FileFilter(){
            @Override
            public boolean accept(File file) {
                return p.matcher(file.getName()).matches();
            }
        });
    }
}


