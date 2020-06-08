package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Class for connecting to database of coffee bar
public class DBCoffee extends Database {

    public DBCoffee() throws SQLException, ClassNotFoundException {
        super();
    }

    //if you want, you can access to your database but name of tables and tables' fields must match
    public DBCoffee(String conURL, String conUser, String conPassword) throws SQLException, ClassNotFoundException {
        super(conURL, conUser, conPassword);
    }

    //get how many times item was purchased
    public int getPurchased(int id) throws SQLException {
        ResultSet counter = super.query("SELECT times_purchased FROM coffee WHERE id = " + id);
        if(counter.next()) {
            return counter.getInt("times_purchased");
        }
        return  0;
    }

    //getting cost of an item from database
    public double getCost(int id) throws SQLException {
        ResultSet counter = super.query("SELECT cost FROM coffee WHERE id = " + id);

        if(counter.next()) {
            return counter.getDouble("cost");
        }
        return  0.0;
    }

    //adding new purchases
    public void setNewPurchase(int newPurchase, int id) throws SQLException {
        PreparedStatement ps = super.getCon().prepareStatement("UPDATE coffee SET times_purchased = ? WHERE id = ?");
        ps.setInt(1, newPurchase);
        ps.setInt(2, id);
        ps.executeUpdate();
        ps.close();
    }
}
