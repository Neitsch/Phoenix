import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';
import $ from 'jquery';
import {HOST} from '../CONSTANTS';
import moment from 'moment';
import PanelContainer from './PanelContainer';
import SimplePanel from './SimplePanel';
import TableContainer from './TableContainer';
import {TestCaseContainer} from './TestCaseContainer';
import {TestResultContainer} from './TestResultContainer';
import {fromJS} from 'immutable';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  getTestcases: function() {
    return this.props.testcases || [];
  },
  getTestresults: function() {
    return this.props.testresults || [];
  },
  getTestsuites: function() {
    return this.props.testsuites || [];
  },
  toogleModal: function() {

      console.log(this.props.toogleModal);
      this.props.toogleModal();
  },
  render: function() {
    return <div className="main">
    <button onClick={this.toogleModal}></button>
      <TestCaseContainer></TestCaseContainer>
      <TestResultContainer></TestResultContainer>
          <div className="panel panel-default">
            <div className="panel-body">
              This table displays testcase results.
            </div>
            <div className="table-responsive">
              <table className="table table-hover">
                <thead>
                  <tr>
                    <th>Testcase ID</th>
                    <th>Testcase Title</th>
                    <th>Start time</th>
                  </tr>
                </thead>
                <tbody>
                  {this.getTestsuites().map(entry =>
                    <tr key={entry.get("id")}>
                      <td>{entry.get("id")}</td>
                      <td>{entry.get("name")}</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>
    </div>;
  }
});

function mapStateToProps(state) {
  return {
    testsuites: state.getIn(["testsuites"])
  };
}

export const MainContainer = connect(mapStateToProps, actionCreators)(Container);
