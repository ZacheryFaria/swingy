package zfaria.swingy.view;

import zfaria.swingy.Coordinate;
import zfaria.swingy.Map;
import zfaria.swingy.hero.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingView implements GameView {

    private JTextField messageInput;
    private JTextArea mapDisplay;
    private JTextArea heroDisplay;
    private JTextArea messageArea;
    private JScrollPane messageAreaPane;
    private JScrollPane mapDisplayPane;
    private JScrollPane heroDisplayPane;

    private JFrame frame;

    private String inputMessage = null;


    public SwingView() {
    }

    @Override
    public void init() {
        frame = new JFrame("Swingy");
        frame.setResizable(false);
        frame.setLocation(200, 200);

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.ipady = 240;
        c.weightx = .1;
        heroDisplay = new JTextArea();
        heroDisplay.setText("heroDisplay");
        heroDisplay.setEditable(false);
        heroDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        heroDisplayPane = new JScrollPane(heroDisplay);
        frame.add(heroDisplayPane, c);

        c.gridx = 1;
        mapDisplay = new JTextArea();
        mapDisplay.setText("mapDisplay");
        mapDisplay.setEditable(false);
        mapDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapDisplayPane = new JScrollPane(mapDisplay);
        frame.add(mapDisplayPane, c);


        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setText("messageArea");
        messageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        messageAreaPane = new JScrollPane(messageArea);
        messageAreaPane.setAutoscrolls(true);
        frame.add(messageAreaPane, c);

        c.gridy = 2;
        c.ipady = 0;
        messageInput = new JTextField();
        messageInput.setPreferredSize(new Dimension(800, 30));
        messageInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputMessage = messageInput.getText();
                messageInput.setText("");
            }
        });
        frame.add(messageInput, c);

        // TODO
        // This may need to be overriden later with a component listener to save game before closing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public String promptUser(String prompt) {
        messageUser(prompt);
        while (inputMessage == null) {
            try {
                // We sleep to allow the cpu to 'breathe', won't return promptly otherwise.
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e); // not really sure what happened or why
            }
        }
        String tmp = inputMessage;
        inputMessage = null;
        return tmp;
    }

    @Override
    public void messageUser(String msg) {
        messageArea.append(msg + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }

    @Override
    public void updateHeroData(Hero h) {
        String fmtstr = "Name: %s\nClass: %s\nLevel: %d\nExperience: %d\nAttack Damage: %d + %d\n" +
                "Hit Points: %.2f + %d\nDefense: %.0f%% + %d%%\nLuck: %.0f%%";
        String res = String.format(fmtstr, h.getName(), h.getHeroClass(), h.getLevel(), h.getExperience(),
                h.getAttack(), h.getWeapon().getStat(),
                h.getHitPoints(), h.getHelm().getStat(), h.getDefense() * 100, h.getArmor().getStat(), h.getLuck() * 100);
        heroDisplay.setText(res);
    }

    @Override
    public void updateMapData(Map m, Hero h) {
        StringBuilder builder = new StringBuilder();
        Coordinate c = new Coordinate();
        for (int j = 0; j < m.getSize(); j++) {
            for (int i = 0; i < m.getSize(); i++) {
                if (h.getPosition().equals(c)) {
                    builder.append("X ");
                } else if (m.containsMonster(c)) {
                    builder.append("E ");
                } else if (m.hasVisited(c)) {
                    builder.append(". ");
                } else {
                    builder.append("? ");
                }
                c.addCoordinate(1, 0);
            }
            builder.append("\n");
            c.addCoordinate(-m.getSize(), 0);
            c.addCoordinate(0, 1);
        }
        mapDisplay.setText(builder.toString());
    }

    @Override
    public void clearScreen() {
        messageArea.setText("");
    }
}
