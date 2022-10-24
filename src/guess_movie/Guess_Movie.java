
package guess_movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane; // for ShowMessage
import java.util.Random;
import java.io.*; // for Files

public class Guess_Movie {
        public static void main(String[] args) {
        String v[] = new String[6];
        v[0] = "...";
        v[1] = "...";
        v[2] = "...";
        v[3] = "...";
        v[4] = "...";
        v[5] = "...";
        
        DesignGUI dgui = new DesignGUI();
        dgui.LoadVariants(v);

    }
}

interface Const{
    static final String movie_base_dir = "C:\\Users\\Asus\\Desktop\\prog_java\\Guess_Movie\\src\\movie_base\\";
    static final String movie_base_list = "C:\\Users\\Asus\\Desktop\\prog_java\\Guess_Movie\\src\\movie_base\\list_titles.txt";
    static final int movies_count = 7;
    static final int movie_pics_count = 5;
    static final int answer_variants_count = 6;
}

class DesignGUI implements Const, ActionListener{
    JFrame control_frame;
    JFrame image_frame;
    JFrame MSGframe;
    JLabel guessed_label;
    JLabel not_guessed_label;
    JLabel omitted_label;
    JButton start_button;
    JButton next_button;
    JButton answer_button;
    ButtonGroup rBg;
    JRadioButton rButton_option_0;
    JRadioButton rButton_option_1;
    JRadioButton rButton_option_2;
    JRadioButton rButton_option_3;
    JRadioButton rButton_option_4;
    JRadioButton rButton_option_5;
    LoadRandomMovieInfo loadRandomMovieInfo;
    LoadRandomMovieImage loadRandomMovieImage;
    String right_answer;
    int guessed_count = 0;
    int not_guessed_count = 0;
    int omitted_count = 0;

    DesignGUI(){
        loadRandomMovieInfo = new LoadRandomMovieInfo();
        loadRandomMovieImage = new LoadRandomMovieImage();
        control_frame = new JFrame ("Guess Movie");
        
        MSGframe = new JFrame("Guess Movie Game message");

         //construct components
        guessed_label = new JLabel("Guessed : " + guessed_count);
        not_guessed_label = new JLabel("Not guessed : " + not_guessed_count);
        omitted_label = new JLabel("Omitted : " + omitted_count);
        start_button = new JButton ("START");
        next_button = new JButton ("NEXT");
        answer_button = new JButton ("ANSWER");
        rBg = new ButtonGroup();
        rButton_option_0 = new JRadioButton ("..."); rBg.add(rButton_option_0);
        rButton_option_1 = new JRadioButton ("..."); rBg.add(rButton_option_1);
        rButton_option_2 = new JRadioButton ("..."); rBg.add(rButton_option_2);
        rButton_option_3 = new JRadioButton ("..."); rBg.add(rButton_option_3);
        rButton_option_4 = new JRadioButton ("..."); rBg.add(rButton_option_4);
        rButton_option_5 = new JRadioButton ("..."); rBg.add(rButton_option_5);
        //set layout      
        control_frame.setLayout (null);
        //add components
        control_frame.add (guessed_label);
        control_frame.add (not_guessed_label);
        control_frame.add (omitted_label);
        control_frame.add (start_button);
            start_button.addActionListener(this);
        control_frame.add (next_button);
            next_button.addActionListener(this);
        control_frame.add (answer_button);
            answer_button.addActionListener(this);
        control_frame.add (rButton_option_0);
        control_frame.add (rButton_option_1);
        control_frame.add (rButton_option_2);
        control_frame.add (rButton_option_3);
        control_frame.add (rButton_option_4);
        control_frame.add (rButton_option_5);
        //set component bounds (only needed by Absolute Positioning)
        guessed_label.setBounds (10, 10, 100, 15);
        not_guessed_label.setBounds (10, 50, 100, 15);
        omitted_label.setBounds (10, 90, 100, 15);
        start_button.setBounds (200, 10, 100, 20);
        next_button.setBounds (200, 50, 100, 20);
        answer_button.setBounds (200, 90, 100, 20);
        next_button.setEnabled(false);
        answer_button.setEnabled(false);
        rButton_option_0.setBounds (10, 150, 200, 25);
        rButton_option_1.setBounds (10, 180, 200, 25);
        rButton_option_2.setBounds (10, 210, 200, 25);
        rButton_option_3.setBounds (210, 150, 200, 25);        
        rButton_option_4.setBounds (210, 180, 200, 25);
        rButton_option_5.setBounds (210, 210, 200, 25);
        // frame 1 tuning
        control_frame.setSize(400,300);
        control_frame.setLocation(100, 100);
        control_frame.setResizable(false);
        control_frame.setVisible (true);
        control_frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
    
    void LoadVariants(String v[]) {
        // set variants in Radiobuttons
        rButton_option_0.setText(v[0]);
        rButton_option_1.setText(v[1]);
        rButton_option_2.setText(v[2]);
        rButton_option_3.setText(v[3]);
        rButton_option_4.setText(v[4]);
        rButton_option_5.setText(v[5]);  
    }
    
    void NextMovie() {
        String var_movie_names[] = loadRandomMovieInfo.LoadMovieNames();
        LoadVariants(var_movie_names);
        Random rand = new Random();
        right_answer = var_movie_names[rand.nextInt(answer_variants_count)];
        loadRandomMovieImage.LoadRightAnswerImage(right_answer);
        image_frame.getContentPane().add(loadRandomMovieImage);
        image_frame.setSize(loadRandomMovieImage.getwidth(), loadRandomMovieImage.getheight());
        rBg.clearSelection();   
    }
    
    Boolean AnswerButton(){
        String coosen_answer = "";
        Boolean result = null;
        if (rButton_option_0.isSelected()) coosen_answer = rButton_option_0.getText();
        if (rButton_option_1.isSelected()) coosen_answer = rButton_option_1.getText();
        if (rButton_option_2.isSelected()) coosen_answer = rButton_option_2.getText();
        if (rButton_option_3.isSelected()) coosen_answer = rButton_option_3.getText();
        if (rButton_option_4.isSelected()) coosen_answer = rButton_option_4.getText();
        if (rButton_option_5.isSelected()) coosen_answer = rButton_option_5.getText();
        rBg.clearSelection();
        if (coosen_answer == right_answer) result = true;
            else result = false;
        return result;
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == start_button) {
            image_frame = new JFrame ("Movie Image");
            // frame 2 tuning
            image_frame.setSize(400,300);
            image_frame.setLocation(530, 100);
            image_frame.setResizable(false);
            image_frame.setVisible (true);
            image_frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 
                next_button.setEnabled(true);
                answer_button.setEnabled(true);
                start_button.setEnabled(false);
            NextMovie();
	}
                
        if (event.getSource() == next_button) {
                ++omitted_count;
                omitted_label.setText("Omitted : " + omitted_count);
            NextMovie();
	}
                
	if (event.getSource() == answer_button) {
            Boolean result = AnswerButton();
            if (result) {
                ++guessed_count;
                guessed_label.setText("Guessed : " + guessed_count);
                JOptionPane.showMessageDialog(MSGframe, "YES");
            } else {
                ++not_guessed_count;
                not_guessed_label.setText("Not guessed : " + not_guessed_count);
                JOptionPane.showMessageDialog(MSGframe, "NO");
                
            }
            NextMovie();
	}
    }
}

class LoadRandomMovieImage extends JPanel implements Const {
        String image = movie_base_dir;
        Image IMG;
    
    void LoadRightAnswerImage(String right_answer){
        Random rand = new Random();
        int pic_no = rand.nextInt(movie_pics_count)+1;
        IMG = new ImageIcon(image+"\\"+right_answer+"\\"+pic_no+".jpg").getImage();
    }
        
    public void paintComponent(Graphics g) {
	g.drawImage(IMG,0,0,this);
	//g.setColor(Color.red);
	//g.fillRect(150,150,100,100);
        repaint(); 

    }
    
    int getwidth(){
        int w = IMG.getWidth(this) + 15;
        return w;
    }
    
    int getheight(){
        int h = IMG.getHeight(this) + 40;
        return h;
    }
}

class LoadRandomMovieInfo implements Const{

    String[] LoadMovieNames(){
        String var_movie_names[] = new String[answer_variants_count];
        String all_movie_names[] = ReadF(movie_base_list);
        Random rand = new Random();
        int rand_movie_no = 0;
        for (int x =0; x < answer_variants_count; x++){
            do {
                rand_movie_no = rand.nextInt(movies_count);
            } while (all_movie_names[rand_movie_no] == null);
            var_movie_names[x] = all_movie_names[rand_movie_no];
            all_movie_names[rand_movie_no] = null;   
        }
        return var_movie_names;
    }
    
    
    String[] ReadF(String fileName) {
        String list_str[] = new String[movies_count];
        try {
            //создаем объект FileReader для объекта File
            FileReader reader = new FileReader(fileName);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader bufReader = new BufferedReader(reader);
            // считаем сначала первую строку
            int x = 0;
            String line = bufReader.readLine();
            while (line != null) {
                list_str[x] = line;
            // считываем остальные строки в цикле
            x++;
            line = bufReader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    return list_str;
    }

}