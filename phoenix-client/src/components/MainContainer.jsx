import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';
import $ from 'jquery';
import {HOST} from '../CONSTANTS';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  getTestcases: function() {
    return this.props.testcases || [];
  },
  getTestresults: function() {
    return this.props.testresults || [];
  },
  enqueue: function(id) {
    $.ajax({
      url: HOST+"/tc/enqueue/"+id,
      type: "GET",
      success: function(data) { $.notify("Enqueued testcases", "success");},
      error: function(data) { $.notify("Problem encountered!" + data); }
    });
  },
  render: function() {
    return <div className="main">
      <div className="panel panel-default">
        <div className="panel-body">
          This table displays all available testcases.
        </div>
        <div className="table-responsive">
          <table className="table table-hover">
            <thead>
              <tr>
                <th>ID</th>
                <th>Testcase Title</th>
                <th>Run Testcase</th>
              </tr>
            </thead>
            <tbody>
              {this.getTestcases().map(entry =>
                <tr key={entry.get("id")}>
                  <td>{entry.get("id")}</td>
                  <td>{entry.get("name")}</td>
                  <td>
                    <a className="btn btn-primary" onClick={() => this.enqueue(entry.get("id"))}>
                      Enqueue
                    </a>
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
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
                </tr>
              </thead>
              <tbody>
                {this.getTestresults().map(entry =>
                  <tr key={entry.get("id")} className={entry.get("success")?"success":"danger"}>
                    <td>{entry.get("tcId")}</td>
                    <td>{entry.get("title")}</td>
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
    testcases: state.getIn(["testcases"]),
    testresults: state.getIn(["testresults"])
  };
}

export const MainContainer = connect(mapStateToProps, actionCreators)(Container);
