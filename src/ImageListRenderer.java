import javax.swing.*;
import java.awt.*;

public class ImageListRenderer extends JPanel implements ListCellRenderer<byte[]> {
    private JLabel lblIcon;

    public ImageListRenderer() {
        this.lblIcon = new JLabel();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends byte[]> list, byte[] value, int index, boolean isSelected, boolean cellHasFocus) {

        ImageIcon imgUnResized = new ImageIcon(value);
        lblIcon.setIcon(new ImageIcon(imgUnResized.getImage().getScaledInstance(60, 60, Image.SCALE_FAST)));
        this.add(lblIcon, BorderLayout.CENTER);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setOpaque(true);
        return this;
    }
}
