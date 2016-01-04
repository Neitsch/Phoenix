/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.net.URL;

import lombok.Data;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Data
public class TestCaseSetup {
  private String frameName;
  private URL jarUrl;
  private String startArgs;
  private String startClass;
}
