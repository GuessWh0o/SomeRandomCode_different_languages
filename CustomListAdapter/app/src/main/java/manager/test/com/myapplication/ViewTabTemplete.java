package manager.test.com.myapplication;
/**
 * Created by Maks on 9/8/2017.
 */

public class ViewTabTemplete {
    public String subjectName;
    public String correctAnswers;
    public String tasksCompleted;
    public int imageView;
    public int color;

    public ViewTabTemplete(String subjectName, String correctAnswers, String tasksCompleted, int imageView, int color) {
        this.subjectName = subjectName;
        this.correctAnswers = correctAnswers;
        this.tasksCompleted = tasksCompleted;
        this.imageView = imageView;
        this.color = color;
    }
}
