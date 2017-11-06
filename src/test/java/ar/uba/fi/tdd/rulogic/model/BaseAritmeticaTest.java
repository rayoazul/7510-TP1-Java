package test.java.ar.uba.fi.tdd.rulogic.model;

import main.java.ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseAritmeticaTest {
	
	private KnowledgeBase knowledgeBase;
	
	@Before
	public void setUp() throws Exception {
	knowledgeBase = new KnowledgeBase();
	String baseruta = "bin\\main\\resources\\basearitmetica.txt";
	knowledgeBase.cargarBase(baseruta);
	knowledgeBase.parsear();
	}
	
	@Test
	public void test1()
	{
		Assert.assertTrue(this.knowledgeBase.answer("dividir(100,5,20)."));
	}
	@Test
	public void test2()
	{
		Assert.assertTrue(this.knowledgeBase.answer("dividir(144,12,12)."));
	}
	@Test
	public void test3()
	{
		Assert.assertFalse(this.knowledgeBase.answer("dividir(9,3,3)."));
	}
	@Test
	public void test4()
	{
		Assert.assertFalse(this.knowledgeBase.answer("dividir(8,4,2)."));
	}
	@Test
	public void test5()
	{
		Assert.assertTrue(this.knowledgeBase.answer("multiplicar(5,20,100)."));
	}
	@Test
	public void test6()
	{
		Assert.assertTrue(this.knowledgeBase.answer("multiplicar(4,5,20)."));
	}
	@Test
	public void test7()
	{
		Assert.assertTrue(this.knowledgeBase.answer("multiplicar(12,12,144)."));
	}
	@Test
	public void test8()
	{
		Assert.assertFalse(this.knowledgeBase.answer("multiplicar(20,5,100)."));
	}
	@Test
	public void test9()
	{
		Assert.assertFalse(this.knowledgeBase.answer("multiplicar(5,4,20)."));
	}
	@Test
	public void test10()
	{
		Assert.assertFalse(this.knowledgeBase.answer("multiplicar(8,2,16)."));
	}
	@Test
	public void test11()
	{
		Assert.assertFalse(this.knowledgeBase.answer("multiplicar(8,3,21)."));
	}
	
}
