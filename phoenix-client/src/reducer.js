import {List, Map, fromJS} from 'immutable';

const INITIAL_STATE = Map();

function setState(state, newState) {
  return state.merge(newState);
}

function setTestCases(state, testcases) {
  return state.set("testcases", fromJS(testcases));
}

function setTestResults(state, testresults) {
  var tr = fromJS(testresults);
  tr = tr.sortBy(function(value, key) {
    console.log(value.get('end'));
    return - value.get('end');
  });
  return state.set("testresults", tr);
}

export default function(state = INITIAL_STATE, action) {
  switch(action.type) {
    case 'TESTCASES': return setTestCases(state, action.testcases);
    case 'TESTRESULTS': return setTestResults(state, action.testresults);
  }
  return state;
}
