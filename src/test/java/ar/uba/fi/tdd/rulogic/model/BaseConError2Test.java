package test.java.ar.uba.fi.tdd.rulogic.model;

import org.junit.Before;

import main.java.ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseConError2Test 
{
	private KnowledgeBase knowledgeBase;
	
	//En este caso el parseo fallara
	//ya que no está definido los hechos con 'padre'
	@Before
	public void setUp() throws Exception {
	knowledgeBase = new KnowledgeBase();
	String baseruta = "bin\\main\\resources\\baseconerror2.txt";
	knowledgeBase.cargarBase(baseruta);
	knowledgeBase.parsear();
	}
	
	//En caso que la base parseada no sea valida, 
	//todas las consultas devuelven false.
	
	@Test
	public void test1()
	{
		Assert.assertFalse(this.knowledgeBase.answer("varon(juan)."));
	}
	@Test
	public void test2()
	{
		Assert.assertFalse(this.knowledgeBase.answer("mujer(maria)."));
	}
	@Test
	public void test3()
	{
		Assert.assertFalse(this.knowledgeBase.answer("hijo(pepe,juan)."));
	}
	
}
