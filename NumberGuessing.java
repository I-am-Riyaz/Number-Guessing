import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;                                    //Required Packages...
import java.util.Random;

class Number 
{
    private int number;
    private int attempts = 0;

    Number() 
    {
        Random random = new Random();
        this.number = random.nextInt(100) + 1;                                //Generates a Random Number(1 to 100)...
    }

    public int getAttempts() 
    {                                                                     //Counts The Attempt...
        return attempts;                                                                                                             
    }

    void resetGame()
    {
        Random random = new Random();
        this.number = random.nextInt(100) + 1;                                  //Reset the Game...
        this.attempts = 0;
    }

    boolean isCorrectNumber(int guess)
    {                                                      
        attempts++;
        if (guess == number)                                                       //Checks The Input Whether it is matching or not...
        {
            JOptionPane.showMessageDialog(null, "Congratulations! You guessed it right.\nThe number was : " + number +
                    "\nYou took " + attempts + " attempts.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } 
        else if (guess < number)
        {
            JOptionPane.showMessageDialog(null, "Number is too low. Try a higher number.",
                    "Too Low :(", JOptionPane.WARNING_MESSAGE);
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Number is too high. Try a lower number.",
                    "Too High :(", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
}

public class NumberGuessing
{
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(()->{                                                            
            JFrame frame = new JFrame("NUMBER GUESSING");
            frame.setSize(400, 250);                                                                         
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            Number game = new Number();

            JLabel label = new JLabel("Guess The Number :");
            label.setFont(new Font("Arial", Font.BOLD, 18)); 
            JTextField textField = new JTextField();
            textField.setBackground(new Color(224, 255, 255));
            JButton guessButton = new JButton("GUESS");
            textField.addActionListener(e -> guessButton.doClick());
            JButton resetButton = new JButton("RESET"); 
            JLabel attemptsLabel = new JLabel("ATTEMPTS : 0");
            attemptsLabel.setForeground(Color.RED);
            attemptsLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
            JLabel noteLabel = new JLabel("NOTE : Please guess a Number between 1 and 100.");   
            noteLabel.setForeground(Color.BLUE);
            noteLabel.setFont(new Font("Arial", Font.BOLD, 18)); 
            
            resetButton.addActionListener(e->{
                game.resetGame();                         //Reset The Game...
                guessButton.setEnabled(true);
                textField.setEnabled(true);
                attemptsLabel.setText("ATTEMPTS : 0");    //Reset The Attempts...
                textField.setText("");
            });

            guessButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    try 
                    {
                        int guess = Integer.parseInt(textField.getText());
                        
                        if (game.isCorrectNumber(guess)) 
                        {
                            guessButton.setEnabled(false);
                            textField.setEnabled(false);
                        }
                        attemptsLabel.setText("ATTEMPTS : " + game.getAttempts());
                    }
                    catch (NumberFormatException ex) 
                    {
                        JOptionPane.showMessageDialog(frame, "PLEASE ENTER A VALID NUMBER.", "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    textField.setText("");
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 1));    
            panel.add(label);
            panel.add(textField);
            panel.add(guessButton);
            panel.add(resetButton);
            panel.add(attemptsLabel);
            panel.add(noteLabel);    
            
            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}