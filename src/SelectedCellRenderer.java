import javax.swing.*;
import java.awt.*;

public class SelectedCellRenderer extends JLabel implements ListCellRenderer<Model> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Model> list, Model value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setText(value.getName());

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
