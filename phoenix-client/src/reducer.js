import {List, Map, fromJS} from 'immutable';

const INITIAL_STATE = fromJS({modal:{open:false},testcases:[],testresults:[],testsuites:[]});

function setState(state, newState) {
  return state.merge(newState);
}

function setTestCases(state, testcases) {
  return state.set("testcases", fromJS(testcases));
}

function setTestSuites(state, testsuites) {
  return state.set("testsuites", fromJS(testsuites));
}

function setTestResults(state, testresults) {
  var tr = fromJS(testresults);
  tr = tr.sortBy(function(value, key) {
    return - value.get('end');
  });
  return state.set("testresults", tr);
}

function toogleModal(state) {
  console.log("Toogle Modal");
  return state.set("modal", fromJS({open: !state.get("modal").get("open")}));
}

export default function(state = INITIAL_STATE, action) {
  switch(action.type) {
    case 'TESTCASES': return setTestCases(state, action.testcases);
    case 'TESTRESULTS': return setTestResults(state, action.testresults);
    case 'TESTSUITES': return setTestSuites(state, action.testsuites);
    case 'TOOGLE_MODAL': return toogleModal(state);
  }
  return state;
}
