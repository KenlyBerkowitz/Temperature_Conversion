import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempGui {
    //declare variable string variables
    private String resultStr;
    private String lDegStr;
    private String rDegStr;
    private float temper;

    //GUI JFrame declaration
    private JFrame frame;
    private JPanel panel;
    private JLabel tempConverterLabel;
    private JRadioButton fahrToCel;
    private JRadioButton celToFahr;
    private JLabel unitLabel;
    private JTextField unitTextField;
    private JButton convert;
    private JPanel displayPanel;
    private JLabel result;
    private JLabel lDeg;
    private JLabel rDeg;
    private JButton done;

    
    public TempGui() {
        //creating new frame
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                 //closes when system close (x)
        frame.setSize(400, 250);
     
        //creating mainPanel for the inputs and selectors
        panel = new JPanel();
        tempConverterLabel = new JLabel("Temperature Converter");             //creates a label
        fahrToCel = new JRadioButton("Fahrenheit to celcius", true);          //creates a radio button for F˚ to C˚
        celToFahr = new JRadioButton("Celcius to fahrenheit");                //creates a radio button for C˚ to F˚
        unitLabel = new JLabel("Units: ");                                    //creates label to indicate input
        unitTextField = new JTextField(10);                                   //creates a text box to input units
        convert = new JButton("Convert");                                     //creates button to run conversion
        panel.setLayout(null);

        //create button group which will de-select other button when another is selected 
        ButtonGroup radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(fahrToCel);
        radioBtnGroup.add(celToFahr);
        
        //setting postions for mainPanel components
        tempConverterLabel.setBounds(0, 25, 400, 25);
        tempConverterLabel.setHorizontalAlignment(JLabel.CENTER);             //centers the label
        tempConverterLabel.setFont(new Font("arial", Font.BOLD, 20));
        panel.add(tempConverterLabel);

        fahrToCel.setBounds(20, 75, 180, 25);
        panel.add(fahrToCel);

        celToFahr.setBounds(220, 75, 180, 25);
        panel.add(celToFahr);

        unitLabel.setBounds(30, 125, 70, 25);
        panel.add(unitLabel);

        unitTextField.setBounds(72, 125, 180, 25);
        unitTextField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        panel.add(unitTextField);

        convert.setBounds(130, 175, 140, 25);
        panel.add(convert);

        //creating displayPanel for the inputs and selectors
        displayPanel = new JPanel();
        result = new JLabel(resultStr);
        lDeg = new JLabel(lDegStr);
        rDeg = new JLabel(rDegStr);
        done = new JButton("Done");
        displayPanel.setLayout(null);

        //style displayPanel
        result.setBounds(0, 25, 400, 25);
        result.setHorizontalAlignment(JLabel.CENTER);
        result.setFont(new Font("arial", Font.BOLD, 22));
        displayPanel.add(result);

        lDeg.setBounds(75, 75, 150, 25);
        lDeg.setFont(new Font("arial", Font.BOLD, 20));
        displayPanel.add(lDeg);

        rDeg.setBounds(240, 75, 150, 25);
        rDeg.setFont(new Font("arial", Font.BOLD, 20));
        displayPanel.add(rDeg);

        done.setBounds(130, 150, 140, 25);
        displayPanel.add(done);

        //add action listeners to buttons
        //action listener for convert button
        convert.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                runConvertBtn();
            }
        } );

        //action listener fo done button
        done.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e2) { 
              runDoneBtn();
            } 
        } );

        frame.add(panel);
        frame.setVisible(true);
    }

    private void setResultStr() {
        if (fahrToCel.isSelected()) {
            resultStr = "Conversion of Fahrenheit to Celcius: ";  
        } 
        else {
            resultStr = "Conversion of Celcius to Fahrenheit: ";
        }
    }

    /*setLDegStr() will create a string that is 
     *adjustable and will be determined if fahrToCel
     *radio button is selected or not
     */
    public void setLDegStr(Temperature tmp) {
        if (fahrToCel.isSelected()) {
            lDegStr = "F˚: " + String.format("%.2f", tmp.getFahrenheit());  //String.format formats number and returns string 
            rDegStr = "C˚: " + String.format("%.2f", tmp.getCelcius());
        } 
        else {
            lDegStr = "C˚: " + String.format("%.2f", tmp.getCelcius());
            rDegStr = "F˚: " + String.format("%.2f", tmp.getFahrenheit());
        }
    }

    //function to run the convert button
    private int runConvertBtn() {
        Temperature temperature = new Temperature();
       
        try {
            String temp1 = unitTextField.getText();
            temper = Float.parseFloat(temp1);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Make a valid input", "Error", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
               
            if (fahrToCel.isSelected()) {
                temperature.setFahrenheit(temper);
                setResultStr();
                setLDegStr(temperature);
                result.setText(resultStr);
                lDeg.setText(lDegStr);
                rDeg.setText(rDegStr);
                frame.remove(panel);
                frame.add(displayPanel);
                frame.revalidate();
                frame.repaint();
            }
            else if (celToFahr.isSelected()) {
                temperature.setCelcius(temper);
                setResultStr();
                setLDegStr(temperature);
                result.setText(resultStr);
                lDeg.setText(lDegStr);
                rDeg.setText(rDegStr);
                frame.remove(panel);
                frame.add(displayPanel);
                frame.revalidate();
                frame.repaint();
            }
             return 0;
    }

    private void runDoneBtn() {
        unitTextField.setText("");            //clears the textField box 
        fahrToCel.setSelected(true);          //resets fahrToCel radiobutton to be selected
        frame.remove(displayPanel);
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}