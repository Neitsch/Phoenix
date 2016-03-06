import {List, Map, fromJS} from 'immutable';

const INITIAL_STATE = fromJS({modal:{open:false},testcases:{},testresults:{},testsuites:{}});

function toMap(testobject) {
  var map = Map();
  testobject.forEach(function(elem) {
    map = map.set(elem.id, fromJS(elem));
  });
  return map;
}

function setState(state, newState) {
  return state.merge(newState);
}

function setTestCases(state, testcases) {
  return state.set("testcases", toMap(testcases));
}

function setTestSuites(state, testsuites) {
  return state.set("testsuites", toMap(testsuites));
}

function setTestResults(state, testresults) {
  var tr = toMap(testresults);
  tr = tr.sortBy(function(value, key) {
    return - value.get('end');
  });
  return state.set("testresults", tr);
}

function addTestSuite(state, testsuite) {
  return state.push(testsuite);
}

function addTestCaseToTestSuite(state, testsuiteid, testcaseid) {
  if(state.getIn(["testsuites", testsuiteid, "testcaseids"]).includes(testcaseid) || !state.get("testcases").has(testcaseid)) return state;
  return state.updateIn(["testsuites", testsuiteid, "testcaseids"], val => val.push(testcaseid));
}

function toogleModal(state) {
  console.log("Toogle Modal");
  return state.set("modal", fromJS({open: !state.get("modal").get("open")}));
}

export default function(state = INITIAL_STATE, action) {
  switch(action.type) {
    case 'ADD_TESTCASE_TO_TESTSUITE': return addTestCaseToTestSuite(state, action.testsuiteid, action.testcaseid);
    case 'TESTCASES': return setTestCases(state, action.testcases);
    case 'TESTSUITE': return addTestSuite(state, action.testsuite);
    case 'TESTRESULTS': return setTestResults(state, action.testresults);
    case 'TESTSUITES': return setTestSuites(state, action.testsuites);
    case 'TOOGLE_MODAL': return toogleModal(state);
  }
  return state;
}
