import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Football Players Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setLocationRelativeTo(null);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton qbButton = createStyledButton("Show Quarterbacks");
        JButton wrButton = createStyledButton("Show Wide Receivers");
        JButton rbButton = createStyledButton("Show Running Backs");
        JButton teButton = createStyledButton("Show Tight Ends");

        QB qb = new QB();
        WR wr = new WR();
        RB rb = new RB();
        TE te = new TE();

        Player[] quarterbacks = QB.getTop30();
        Player[] runningBacks = RB.getTop30();
        Player[] wideReceivers = WR.getTop30();
        Player[] tightEnds = TE.getTop30();

        qbButton.addActionListener(e -> displayPlayers(quarterbacks, displayArea));
        wrButton.addActionListener(e -> displayPlayers(wideReceivers, displayArea));
        rbButton.addActionListener(e -> displayPlayers(runningBacks, displayArea));
        teButton.addActionListener(e -> displayPlayers(tightEnds, displayArea));

        buttonPanel.add(qbButton);
        buttonPanel.add(wrButton);
        buttonPanel.add(rbButton);
        buttonPanel.add(teButton);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Sans Serif", Font.BOLD, 14));
        button.setBackground(new Color(0, 122, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 50));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

    private static void displayPlayers(Player[] players, JTextArea displayArea) {
        displayArea.setText("");

        if (players == null || players.length == 0) {
            displayArea.append("No players available.\n");
            return;
        }

        for (Player player : players) {
            displayArea.append(player.toString() + "\n");
        }
    }
}
