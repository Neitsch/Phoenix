import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute} from 'react-router';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import reducer from './reducer';
import App from './components/App';
import {MainContainer, Container} from './components/MainContainer';
import { hashHistory } from 'react-router';
import $ from 'jquery';
import {HOST} from './CONSTANTS';
import {setTestCases, setTestResults, setTestSuite} from './action_creators';
import "notifyjs-browser";
import {TestCaseContainer} from './components/TestCaseContainer';
import {TestSuiteContainer} from './components/TestSuiteContainer';
import {TestSuiteEditContainer} from './components/TestSuiteEditContainer';
import {TestResultContainer} from './components/TestResultContainer';

const store = createStore(reducer);

$.get(HOST+"/tc", function(data) {
  store.dispatch(setTestCases(data));
});

$.get(HOST+"/tr", function(data) {
    store.dispatch(setTestResults(data));
});

$.get(HOST+"/ts", function(data) {
  store.dispatch(setTestSuite(data));
});

const routes = <Route component={App}>
  <Route path="/" component={MainContainer} />
  <Route path="/testcase" component={TestCaseContainer} />
  <Route path="/testsuite">
    <IndexRoute component={TestSuiteContainer} />
    <Route path="edit/:id" component={TestSuiteEditContainer} />
  </Route>
  <Route path="/testresult" component={TestResultContainer} />
</Route>;

ReactDOM.render(
  <Provider store={store}>
    <Router history={hashHistory}>{routes}</Router>
  </Provider>,
  document.getElementById('app')
);
