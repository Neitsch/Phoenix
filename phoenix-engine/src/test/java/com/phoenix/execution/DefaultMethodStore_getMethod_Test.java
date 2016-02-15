/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import com.phoenix.spi.GuiPackage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultMethodStore_getMethod_Test {
  @Mock
  ApplicationContext context;
  @InjectMocks
  DefaultMethodStore store;

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @After
  public void tearDown() throws Exception {}

  @Test
  public final void test() {
    final Map<String, Object> reg = new HashMap<>();
    reg.put("", new Sample());
    Mockito.when(this.context.getBeansWithAnnotation(GuiPackage.class)).thenReturn(reg);
    this.store.init();
    Method m = this.store.getMethod("Sample.MyMethod1");
    Assert.assertNotNull(m);
    m = this.store.getMethod("Sample.MyMethod4");
    Assert.assertNull(m);
    m = this.store.getMethod("Sample.MyMethod3");
    Assert.assertNotNull(m);
    m = this.store.getMethod("Sample.MyMethod2");
    Assert.assertNotNull(m);
  }
}
