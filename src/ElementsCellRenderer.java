import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ElementsCellRenderer extends JPanel implements ListCellRenderer<Model> {

    static private ImageIcon defaultImage = new ImageIcon(new ImageIcon("default.png").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH));
    private Connection connection;
    private JLabel lblImg;
    private JLabel lblDescription;
    private JCheckBox cbConfirm;

    public ElementsCellRenderer(Connection connection) {
        this.connection = connection;
        setLayout(null);
        lblImg = new JLabel();
        lblImg.setBounds(10,10,110,110);
        lblDescription = new JLabel();
        lblDescription.setBounds(this.getX(), this.getY() + 130, 130, 20);
        cbConfirm = new JCheckBox();
        cbConfirm.setBounds(this.getX() + 10, this.getY() + 10, 30, 30);
        cbConfirm.setSelected(false);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Model> list, Model value, int index, boolean isSelected, boolean cellHasFocus) {
        byte[] imageBytes = {};

        //Read Image or set default one
        try {
            PreparedStatement s = connection.prepareStatement("SELECT image FROM images WHERE printid = ? LIMIT 1");
            s.setInt(1, value.getId());
            ResultSet result = s.executeQuery();
            if (result.next()) {
                ImageIcon image = new ImageIcon(result.getBytes("image"));
                lblImg.setIcon(new ImageIcon(image.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
                //defaultImage = new ImageIcon(imageBytes);
            } else {
                lblImg.setIcon(defaultImage);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String modelName = value.getName();
        lblDescription.setText(modelName);
        lblDescription.setHorizontalAlignment(0);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        cbConfirm.setSelected(isSelected);
        add(cbConfirm);
        add(lblImg);
        add(lblDescription);

        setOpaque(true);
        return this;
    }

}
