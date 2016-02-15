import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';

export const Container = React.createClass({
  mixins: [PureRenderMixin],
  render: function() {
    return <div className="main">
      Hi
    </div>;
  }
});

function mapStateToProps(state) {
  return {
  };
}

export const MainContainer = connect(mapStateToProps, actionCreators)(Container);
