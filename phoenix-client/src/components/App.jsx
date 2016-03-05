import React from 'react';
import {ModalContainer} from './ModalContainer';

export default React.createClass({
  render: function() {
    return <div className="container">
      <div className="page-header clearfix">
        Test
      </div>
      <div className="jumbotron">
        {this.props.children}
      </div>
      <nav className="navbar navbar-default navbar-fixed-bottom">
        <div className="container">
          Test
        </div>
      </nav>
      <ModalContainer></ModalContainer>
    </div>;
  }
});
