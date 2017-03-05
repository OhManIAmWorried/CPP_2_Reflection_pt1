import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

public class MainFrame extends JFrame {
    static MainFrame mainFrame;
    static JPanel mainPanel;
    static JScrollPane scrollPane;
    static JTextField textField;
    static JLabel nameLabel;
    static JLabel propsLabel;
    static JLabel mainLabel;
    static JPanel upperPanel;

    public MainFrame(){
        mainPanel = new JPanel();
        textField = new JTextField(35);
        nameLabel = new JLabel("Name: ");
        mainLabel = new JLabel("<html><pre>");
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(nameLabel,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0.9;
        c.weighty = 0.1;
        c.gridx = 1;
        mainPanel.add(textField,c);

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1;
        c.weighty = 0.9;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        scrollPane = new JScrollPane(mainLabel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMinimumSize(new Dimension(300,300));
        mainLabel.setBackground(Color.WHITE);
        mainLabel.setOpaque(true);
        mainPanel.add(scrollPane,c);
        add(mainPanel);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputInfo(textField.getText());
            }
        });

        setTitle("Reflection pt1");
        setMinimumSize(new Dimension(500,370));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void outputInfo(String className) {
        try {
            Class c = Class.forName(className);
            if (c.isInterface()) {System.out.println("Interfaces are not supported yet"); throw new ClassNotFoundException();}

            /*Package*/
            addCategory(0);
            addProperty(c.getPackage().getName().toString());

            /*Modifiers*/
            addCategory(1);
            addProperty(Modifier.toString(c.getModifiers()));

            /*Name*/
            addCategory(2);
            addProperty(c.getSimpleName());

            /*SuperClass*/
            addCategory(3);
            addProperty(c.getSuperclass().getSimpleName());

            /*Interfaces*/
            addCategory(4);
            Class<?>[] interfaces = c.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {addProperty(interfaces[i].getName());}

            /*Fields*/
            addCategory(5);
            Field[] fields = c.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType().isArray()) {
                    addProperty(Modifier.toString(fields[i].getModifiers()) +
                            " " + fields[i].getType().getSimpleName() +
                            " " + fields[i].getName());
                } else {
                    addProperty(Modifier.toString(fields[i].getModifiers()) +
                            " " + fields[i].getType().getSimpleName() +
                            " " + fields[i].getName());
                }
            }

            /*Constructors*/
            addCategory(6);
            Constructor<?>[] constructors = c.getDeclaredConstructors();
            Class<?>[] parameters;
            String string;
            for (int i = 0; i < constructors.length; i++) {
                string = "";
                parameters = constructors[i].getParameterTypes();
                for (int j = 0; j < parameters.length; j++) {
                    string += getParamType(parameters[j]) + " " + "arg" + j;
                    if (j + 1 != parameters.length) string += ", ";
                }
                addProperty(Modifier.toString(constructors[i].getModifiers()) + " " +
                        constructors[i].getDeclaringClass().getSimpleName() + "(" +
                        string + ")");
            }

            /*Methods*/
            addCategory(7);
            Method[] methods = c.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                string = "";
                parameters = methods[i].getParameterTypes();
                for (int j = 0; j < parameters.length; j++) {
                    string += getParamType(parameters[j]) + " " + "arg" + j;
                    if (j + 1 != parameters.length) string += ", ";
                }
                addProperty(Modifier.toString(methods[i].getModifiers()) +
                        " " + methods[i].getName() + "(" +
                        string + "): " + methods[i].getReturnType().getSimpleName());
            }

            /*public Methods*/
            addCategory(8);
            methods = c.getMethods();
            for (int i = 0; i < methods.length; i++) {
                string = "";
                parameters = methods[i].getParameterTypes();
                for (int j = 0; j < parameters.length; j++) {
                    string += getParamType(parameters[j]) + " " + "arg" + j;
                    if (j + 1 != parameters.length) string += ", ";
                }
                addProperty(Modifier.toString(methods[i].getModifiers()) +
                        " " + methods[i].getName() + "(" +
                        string + "): " + methods[i].getReturnType().getSimpleName());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("EXCEPTION: Class " + className + " not found.");
        }
    }

    private static void addCategory(int type) {
        switch (type) {
            case 0: {mainLabel.setText(mainLabel.getText() + "Package:<br>"); break;}
            case 1: {mainLabel.setText(mainLabel.getText() + "Modifiers:<br>"); break;}
            case 2: {mainLabel.setText(mainLabel.getText() + "Name:<br>"); break;}
            case 3: {mainLabel.setText(mainLabel.getText() + "Parent:<br>"); break;}
            case 4: {mainLabel.setText(mainLabel.getText() + "Interfaces:<br>"); break;}
            case 5: {mainLabel.setText(mainLabel.getText() + "Fields:<br>"); break;}
            case 6: {mainLabel.setText(mainLabel.getText() + "Constructors:<br>"); break;}
            case 7: {mainLabel.setText(mainLabel.getText() + "Methods:<br>"); break;}
            case 8: {mainLabel.setText(mainLabel.getText() + "public Methods:<br>"); break;}
            //case 9: {mainLabel.setText(mainLabel.getText() + ""); break;}
        }
    }

    private String getParamType(Class<?> that) {
        if (that.isArray()) {
            return that.getSimpleName();
        } else return that.getSimpleName();
    }

    private static void addProperty(String string) {
        mainLabel.setText(mainLabel.getText() + "   " + string + "<br>");
    }

    public static void main(String[] args) {
        mainFrame = new MainFrame();
    }
}