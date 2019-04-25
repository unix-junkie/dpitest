import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.JLabel;

/**
 * @author $Author$
 * @version $Revision$, $Date$
 */
public class DpiTest {
	private DpiTest() {
		assert false;
	}

	public static void main(final String args[]) {
		final int pointSize = 12;

		final int fontStyles[] = new int[]{Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final String toolkitName = toolkit.getClass().getName();
		final String fontFamilyNames[] = new String[]{"Courier New", "Times New Roman", "Arial"};
		String logicalFontFamilyNames[];
		if (toolkitName.equals("sun.awt.X11.XToolkit")) {
			logicalFontFamilyNames = fontFamilyNames;
		} else {
			logicalFontFamilyNames = new String[]{"Monospaced", "Serif", "SansSerif"};
		}
		final Frame frame = new Frame(toolkitName + " at " + toolkit.getScreenResolution() + " dpi");
		frame.setLayout(new GridLayout(fontFamilyNames.length * fontStyles.length, 3));

		assert fontFamilyNames.length == logicalFontFamilyNames.length;
		for (int i = 0; i < fontFamilyNames.length; i++) {
			final String fontFamilyName = fontFamilyNames[i];
			final String logicalFontFamilyName = logicalFontFamilyNames[i];
			for (int j = 0; j < fontStyles.length; j++) {
				final int fontStyle = fontStyles[j];
				final Font logicalFont = new Font(logicalFontFamilyName, fontStyle, pointSize);
				final Font font = new Font(fontFamilyName, fontStyle, pointSize);
				final String defaultText = " " + pointSize +  "pt (" + fontStyle + "): \u043c\u0430\u043c\u0430 \u043c\u044b\u043b\u0430 \u0440\u0430\u043c\u0443";
				final String logicalText = logicalFontFamilyName + defaultText;
				final String text = fontFamilyName + defaultText;
				final Label label = new Label(logicalText);
				label.setFont(logicalFont);
				final JLabel jLabel1 = new JLabel(logicalText);
				jLabel1.setFont(logicalFont);
				final JLabel jLabel2 = new JLabel(text);
				jLabel2.setFont(font);
				frame.add(label);
				frame.add(jLabel1);
				frame.add(jLabel2);
			}
		}

		frame.pack();
		frame.setVisible(true);
	}
}
