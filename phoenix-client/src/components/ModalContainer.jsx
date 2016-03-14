import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {connect} from 'react-redux';
import * as actionCreators from '../action_creators';
import {HOST} from '../CONSTANTS';
import Modal from 'react-modal';

export const ModalContainerInt = React.createClass({
  mixins: [PureRenderMixin],
  getModal: function() {
    console.log("Get modal: "+this.props.modal);
    return this.props.modal || [];
  },
  render: function() {
    return <div><Modal isOpen={this.getModal().get("open")}>
    <button onClick={this.props.toogleModal}></button>
    </Modal></div>;
    }
  });

  function mapStateToProps(state) {
    return {
      modal: state.getIn(["modal"])
    };
  }

  export const ModalContainer = connect(mapStateToProps, actionCreators)(ModalContainerInt);
