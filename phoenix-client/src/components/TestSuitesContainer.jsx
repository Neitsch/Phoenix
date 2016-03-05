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
    return fromJS([{id: "id", title: "Testsuite ID"},
        {id: "name",title: "Testsuite Name"},
        {id: "detail", title: "Detail view"}]);
  },
  getData: function() {
    return this.props.testsuites.map(x => {
      var meta = fromJS({
        id: x.get("id")
      });
      return x.set("end", moment(x.get("end")).format("YYYY-MM-DD hh:mm:ss")).set("meta", meta);
    });
  },
  render: function() {
    var TrPanelContent = <span>This table displays all available suites.</span>;
    var TrPanelTable = <TableContainer data={this.getData()} headers={this.getHeaders()}></TableContainer>;
    return <PanelContainer title="Testsuites" panel={TsPanelContent} footer={TsPanelTable}></PanelContainer>;
  }
});

function mapStateToProps(state) {
  return {
    testsuites: state.getIn(["testsuites"])
  };
}

export const TestSuiteContainer = connect(mapStateToProps, actionCreators)(Container);
