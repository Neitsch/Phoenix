import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';
import $ from 'jquery';
import {HOST} from '../CONSTANTS';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  getTs: function(id) {
    return this.props.testsuites.find(v => v.get("id") == id);
  },
  handleKeyPress: function(event) {
    if (event.key === 'Enter') {
      $.ajax({
        url: HOST+"/ts/addTc/"+this.props.params.id+"/"+event.target.value,
        type: "GET",
        success: function(data) {
          this.props.saveTestSuite(data);
          console.log("Done");
        }.bind(this),
        error: function(data) { $.notify("Problem encountered!" + data); console.error(data); }
      });
    }
  },
  enqueue: function(id) {
    $.ajax({
      url: "/en2?id="+id,
      type: "GET",
      success: function(data) { $.notify("Enqueued testsuite", "success");},
      error: function(data) { $.notify("Problem encountered!" + data); }
    });
  },
  render: function() {
    var ts = this.getTs(this.props.params.id);
    if(ts == undefined) {
      return <div></div>;
    }
    return <div>
      <button onClick={() => this.enqueue(this.props.params.id)}>Start</button>
      <form className="form-horizontal">
        <div className="form-group">
          <label htmlFor="input1" className="col-sm-2 control-label">Testsuite Name</label>
          <div className="col-sm-10">
            <input type="text" value={ts.get("name")} className="form-control" id="input1" placeholder="Simple Name"></input>
          </div>
        </div>
      </form>
      <ul className="list-group">
        {this.props.testcases.filter(x => {
            return ts.get("testcaseids").includes(x.get("id"));
          }
        ).map(x => <li className="list-group-item">{x.get("name")}</li>)}
      </ul>
      <form className="form-horizontal">
        <div className="form-group">
          <label htmlFor="input1" className="col-sm-2 control-label">Testcase ID</label>
          <div className="col-sm-10">
            <input type="text" className="form-control" id="input1" placeholder="ID" onKeyPress = {this.handleKeyPress}></input>
          </div>
        </div>
      </form>
    </div>;
  }
});

function mapStateToProps(state) {
  return {
    testsuites: state.getIn(["testsuites"]),
    testcases: state.getIn(["testcases"])
  };
}

export const TestSuiteEditContainer = connect(mapStateToProps, actionCreators)(Container);
