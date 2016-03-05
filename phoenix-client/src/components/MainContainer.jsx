import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';
import $ from 'jquery';
import {TestCaseContainer} from './TestCaseContainer';
import {TestResultContainer} from './TestResultContainer';
import {TestSuiteContainer} from './TestSuiteContainer';
import {fromJS} from 'immutable';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  toogleModal: function() {

      console.log(this.props.toogleModal);
      this.props.toogleModal();
  },
  render: function() {
    return <div className="main">
    <button onClick={this.toogleModal}></button>
      <TestCaseContainer></TestCaseContainer>
      <TestResultContainer></TestResultContainer>
      <TestSuiteContainer></TestSuiteContainer>
    </div>;
  }
});

function mapStateToProps(state) {
  return {
  };
}

export const MainContainer = connect(mapStateToProps, actionCreators)(Container);
