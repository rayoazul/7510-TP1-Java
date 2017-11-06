package test.java.ar.uba.fi.tdd.rulogic.model;

import main.java.ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseDeportivaTest {
	
private KnowledgeBase knowledgeBase;
	
	@Before
	public void setUp() throws Exception {
	knowledgeBase = new KnowledgeBase();
	String baseruta = "bin\\main\\resources\\basedeportiva.txt";
	knowledgeBase.cargarBase(baseruta);
	knowledgeBase.parsear();
	}
	
	@Test
	public void test1()
	{
		Assert.assertTrue(this.knowledgeBase.answer("nombre(ginobili)."));
	}
	@Test
	public void test2()
	{
		Assert.assertTrue(this.knowledgeBase.answer("nombre(maradona)."));
	}
	@Test
	public void test3()
	{
		Assert.assertFalse(this.knowledgeBase.answer("nombre(perez)."));
	}
	@Test
	public void test4()
	{
		Assert.assertTrue(this.knowledgeBase.answer("nacimiento(maradona,1960)."));
	}
	@Test
	public void test5()
	{
		Assert.assertFalse(this.knowledgeBase.answer("nacimiento(monzon,1944)."));
	}
	@Test
	public void test6()
	{
		Assert.assertTrue(this.knowledgeBase.answer("nacimiento(vilas,1942)."));
	}
	@Test
	public void test7()
	{
		Assert.assertTrue(this.knowledgeBase.answer("jugador(vilas,tenis)."));
	}
	@Test
	public void test8()
	{
		Assert.assertFalse(this.knowledgeBase.answer("jugador(aymar,basquet)."));
	}
	@Test
	public void test9()
	{
		Assert.assertTrue(this.knowledgeBase.answer("jugador(monzon,boxeo)."));
	}
	@Test
	public void test10()
	{
		Assert.assertTrue(this.knowledgeBase.answer("jugadorNacimiento(maradona,1960)."));	
	}
	@Test
	public void test11()
	{
		Assert.assertTrue(this.knowledgeBase.answer("jugadorNacimiento(ginobili,1977)."));
	}
	@Test
	public void test12()
	{
		Assert.assertFalse(this.knowledgeBase.answer("jugadorNacimiento(ginobili,1970)."));
	}
	@Test
	public void test13()
	{
		Assert.assertFalse(this.knowledgeBase.answer("jugadorNacimiento(monzon,1977)."));
	}
	@Test
	public void test14()
	{
		Assert.assertTrue(this.knowledgeBase.answer("juegaAl(maradona,1960,futbol)."));
	}
	@Test
	public void test15()
	{
		Assert.assertFalse(this.knowledgeBase.answer("juegaAl(ginobili,1960,futbol)."));
	}
	@Test
	public void test16()
	{
		Assert.assertFalse(this.knowledgeBase.answer("juegaAl(vilas,1924,tenis)."));
	}
	@Test
	public void test17()
	{
		Assert.assertTrue(this.knowledgeBase.answer("juegaAl(aymar,1977,hockey)."));
	}
}
