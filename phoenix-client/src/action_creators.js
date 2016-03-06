//export function setState(state) {
//  return {
//    type: 'SET_STATE',
//    state
//  };
//}

export function addTestCaseToTestSuite(testsuiteid, testcaseid) {
  return {
    type: 'ADD_TESTCASE_TO_TESTSUITE',
    testsuiteid,
    testcaseid
  }
}

export function setTestCases(testcases) {
  return {
    type: 'TESTCASES',
    testcases
  };
}

export function addTestSuite(testsuite) {
  return {
    type: 'TESTSUITE',
    testsuite
  }
}

export function toogleModal() {
  console.log("Toogle modal");
  return {
    type: 'TOOGLE_MODAL'
  };
}

export function setTestResults(testresults) {
  return {
    type: 'TESTRESULTS',
    testresults
  };
}

export function setTestSuite(testsuites) {
  return {
    type: 'TESTSUITES',
    testsuites
  };
}
