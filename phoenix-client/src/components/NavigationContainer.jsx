import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import { Link } from 'react-router';
import $ from 'jquery';
import {HOST} from '../CONSTANTS';
import { hashHistory } from 'react-router';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  createTestcase: function() {
    $.ajax({
      url: HOST+"/ts/create",
      type: "GET",
      success: function(data) {
        this.props.saveTestSuite(data);
        hashHistory.push("/testsuite/edit/"+data.id);
      },
      error: function(data) { $.notify("Problem encountered!" + data); console.error(data); }
    });
  },
  render: function() {
    return <nav className="navbar navbar-default navbar-fixed-top">
      <div className="container">
        <div className="navbar-header">
          <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span className="sr-only">Toggle navigation</span>
            <span className="icon-bar"></span>
            <span className="icon-bar"></span>
            <span className="icon-bar"></span>
          </button>
          <a className="navbar-brand" href="#">Phoenix</a>
        </div>
        <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul className="nav navbar-nav">
            <li role="presentation"><Link to="/">Home</Link></li>
            <li role="presentation"><Link to="testcase">Testcase</Link></li>
            <li role="presentation"><Link to="testresult">Testresult</Link></li>
            <li role="presentation" className="dropdown">
              <a className="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                Testsuite <span className="caret"></span>
            </a>
              <ul className="dropdown-menu">
                <li role="presentation"><Link to="testsuite">View</Link></li>
                <li role="presentation"><a href="#" onClick={() => this.createTestcase()}>Create new</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>;
  }
});


function mapStateToProps(state) {
  return {
  };
}

export default connect(mapStateToProps, actionCreators)(Container);
