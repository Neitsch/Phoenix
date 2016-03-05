import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route} from 'react-router';
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

const store = createStore(reducer);

$.get(HOST+"/tc", function(data) {
  console.log(data);
  store.dispatch(setTestCases(data));
});

$.get(HOST+"/tr", function(data) {
  console.log("TR:");
  console.log(data);
  store.dispatch(setTestResults(data));
});

/*$.get(HOST+"/ts", function(data) {
  store.dispatch(setTestSuite(data));
});*/

const routes = <Route component={App}>
  <Route path="/" component={MainContainer} />
</Route>;

ReactDOM.render(
  <Provider store={store}>
    <Router history={hashHistory}>{routes}</Router>
  </Provider>,
  document.getElementById('app')
);
