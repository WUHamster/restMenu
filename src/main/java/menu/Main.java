package menu;

/**
 * Created by WUHamster on 13.06.2016.
 */
public class Main {
    public static void main(String[] args) {
        MenuManipulator.createBaseMenu();
        //MenuManipulator.addDish();
        MenuManipulator.viewMenu();
        MenuManipulator.choosePrice();
        MenuManipulator.em.close();
        MenuManipulator.emf.close();

    }
}
