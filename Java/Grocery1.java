import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;

public class Grocery1 extends JFrame {

    private double totalCost;
    private JPanel contentPane;
    private JTextField txtTotal;
    private JTextArea txtReceipt;
    private JCheckBox chkMatchBox, chkEggs, chkMilk, chkCheese, chkRice, chkBread;
    private JTextField txtMatchBox, txtEggs, txtMilk, txtCheese, txtRice, txtBread;
    private JButton btnExit;
    private JButton btnClear;
    private JButton btnTotal;
    private JButton btnPrint;

    private static final BigDecimal PRICE_MATCH_BOX = new BigDecimal("5.00");
    private static final BigDecimal PRICE_EGGS = new BigDecimal("10.00");
    private static final BigDecimal PRICE_MILK = new BigDecimal("15.00");
    private static final BigDecimal PRICE_CHEESE = new BigDecimal("15.00");
    private static final BigDecimal PRICE_RICE = new BigDecimal("52.00");
    private static final BigDecimal PRICE_BREAD = new BigDecimal("50.00");

    public Grocery1() {
        setTitle("Grocery Store Software");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 500);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        initUIComponents();
        initEventHandlers();
    }

    private void initUIComponents() {
        txtTotal = new JTextField("P 0");
        txtTotal.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        txtTotal.setBounds(350, 20, 70, 25);
        contentPane.add(txtTotal);

        txtReceipt = new JTextArea();
        txtReceipt.setFont(new Font("Courier New", Font.PLAIN, 12));
        txtReceipt.setBounds(20, 120, 400, 200);
        contentPane.add(txtReceipt);

        chkMatchBox = createCheckBox("Match Box", 20, 20);
        txtMatchBox = createTextField("0", 100, 20);

        chkEggs = createCheckBox("Eggs", 20, 50);
        txtEggs = createTextField("0", 100, 50);

        chkMilk = createCheckBox("Milk", 20, 80);
        txtMilk = createTextField("0", 100, 80);

        chkCheese = createCheckBox("Cheese", 200, 20);
        txtCheese = createTextField("0", 280, 20);

        chkRice = createCheckBox("Rice", 200, 50);
        txtRice = createTextField("0", 280, 50);

        chkBread = createCheckBox("Bread", 200, 80);
        txtBread = createTextField("0", 280, 80);

        btnExit = createButton("Exit", 20, 420);
        btnClear = createButton("Clear", 120, 420);
        btnTotal = createButton("Total", 220, 420);
        btnPrint = createButton("Print", 320, 420);
    }

    private JCheckBox createCheckBox(String text, int x, int y) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBounds(x, y, 80, 25);
        contentPane.add(checkBox);
        return checkBox;
    }

    private JTextField createTextField(String text, int x, int y) {
        JTextField textField = new JTextField(text);
        textField.setEnabled(false);
        textField.setBounds(x, y, 50, 25);
        contentPane.add(textField);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        return textField;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 80, 30);
        contentPane.add(button);
        return button;
    }

    private void initEventHandlers() {
        btnExit.addActionListener(e -> exitApplication());
        btnClear.addActionListener(e -> clearSelections());
        btnTotal.addActionListener(e -> calculateTotal());
        btnPrint.addActionListener(e -> printReceipt());

        chkMatchBox.addItemListener(e -> toggleTextField(chkMatchBox, txtMatchBox));
        chkEggs.addItemListener(e -> toggleTextField(chkEggs, txtEggs));
        chkMilk.addItemListener(e -> toggleTextField(chkMilk, txtMilk));
        chkCheese.addItemListener(e -> toggleTextField(chkCheese, txtCheese));
        chkRice.addItemListener(e -> toggleTextField(chkRice, txtRice));
        chkBread.addItemListener(e -> toggleTextField(chkBread, txtBread));
    }

    private void toggleTextField(JCheckBox checkBox, JTextField textField) {
        if (checkBox.isSelected()) {
            textField.setEnabled(true);
            textField.setText("1");
            textField.requestFocus();
        } else {
            textField.setEnabled(false);
            textField.setText("0");
        }
    }

    private void exitApplication() {
        int choice = JOptionPane.showConfirmDialog(contentPane, "Confirm if You want to Exit", "Grocery Store Software", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void clearSelections() {
        JCheckBox[] checkBoxes = {chkMatchBox, chkEggs, chkMilk, chkCheese, chkRice, chkBread};
        JTextField[] textFields = {txtMatchBox, txtEggs, txtMilk, txtCheese, txtRice, txtBread};

        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
        }

        for (JTextField textField : textFields) {
            textField.setEnabled(false);
            textField.setText("0");
        }

        txtTotal.setText("P 0");
        txtReceipt.setText("");
    }

    private void calculateTotal() {
        int quantityMatchBox = Integer.parseInt(txtMatchBox.getText());
        int quantityEggs = Integer.parseInt(txtEggs.getText());
        int quantityMilk = Integer.parseInt(txtMilk.getText());
        int quantityCheese = Integer.parseInt(txtCheese.getText());
        int quantityRice = Integer.parseInt(txtRice.getText());
        int quantityBread = Integer.parseInt(txtBread.getText());

        BigDecimal itemCostMatchBox = BigDecimal.valueOf(quantityMatchBox).multiply(PRICE_MATCH_BOX);
        BigDecimal itemCostEggs = BigDecimal.valueOf(quantityEggs).multiply(PRICE_EGGS);
        BigDecimal itemCostMilk = BigDecimal.valueOf(quantityMilk).multiply(PRICE_MILK);
        BigDecimal itemCostCheese = BigDecimal.valueOf(quantityCheese).multiply(PRICE_CHEESE);
        BigDecimal itemCostRice = BigDecimal.valueOf(quantityRice).multiply(PRICE_RICE);
        BigDecimal itemCostBread = BigDecimal.valueOf(quantityBread).multiply(PRICE_BREAD);

        totalCost = itemCostMatchBox.add(itemCostEggs).add(itemCostMilk).add(itemCostCheese).add(itemCostRice).add(itemCostBread).doubleValue();

        txtTotal.setText("P " + totalCost);

        String receipt = "============= Receipt ============\n\n";
        receipt += "Item\t\tQuantity\tCost\n";
        receipt += "----------------------------------\n";
        receipt += "Match Box\t" + quantityMatchBox + "\t\tP " + itemCostMatchBox + "\n";
        receipt += "Eggs\t\t" + quantityEggs + "\t\tP " + itemCostEggs + "\n";
        receipt += "Milk\t\t" + quantityMilk + "\t\tP " + itemCostMilk + "\n";
        receipt += "Cheese\t\t" + quantityCheese + "\t\tP " + itemCostCheese + "\n";
        receipt += "Rice\t\t" + quantityRice + "\t\tP " + itemCostRice + "\n";
        receipt += "Bread\t\t" + quantityBread + "\t\tP " + itemCostBread + "\n";
        receipt += "----------------------------------\n";
        receipt += "Total\t\t\t\tP " + totalCost + "\n\n";
        receipt += "Thank you for shopping at Sari sari store";
        txtReceipt.setText(receipt);
    }

    private void printReceipt() {
        try {
            txtReceipt.print();
        } catch (java.awt.print.PrinterException ex) {
            Logger.getLogger(Grocery1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Grocery1 frame = new Grocery1();
                frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(Grocery1.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }
}
