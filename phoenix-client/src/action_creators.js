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

export function setTestResults(testresults) {
  return {
    type: 'TESTRESULTS',
    testresults
  };
}
