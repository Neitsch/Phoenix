/**
 * Copyright 2015 Nigel Schuster. This class can store information on how to provision the system
 * and launching the application.
 */


package com.phoenix.to;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TestCaseSetup holds all information to provision the system and subsequently launch the
 * application.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TestCaseSetup implements Serializable {
  /**
   * List of downloads to perform to have all necessary files present.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private List<DownloadWorker> downloads;
  /**
   * Name of the JFrame, used by AssertJ to locate it in the Swing/AWT hierarchy
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String frameName;
  /**
   * Arguments to launch application, currently not used, as application is loaded into same VM.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String[] startArgs;
  /**
   * Class that holds the main method of our Java application.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String startClass;
  /**
   * Script to run immediately before Frame is launched
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String startupScript;
  /**
   * Timeout of the setup. Not implemented yet.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private long timeout;
}
