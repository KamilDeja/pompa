import com.sun.glass.ui.View;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * Created by Kamil on 2015-06-05.
 */
class Verifier extends InputVerifier {

    public class ErrorFrame
            extends 	JFrame
    {
        public ErrorFrame()
        {{
            setSize(250, 70);
            setBackground(Model.colour);

            Panel topPanel = new Panel();
            topPanel.setBackground(Model.colour);
            topPanel.setLayout(new GridBagLayout());
            getContentPane().add(topPanel);

            Label labelError = new Label( "Bledny format danych!" );
            labelError.setFont(new Font("Serif",Font.BOLD,20));
            topPanel.add(labelError);
            //topPanel.add( labelHello, BorderLayout.NORTH );
            setLocationRelativeTo(null);
            setVisible(true);
        }
        }
    }

    public boolean verify(JComponent input) {
        final String Digits     = "(\\p{Digit}+)";
        final String HexDigits  = "(\\p{XDigit}+)";
        // an exponent is 'e' or 'E' followed by an optionally
        // signed decimal integer.
        final String Exp        = "[eE][+-]?"+Digits;
        final String fpRegex    =
                (//"[\\x00-\\x20]*"+  // Optional leading "whitespace"
                        "[+-]?(" + // Optional sign character
                                "NaN|" +           // "NaN" string
                                "Infinity|" +      // "Infinity" string

                                // A decimal floating-point string representing a finite positive
                                // number without a leading sign has at most five basic pieces:
                                // Digits . Digits ExponentPart FloatTypeSuffix
                                //
                                // Since this method allows integer-only strings as input
                                // in addition to strings of floating-point literals, the
                                // two sub-patterns below are simplifications of the grammar
                                // productions from the Java Language Specification, 2nd
                                // edition, section 3.10.2.

                                // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
                                "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

                                // . Digits ExponentPart_opt FloatTypeSuffix_opt
                                "(\\.("+Digits+")("+Exp+")?)|"+

                                // Hexadecimal strings
                                "((" +
                                // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
                                "(0[xX]" + HexDigits + "(\\.)?)|" +

                                // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
                                "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

                                ")[pP][+-]?" + Digits + "))" +
                                "[fFdD]?))" +
                                "[\\x00-\\x20]*");// Optional trailing "whitespace"
        JTextField tf = (JTextField) input;
        if (Pattern.matches(fpRegex, tf.getText())==false) {
            ErrorFrame blad= new ErrorFrame();
//            if(view2!=null)
//                view2.rozpispompy.setEnabled(false);
            return false;
        }
        return true;
    }


}
