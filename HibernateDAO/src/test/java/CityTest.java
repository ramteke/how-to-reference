
import hibernate.City;
import hibernate.CityDAOImpl;

import java.util.List;


/**
 * @author skynet
 */
public class CityTest {

    public static void main(String args[]) throws Exception {

        Class.forName("org.postgresql.Driver");

        City city = new City("Bombay", "Maharashtra");   //Save Object
        CityDAOImpl cityDAO = new CityDAOImpl();
        cityDAO.persist(city);

        City mumbai = cityDAO.findByCityName("Bombay");  //Update Object
        mumbai.setCountry("India");
        cityDAO.update(mumbai);


        City blr = new City("Bangalore", "India");       //Save Object
        cityDAO.persist(blr);

        List<City> cityList  = cityDAO.findAll();        //Get list of all cities
        System.out.println("Found " + cityList.size() + "  cities");


        for ( City c : cityList) {
            System.out.println("Deleting City: " + c.getName());
            cityDAO.delete(c);                         //Delete Object
        }


        cityDAO.closeSession();

        System.exit(0);

    }



}
