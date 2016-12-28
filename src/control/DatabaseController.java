package control;

import databaseINIT.Provider;
import entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author dandi on 28/12/16.
 */

class DatabaseController {

    private static DatabaseController instance = new DatabaseController();
    private static Provider provider = new Provider();

    private DatabaseController(){}

    static DatabaseController getInstance(){
        return instance;
    }

    ArrayList<Review> getPendingReviews() throws SQLException {
        Review nuovoArticolo;
        ArrayList<Review> array = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM ARTICLES.recensione WHERE TOCHECK = TRUE";
        try {
            Statement stmt = provider.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs == null){
            return null;
        }
        else{
            while(rs.next()){
                nuovoArticolo = Factory.getInstance().getReview();
                nuovoArticolo.setArticle(rs.getString("ARTICOLO"));
                nuovoArticolo.setOwner(rs.getString("PROPRIETARIO"));
                nuovoArticolo.setRating(rs.getInt("RAITNG"));
                nuovoArticolo.setUser(rs.getString("UTENTE"));
                nuovoArticolo.setReview(rs.getString("TESTO"));

                array.add(nuovoArticolo);
            }
            return array;
        }
    }

    void executeQuery(String sql) throws SQLException {
        Statement stmt = provider.getConnection().createStatement();
        stmt.executeUpdate(sql);
    }
}
