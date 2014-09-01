package pairhero.client.view.game;

import javax.swing.*;

import static javax.swing.BoxLayout.Y_AXIS;

public class StatisticsView extends JPanel {

    private final JLabel score = new JLabel("0");
    private final JLabel time = new JLabel("00:00:00");

    public StatisticsView() {
        setLayout(new BoxLayout(this, Y_AXIS));
        setOpaque(false);
        add(center(score));
        add(center(time));
    }

    private JLabel center(JLabel toCenter) {
        toCenter.setAlignmentX(CENTER_ALIGNMENT);
        return toCenter;
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

    public void setScore(long score) {
        this.score.setText(String.valueOf(score));
    }
}
