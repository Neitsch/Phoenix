import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  render: function() {
    return <div className="main">
      <a className="btn btn-primary">Hi</a>
    </div>;
  }
});

function mapStateToProps(state) {
  return {
  };
}

export const MainContainer = connect(mapStateToProps, actionCreators)(Container);
