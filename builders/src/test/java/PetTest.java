import org.junit.Test;
import static org.junit.Assert.*;

public class PetTest {
    @Test public void createPet() {
        // Pet pet = new Pet().setColor("white").setLegs(4).setName("Bobby"); does not compile
        Pet pet = (Pet) new Pet().setName("Bobby").setColor("white").setLegs(4);

        assertTrue(pet.getLegs() == 4);

        System.out.println("My pet is " + pet);
    }
}
