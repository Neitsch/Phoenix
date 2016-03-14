//export function setState(state) {
//  return {
//    type: 'SET_STATE',
//    state
//  };
//}

export function setTestCases(testcases) {
  return {
    type: 'TESTCASES',
    testcases
  };
}

export function saveTestSuite(testsuite) {
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
