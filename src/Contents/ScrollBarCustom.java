package Contents;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import tabledark.ModernScrollBarUI;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(249, 234, 208));
        setBackground(new Color(79, 92, 82));
    }
}
