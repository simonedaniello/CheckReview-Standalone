package control;

import boundary.ListBoundary;
import boundary.ReviewBoundary;
import entity.Review;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author dandi on 28/12/16.
 */

public class AdminController {


    private static AdminController instance = new AdminController();

    private ArrayList<Review> array = null;

    private AdminController(){}

    public static AdminController getInstance(){
        return instance;
    }

    public DefaultListModel<String> update() throws SQLException {

        DefaultListModel<String> model = new DefaultListModel<>();
        array = DatabaseController.getInstance().getPendingReviews();

        for (Review anArray : array)
            model.addElement(anArray.getArticle());

        return model;
    }

    public void checkReview(int index, JFrame frame){

        frame.repaint();
        Review review = array.get(index);
        new ReviewBoundary(frame, review.getReview(), review.getOwner(), review.getRating(), review.getArticle(), review.getUser());
    }

    public void finishedReview(JFrame frame){

        frame.repaint();
        new ListBoundary(frame);
    }

    public void acceptReview(String articleName, String user) throws SQLException {
        String sql = "UPDATE ARTICLES.recensione SET TOCHECK = FALSE WHERE UPPER(UTENTE) " +
                "LIKE UPPER('"+ user +"') AND UPPER(ARTICOLO) LIKE UPPER('"+ articleName +"')";

        DatabaseController.getInstance().executeQuery(sql);
    }

    public void refuseReview(String articleName, String user) throws SQLException {
        String sql = "DELETE FROM ARTICLES.recensione WHERE UPPER(UTENTE) " +
                "LIKE UPPER('"+ user +"') AND UPPER(ARTICOLO) LIKE UPPER('"+ articleName +"')";

        DatabaseController.getInstance().executeQuery(sql);
    }

}
