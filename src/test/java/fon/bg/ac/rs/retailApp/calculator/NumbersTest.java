package fon.bg.ac.rs.retailApp.calculator;


import org.junit.jupiter.api.*;


public class NumbersTest {

    private final Numbers numbers= new Numbers();

    @Test
    void treeDigitSum() {
        int num=123;
        int sum=numbers.treeDigitsum(num);

       Assertions.assertEquals(6, sum);
    }

    @Test
    void testNumberFourDigits(){
        int num=1043;
        //int sum=numbers.treeDigitsum(num);

       RuntimeException ex= Assertions.assertThrows(RuntimeException.class, ()->{
            numbers.treeDigitsum(num);
        });
       Assertions.assertEquals("Invalid Number!", ex.getMessage());
    }

    @BeforeAll
    public static void setUp(){
        System.out.println("Pokrecemo testove");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("Zavrseni su testovi");
    }

    @BeforeEach
    public void doBeforeEach(){
        System.out.println("Ovo cemo ispisati pre svakog testa ");
    }
}
