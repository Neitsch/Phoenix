import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';
import {ModalContainer} from './ModalContainer';
import NavigationContainer from './NavigationContainer';

export default React.createClass({
  mixins: [PureRenderMixin],
  render: function() {
    return <div className="container">
        <NavigationContainer></NavigationContainer>
      <div className="container">
        <div className="jumbotron">
          {this.props.children}
        </div>
      </div>
      <nav className="navbar navbar-default navbar-fixed-bottom">
        <div className="container">
          <p className="navbar-text navbar-right">Copyright <a href="#" className="navbar-link">Nigel Schuster</a></p>
        </div>
      </nav>
      <ModalContainer></ModalContainer>
    </div>;
  }
});
