package zfaria.swingy.hero;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

public class HeroTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nameLength() {
        Hero hero = new Hero("bob", "warrior");
        assertEquals(0, validator.validate(hero).size());
        Hero h = new Hero("bo", "warrior");
        assertEquals(1, validator.validate(h).size());
    }

    @Test
    public void testClass() {
        Hero hero = new Hero("robert", "warrior");
        assertEquals(0, validator.validate(hero).size());

        hero = new Hero("roberto", "war");
        assertEquals(1, validator.validate(hero).size());
    }

    @Test
    public void shouldWork() {
        Hero hero = new Hero("Robert", "warrior");
        assertEquals(0, validator.validate(hero).size());
    }

    @Test
    public void xpTest() {
        Hero h = new Hero("Robert","warrior");
        h.reward(400);
        assertEquals(1, h.getLevel());
        h.reward(600);
        assertEquals(2, h.getLevel());
    }

}