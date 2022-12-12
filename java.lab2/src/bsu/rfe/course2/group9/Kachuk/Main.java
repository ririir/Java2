package bsu.rfe.course2.group9.Kachuk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;


public class Main {

    @SuppressWarnings("serial")
    public static class MainFrame extends JFrame{
        private static final int WIDTH = 700;
        private static final int HEIGHT = 500;

        private JTextField textFieldX;
        private JTextField textFieldY;
        private JTextField textFieldZ;

        private JTextField textFieldResult;

        private int formulaId = 1;
        private ButtonGroup radioButtons = new ButtonGroup();
        private Box hboxFormulaType = Box.createHorizontalBox();


        private int memoryID = 0;
        private ButtonGroup memoryButtons = new ButtonGroup();
        private Box Memorybox = Box.createHorizontalBox();

        private Vector<JTextField> TextMemory = new Vector<JTextField>();


        public Double calculate1(Double x, Double y, Double z) {
            return Math.pow((Math.pow(Math.sin(y) + Math.pow(y, 2) + Math.exp(Math.cos(y)) , 2)) + (Math.pow(Math.log(Math.pow(z, 2)) + Math.sin(Math.PI * Math.pow(x, 2)), 3)), 1/2);
        }

        public Double calculate2(Double x, Double y, Double z) {
            return x * ((Math.pow(Math.cos(Math.pow(y, 2)), 3)) / (Math.pow(z, 1 / x)));

        }


        private void addFormulaRadioButton(String buttonName, final int formulaId) {            //method-helper
            JRadioButton button = new JRadioButton(buttonName);
            button.addActionListener(new ActionListener() {                         //обработчик
                public void actionPerformed(ActionEvent ev) {

                    MainFrame.this.formulaId = formulaId;
                }
            });
            radioButtons.add(button);
            hboxFormulaType.add(button);
        }

        private void addMemoryRadioButton(String buttonname, final int ID){
            JRadioButton button = new JRadioButton(buttonname);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {

                    MainFrame.this.memoryID = ID;
                }
            });
            memoryButtons.add(button);
            Memorybox.add(button);
        }


        public MainFrame() throws IOException {
            super("Formula evaluation");
            setSize(WIDTH, HEIGHT);
            Toolkit kit = Toolkit.getDefaultToolkit();

            setLocation((kit.getScreenSize().width - WIDTH)/2,
                    (kit.getScreenSize().height - HEIGHT)/2);

            hboxFormulaType.add(Box.createHorizontalGlue());
            addFormulaRadioButton("Formula 1", 1);
            addFormulaRadioButton("Formula 2", 2);
            radioButtons.setSelected(
                    radioButtons.getElements().nextElement().getModel(), true);
            hboxFormulaType.add(Box.createHorizontalGlue());
            hboxFormulaType.setBorder(
                    BorderFactory.createLineBorder(Color.YELLOW));

            JLabel labelForX = new JLabel("X:");
            textFieldX = new JTextField("0", 10);
            textFieldX.setMaximumSize(textFieldX.getPreferredSize());
            JLabel labelForY = new JLabel("Y:");
            textFieldY = new JTextField("0", 10);
            textFieldY.setMaximumSize(textFieldY.getPreferredSize());
            JLabel labelForZ = new JLabel("Z:");
            textFieldZ = new JTextField("0", 10);
            textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

            Box hboxVariables = Box.createHorizontalBox();
            hboxVariables.setBorder(
                    BorderFactory.createLineBorder(Color.RED));

            hboxVariables.add(Box.createHorizontalGlue());
            hboxVariables.add(labelForX);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldX);
            hboxVariables.add(Box.createHorizontalGlue());
            hboxVariables.add(labelForY);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldY);
            hboxVariables.add(Box.createHorizontalGlue());
            hboxVariables.add(labelForZ);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldZ);
            hboxVariables.add(Box.createHorizontalGlue());

            JLabel labelForResult = new JLabel("Result:");
            textFieldResult = new JTextField("0", 20);
            textFieldResult.setMaximumSize(
                    textFieldResult.getPreferredSize());
            Box hboxResult = Box.createHorizontalBox();
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.add(labelForResult);
            hboxResult.add(Box.createHorizontalStrut(20));
            hboxResult.add(textFieldResult);
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

            JButton buttonCalc = new JButton("Calculate");
            buttonCalc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    try {
                        Double x = Double.parseDouble(textFieldX.getText());
                        Double y = Double.parseDouble(textFieldY.getText());
                        Double z = Double.parseDouble(textFieldZ.getText());
                        Double result;
                        if (formulaId==1)
                            result = calculate1(x, y, z);
                        else
                            result = calculate2(x, y, z);
                        textFieldResult.setText(result.toString());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Floating-point error!", "Numbers format error!",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            JButton buttonReset = new JButton("Clear");
            buttonReset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    textFieldX.setText("0");
                    textFieldY.setText("0");
                    textFieldZ.setText("0");
                    textFieldResult.setText("0");
                }
            });

            Box hboxButtons = Box.createHorizontalBox();
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.add(buttonCalc);
            hboxButtons.add(Box.createHorizontalStrut(30));
            hboxButtons.add(buttonReset);
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.setBorder(
                    BorderFactory.createLineBorder(Color.GREEN));


            Memorybox.add(Box.createHorizontalGlue());
            addMemoryRadioButton("Mem1", 0);
            Memorybox.add(Box.createHorizontalStrut(150));
            addMemoryRadioButton("Mem2", 1);
            Memorybox.add(Box.createHorizontalStrut(150));
            addMemoryRadioButton("Mem3", 2);
            memoryButtons.setSelected(
                    memoryButtons.getElements().nextElement().getModel(), true);
            Memorybox.add(Box.createHorizontalGlue());
            Memorybox.setBorder(
                    BorderFactory.createLineBorder(Color.BLACK));


            Vector<JLabel> MemoryLables = new Vector<JLabel>();
            MemoryLables.add(0, new JLabel(" Mem1"));
            MemoryLables.get(0).setMaximumSize(MemoryLables.get(0).getPreferredSize());
            MemoryLables.add(1, new JLabel(" Mem2"));
            MemoryLables.get(1).setMaximumSize(MemoryLables.get(1).getPreferredSize());
            MemoryLables.add(2, new JLabel(" Mem3"));
            MemoryLables.get(2).setMaximumSize(MemoryLables.get(2).getPreferredSize());

            TextMemory.add(0, new JTextField("0", 20));
            TextMemory.get(0).setMaximumSize(TextMemory.get(0).getPreferredSize());
            TextMemory.add(1, new JTextField("0", 20));
            TextMemory.get(1).setMaximumSize(TextMemory.get(1).getPreferredSize());
            TextMemory.add(2, new JTextField("0", 20));
            TextMemory.get(2).setMaximumSize(TextMemory.get(2).getPreferredSize());
            Box TextMemoryBox = Box.createHorizontalBox();
            TextMemoryBox.setBorder(
                    BorderFactory.createLineBorder(Color.RED));

           /* for(int i = 0; i < 3; i++) {
                //TextMemoryBox.add(Box.createHorizontalGlue());
                TextMemoryBox.add(MemoryLables.get(i));
                //TextMemoryBox.add(Box.createHorizontalGlue());
                TextMemoryBox.add(Box.createHorizontalStrut(10)); //
               // TextMemoryBox.add(Box.createHorizontalGlue());
                TextMemoryBox.add(TextMemory.get(i));
                TextMemoryBox.add(Box.createHorizontalGlue()); //
            }*/

            TextMemoryBox.add(MemoryLables.get(0));
            //TextMemoryBox.add(Box.createHorizontalGlue());
            TextMemoryBox.add(Box.createHorizontalStrut(10)); //
            // TextMemoryBox.add(Box.createHorizontalGlue());
            TextMemoryBox.add(TextMemory.get(0));
            TextMemoryBox.add(Box.createHorizontalGlue()); //

            TextMemoryBox.add(Box.createHorizontalGlue());
            TextMemoryBox.add(MemoryLables.get(1));
            //TextMemoryBox.add(Box.createHorizontalGlue());
            TextMemoryBox.add(Box.createHorizontalStrut(10)); //
            // TextMemoryBox.add(Box.createHorizontalGlue());
            TextMemoryBox.add(TextMemory.get(1));
            TextMemoryBox.add(Box.createHorizontalGlue()); //

            TextMemoryBox.add(Box.createHorizontalGlue());
            TextMemoryBox.add(MemoryLables.get(2));
            //TextMemoryBox.add(Box.createHorizontalGlue());
            TextMemoryBox.add(Box.createHorizontalStrut(10)); //
            // TextMemoryBox.add(Box.createHorizontalGlue());
            TextMemoryBox.add(TextMemory.get(2));
            //TextMemoryBox.add(Box.createHorizontalGlue()); //


            JButton MPlusButton = new JButton("M+");
            MPlusButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    Double num = Double.parseDouble(textFieldResult.getText());
                    num += Double.parseDouble(TextMemory.get(memoryID).getText());
                    TextMemory.get(memoryID).setText(String.valueOf(num));
                }
            });

            JButton MCButton = new JButton("MC");
            MCButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    TextMemory.get(memoryID).setText("0");
                }
            });


            Box MButtonBox = Box.createHorizontalBox();
            MButtonBox.add(Box.createHorizontalGlue());
            MButtonBox.add(MPlusButton);
            MButtonBox.add(Box.createHorizontalStrut(30));
            MButtonBox.add(MCButton);
            MButtonBox.add(Box.createHorizontalGlue());
            MButtonBox.setBorder(
                    BorderFactory.createLineBorder(Color.BLACK));


            Box contentBox = Box.createVerticalBox();
            contentBox.add(Box.createVerticalGlue());

            contentBox.add(hboxFormulaType);
            contentBox.add(hboxVariables);
            contentBox.add(hboxResult);

            contentBox.add(hboxButtons);
            contentBox.add(MButtonBox);
            contentBox.add(Memorybox);
            contentBox.add(TextMemoryBox);


            contentBox.add(Box.createVerticalGlue());
            getContentPane().add(contentBox, BorderLayout.CENTER);
        }

    }

    public static void main(String[] args) throws IOException {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
