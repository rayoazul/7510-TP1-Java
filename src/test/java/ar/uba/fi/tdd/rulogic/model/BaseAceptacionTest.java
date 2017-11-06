package test.java.ar.uba.fi.tdd.rulogic.model;

import main.java.ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class BaseAceptacionTest {

	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
	knowledgeBase = new KnowledgeBase();
	String baseruta = "bin\\main\\resources\\baseaceptacion.txt";
	knowledgeBase.cargarBase(baseruta);
	knowledgeBase.parsear();
	}

	//Interpreter facts
	@Test
	public void test1 () {

		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)."));
	}
	@Test
	public void test2 () {

		Assert.assertFalse(this.knowledgeBase.answer("varon(maria)."));
	}
	
	@Test
	public void test3 () {
		Assert.assertTrue(this.knowledgeBase.answer("mujer(cecilia)."));
	}
	@Test
	public void test4 () {

		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)."));
	}
	@Test
	public void test5 () {

		Assert.assertTrue(this.knowledgeBase.answer("padre(juan,pepe)."));
	}
	@Test
	public void test6 () {
		Assert.assertFalse(this.knowledgeBase.answer("padre(mario,pepe)."));
	}
	@Test
	public void test7 () {

		Assert.assertTrue(this.knowledgeBase.answer("padre(hector,maria)."));
	}
	@Test
	public void test8 () {
		Assert.assertFalse(this.knowledgeBase.answer("padre(jose,carlos)."));
	}
	@Test
	public void test9 () {
		Assert.assertFalse(this.knowledgeBase.answer("mujer(hector)."));
	}
	//Interpreter Rules
	@Test
	public void test10 () {
		Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe,juan)."));
	}
	@Test
	public void test11 () {
		Assert.assertFalse(this.knowledgeBase.answer("hija(maria,roberto)."));
	}
	@Test
	public void test12 () {
		Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe,juan)."));
	}
	@Test
	public void test13 () {
		Assert.assertTrue(this.knowledgeBase.answer("hija(maria,hector)."));
	}
	@Test
	public void test14 () {
		Assert.assertTrue(this.knowledgeBase.answer("hijo(alejandro,roberto)."));
	}
	@Test
	public void test15 () {
		Assert.assertFalse(this.knowledgeBase.answer("hijo(alejandro,maria)."));
	}
	
	
}
