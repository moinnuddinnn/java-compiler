import javax.swing.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;
import java.io.*;

public class EditorFrame extends JFrame {

    private RSyntaxTextArea textArea;

    public EditorFrame() {
        setTitle("Code Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new RSyntaxTextArea(20, 60);//createing text area with syntax highlighting
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA); //highlight Java
        textArea.setCodeFoldingEnabled(true);

        RTextScrollPane sp = new RTextScrollPane(textArea);
        add(sp);

        //menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem exitApp = new JMenuItem("Exit");

        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(exitApp);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        //file actions
        openFile.addActionListener(e -> openFile());
        saveFile.addActionListener(e -> saveFile());
        exitApp.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file");
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file");
            }
        }
    }
}

