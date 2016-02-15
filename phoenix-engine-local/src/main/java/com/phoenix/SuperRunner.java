/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.phoenix.command.GuiEventDispatcher;
import com.phoenix.data.LocalRequestModule;
import com.phoenix.data.RemoteRequestModule;
import com.phoenix.data.ToRequestModule;
import com.phoenix.execution.StepExecutor;
import com.phoenix.execution.TcExecutor;
import com.phoenix.recorder.UserInterface;
import com.phoenix.recorder.UserInterface.PHASE;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseStep;
import com.phoenix.util.MyEventListener;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@XSlf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class SuperRunner implements MyEventListener<TestCaseStep> {
  ApplicationContext ctx;
  GuiEventDispatcher dispatcher;
  StepExecutor stepExecutor;
  TcExecutor executor;
  @NonFinal
  boolean doingStep;
  @NonFinal
  ExecutorService execServ;
  @NonFinal
  PHASE phase;
  @NonFinal
  int stepNum;
  @NonFinal
  TestCase tc;
  @NonFinal
  UserInterface userIntf;
  @NonFinal
  ToRequestModule requestModule;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.util.MyEventListener#event(java.lang.Object)
   * @since Feb 12, 2016
   */
  @Override
  public void event(final TestCaseStep e) {
    if (!this.doingStep && this.phase == PHASE.EXECUTION) {
      this.tc.getTcBody().getLines().add(this.stepNum, e);
      this.stepNum++;
      log.info("Added step " + e.toString() + " at position " + this.stepNum);
    }
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @param configLocation
   * @param intf
   * @since Feb 11, 2016
   */
  public void run(final String configLocation, final UserInterface intf) {
    this.doingStep = false;
    this.userIntf = intf;
    this.ctx.getAutowireCapableBeanFactory().autowireBean(intf);
    try {
      intf.phase(UserInterface.PHASE.PREPARATION);
      this.phase = PHASE.PREPARATION;
      this.tc = this.prepare();
      intf.phase(UserInterface.PHASE.SETUP);
      this.phase = PHASE.SETUP;
      this.executor.setUp(this.tc.getTcHead().getSetup());
      this.dispatcher.initialize(this);
      this.phase = PHASE.EXECUTION;
      intf.phase(UserInterface.PHASE.EXECUTION);
      if (this.execServ == null) {
        this.execServ = Executors.newSingleThreadExecutor();
      }
      final Semaphore sem = new Semaphore(0);
      while (this.stepNum < this.tc.getTcBody().getLines().size()) {
        final TestCaseStep step = this.tc.getTcBody().getLines().get(this.stepNum);
        log.info("Next step: " + step.toString());
        this.execServ.submit(() -> intf.proceed(sem));
        sem.acquire();
        this.doingStep = true;
        final ResultWithMessage message =
            this.stepExecutor.doStep(step, this.executor.getEnvironment());
        this.doingStep = false;
        intf.stepResult(message);
        this.stepNum++;
      }
      intf.phase(UserInterface.PHASE.TEAR_DOWN);
      this.phase = PHASE.TEAR_DOWN;
      this.executor.tearDown(null);
      intf.phase(UserInterface.PHASE.DONE);
      this.phase = PHASE.DONE;
      if (intf.saveTestCase()) {
        log.info("Saved Testcase as " + this.requestModule.saveTc(this.tc));
      }
    } catch (final Exception e) {
      log.catching(e);
    }
  }

  private TestCase prepare() throws Exception {
    final boolean createNew = this.userIntf.shouldCreateNew();
    final boolean remoteResource = this.userIntf.remoteResource();
    if (remoteResource) {
      this.requestModule = new RemoteRequestModule();
    } else {
      this.requestModule = new LocalRequestModule();
    }
    this.ctx.getAutowireCapableBeanFactory().autowireBean(this.requestModule);
    TestCase tc;
    if (createNew) {
      tc =
          TestCase.builder().tcBody(TestCaseBody.builder().lines(new ArrayList<>()).build())
              .tcHead(this.requestModule.requestHead(this.userIntf.getResourcePath())).build();
    } else {
      tc = this.requestModule.requestTestCase(this.userIntf.getResourcePath());
    }
    return tc;
  }
}
