import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';
import $ from 'jquery';
import {HOST} from '../CONSTANTS';
import AutoComplete from 'react-autocomplete';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  getTs: function(id) {
    return this.props.testsuites.find(v => v.get("id") == id);
  },
  handleKeyPress: function(event) {
    if (event.key === 'Enter') {
      event.preventDefault();
      this.addTc(event.target.value);
    }
  },
  rmTc: function(id) {
    $.ajax({
      url: HOST+"/ts/rmTc/"+this.props.params.id+"/"+id,
      type: "GET",
      success: function(data) {
        this.props.saveTestSuite(data);
      }.bind(this),
    });
  },
  addTc: function(id) {
    console.log(id);
    $.ajax({
      url: HOST+"/ts/addTc/"+this.props.params.id+"/"+id,
      type: "GET",
      success: function(data) {
        this.props.saveTestSuite(data);
        console.log("Done");
      }.bind(this),
      error: function(data) { $.notify("Problem encountered!" + data); console.error(data); }
    });
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
    console.log(this.props.testcases.filter(x => {
        return !ts.get("testcaseids").includes(x.get("id"));
      }).toArray());
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
        ).map(x => <li className="list-group-item">{x.get("name")}<button onClick={() => this.rmTc(x.get("id"))}>Remove</button></li>)}
      </ul>
      <form className="form-horizontal" onSubmit={event => event.preventDefault()}>
        <div className="form-group">
          <label htmlFor="input2" className="col-sm-2 control-label">Testcase Name</label>
          <div className="col-sm-10">
            <AutoComplete
              items={this.props.testcases.filter(x => {
                  return !ts.get("testcaseids").includes(x.get("id"));
                }).toArray()}
                getItemValue={(item) => item.get("name")}
                renderItem={(item, isHighlighted) => (
                  <div
                    style={isHighlighted ? {} : {}}
                    key={item.get("id")}
                  >{item.get("name")}</div>
                )}
                onSelect={(value, item) => this.addTc(item.get("id"))}
                inputProps={{className: "form-control"}}
              />
          </div>
        </div>
      </form>
      <form className="form-horizontal">
        <div className="form-group">
          <label htmlFor="input3" className="col-sm-2 control-label">Testcase ID</label>
          <div className="col-sm-10">
            <input type="text" className="form-control" id="input3" placeholder="ID" onKeyPress = {this.handleKeyPress}></input>
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
