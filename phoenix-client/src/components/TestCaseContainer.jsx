import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';
import $ from 'jquery';
import PanelContainer from './PanelContainer';
import SimplePanel from './SimplePanel';
import TableContainer from './TableContainer';
import {fromJS} from 'immutable';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  getHeaders: function() {
    return fromJS([{id: "id", title: "Testcase ID"},
        {id: "name",title: "Testcase Title"},
        {id: "run", title: "Run Testcase"}]);
  },
  getData: function() {
    return this.props.testcases.map(x => {
      var meta = fromJS({
        id: x.get("id"),
        onclick: {
          id: "run",
          title: "Enqueue",
          get: function() {
            $.ajax({
              url: "/en?id="+x.get("id"),
              type: "GET",
              success: function(data) { $.notify("Enqueued testcases", "success");},
              error: function(data) { $.notify("Problem encountered!" + data); }
            });
          }
        }
      });
      return x.set("meta", meta);
    });
  },
  render: function() {
    var TcPanelContent = <span>This table displays all available testcases.</span>;
    var TcPanelTable = <TableContainer data={this.getData()} headers={this.getHeaders()}></TableContainer>;
    return <PanelContainer title="Testcases" panel={TcPanelContent} footer={TcPanelTable}></PanelContainer>;
  }
});

function mapStateToProps(state) {
  console.log(state);
  return {
    testcases: state.getIn(["testcases"])
  };
}

export const TestCaseContainer = connect(mapStateToProps, actionCreators)(Container);
