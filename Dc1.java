import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Dc1 extends JFrame {

    private JLabel timeLabel;
    private JLabel dateLabel;
    private SimpleDateFormat timeFormat;
    private boolean is24HourFormat = true;
    private JComboBox<String> timeZoneComboBox;

    public Dc1() {
        setTitle("Digital Clock");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel to hold the time and date labels
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        // Create a label to display the time
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(Color.BLUE);

        // Create a label to display the date
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setForeground(Color.WHITE);

        // Add the time and date labels to the panel
        panel.add(timeLabel, BorderLayout.CENTER);
        panel.add(dateLabel, BorderLayout.SOUTH);

        // Set the panel's background color
        panel.setBackground(Color.BLACK);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Create a control panel for buttons and combo box
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JButton switchFormatButton = new JButton("Switch 12/24 Hour");
        switchFormatButton.addActionListener(e -> switchTimeFormat());
        controlPanel.add(switchFormatButton);

        timeZoneComboBox = new JComboBox<>(TimeZone.getAvailableIDs());
        timeZoneComboBox.addActionListener(e -> updateTimeZone());
        controlPanel.add(timeZoneComboBox);

        add(controlPanel, BorderLayout.SOUTH);

        // Use a Timer to update the time and date labels every second
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTimeAndDate();
            }
        });
        timer.start();
        updateTimeAndDate();
    }

    private void switchTimeFormat() {
        is24HourFormat = !is24HourFormat;
        if (is24HourFormat) {
            timeFormat = new SimpleDateFormat("HH:mm:ss");
        } else {
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
        }
        updateTimeAndDate();
    }

    private void updateTimeZone() {
        String selectedTimeZone = (String) timeZoneComboBox.getSelectedItem();
        timeFormat.setTimeZone(TimeZone.getTimeZone(selectedTimeZone));
        updateTimeAndDate();
    }

    private void updateTimeAndDate() {
        // Get the current time and format it
        Calendar calendar = Calendar.getInstance();
        String time = timeFormat.format(calendar.getTime());

        // Get the current date and format it
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
        dateFormatter.setTimeZone(timeFormat.getTimeZone());
        String date = dateFormatter.format(calendar.getTime());

        // Update the time and date labels
        timeLabel.setText(time);
        dateLabel.setText(date);
    }

    public static void main(String[] args) {
            Dc1 clock = new Dc1();
            clock.setVisible(true);
    }
}