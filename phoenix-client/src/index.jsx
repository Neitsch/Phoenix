import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route} from 'react-router';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import reducer from './reducer';
import App from './components/App';
import {MainContainer, Container} from './components/MainContainer';
import { hashHistory } from 'react-router';

const store = createStore(reducer);

const routes = <Route component={App}>
  <Route path="/" component={MainContainer} />
</Route>;

ReactDOM.render(
  <Provider store={store}>
    <Router history={hashHistory}>{routes}</Router>
  </Provider>,
  document.getElementById('app')
);
