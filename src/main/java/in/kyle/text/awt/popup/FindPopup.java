package in.kyle.text.awt.popup;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import in.kyle.text.TextJ;
import in.kyle.text.awt.TextTab;
import in.kyle.text.event.events.TabChangeEvent;
import in.kyle.text.event.events.TextEditEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kyle on 9/5/2015.
 */
public class FindPopup extends JDialog {
    
    private static FindPopup instance;
    
    private JPanel mainPanel;
    private JTabbedPane box;
    private JPanel Find;
    private JTextField searchText;
    private JButton findNextButton;
    private JButton lastButton;
    private JButton countButton;
    private JCheckBox wholeWordCheckBox;
    private JCheckBox matchCaseCheckBox;
    private JRadioButton normalRadioButton;
    private JRadioButton extendedNRRadioButton;
    private JRadioButton regexRadioButton;
    private JButton countButton1;
    private JLabel output;
    private TextJ textJ;
    private Matcher matcher;
    
    public FindPopup(JFrame component, TextJ textJ) {
        super(component);
        
        if (instance != null) {
            instance.searchText.requestFocus();
            System.out.println("Focused on text field");
            return;
        }
        //setModal(true);
        this.textJ = textJ;
        setAlwaysOnTop(true);
        $$$setupUI$$$();
        setContentPane(mainPanel);
        setResizable(false);
        setTitle("Find");
        setSize(500, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        
        mainPanel.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        findNextButton.addActionListener(e -> search());
        countButton.addActionListener(e -> count());
        countButton1.addActionListener(e -> onCancel());
        setLocationRelativeTo(component);
        normalRadioButton.setSelected(true);
        
        // disable for now
        lastButton.setVisible(false);
        extendedNRRadioButton.setVisible(false);
        
        setVisible(true);
        searchText.requestFocus();
        textJ.getEventManager().register(this);
        instance = this;
    }
    
    private void onCancel() {
        dispose();
        instance = null;
        textJ.getEventManager().unregister(this);
    }
    
    public void onTabChange(TabChangeEvent e) {
        matcher = null;
    }
    
    public void onTextEdit(TextEditEvent e) {
        matcher = null;
    }
    
    private void search() {
        if (searchText.getText().isEmpty()) {
            output.setForeground(Color.RED);
            output.setText("Enter a search string");
        } else {
            TextTab textTab = textJ.getFrame().getTextTabs().getSelectedTab();
    
            if (matcher == null) {
                String text = textTab.getTextArea().getJTextArea().getText();
                String search = searchText.getText();
                Pattern pattern = null;
    
                boolean ignoreCase = !matchCaseCheckBox.isSelected();
    
                if (normalRadioButton.isSelected()) {
                    pattern = Pattern.compile(Matcher.quoteReplacement(search), (ignoreCase ? Pattern.CASE_INSENSITIVE : 0));
                } else if (extendedNRRadioButton.isSelected()) {
                    
                    //found = searchExtended(searchText.getText(), textTab);
                } else {
                    pattern = Pattern.compile(search, (ignoreCase ? Pattern.CASE_INSENSITIVE : 0));
                }
                
                matcher = pattern.matcher(text);
            }
    
            if (matcher.find()) {
                textTab.select(matcher.start(), matcher.end());
            } else {
                output.setForeground(Color.RED);
                output.setText("No occurrences found");
            }
        }
    }
    
    private void count() {
        if (searchText.getText().isEmpty()) {
            output.setForeground(Color.RED);
            output.setText("Enter a search string");
        } else {
            TextTab textTab = textJ.getFrame().getTextTabs().getSelectedTab();
            
            String text = textTab.getTextArea().getJTextArea().getText();
            String search = searchText.getText();
            Pattern pattern = null;
            
            boolean ignoreCase = !matchCaseCheckBox.isSelected();
            
            if (normalRadioButton.isSelected()) {
                pattern = Pattern.compile(Matcher.quoteReplacement(search), (ignoreCase ? Pattern.CASE_INSENSITIVE : 0));
            } else if (extendedNRRadioButton.isSelected()) {
                
                //found = searchExtended(searchText.getText(), textTab);
            } else {
                pattern = Pattern.compile(search, (ignoreCase ? Pattern.CASE_INSENSITIVE : 0));
            }
            
            Matcher matcher = pattern.matcher(text);
            
            int count = 0;
            while (matcher.find()) {
                count++;
            }
    
            output.setForeground(Color.BLUE);
            output.setText("Found " + count + " matches");
        }
    }
    
    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setInheritsPopupMenu(true);
        mainPanel.setMinimumSize(new Dimension(500, 300));
        mainPanel.setPreferredSize(new Dimension(500, 300));
        box = new JTabbedPane();
        mainPanel.add(box, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        Find = new JPanel();
        Find.setLayout(new GridLayoutManager(3, 3, new Insets(10, 0, 10, 0), -1, -1));
        Find.setName("Find");
        box.addTab("Find", Find);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        Find.add(panel1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Find");
        panel1.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        searchText = new JTextField();
        searchText.setText("");
        panel1.add(searchText, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        Find.add(spacer3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        Find.add(panel2, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel3.add(spacer6, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        findNextButton = new JButton();
        findNextButton.setText("Find Next");
        panel3.add(findNextButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        lastButton = new JButton();
        lastButton.setText("Last");
        panel3.add(lastButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        countButton = new JButton();
        countButton.setText("Count");
        panel3.add(countButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        countButton1 = new JButton();
        countButton1.setLabel("Close");
        countButton1.setText("Close");
        panel3.add(countButton1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final Spacer spacer7 = new Spacer();
        Find.add(spacer7, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 10), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        Find.add(panel4, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        wholeWordCheckBox = new JCheckBox();
        wholeWordCheckBox.setText("Whole word");
        panel4.add(wholeWordCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matchCaseCheckBox = new JCheckBox();
        matchCaseCheckBox.setText("Match case");
        panel4.add(matchCaseCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        panel4.add(spacer8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.add(panel5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Search Mode"));
        normalRadioButton = new JRadioButton();
        normalRadioButton.setText("Normal");
        panel5.add(normalRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        extendedNRRadioButton = new JRadioButton();
        extendedNRRadioButton.setText("Extended (\\n, \\r)");
        panel5.add(extendedNRRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        regexRadioButton = new JRadioButton();
        regexRadioButton.setText("Regex");
        panel5.add(regexRadioButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        Find.add(spacer9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(10, -1), null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 2, new Insets(0, 10, 5, 0), -1, -1));
        mainPanel.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        output = new JLabel();
        output.setText("");
        panel6.add(output, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        panel6.add(spacer10, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(normalRadioButton);
        buttonGroup.add(extendedNRRadioButton);
        buttonGroup.add(regexRadioButton);
    }
    
    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
    
    @AllArgsConstructor
    public static class SearchResult {
        @Getter
        private int start, end;
    }
}
