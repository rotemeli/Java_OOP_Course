import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyScrollPane extends JPanel implements AdjustmentListener, ComponentListener {
    private final JPanel panel;
    private final JPanel content;
    private final JScrollBar vertical;
    private final JScrollBar horizontal;

    public MyScrollPane(JPanel p){
        // Dimension
        Dimension dPanel = new Dimension(p.getPreferredSize().width, p.getPreferredSize().height);


        // input content
        content = p;
        content.setSize(dPanel);


        // Panel
        panel = new JPanel();
        panel.setSize(dPanel);
        panel.setLayout(null);
        panel.add(p);


        // Scrolls
        vertical = new JScrollBar(Adjustable.VERTICAL);
        vertical.addAdjustmentListener(this);

        horizontal = new JScrollBar(Adjustable.HORIZONTAL);
        horizontal.addAdjustmentListener(this);


        // MyScrollPain panel
        super.setLayout(new BorderLayout());
        super.setSize(new Dimension(500,450));
        super.add(horizontal, BorderLayout.SOUTH);
        super.add(vertical, BorderLayout.WEST);
        super.add(panel, BorderLayout.CENTER);
        super.addComponentListener(this);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if(e.getSource() == this.horizontal) {
            content.setLocation(-horizontal.getValue(), panel.getY());
            content.repaint();
        }

        if(e.getSource() == this.vertical) {
            content.setLocation(panel.getX(), -vertical.getValue());
            content.repaint();
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if(content.getHeight() > super.getHeight()) {
            vertical.setMinimum(0);
            vertical.setMaximum(content.getHeight());
            super.add(vertical, BorderLayout.WEST);
        } else {
            super.remove(vertical);
        }

        if(content.getWidth() > super.getWidth()) {
            horizontal.setMinimum(0);
            horizontal.setMaximum(content.getWidth());
            super.add(horizontal, BorderLayout.SOUTH);
        } else {
            super.remove(horizontal);
        }
        content.repaint();
        super.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    public static void main( String[] args )
    {
        JTextArea jta = new JTextArea(3,16);
        String text = "’Twas brillig, and the slithy toves\n"
                + "      Did gyre and gimble in the wabe:\n"
                + "All mimsy were the borogoves,\n"
                + "      And the mome raths outgrabe.\n"
                + "\n"
                + "“Beware the Jabberwock, my son!\n"
                + "      The jaws that bite, the claws that catch!\n"
                + "Beware the Jubjub bird, and shun\n"
                + "      The frumious Bandersnatch!”\n"
                + "\n"
                + "He took his vorpal sword in hand;\n"
                + "      Long time the manxome foe he sought—\n"
                + "So rested he by the Tumtum tree\n"
                + "      And stood awhile in thought.\n"
                + "\n"
                + "And, as in uffish thought he stood,\n"
                + "      The Jabberwock, with eyes of flame,\n"
                + "Came whiffling through the tulgey wood,\n"
                + "      And burbled as it came!\n"
                + "\n"
                + "One, two! One, two! And through and through\n"
                + "      The vorpal blade went snicker-snack!\n"
                + "He left it dead, and with its head\n"
                + "      He went galumphing back.\n"
                + "\n"
                + "“And hast thou slain the Jabberwock?\n"
                + "      Come to my arms, my beamish boy!\n"
                + "O frabjous day! Callooh! Callay!”\n"
                + "      He chortled in his joy.\n"
                + "\n"
                + "’Twas brillig, and the slithy toves\n"
                + "      Did gyre and gimble in the wabe:\n"
                + "All mimsy were the borogoves,\n"
                + "      And the mome raths outgrabe.";

        jta.setText( text );
        JPanel jp = new JPanel();
        jp.setBackground( Color.yellow );
        jp.add( jta );

        MyScrollPane msp = new MyScrollPane(jp);

        int actualWidth = 200;
        int actualHeight = 400;
        JFrame frame = new JFrame("MyScrollPane");
        frame.getContentPane().setPreferredSize(new Dimension(actualWidth, actualHeight));
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add(msp);
        frame.pack();
        frame.setVisible( true );
    }
}
