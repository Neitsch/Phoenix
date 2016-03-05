import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';
import PanelContainer from './PanelContainer';
import SimplePanel from './SimplePanel';
import TableContainer from './TableContainer';
import {fromJS} from 'immutable';
import moment from 'moment';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  getHeaders: function() {
    return fromJS([{id: "tcId", title: "Testcase ID"},
        {id: "title",title: "Testcase Title"},
        {id: "end", title: "Finish time"}]);
  },
  getData: function() {
    return this.props.testresults.map(x => {
      var meta = fromJS({
        id: x.get("id"),
        rowClass: x.get("success")?"success":"danger"
      });
      return x.set("end", moment(x.get("end")).format("YYYY-MM-DD hh:mm:ss")).set("meta", meta);
    });
  },
  render: function() {
    console.log(this.props.testresults);
    console.log(this.getData());
    var TrPanelContent = <span>This table displays all available testresults.</span>;
    var TrPanelTable = <TableContainer data={this.getData()} headers={this.getHeaders()}></TableContainer>;
    return <PanelContainer title="Testresults" panel={TrPanelContent} footer={TrPanelTable}></PanelContainer>;
  }
});

function mapStateToProps(state) {
  return {
    testresults: state.getIn(["testresults"])
  };
}

export const TestResultContainer = connect(mapStateToProps, actionCreators)(Container);
